package application;
	

import application.components.SongInstallerV2Components;
import application.services.DownloadManager;
import application.services.MP3DownloadManager;
import application.utils.ResourceLoader;
import javafx.application.Application;
import javafx.stage.Stage;


public class SongInstallerV2 extends Application {
	
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("SongInstallerV2");
		stage.setWidth(800);
		stage.setHeight(500);
		stage.getIcons().add(ResourceLoader.loadImage("/images/song.png", SongInstallerV2.class));
		stage.getStyle();
		stage.setResizable(false);
		
		SongInstallerV2Components components = new SongInstallerV2Components();
		components.initializeUI(stage);
		MP3DownloadManager manager = new MP3DownloadManager();
		manager.initializeManager(components);
		
		if (manager instanceof DownloadManager) {
			manager.downloadSongs();
		}
		
		stage.show();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
