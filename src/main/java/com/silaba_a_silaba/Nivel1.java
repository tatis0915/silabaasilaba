package com.silaba_a_silaba;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.*;

public class Nivel1 {
    private Stage primaryStage;
    private int usuarioId;
    private int palabrasCompletadas = 0;
    private int vidas = 3;
    private int tiempoSegundos = 0; // Tiempo transcurrido en segundos
    private int cantidadErrores = 0; // Contador de errores
    private Label lblVidas; // Referencia al label de vidas
    private Label lblTiempo; // Referencia al label del tiempo
    private Timeline timeline; // Temporizador
    private List<String> palabrasDelNivel = new ArrayList<>();
    private Map<String, String[]> palabrasYSilabas = new HashMap<>();
    private String palabraActual;
    private String[] silabasActuales;
    private Label[] areasSilabas;
    private List<Button> botonesSilabas = new ArrayList<>();

    private static final int ANCHO_VENTANA = 500;
    private static final int ALTO_VENTANA = 400;
    private static final String FUENTE_BONITA = "Arial Rounded MT Bold";

    public Nivel1(int usuarioId) {
        this.usuarioId = usuarioId; // Inicializamos usuarioId con el valor recibido
        inicializarDiccionario();
        seleccionarPalabrasAleatorias();
    }

