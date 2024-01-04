package fr.eni.bonapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
  Logger logger = LoggerFactory.getLogger(SecurityController.class);

  @GetMapping("/login")
  public String login() {
    logger.info("Dans le login");
    return "login";
  }
}
