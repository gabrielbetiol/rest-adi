package com.curso.osworks.api.controller;

import com.curso.osworks.api.model.ComentarioDTO;
import com.curso.osworks.api.model.ComentarioInput;
import com.curso.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.curso.osworks.domain.model.Comentario;
import com.curso.osworks.domain.model.OrdemServico;
import com.curso.osworks.domain.repository.OrdemServicoRepository;
import com.curso.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDTO adicionar(@PathVariable Long ordemServicoId,
                                   @Valid @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId,
                comentarioInput.getDescricao());
        return toDTO(comentario);
    }

    @GetMapping
    public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        return toCollectionDTO(ordemServico.getComentarios());
    }

    private ComentarioDTO toDTO(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioDTO.class);
    }

    private List<ComentarioDTO> toCollectionDTO(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toDTO(comentario))
                .collect(Collectors.toList());
    }
}
