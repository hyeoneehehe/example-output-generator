package guslgogo.example.output.common;

import java.util.HashMap;
import java.util.Map;

public class CompilationUnitContext {
	
	private static ThreadLocal<CompilationUnitContext> local = new ThreadLocal<>();
	
	private Map<String, String> fieldMember = new HashMap<>();
	private Map<String, String> callRelation = new HashMap<>();
	
	public static CompilationUnitContext getLocal() {
		return local.get();
	}
	
	public static void setLocal(CompilationUnitContext context) {
		local.set(context);
	}
	
	public static void clear() {
		if(local != null) {
			local.remove();
		}
	}
	
	public Map<String, String> getFieldMember() {
		return fieldMember;
	}
	
	public void addField(String name, String type) {
		fieldMember.put(name, type);
	}
	
}
