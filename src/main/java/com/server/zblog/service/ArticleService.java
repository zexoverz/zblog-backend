package com.server.zblog.service;

import com.server.zblog.model.Article;
import com.server.zblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Object storeArticle (Article reqData) {
        return articleRepository.save(reqData);
    }
}
