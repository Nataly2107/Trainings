package tests;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.lang.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AppTest 
{
	Path paths;

	private boolean CreateFile(String nameFile)  {
		boolean h;
		String s="/"+paths.toString()+"/";
		
		try {
			File file = new File(s,nameFile);
			h=file.createNewFile();
			System.out.println(file.getAbsolutePath());
		}catch (Exception e) {
			h=false;
			System.out.println("We have exception!");
		}
		return h;
	}
	private Object GenerateFileName() {
		return "FileTest"+new Random().nextInt();		
	}
	@DataProvider
	public Iterator<Object> fileName() {
		List<Object> names = new ArrayList<Object>();
		for(int i=0;i<10;i++) 
		{
			names.add(GenerateFileName());
		}
		return names.iterator();
	}
	
	@BeforeTest(alwaysRun=true)
	public void CreateTemp() 
	{
		try {
			paths = Files.createTempDirectory("temp");
					
			System.out.println("Temp directory is created in "+paths.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@AfterTest(alwaysRun=true)
	public void DeleteTemp() 
	{
		for (File file: new File(paths.toString()).listFiles())  if (file.isFile()) file.delete();
		try {
			Files.deleteIfExists(paths);
			System.out.println("Temp directory is deleted");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Test(groups = "positive", dataProvider="fileName")
    public void FirstTest(String name) 
    {
        System.out.println("Test 1 is running!");
        System.out.println("File is create? ");
        System.out.println(CreateFile(name));
        
        
    }
    @Test(groups = "positive", dataProvider="fileName")
    public void SecondTest(String name)
    {
        System.out.println("Test 2 is running!");
        System.out.println("File is create? ");
        assertTrue(CreateFile(name));
    }
    @Test(groups = "positive", dataProviderClass = DataProviders.class, dataProvider="loadNamesFromFile")
    public void ThirdTest(String name)
    {
        System.out.println("Test 3 is running!");
        System.out.println("File is create? ");
        AssertJUnit.assertTrue(CreateFile(name));
    }
    
    @Test(groups = "negative", dataProvider="fileName")
    public void BrokenTest1(String name)
    {
        System.out.println("Broken Test is running!");
        System.out.println("File is create? ");
        Assert.assertFalse(CreateFile(null));
    }
    
}
