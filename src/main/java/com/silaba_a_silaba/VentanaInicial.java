package com.silaba_a_silaba;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VentanaInicial {

    private Button crearBotonMenu(String texto, String color) {
        Button boton = new Button(texto);
        boton.setStyle("-fx-font-size: 24px; " +
                      "-fx-font-family: 'Arial Rounded MT Bold'; " +
                      "-fx-text-fill: white; " +
                      "-fx-background-color: " + color + "; " +
                      "-fx-padding: 20px 50px; " + // Ajustar el padding horizontal para que el texto quepa
                      "-fx-background-radius: 10px; " +
                      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        
        // Efecto hover
        boton.setOnMouseEntered(e -> boton.setStyle(boton.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        boton.setOnMouseExited(e -> boton.setStyle(boton.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
        
        boton.setMaxWidth(300); // Ajustar el ancho máximo
        return boton;
    }

    public void mostrarPantalla(Stage stage) {
        // Botones
        Button btnInicioSesion = crearBotonMenu("Inicio de Sesión", "#FFA500"); // Cambiar a color naranja
        Button btnRegistro = crearBotonMenu("Registro", "#2196F3");

        // Configurar acciones de los botones
        btnInicioSesion.setOnAction(e -> {
            InicioSesion inicioSesion = new InicioSesion();
            inicioSesion.mostrarPantalla(stage);
        });

        btnRegistro.setOnAction(e -> {
            Registro registro = new Registro();
            registro.mostrarPantalla(stage);
        });

        // Cargar la imagen de fondo
        Image fondo = null;
        try {
            fondo = new Image(getClass().getResource("/resources/imagenes/fondo.jpg").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("Advertencia: No se pudo cargar la imagen de fondo. Continuando sin fondo.");
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

        // Contenedor principal
        VBox vbox = new VBox(20, btnInicioSesion, btnRegistro);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        if (backgroundImage != null) {
            vbox.setBackground(new Background(backgroundImage)); // Establecer el fondo si la imagen se cargó correctamente
        }

        // Crear y mostrar la escena
        Scene scene = new Scene(vbox, 500, 400);
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.show();
    }
}