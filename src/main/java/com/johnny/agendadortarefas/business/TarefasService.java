package com.johnny.agendadortarefas.business;

import com.johnny.agendadortarefas.business.dto.TarefasDTO;
import com.johnny.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.johnny.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.johnny.agendadortarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository repository;

    // ===========================================================
    // CREATE
    // ===========================================================
    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {

        TarefasEntity entity = new TarefasEntity();
        entity.setNomeTarefa(dto.getNomeTarefa());
        entity.setDescricao(dto.getDescricao());
        entity.setDataCriacao(LocalDateTime.now());
        entity.setDataEvento(dto.getDataEvento());
        entity.setEmailUsuario(dto.getEmailUsuario());
        entity.setStatusNotificacaoEnum(dto.getStatusNotificacaoEnum());
        entity.setDataAlteracao(LocalDateTime.now());

        entity = repository.save(entity);

        return toDTO(entity);
    }

    // ===========================================================
    // FIND BY PERIOD
    // ===========================================================
    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {

        return repository
                .findByDataEventoBetween(inicio, fim)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===========================================================
    // FIND BY EMAIL
    // ===========================================================
    public List<TarefasDTO> buscaTarefasPorEmail(String email) {

        return repository
                .findByEmailUsuario(email)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===========================================================
    // DELETE
    // ===========================================================
    public void deletaTarefaPorId(String id) {
        repository.deleteById(id);
    }

    // ===========================================================
    // UPDATE STATUS
    // ===========================================================
    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {

        TarefasEntity entity = repository.findById(id).orElseThrow();
        entity.setStatusNotificacaoEnum(status);
        entity.setDataAlteracao(LocalDateTime.now());

        repository.save(entity);

        return toDTO(entity);
    }

    // ===========================================================
    // UPDATE TASK
    // ===========================================================
    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {

        TarefasEntity entity = repository.findById(id).orElseThrow();

        entity.setNomeTarefa(dto.getNomeTarefa());
        entity.setDescricao(dto.getDescricao());
        entity.setDataEvento(dto.getDataEvento());
        entity.setDataAlteracao(LocalDateTime.now());
        entity.setStatusNotificacaoEnum(dto.getStatusNotificacaoEnum());

        repository.save(entity);

        return toDTO(entity);
    }

    // ===========================================================
    // CONVERTER
    // ===========================================================
    private TarefasDTO toDTO(TarefasEntity entity) {

        TarefasDTO dto = new TarefasDTO();
        dto.setId(entity.getId());
        dto.setNomeTarefa(entity.getNomeTarefa());
        dto.setDescricao(entity.getDescricao());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataEvento(entity.getDataEvento());
        dto.setEmailUsuario(entity.getEmailUsuario());
        dto.setDataAlteracao(entity.getDataAlteracao());
        dto.setStatusNotificacaoEnum(entity.getStatusNotificacaoEnum());

        return dto;
    }
}

