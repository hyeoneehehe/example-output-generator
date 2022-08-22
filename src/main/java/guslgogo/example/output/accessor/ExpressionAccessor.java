package guslgogo.example.output.accessor;

import java.util.Map;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import guslgogo.example.output.common.CompilationUnitContext;
import sun.net.www.content.image.gif;

public class ExpressionAccessor {
	
	public static void access(Expression expr) {
		if(expr.isMethodCallExpr()) {
			accessMethodCallExpr(expr.asMethodCallExpr());
		}
	}
	
	public static void accessMethodCallExpr(MethodCallExpr expr) {		
		expr.getScope().ifPresent(scope -> {
			if(!scope.isNameExpr()) {
				access(scope);
			} else {
				Map<String, String> member = CompilationUnitContext.getLocal().getFieldMember();
				for(String key : member.keySet()) {
					if(key.equals(scope.asNameExpr().getNameAsString())) {
						
					}
				}
			}
		});
	}
	
}
