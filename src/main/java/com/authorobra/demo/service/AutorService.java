package com.authorobra.demo.service;

import com.authorobra.demo.dtos.AutorDto;
import com.authorobra.demo.entity.Autor;
import com.authorobra.demo.repository.AutorRepository;
import com.authorobra.demo.repository.PaisesRepository;
import com.authorobra.demo.service.exception.EntityNotFoud;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private PaisesRepository paisesRepository;

    public AutorDto criar(AutorDto autor) {
        validarNome(autor.getNome());
        validarEmail(autor.getEmail());
        validarSexo(autor.getSexo());
        validarCPF(autor);
        validarDataNascimento(autor);
        validarPais(autor.getPais_orig(), autor);
        return salvar(autor);
    }

    public List<AutorDto> todosAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            throw new EntityNotFoud("Ainda não temos nenhum Autor");
        }
        return autores.stream()
                .map(autor -> new AutorDto(
                        autor.getId(),
                        autor.getNome(),
                        autor.getSexo(),
                        autor.getEmail(),
                        autor.getData_nasc(),
                        autor.getPais_orig(),
                        autor.getCpf()
                ))
                .collect(Collectors.toList());
    }

    public AutorDto buscaPorId(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            return convertToDTO(autor.get());
        } else {
            throw new EntityNotFoud("Autor não encontrado com o ID: " + id);
        }
    }

    public AutorDto atualizarAutor(Long id, AutorDto mudancasAutor) {
        AutorDto autorAtual = buscaPorId(id);
        Optional<Autor> autorExistente = autorRepository.findByCpf(mudancasAutor.getCpf());

        if (mudancasAutor.getNome() != null && !mudancasAutor.getNome().isEmpty()) {
            autorAtual.setNome(mudancasAutor.getNome());
        }
        if (mudancasAutor.getSexo() != null) {
            autorAtual.setSexo(mudancasAutor.getSexo());
        }
        if (mudancasAutor.getEmail() != null && !mudancasAutor.getEmail().isEmpty()) {
            validarEmail(mudancasAutor.getEmail());
            autorAtual.setEmail(mudancasAutor.getEmail());
        }
        if (mudancasAutor.getPais_orig() != null) {
            validarPais(mudancasAutor.getPais_orig(), mudancasAutor);
            autorAtual.setPais_orig(mudancasAutor.getPais_orig());
        }
        if ((!mudancasAutor.getCpf().isEmpty()) && mudancasAutor.getCpf() != autorAtual.getCpf()){
            validarCPF(mudancasAutor);
            autorAtual.setCpf(mudancasAutor.getCpf());
        }
        validarNome(autorAtual.getNome());
        validarDataNascimento(autorAtual);
        return salvar(autorAtual);
    }

    public void deletarAutor(Long id) {
        Optional<Autor> autorId = autorRepository.findById(id);
        if (autorId.isPresent()) {
            autorRepository.deleteById(id);
        } else {
            throw new EntityNotFoud("Autor não encontrado");
        }
    }

    public boolean dataFutura(LocalDate dataStr) {
        if (dataStr == null) {
            throw new EntityNotFoud("A data não pode ser nula.");
        }
        LocalDate hoje = LocalDate.now();
        return dataStr.isAfter(hoje);
    }

    private void validarDataNascimento(AutorDto autor) {
        if (autor.getData_nasc() == null || autor.getData_nasc().toString().isEmpty()) {
            throw new EntityNotFoud("Data deve ser preenchida");
        } else if (dataFutura(autor.getData_nasc())) {
            throw new EntityNotFoud("Data invalida pois é uma data futura");
        }
        String autorDataNascConvert = formatarDataparaString(autor.getData_nasc());
        autor.setData_nasc(formatarData(autorDataNascConvert));
    }

    public String formatarDataparaString(LocalDate dataStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = dataStr.format(formatter);
        return dateString;
    }

    public LocalDate formatarData(String dataStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dataStr, dateFormatter);
        return date;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new EntityNotFoud("É necessário um nome para o autor.");
        }
    }

    private void validarEmail(String email) {
        if (!email.isEmpty() || email == null){
            if(autorRepository.findByEmail(email).isPresent()) {
                throw new EntityNotFoud("E-mail já está em uso.");
            }
        }
    }

    private void validarPais(String paisOrigem, AutorDto autor) {
        if (!paisesRepository.findByNome(paisOrigem).isPresent()) {
            throw new EntityNotFoud("Digite um país válido.");
        } else if (paisOrigem.equals("Brasil")){
            validarCPF(autor);
            autor.setCpf(formataCPF(autor.getCpf()));
        }
    }

    private void validarSexo(String sx) {
        if (!(sx.equals("M") || sx.equals("Masculino")
                || sx.equals("F") || sx.equals("Feminino"))) {
            throw new EntityNotFoud("Digite um sexo válido.");
        }
    }

    private void validarCPF(AutorDto autor) {
        if (autor.getPais_orig().equals("Brasil")) {
            if (autor.getCpf() == null || autor.getCpf().isEmpty()) {
                throw new EntityNotFoud("CPF é obrigatório para autores do Brasil.");
            } else if (!validaCPF(autor.getCpf())) {
                throw new EntityNotFoud("CPF inválido.");
            }
        }

    }

    private AutorDto convertToDTO(Autor autor) {
        AutorDto dto = new AutorDto();
        dto.setId(autor.getId());
        dto.setNome(autor.getNome());
        dto.setSexo(autor.getSexo());
        dto.setEmail(autor.getEmail());
        dto.setData_nasc(autor.getData_nasc());
        dto.setPais_orig(autor.getPais_orig());
        dto.setCpf(autor.getCpf());
        return dto;
    }

    private Autor convertToEntity(AutorDto autorDto) {
        Autor autor = new Autor();
        autor.setId(autorDto.getId());
        autor.setNome(autorDto.getNome());
        autor.setSexo(autorDto.getSexo());
        autor.setEmail(autorDto.getEmail());
        autor.setData_nasc((autorDto.getData_nasc()));
        autor.setPais_orig(autorDto.getPais_orig());
        if (autorDto.getPais_orig().equals("Brasil")) {
            autor.setCpf(autorDto.getCpf());
        }
        return autor;
    }

    public AutorDto salvar(AutorDto autorDto) {
        Autor autor = convertToEntity(autorDto);
        Autor salvo = autorRepository.save(autor);
        return convertToDTO(salvo);
    }

    public static String formataCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new EntityNotFoud("CPF inválido. Deve conter 11 dígitos.");
        }

        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    public static boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            digito1 = (digito1 >= 10) ? 0 : digito1;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            digito2 = (digito2 >= 10) ? 0 : digito2;

            return (cpf.charAt(9) - '0' == digito1) && (cpf.charAt(10) - '0' == digito2);
        } catch (Exception e) {
            return false;
        }
    }

}
