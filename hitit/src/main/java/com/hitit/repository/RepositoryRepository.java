package com.hitit.repository;

import com.hitit.entity.RepositoryTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<RepositoryTable, Long> {
}
