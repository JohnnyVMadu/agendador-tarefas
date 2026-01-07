package com.johnny.agendadortarefas.business.mapper;

import com.johnny.agendadortarefas.business.dto.TarefasDTO;
import com.johnny.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    // DTO -> Entity
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    // Entity -> DTO
    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    // Lista DTO -> Entity
    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

    // Lista Entity -> DTO
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}

