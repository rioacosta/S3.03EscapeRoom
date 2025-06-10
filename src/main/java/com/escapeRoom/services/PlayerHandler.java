package com.escapeRoom.services;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.dao.mysqlimp.MySQLCertificateDAO;
import com.escapeRoom.entities.Certificate;
import com.escapeRoom.entities.Player;
import com.escapeRoom.exceptions.NullOrEmptyException;
import com.escapeRoom.notifications.concreteSubject.NewNewsletter;
import java.time.LocalDate;
import java.util.Optional;

public class PlayerHandler {
    private final IGenericDAO<Player, Integer> playerDao;
    private final MySQLCertificateDAO certificateDao;
    private final NewNewsletter newsletter = new NewNewsletter();

    public PlayerHandler(IGenericDAO<Player, Integer> playerDao) {
        this.playerDao = playerDao;
        this.certificateDao = new MySQLCertificateDAO(DatabaseConnection.getInstance());
    }

    public void createPlayer(Player player) {
        playerDao.create(player);
    }

    public void subscribePlayer(Player player) {
        if (player == null || player.getName() == null || player.getEmail() == null) {
            throw new NullOrEmptyException("Datos del jugador inválidos, no se puede suscribir");
        }
        //playerDao.create(player);
        newsletter.addObserver(player);
        System.out.println("Jugador " + player.getName() + " suscrito");
    }

    public void unsbscribePlayer(Player player) {
        if (player != null) {
            newsletter.removeObserver(player);
            boolean result = NewNewsletter.subscribers.contains(player); //playerDao.deleteById(player.getIdPlayer());
            if (result) {
                System.out.println("Jugador " + player.getName() + " des-suscrito.");
            } else {
                System.err.println("No se pudo des-suscribir al jugador " + player.getName() +
                        ". Asegúrate de eliminar las entradas asociadas.");
            }
        } else {
            throw new RuntimeException("Jugador no encontrado, no se puede des-suscribir.");
        }
    }

    public void notifySubscribers(String notification) {
        newsletter.notifyObservers(notification);
    }

   /* public Optional<Player> findPlayerById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de jugador inválido");
        }
        return playerDao.findById(id);
    }*/

    public void showAllPlayers() {
        playerDao.findAll().forEach(System.out::println);
    }


    public Optional<Player> findPlayerByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullOrEmptyException("Jugador no encontrado");
        }
        return playerDao.findByName(name);
    }

    public void assignCertificateToPlayer(String playerName, String certification) {
        try {
            Optional<Player> playerOpt = findPlayerByName(playerName);
            if (playerOpt.isEmpty()) {
                throw new RuntimeException("Jugador no encontrado");
            }

            Player player = playerOpt.get();

            Certificate newCertificate = new Certificate();
            newCertificate.setDateOfDelivery(LocalDate.now());
            newCertificate.setName(player.getName());
            newCertificate.setDescription(certification); 
            newCertificate.setIdPlayer(player.getIdPlayer());

            player.setCertificate(newCertificate);
            certificateDao.create(newCertificate);
            playerDao.update(player);

            System.out.println("Certificado asignado correctamente a " + playerName);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error asignando certificado: " + e.getMessage(), e);
        }
    }
}
