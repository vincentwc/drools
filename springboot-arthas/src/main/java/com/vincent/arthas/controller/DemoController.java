package com.vincent.arthas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author wang_cheng
 * @date 2022/11/10 16:47
 * @desc
 **/
@RestController
@RequestMapping("/arthas/demo")
public class DemoController {

    @GetMapping("/randomInteger")
    public Integer getRandomInteger() {
        return new Random().nextInt(1000);
    }


}
