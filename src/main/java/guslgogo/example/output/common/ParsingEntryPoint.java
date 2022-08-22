package guslgogo.example.output.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import guslgogo.example.output.assistant.JavaParserAssistant;

public class ParsingEntryPoint {
	
	private List<String> pathList;
	
	public ParsingEntryPoint(String path) {
		this.pathList = new ArrayList<>();
		addAbsolutePath(path);
	}

	private void addAbsolutePath(String path) {
		File file = new File(path);
		File[] filelist = file.listFiles();
		if(ArrayUtils.isNotEmpty(filelist)) {
			for(File current : filelist) {
				String absolutePath = current.getAbsolutePath();
				if(current.isDirectory()) {
					addAbsolutePath(absolutePath);
				} else {
					if(absolutePath.endsWith("java")) {
						this.pathList.add(absolutePath);						
					}
				}
			}
		}
	}
	
	public void doProcess() throws Exception {
		for(String path : pathList) {
			CompilationUnitContext context = new CompilationUnitContext();
			CompilationUnitContext.setLocal(context);
			System.out.println("*** current path : " + path);
			JavaParserAssistant japaAssistant = new JavaParserAssistant(path);
		}
	}
	
}
