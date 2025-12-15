package com.johnny.agendadortarefas.infrastructure.client;

import com.johnny.agendadortarefas.business.dto.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notificacao-service",
        url = "http://localhost:8082/email"
)
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody EmailDTO dto);
}
