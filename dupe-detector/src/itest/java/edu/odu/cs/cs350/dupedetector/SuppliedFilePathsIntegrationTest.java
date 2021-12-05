package edu.odu.cs.cs350.dupedetector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SuppliedFilePathsIntegrationTest {

	ArrayList<String> userFileInput = new ArrayList<String>(
			Arrays.asList("src/itest/data/itest-project1"));
	ArrayList<String> userFileInput2 = new ArrayList<String>(
			Arrays.asList("src/itest/data/itest-project1","src/itest/data/itest-project1"));
	
	/**
	 * Tests how properties file integrates with supplied file paths
	 */
	@Test
	void testPropertiesFileWithSuppliedFilePaths() {
		
		PropertiesFile defaultProps = new PropertiesFile();
		
		ArrayList<String> expectedExtensions = new ArrayList<String>(Arrays.asList("h","cpp"));
		
		assertThat(defaultProps.getCppExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
		assertThat(defaultProps.getMaxSubstitutions(), is(8));
		assertThat(defaultProps.getMinSequenceLength(), is(10));
		
		SuppliedFilePaths project = new SuppliedFilePaths();
		project.setEligibleExtensions(defaultProps.getCppExtensions());
		
		assertThat(project.getEligibleExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
		
		ArrayList<SourceCodeFile> projectFiles = project.findEligibleSourceCode(userFileInput);
		
		assertThat(projectFiles.size(), is(5));
		assertThat(projectFiles.get(0).getPath().toString(),
				containsString("hello-world.cpp"));
		assertThat(projectFiles.get(1).getPath().toString(),
				containsString("invalid.cpp"));
		assertThat(projectFiles.get(2).getPath().toString(),
				containsString("hello-world.cpp"));
		assertThat(projectFiles.get(3).getPath().toString(),
				containsString("hello-world.h"));
		assertThat(projectFiles.get(4).getPath().toString(),
				containsString("hello-world1.cpp"));
		
		projectFiles = project.findEligibleSourceCode(userFileInput2);
		
		assertThat(projectFiles.size(), is(5));
		assertThat(projectFiles.get(0).getPath().toString(),
				containsString("hello-world.cpp"));
		assertThat(projectFiles.get(1).getPath().toString(),
				containsString("invalid.cpp"));
		assertThat(projectFiles.get(2).getPath().toString(),
				containsString("hello-world.cpp"));
		assertThat(projectFiles.get(3).getPath().toString(),
				containsString("hello-world.h"));
		assertThat(projectFiles.get(4).getPath().toString(),
				containsString("hello-world1.cpp"));
		
		try {
			PropertiesFile normalProps = new PropertiesFile("src/itest/data/propertyFile/expectedProperty.ini");	
			expectedExtensions = new ArrayList<String>(Arrays.asList("h","cpp","H","hpp"));
			
			assertThat(normalProps.getCppExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
			assertThat(normalProps.getMaxSubstitutions(), is(5));
			assertThat(normalProps.getMinSequenceLength(), is(20));
			
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(normalProps.getCppExtensions());
			
			assertThat(project.getEligibleExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
			
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
			
			assertThat(projectFiles.size(), is(6));
			assertThat(projectFiles.get(0).getPath().toString(),
					containsString("hello-world.cpp"));
			assertThat(projectFiles.get(1).getPath().toString(),
					containsString("invalid.cpp"));
			assertThat(projectFiles.get(2).getPath().toString(),
					containsString("hello-world.cpp"));
			assertThat(projectFiles.get(3).getPath().toString(),
					containsString("hello-world.h"));
			assertThat(projectFiles.get(4).getPath().toString(),
					containsString("hello-world1.cpp"));
			assertThat(projectFiles.get(5).getPath().toString(),
					containsString("moreTestFiles.hpp"));
			
			projectFiles = project.findEligibleSourceCode(userFileInput2);
			
			assertThat(projectFiles.size(), is(6));
			assertThat(projectFiles.get(0).getPath().toString(),
					containsString("hello-world.cpp"));
			assertThat(projectFiles.get(1).getPath().toString(),
					containsString("invalid.cpp"));
			assertThat(projectFiles.get(2).getPath().toString(),
					containsString("hello-world.cpp"));
			assertThat(projectFiles.get(3).getPath().toString(),
					containsString("hello-world.h"));
			assertThat(projectFiles.get(5).getPath().toString(),
					containsString("moreTestFiles.hpp"));
		}
		catch(Exception e) {
			fail("Error was not expected: " + e);
		}
		
		try {
			PropertiesFile diffProps = new PropertiesFile("src/itest/data/propertyFile/differentExtensions.ini");	
			expectedExtensions = new ArrayList<String>(Arrays.asList("txt","java","data"));
			
			assertThat(diffProps.getCppExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
			assertThat(diffProps.getMaxSubstitutions(), is(8));
			assertThat(diffProps.getMinSequenceLength(), is(10));
			
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(diffProps.getCppExtensions());
			
			assertThat(project.getEligibleExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
			
			projectFiles = project.findEligibleSourceCode(userFileInput);
				
			assertThat(projectFiles.size(), is(3));
			assertThat(projectFiles.get(0).getFilePath().toString(),
					containsString("empty.txt"));
			assertThat(projectFiles.get(1).getFilePath().toString(),
					containsString("Information.data"));
			assertThat(projectFiles.get(2).getFilePath().toString(),
					containsString("oopsJava.java"));
			
			projectFiles = project.findEligibleSourceCode(userFileInput2);
			
			assertThat(projectFiles.size(), is(3));
			assertThat(projectFiles.get(0).getFilePath().toString(),
					containsString("empty.txt"));
			assertThat(projectFiles.get(1).getFilePath().toString(),
					containsString("Information.data"));
			assertThat(projectFiles.get(2).getFilePath().toString(),
					containsString("oopsJava.java"));
		}
		catch(Exception e) {
			fail("Error was not expected: " + e);
		}
	}

}
