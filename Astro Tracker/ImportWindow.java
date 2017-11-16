import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;







public class ImportWindow extends Application {
	public static void main(String[] args) {
        launch(args);
    }
	

	private Stage stage = new Stage(StageStyle.UNDECORATED);
	

	public Stage getStage() {
		return stage;
	}

	private ObservableList<File> selectedFiles = FXCollections.observableArrayList();

	private ListView<File> lvList = new ListView<>();

	//private ObservableList<PhotoFiles> pFiles = FXCollections.observableArrayList();


	public ImportWindow() {

		VBox vbox = new VBox();
		VBox vbox1 = new VBox(10);
		VBox vbox2 = new VBox(5);
		GridPane grid1 = new GridPane();

		// HBox hbox1 = new HBox(10);
		// HBox hbox2 = new HBox(10);
		HBox hbox3 = new HBox(10);
		HBox hbox4 = new HBox(10);
		HBox hbox5 = new HBox(10);
		BorderPane titleList = new BorderPane();
		titleList.setPadding(new Insets(10, 20, 10, 20));


		Scene scene = new Scene(vbox, 920, 980);
		
		// File chosenFile = pickfile();
		// HBox hbox4 = new HBox();
		// variables
		// String actfiles = null;
		

		
		// ==============================================================================================\\
		// Buttons, Labels, and others
		Label importFiles = new Label("Import Files");
		importFiles.setTextFill(Color.rgb(255,255,255));
		importFiles.setFont(Font.font("MS Reference Sans Serif", FontWeight.BOLD, 20));

		Label explanation = new Label("Select a directory or a file to search for existing files.");
		explanation.setTextFill(Color.rgb(255,255,255));
		explanation.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 14));

		// Top_Left
		Label selectFile = new Label("Select Files:");
		selectFile.setTextFill(Color.rgb(255,255,255));
		selectFile.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));


		Label selectDirectory = new Label("Select Directory:");
		selectDirectory.setTextFill(Color.rgb(255,255,255));
		selectDirectory.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		// Top_Center
		TextField directoryTF = new TextField("P:\\");
		directoryTF.setPrefWidth(500);
		directoryTF.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		TextField fileTF = new TextField("P:\\");
		fileTF.setPrefWidth(500);
		fileTF.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		// Top_Right
		Button attachFile1 = new Button("Browse");
		attachFile1.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));
		attachFile1.setOnAction(e -> {
			File chosen = pickFile();
			if (chosen != null) {
				selectedFiles.add(chosen);
			}

		});

		Button attachFile2 = new Button("Browse"); // browse button
		attachFile2.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));
		attachFile2.setOnAction(e -> {
			File dir = pickDirectory();
			if (dir != null) {
				File[] files = dir.listFiles(file -> file.getName().endsWith(".m"));
				for (File file : files) {
					selectedFiles.add(file);
				}
			}
		});

		// Middle_Left
		// Rectangle filesUploadedTitle = new Rectangle(200, 100);
		// Label filesUploadedTitle = new Label("Files Uploaded:");

		lvList.setItems(selectedFiles);
		lvList.setMaxHeight(Control.USE_PREF_SIZE);
		lvList.setPrefWidth(500.0);

		// border.setBottom(vbox3()); // Uses a tile pane for sizing
		// border.setBottom(createButtonBox()); // Uses an HBox, no sizing

		// Middle_Center
		// Label filesUploaded = new Label(actfiles);
		// filesUploaded.setText(filesUploaded.getText()+;

		// Middle_Right
		Button selectAll = new Button("Select All"); // selects all files uploaded
		selectAll.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		Button deselectAll = new Button("Deselect All");// deselects all files uploaded
		deselectAll.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		Button deleteFile = new Button("Delete");// deletes selected file
		deleteFile.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));
		deleteFile.setOnAction(e -> { // if delete button is pressed
			selectedFiles.remove(lvList.getSelectionModel().getSelectedItem());
		});

		final Text errormsg = new Text(); // 2 or less files
		errormsg.setFill(Color.RED);
		errormsg.setFont(Font.font("MS Reference Sans Serif", FontWeight.BOLD, 12));
		errormsg.setText("You need to attach two or more files to run the program.");
		errormsg.setVisible(false);

		selectAll.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		deselectAll.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		deleteFile.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		// Bottom_Right
		Button runProgram = new Button("Finish");
		runProgram.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));

		//RUN PROGRAM
		/*		runProgram.setOnAction(e -> {
			if (selectedFiles.size() < 2) {
				errormsg.setVisible(true);
			} else {
				for (File file : selectedFiles) {
					pFiles.add(new PhotoFile(file));
					stage.hide();
					
					// do calculations for selected stuff
				}
				FileComparer comparisons = new FileComparer(pFiles);
				new GUI(pFiles, comparisons).getStage().show();
			}
		});*/

		Button quitProgram = new Button("Cancel"); // quit program
		quitProgram.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 12));
		quitProgram.setOnAction(e -> {
			Platform.exit();
		});

		runProgram.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // btn size
		quitProgram.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);// btn size

		Label files = new Label("Select Files:"); 
		files.setFont(Font.font("MS Reference Sans Serif", FontWeight.NORMAL, 14));
		files.setTextFill(Color.rgb(255,255,255));

		// ==============================================================================================\\

		// ==============================================================================================\\
		vbox1.getChildren().add(importFiles);
		vbox1.getChildren().add(explanation);
		vbox1.setPadding(new Insets(30, 0, 25, 20));
		vbox.getChildren().add(vbox1);

		grid1.setAlignment(Pos.TOP_LEFT); 
		grid1.setHgap(10);
		grid1.setVgap(10);
		grid1.setPadding(new Insets(0, 0, 10, 40));
		grid1.add(selectFile, 0, 1);
		grid1.add(directoryTF, 1, 1);
		grid1.add(attachFile1, 2, 1);

		grid1.add(selectDirectory, 0, 2);
		grid1.add(fileTF, 1, 2);
		grid1.add(attachFile2, 2, 2);
		vbox.getChildren().add(grid1);

		/*
		 * hbox1.getChildren().add(selectDirectory);
		 * hbox1.getChildren().add(directoryTF);
		 * hbox1.getChildren().add(attachFile1); vbox.getChildren().add(hbox1);
		 * hbox1.setPadding(new Insets(0, 40, 5, 40));
		 * 
		 * hbox2.getChildren().add(selectFile); hbox2.getChildren().add(fileTF);
		 * hbox2.getChildren().add(attachFile2); vbox.getChildren().add(hbox2);
		 * hbox2.setPadding(new Insets(0, 40, 5, 40));
		 */

		// vbox.getChildren().add(filesUploadedTitle);
		// vbox1.getChildren().add(filesUploaded);
		// vbox.getChildren().add(vbox1);

		hbox3.getChildren().add(files);
		hbox3.setPadding(new Insets(20, 40, 5, 40));
		vbox.getChildren().add(hbox3);

		vbox2.getChildren().add(selectAll); // buttons
		vbox2.getChildren().add(deselectAll);
		vbox2.getChildren().add(deleteFile);
		// vbox.getChildren().add(vbox2);

		titleList.setPadding(new Insets(0, 40, 20, 40));
		titleList.setCenter(lvList); // list of files
		titleList.setRight(vbox2);
		vbox.getChildren().add(titleList);
		BorderPane.setMargin(vbox2, new Insets(0, 15, 0, 15));
		BorderPane.setMargin(titleList, new Insets(10));

		hbox4.getChildren().add(runProgram);
		hbox4.getChildren().add(quitProgram);
		hbox4.setPadding(new Insets(10, 40, 20, 40));
		HBox.setMargin(runProgram, new Insets(0, 0, 5, 0));
		HBox.setMargin(quitProgram, new Insets(0, 0, 5, 5));
		vbox.getChildren().add(hbox4);

		hbox5.getChildren().add(errormsg);
		HBox.setMargin(errormsg, new Insets(0, 0, 5, 40));
		vbox.getChildren().add(hbox5);

		// vbox.getChildren().add(files3);
		// vbox.getChildren().add();

		stage.setScene(scene);
		vbox.setStyle("-fx-background-color: rgb(2,227,186);");
		stage.setResizable(false);
	}

	public static File pickFile() { // choosing file
		FileChooser chooser1 = new FileChooser();
		chooser1.setTitle("Select a File");
		chooser1.getExtensionFilters().add(new ExtensionFilter("Photos", "*.jpg"));
		File defaultDirectory1 = new File("P:\\");
		chooser1.setInitialDirectory(defaultDirectory1);
		return chooser1.showOpenDialog(null);
	}

	public static File pickDirectory() { // choosing directory
		DirectoryChooser chooser2 = new DirectoryChooser();
		chooser2.setTitle("Select a Directory");
		File defaultDirectory2 = new File("P:\\");
		chooser2.setInitialDirectory(defaultDirectory2);
		return chooser2.showDialog(null);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
	
