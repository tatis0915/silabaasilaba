package com.silaba_a_silaba;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Opciones {

    private int usuarioId; // ID del usuario actual

    // Constructor para recibir el ID del usuario
    public Opciones(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void mostrarPantalla(Stage primaryStage) {
        // 1. Configuración de botones con estilo mejorado
        Button btnTutorial = crearBotonMenu("Tutorial", "#3498db");
        Button btnNivel1 = crearBotonMenu("Nivel 1", "#2ecc71");
        Button btnNivel2 = crearBotonMenu("Nivel 2", "#e67e22");
        Button btnNivel3 = crearBotonMenu("Nivel 3", "#9b59b6");
        Button btnReportes = crearBotonMenu("Ver Reportes", "#1abc9c");
        Button btnSalir = crearBotonMenu("Salir", "#e74c3c");

        // 2. Acciones para cada botón
        btnTutorial.setOnAction(event -> {
            try {
                System.out.println("Cargando Tutorial con usuarioId: " + usuarioId);
                Tutorial tutorial = new Tutorial(usuarioId); // Pasamos el usuarioId al tutorial
                tutorial.mostrarPantalla(primaryStage);
            } catch (Exception ex) {
                mostrarMensaje("Error", "No se pudo cargar el tutorial.", Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnNivel1.setOnAction(event -> {
            try {
                System.out.println("Cargando Nivel 1 con usuarioId: " + usuarioId);
                Nivel1 nivel1 = new Nivel1(usuarioId); // Pasamos el usuarioId al nivel 1
                nivel1.mostrarPantalla(primaryStage);
            } catch (Exception ex) {
                mostrarMensaje("Error", "No se pudo cargar el Nivel 1.", Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnNivel2.setOnAction(event -> {
            try {
                System.out.println("Cargando Nivel 2 con usuarioId: " + usuarioId);
                Nivel2 nivel2 = new Nivel2(usuarioId); // Pasamos el usuarioId al nivel 2
                nivel2.mostrarPantalla(primaryStage);
            } catch (Exception ex) {
                mostrarMensaje("Error", "No se pudo cargar el Nivel 2.", Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnNivel3.setOnAction(event -> {
            try {
                System.out.println("Cargando Nivel 3 con usuarioId: " + usuarioId);
                Nivel3 nivel3 = new Nivel3(usuarioId); // Pasamos el usuarioId al nivel 3
                nivel3.mostrarPantalla(primaryStage);
            } catch (Exception ex) {
                mostrarMensaje("Error", "No se pudo cargar el Nivel 3.", Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnReportes.setOnAction(event -> {
            try {
                System.out.println("Cargando Reportes con usuarioId: " + usuarioId);
                Reportes reportes = new Reportes(usuarioId); // Pasamos el usuarioId a los reportes
                reportes.mostrarPantalla(primaryStage);
            } catch (Exception ex) {
                mostrarMensaje("Error", "No se pudo cargar los reportes.", Alert.AlertType.ERROR);
                ex.printStackTrace();
            }
        });

        btnSalir.setOnAction(event -> {
            System.out.println("Saliendo de la aplicación...");
            primaryStage.close();
        });

        // Cargar la imagen de fondo desde el classpath
        Image fondo = null;
        try {
            fondo = new Image(getClass().getResource("/resources/imagenes/fondo.jpg").toExternalForm());
            System.out.println("Imagen de fondo cargada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen de fondo: " + e.getMessage());
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

        // Configuración del layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        // Asegurar que el fondo se aplique correctamente
        if (backgroundImage != null) {
            System.out.println("BackgroundImage creado correctamente.");
            root.setBackground(new Background(backgroundImage));
            System.out.println("Background aplicado al contenedor root.");
        } else {
            System.out.println("Advertencia: BackgroundImage es nulo, no se aplicó el fondo.");
        }

        // Título del juego
        Label titulo = new Label("Sílaba a Sílaba");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Panel de botones
        VBox panelBotones = new VBox(10, btnTutorial, btnNivel1, btnNivel2, btnNivel3, btnReportes, btnSalir);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setMaxWidth(200);

        root.getChildren().addAll(titulo, panelBotones);

        // Configuración de la escena
        Scene scene = new Scene(root, 500, 400); // Tamaño igual al de VentanaInicial
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menú Principal");
        primaryStage.show();
    }

    // Método para crear botones del menú con estilo
    private Button crearBotonMenu(String texto, String color) {
        Button boton = new Button(texto);
        boton.setStyle("-fx-font-size: 16px; " +
                      "-fx-text-fill: white; " +
                      "-fx-background-color: " + color + "; " +
                      "-fx-padding: 10px 20px; " +
                      "-fx-background-radius: 5px; " +
                      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1);");
        
        // Efecto hover
        boton.setOnMouseEntered(event -> boton.setStyle(boton.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
        boton.setOnMouseExited(event -> boton.setStyle(boton.getStyle().replace("-fx-scale-x: 1.05; -fx-scale-y: 1.05;", "")));
        
        boton.setMaxWidth(Double.MAX_VALUE);
        return boton;
    }

    // Método para mostrar mensajes
    public void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}