package guslgogo.example.output.common;

public class CompilationUnitContext {
	
	private static final ThreadLocal<CompilationUnitContext> local = new ThreadLocal();
	
	public CompilationUnitContext getLocal() {
		return this.local.get();
	}
	
	public void setLocal(CompilationUnitContext context) {
		this.local.set(context);
	}
	
}
