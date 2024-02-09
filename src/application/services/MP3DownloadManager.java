package application.services;

import java.util.concurrent.atomic.AtomicBoolean;

import application.components.SongInstallerV2Components;
import javafx.animation.ScaleTransition;
import javafx.scene.Cursor;
import javafx.util.Duration;

public class MP3DownloadManager implements DownloadManager{

	private SongInstallerV2Components components;
	private AtomicBoolean isClicked = new AtomicBoolean(false);
	
	public void initializeManager(SongInstallerV2Components components) {
		this.components = components;
	}

	private void handleSongsInputField() {
		
		this.components.getScene().setOnMousePressed(event -> {
			this.components.getSongsInputField().setEditable(false);
		});
		
		this.components.getSongsInputField().setOnMouseClicked(event -> {
			this.components.getSongsInputField().setEditable(true);
			this.components.getSongsInputField().requestFocus();
		});
		
		this.components.getSongsInputField().focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				this.components.getSongsInputField().setEditable(false);
			}
		});
		
	}

	@Override
	public void downloadSongs() {
		
		handleSongsInputField();
		ScaleTransition scaleTransition = new ScaleTransition();
		scaleTransition.setNode(components.getDownloadButton());
		
		this.components.getDownloadButton().setOnMouseClicked(events -> {
			scaleTransition.setDuration(Duration.millis(300));
			scaleTransition.setToX(0);
			scaleTransition.setToY(0);
			scaleTransition.play();
			this.components.getDownloadButton().setCursor(Cursor.DEFAULT);
			isClicked.set(true);
			this.components.getDownloadButton().setDisable(true);
		});
		
		this.components.getDownloadButton().setOnMouseEntered(events -> {
			if (!isClicked.get()) {
				this.components.getDownloadButton().setCursor(Cursor.HAND);
				scaleTransition.setDuration(Duration.millis(60));
				scaleTransition.setToX(1.2);
				scaleTransition.setToY(1.2);
				scaleTransition.play();
			}
		});
		
		this.components.getDownloadButton().setOnMouseExited(events -> {
			if (!isClicked.get()) {
				scaleTransition.setToX(1.0);
				scaleTransition.setToY(1.0);
				scaleTransition.play();
			}
		});
	}
	
}