    private void inicializarDiccionario() {
        palabrasYSilabas.put("casa", new String[]{"ca", "sa"});
        palabrasYSilabas.put("silla", new String[]{"si", "lla"});
        palabrasYSilabas.put("mapa", new String[]{"ma", "pa"});
        palabrasYSilabas.put("mesa", new String[]{"me", "sa"});
        palabrasYSilabas.put("llave", new String[]{"lla", "ve"});
        palabrasYSilabas.put("ropa", new String[]{"ro", "pa"});
        palabrasYSilabas.put("lápiz", new String[]{"lá", "piz"});
        palabrasYSilabas.put("globo", new String[]{"glo", "bo"});
        palabrasYSilabas.put("suelo", new String[]{"sue", "lo"});
        palabrasYSilabas.put("niño", new String[]{"ni", "ño"});
        palabrasYSilabas.put("bote", new String[]{"bo", "te"});
        palabrasYSilabas.put("caja", new String[]{"ca", "ja"});
        palabrasYSilabas.put("rana", new String[]{"ra", "na"});
        palabrasYSilabas.put("sapo", new String[]{"sa", "po"});
        palabrasYSilabas.put("vaso", new String[]{"va", "so"});
        palabrasYSilabas.put("plato", new String[]{"pla", "to"});
        palabrasYSilabas.put("bicho", new String[]{"bi", "cho"});
        palabrasYSilabas.put("boca", new String[]{"bo", "ca"});
        palabrasYSilabas.put("lupa", new String[]{"lu", "pa"});
        palabrasYSilabas.put("foco", new String[]{"fo", "co"});
        palabrasYSilabas.put("taza", new String[]{"ta", "za"});
        palabrasYSilabas.put("gato", new String[]{"ga", "to"});
        palabrasYSilabas.put("perro", new String[]{"pe", "rro"});
        palabrasYSilabas.put("pato", new String[]{"pa", "to"});
        palabrasYSilabas.put("pera", new String[]{"pe", "ra"});
        palabrasYSilabas.put("nube", new String[]{"nu", "be"});
        palabrasYSilabas.put("coche", new String[]{"co", "che"});
        palabrasYSilabas.put("moto", new String[]{"mo", "to"});
        palabrasYSilabas.put("nido", new String[]{"ni", "do"});
        palabrasYSilabas.put("muro", new String[]{"mu", "ro"});
        palabrasYSilabas.put("pino", new String[]{"pi", "no"});
        palabrasYSilabas.put("fresa", new String[]{"fre", "sa"});
        palabrasYSilabas.put("manos", new String[]{"ma", "nos"});
        palabrasYSilabas.put("dedo", new String[]{"de", "do"});
        palabrasYSilabas.put("campo", new String[]{"cam", "po"});
        palabrasYSilabas.put("jugo", new String[]{"ju", "go"});
        palabrasYSilabas.put("pila", new String[]{"pi", "la"});
        palabrasYSilabas.put("cable", new String[]{"ca", "ble"});
        palabrasYSilabas.put("brocha", new String[]{"bro", "cha"});
        palabrasYSilabas.put("bolsa", new String[]{"bol", "sa"});
        palabrasYSilabas.put("hoja", new String[]{"ho", "ja"});
        palabrasYSilabas.put("rayo", new String[]{"ra", "yo"});
        palabrasYSilabas.put("noche", new String[]{"no", "che"});
        palabrasYSilabas.put("calle", new String[]{"ca", "lle"});
        palabrasYSilabas.put("monte", new String[]{"mon", "te"});
        palabrasYSilabas.put("banco", new String[]{"ban", "co"});
        palabrasYSilabas.put("bomba", new String[]{"bom", "ba"});
        palabrasYSilabas.put("vela", new String[]{"ve", "la"});
        palabrasYSilabas.put("nota", new String[]{"no", "ta"});
        palabrasYSilabas.put("mano", new String[]{"ma", "no"});
        palabrasYSilabas.put("sello", new String[]{"se", "llo"});
        palabrasYSilabas.put("pavo", new String[]{"pa", "vo"});
        palabrasYSilabas.put("lago", new String[]{"la", "go"});
        palabrasYSilabas.put("rango", new String[]{"ran", "go"});
        palabrasYSilabas.put("plomo", new String[]{"plo", "mo"});
        palabrasYSilabas.put("pinta", new String[]{"pin", "ta"});
        palabrasYSilabas.put("queso", new String[]{"que", "so"});
        palabrasYSilabas.put("leche", new String[]{"le", "che"});
        palabrasYSilabas.put("agua", new String[]{"a", "gua"});
        palabrasYSilabas.put("lucha", new String[]{"lu", "cha"});
        palabrasYSilabas.put("llama", new String[]{"lla", "ma"});
        palabrasYSilabas.put("bulto", new String[]{"bul", "to"});
        palabrasYSilabas.put("trapo", new String[]{"tra", "po"});
        palabrasYSilabas.put("pista", new String[]{"pis", "ta"});
        palabrasYSilabas.put("botas", new String[]{"bo", "tas"});
        palabrasYSilabas.put("cielo", new String[]{"cie", "lo"});
        palabrasYSilabas.put("grito", new String[]{"gri", "to"});
        palabrasYSilabas.put("punto", new String[]{"pun", "to"});
        palabrasYSilabas.put("grano", new String[]{"gra", "no"});
        palabrasYSilabas.put("copa", new String[]{"co", "pa"});
        palabrasYSilabas.put("yema", new String[]{"ye", "ma"});
        palabrasYSilabas.put("tela", new String[]{"te", "la"});
        palabrasYSilabas.put("roca", new String[]{"ro", "ca"});
        palabrasYSilabas.put("foca", new String[]{"fo", "ca"});
        palabrasYSilabas.put("mona", new String[]{"mo", "na"});
        palabrasYSilabas.put("loro", new String[]{"lo", "ro"});
        palabrasYSilabas.put("toro", new String[]{"to", "ro"});
        palabrasYSilabas.put("polo", new String[]{"po", "lo"});
        palabrasYSilabas.put("tubo", new String[]{"tu", "bo"});
        palabrasYSilabas.put("menta", new String[]{"men", "ta"});
        palabrasYSilabas.put("burro", new String[]{"bu", "rro"});
        palabrasYSilabas.put("mulo", new String[]{"mu", "lo"});
        palabrasYSilabas.put("lomo", new String[]{"lo", "mo"});
        palabrasYSilabas.put("palta", new String[]{"pla", "ta"});
        palabrasYSilabas.put("tema", new String[]{"te", "ma"});
        palabrasYSilabas.put("duro", new String[]{"du", "ro"});
        palabrasYSilabas.put("blusa", new String[]{"blu", "sa"});
        palabrasYSilabas.put("tina", new String[]{"ti", "na"});
        palabrasYSilabas.put("metro", new String[]{"me", "tro"});
        palabrasYSilabas.put("lunes", new String[]{"lu", "nes"});
        palabrasYSilabas.put("brote", new String[]{"bro", "te"});
        palabrasYSilabas.put("nube", new String[]{"nu", "be"});
        palabrasYSilabas.put("sumo", new String[]{"su", "mo"});
        palabrasYSilabas.put("fila", new String[]{"fi", "la"});
        palabrasYSilabas.put("doble", new String[]{"do", "ble"});
        palabrasYSilabas.put("dardo", new String[]{"dar", "do"});
        palabrasYSilabas.put("clavo", new String[]{"cla", "vo"});
        palabrasYSilabas.put("peso", new String[]{"pe", "so"});
    }

