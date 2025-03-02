package com.br.minispc.divide.repositories;

import com.br.minispc.divide.entities.DivideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivideRepository extends JpaRepository<DivideEntity, Long> {
}
