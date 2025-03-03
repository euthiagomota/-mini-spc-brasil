package com.br.minispc.debt.repositories;

import com.br.minispc.debt.entities.DebtEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<DebtEntity, Long> {
    List<DebtEntity> findByCustomerId(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM debts WHERE customer_id = :customerId", nativeQuery = true)
    void deleteByCustomerId(@Param("customerId") Long customerId);

}
