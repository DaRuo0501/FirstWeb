package com.daruo.firstweb.controller;

import com.daruo.firstweb.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoxController {

    @Autowired
    private BoxService boxService;
}
