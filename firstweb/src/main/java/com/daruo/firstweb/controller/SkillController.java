package com.daruo.firstweb.controller;

import com.daruo.firstweb.dto.TempUser;
import com.daruo.firstweb.model.User;
import com.daruo.firstweb.service.SkillService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/skill/delete/{skillName}")
    public void delete(@PathVariable String skillName,
                         HttpServletRequest request,
                         HttpSession session
    ) {

        // 取得 當前使用者
        session = request.getSession();
        TempUser tempUser = (TempUser) session.getAttribute("showUserName");

//        skillService.remove(tempUser.getUserId(), skillName);

    }
}
