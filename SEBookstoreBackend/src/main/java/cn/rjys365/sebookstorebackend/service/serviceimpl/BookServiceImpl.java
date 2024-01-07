package cn.rjys365.sebookstorebackend.service.serviceimpl;

import cn.rjys365.sebookstorebackend.dao.BookDAO;
import cn.rjys365.sebookstorebackend.dao.TagDAO;
import cn.rjys365.sebookstorebackend.entities.Book;
import cn.rjys365.sebookstorebackend.entities.BookInfo;
import cn.rjys365.sebookstorebackend.entities.TagNode;
import cn.rjys365.sebookstorebackend.service.BookService;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;
    private final TagDAO tagDAO;

    private final JavaSparkContext javaSparkContext;

    public BookServiceImpl(BookDAO bookDAO, TagDAO tagDAO, JavaSparkContext javaSparkContext) {
        this.bookDAO = bookDAO;
        this.tagDAO = tagDAO;
        this.javaSparkContext = javaSparkContext;
    }

    public Iterable<Book> getAllBooks() {
        return this.bookDAO.getAllBooks();
    }

    @Override
    public List<Book> findBookByTitleContaining(String title) {
        return this.bookDAO.getBooksWithTitleContaining(title);
    }

    public Optional<Book> getBookById(Integer id) {
        return this.bookDAO.getBookById(id);
    }

    @Override
    public Optional<Book> saveBook(Book book) {
        return bookDAO.saveBook(book);
    }

    @Override
    public Boolean deleteBookById(Integer bookId) {
        Optional<Book> bookOptional = bookDAO.getBookById(bookId);
        if (bookOptional.isEmpty()) return false;
        return bookDAO.deleteBook(bookOptional.get());
    }

    @Override
    public List<TagNode> getRelatedTags(String name) {
        return this.tagDAO.getRelatedTags(name);
    }

    @Override
    public List<Book> getBooksWithRelatedTags(String name) {
        List<TagNode> relatedTags = this.tagDAO.getRelatedTags(name);
        if (!relatedTags.isEmpty()) {
            return this.bookDAO.getBooksWithTags(relatedTags.stream().map(TagNode::getName).toList());
        } else {
            return this.bookDAO.getBooksWithTags(List.of(name));
        }
    }

    @Override
    public Map<String, Map<String, Integer>> getWordCountByCategories(Set<String> wordsToCount) {
        Map<String, String> data = new HashMap<>();
        for (Book book : this.bookDAO.getAllBooks()) {
            BookInfo bookInfo = book.getBookInfo();
            if (bookInfo.getExtraInfo().containsKey("category")) {
                String category = bookInfo.getExtraInfo().get("category");
                if (!data.containsKey(category)) {
                    data.put(category, book.getIntroduction());
                } else {
                    data.put(category, data.get(category) + " " + book.getIntroduction());
                }
            }
        }
        return wordCount(data, wordsToCount);
    }

    private Map<String, Map<String, Integer>> wordCount(Map<String, String> data, Set<String> wordsToCount) {
        JavaPairRDD<String, String> dataRDD = javaSparkContext.parallelizePairs(data.entrySet().stream()
                .map(entry -> new Tuple2<>(entry.getKey(), entry.getValue())).toList());
        JavaPairRDD<String, Integer> mappedRDD = dataRDD.flatMapToPair(
                tuple2 -> {
                    String group = tuple2._1();
                    String text = tuple2._2();
                    StringTokenizer itr = new StringTokenizer(text);
                    List<Tuple2<String, Integer>> list = new ArrayList<>();
                    while (itr.hasMoreTokens()) {
                        String word = itr.nextToken();
                        if (wordsToCount.contains(word)) {
                            list.add(new Tuple2<>(group + ":" + word, 1));
                        }
                    }
                    return list.iterator();
                }
        );
        JavaPairRDD<String, Integer> reducedRDD = mappedRDD.reduceByKey(Integer::sum);
        JavaPairRDD<String, Tuple2<String, Integer>> groupAsKeyRDD = reducedRDD.mapToPair(
                tuple -> {
                    String[] split = tuple._1().split(":");
                    String group = split[0];
                    String word = split[1];
                    Integer count = tuple._2();
                    return new Tuple2<>(group, new Tuple2<>(word, count));
                }
        );
        JavaPairRDD<String, Iterable<Tuple2<String, Integer>>> groupedRDD = groupAsKeyRDD.groupByKey();
        Map<String, Iterable<Tuple2<String, Integer>>> result = groupedRDD.collectAsMap();
        Map<String, Map<String, Integer>> map = result.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    Iterable<Tuple2<String, Integer>> iterable = entry.getValue();
                    Map<String, Integer> valmap = new HashMap<>();
                    iterable.forEach(tuple -> valmap.put(tuple._1(), tuple._2()));
                    return valmap;
                }
        ));
        return map;
    }
}
