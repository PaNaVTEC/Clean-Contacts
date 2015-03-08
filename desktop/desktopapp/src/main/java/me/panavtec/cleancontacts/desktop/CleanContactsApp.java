package me.panavtec.cleancontacts.desktop;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CleanContactsApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLMain.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
