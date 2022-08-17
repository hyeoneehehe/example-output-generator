package guslgogo.example.output.common;

import java.io.File;

import guslgogo.example.output.assistant.JavaParserAssistant;

public class ParsingThread implements Runnable {
	
	private final File file;
	
	public ParsingThread(File file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		try {
			JavaParserAssistant assistant = new JavaParserAssistant(null);
			
		} catch(Exception e) {
			
		}
	}
	
}
