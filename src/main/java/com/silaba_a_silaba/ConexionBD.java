package com.silaba_a_silaba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/silabaasilaba?useSSL=false&serverTimezone=UTC"; // Asegúrate de que el nombre de la base de datos sea correcto
    private static final String USUARIO = "root"; // Usuario de MySQL
    private static final String CONTRASENA = ""; // Contraseña de MySQL (deja vacío si no tienes contraseña)

    public static Connection conectar() {
        try {
            // Eliminar registro explícito del controlador JDBC si no era necesario
            Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a la base de datos.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Método para guardar el progreso del usuario en un nivel
    public static void guardarProgreso(int usuarioId, int nivel, int palabrasFormadas, boolean completado) {
        String sql = "INSERT INTO progreso (usuario_id, nivel, palabras_formadas, completado) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE palabras_formadas = ?, completado = ?";

        Connection conn = conectar();
        if (conn == null) {
            System.out.println("No se pudo guardar el progreso: conexión nula.");
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, nivel);
            stmt.setInt(3, palabrasFormadas);
            stmt.setBoolean(4, completado);
            stmt.setInt(5, palabrasFormadas);
            stmt.setBoolean(6, completado);

            stmt.executeUpdate();
            System.out.println("Progreso guardado correctamente para el usuario con ID: " + usuarioId + " en el nivel " + nivel);
        } catch (SQLException e) {
            System.out.println("Error al guardar el progreso: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    // Método para guardar el progreso del usuario con tiempo total en segundos
    public static void guardarProgresoConTiempo(int usuarioId, int nivel, int palabrasFormadas, boolean completado, int tiempoTotalSegundos) {
        String sql = "INSERT INTO progreso (usuario_id, nivel, palabras_formadas, completado, tiempo_total_segundos) " +
                     "VALUES (?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE palabras_formadas = ?, completado = ?, tiempo_total_segundos = ?";

        Connection conn = conectar();
        if (conn == null) {
            System.out.println("No se pudo guardar el progreso con tiempo: conexión nula.");
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, nivel);
            stmt.setInt(3, palabrasFormadas);
            stmt.setBoolean(4, completado);
            stmt.setInt(5, tiempoTotalSegundos);

            // Para la actualización en caso de duplicados
            stmt.setInt(6, palabrasFormadas);
            stmt.setBoolean(7, completado);
            stmt.setInt(8, tiempoTotalSegundos);

            stmt.executeUpdate();
            System.out.println("Progreso con tiempo guardado correctamente para el usuario con ID: " + usuarioId + " en el nivel " + nivel);
        } catch (SQLException e) {
            System.out.println("Error al guardar el progreso con tiempo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    // Método para guardar los intentos del usuario en un nivel
    public static void guardarIntento(int usuarioId, int nivel, int cantidadErrores) {
        String sql = "INSERT INTO intento (usuario_id, nivel, cantidad_errores) VALUES (?, ?, ?)";

        Connection conn = conectar();
        if (conn == null) {
            System.out.println("No se pudo guardar el intento: conexión nula.");
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, nivel);
            stmt.setInt(3, cantidadErrores);

            stmt.executeUpdate();
            System.out.println("Intento guardado correctamente para el usuario con ID: " + usuarioId + " en el nivel " + nivel);
        } catch (SQLException e) {
            System.out.println("Error al guardar el intento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    // Método para actualizar el reporte general del usuario con tiempo total en segundos
    public static void actualizarReporte(int usuarioId, int progreso, int actividadesCompletadas, int tiempoTotalSegundos) {
        String sql = "INSERT INTO reportes (usuario_id, progreso, actividades_completadas, tiempo_total_segundos) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE progreso = ?, actividades_completadas = ?, tiempo_total_segundos = ?";

        Connection conn = conectar();
        if (conn == null) {
            System.out.println("No se pudo actualizar el reporte: conexión nula.");
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, progreso);
            stmt.setInt(3, actividadesCompletadas);
            stmt.setInt(4, tiempoTotalSegundos);
            stmt.setInt(5, progreso);
            stmt.setInt(6, actividadesCompletadas);
            stmt.setInt(7, tiempoTotalSegundos);

            stmt.executeUpdate();
            System.out.println("Reporte actualizado correctamente para el usuario con ID: " + usuarioId);
        } catch (SQLException e) {
            System.out.println("Error al actualizar el reporte: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }
}