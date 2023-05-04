package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.BagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BagController {

    private final static Logger log = LoggerFactory.getLogger(BagController.class);

    @Autowired
    private BagService bagService;

    // 刪除
    @GetMapping("/users/bag/delete/{bagId}")
    public String deleteById(@PathVariable Integer bagId,
                             HttpServletRequest request,
                             HttpSession session
    ) {

        // 取得 當前使用者
        session = request.getSession();
        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

        bagService.deleteById(tempUser.getUserId(), bagId);

        return "redirect:/users/bag";
    }

    // 收藏
    @GetMapping("/users/bag/goToBox/{bagId}")
    public String goToBoxById(@PathVariable Integer bagId,
                              HttpServletRequest request,
                              HttpSession session
    ) {

        // 取得 當前使用者
        session = request.getSession();
        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

        bagService.goToBoxById(tempUser.getUserId(), bagId);


        return "redirect:/users/bag";
    }

}
