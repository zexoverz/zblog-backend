package com.server.zblog.controller;

import com.server.zblog.bean.BeanValidator;
import com.server.zblog.bean.ResultDTO;
import com.server.zblog.model.User;
import com.server.zblog.req.UserCreationReq;
import com.server.zblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanValidator beanValidator;

    @Autowired
    BCryptPasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserCreationReq reqData) {
        System.out.println(":::  UserController.register :::");
        ResultDTO<?> responsePacket = null;

        // encode password
        reqData.setPassword(encoder.encode(reqData.getPassword()));

        try {
            ArrayList<String> errorList = beanValidator.userValidate(reqData);
            if (errorList.size() != 0) {
                ResultDTO<ArrayList<String>> errorPacket = new ResultDTO<>(errorList,
                        "Above fields values must not be empty", false);
                return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
            }
            User isData = userService.isDataExist(reqData.getUsername());
            if (isData == null) {
                User userCreate = new User();
                BeanUtils.copyProperties(reqData, userCreate);
                responsePacket = new ResultDTO<>(userService.createUser(userCreate), "User registered Successfully", true);
                return new ResponseEntity<>(responsePacket, HttpStatus.OK);
            } else {
                responsePacket = new ResultDTO<>("Record already exist", false);
                return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responsePacket = new ResultDTO<>(e.getMessage(), false);
            return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
        }
    }
}
