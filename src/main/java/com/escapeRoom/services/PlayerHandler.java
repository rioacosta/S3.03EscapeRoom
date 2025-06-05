package com.escapeRoom.services;

import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Certificate;
import com.escapeRoom.entities.Player;
import com.escapeRoom.exceptions.NullOrEmptyException;

import java.time.LocalDate;
import java.util.Optional;

public class PlayerHandler {
    private final IGenericDAO<Player, Integer> playerDao;

    public PlayerHandler(IGenericDAO<Player, Integer> playerDao) {
        this.playerDao = playerDao;
    }

    public boolean subscribePlayer(Player player) {
        if(player == null || player.getName() == null || player.getEmail() == null) {
            throw new NullOrEmptyException("Datos del jugador inválidos");
        }
        return playerDao.create(player);
    }

    public Optional<Player> findPlayerById(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        return playerDao.findById(id);
    }

    public Optional<Player> findPlayerByName(String name) {
        if(name == null || name.isEmpty()) {       throw new  NullOrEmptyException("Jugador no encontrado");
        }
        return playerDao.findByName(name);
    }

    public boolean assignCertificateToPlayer(int playerId, int certificateId) {

        Optional<Player> playerOpt = findPlayerById(playerId);
        if(playerOpt.isEmpty()) {   throw new RuntimeException("Jugador no encontrado");
        }
        String name = playerOpt.get().getName();

        Certificate newCertificate = new Certificate(certificateId, name, LocalDate.now(), playerId);

        playerOpt.ifPresent(p -> p.setCertificate(newCertificate));

        System.out.println("Asignando certificado " + certificateId + " al jugador " + playerId);
        return true;
    }
}
