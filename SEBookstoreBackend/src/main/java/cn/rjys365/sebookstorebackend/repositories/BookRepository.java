package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
}