package com.johnny.agendadortarefas.infrastructure.repository;

import com.johnny.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.johnny.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    // Buscar tarefas por período (já existia)
    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    // Buscar tarefas pelo email (já existia)
    List<TarefasEntity> findByEmailUsuario(String email);

    // NOVO MÉTODO → usado pelo cron:
    // Busca todas as tarefas com dataEvento anterior OU igual ao horário atual
    // E com status PENDENTE
    List<TarefasEntity> findByDataEventoBeforeAndStatusNotificacaoEnum(
            LocalDateTime dataEvento,
            StatusNotificacaoEnum status
    );
}
