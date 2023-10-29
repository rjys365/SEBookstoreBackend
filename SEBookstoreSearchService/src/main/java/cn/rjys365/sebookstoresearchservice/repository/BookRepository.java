package cn.rjys365.sebookstoresearchservice.repository;

import cn.rjys365.sebookstoresearchservice.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findAllByAuthorIs(String author);
}
