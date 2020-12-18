package com.curso.osworks.api.controller;

import com.curso.osworks.api.model.OrdemServicoDTO;
import com.curso.osworks.api.model.OrdemServicoInput;
import com.curso.osworks.domain.model.OrdemServico;
import com.curso.osworks.domain.service.GestaoOrdemServicoService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = gestaoOrdemServicoService.toEntity(ordemServicoInput);
        return gestaoOrdemServicoService.toDTO(
                gestaoOrdemServicoService.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoDTO> listar() {
        return gestaoOrdemServicoService.toCollectionDTO(
                gestaoOrdemServicoService.listar());
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId) {
        return gestaoOrdemServicoService.buscar(ordemServicoId);
    }

    @PutMapping("/{ordemServicoId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long ordemServicoId) {
        gestaoOrdemServicoService.finalizar(ordemServicoId);
    }

}
