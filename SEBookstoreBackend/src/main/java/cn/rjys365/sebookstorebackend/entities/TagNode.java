package cn.rjys365.sebookstorebackend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("BookstoreTag")
@Getter
@Setter
public class TagNode {
    @Id
    private Integer id;

    private String name;

    public TagNode(){}
}
