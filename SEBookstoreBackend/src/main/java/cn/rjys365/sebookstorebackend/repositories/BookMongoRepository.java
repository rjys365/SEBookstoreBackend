package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookMongoRepository extends MongoRepository<BookInfo, Integer> {
}