    private void seleccionarPalabrasAleatorias() {
        List<String> todasLasPalabras = new ArrayList<>(palabrasYSilabas.keySet());
        Collections.shuffle(todasLasPalabras);
        palabrasDelNivel = todasLasPalabras.subList(0, Math.min(25, todasLasPalabras.size()));
    }

    public void mostrarPantalla(Stage primaryStage) {
        this.primaryStage = primaryStage;
        tiempoSegundos = 0; // Reinicia el tiempo
        iniciarTemporizador(); // Inicia el temporizador
        cargarNuevaPalabra();
    }

    private void iniciarTemporizador() {
        lblTiempo = new Label("Tiempo: 0s"); // Inicializa la etiqueta del tiempo

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoSegundos++;
            lblTiempo.setText("Tiempo: " + tiempoSegundos + "s");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repite indefinidamente
        timeline.play(); // Inicia el temporizador
    }

    private void cargarNuevaPalabra() {
        if (palabrasDelNivel.isEmpty()) {
            nivelCompletado();
            return;
        }

        palabraActual = palabrasDelNivel.remove(0);
        silabasActuales = palabrasYSilabas.get(palabraActual);
        crearInterfaz();
    }

    private void crearInterfaz() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label lblInstruccion = new Label("Forma la palabra: (" + (palabrasCompletadas + 1) + "/25)");
        estilizarLabelBonito(lblInstruccion);
        lblVidas = new Label("Vidas: " + vidas);
        estilizarLabelBonito(lblVidas);

        // Áreas para soltar sílabas
        HBox areasBox = new HBox(10);
        areasBox.setAlignment(Pos.CENTER);
        areasSilabas = new Label[silabasActuales.length];

        for (int i = 0; i < silabasActuales.length; i++) {
            Label area = new Label("");
            prepararAreaSoltar(area);
            estilizarLabelOrden(area);
            areasSilabas[i] = area;
            areasBox.getChildren().add(area);
        }

        // Botones de sílabas (mezclados)
        HBox silabasBox = new HBox(10);
        silabasBox.setAlignment(Pos.CENTER);
        botonesSilabas.clear();

        List<String> silabasMezcladas = new ArrayList<>(Arrays.asList(silabasActuales));
        Collections.shuffle(silabasMezcladas);

        for (String silaba : silabasMezcladas) {
            Button btn = new Button(silaba);
            prepararBotonArrastre(btn);
            estilizarBotonPequeno(btn);
            botonesSilabas.add(btn);
            silabasBox.getChildren().add(btn);
        }

        Button btnVerificar = new Button("Verificar");
        estilizarBotonPequeno(btnVerificar);
        btnVerificar.setOnAction(e -> verificarPalabra());

        Button btnAtras = new Button("Atrás");
        estilizarBotonPequeno(btnAtras);
        btnAtras.setOnAction(e -> {
            if (timeline != null) {
                timeline.stop(); // Detener el temporizador
            }
            // Guardar el progreso con el tiempo transcurrido en segundo plano
            new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 1, palabrasCompletadas, false, tiempoSegundos)).start();

