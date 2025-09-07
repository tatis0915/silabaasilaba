package com.silaba_a_silaba;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesion {

    public void mostrarPantalla(Stage stage) {
        // Campos de texto
        TextField nombreField = new TextField();
        TextField apellidoField = new TextField();
        TextField tarjetaIdentidadField = new TextField();

        // Botón "Siguiente"
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #4CAF50; -fx-padding: 20px 40px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        btnSiguiente.setOnMouseEntered(mouseEvent -> btnSiguiente.setStyle(btnSiguiente.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btnSiguiente.setOnMouseExited(mouseEvent -> btnSiguiente.setStyle(btnSiguiente.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));

        btnSiguiente.setOnAction(actionEvent -> {
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String tarjetaIdentidad = tarjetaIdentidadField.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || tarjetaIdentidad.isEmpty()) {
                mostrarAlerta("Error", "Por favor, completa todos los campos.");
                return;
            }

            int usuarioId = obtenerUsuarioId(nombre, apellido, tarjetaIdentidad);
            if (usuarioId != -1) {
                Opciones opciones = new Opciones(usuarioId);
                opciones.mostrarPantalla(stage);
            } else {
                mostrarAlerta("Error", "Usuario no encontrado o datos incorrectos.");
            }
        });

        // Botón "Atrás"
        Button btnAtras = new Button("Atrás");
        btnAtras.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #F44336; -fx-padding: 20px 40px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        btnAtras.setOnMouseEntered(mouseEvent -> btnAtras.setStyle(btnAtras.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btnAtras.setOnMouseExited(mouseEvent -> btnAtras.setStyle(btnAtras.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));

        btnAtras.setOnAction(actionEvent -> {
            VentanaInicial ventanaInicial = new VentanaInicial();
            ventanaInicial.mostrarPantalla(stage);
        });

        // Cargar la imagen de fondo
        Image fondo = null;
        try {
            fondo = new Image(getClass().getResource("/resources/imagenes/fondo.jpg").toExternalForm());
        } catch (NullPointerException ex) {
            System.out.println("Error: No se pudo cargar la imagen de fondo. Verifica la ruta y ubicación del archivo.");
        }

        BackgroundImage backgroundImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        // Contenedor principal
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Cambiar el estilo de las etiquetas
        Label nombreLabel = new Label("Nombre:");
        nombreLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label apellidoLabel = new Label("Apellido:");
        apellidoLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label tarjetaIdentidadLabel = new Label("Tarjeta de identidad:");
        tarjetaIdentidadLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");

        // Actualizar el GridPane
        grid.add(nombreLabel, 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(apellidoLabel, 0, 1);
        grid.add(apellidoField, 1, 1);
        grid.add(tarjetaIdentidadLabel, 0, 2);
        grid.add(tarjetaIdentidadField, 1, 2);
        grid.add(btnSiguiente, 1, 3);
        grid.add(btnAtras, 0, 3);

        VBox vbox = new VBox(20, grid);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        if (fondo != null) {
            vbox.setBackground(new Background(backgroundImage));
        }

        Scene scene = new Scene(vbox, 500, 400);
        stage.setTitle("Inicio de Sesión");
        stage.setScene(scene);
        stage.show();
    }

    private int obtenerUsuarioId(String nombre, String apellido, String tarjetaIdentidad) {
        String sql = "SELECT id_usuario FROM usuario WHERE nombre = ? AND apellido = ? AND tarjeta_identidad = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Error: La conexión a la base de datos es nula.");
                return -1;
            }

            if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || tarjetaIdentidad == null || tarjetaIdentidad.isEmpty()) {
                System.out.println("Error: Uno o más valores son nulos o vacíos.");
                return -1;
            }

            System.out.println("Ejecutando consulta con los siguientes datos:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Tarjeta de identidad: " + tarjetaIdentidad);

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, tarjetaIdentidad);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                System.out.println("Usuario encontrado: ID = " + idUsuario);
                return idUsuario;
            } else {
                System.out.println("No se encontró un usuario con los datos proporcionados.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra el usuario
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}