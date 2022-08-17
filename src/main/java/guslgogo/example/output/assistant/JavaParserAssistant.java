package guslgogo.example.output.assistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.TypeDeclaration;

public class JavaParserAssistant {
	
	private CompilationUnit compUnit;
	
	public JavaParserAssistant(File file) throws Exception {
		JavaParser japa = new JavaParser();
		try(InputStream is = new FileInputStream(file)) {
			ParseResult<CompilationUnit> parseResult = japa.parse(is);
			if(parseResult.isSuccessful() && parseResult.getProblems().size() == 0) {
				this.compUnit = parseResult.getResult().get();
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	public NodeList<TypeDeclaration<?>> getTypeDeclarations() {
		return this.compUnit.getTypes();
	}
	
	@Override
	public String toString() {
		return compUnit.toString();
	} 
	
}
