package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainClient extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.setProperty("java.security.policy","file:///Users/michal/Desktop/Main/JAVA/Lab5/client/src/main/resources/client.policy");
		System.setSecurityManager(new SecurityManager());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/MainScene.fxml"));
		StackPane stackPane = loader.load();
		
		Scene scene = new Scene(stackPane);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}

}
