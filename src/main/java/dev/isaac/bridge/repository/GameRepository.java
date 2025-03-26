package dev.isaac.bridge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.isaac.bridge.entity.model.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    
    Optional<Game> findById(long id);
}
