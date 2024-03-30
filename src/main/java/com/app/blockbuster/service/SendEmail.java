package com.app.blockbuster.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendEmail {
    public void send(List<String> emails) {
        //sending emails
        emails.stream().forEach(System.out::println);
    }
}
