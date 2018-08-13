package JunitTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class UniversalDataProvider {
	@DataProvider
	public static Object[][] DataUploader(FrameworkMethod testMethod) 
			
	{
		DataSource ds = testMethod.getAnnotation(DataSource.class);
		if(ds==null) throw new Error("Test method has no @DataSource annotation" + testMethod.getName());
		
		switch (ds.type()) {
			case FILE:
				return ReadFile(ds.value());
			case RESOURCE:
				return null;
			default:
				throw new Error("Unknown type "+ ds.type());
		}

	}
	public static Object[][] ReadFile(String fileName) {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				AppTest.class.getResourceAsStream(fileName)));
		
		List<Object[]> name = new ArrayList<Object[]>();
		String line="";
		try {
			line = in.readLine();
		
		while(line != null) 
		{
			name.add(line.split(";"));
			line=in.readLine();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return (Object[][]) name.toArray(new Object[][] {});
	}
	
}
