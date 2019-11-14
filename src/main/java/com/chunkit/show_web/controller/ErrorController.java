package com.chunkit.show_web.controller;

import com.chunkit.show_web.util.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther ChunKit
 * @date 2019/10/24-9:15
 */
@RestController
public class ErrorController {

    @GetMapping("/error")
    public Msg error() {
        return Msg.failure();
    }

}
