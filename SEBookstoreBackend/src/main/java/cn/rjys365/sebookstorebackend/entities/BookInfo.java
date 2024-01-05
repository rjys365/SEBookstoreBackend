package cn.rjys365.sebookstorebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "book_info")
@Getter
@Setter
public class BookInfo {
    @Id
    @JsonIgnore
    private Integer id;

//    private Binary coverImage;

    private Map<String, String> extraInfo;

    public BookInfo(){
    }
}
