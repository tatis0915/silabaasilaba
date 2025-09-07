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

public class Nivel3 {
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

    public Nivel3(int usuarioId) {
        this.usuarioId = usuarioId;
        inicializarDiccionario();
        seleccionarPalabrasAleatorias();
    }

    private void inicializarDiccionario() {
        // Palabras de 4 sílabas para el Nivel 3
        palabrasYSilabas.put("mariposa", new String[]{"ma", "ri", "po", "sa"});
        palabrasYSilabas.put("cocodrilo", new String[]{"co", "co", "dri", "lo"});
        palabrasYSilabas.put("elefante", new String[]{"e", "le", "fan", "te"});
        palabrasYSilabas.put("dinosaurio", new String[]{"di", "no", "sau", "rio"});
        palabrasYSilabas.put("camaleón", new String[]{"ca", "ma", "le", "ón"});
        palabrasYSilabas.put("pelícano", new String[]{"pe", "lí", "ca", "no"});
        palabrasYSilabas.put("ventilador", new String[]{"ven", "ti", "la", "dor"});
        palabrasYSilabas.put("televisor", new String[]{"te", "le", "vi", "sor"});
        palabrasYSilabas.put("microondas", new String[]{"mi", "cro", "on", "das"});
        palabrasYSilabas.put("lavadora", new String[]{"la", "va", "do", "ra"});
        palabrasYSilabas.put("batidora", new String[]{"ba", "ti", "do", "ra"});
        palabrasYSilabas.put("iluminar", new String[]{"i", "lu", "mi", "nar"});
        palabrasYSilabas.put("arquitecto", new String[]{"ar", "qui", "tec", "to"});
        palabrasYSilabas.put("palomitas", new String[]{"pa", "lo", "mi", "tas"});
        palabrasYSilabas.put("melocotón", new String[]{"me", "lo", "co", "tón"});
        palabrasYSilabas.put("galletita", new String[]{"ga", "lle", "ti", "ta"});
        palabrasYSilabas.put("piruleta", new String[]{"pi", "ru", "le", "ta"});
        palabrasYSilabas.put("cereales", new String[]{"ce", "re", "a", "les"});
        palabrasYSilabas.put("naranjada", new String[]{"na", "ran", "ja", "da"});
        palabrasYSilabas.put("limonada", new String[]{"li", "mo", "na", "da"});
        palabrasYSilabas.put("arcoíris", new String[]{"ar", "co", "í", "ris"});
        palabrasYSilabas.put("estrellita", new String[]{"es", "tre", "lli", "ta"});
        palabrasYSilabas.put("nubecita", new String[]{"nu", "be", "ci", "ta"});
        palabrasYSilabas.put("platanero", new String[]{"pla", "ta", "ne", "ro"});
        palabrasYSilabas.put("patineta", new String[]{"pa", "ti", "ne", "ta"});
        palabrasYSilabas.put("muñequita", new String[]{"mu", "ñe", "qui", "ta"});
        palabrasYSilabas.put("arenero", new String[]{"a", "re", "ne", "ro"});
        palabrasYSilabas.put("caminando", new String[]{"ca", "mi", "nan", "do"});
        palabrasYSilabas.put("bicicleta", new String[]{"bi", "ci", "cle", "ta"});
        palabrasYSilabas.put("automóvil", new String[]{"au", "to", "mó", "vil"});
        palabrasYSilabas.put("avioneta", new String[]{"a", "vio", "ne", "ta"});
        palabrasYSilabas.put("plastilina", new String[]{"plas", "ti", "li", "na"});
        palabrasYSilabas.put("abuelito", new String[]{"a", "bue", "li", "to"});
        palabrasYSilabas.put("hermanito", new String[]{"her", "ma", "ni", "to"});
        palabrasYSilabas.put("amarillo", new String[]{"a", "ma", "ri", "llo"});
        palabrasYSilabas.put("pastelito", new String[]{"pas", "te", "li", "to"});
        palabrasYSilabas.put("peluchito", new String[]{"pe", "lu", "chi", "to"});
        palabrasYSilabas.put("cucharita", new String[]{"cu", "cha", "ri", "ta"});
        palabrasYSilabas.put("botellita", new String[]{"bo", "te", "lli", "ta"});
        palabrasYSilabas.put("almohada", new String[]{"al", "mo", "ha", "da"});
        palabrasYSilabas.put("campesino", new String[]{"cam", "pe", "si", "no"});
        palabrasYSilabas.put("cultivada", new String[]{"cul", "ti", "va", "da"});
        palabrasYSilabas.put("fertilizar", new String[]{"fer", "ti", "li", "zar"});
        palabrasYSilabas.put("espinaca", new String[]{"es", "pi", "na", "ca"});
        palabrasYSilabas.put("estimular", new String[]{"es", "ti", "mu", "lar"});
        palabrasYSilabas.put("declaración", new String[]{"de", "cla", "ra", "ción"});
        palabrasYSilabas.put("educación", new String[]{"e", "du", "ca", "ción"});
        palabrasYSilabas.put("periodista", new String[]{"pe", "rio", "dis", "ta"});
        palabrasYSilabas.put("monedero", new String[]{"mo", "ne", "de", "ro"});
        palabrasYSilabas.put("aventura", new String[]{"a", "ven", "tu", "ra"});
        palabrasYSilabas.put("camiseta", new String[]{"ca", "mi", "se", "ta"});
        palabrasYSilabas.put("conquistador", new String[]{"con", "quis", "ta", "dor"});
        palabrasYSilabas.put("animales", new String[]{"a", "ni", "ma", "les"});
        palabrasYSilabas.put("marinero", new String[]{"ma", "ri", "ne", "ro"});
        palabrasYSilabas.put("parásitos", new String[]{"pa", "rá", "si", "tos"});
        palabrasYSilabas.put("pelotazo", new String[]{"pe", "lo", "ta", "zo"});
        palabrasYSilabas.put("estudiante", new String[]{"es", "tu", "dian", "te"});
        palabrasYSilabas.put("cariñoso", new String[]{"ca", "ri", "ño", "so"});
        palabrasYSilabas.put("motivador", new String[]{"mo", "ti", "va", "dor"});
        palabrasYSilabas.put("evaluador", new String[]{"e", "va", "lua", "dor"});
        palabrasYSilabas.put("material", new String[]{"ma", "te", "rial"});
        palabrasYSilabas.put("decorado", new String[]{"de", "co", "ra", "do"});
        palabrasYSilabas.put("enfermera", new String[]{"en", "fer", "me", "ra"});
        palabrasYSilabas.put("artesano", new String[]{"ar", "te", "sa", "no"});
        palabrasYSilabas.put("velocidad", new String[]{"ve", "lo", "ci", "dad"});
        palabrasYSilabas.put("cartulina", new String[]{"car", "tu", "li", "na"});
        palabrasYSilabas.put("cartuchera", new String[]{"car", "tu", "che", "ra"});
        palabrasYSilabas.put("alegría", new String[]{"a", "le", "grí", "a"});
        palabrasYSilabas.put("calabaza", new String[]{"ca", "la", "ba", "za"});
        palabrasYSilabas.put("carretera", new String[]{"ca", "rre", "te", "ra"});
        palabrasYSilabas.put("diccionario", new String[]{"dic", "cio", "na", "rio"});
        palabrasYSilabas.put("estupendo", new String[]{"es", "tu", "pen", "do"});
        palabrasYSilabas.put("excelente", new String[]{"ex", "ce", "len", "te"});
        palabrasYSilabas.put("fantástico", new String[]{"fan", "tás", "ti", "co"});
        palabrasYSilabas.put("futurista", new String[]{"fu", "tu", "ris", "ta"});
        palabrasYSilabas.put("gallinero", new String[]{"ga", "lli", "ne", "ro"});
        palabrasYSilabas.put("generoso", new String[]{"ge", "ne", "ro", "so"});
        palabrasYSilabas.put("habitación", new String[]{"ha", "bi", "ta", "ción"});
        palabrasYSilabas.put("honorable", new String[]{"ho", "no", "ra", "ble"});
        palabrasYSilabas.put("increíble", new String[]{"in", "cre", "í", "ble"});
        palabrasYSilabas.put("lapicero", new String[]{"la", "pi", "ce", "ro"});
        palabrasYSilabas.put("liberado", new String[]{"li", "be", "ra", "do"});
        palabrasYSilabas.put("película", new String[]{"pe", "lí", "cu", "la"});
        palabrasYSilabas.put("alteridad", new String[]{"al", "te", "ri", "dad"});
        palabrasYSilabas.put("anticuario", new String[]{"an", "ti", "cua", "rio"});
        palabrasYSilabas.put("archivador", new String[]{"ar", "chi", "va", "dor"});
        palabrasYSilabas.put("blanquecino", new String[]{"blan", "que", "ci", "no"});
        palabrasYSilabas.put("bolígrafo", new String[]{"bo", "lí", "gra", "fo"});
        palabrasYSilabas.put("bondadoso", new String[]{"bon", "da", "do", "so"});
        palabrasYSilabas.put("brutalmente", new String[]{"bru", "tal", "men", "te"});
        palabrasYSilabas.put("comestible", new String[]{"co", "mes", "ti", "ble"});
        palabrasYSilabas.put("decisivo", new String[]{"de", "ci", "si", "vo"});
        palabrasYSilabas.put("democracia", new String[]{"de", "mo", "cra", "cia"});
        palabrasYSilabas.put("configurar", new String[]{"con", "fi", "gu", "rar"});
        palabrasYSilabas.put("satélite", new String[]{"sa", "té", "li", "te"});
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

        // Ajustar lambdas para evitar advertencias
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
                timeline.stop();
            }
            // Guardar el progreso con el tiempo transcurrido
            ConexionBD.guardarProgresoConTiempo(usuarioId, 3, palabrasCompletadas, false, tiempoSegundos);

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
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/nivel3.jpg"));
        BackgroundImage bgImg = new BackgroundImage(
            fondo,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        stackPane.setBackground(new Background(bgImg));

        primaryStage.setScene(new Scene(stackPane, ANCHO_VENTANA, ALTO_VENTANA));
        primaryStage.setTitle("Nivel 3 - 4 Sílabas");
        primaryStage.show();
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
            ConexionBD.guardarProgreso(usuarioId, 3, palabrasCompletadas, palabrasCompletadas == 25);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("¡Palabra formada correctamente!");
            alert.showAndWait();
            cargarNuevaPalabra();
        } else {
            vidas--;
            cantidadErrores++;
            ConexionBD.guardarIntento(usuarioId, 3, cantidadErrores);
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
    
        // Guardar el progreso con tiempo total en segundos
        ConexionBD.guardarProgresoConTiempo(usuarioId, 3, palabrasCompletadas, false, tiempoSegundos);
    
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
    
        // Guardar el progreso con tiempo total en segundos
        ConexionBD.guardarProgresoConTiempo(usuarioId, 3, palabrasCompletadas, true, tiempoSegundos);
    
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
}