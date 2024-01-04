package fr.eni.bonapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }


  @GetMapping("/inscription")
  public String inscription(){
    return "inscritpion";
  }
}
