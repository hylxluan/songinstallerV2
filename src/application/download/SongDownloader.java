package application.download;

import java.io.IOException;

public class SongDownloader {

	public synchronized void initializeDownload(String[] songs) throws IOException, InterruptedException {
		String[] commandList = {"yt-dlp", "--extract-audio", "--audio-format", "mp3", "--audio-quality", "0"};
		String[] songList = new String[commandList.length + songs.length];
		System.arraycopy(commandList, 0, songList, 0, commandList.length);
		System.arraycopy(songs, 0, songList, commandList.length, songs.length);
		ProcessBuilder processBuilder = new ProcessBuilder(songList);
		processBuilder.inheritIO();
		Process process = processBuilder.start();
		process.waitFor();
	}
	
}
