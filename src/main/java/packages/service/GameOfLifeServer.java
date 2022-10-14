package packages.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfLifeServer {

    public static void main(String[] args) {
        SpringApplication.run(GameOfLifeServer.class, args);
    }
}