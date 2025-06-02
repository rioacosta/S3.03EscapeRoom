package dao;

import dao.mysqlimp.DatabaseConnection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnabledIfSystemProperty(named = "integration.tests", matches = "true")
class DatabaseConnectionIntegrationTest {

    private DatabaseConnection databaseConnection;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Iniciando tests de integración de DatabaseConnection");
        System.out.println("Asegúrate de que la base de datos MySQL esté ejecutándose");
    }

    @BeforeEach
    void setUp() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Test
    @Order(1)
    @DisplayName("Integration Test - Database Schema Validation")
    void testDatabaseSchemaValidation() {
        // Arrange
        Connection connection = databaseConnection.getConnection();

        // Act & Assert
        assertDoesNotThrow(() -> {
            // Verificar que las tablas principales existen
            String[] expectedTables = {"escaperoom", "room", "decoration", "player", "tickets"};

            for (String tableName : expectedTables) {
                try (PreparedStatement stmt = connection.prepareStatement(
                        "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?")) {

                    stmt.setString(1, "escaperoomdb");
                    stmt.setString(2, tableName);

                    try (ResultSet rs = stmt.executeQuery()) {
                        assertTrue(rs.next(), "Debe haber resultado para la tabla " + tableName);
                        assertEquals(1, rs.getInt(1), "La tabla " + tableName + " debe existir");
                    }
                }
            }
        }, "No debe lanzar excepción al validar el esquema de la base de datos");
    }

    @Test
    @Order(2)
    @DisplayName("Integration Test - Basic CRUD Operations")
    void testBasicCrudOperations() {
        // Arrange
        Connection connection = databaseConnection.getConnection();

        // Act & Assert
        assertDoesNotThrow(() -> {
            databaseConnection.beginTransaction();

            // Test INSERT
            try (PreparedStatement insertStmt = connection.prepareStatement(
                    "INSERT INTO escaperoom (name) VALUES (?)")) {
                insertStmt.setString(1, "Test Escape Room");
                int rowsAffected = insertStmt.executeUpdate();
                assertEquals(1, rowsAffected, "Debe insertar exactamente una fila");
            }

            // Test SELECT
            try (PreparedStatement selectStmt = connection.prepareStatement(
                    "SELECT id, name FROM escaperoom WHERE name = ?")) {
                selectStmt.setString(1, "Test Escape Room");
                try (ResultSet rs = selectStmt.executeQuery()) {
                    assertTrue(rs.next(), "Debe encontrar el registro insertado");
                    assertEquals("Test Escape Room", rs.getString("name"));
                }
            }

            // Test UPDATE
            try (PreparedStatement updateStmt = connection.prepareStatement(
                    "UPDATE escaperoom SET name = ? WHERE name = ?")) {
                updateStmt.setString(1, "Updated Test Escape Room");
                updateStmt.setString(2, "Test Escape Room");
                int rowsAffected = updateStmt.executeUpdate();
                assertEquals(1, rowsAffected, "Debe actualizar exactamente una fila");
            }

            // Test DELETE
            try (PreparedStatement deleteStmt = connection.prepareStatement(
                    "DELETE FROM escaperoom WHERE name = ?")) {
                deleteStmt.setString(1, "Updated Test Escape Room");
                int rowsAffected = deleteStmt.executeUpdate();
                assertEquals(1, rowsAffected, "Debe eliminar exactamente una fila");
            }

            databaseConnection.commitTransaction();

        }, "No debe lanzar excepción durante las operaciones CRUD básicas");
    }

    @Test
    @Order(3)
    @DisplayName("Integration Test - Transaction Rollback")
    void testTransactionRollback() {
        // Arrange
        Connection connection = databaseConnection.getConnection();

        // Act & Assert
        assertDoesNotThrow(() -> {
            // Contar registros antes de la transacción
            int initialCount;
            try (PreparedStatement countStmt = connection.prepareStatement(
                    "SELECT COUNT(*) FROM escaperoom")) {
                try (ResultSet rs = countStmt.executeQuery()) {
                    rs.next();
                    initialCount = rs.getInt(1);
                }
            }

            databaseConnection.beginTransaction();

            // Insertar un registro
            try (PreparedStatement insertStmt = connection.prepareStatement(
                    "INSERT INTO escaperoom (name) VALUES (?)")) {
                insertStmt.setString(1, "Rollback Test Room");
                insertStmt.executeUpdate();
            }

            // Hacer rollback
            databaseConnection.rollbackTransaction();

            // Verificar que el registro no existe después del rollback
            int finalCount;
            try (PreparedStatement countStmt = connection.prepareStatement(
                    "SELECT COUNT(*) FROM escaperoom")) {
                try (ResultSet rs = countStmt.executeQuery()) {
                    rs.next();
                    finalCount = rs.getInt(1);
                }
            }

            assertEquals(initialCount, finalCount,
                    "El número de registros debe ser el mismo después del rollback");

        }, "No debe lanzar excepción durante el test de rollback");
    }

    @Test
    @Order(4)
    @DisplayName("Integration Test - Foreign Key Constraints")
    void testForeignKeyConstraints() {
        // Arrange
        Connection connection = databaseConnection.getConnection();

        // Act & Assert
        assertDoesNotThrow(() -> {
            databaseConnection.beginTransaction();

            // Insertar un escaperoom padre
            int escapeRoomId;
            try (PreparedStatement insertEscapeRoom = connection.prepareStatement(
                    "INSERT INTO escaperoom (name) VALUES (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertEscapeRoom.setString(1, "FK Test Escape Room");
                insertEscapeRoom.executeUpdate();

                try (ResultSet generatedKeys = insertEscapeRoom.getGeneratedKeys()) {
                    assertTrue(generatedKeys.next(), "Debe generar una clave primaria");
                    escapeRoomId = generatedKeys.getInt(1);
                }
            }

            // Insertar una room que referencia al escaperoom
            try (PreparedStatement insertRoom = connection.prepareStatement(
                    "INSERT INTO room (idEscaperoom_ref, name, dificulty, price) VALUES (?, ?, ?, ?)")) {
                insertRoom.setInt(1, escapeRoomId);
                insertRoom.setString(2, "FK Test Room");
                insertRoom.setString(3, "EASY");
                insertRoom.setBigDecimal(4, new java.math.BigDecimal("25"));

                int rowsAffected = insertRoom.executeUpdate();
                assertEquals(1, rowsAffected, "Debe insertar la room correctamente");
            }

            databaseConnection.rollbackTransaction(); // Limpiar datos de prueba

        }, "No debe lanzar excepción al probar las restricciones de clave foránea");
    }

    @Test
    @Order(5)
    @DisplayName("Integration Test - Connection Pool Behavior")
    void testConnectionPoolBehavior() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            // Simular múltiples operaciones consecutivas
            for (int i = 0; i < 5; i++) {
                Connection connection = databaseConnection.getConnection();
                assertNotNull(connection, "La conexión " + i + " no debe ser null");
                assertTrue(connection.isValid(2), "La conexión " + i + " debe ser válida");

                // Realizar una operación simple
                try (PreparedStatement stmt = connection.prepareStatement("SELECT 1")) {
                    try (ResultSet rs = stmt.executeQuery()) {
                        assertTrue(rs.next(), "Debe haber resultado en la iteración " + i);
                        assertEquals(1, rs.getInt(1), "El resultado debe ser 1");
                    }
                }
            }
        }, "No debe lanzar excepción durante múltiples operaciones de conexión");
    }

    @AfterEach
    void tearDown() {
        // Asegurar que no hay transacciones pendientes
        try {
            Connection connection = databaseConnection.getConnection();
            if (connection != null && !connection.isClosed()) {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en tearDown: " + e.getMessage());
        }
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Finalizando tests de integración de DatabaseConnection");
    }
}