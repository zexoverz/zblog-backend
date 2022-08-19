package com.server.zblog.req;

import com.server.zblog.bean.CommentDTO;
import com.server.zblog.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleCreationReq {

    private String title;
    private String content;
    private String imgUrl;
    private Long userId;
    private ArrayList<String> categories;
    private ArrayList<Long> likes;
    private ArrayList<CommentDTO> comments;
}
