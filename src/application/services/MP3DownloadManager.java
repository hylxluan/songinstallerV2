package application.services;



import application.components.SongInstallerV2Components;
import application.download.SongDownloader;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.Cursor;
import javafx.util.Duration;

public class MP3DownloadManager implements DownloadManager{

	private SongInstallerV2Components components;
	BooleanProperty isClicked = new SimpleBooleanProperty();
	
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
	
	private void handleLoadingLabel() {
		this.components.getLoadingLabel().setDisable(true);
		this.components.getLoadingLabel().setVisible(false);
		this.components.getLoadingLabel().setScaleX(0);
		this.components.getLoadingLabel().setScaleY(0);
	}
	

	@Override
	public void downloadSongs() {
		handleLoadingLabel();
		handleSongsInputField();
		ScaleTransition downloadScaleTransition = new ScaleTransition();
		ScaleTransition loadingScaleTransition = new ScaleTransition();
		downloadScaleTransition.setNode(components.getDownloadButton());
		loadingScaleTransition.setNode(components.getLoadingLabel());
		
		this.components.getDownloadButton().setOnMouseClicked(events -> {
			String[] songList = this.components.getSongsInputField().getText().split(" |\\n");
			downloadScaleTransition.setDuration(Duration.millis(300));
			downloadScaleTransition.setToX(0);
			downloadScaleTransition.setToY(0);
			downloadScaleTransition.play();
			this.components.getDownloadButton().setCursor(Cursor.DEFAULT);
			isClicked.set(true);
			this.components.getDownloadButton().setDisable(true);
			this.components.getDownloadButton().setVisible(false);
			this.components.getLoadingLabel().setVisible(true);
			this.components.getLoadingLabel().setDisable(false);
			loadingScaleTransition.setDuration(Duration.millis(700));
			loadingScaleTransition.setDelay(Duration.millis(600));
			loadingScaleTransition.setFromX(0);
			loadingScaleTransition.setFromY(0);
			loadingScaleTransition.setToX(1.2);
			loadingScaleTransition.setToY(1.2);
			loadingScaleTransition.play();
			
			SongDownloader downloader = new SongDownloader();
			Task<Void> downloadTask = new Task<Void>() {
				
				@Override
				protected Void call() throws Exception {
					downloader.initializeDownload(songList);
					return null;
				}
			};
			
			downloadTask.setOnSucceeded(event -> {
				loadingScaleTransition.setDuration(Duration.millis(600));
				loadingScaleTransition.setToX(0);
				loadingScaleTransition.setToY(0);
				loadingScaleTransition.play();
				this.components.getLoadingLabel().setDisable(true);
				this.components.getLoadingLabel().setVisible(false);
				this.components.getDownloadButton().setVisible(true);
				downloadScaleTransition.setDuration(Duration.millis(300));
				downloadScaleTransition.setToX(1.0);
				downloadScaleTransition.setToY(1.0);
				downloadScaleTransition.play();
				this.components.getDownloadButton().setDisable(false);
				this.components.getDownloadButton().setVisible(true);
				isClicked.set(false);
			});
			
			new Thread(downloadTask).start();
		});
		
		this.components.getDownloadButton().setOnMouseEntered(events -> {
			if (!isClicked.get()) {
				this.components.getDownloadButton().setCursor(Cursor.HAND);
				downloadScaleTransition.setDuration(Duration.millis(60));
				downloadScaleTransition.setToX(1.2);
				downloadScaleTransition.setToY(1.2);
				downloadScaleTransition.play();
			}
		});
		
		this.components.getDownloadButton().setOnMouseExited(events -> {
			if (!isClicked.get()) {
				downloadScaleTransition.setToX(1.0);
				downloadScaleTransition.setToY(1.0);
				downloadScaleTransition.play();
			}
		});
		
	}
	
}
