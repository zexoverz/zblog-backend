package com.server.zblog.service;

import com.server.zblog.model.Article;
import com.server.zblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Article> getArticle(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
