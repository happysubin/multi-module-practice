package com.template.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HelloRepository extends JpaRepository<Hello, Long> {
}
