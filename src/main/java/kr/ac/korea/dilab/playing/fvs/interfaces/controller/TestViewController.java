package kr.ac.korea.dilab.playing.fvs.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Koo Lee on 3/26/2015.
 */
@Controller
public class TestViewController {

    @RequestMapping("/test.thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Koo");
        return "test";
    }

    @RequestMapping("/test.json")
    @ResponseBody
    public Object json() {
        HashMap json = new HashMap<>();
        json.put("name", "Koo");
        return json;
    }
}
