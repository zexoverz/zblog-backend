package com.server.zblog.service;

import com.server.zblog.model.Comment;
import com.server.zblog.repository.ArticleRepository;
import com.server.zblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;


    public Object storeComment (Comment reqData) {
        return commentRepository.save(reqData);
    }
}
