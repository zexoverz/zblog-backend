package com.server.zblog.controller;


import com.server.zblog.bean.BeanValidator;
import com.server.zblog.bean.ResultDTO;
import com.server.zblog.model.Article;
import com.server.zblog.model.User;
import com.server.zblog.req.ArticleCreationReq;
import com.server.zblog.service.ArticleService;
import com.server.zblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BeanValidator beanValidator;

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody ArticleCreationReq reqData) {
        System.out.println(":::  ArticleController.create :::");

        ResultDTO<?> responsePacket = null;

        try {
            ArrayList<String> errorList = beanValidator.articleValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            User user = userService.isDataExist(reqData.getCreatedBy());
            if (user == null) {
                responsePacket = new ResultDTO<>("User not found", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            } else {
                Article article = new Article();

                BeanUtils.copyProperties(reqData, article);
                article.setUser(user);

                responsePacket = new ResultDTO<>(articleService.storeArticle(article), "Article created Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{articleId}")
    public ResponseEntity<?> updateArticle(@PathVariable("articleId") Long articleId, @RequestBody ArticleCreationReq reqData) {
        System.out.println(":::  ArticleController.update :::");

        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        ResultDTO<?> responsePacket = null;

        try {
            Optional<Article> article = articleService.getArticle(articleId);

            if (!principal.toString().equals(article.get().getUser().getUsername())){
                responsePacket = new ResultDTO<>("You are not authorized", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            } else if(!article.isPresent()){
                responsePacket = new ResultDTO<>("Article not found", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            }else {
                Article articleDb = article.get();

                BeanUtils.copyProperties(reqData, articleDb);
                responsePacket = new ResultDTO<>(articleService.storeArticle(articleDb), "Article updated Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateView/{articleId}")
    public ResponseEntity<?> updateView(@PathVariable("articleId") Long articleId) {
        System.out.println(":::  ArticleController.updateView :::");

        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        ResultDTO<?> responsePacket = null;

        try {
            Optional<Article> article = articleService.getArticle(articleId);
             if(!article.isPresent()){
                responsePacket = new ResultDTO<>("Article not found", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            }else {
                Article articleDb = article.get();
                ArrayList<String> views = articleDb.getViews();

                if(views.contains(principal)){
                    responsePacket = new ResultDTO<>("User Already Viewed", false);
                    return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
                }else{
                    views.add((String) principal);
                    articleService.storeArticle(articleDb);
                    responsePacket = new ResultDTO<>(null, "First View!!", true);
                    return new ResponseEntity<>(responsePacket, HttpStatus.OK);
                }

            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getArticles() {
        System.out.println(":::  ArticleController.getArticles :::");
        ResultDTO<?> responsePacket = null;

        try {
            responsePacket = new ResultDTO<>(articleService.getAll(), "Article fetch Successfully", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getDetail/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable Long articleId) {
        System.out.println(":::  ArticleController.getArticle :::");
        ResultDTO<?> responsePacket = null;

        try {
            responsePacket = new ResultDTO<>(articleService.getArticle(articleId), "Get Detail Article Successfully", true);
            return new ResponseEntity<>(responsePacket, HttpStatus.OK);
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        System.out.println(":::  ArticleController.delete :::");

        Object principal = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        ResultDTO<?> responsePacket = null;

        try {
            Optional<Article> article = articleService.getArticle(id);

            if (!principal.toString().equals(article.get().getUser().getUsername())){
                responsePacket = new ResultDTO<>("You are not authorized", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            } else if(!article.isPresent()){
                responsePacket = new ResultDTO<>("Article not found", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            }else {
                articleService.deleteArticle(id);
                responsePacket = new ResultDTO<>(null, "Delete Article Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }

    }
}
