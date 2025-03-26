package dev.isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.isaac.bridge.entity.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
