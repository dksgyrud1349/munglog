package com.munglog.document;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "login_log")
@Getter @Setter
public class LoginLog {

    @Id
    private String id;

    private String userId;
}
