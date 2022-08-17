package guslgogo.example.output.accessor;

import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.LabeledStmt;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.stmt.LocalRecordDeclarationStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.SynchronizedStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.UnparsableStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.YieldStmt;

public class StatementAccessor {
	
	public static void accessStatement(Statement stmt) {
		if(stmt.isAssertStmt()) {
			accessAssertStmt(stmt.asAssertStmt());
			
		} else if(stmt.isBlockStmt()) {
			accessBlockStmt(stmt.asBlockStmt());
			
		} else if(stmt.isDoStmt()) {	
			accessDoStmt(stmt.asDoStmt());
			
		} else if(stmt.isExplicitConstructorInvocationStmt()) {
			accessExplicitConstructorInvocationStmt(stmt.asExplicitConstructorInvocationStmt());
			
		} else if(stmt.isExpressionStmt()) {
			accessExpressionStmt(stmt.asExpressionStmt());
			
		} else if(stmt.isForEachStmt()) {
			accessForEachStmt(stmt.asForEachStmt());
			
		} else if(stmt.isForStmt()) {
			accessForStmt(stmt.asForStmt());
			
		} else if(stmt.isIfStmt()) {
			accessIfStmt(stmt.asIfStmt());
			
		} else if(stmt.isLabeledStmt()) {
			accessLabeledStmt(stmt.asLabeledStmt());
			
		} else if(stmt.isLocalClassDeclarationStmt()) {
			accessLocalClassDeclarationStmt(stmt.asLocalClassDeclarationStmt());
			
		} else if(stmt.isLocalRecordDeclarationStmt()) {
			accessLocalRecordDeclarationStmt(stmt.asLocalRecordDeclarationStmt());
			
		} else if(stmt.isReturnStmt()) {
			accessReturnStmt(stmt.asReturnStmt());
			
		} else if(stmt.isSwitchStmt()) {
			accessSwitchStmt(stmt.asSwitchStmt());
			
		} else if(stmt.isSynchronizedStmt()) {
			accessSynchronizedStmt(stmt.asSynchronizedStmt());
			
		} else if(stmt.isThrowStmt()) {
			accessThrowStmt(stmt.asThrowStmt());
			
		} else if(stmt.isTryStmt()) {
			accessTryStmt(stmt.asTryStmt());
			
		} else if(stmt.isWhileStmt()) {
			accessWhileStmt(stmt.asWhileStmt());
			
		} else if(stmt.isYieldStmt()) {
			accessYieldStmt(stmt.asYieldStmt());
			
		}
	}
	
	public static void accessAssertStmt(AssertStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getCheck());
		stmt.getMessage().ifPresent(message -> ExpressionAccessor.accessExpression(message));
	}
	
	public static void accessBlockStmt(BlockStmt stmt) {
		stmt.getStatements().forEach(statement -> accessStatement(statement));
	}
	
	public static void accessCatchClause(CatchClause clause) {
		accessStatement(clause.getBody());
	}
	
	public static void accessDoStmt(DoStmt stmt) {
		accessStatement(stmt.getBody());
		ExpressionAccessor.accessExpression(stmt.getCondition());
	}
	
	public static void accessExplicitConstructorInvocationStmt(ExplicitConstructorInvocationStmt stmt) {
		stmt.getArguments().forEach(argument -> ExpressionAccessor.accessExpression(argument));
		stmt.getExpression().ifPresent(expression -> ExpressionAccessor.accessExpression(expression));
	}
	
	public static void accessExpressionStmt(ExpressionStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getExpression());
	}
	
	public static void accessForEachStmt(ForEachStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getVariable());
		ExpressionAccessor.accessExpression(stmt.getIterable());
		accessStatement(stmt.getBody());
	}
	
	public static void accessForStmt(ForStmt stmt) {
		stmt.getInitialization().forEach(initialization -> ExpressionAccessor.accessExpression(initialization));
		stmt.getCompare().ifPresent(compare -> ExpressionAccessor.accessExpression(compare));
		stmt.getUpdate().forEach(update -> ExpressionAccessor.accessExpression(update));
		accessStatement(stmt.getBody());
	}
	
	public static void accessIfStmt(IfStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getCondition());
		accessStatement(stmt.getThenStmt());
		stmt.getElseStmt().ifPresent(elseStmt -> accessStatement(elseStmt));
	}
	
	public static void accessLabeledStmt(LabeledStmt stmt) {
		accessStatement(stmt.getStatement());
	}
	
	public static void accessLocalClassDeclarationStmt(LocalClassDeclarationStmt stmt) {
		DeclarationAccessor.accessDeclaration(stmt.getClassDeclaration());
	}
	
	public static void accessLocalRecordDeclarationStmt(LocalRecordDeclarationStmt stmt) {
		DeclarationAccessor.accessDeclaration(stmt.getRecordDeclaration());
	}
	
	public static void accessReturnStmt(ReturnStmt stmt) {
		stmt.getExpression().ifPresent(expression -> ExpressionAccessor.accessExpression(expression));
	}
	
	public static void accessSwitchEnrty(SwitchEntry entry) {
		entry.getLabels().forEach(label -> ExpressionAccessor.accessExpression(label));
		entry.getStatements().forEach(statement -> accessStatement(statement));
	}
	
	public static void accessSwitchStmt(SwitchStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getSelector());
		stmt.getEntries().forEach(entry -> accessSwitchEnrty(entry));
	}
	
	public static void accessSynchronizedStmt(SynchronizedStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getExpression());
		accessStatement(stmt.getBody());
	}
	
	public static void accessThrowStmt(ThrowStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getExpression());
	}
	
	public static void accessTryStmt(TryStmt stmt) {
		accessStatement(stmt.getTryBlock());
		stmt.getResources().forEach(resource -> ExpressionAccessor.accessExpression(resource));
		stmt.getCatchClauses().forEach(clause -> accessCatchClause(clause));
		stmt.getFinallyBlock().ifPresent(finallyBlock -> accessStatement(finallyBlock));		
	}
	
	public static void accessWhileStmt(WhileStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getCondition());
		accessStatement(stmt.getBody());
	}
	
	public static void accessYieldStmt(YieldStmt stmt) {
		ExpressionAccessor.accessExpression(stmt.getExpression());
	}
	
}
