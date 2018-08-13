package JunitTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

public class Fixtures {
	
	public static Path paths;
	@ClassRule
	public static ExternalResource temp = new ExternalResource() {
		@Override
		protected void before() throws Throwable
		{
			try {
				paths = Files.createTempDirectory("temp");
						
				System.out.println("Temp directory is created in "+paths.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	
	@Override
	protected void after() 
	{
		for (File file: new File(paths.toString()).listFiles())  if (file.isFile()) file.delete();
		try {
			Files.deleteIfExists(paths);
			System.out.println("Temp directory is deleted");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}; 

}
