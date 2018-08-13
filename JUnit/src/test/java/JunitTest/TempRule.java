package JunitTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TempRule {
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();
	@Test
	public void test() 
	{
		System.out.println(tmp.getRoot());
	}
	
}
