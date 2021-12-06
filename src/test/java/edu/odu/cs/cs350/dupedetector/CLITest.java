package edu.odu.cs.cs350.dupedetector;

//Importing JUNIT
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class CLITest {
	
	//Testing functions from CLI.java	
    @Test
     void TestCLI(){
    	//Test CLI constructor parseArgs
    	CLI passArg = new CLI();
    	String[] args = new String[]{"3", ".ini", "hello-world.cpp", "hello2-world.cpp"};
    	String[] args_2 = new String[]{"2", ".h", "hello-world.java", "hello2-world.cpp"};
    	
    	String filepath1 = "hello-world.cpp";
    	String filepath2 = "hello2-world.cpp";
    	String properties = ".ini";
    	String numSuggest = "3";
    	
    	
    	assertEquals(args[0], numSuggest);
    	assertEquals(args[1], properties);
		assertEquals(args[2], filepath1);
		assertEquals(args[3], filepath2);
		
		assertNotEquals("Invalid Argument", args[0], properties);
    	assertNotEquals("Invalid Argument",args[1], numSuggest);
		assertNotEquals("Invalid Argument",args[2], filepath2);
		assertNotEquals("Invalid Argument", args[3], filepath1);
		
		assertNotEquals("Invalid Argument", args_2[0], numSuggest);
    	assertNotEquals("Invalid Argument",args_2[1], properties);
		assertNotEquals("Invalid Argument",args_2[2], filepath1);
		assertNotEquals("Invalid Argument", args_2[3], filepath2);
		
    	
    }
        
        
    	
    	
    
}
