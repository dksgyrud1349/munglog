package com.munglog.munglog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.munglog.munglog.document.Log;

public interface LogRepository extends MongoRepository<Log, String> {
}
