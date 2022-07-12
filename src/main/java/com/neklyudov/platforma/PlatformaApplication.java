package com.neklyudov.platforma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PlatformaApplication {
//    static { System.setProperty("logback.xml", "C:\\Users\\user\\platforma\\src\\main\\resources\\logging\\logback.xml");}

    private static final Logger logger = LoggerFactory.getLogger(PlatformaApplication.class);

    public static void main(String[] args) throws IOException {
        logger.info("Приложение запустилось!");
        SpringApplication.run(PlatformaApplication.class, args);
        logger.info("Приложение завершило работу!");
    }

}
