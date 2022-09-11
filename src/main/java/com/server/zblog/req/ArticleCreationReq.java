package com.server.zblog.req;


import com.server.zblog.bean.CommentDTO;
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
    private ArrayList<CommentDTO> comments;
}
