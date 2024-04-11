package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.UIHelper; // using util class for handling buttons

public class main extends Application {

	@Override
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to Office Automation System for CSE360");

		 VBox vbox = new VBox(10);
	        vbox.setAlignment(Pos.CENTER);

// declaring buttons for navigation on main dashboard

	        Button NursePortal = UIHelper.createButton("Nurses Click Here");
	        NursePortal.setOnAction(e -> openScreen(new NurseViewScreen()));
	
	        Button DoctorPortal = UIHelper.createButton("Doctors Click Here");
	        DoctorPortal.setOnAction(e -> openScreen(new DoctorViewScreen()));
	        
	        Button PatientPortal = UIHelper.createButton("Patients Click Here");
	        PatientPortal.setOnAction(e -> openScreen(new PatientViewScreen()));
	    
// declaring display funcitonality for all buttons    
	        vbox.getChildren().addAll(NursePortal, DoctorPortal, PatientPortal);

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
