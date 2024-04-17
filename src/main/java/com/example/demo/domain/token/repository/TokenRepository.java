package com.example.demo.domain.token.repository;

import com.example.demo.domain.token.entity.Token;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query("SELECT COALESCE(MAX(t.waitNo),0)+1 FROM Token t")
    Long selectNextWaitNumber();

    @Query("SELECT COALESCE(COUNT(t.progressStatus), 0) FROM Token t WHERE t.status = :status")
    Long getProgressStatusCount(@Param("status") String status);

    Optional<Token> findByWaitNo(long nextWaitNo);

    List<Token> findAllByStatus(String status);

    @Query("SELECT MIN(t.waitNo) FROM Token t WHERE t.status = :status")
    long getNextPriorityWaitNumber(String status);

    @Query("SELECT t FROM Token t WHERE t.status = :status ORDER BY t.waitNo ASC")
    List<Token> findAllTokensToActivate(@Param("status") String status, Pageable pageable);

    Optional<Token> findByToken(String token);
}