            // Regresar al menú principal
            Opciones opciones = new Opciones(usuarioId);
            opciones.mostrarPantalla(primaryStage);
        });

        // Contenedor para los botones
        HBox botonesBox = new HBox(10, btnVerificar, btnAtras);
        botonesBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
            lblInstruccion,
            lblVidas,
            lblTiempo, // Añadimos el tiempo a la interfaz
            new Label("Arrastra las sílabas:"),
            silabasBox,
            new Label("Colócalas en orden:"),
            areasBox,
            botonesBox
        );

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(root);
        stackPane.setPrefSize(ANCHO_VENTANA, ALTO_VENTANA);

        // Configurar fondo
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/nivel1.jpg"));
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        stackPane.setBackground(new Background(bgImg));

        primaryStage.setScene(new Scene(stackPane, ANCHO_VENTANA, ALTO_VENTANA));
        primaryStage.setTitle("Nivel 1 - 2 Sílabas");
        primaryStage.show();
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

    private void verificarPalabra() {
        boolean correcto = true;
        for (int i = 0; i < silabasActuales.length; i++) {
            if (!areasSilabas[i].getText().equals(silabasActuales[i])) {
                correcto = false;
                break;
            }
        }

        if (correcto) {
            palabrasCompletadas++;
            // Guardar en segundo plano para no bloquear el hilo de la UI
            new Thread(() -> ConexionBD.guardarProgreso(usuarioId, 1, palabrasCompletadas, palabrasCompletadas == 25)).start();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("¡Palabra formada correctamente!");
            alert.showAndWait();
            cargarNuevaPalabra();
        } else {
            vidas--;
            cantidadErrores++;
            // Guardar intento en segundo plano
            new Thread(() -> ConexionBD.guardarIntento(usuarioId, 1, cantidadErrores)).start();
            actualizarContadorVidas();

            if (vidas <= 0) {
                gameOver();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error ERR02: Incorrecto");
                alert.setHeaderText(null);
                alert.setContentText("Intenta de nuevo.");
                alert.showAndWait();

                // Limpiar áreas para reintentar
                for (Label area : areasSilabas) {
                    area.setText("");
                }
                // Mostrar todos los botones nuevamente
                for (Button btn : botonesSilabas) {
                    btn.setVisible(true);
                }
            }
        }
    }

    private void actualizarContadorVidas() {
        lblVidas.setText("Vidas: " + vidas);
    }

    private void gameOver() {
        if (timeline != null) {
            timeline.stop(); // Detiene el temporizador
        }
    
        // Guardar el progreso con tiempo total en segundos en segundo plano
        new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 1, palabrasCompletadas, false, tiempoSegundos)).start();
    
        // Convertir el tiempo total a minutos y segundos
        int minutos = tiempoSegundos / 60;
        int segundos = tiempoSegundos % 60;
    
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Has perdido todas tus vidas. Tiempo total: " + minutos + " minutos y " + segundos + " segundos.");
        alert.showAndWait();
    
        // Regresar al menú principal
        Opciones opciones = new Opciones(usuarioId);
        opciones.mostrarPantalla(primaryStage);
    }
    
    private void nivelCompletado() {
        if (timeline != null) {
            timeline.stop(); // Detiene el temporizador
        }
    
        // Guardar el progreso con tiempo total en segundos en segundo plano
        new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 1, palabrasCompletadas, true, tiempoSegundos)).start();
    
        // Convertir el tiempo total a minutos y segundos
        int minutos = tiempoSegundos / 60;
        int segundos = tiempoSegundos % 60;
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nivel Completado");
        alert.setHeaderText(null);
        alert.setContentText("¡Felicidades! Has completado el nivel en " + minutos + " minutos y " + segundos + " segundos.");
        alert.showAndWait();
    
        // Regresar al menú principal
        Opciones opciones = new Opciones(usuarioId);
        opciones.mostrarPantalla(primaryStage);
    }

    private void prepararBotonArrastre(Button boton) {
        boton.setOnDragDetected(event -> {
            Dragboard db = boton.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(boton.getText());
            db.setContent(content);
            event.consume();
        });
    }

    private void prepararAreaSoltar(Label area) {
        area.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        area.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                area.setText(db.getString());
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }
}