package guslgogo.example.output.accessor;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class ExpressionAccessor {
	
	public static void accessExpression(Expression expr) {
		if(expr.isMethodCallExpr()) {
			accessMethodCallExpr(expr.asMethodCallExpr());
		}
	}
	
	public static void accessMethodCallExpr(MethodCallExpr expr) {
		
	}
	
}
