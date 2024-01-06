package cn.rjys365.sebookstorebackend.dao.daoimpl;

import cn.rjys365.sebookstorebackend.dao.TagDAO;
import cn.rjys365.sebookstorebackend.entities.TagNode;
import cn.rjys365.sebookstorebackend.repositories.BookTagGraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {
    private final BookTagGraphRepository bookTagGraphRepository;

    public TagDAOImpl(BookTagGraphRepository bookTagGraphRepository) {
        this.bookTagGraphRepository = bookTagGraphRepository;
    }

    @Override
    public List<TagNode> getRelatedTags(String name) {
        return this.bookTagGraphRepository.getRelatedTags(name);
    }
}
