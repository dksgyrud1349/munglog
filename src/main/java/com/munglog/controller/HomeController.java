package com.munglog.controller;

import com.munglog.document.Log;
import com.munglog.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    public final LogRepository logRepository;

    @GetMapping("/")
    public String home() {

        Log log = new Log();
        log.setMessage("hello");

        logRepository.save(log);

        return "index";

    }
}
