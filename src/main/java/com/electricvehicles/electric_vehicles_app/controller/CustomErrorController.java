// src/main/java/com/electricvehicles/electric_vehicles_app/ErrorController.java
package com.electricvehicles.electric_vehicles_app.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest, Model model) {
        Map<String, Object> errors = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        String errorMessage = (String) errors.get("error");
        model.addAttribute("errorMessage", errorMessage);
        return "error"; // This corresponds to the 'error.html' template
    }
}
