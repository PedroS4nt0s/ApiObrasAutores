package com.authorobra.demo.service;

import com.authorobra.demo.dtos.ObraDto;
import com.authorobra.demo.entity.Obra;
import com.authorobra.demo.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    public ObraDto criar(ObraDto obra) {
        validarNome(obra.getNome());
        validaDatas(obra);
        //DelimitaçãodeCaratersEstáNaEntity
        return salvar(obra);
    }

    public List<Obra> todasAsObras() {
        List<Obra> obras = obraRepository.findAll();
        if (obras.isEmpty()) {
            throw new IllegalArgumentException("Ainda não temos nenhuma obra");
        }
        return obras;
    }

    public ObraDto buscaPorId(Long id) {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent()) {
            return convertToDTO(obra.get());
        } else {
            throw new IllegalArgumentException("Autor não encontrado com o ID: " + id);
        }
    }

    public ObraDto atualizarObra(Long id, ObraDto mudancasObra) {
        ObraDto novaObra = buscaPorId(id);
        novaObra.setNome(mudancasObra.getNome());
        novaObra.setDescricao(mudancasObra.getDescricao());
        novaObra.setDataPublicacao(mudancasObra.getDataPublicacao());
        novaObra.setDataExposicao(mudancasObra.getDataExposicao());

        return criar(novaObra);
    }

    public void deletarObra(Long id) {
        Optional<Obra> obra = obraRepository.findById(id);
        if (obra.isPresent()) {
            obraRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Não encontramos um Autor com esse Id");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("É necessário um nome para o autor.");
        }
    }

    private void validaDatas(ObraDto obra) {
        if (obra.getDataPublicacao() == null && obra.getDataExposicao() == null) {
            throw new IllegalArgumentException("É necessário que a Obra tenha uma das datas preenchidas (Publicação ou Exposição)");
        }
        if (obra.getDataPublicacao() != null) {
            obra.setDataPublicacao(LocalDate.parse(formatarDataparaString(obra.getDataPublicacao()), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        if (obra.getDataExposicao() != null) {
            obra.setDataExposicao(LocalDate.parse(formatarDataparaString(obra.getDataExposicao()), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }

    public String formatarData(LocalDate data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }

    public String formatarDataparaString(LocalDate dataStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = dataStr.format(formatter);
        return dateString;
    }

    private ObraDto convertToDTO(Obra obra) {
        ObraDto dto = new ObraDto();
        dto.setId(obra.getId());
        dto.setNome(obra.getNome());
        dto.setDescricao(obra.getDescricao());
        dto.setDataPublicacao(obra.getDataPublicacao());
        dto.setDataExposicao(obra.getDataExposicao());
        dto.setAutoresIds(obra.getAutores());
        return dto;
    }

    private Obra convertToEntity(ObraDto obraDto) {
        Obra obra = new Obra();
        obra.setId(obraDto.getId());
        obra.setNome(obraDto.getNome());
        obra.setDescricao(obraDto.getDescricao());
        obra.setDataPublicacao(obraDto.getDataPublicacao());
        obra.setDataExposicao((obraDto.getDataExposicao()));
        obra.setAutores(obraDto.getAutoresIds());
        return obra;
    }

    public ObraDto salvar(ObraDto obraDto) {
        Obra obra = convertToEntity(obraDto);
        Obra salvo = obraRepository.save(obra);
        return convertToDTO(salvo);
    }
}
