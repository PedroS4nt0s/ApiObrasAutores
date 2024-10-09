package com.authorobra.demo.repository;

import com.authorobra.demo.entity.Autor;
import com.authorobra.demo.entity.Paises;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface PaisesRepository extends JpaRepository<Paises, Long> {
    Optional<Paises> findByNome(@Param("paisNome") String nome);

}