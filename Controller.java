package application;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private BorderPane MainBorderPane;
    @FXML
    private VBox MainBpaneTVBox;
    @FXML
    private MenuBar MenuBar;
    @FXML
    private Menu mItem1;
    @FXML
    private Menu mItem2;
    @FXML
    private Menu mItem3;
    @FXML
    private FlowPane topPane;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab cameraTab;
    @FXML
    private BorderPane captureBorderPane;
    @FXML
    private ImageView imgViewMain;
    @FXML
    private VBox cameraVBoxR;
    @FXML
    private Button VideoButton;
    @FXML
    private Button VideoStartButton;
    @FXML
    private Button videoStopButton;
    @FXML
    private ToggleButton toggleCaptureMode;
    @FXML
    private Button CaptureImageButton;
    @FXML
    private TitledPane TitlePaneFileExp;
    @FXML
    private ScrollPane cameraScrPaneB;
    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView2;
    @FXML
    private ImageView imgView3;
    @FXML
    private ImageView imgView4;
    @FXML
    private ImageView imgView5;
    @FXML
    private Tab videoTab;
    @FXML
    private BorderPane videoBorderPane;
    @FXML
    private Button liveButton;
    @FXML
    private ImageView webcamVideoImgView;
    
    // ************************************************************************************************
    
    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;
    
    @FXML
    void pauseCam(ActionEvent event) {
    	imageProperty = null;
    	stopCamera = true;
    	
    }

    @FXML
    void playCam(ActionEvent event) {
    	stopCamera = false;
    	
    	startLiveFeed();
    }
    
    
    
    
    // ActionEvents...  *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*

 
    // Capture Image --------------------------------------------------------
    private int counter = 0;
    @FXML
    void CaptureImage(ActionEvent event) {
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		//opens webcam
		webcam.open();
		BufferedImage image = null;
		//capture image
		try {
			image = webcam.getImage();
			ImageIO.write(image, "jpg", new File("test.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//closes webcam
		webcam.close();
		// add img to imgViewMain
		imgViewMain.setImage(SwingFXUtils.toFXImage(image, null));
		if (counter == 4) {
			imgView5.setImage(SwingFXUtils.toFXImage(image, null));
			counter++;
		}
		else if (counter == 3) {
			imgView4.setImage(SwingFXUtils.toFXImage(image, null));
			counter++;
		}
		else if (counter == 2) {
			imgView3.setImage(SwingFXUtils.toFXImage(image, null));
			counter++;
		}
		else if (counter == 1) {
			imgView2.setImage(SwingFXUtils.toFXImage(image, null));
			counter++;
		}
		else if (counter == 0) {
			imgView1.setImage(SwingFXUtils.toFXImage(image, null));
			counter++;
		}
    }
    
    
    
    
    
    
    
    
    // JFrame Live feed..
	@FXML
	void liveFeed(ActionEvent event) {

		SwingNode node = new SwingNode();
		WebcamPanel webcamPanel = new WebcamPanel(webcam, true);

		videoBorderPane.setCenter(node);
		webcamPanel.start();
	}	
	
	
	

	// Live Feed --------------------------------------------------------
	 @FXML
	    void startLive(ActionEvent event) {
		 
		 	if (webcam.isOpen() == false) {
				webcam.open();
			}
		 	
			startLiveFeed();
	    }

    
    
    
    
    // Stop Live Feed ----------------------------------------------------------------------
    @FXML
    void stopLive(ActionEvent event) {
    	stopCamera = true;
		webcam.close();
		//disposeWebcam();
		//Webcam.shutdown();
		// enable/disable buttons
    }

    @FXML
    void videoMode(ActionEvent event) {
    	createTopPanel();
    }
    
    // End @FXML... *	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
    
    private String cameraListPromptText = "Choose Camera";
    
    private boolean stopCamera = true;
    
    private BufferedImage grabbedImage;
    
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    
    // Default webcam
    Webcam webcam = Webcam.getDefault();
    
    
    
    
    
    
    // Webcam selector ----------------------------------------------------------
    private void createTopPanel() {

		int webCamCounter                  = 0;
		//Label lbInfoLabel                  = new Label("Select Your WebCam Camera");
		ObservableList<WebCamInfo> options = FXCollections.observableArrayList(
				);
		
		//topPane.getChildren().add(lbInfoLabel);
		for(Webcam webcam:Webcam.getWebcams())
		{
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter ++;
		}
		ComboBox<WebCamInfo> cameraOptions = new ComboBox<WebCamInfo>();
		cameraOptions.setItems(options);
		cameraOptions.setPromptText(cameraListPromptText);
		cameraOptions.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<WebCamInfo>() {

	        @Override
	        public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
	            if (arg2 != null) {
	               
	            	System.out.println("WebCam Index: " + arg2.getWebCamIndex()+": WebCam Name:"+ arg2.getWebCamName());
	            	initializeWebCam(arg2.getWebCamIndex());
	            }
	        }
	    });
		topPane.getChildren().add(cameraOptions);
	}
    
    
    
    
    
    
    
    
    
    // Initialize
	protected void initializeWebCam(final int webCamIndex) {
		
		Task<Void> webCamTask = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				
				if(webcam != null)
				{
					disposeWebcam();
					webcam = Webcam.getWebcams().get(webCamIndex);
					
					for( Dimension supportedSize: webcam.getViewSizes()) {
						System.out.println(supportedSize.toString());
					}
					webcam.setViewSize(WebcamResolution.VGA.getSize());
					webcam.open();
				}else
				{
					webcam = Webcam.getWebcams().get(webCamIndex);
					for( Dimension supportedSize: webcam.getViewSizes()) {
						System.out.println(supportedSize.toString());
					}
					webcam.setViewSize(WebcamResolution.VGA.getSize());
					webcam.open();
				}
				
				startLiveFeed();
				return null;
			}
		};
		
		Thread webCamThread = new Thread(webCamTask);
		webCamThread.setDaemon(true);
		webCamThread.start();
		//bottomCameraControlPane.setDisable(false);
		//btnCamreaStart.setDisable(true);
		
	}
	
	//Start live feed ------------------------------------------------------------------------------------------------
	protected void startLiveFeed() {
		imgViewMain.setImage(null);
		stopCamera  = false;
		Task<Void> task = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				while (!stopCamera) {
					try {
						if ((grabbedImage = webcam.getImage()) != null) {
							System.out.println("Captured Image height*width:"+grabbedImage.getWidth()+"*"+grabbedImage.getHeight());
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
											.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
								}
							});
							grabbedImage.flush();
						}
					} catch (Exception e) {
					} finally {
					}
				}
				return null;
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgViewMain.imageProperty().bind(imageProperty);
	}

	// removes device ----------------------------------------------------------------------
	protected void disposeWebcam() {
		stopCamera = true;
		webcam.close();
}
    
	
	// Displays information about the camera -------------------------------------------------
	class WebCamInfo
	{
		private String webCamName ;
		private int webCamIndex ;
		
		public String getWebCamName() {
			return webCamName;
		}
		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}
		public int getWebCamIndex() {
			return webCamIndex;
		}
		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}
		
		@Override
		public String toString() {
		        return webCamName;
	     }
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
