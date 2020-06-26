package org.album.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @RequestMapping(value = {"/{path:[^\\.]+}/**"})
    public String redirect(HttpServletResponse responce) {
        responce.setHeader("content-Type", "text/plain");
        return "forward:/";
    }

}
