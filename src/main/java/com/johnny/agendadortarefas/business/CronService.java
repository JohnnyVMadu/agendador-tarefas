package com.johnny.agendadortarefas.business;

import com.johnny.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.johnny.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.johnny.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasRepository tarefasRepository;

    // Roda conforme o cron definido no application.properties
    @Scheduled(cron = "${cron.horario}")
    public void verificarTarefasPendentes() {

        LocalDateTime agora = LocalDateTime.now();
        log.info("CRON disparado às {}", agora);  // 👈 sempre vamos ver isso se o @Scheduled estiver funcionando

        List<TarefasEntity> tarefasPendentes =
                tarefasRepository.findByDataEventoBeforeAndStatusNotificacaoEnum(
                        agora,
                        StatusNotificacaoEnum.Pendente
                );

        if (tarefasPendentes.isEmpty()) {
            log.info("Nenhuma tarefa pendente para notificação neste ciclo.");
            return;
        }

        log.info("=== CRON EXECUTADO ===");
        log.info("Tarefas pendentes encontradas: {}", tarefasPendentes.size());

        tarefasPendentes.forEach(tarefa -> log.info(
                "Tarefa ID={} | Nome={} | Evento={} | Email={}",
                tarefa.getId(),
                tarefa.getNomeTarefa(),
                tarefa.getDataEvento(),
                tarefa.getEmailUsuario()
        ));
    }
}
