package com.silaba_a_silaba;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Registro {

    private Button crearBotonMenu(String texto, String color) {
        Button boton = new Button(texto);
        boton.setStyle("-fx-font-size: 24px; " +
                      "-fx-font-family: 'Arial Rounded MT Bold'; " +
                      "-fx-text-fill: white; " +
                      "-fx-background-color: " + color + "; " +
                      "-fx-padding: 20px 50px; " +
                      "-fx-background-radius: 10px; " +
                      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        
        boton.setOnMouseEntered(e -> boton.setStyle(boton.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        boton.setOnMouseExited(e -> boton.setStyle(boton.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
        
        boton.setMaxWidth(300);
        return boton;
    }

    public void mostrarPantalla(Stage stage) {
        // Campos de texto
        TextField nombreField = new TextField();
        TextField apellidoField = new TextField();
        DatePicker fechaNacimientoPicker = new DatePicker();
        TextField correoField = new TextField();
        TextField tarjetaIdentidadField = new TextField();

        // Restringir fechas futuras en el DatePicker
        fechaNacimientoPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date != null && date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Color para fechas deshabilitadas
                }
            }
        });

        // Botón "Siguiente"
        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #4CAF50; -fx-padding: 20px 40px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        btnSiguiente.setOnMouseEntered(e -> btnSiguiente.setStyle(btnSiguiente.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btnSiguiente.setOnMouseExited(e -> btnSiguiente.setStyle(btnSiguiente.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
        btnSiguiente.setOnAction(e -> {
            String nombre = nombreField.getText().trim();
            String apellido = apellidoField.getText().trim();
            String fechaNacimiento = (fechaNacimientoPicker.getValue() != null) ? fechaNacimientoPicker.getValue().toString() : null;
            String correo = correoField.getText().trim();
            String tarjetaIdentidad = tarjetaIdentidadField.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento == null || correo.isEmpty() || tarjetaIdentidad.isEmpty()) {
                mostrarAlerta("Error", "Por favor, completa todos los campos obligatorios.");
                return;
            }

            if (registrarUsuario(nombre, apellido, fechaNacimiento, correo, tarjetaIdentidad)) {
                mostrarAlerta("Éxito", "Usuario registrado exitosamente.");
                VentanaInicial ventanaInicial = new VentanaInicial();
                ventanaInicial.mostrarPantalla(stage);
            } else {
                mostrarAlerta("Error", "No se pudo registrar el usuario. Inténtalo de nuevo.");
            }
        });

        // Botón "Atrás"
        Button btnAtras = new Button("Atrás");
        btnAtras.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #F44336; -fx-padding: 20px 40px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        btnAtras.setOnMouseEntered(e -> btnAtras.setStyle(btnAtras.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btnAtras.setOnMouseExited(e -> btnAtras.setStyle(btnAtras.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
        btnAtras.setOnAction(e -> {
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

        BackgroundImage backgroundImage = null;
        if (fondo != null) {
            backgroundImage = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
        }

        // Estilo para las etiquetas
        Label nombreLabel = new Label("Nombre:");
        nombreLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label apellidoLabel = new Label("Apellido:");
        apellidoLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label fechaNacimientoLabel = new Label("Fecha de nacimiento:");
        fechaNacimientoLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label correoLabel = new Label("Correo institucional:");
        correoLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");
        Label tarjetaIdentidadLabel = new Label("Tarjeta de identidad:");
        tarjetaIdentidadLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: #333;");

        // Contenedor principal
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(nombreLabel, 0, 0);
        grid.add(nombreField, 1, 0);
        grid.add(apellidoLabel, 0, 1);
        grid.add(apellidoField, 1, 1);
        grid.add(fechaNacimientoLabel, 0, 2);
        grid.add(fechaNacimientoPicker, 1, 2);
        grid.add(correoLabel, 0, 3);
        grid.add(correoField, 1, 3);
        grid.add(tarjetaIdentidadLabel, 0, 4);
        grid.add(tarjetaIdentidadField, 1, 4);
        grid.add(btnSiguiente, 1, 5);
        grid.add(btnAtras, 0, 5);

        VBox vbox = new VBox(20, grid);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(backgroundImage));

        Scene scene = new Scene(vbox, 500, 400);
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.show();
    }

    private boolean registrarUsuario(String nombre, String apellido, String fechaNacimiento, String correo, String tarjetaIdentidad) {
        String sql = "INSERT INTO usuario (nombre, apellido, fecha_nacimiento, correo, tarjeta_identidad) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error: La conexión a la base de datos es nula.");
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println("Registrando usuario con los siguientes datos:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Fecha de nacimiento: " + fechaNacimiento);
            System.out.println("Correo: " + correo);
            System.out.println("Tarjeta de identidad: " + tarjetaIdentidad);

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, fechaNacimiento);
            stmt.setString(4, correo);
            stmt.setString(5, tarjetaIdentidad);

            stmt.executeUpdate();
            System.out.println("Usuario registrado exitosamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    private int obtenerUsuarioId(String tarjetaIdentidad) {
        String sql = "SELECT id_usuario FROM usuario WHERE tarjeta_identidad = ?";
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error: La conexión a la base de datos es nula.");
            return -1;
        }

        if (tarjetaIdentidad == null || tarjetaIdentidad.isEmpty()) {
            System.out.println("Error: El valor de tarjetaIdentidad es nulo o vacío.");
            try { conn.close(); } catch (SQLException ignored) {}
            return -1;
        }

        System.out.println("Ejecutando consulta con tarjetaIdentidad: " + tarjetaIdentidad);
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarjetaIdentidad);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    System.out.println("Usuario encontrado: ID = " + idUsuario);
                    return idUsuario;
                } else {
                    System.out.println("No se encontró un usuario con la tarjeta de identidad: " + tarjetaIdentidad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
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