package com.rosie.restdocsswaggerui.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HelloResponse {

    private List<String> messages;
    private String ps;

    public HelloResponse(final List<String> messages) {
        this.messages = messages;
    }

}
