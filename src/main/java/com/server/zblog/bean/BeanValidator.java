package com.server.zblog.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.server.zblog.model.Article;
import com.server.zblog.model.User;
import com.server.zblog.req.ArticleCreationReq;
import com.server.zblog.req.UserCreationReq;
import org.springframework.stereotype.Component;

@Component
public class BeanValidator {
    public Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ArrayList<String> userValidate(UserCreationReq user) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<UserCreationReq>> constraintViolations = getValidator().validate(user);
        for (ConstraintViolation<UserCreationReq> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("username")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("password")) {
                arrayList.add(constraintViolation.getMessage());
            }
        }
        return arrayList;
    }

    public ArrayList<String> articleValidate(ArticleCreationReq article) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<ConstraintViolation<ArticleCreationReq>> constraintViolations = getValidator().validate(article);
        for (ConstraintViolation<ArticleCreationReq> constraintViolation : constraintViolations) {
            if (constraintViolation.getPropertyPath().toString().equals("userId")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("title")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("content")) {
                arrayList.add(constraintViolation.getMessage());
            }
            if (constraintViolation.getPropertyPath().toString().equals("imgUrl")) {
                arrayList.add(constraintViolation.getMessage());
            }
        }
        return arrayList;
    }
}
