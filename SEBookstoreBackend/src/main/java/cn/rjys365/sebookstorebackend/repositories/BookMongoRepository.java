package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookMongoRepository extends MongoRepository<BookInfo, Integer> {
    @Query("{'tags': {$in: ?0}}")
    List<BookInfo> findByTagsIn(List<String> tags);
}
