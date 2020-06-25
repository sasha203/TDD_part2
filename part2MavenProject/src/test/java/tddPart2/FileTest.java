package tddPart2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tddPart2.FileTask;
import tddPart2.StubFile;
import tddPart2.Task;

public class FileTest {

	// need to test (not machine dependent.)
	@Test
	public void available_file_and_location_normal_result() {
		FileTask t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("src/test/resources");
		fileDetails.add("test.png");
		assertTrue(t.configure(fileDetails));  
		assertTrue(t.setFileSize());
		System.out.print("File Size in Bytes: " + t.getSize() + "\n");  
	}
	

	
	@Test
	public void passing_null_to_config() {
		Task t = new FileTask();
		assertFalse(t.configure(null));  
	}
	
	
	
	@Test
	public void passing_only_one_item_to_config() {
		FileTask t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("src/test/resources");
		assertFalse(t.configure(fileDetails));		
	}
	
	

	
	@Test
	public void passing_empty_values_to_config() {
		Task t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("");
		fileDetails.add("");
		assertFalse(t.configure(fileDetails));  
	}
	
	@Test
	public void passing_null_and_empty_values_to_config() {
		Task t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add(null);
		fileDetails.add("");
		assertFalse(t.configure(fileDetails));  
	}
	
	

	
	@Test
	public void file_not_found() {
		FileTask t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("src/test/resources");
		fileDetails.add("notFound.png");
		t.configure(fileDetails);
		assertEquals("src\\test\\resources\\notFound.png (The system cannot find the file specified)", t.run());
		System.out.print(t.showErrorMsg() + "\n");  
	}

	
	
	@Test
	public void convertion_from_bytes_to_Kb() {
		FileTask t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("src/test/resources");
		fileDetails.add("test.png");
		t.configure(fileDetails);  
		t.run();
		System.out.print("Size in KB - no d.p formatting: " + t.convFromBytesToKb() + "\n");  
		assertEquals(7.37, t.convFromBytesToKb(), 1);		
	}
	

	// Showing in KB with 2 d.p (string format)
	@Test
	public void File_Driver() {
		FileTask t = new FileTask();
		List<String> fileDetails = new ArrayList<String>();
		fileDetails.add("src/test/resources");
		fileDetails.add("test.png");
		
		if(t.configure(fileDetails)){
			assertEquals("FileSize: 7.37", t.run());
			System.out.println(t.run());
		}  
	}
	
	@Test
	public void file_stub() {
		StubFile sf = new StubFile();
		List<String> fileDetails = new ArrayList<String>();
		if(sf.configure(fileDetails)){
			assertEquals("FileSize: 7.37", sf.run());
			System.out.println(sf.run());
		}  
		
	}
	

	
	
	

}
