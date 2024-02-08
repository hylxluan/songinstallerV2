module SongInstaller2 {
	requires javafx.controls;
	requires java.desktop;
	requires lombok;
	
	opens application to javafx.graphics, javafx.fxml;
}
