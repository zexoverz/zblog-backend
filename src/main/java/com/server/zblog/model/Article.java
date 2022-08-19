package com.server.zblog.model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.server.zblog.bean.CommentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "articles")

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Content must not be empty")
    @Lob
    private String content;

    @NotEmpty(message = "ImgUrl must not be empty")
    private String imgUrl;

    private ArrayList<String> categories;

    private ArrayList<Long> likes;

    private ArrayList<CommentDTO> comments;


    @CreationTimestamp //this adds the default timestamp on save
    private Timestamp createDate;
}
