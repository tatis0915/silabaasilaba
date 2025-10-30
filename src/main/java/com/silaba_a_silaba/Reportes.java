package com.silaba_a_silaba;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reportes {

    private int usuarioId; // ID del usuario para filtrar los reportes
    private String nombreEstudiante; // Nombre del estudiante
    private String identificacion; // Identificación del estudiante

    private static final int ANCHO_VENTANA = 700;
    private static final int ALTO_VENTANA = 600;
    private static final String FUENTE_BONITA = "Arial Rounded MT Bold";

    // Constructor para recibir el ID del usuario
    public Reportes(int usuarioId) {
        this.usuarioId = usuarioId;
        cargarDatosUsuario(); // Cargar datos del usuario desde la base de datos
    }

    private void cargarDatosUsuario() {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error: La conexión a la base de datos es nula.");
            nombreEstudiante = "Desconocido";
            identificacion = "N/A";
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT nombre, tarjeta_identidad FROM usuario WHERE id_usuario = ?")) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nombreEstudiante = rs.getString("nombre");
                    identificacion = rs.getString("tarjeta_identidad");
                } else {
                    nombreEstudiante = "Desconocido";
                    identificacion = "N/A";
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos del usuario: " + e.getMessage());
            nombreEstudiante = "Desconocido";
            identificacion = "N/A";
        } finally {
            try { conn.close(); } catch (SQLException ignored) {}
        }
    }

    public void mostrarPantalla(Stage stage) {
        // Fecha actual
        String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Encabezado con información del estudiante
        Label lblNombre = new Label("Nombre: " + nombreEstudiante);
        estilizarLabelBonito(lblNombre);
        Label lblIdentificacion = new Label("T.I.: " + identificacion);
        estilizarLabelBonito(lblIdentificacion);
        Label lblFecha = new Label("Fecha: " + fechaActual);
        estilizarLabelBonito(lblFecha);

        VBox encabezado = new VBox(5, lblNombre, lblIdentificacion, lblFecha);
        encabezado.setAlignment(Pos.TOP_LEFT);

        // Tablas para cada nivel
        VBox nivel1 = crearTablaNivel("Nivel 1", 1);
        VBox nivel2 = crearTablaNivel("Nivel 2", 2);
        VBox nivel3 = crearTablaNivel("Nivel 3", 3);

        // Botones de navegación
        Button btnAtras = new Button("Atrás");
        estilizarBotonPequeno(btnAtras);
        btnAtras.setOnAction(actionEvent -> {
            Opciones opciones = new Opciones(usuarioId);
            opciones.mostrarPantalla(stage);
        });

        Button btnSalir = new Button("Salir");
        estilizarBotonPequeno(btnSalir);
        btnSalir.setOnAction(actionEvent -> stage.close());

        HBox botonesNavegacion = new HBox(10, btnAtras, btnSalir);
        botonesNavegacion.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, encabezado, nivel1, nivel2, nivel3, botonesNavegacion);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(layout);
        stackPane.setPrefSize(ANCHO_VENTANA, ALTO_VENTANA);

        // Configurar fondo
        Image fondo = new Image(getClass().getResourceAsStream("/resources/imagenes/reportes.jpg"));
        BackgroundImage bgImg = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(ANCHO_VENTANA, ALTO_VENTANA, false, false, false, false)
        );
        stackPane.setBackground(new Background(bgImg));

        Scene scene = new Scene(stackPane, ANCHO_VENTANA, ALTO_VENTANA);
        stage.setScene(scene);
        stage.setTitle("Reportes");
        stage.show();
    }

    private VBox crearTablaNivel(String tituloNivel, int nivel) {
        Label lblTitulo = new Label(tituloNivel);
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #3F51B5; -fx-font-weight: bold;");

        // Crear tabla con datos del nivel
        GridPane tabla = new GridPane();
        tabla.setHgap(10);
        tabla.setVgap(10);
        tabla.setPadding(new Insets(10));

        // Encabezados
        Label lblPalabrasFormadas = new Label("Palabras Formadas");
        lblPalabrasFormadas.setStyle("-fx-font-weight: bold;");
        tabla.add(lblPalabrasFormadas, 0, 0);

        Label lblCompletado = new Label("Completado");
        lblCompletado.setStyle("-fx-font-weight: bold;");
        tabla.add(lblCompletado, 1, 0);

        Label lblIntentos = new Label("Intentos Realizados");
        lblIntentos.setStyle("-fx-font-weight: bold;");
        tabla.add(lblIntentos, 2, 0);

        Label lblTiempoPromedio = new Label("Tiempo Promedio");
        lblTiempoPromedio.setStyle("-fx-font-weight: bold;");
        tabla.add(lblTiempoPromedio, 3, 0);

        // Cargar datos del nivel
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error: La conexión a la base de datos es nula.");
            // Rellenar con valores por defecto
            Label lblPalabras = new Label("0/25");
            lblPalabras.setStyle("-fx-font-weight: bold;");
            tabla.add(lblPalabras, 0, 1);

            Label lblComp = new Label("No");
            lblComp.setStyle("-fx-font-weight: bold;");
            tabla.add(lblComp, 1, 1);

            Label lblInt = new Label("0");
            lblInt.setStyle("-fx-font-weight: bold;");
            tabla.add(lblInt, 2, 1);

            Label lblTiempo = new Label("N/A");
            lblTiempo.setStyle("-fx-font-weight: bold;");
            tabla.add(lblTiempo, 3, 1);
        } else {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.palabras_formadas, p.completado, " +
                            "       (SELECT COUNT(*) FROM intento i WHERE i.usuario_id = p.usuario_id AND i.nivel = p.nivel) AS intentos, " +
                            "       p.tiempo_total_segundos " +
                            "FROM progreso p " +
                            "WHERE p.usuario_id = ? AND p.nivel = ?")) {

                stmt.setInt(1, usuarioId);
                stmt.setInt(2, nivel);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Palabras formadas
                        Label lblPalabras = new Label(rs.getInt("palabras_formadas") + "/25");
                        lblPalabras.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblPalabras, 0, 1);

                        // Completado
                        Label lblComp = new Label(rs.getBoolean("completado") ? "Sí" : "No");
                        lblComp.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblComp, 1, 1);

                        // Intentos realizados
                        Label lblInt = new Label(String.valueOf(rs.getInt("intentos")));
                        lblInt.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblInt, 2, 1);

                        // Tiempo promedio (convertir de segundos a minutos y segundos)
                        int tiempoTotalSegundos = rs.getInt("tiempo_total_segundos");
                        Label lblTiempo;
                        if (tiempoTotalSegundos > 0) {
                            int minutos = tiempoTotalSegundos / 60;
                            int segundos = tiempoTotalSegundos % 60;
                            String tiempoFormateado = minutos + " minutos y " + segundos + " segundos";
                            lblTiempo = new Label(tiempoFormateado);
                        } else {
                            lblTiempo = new Label("N/A");
                        }
                        lblTiempo.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblTiempo, 3, 1);
                    } else {
                        // Si no hay datos para el nivel
                        Label lblPalabras = new Label("0/25");
                        lblPalabras.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblPalabras, 0, 1);

                        Label lblComp = new Label("No");
                        lblComp.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblComp, 1, 1);

                        Label lblInt = new Label("0");
                        lblInt.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblInt, 2, 1);

                        Label lblTiempo = new Label("N/A");
                        lblTiempo.setStyle("-fx-font-weight: bold;");
                        tabla.add(lblTiempo, 3, 1);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error al cargar los datos del nivel " + nivel + ": " + e.getMessage());
            } finally {
                try { conn.close(); } catch (SQLException ignored) {}
            }
        }

        VBox contenedorNivel = new VBox(10, lblTitulo, tabla);
        contenedorNivel.setAlignment(Pos.TOP_LEFT);
        return contenedorNivel;
    }

    private void estilizarBotonPequeno(Button btn) {
        btn.setStyle("-fx-font-size: 16px; -fx-font-family: 'Arial Rounded MT Bold'; -fx-text-fill: white; -fx-background-color: #4CAF50; -fx-padding: 10px 24px; -fx-background-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 3);");
        btn.setMaxWidth(180);
        btn.setFont(Font.font(FUENTE_BONITA, FontWeight.BOLD, 16));
        btn.setOnMouseEntered(mouseEvent -> btn.setStyle(btn.getStyle() + "-fx-cursor: hand; -fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        btn.setOnMouseExited(mouseEvent -> btn.setStyle(btn.getStyle().replace("-fx-scale-x: 1.1; -fx-scale-y: 1.1;", "")));
    }

    private void estilizarLabelBonito(Label lbl) {
        lbl.setFont(Font.font(FUENTE_BONITA, FontWeight.BOLD, 20));
        lbl.setStyle("-fx-text-fill: #333; -fx-font-weight: bold;");
    }
}