package application.utils;

import java.util.Objects;

import javafx.scene.image.Image;

public class ResourceLoader {
	
	public static <T> Image loadImage(String imagePath, Class<T> resourceClass) {
		return new Image(Objects.requireNonNull(resourceClass.getResource(imagePath).toExternalForm()));
	}
	
	private ResourceLoader() {
		
	}
}
