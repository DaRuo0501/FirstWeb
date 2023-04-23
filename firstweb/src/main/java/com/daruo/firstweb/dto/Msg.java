package com.daruo.firstweb.dto;

import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Request;

public class Msg {

    private String text;
    private Integer count = 0;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
