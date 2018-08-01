package com.cbc.myblog.ui.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author cbc
 */
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Long userId;

    private Long blogId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
