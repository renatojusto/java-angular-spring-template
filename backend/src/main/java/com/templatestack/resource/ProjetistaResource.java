package com.templatestack.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import com.templatestack.service.ProjetistaService;
import com.templatestack.service.dto.response.ProjetistaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/projetistas")
public class ProjetistaResource {

    private static final Logger log = LoggerFactory.getLogger(ProjetistaResource.class);

    private ProjetistaService projetistaService;

    public ProjetistaResource(ProjetistaService projetistaService) {
        this.projetistaService = projetistaService;
    }

    @GetMapping
    public List<ProjetistaResponse> getAllProjetistas() {
        log.info("Chamada Rest getAllProjetistas");
        List<ProjetistaResponse> list = projetistaService.getAll();
        list.forEach(this::addHateoasLink);
        return list;
    }

    @GetMapping("/{id}")
    public ProjetistaResponse getProjetista(@PathVariable Long id) {
        log.info("Chamada Rest getProjetista {}", id);
        ProjetistaResponse response = projetistaService.findById(id);
        addHateoasLink(response);
        return response;
    }

    private void addHateoasLink(ProjetistaResponse response) {
        response.add(linkTo(methodOn(ProjetistaResource.class).getProjetista(response.getId())).withSelfRel());
    }
}
