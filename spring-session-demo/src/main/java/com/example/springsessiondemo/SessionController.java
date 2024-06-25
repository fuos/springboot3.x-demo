package com.example.springsessiondemo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/setSession")
    public String setSession(HttpSession session) {
        session.setAttribute("user", "G4O-X");
        return "Session set for user G4O-X";
    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {
        return "Session retrieved for user " + session.getAttribute("user");
    }
}
