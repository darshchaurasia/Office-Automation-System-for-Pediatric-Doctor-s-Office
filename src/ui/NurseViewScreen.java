package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.UIHelper;

public class NurseViewScreen extends Application {

	@Override
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Nurse Screen");

		 VBox vbox = new VBox(10);
	        vbox.setAlignment(Pos.CENTER);

		
	        Button patientButton = UIHelper.createButton("Register Patient");
	        patientButton.setOnAction(e -> openScreen(new PatientRegistration()));


	        Button DashboardButton = UIHelper.createButton("Open Dashboard");
	        DashboardButton.setOnAction(e -> openScreen(new NurseDocDashboardScreen()));
	
	
	        vbox.getChildren().addAll(patientButton, DashboardButton);

	        Scene scene = new Scene(vbox, 300, 250);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	
	
	   private void openScreen(Application screen) {
	        try {
	            Stage stage = new Stage();
	            screen.start(stage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }

}
