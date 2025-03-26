package dev.isaac.bridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.repository.PlayerRepository;

@Service 
public class PlayerService {
    
    PlayerRepository playerRepo;

    @Autowired 
    public PlayerService(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player createBot() {

        Player bot = new Player();

        return playerRepo.save(bot);
    }
}
