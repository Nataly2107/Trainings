package JunitTest;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import JunitTest.DataSource.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@RunWith(DataProviderRunner.class)
public class AppTest implements MyCategories
{
	
	private boolean CreateFile(String path_s,String nameFile)  {
		boolean h=false;
		
		
		try {
			File file = new File(path_s,nameFile);
			h=file.createNewFile();
			System.out.println(file.getAbsolutePath());
		}catch (Exception e) {
			System.out.println("We have exception!");
		}
		return h;
	}
	
	private static String GenerateFileName() {
		return ("FileTest_gen_"+new Random().nextInt());		
	}
	private boolean ValidateFileExist(String name)
	{
		for (File file: new File(tmp.getRoot().toString()).listFiles()) { 
			if (file.getName().equals(name))
				{
					System.out.println("Names are the same");
					return true;
					
				}
		}
		System.out.println("Return false");
		return false;
		
	}
	
	@DataProvider
	public static Object[][] name() {
		List<String[]> names = new ArrayList<String[]>();
		for(int i=0;i<10;i++) 
		{
			names.add(GenerateFileName().split(";"));
		}
		return (Object[][]) names.toArray(new Object[][] {});
	}
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();
	
	@Test
    @Category({PositiveTests.class})
    @UseDataProvider(value="DataUploader", location = UniversalDataProvider.class)
	@DataSource(value="/names.data", type=Type.FILE)
    public void FirstTest(String name) 
    {
    	String temp = tmp.getRoot().toString()+"/";
    	//String path_s="/"+paths.toString()+"/";
        System.out.println("Test 1 is running!");
        System.out.println("File is create? ");
        System.out.println(temp);
        assertTrue(CreateFile(temp, name));
        assertTrue(ValidateFileExist(name));
        
        
    }
    @Test
    @Category({PositiveTests.class})
    @UseDataProvider(value="DataUploader", location = UniversalDataProvider.class)
    @DataSource(value="/names.data", type=Type.FILE)
    public void SecondTest(String name)
    {
    	String temp = tmp.getRoot().toString()+"/";
    	//String path_s="/"+paths.toString()+"/";
        System.out.println("Test 2 is running!");
        System.out.println("File is create? ");
        CreateFile(temp,name);
        Assert.assertTrue(ValidateFileExist(name));
    }
    @Test
    @Category({PositiveTests.class})
    @UseDataProvider(value="DataUploader", location = UniversalDataProvider.class)
    @DataSource(value="/names.data", type=Type.FILE)
    public void ThirdTest(String name)
    {
    	String temp = tmp.getRoot().toString()+"/";
    	//String path_s="/"+paths.toString()+"/";
        System.out.println("Test 3 is running!");
        System.out.println("File is create? ");
        Assert.assertTrue(CreateFile(temp,name));
    }
    
    @Test
    @Category({NegativeTests.class})
    @UseDataProvider(value="DataUploader", location = UniversalDataProvider.class)
    @DataSource(value="/names.data", type=Type.FILE)
    public void BrokenTest1(String name)
    {
    	String path_incorrect="/home/natasha/null folder";
        System.out.println("Broken Test 1 is running!");
        System.out.println("File is create? ");
        Assert.assertFalse(CreateFile(path_incorrect,name));
    }
    @Test
    @Category({NegativeTests.class})
    @UseDataProvider(value="DataUploader", location = UniversalDataProvider.class)
    @DataSource(value="/names.data", type=Type.FILE)
    public void BrokenTest2(String name)
    {
    	String temp = tmp.getRoot().toString()+"/";
    	//String path_s="/"+paths.toString()+"/";
        System.out.println("Broken Test 2 is running!");
        System.out.println("File is create? ");
        CreateFile(temp,name);
        Assert.assertFalse(CreateFile(temp,name));
    }
    

	@Rule
	public ThreeRunRule myRule=new ThreeRunRule(); 
	
   private static int i=1;
   @Test
   @Unstable(3)
   public void UnstableTest()
   {
	if(i==3) 
	{
		i=1;
	}else 
	{
		Assert.fail("Failed on" +(i++) + " attempt");
	}
	
   }
}
