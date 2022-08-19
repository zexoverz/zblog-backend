package com.server.zblog.req;

import lombok.Data;

@Data
public class UserCreationReq {
    private String username;
    private String password;
}
