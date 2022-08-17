package guslgogo.example.output.common;

import java.io.File;

public class ParsingEntryPoint {
	
	public void enterProcess(String path) {
		CompilationUnitContext context = new CompilationUnitContext();
		runThread(path);
	}
	
	public void runThread(String path) {
		File file = new File(path);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File currentFile : files) {
				enterProcess(currentFile.getAbsolutePath());
			}
		} else {
			Thread thread = new Thread(new ParsingThread(file));
			thread.start();			
		}
	}
	
}
