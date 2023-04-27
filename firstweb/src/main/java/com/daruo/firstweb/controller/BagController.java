package com.daruo.firstweb.controller;

import com.daruo.firstweb.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BagController {

    @Autowired
    private BagService bagService;
}
