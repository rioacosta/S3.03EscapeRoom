package com.escapeRoom.services;

import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Certificate;
import com.escapeRoom.entities.Player;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.notifications.concreteSubject.NewNewsletter;

import java.time.LocalDate;
import java.util.Optional;

public class PlayerHandler {
    private final IGenericDAO<Player, Integer> playerDao;
    private final NewNewsletter newsletter = new NewNewsletter();

    public PlayerHandler(IGenericDAO<Player, Integer> playerDao) {
        this.playerDao = playerDao;
    }

    public boolean subscribePlayer(Player player) {
        if(player == null || player.getName() == null || player.getEmail() == null) {
            throw new NullOrEmptyException("Datos del jugador inválidos, no se puede suscribir");
        }
        playerDao.create(player);
                newsletter.addObserver(player);
        return true;
    }
    public void unsbscribePlayer(Player player) {
        if (player != null) {
            newsletter.removeObserver(player);
            playerDao.deleteById(player.getIdPlayer());
        } else { throw new RuntimeException("Jugador no encontrado, no se puede des-suscribir");
        }
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

    public boolean assignCertificateToPlayer(String playerName) {

        Optional<Player> playerOpt = findPlayerByName(playerName);
        if(playerOpt.isEmpty()) {   throw new RuntimeException("Jugador no encontrado");
        }
        String name = playerOpt.get().getName();

        Certificate newCertificate = new Certificate(); //(certificateId, name, LocalDate.now(), playerId);
        newCertificate.setDateOfDelivery(LocalDate.now());
        newCertificate.setName(name);
        playerOpt.ifPresent(p -> p.setCertificate(newCertificate));

        System.out.println("Asignando certificado al jugador " + playerName);
        return true;
    }
}
