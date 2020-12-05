package com.curso.osworks.domain.service;

import com.curso.osworks.api.model.OrdemServicoDTO;
import com.curso.osworks.api.model.OrdemServicoInput;
import com.curso.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.curso.osworks.domain.exception.NegocioException;
import com.curso.osworks.domain.model.Cliente;
import com.curso.osworks.domain.model.Comentario;
import com.curso.osworks.domain.model.OrdemServico;
import com.curso.osworks.domain.model.StatusOrdemServico;
import com.curso.osworks.domain.repository.ClienteRepository;
import com.curso.osworks.domain.repository.ComentarioRepository;
import com.curso.osworks.domain.repository.OrdemServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public ResponseEntity<OrdemServicoDTO> buscar(Long ordemServicoId) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

        if(ordemServico.isPresent()) {
            OrdemServicoDTO ordemServicoDTO = toDTO(ordemServico.get());
            return ResponseEntity.ok(ordemServicoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    public OrdemServicoDTO toDTO(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoDTO.class);
    }

    public List<OrdemServicoDTO> toCollectionDTO(List<OrdemServico> ordemServicos) {
        return ordemServicos.stream()
                .map(ordemServico -> toDTO(ordemServico))
                .collect(Collectors.toList());
    }

    public OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }

    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }


    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    private OrdemServico buscarOrdemServico(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }

}
