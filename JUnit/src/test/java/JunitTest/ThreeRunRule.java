package JunitTest;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ThreeRunRule implements TestRule{
	
	  public ThreeRunRule() {
	  }

	 	@Override
	public Statement apply(Statement base, Description description) {
		return new ThreeRunStatment(base, description);
	}
	public class ThreeRunStatment extends Statement
	{
		private Description desc;
		private Statement statement;
		
		public ThreeRunStatment(Statement statement, Description desc) {
			this.statement = statement;
			this.desc = desc;
		}
		
		@Override
		public void evaluate() throws Throwable {
			if (desc.getAnnotation(Unstable.class) != null) { 
		    	int value=desc.getAnnotation(Unstable.class).value();
		    	
		    	for(int i=1;i<=value;i++) {
		    		System.out.println("Restarting");
		    			try {
		    				statement.evaluate();
		    			} catch (Throwable e) {
				
		    				e.printStackTrace();
		    			}
		    	}
		    }
		}
		
	}
}

