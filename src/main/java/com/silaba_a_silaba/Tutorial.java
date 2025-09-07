package com.silaba_a_silaba;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.function.Consumer;

public class Tutorial {

    private int usuarioId; // ID del usuario actual

    // Constructor que recibe el usuarioId
    public Tutorial(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    private static final int ANCHO_VENTANA = 500;
    private static final int ALTO_VENTANA = 400;
    private static final String FUENTE_BONITA = "Arial Rounded MT Bold";

    public void mostrarPantalla(Stage stage) {
        Label lblPregunta = new Label("Reordena las sílabas para formar la palabra:");
        estilizarLabelBonito(lblPregunta);

        Button btnSilaba1 = new Button("sa");
        Button btnSilaba2 = new Button("ca");
        prepararArrastre(btnSilaba1);
        prepararArrastre(btnSilaba2);
        estilizarBotonPequeno(btnSilaba1);
        estilizarBotonPequeno(btnSilaba2);

        Label lblOrden1 = new Label(" ");
        Label lblOrden2 = new Label(" ");
        prepararSoltado(lblOrden1);
        prepararSoltado(lblOrden2);
        estilizarLabelOrden(lblOrden1);
        estilizarLabelOrden(lblOrden2);

        Button btnVerificar = new Button("Verificar");
        estilizarBotonPequeno(btnVerificar);
        btnVerificar.setOnAction(e -> verificarOrden(
            new Label[]{lblOrden1, lblOrden2},
            new String[]{"ca", "sa"},
            stage,
            this::mostrarPaso2
        ));

        HBox silabasBox = new HBox(10, btnSilaba1, btnSilaba2);
        silabasBox.setAlignment(Pos.CENTER);

        HBox ordenBox = new HBox(10, lblOrden1, lblOrden2);
        ordenBox.setAlignment(Pos.CENTER);

        Label lblArea = new Label("Área de reordenamiento:");
        estilizarLabelBonito(lblArea);

        VBox layout = new VBox(20, lblPregunta, silabasBox, lblArea, ordenBox, btnVerificar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        root.setPrefSize(ANCHO_VENTANA, ALTO_VENTANA);
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/tutorial.jpg"));
        // Depuración: Verificar si el recurso está disponible
        if (getClass().getResource("/resources/imagenes/tutorial.jpg") == null) {
            System.out.println("Error: El recurso /resources/imagenes/tutorial.jpg no se encuentra en el classpath.");
        } else {
            System.out.println("Recurso /resources/imagenes/tutorial.jpg encontrado correctamente.");
        }
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        root.setBackground(new Background(bgImg));

        stage.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        stage.setTitle("Paso 1: Palabra de 2 sílabas");
        stage.show();
    }

    private void mostrarPaso2(Stage stage) {
        Label lblPregunta = new Label("Reordena las sílabas para formar la palabra:");
        estilizarLabelBonito(lblPregunta);

        Button btnSilaba1 = new Button("mi");
        Button btnSilaba2 = new Button("go");
        Button btnSilaba3 = new Button("a");
        prepararArrastre(btnSilaba1);
        prepararArrastre(btnSilaba2);
        prepararArrastre(btnSilaba3);
        estilizarBotonPequeno(btnSilaba1);
        estilizarBotonPequeno(btnSilaba2);
        estilizarBotonPequeno(btnSilaba3);

        Label lblOrden1 = new Label(" ");
        Label lblOrden2 = new Label(" ");
        Label lblOrden3 = new Label(" ");
        prepararSoltado(lblOrden1);
        prepararSoltado(lblOrden2);
        prepararSoltado(lblOrden3);
        estilizarLabelOrden(lblOrden1);
        estilizarLabelOrden(lblOrden2);
        estilizarLabelOrden(lblOrden3);

        Button btnVerificar = new Button("Verificar");
        estilizarBotonPequeno(btnVerificar);
        btnVerificar.setOnAction(e -> verificarOrden(
            new Label[]{lblOrden1, lblOrden2, lblOrden3},
            new String[]{"a", "mi", "go"},
            stage,
            this::mostrarPaso3
        ));

        HBox silabasBox = new HBox(10, btnSilaba1, btnSilaba2, btnSilaba3);
        silabasBox.setAlignment(Pos.CENTER);

        HBox ordenBox = new HBox(10, lblOrden1, lblOrden2, lblOrden3);
        ordenBox.setAlignment(Pos.CENTER);

        Label lblArea = new Label("Área de reordenamiento:");
        estilizarLabelBonito(lblArea);

        VBox layout = new VBox(20, lblPregunta, silabasBox, lblArea, ordenBox, btnVerificar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        root.setPrefSize(ANCHO_VENTANA, ALTO_VENTANA);
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/tutorial.jpg"));
        // Depuración: Verificar si el recurso está disponible
        if (getClass().getResource("/resources/imagenes/tutorial.jpg") == null) {
            System.out.println("Error: El recurso /resources/imagenes/tutorial.jpg no se encuentra en el classpath.");
        } else {
            System.out.println("Recurso /resources/imagenes/tutorial.jpg encontrado correctamente.");
        }
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        root.setBackground(new Background(bgImg));

        stage.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        stage.setTitle("Paso 2: Palabra de 3 sílabas");
        stage.show();
    }

    private void mostrarPaso3(Stage stage) {
        Label lblPregunta = new Label("Reordena las sílabas para formar la palabra:");
        estilizarLabelBonito(lblPregunta);

        Button btnSilaba1 = new Button("ri");
        Button btnSilaba2 = new Button("po");
        Button btnSilaba3 = new Button("sa");
        Button btnSilaba4 = new Button("ma");
        prepararArrastre(btnSilaba1);
        prepararArrastre(btnSilaba2);
        prepararArrastre(btnSilaba3);
        prepararArrastre(btnSilaba4);
        estilizarBotonPequeno(btnSilaba1);
        estilizarBotonPequeno(btnSilaba2);
        estilizarBotonPequeno(btnSilaba3);
        estilizarBotonPequeno(btnSilaba4);

        Label lblOrden1 = new Label(" ");
        Label lblOrden2 = new Label(" ");
        Label lblOrden3 = new Label(" ");
        Label lblOrden4 = new Label(" ");
        prepararSoltado(lblOrden1);
        prepararSoltado(lblOrden2);
        prepararSoltado(lblOrden3);
        prepararSoltado(lblOrden4);
        estilizarLabelOrden(lblOrden1);
        estilizarLabelOrden(lblOrden2);
        estilizarLabelOrden(lblOrden3);
        estilizarLabelOrden(lblOrden4);

        Button btnVerificar = new Button("Verificar");
        estilizarBotonPequeno(btnVerificar);
        btnVerificar.setOnAction(e -> verificarOrden(
            new Label[]{lblOrden1, lblOrden2, lblOrden3, lblOrden4},
            new String[]{"ma", "ri", "po", "sa"},
            stage,
            this::regresarAlMenu
        ));

        HBox silabasBox = new HBox(10, btnSilaba1, btnSilaba2, btnSilaba3, btnSilaba4);
        silabasBox.setAlignment(Pos.CENTER);

        HBox ordenBox = new HBox(10, lblOrden1, lblOrden2, lblOrden3, lblOrden4);
        ordenBox.setAlignment(Pos.CENTER);

        Label lblArea = new Label("Área de reordenamiento:");
        estilizarLabelBonito(lblArea);

        VBox layout = new VBox(20, lblPregunta, silabasBox, lblArea, ordenBox, btnVerificar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        root.setPrefSize(ANCHO_VENTANA, ALTO_VENTANA);
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/tutorial.jpg"));
        // Depuración: Verificar si el recurso está disponible
        if (getClass().getResource("/resources/imagenes/tutorial.jpg") == null) {
            System.out.println("Error: El recurso /resources/imagenes/tutorial.jpg no se encuentra en el classpath.");
        } else {
            System.out.println("Recurso /resources/imagenes/tutorial.jpg encontrado correctamente.");
        }
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        root.setBackground(new Background(bgImg));

        stage.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        stage.setTitle("Paso 3: Palabra de 4 sílabas");
        stage.show();
    }

    private void regresarAlMenu(Stage stage) {
        // Ahora pasamos el usuarioId al constructor de Opciones
        Opciones opciones = new Opciones(usuarioId);
        opciones.mostrarPantalla(stage);
    }

    private void verificarOrden(Label[] labels, String[] correcta, Stage stage, Consumer<Stage> siguientePaso) {
        boolean correcto = true;
        for (int i = 0; i < labels.length; i++) {
            if (!labels[i].getText().trim().equals(correcta[i])) {
                correcto = false;
                break;
            }
        }

        Alert alert = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(correcto ? "¡Correcto!" : "Intenta de nuevo.");
        alert.showAndWait();

        if (correcto) siguientePaso.accept(stage);
    }

    private void prepararArrastre(Button boton) {
        boton.setOnDragDetected(event -> {
            Dragboard db = boton.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(boton.getText());
            db.setContent(content);
            event.consume();
        });
    }

    private void prepararSoltado(Label label) {
        label.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        label.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                label.setText(db.getString());
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    private void estilizarBotonPequeno(Button btn) {
        btn.setStyle("-fx-font-size: 16px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #4CAF50; -fx-padding: 10px 24px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        btn.setMaxWidth(180);
        btn.setFont(Font.font(FUENTE_BONITA, FontWeight.BOLD, 16));
        btn.setOnMouseEntered(e -> btn.setStyle(btn.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btn.setOnMouseExited(e -> btn.setStyle(btn.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
    }

    private void estilizarLabelBonito(Label lbl) {
        lbl.setFont(Font.font(FUENTE_BONITA, FontWeight.BOLD, 20));
        lbl.setStyle("-fx-text-fill: #333;");
    }

    private void estilizarLabelOrden(Label lbl) {
        lbl.setStyle("-fx-border-color: gray; -fx-padding: 15px; -fx-background-color: white; -fx-font-size: 18px; -fx-min-width: 80px; -fx-alignment: center;");
        lbl.setFont(Font.font(FUENTE_BONITA, FontWeight.NORMAL, 18));
    }
}