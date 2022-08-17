package guslgogo.example.output.accessor;

import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

public class DeclarationAccessor {
	
	public static void accessDeclaration(BodyDeclaration<?> dec) {
		if(dec.isClassOrInterfaceDeclaration()) {
			accessClassOrInterfaceDeclaration(dec.asClassOrInterfaceDeclaration());
		
		} else if(dec.isConstructorDeclaration()) {
			accessConstructorDeclaration(dec.asConstructorDeclaration());
		
		} else if(dec.isFieldDeclaration()) {
			accessFieldDeclaration(dec.asFieldDeclaration());
		
		} else if(dec.isMethodDeclaration()) {
			accessMethodDeclaration(dec.asMethodDeclaration());
		
		} else if(dec.isRecordDeclaration()) {
			accessRecordDeclaration(dec.asRecordDeclaration());
		
		}
	}
	
	public static void accessClassOrInterfaceDeclaration(ClassOrInterfaceDeclaration dec) {
		dec.getMembers().forEach(member -> accessDeclaration(member));
	}
	
	public static void accessConstructorDeclaration(ConstructorDeclaration dec) {
		StatementAccessor.accessStatement(dec.getBody());
	}
	
	public static void accessFieldDeclaration(FieldDeclaration dec) {
		dec.getVariables().forEach(variable -> accessVariableDeclarator(variable));
	}
	
	public static void accessMethodDeclaration(MethodDeclaration dec) {
		dec.getBody().ifPresent(body -> StatementAccessor.accessStatement(body));
	}
	
	public static void accessRecordDeclaration(RecordDeclaration dec) {
		dec.getMembers().forEach(member -> accessDeclaration(member));
	}
	
	public static void accessVariableDeclarator(VariableDeclarator declarator) {
		declarator.getInitializer().ifPresent(initializer -> ExpressionAccessor.accessExpression(initializer));
	}
	
}
