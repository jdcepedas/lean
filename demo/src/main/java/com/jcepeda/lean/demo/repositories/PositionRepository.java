package com.jcepeda.lean.demo.repositories;

import com.jcepeda.lean.demo.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
