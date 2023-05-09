package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.service.BoxService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoxController {

    @Autowired
    private BoxService boxService;

    // 刪除
    @GetMapping("/users/box/delete/{boxId}")
    public String deleteById(@PathVariable Integer boxId,
                             HttpServletRequest request,
                             HttpSession session
    ) {

        // 取得 當前使用者
        session = request.getSession();
        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

        boxService.deleteById(tempUser.getUserId(), boxId);

        return "redirect:/users/box";
    }

    // 收藏
    @GetMapping("/users/box/goToBag/{boxId}")
    public String goToBagById(@PathVariable Integer boxId,
                              HttpServletRequest request,
                              HttpSession session
    ) {

        // 取得 當前使用者
        session = request.getSession();
        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

        boxService.goToBagById(tempUser.getUserId(), boxId);


        return "redirect:/users/box";
    }
}
