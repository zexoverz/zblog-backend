package com.server.zblog.bean;

import com.server.zblog.model.User;
import lombok.Data;

@Data
public class LoginDTO {
    private String token;
    private String username;
}
