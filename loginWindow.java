package astroTracker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//got help from Oracle Document
public class loginWindow extends Application {

	String username = "astro";
	String password = "tracker";
	GridPane grid = new GridPane();
	VBox vbox = new VBox();
	HBox hbox = new HBox();
	HBox hbox2 = new HBox(10);
	
	Scene scene = new Scene(vbox, 500, 375);
	
	
ControllerWindow nextwindow = new ControllerWindow();
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//primaryStage = new Stage(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Login");
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
		grid.add(scenetitle, 0, 0, 2, 1);
		scenetitle.setFill(Color.rgb(255,255,255));


		Label userName = new Label("Username:");
		userName.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 13));
		userName.setTextFill(Color.rgb(255,255,255));


		grid.add(userName, 0, 1);

		TextField unBox = new TextField();
		unBox.setPrefWidth(150);
		grid.add(unBox, 1, 1);

		Label pw = new Label("Password:");
		pw.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 13));
		pw.setTextFill(Color.rgb(255,255,255));


		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		vbox.getChildren().add(grid);
		Button btn = new Button("Sign in");
		btn.setMinSize(90, 25);
		btn.setMaxSize(90, 25);

		
		Button btn2 = new Button("Quit");
		btn2.setMinSize(90, 25);
		btn2.setMaxSize(90, 25);
		
		hbox.getChildren().add(btn);
		hbox.getChildren().add(btn2);
		HBox.setMargin(btn, new Insets(0, 5, 5, 5));
		HBox.setMargin(btn2, new Insets(0, 5, 5, 5));
		hbox.setAlignment(Pos.TOP_CENTER);


		
		vbox.getChildren().add(hbox);
		
		/*HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);*/
		//grid.add(hbBtn, 1, 4, 2, 1);
		
		
		/*		Button btn2 = new Button("Cancel");
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn2.getChildren().add(btn2);
		grid.add(hbBtn2, 1, 4);*/



		final Text errormsg = new Text();
		errormsg.setFill(Color.RED);
		errormsg.setFont(Font.font("MS Reference Sans Serif", FontWeight.BOLD, 12));
		errormsg.setText("The email or the password you've entered is incorrect.\nUsername : astro\nPassword : tracker");
		errormsg.setVisible(false);
		hbox2.getChildren().add(errormsg);
		vbox.getChildren().add(hbox2);
		hbox2.setAlignment(Pos.CENTER);
		HBox.setMargin(errormsg, new Insets(15,0,0,0));



		//grid.add(errormsg, 1, 6);
		
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (unBox.getText().equals(username) && pwBox.getText().equals(password)){
					primaryStage.hide();
					new ControllerWindow().getStage().show();
					//borderpane.setCenter(new Pane());
					//borderpane.setCenter(nextwindow.getTheNextPane());
				}
				else{
					errormsg.setVisible(true);
				}
			}
		});
		
		btn2.setOnAction(e -> {
			Platform.exit();					
		});

		vbox.setAlignment(Pos.CENTER);
		



		new ControllerWindow().getStage().show();
		primaryStage.setScene(scene);
		primaryStage.show();
		vbox.setStyle("-fx-background-color: rgb(2,227,186);");
	}
}


