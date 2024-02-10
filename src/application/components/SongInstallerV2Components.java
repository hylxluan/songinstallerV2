package application.components;

import application.utils.ResourceLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SongInstallerV2Components {
	
	private Scene scene;
	private Label downloadLabel, arrowLabel, loadingLabel;
	private Button downloadButton;
	private TextArea songsInputField;
	
	public void initializeUI(Stage stage) {
		BorderPane root = new BorderPane();
		this.scene = new Scene(root, 800, 500);
		this.scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
		createDownloadLabel();
		createTextArea();
		createArrowLabel();
		createDownloadButton();
		createLoadingAnimation();
		root.setTop(this.downloadLabel);
		root.setCenter(this.songsInputField);
		root.setLeft(this.arrowLabel);
		root.setBottom(this.downloadButton);
		root.setRight(this.loadingLabel);
		BorderPane.setAlignment(this.downloadLabel, Pos.TOP_CENTER);
		BorderPane.setAlignment(this.arrowLabel, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(this.downloadButton, Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(this.loadingLabel, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(this.downloadLabel, new Insets(-70, 0, 0, -25));
		BorderPane.setMargin(this.songsInputField, new Insets(-160, 0, 0, -155));
		BorderPane.setMargin(this.arrowLabel, new Insets(20, 0, -40, 160));
		BorderPane.setMargin(this.downloadButton, new Insets(-58, 5, 115, -15));
		BorderPane.setMargin(this.loadingLabel, new Insets(0, 375, 0, -285));
		root.getStyleClass().add("scene");
		stage.setScene(scene);
	}
	
	private void createDownloadLabel() {
		this.downloadLabel = new Label();
		ImageView imageView = new ImageView(ResourceLoader.loadImage("/images/downloadSong.png", SongInstallerV2Components.class));
		imageView.setFitHeight(390);
		imageView.setFitWidth(390);
		this.downloadLabel.setGraphic(imageView);
	}
	
	private void createTextArea() {
		this.songsInputField = new TextArea();
		this.songsInputField.setOpacity(0.6);
		this.songsInputField.setWrapText(true);
		this.songsInputField.setMaxWidth(350);
		this.songsInputField.setMinWidth(350);
		this.songsInputField.setMinHeight(100);
		this.songsInputField.setMaxHeight(100);
		this.songsInputField.setEditable(false);
		this.songsInputField.getStyleClass().add("songsInputField");
	}
	
	private void createArrowLabel() {
		this.arrowLabel = new Label();
		this.arrowLabel.setMaxHeight(120);
		this.arrowLabel.setMinHeight(120);
		this.arrowLabel.setMaxWidth(120);
		this.arrowLabel.setMinWidth(120);
		ImageView imageView = new ImageView(ResourceLoader.loadImage("/images/arrow.gif", SongInstallerV2Components.class));
		imageView.setFitHeight(120);
		imageView.setFitWidth(120);
		this.arrowLabel.setGraphic(imageView);
	}
	
	private void createDownloadButton() {
		this.downloadButton = new Button();
		this.downloadButton.setMaxHeight(65);
		this.downloadButton.setMinHeight(65);
		this.downloadButton.setMaxWidth(65);
		this.downloadButton.setMinWidth(65);
		this.downloadButton.setStyle("-fx-background-color: #6169F9;");
		ImageView imageView = new ImageView(ResourceLoader.loadImage("/images/downloadButton.png", SongInstallerV2Components.class));
		imageView.setFitHeight(65);
		imageView.setFitWidth(65);
		imageView.setScaleX(1.0);
		imageView.setScaleY(1.0);
		this.downloadButton.setGraphic(imageView);
		this.downloadButton.getStyleClass().add("downloadButton");
	}
	
	private void createLoadingAnimation() {
		this.loadingLabel = new Label();
		this.loadingLabel.setMaxHeight(50);
		this.loadingLabel.setMinHeight(50);
		this.loadingLabel.setMaxWidth(50);
		this.loadingLabel.setMinWidth(50);
		ImageView imageView = new ImageView(ResourceLoader.loadImage("/images/loading.gif", SongInstallerV2Components.class));
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		imageView.setScaleX(1);
		imageView.setScaleY(1);
		this.loadingLabel.setGraphic(imageView);
		this.loadingLabel.getStyleClass().add("loading");
	}
	
}
