package com.server.zblog.bean;

import com.server.zblog.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private String username;
    private String comment;
}
