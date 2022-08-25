package com.server.zblog.req;


import lombok.Data;

import java.util.ArrayList;

@Data
public class ArticleCreationReq {

    private String title;
    private String content;
    private String imgUrl;
    private String description;
    private String createdBy;
    private ArrayList<String> categories;
    private ArrayList<String> views;
}
