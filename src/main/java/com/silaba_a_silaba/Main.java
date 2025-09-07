package com.silaba_a_silaba;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
   public Main() {
   }

   public void start(Stage var1) {
      VentanaInicial var2 = new VentanaInicial();
      var2.mostrarPantalla(var1);
   }

   public static void main(String[] var0) {
      launch(var0);
   }
}
