package com.templatestack.service;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;

import com.templatestack.exception.NotFoundException;
import com.templatestack.repository.ProjetistaRepository;
import com.templatestack.service.dto.mapper.ProjetistaResponseMapper;
import com.templatestack.service.dto.response.ProjetistaResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetistaService {

    private ProjetistaRepository projetistaRepository;
    private ProjetistaResponseMapper projetistaResponseMapper;

    public ProjetistaService(ProjetistaRepository projetistaRepository,
                             ProjetistaResponseMapper projetistaResponseMapper) {
        this.projetistaRepository = projetistaRepository;
        this.projetistaResponseMapper = projetistaResponseMapper;
    }

    @Transactional
    public List<ProjetistaResponse> getAll() {
        return projetistaRepository.findAll()
            .stream()
            .map(projetistaResponseMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProjetistaResponse findById(Long id) {
        return projetistaRepository.findById(id)
            .map(projetistaResponseMapper::toDto)
            .orElseThrow(() -> wrapNotFoundException(id));
    }

    private RuntimeException wrapNotFoundException(Long id) {
        throw new NotFoundException(format("Projetista %d not found.", id));
    }
}
