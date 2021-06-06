package com.templatestack.service.dto.mapper;

import com.templatestack.domain.Projetista;
import com.templatestack.service.dto.response.ProjetistaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjetistaResponseMapper extends EntityMapper<Projetista, ProjetistaResponse> {

}
