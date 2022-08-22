package guslgogo.example.output.accessor;

import java.util.Arrays;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AnnotationExpr;

import guslgogo.example.output.assistant.CompilationUnitAssistant;
import guslgogo.example.output.common.CompilationUnitContext;

public class DeclarationAccessor {
	
	public static void access(BodyDeclaration<?> dec) { 
		if(dec.isClassOrInterfaceDeclaration()) { // 클래스, 인터페이스 선언
			accessClassOrInterfaceDeclaration(dec.asClassOrInterfaceDeclaration());
		
		} else if(dec.isConstructorDeclaration()) { // 생성자 선언
			accessConstructorDeclaration(dec.asConstructorDeclaration());
		
		} else if(dec.isFieldDeclaration()) { // 필드 선언
			accessFieldDeclaration(dec.asFieldDeclaration());
		
		} else if(dec.isMethodDeclaration()) { // 메소드 선언
			accessMethodDeclaration(dec.asMethodDeclaration());
		
		} else if(dec.isRecordDeclaration()) { // 레코드 선언
			accessRecordDeclaration(dec.asRecordDeclaration());
		
		}
	}
	
	public static void accessClassOrInterfaceDeclaration(ClassOrInterfaceDeclaration dec) {
		dec.getMembers().forEach(member -> access(member));
	}
	
	public static void accessConstructorDeclaration(ConstructorDeclaration dec) {
		StatementAccessor.access(dec.getBody());
	}
	
	public static void accessFieldDeclaration(FieldDeclaration dec) {
		dec.getVariables().forEach(variable -> accessVariableDeclarator(variable));
	}
	
	public static void accessMethodDeclaration(MethodDeclaration dec) {
		dec.getBody().ifPresent(body -> StatementAccessor.access(body));
	}
	
	public static void accessRecordDeclaration(RecordDeclaration dec) {
		dec.getMembers().forEach(member -> access(member));
	}
	
	public static void accessVariableDeclarator(VariableDeclarator declarator) {
		declarator.getInitializer().ifPresent(initializer -> ExpressionAccessor.access(initializer));
		
		declarator.getParentNode().ifPresent(parent -> {
			if(parent instanceof FieldDeclaration) {
				FieldDeclaration field = (FieldDeclaration) parent;
				List<String> names = Arrays.asList("Resource", "javax.annotation.Resource", 
						"Autowired", "org.springframework.beans.factory.annotation.Autowired");
				for(AnnotationExpr annotation : field.getAnnotations()) {
					if(names.contains(annotation.getNameAsString())) {
						String type = CompilationUnitAssistant.findImportName(declarator.getTypeAsString(), field.findCompilationUnit().get());
						CompilationUnitContext.getLocal().addField(declarator.getNameAsString(), type);
					}
				}
			}
		});
	}
	
}
