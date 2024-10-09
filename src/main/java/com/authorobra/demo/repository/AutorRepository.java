package com.authorobra.demo.repository;

import com.authorobra.demo.entity.Autor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);

    Optional<Autor> findByCpf(String cpf);

    @NotNull
    Optional<Autor> findById(Long id);

}
