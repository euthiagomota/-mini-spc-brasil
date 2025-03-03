package com.br.minispc.debit.repositories;

import com.br.minispc.debit.entities.DebitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebitRepository extends JpaRepository<DebitEntity, Long> {

    List<DebitEntity> findByCustomerId(Long id);
}
