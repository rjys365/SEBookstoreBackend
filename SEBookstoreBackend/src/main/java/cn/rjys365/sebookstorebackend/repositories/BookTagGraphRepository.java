package cn.rjys365.sebookstorebackend.repositories;

import cn.rjys365.sebookstorebackend.entities.TagNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTagGraphRepository extends Neo4jRepository<TagNode, Integer> {
    @Query("""
            match (startTag:BookstoreTag)-[:BOOKSTORE_TAG_RELATED_TO]->{1,2}(endTag:BookstoreTag)
            where startTag.name = $name
            return endTag as tag
            union
            match (startTag:BookstoreTag)
            where startTag.name = $name
            return distinct startTag as tag
                                            """)
    List<TagNode> getRelatedTags(@Param("name") String name);
}
