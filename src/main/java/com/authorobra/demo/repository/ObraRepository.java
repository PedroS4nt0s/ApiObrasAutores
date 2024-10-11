package com.authorobra.demo.repository;

import com.authorobra.demo.entity.Autor;
import com.authorobra.demo.entity.Obra;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ObraRepository extends JpaRepository<Obra, Long> {
    @NotNull
    Optional<Obra> findById(Long id);


}
