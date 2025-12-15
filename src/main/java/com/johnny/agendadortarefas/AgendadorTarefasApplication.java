package com.johnny.agendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling   // ⬅ ADICIONE ESTA LINHA
public class AgendadorTarefasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendadorTarefasApplication.class, args);
    }
}

