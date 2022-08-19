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

        Object User = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        System.out.println(User);
        ResultDTO<?> responsePacket = null;

        try {
            ArrayList<String> errorList = beanValidator.articleValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            Optional<User> user = userService.getUserById(reqData.getUserId());
            if (!user.isPresent()) {
                responsePacket = new ResultDTO<>("User not found", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            } else {
                Article article = new Article();

                BeanUtils.copyProperties(reqData, article);
                article.setUser(user.get());

                responsePacket = new ResultDTO<>(articleService.storeArticle(article), "Article created Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
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
}
