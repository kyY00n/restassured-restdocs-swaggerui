package com.rosie.restdocsswaggerui.controller;

import com.rosie.restdocsswaggerui.controller.dto.HelloResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rosie")
public class RosieController {

    @GetMapping("/{name}")
    public ResponseEntity<HelloResponse> helloRosie(@PathVariable String name) {
        if (name.contains("코치")) {
            throw new RuntimeException("죄송해요.. 안녕히 가세요.");
        }

        HelloResponse body = new HelloResponse(
                List.of("Hi,", name, "Welcome", "to", "Rosie", "World")
        );
        return ResponseEntity.ok(body);
    }

}
