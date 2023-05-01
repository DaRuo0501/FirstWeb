package com.daruo.firstweb.controller;

import com.daruo.firstweb.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;
}
