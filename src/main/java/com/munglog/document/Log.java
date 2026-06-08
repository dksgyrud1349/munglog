package com.munglog.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "log")
@Getter
@Setter
public class Log {

    @Id
    private String id;

    private String message;
}
