package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("info")
public class InfoController {
    @Autowired
    private ServerProperties serverProperties;

    @GetMapping("/get-port")
    public Integer getPort() {
        return serverProperties.getPort();
    }

    @GetMapping("/get-number")
    @Operation(summary = "Получить число по формуле")
    public Integer getNumber() {
        return Stream.iterate(1, a -> a + 1).parallel().limit(1_000_000).reduce(0, Integer::sum);
    }
}
