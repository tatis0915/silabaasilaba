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

public class Nivel2 {
    private Stage primaryStage;
    private int usuarioId; // ID del usuario actual
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

    public Nivel2(int usuarioId) {
        this.usuarioId = usuarioId;
        inicializarDiccionario();
        seleccionarPalabrasAleatorias();
    }

    private void inicializarDiccionario() {
        // Palabras de 3 sílabas para el Nivel 2
        palabrasYSilabas.put("abeja", new String[]{"a", "be", "ja"});
        palabrasYSilabas.put("abrazo", new String[]{"a", "bra", "zo"});
        palabrasYSilabas.put("acento", new String[]{"a", "cen", "to"});
        palabrasYSilabas.put("adorno", new String[]{"a", "dor", "no"});
        palabrasYSilabas.put("adulto", new String[]{"a", "dul", "to"});
        palabrasYSilabas.put("agenda", new String[]{"a", "gen", "da"});
        palabrasYSilabas.put("alambre", new String[]{"a", "lam", "bre"});
        palabrasYSilabas.put("alejar", new String[]{"a", "le", "jar"});
        palabrasYSilabas.put("amigo", new String[]{"a", "mi", "go"});
        palabrasYSilabas.put("andino", new String[]{"an", "di", "no"});
        palabrasYSilabas.put("anillo", new String[]{"a", "ni", "llo"});
        palabrasYSilabas.put("armada", new String[]{"ar", "ma", "da"});
        palabrasYSilabas.put("arruga", new String[]{"a", "rru", "ga"});
        palabrasYSilabas.put("artista", new String[]{"ar", "tis", "ta"});
        palabrasYSilabas.put("barrera", new String[]{"ba", "rre", "ra"});
        palabrasYSilabas.put("barriga", new String[]{"ba", "rri", "ga"});
        palabrasYSilabas.put("bebida", new String[]{"be", "bi", "da"});
        palabrasYSilabas.put("bandera", new String[]{"ban", "de", "ra"});
        palabrasYSilabas.put("botines", new String[]{"bo", "ti", "nes"});
        palabrasYSilabas.put("cabina", new String[]{"ca", "bi", "na"});
        palabrasYSilabas.put("cadena", new String[]{"ca", "de", "na"});
        palabrasYSilabas.put("cajero", new String[]{"ca", "je", "ro"});
        palabrasYSilabas.put("camello", new String[]{"ca", "me", "llo"});
        palabrasYSilabas.put("camino", new String[]{"ca", "mi", "no"});
        palabrasYSilabas.put("candado", new String[]{"can", "da", "do"});
        palabrasYSilabas.put("canario", new String[]{"ca", "na", "rio"});
        palabrasYSilabas.put("cantina", new String[]{"can", "ti", "na"});
        palabrasYSilabas.put("cariño", new String[]{"ca", "ri", "ño"});
        palabrasYSilabas.put("caricia", new String[]{"ca", "ri", "cia"});
        palabrasYSilabas.put("castigo", new String[]{"cas", "ti", "go"});
        palabrasYSilabas.put("cebolla", new String[]{"ce", "bo", "lla"});
        palabrasYSilabas.put("cerilla", new String[]{"ce", "ri", "lla"});
        palabrasYSilabas.put("cigarro", new String[]{"ci", "ga", "rro"});
        palabrasYSilabas.put("colina", new String[]{"co", "li", "na"});
        palabrasYSilabas.put("colonia", new String[]{"co", "lo", "nia"});
        palabrasYSilabas.put("cometa", new String[]{"co", "me", "ta"});
        palabrasYSilabas.put("comino", new String[]{"co", "mi", "no"});
        palabrasYSilabas.put("cocina", new String[]{"co", "ci", "na"});
        palabrasYSilabas.put("colegio", new String[]{"co", "le", "gio"});
        palabrasYSilabas.put("cortina", new String[]{"cor", "ti", "na"});
        palabrasYSilabas.put("cuaderno", new String[]{"cua", "der", "no"});
        palabrasYSilabas.put("cuchara", new String[]{"cu", "cha", "ra"});
        palabrasYSilabas.put("cultura", new String[]{"cul", "tu", "ra"});
        palabrasYSilabas.put("curioso", new String[]{"cu", "rio", "so"});
        palabrasYSilabas.put("decente", new String[]{"de", "cen", "te"});
        palabrasYSilabas.put("defecto", new String[]{"de", "fec", "to"});
        palabrasYSilabas.put("deleite", new String[]{"de", "lei", "te"});
        palabrasYSilabas.put("demora", new String[]{"de", "mo", "ra"});
        palabrasYSilabas.put("dentista", new String[]{"den", "tis", "ta"});
        palabrasYSilabas.put("derrota", new String[]{"de", "rro", "ta"});
        palabrasYSilabas.put("desvelo", new String[]{"des", "ve", "lo"});
        palabrasYSilabas.put("dilema", new String[]{"di", "le", "ma"});
        palabrasYSilabas.put("dibujo", new String[]{"di", "bu", "jo"});
        palabrasYSilabas.put("dormido", new String[]{"dor", "mi", "do"});
        palabrasYSilabas.put("eclipse", new String[]{"e", "clip", "se"});
        palabrasYSilabas.put("embudo", new String[]{"em", "bu", "do"});
        palabrasYSilabas.put("empaque", new String[]{"em", "pa", "que"});
        palabrasYSilabas.put("encanto", new String[]{"en", "can", "to"});
        palabrasYSilabas.put("enciende", new String[]{"en", "cien", "de"});
        palabrasYSilabas.put("enfado", new String[]{"en", "fa", "do"});
        palabrasYSilabas.put("enganche", new String[]{"en", "gan", "che"});
        palabrasYSilabas.put("enjambre", new String[]{"en", "jam", "bre"});
        palabrasYSilabas.put("ensueño", new String[]{"en", "sue", "ño"});
        palabrasYSilabas.put("entierra", new String[]{"en", "tie", "rra"});
        palabrasYSilabas.put("erguido", new String[]{"er", "gui", "do"});
        palabrasYSilabas.put("escarcha", new String[]{"es", "car", "cha"});
        palabrasYSilabas.put("esculpe", new String[]{"es", "cul", "pe"});
        palabrasYSilabas.put("espuma", new String[]{"es", "pu", "ma"});
        palabrasYSilabas.put("estrella", new String[]{"es", "tre", "lla"});
        palabrasYSilabas.put("fábrica", new String[]{"fá", "bri", "ca"});
        palabrasYSilabas.put("familia", new String[]{"fa", "mi", "lia"});
        palabrasYSilabas.put("faroles", new String[]{"fa", "ro", "les"});
        palabrasYSilabas.put("figura", new String[]{"fi", "gu", "ra"});
        palabrasYSilabas.put("fortuna", new String[]{"for", "tu", "na"});
        palabrasYSilabas.put("frontera", new String[]{"fron", "te", "ra"});
        palabrasYSilabas.put("fumador", new String[]{"fu", "ma", "dor"});
        palabrasYSilabas.put("gallina", new String[]{"ga", "lli", "na"});
        palabrasYSilabas.put("garganta", new String[]{"gar", "gan", "ta"});
        palabrasYSilabas.put("guitarra", new String[]{"gui", "ta", "rra"});
        palabrasYSilabas.put("historia", new String[]{"his", "to", "ria"});
        palabrasYSilabas.put("invierno", new String[]{"in", "vier", "no"});
        palabrasYSilabas.put("lámpara", new String[]{"lám", "pa", "ra"});
        palabrasYSilabas.put("manzana", new String[]{"man", "za", "na"});
        palabrasYSilabas.put("memoria", new String[]{"me", "mo", "ria"});
        palabrasYSilabas.put("milagro", new String[]{"mi", "la", "gro"});
        palabrasYSilabas.put("mochila", new String[]{"mo", "chi", "la"});
        palabrasYSilabas.put("moneda", new String[]{"mo", "ne", "da"});
        palabrasYSilabas.put("montaña", new String[]{"mon", "ta", "ña"});
        palabrasYSilabas.put("morena", new String[]{"mo", "re", "na"});
        palabrasYSilabas.put("mosquito", new String[]{"mos", "qui", "to"});
        palabrasYSilabas.put("noticia", new String[]{"no", "ti", "cia"});
        palabrasYSilabas.put("paquete", new String[]{"pa", "que", "te"});
        palabrasYSilabas.put("peineta", new String[]{"pei", "ne", "ta"});
        palabrasYSilabas.put("planeta", new String[]{"pla", "ne", "ta"});
        palabrasYSilabas.put("platillo", new String[]{"pla", "ti", "llo"});
        palabrasYSilabas.put("poema", new String[]{"po", "e", "ma"});
        palabrasYSilabas.put("producto", new String[]{"pro", "duc", "to"});
        palabrasYSilabas.put("rutina", new String[]{"ru", "ti", "na"});
        palabrasYSilabas.put("palmera", new String[]{"pal", "me", "ra"});
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

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
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
        btnVerificar.setOnAction(event -> verificarPalabra());

        Button btnAtras = new Button("Atrás");
        estilizarBotonPequeno(btnAtras);
        btnAtras.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop(); // Detener el temporizador
            }
            // Guardar el progreso con el tiempo transcurrido en segundo plano
            new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 2, palabrasCompletadas, false, tiempoSegundos)).start();

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
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/nivel2.jpg"));
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        stackPane.setBackground(new Background(bgImg));

        primaryStage.setScene(new Scene(stackPane, ANCHO_VENTANA, ALTO_VENTANA));
        primaryStage.setTitle("Nivel 2 - 3 Sílabas");
        primaryStage.show();
    }

    private void estilizarBotonPequeno(Button btn) {
        btn.setStyle("-fx-font-size: 16px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #4CAF50; -fx-padding: 10px 24px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        btn.setMaxWidth(180);
        btn.setFont(Font.font(FUENTE_BONITA, FontWeight.BOLD, 16));
        btn.setOnMouseEntered(event -> btn.setStyle(btn.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btn.setOnMouseExited(event -> btn.setStyle(btn.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
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
            // Guardar en segundo plano para no bloquear la UI
            new Thread(() -> ConexionBD.guardarProgreso(usuarioId, 2, palabrasCompletadas, palabrasCompletadas == 25)).start();

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
            new Thread(() -> ConexionBD.guardarIntento(usuarioId, 2, cantidadErrores)).start();
            actualizarContadorVidas();

            if (vidas <= 0) {
                gameOver();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrecto");
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
        new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 2, palabrasCompletadas, false, tiempoSegundos)).start();
    
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
        new Thread(() -> ConexionBD.guardarProgresoConTiempo(usuarioId, 2, palabrasCompletadas, true, tiempoSegundos)).start();
    
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