package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider
	public static Iterator<Object> loadNamesFromFile() 
			throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(
				DataProviders.class.getResourceAsStream("/names.data")));
		
		List<Object> name = new ArrayList<Object>();
		String line = in.readLine();
		while(line != null) 
		{
			name.add(line);
			line=in.readLine();
		}
		in.close();
		return name.iterator();
	}
}
