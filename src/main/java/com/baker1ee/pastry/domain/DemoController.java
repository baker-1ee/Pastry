package com.baker1ee.pastry.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("hello world!!!");
    }
}
