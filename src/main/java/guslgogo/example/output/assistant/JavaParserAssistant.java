package guslgogo.example.output.assistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class JavaParserAssistant {
	
	private CompilationUnit compUnit;
	
	public JavaParserAssistant(String path) throws Exception {
		JavaParser japa = new JavaParser();
		try(InputStream is = new FileInputStream(path)) {
			ParseResult<CompilationUnit> result = japa.parse(is);
			if(result.isSuccessful()) {
				this.compUnit = result.getResult().get();
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	public ClassOrInterfaceDeclaration getFirstClassOrInterfaceDeclaration() {
		return (ClassOrInterfaceDeclaration) compUnit.getType(0);
	}
	
	@Override
	public String toString() {
		return compUnit.toString();
	} 
	
}
