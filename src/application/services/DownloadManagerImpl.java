package application.services;

import java.util.concurrent.atomic.AtomicBoolean;

import application.components.SongInstallerV2Components;
import javafx.scene.image.ImageView;

public class DownloadManagerImpl implements DownloadManager{

	private SongInstallerV2Components components;
	private AtomicBoolean isClicked = new AtomicBoolean(false);
	
	public void initializeManager(SongInstallerV2Components components) {
		this.components = components;
	}

	@Override
	public void handleDownloadButton(ImageView imageView) {
		
	}

	@Override
	public void handleTextAreaButton() {
		
	}

	
	
}
