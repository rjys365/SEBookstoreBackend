package cn.rjys365.sebookstorebackend.dao;

import cn.rjys365.sebookstorebackend.entities.TagNode;

import java.util.List;

public interface TagDAO {
    List<TagNode> getRelatedTags(String name);
}
