package guslgogo.example.output.test;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import guslgogo.example.output.accessor.DeclarationAccessor;
import guslgogo.example.output.accessor.StatementAccessor;
import guslgogo.example.output.assistant.JavaParserAssistant;

public class ApplicationTestBoot {
	public static void main(String[] args) {
		String path = "C:\\Applications\\Workspace\\eclipse-workspace\\example-output-generator\\"
						+ "src\\main\\java\\guslgogo\\example\\output\\test\\BasicController.java";		
		try {
			JavaParserAssistant japaAssistant = new JavaParserAssistant(path);
			//System.out.println(japaAssistant.toString());
			japaAssistant.getTypeDeclarations().forEach(dec -> DeclarationAccessor.accessDeclaration(dec));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
