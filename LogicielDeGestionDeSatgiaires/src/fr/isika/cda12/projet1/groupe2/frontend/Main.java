package fr.isika.cda12.projet1.groupe2.frontend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	double xOffset, yOffset;

	public static final String NOMUTILISATEUR1 = "Admin";
	public static final String MOTDEPASSE1 = "Admin";

	public static final String NOMUTILISATEUR2 = "Employe";
	public static final String MOTDEPASSE2 = "Employe";

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Abook");
		primaryStage.setOnHiding(e -> Platform.exit());
		primaryStage.show();

		root.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		root.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
