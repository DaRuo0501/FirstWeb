package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempBag;
import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.BagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BagController {

    @Autowired
    private BagService bagService;

}
