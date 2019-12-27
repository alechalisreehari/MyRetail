package com.myretail.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class SwaggerController {

	@RequestMapping ("/swagger")
    public String home() {
	return "redirect:/swagger-ui.html";
    }
}
