package com.globi.rpd.rest;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpdController {

    @RequestMapping("/addcolumn")
    public String addColumn() {
        return "Can't do it now, Sorry";
    }
}