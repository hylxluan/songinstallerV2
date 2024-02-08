package application.mock;

import java.io.IOException;

public class SongInstallerMock {

	public static void main(String[] args) {
		
        // specify song list
		String[] songs = {
				"",
				"",
				""
			};
		
		String[] commandList = {"yt-dlp", "--extract-audio", "--audio-format", "mp3", "--audio-quality", "0"};
		
		String[] songList = new String[commandList.length + songs.length];
		System.arraycopy(commandList, 0, songList, 0, commandList.length);
		System.arraycopy(songs, 0, songList, commandList.length, songs.length);
		
		ProcessBuilder processBuilder = new ProcessBuilder(songList);
		processBuilder.inheritIO();
		
		
		try {
			
			Process process = processBuilder.start();
			process.waitFor();
			
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
		
		
		
	}

}
