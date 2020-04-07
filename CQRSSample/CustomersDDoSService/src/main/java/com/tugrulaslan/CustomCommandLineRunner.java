package com.tugrulaslan;

import com.tugrulaslan.service.RandomRequestSenderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {
    private final RandomRequestSenderService randomRequestSenderService;

    public CustomCommandLineRunner(RandomRequestSenderService randomRequestSenderService) {
        this.randomRequestSenderService = randomRequestSenderService;
    }

    @Override
    public void run(String... args) throws Exception {
        randomRequestSenderService.send();
    }
}
