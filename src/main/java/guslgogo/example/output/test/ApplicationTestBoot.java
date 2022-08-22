//package guslgogo.example.output.test;
//
//import java.io.File;
//import java.util.Map;
//
//import com.github.javaparser.ast.NodeList;
//import com.github.javaparser.ast.body.ConstructorDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.body.TypeDeclaration;
//
//import guslgogo.example.output.accessor.DeclarationAccessor;
//import guslgogo.example.output.accessor.StatementAccessor;
//import guslgogo.example.output.assistant.JavaParserAssistant;
//import guslgogo.example.output.common.CompilationUnitContext;
//
//public class ApplicationTestBoot {
//	public static void main(String[] args) {
//		String path = "C:/InswaveTool/workspace/example-output-generator"
//						+ "/src/main/java/guslgogo/example/emp/web/EmpController.java";		
//		try {
//			File file = new File(path);
//			JavaParserAssistant japaAssistant = new JavaParserAssistant(file);
//			
//			CompilationUnitContext context = new CompilationUnitContext();
//			CompilationUnitContext.setLocal(context);
//			
//			japaAssistant.getTypeDeclarations().forEach(dec -> {
//				dec.getFields().forEach(field -> DeclarationAccessor.accessDeclaration(field));
//				dec.getMethods().forEach(method -> DeclarationAccessor.accessDeclaration(method));
//			});
//			
//			//System.out.println(DeclarationUnitContext.getLocal().getFieldMember());
//			
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
