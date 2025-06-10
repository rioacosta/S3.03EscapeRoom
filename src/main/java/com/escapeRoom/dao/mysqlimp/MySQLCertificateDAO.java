package com.escapeRoom.dao.mysqlimp;

import com.escapeRoom.dao.DatabaseConnection;
import com.escapeRoom.dao.interfaces.IGenericDAO;
import com.escapeRoom.entities.Certificate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLCertificateDAO implements IGenericDAO<Certificate, Integer> {
        private static final Logger logger = Logger.getLogger(MySQLCertificateDAO.class.getName());
        private final Connection connection;

        public MySQLCertificateDAO(DatabaseConnection databaseConnection) {
            this.connection = databaseConnection.getConnection();
        }

        @Override
        public boolean create(Certificate certificate) {
            String sql = "INSERT INTO certificate (name, description, dateOfDelivery, idPlayer) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, certificate.getName());
                stmt.setString(2, certificate.getDescription());
                stmt.setDate(3, Date.valueOf(certificate.getDateOfDelivery()));
                stmt.setInt(4, certificate.getIdPlayer());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            certificate.setIdCertificate(rs.getInt(1));
                        }
                    }
                    return true;
                }
                return false;
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error creando el certificado", e);
                return false;
            }
        }

        @Override
        public Optional<Certificate> findById(Integer id) {
            String sql = "SELECT * FROM certificate WHERE idCertificate = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapResultSetToCertificate(rs));
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error encontrando el certificado por ID: " + id, e);
            }
            return Optional.empty();
        }

        @Override
        public Optional<Certificate> findByName(String name) {
            String sql = "SELECT * FROM certificate WHERE name = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapResultSetToCertificate(rs));
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error encontrando el certificado por nombre: " + name, e);
            }
            return Optional.empty();
        }

        @Override
        public boolean update(Certificate certificate) {
            String sql = "UPDATE certificate SET name = ?, description = ?, dateOfDelivery = ?, idPlayer = ? WHERE idCertificate = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, certificate.getName());
                stmt.setString(2, certificate.getDescription());
                stmt.setDate(3, Date.valueOf(certificate.getDateOfDelivery()));
                stmt.setInt(4, certificate.getIdPlayer());
                stmt.setInt(5, certificate.getIdCertificate());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error actualizando el certificado ID: " + certificate.getIdCertificate(), e);
                return false;
            }
        }

        @Override
        public boolean deleteById(Integer id) {
            String sql = "DELETE FROM certificate WHERE idCertificate = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error eliminando el certificado ID: " + id, e);
                return false;
            }
        }

        @Override
        public List<Certificate> findAll() {
            List<Certificate> certificates = new ArrayList<>();
            String sql = "SELECT * FROM certificate";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    certificates.add(mapResultSetToCertificate(rs));
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error recuperando todos los certificados", e);
            }
            return certificates;
        }

        @Override
        public boolean existsById(Integer id) {
            String sql = "SELECT 1 FROM certificate WHERE idCertificate = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error verificando existencia del certificado ID: " + id, e);
                return false;
            }
        }

        private Certificate mapResultSetToCertificate(ResultSet rs) throws SQLException {
            return new Certificate(
                    rs.getInt("idCertificate"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDate("dateOfDelivery").toLocalDate(),
                    rs.getInt("idPlayer")
            );
        }
    }

