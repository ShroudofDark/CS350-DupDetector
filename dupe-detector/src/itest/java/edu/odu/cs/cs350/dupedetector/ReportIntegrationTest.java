package edu.odu.cs.cs350.dupedetector;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class ReportIntegrationTest {

	ArrayList<String> userFileInput = new ArrayList<String>(
			Arrays.asList("src/itest/data/itest-project1"));
	
	//Still stubbed data
	String sequence1 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n";
	String sequence2 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:4\r\n"
			+ "a b 2\r\n";
	String sequence3 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:099:0\r\n"
			+ "m n\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:123:4\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:080:8\r\n"
			+ "a b\r\n";
	String sequence4 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:0120:0\r\n"
			+ "m n o p q l s\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:125:4\r\n"
			+ "x y f g h n r\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:086:8\r\n"
			+ "a b p q t l s\r\n";
	String sequence5 = 
			  "/home/zeil/projects/cppProject1/src/alphabet.cpp:200:0\r\n"
			+ "a b c d e f g h i j\r\n"
			+ "/home/zeil/projects/cppProject1/src/grung.cpp:122:2\r\n"
			+ "\"blah\" z 3 4 5 6 7 8 9 10\r\n";
	String sequence6 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "l \"number\"\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/oops.cpp:156:4\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/oops.cpp:252:1\r\n"
			+ "x t\r\n";
	String sequence7 = 
			  "/home/zeil/projects/cppProject1/src/alphabet.cpp:200:0\r\n"
			+ "a b c\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/grung.cpp:122:2\r\n"
			+ "\"blah\" z r\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/zoo.h:015:2\r\n"
			+ "v \"elaphant\" t\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:211:2\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:317:3\r\n"
			+ "y a y\r\n";
	
	//Still stubbed data for report as this class is not integrated
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, sequence1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, sequence2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, sequence3);
	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, sequence4);
	SuggestedRefactoring refactoring5 = new SuggestedRefactoring(9, 27, 3, sequence5);
	SuggestedRefactoring refactoring6 = new SuggestedRefactoring(16, 16, 3, sequence6);
	SuggestedRefactoring refactoring7 = new SuggestedRefactoring(18, 64, 10, sequence7);
	
	@Test
	void testSuppliedFilePathsWithReport() {
		
		ArrayList<String> eligibleExtensions = new ArrayList<String>(Arrays.asList("h","cpp"));
		
		SuppliedFilePaths project = new SuppliedFilePaths();
		project.setEligibleExtensions(eligibleExtensions);		
		ArrayList<SourceCodeFile> projectFiles = project.findEligibleSourceCode(userFileInput);
		
		//Only Stubbed because this is not a major test factor in this test/class still not developed
		ArrayList<SuggestedRefactoring> stubbedRefList
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
					refactoring5, refactoring6, refactoring7));
		
		Report newReport = new Report(projectFiles, stubbedRefList);
		
		assertThat(newReport.totalSourceCodeFiles(), is(projectFiles.size()));
		assertThat(newReport.getSourceCodeFile(0), is(projectFiles.get(0)));
		assertThat(newReport.getSourceCodeFile(1), is(projectFiles.get(1)));
		assertThat(newReport.getSourceCodeFile(2), is(projectFiles.get(2)));
		assertThat(newReport.getSourceCodeFile(3), is(projectFiles.get(3)));
		
		assertThat(newReport.getSourceCodeFile(0).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(1).getFilePath().toString(),
				containsString("invalid.cpp"));
		assertThat(newReport.getSourceCodeFile(2).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(3).getFilePath().toString(),
				containsString("hello-world.h"));
		assertThat(newReport.getSourceCodeFile(4).getFilePath().toString(),
				containsString("hello-world1.cpp"));
		
		assertThat(newReport.totalRefactorings(), is(stubbedRefList.size()));
		assertThat(newReport.getRefactoring(0), is(stubbedRefList.get(0)));
		assertThat(newReport.getRefactoring(1), is(stubbedRefList.get(1)));
		assertThat(newReport.getRefactoring(2), is(stubbedRefList.get(2)));
		assertThat(newReport.getRefactoring(3), is(stubbedRefList.get(3)));
		
		newReport.trimRefactorings(8, 10);
		newReport.sortRefactorings();
		
		assertThat(newReport.totalRefactorings(), not(is(stubbedRefList.size())));
		
		eligibleExtensions = new ArrayList<String>(Arrays.asList("h","cpp","H","hpp"));
		
		project = new SuppliedFilePaths();
		project.setEligibleExtensions(eligibleExtensions);
		projectFiles = project.findEligibleSourceCode(userFileInput);
		
		newReport = new Report(projectFiles, stubbedRefList);
		
		assertThat(newReport.totalSourceCodeFiles(), is(projectFiles.size()));
		assertThat(newReport.getSourceCodeFile(0), is(projectFiles.get(0)));
		assertThat(newReport.getSourceCodeFile(1), is(projectFiles.get(1)));
		assertThat(newReport.getSourceCodeFile(2), is(projectFiles.get(2)));
		assertThat(newReport.getSourceCodeFile(3), is(projectFiles.get(3)));
		
		assertThat(newReport.getSourceCodeFile(0).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(1).getFilePath().toString(),
				containsString("invalid.cpp"));
		assertThat(newReport.getSourceCodeFile(2).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(3).getFilePath().toString(),
				containsString("hello-world.h"));
		assertThat(newReport.getSourceCodeFile(4).getFilePath().toString(),
				containsString("hello-world1.cpp"));
		assertThat(newReport.getSourceCodeFile(5).getFilePath().toString(),
				containsString("moreTestFiles.hpp"));
		
		assertThat(newReport.totalRefactorings(), is(stubbedRefList.size()));
		assertThat(newReport.getRefactoring(0), is(stubbedRefList.get(0)));
		assertThat(newReport.getRefactoring(1), is(stubbedRefList.get(1)));
		assertThat(newReport.getRefactoring(2), is(stubbedRefList.get(2)));
		assertThat(newReport.getRefactoring(3), is(stubbedRefList.get(3)));
	}
	
	/**
	 * Not entirely convinced this right here is exactly an integration test, as its very
	 * similar to what I did in the UnitTest. Though its possible what I did in the
	 * unit test is an integration test and needs less dependancy.
	 */
	@Test
	void testSourceCodeFilesWithReport() {
		String filePath1 = "/home/zeil/projects/cppProject1/src/foo.cpp";
		String filePath2 = "/home/zeil/projects/cppProject1/src/headers/bar.h";
		String filePath3 = "H:\\cygwin\\home\\Jacob\\someCpp.cpp";
		String filePath4 = "/home/fakeZeil/projects/cppProject1/src/foo.cpp";
		String filePath5 = "/coolhome/fakeZeil/cppProject1/src/tool.cpp";
		
		SourceCodeFile file1 = new SourceCodeFile(filePath1);
		SourceCodeFile file2 = new SourceCodeFile(filePath2);
		SourceCodeFile file3 = new SourceCodeFile(filePath3);
		SourceCodeFile file4 = new SourceCodeFile(filePath4);
		SourceCodeFile file5 = new SourceCodeFile(filePath5);
		
		ArrayList<SourceCodeFile> sourceFileList1 = 
				new ArrayList<SourceCodeFile>(Arrays.asList(file1, file2, file3));
		ArrayList<SourceCodeFile> sourceFileList2 =
				new ArrayList<SourceCodeFile>(Arrays.asList(file1, file2, file3, file4, file5));
		
		//Only Stubbed because this is not a major test factor in this test/class still not developed
		ArrayList<SuggestedRefactoring> stubbedRefList
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
					refactoring5, refactoring6, refactoring7));
		
		Report newReport = new Report(sourceFileList1, stubbedRefList);
		
		assertThat(newReport.totalSourceCodeFiles(), is(sourceFileList1.size()));
		assertThat(newReport.getSourceCodeFile(0), is(file1));
		assertThat(newReport.getSourceCodeFile(1), is(file2));
		assertThat(newReport.getSourceCodeFile(2), is(file3));
		
		newReport.sortSourceFiles();
		
		assertThat(newReport.totalSourceCodeFiles(), is(sourceFileList1.size()));
		assertThat(newReport.getSourceCodeFile(0), is(file1));
		assertThat(newReport.getSourceCodeFile(1), is(file2));
		assertThat(newReport.getSourceCodeFile(2), is(file3));
		
		newReport = new Report(sourceFileList2, stubbedRefList);
		
		assertThat(newReport.totalSourceCodeFiles(), is(sourceFileList2.size()));
		assertThat(newReport.getSourceCodeFile(0), is(file1));
		assertThat(newReport.getSourceCodeFile(1), is(file2));
		assertThat(newReport.getSourceCodeFile(2), is(file3));
		assertThat(newReport.getSourceCodeFile(3), is(file4));
		assertThat(newReport.getSourceCodeFile(4), is(file5));
		
		newReport.sortSourceFiles();
		
		assertThat(newReport.totalSourceCodeFiles(), is(sourceFileList2.size()));
		assertThat(newReport.getSourceCodeFile(0), is(file5));
		assertThat(newReport.getSourceCodeFile(1), is(file4));
		assertThat(newReport.getSourceCodeFile(2), is(file1));
		assertThat(newReport.getSourceCodeFile(3), is(file2));
		assertThat(newReport.getSourceCodeFile(4), is(file3));
	}
	
	@Test
	void testSuggestedRefactoringsWithReport() {
		fail("Suggested refactoring is still a stub class.");
	}
	
	/**
	 * Tests the effect that property file has on information represented in the Report
	 */
	@Test
	void testPropertyFileWithReport() {
		
		PropertiesFile defaultProps = new PropertiesFile();				
		ArrayList<String> expectedExtensions = new ArrayList<String>(Arrays.asList("h","cpp"));
		
		assertThat(defaultProps.getCppExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
		assertThat(defaultProps.getMaxSubstitutions(), is(8));
		assertThat(defaultProps.getMinSequenceLength(), is(10));
		
		SuppliedFilePaths project = new SuppliedFilePaths();
		project.setEligibleExtensions(defaultProps.getCppExtensions());		
		ArrayList<SourceCodeFile> projectFiles = project.findEligibleSourceCode(userFileInput);
		
		//Only Stubbed because this is not a major test factor in this test/class still not developed
		ArrayList<SuggestedRefactoring> stubbedRefList
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
					refactoring5, refactoring6, refactoring7));
		
		Report newReport = new Report(projectFiles, stubbedRefList);
		
		assertThat(newReport.totalSourceCodeFiles(), is(projectFiles.size()));
		assertThat(newReport.getSourceCodeFile(0), is(projectFiles.get(0)));
		assertThat(newReport.getSourceCodeFile(1), is(projectFiles.get(1)));
		assertThat(newReport.getSourceCodeFile(2), is(projectFiles.get(2)));
		assertThat(newReport.getSourceCodeFile(3), is(projectFiles.get(3)));
		
		assertThat(newReport.getSourceCodeFile(0).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(1).getFilePath().toString(),
				containsString("invalid.cpp"));
		assertThat(newReport.getSourceCodeFile(2).getFilePath().toString(),
				containsString("hello-world.cpp"));
		assertThat(newReport.getSourceCodeFile(3).getFilePath().toString(),
				containsString("hello-world.h"));
		assertThat(newReport.getSourceCodeFile(4).getFilePath().toString(),
				containsString("hello-world1.cpp"));
		
		assertThat(newReport.totalRefactorings(), is(stubbedRefList.size()));
		assertThat(newReport.getRefactoring(0), is(stubbedRefList.get(0)));
		assertThat(newReport.getRefactoring(1), is(stubbedRefList.get(1)));
		assertThat(newReport.getRefactoring(2), is(stubbedRefList.get(2)));
		assertThat(newReport.getRefactoring(3), is(stubbedRefList.get(3)));
		
		newReport.trimRefactorings(defaultProps.getMaxSubstitutions(), defaultProps.getMinSequenceLength());
		newReport.sortRefactorings();
		
		assertThat(newReport.totalRefactorings(), not(is(stubbedRefList.size())));
		
		try {
			PropertiesFile normalProps = new PropertiesFile("src/itest/data/propertyFile/expectedProperty.ini");	
			expectedExtensions = new ArrayList<String>(Arrays.asList("h","cpp","H","hpp"));
			
			assertThat(normalProps.getCppExtensions(), containsInAnyOrder(expectedExtensions.toArray()));
			assertThat(normalProps.getMaxSubstitutions(), is(5));
			assertThat(normalProps.getMinSequenceLength(), is(20));
			
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(normalProps.getCppExtensions());
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
			newReport = new Report(projectFiles, stubbedRefList);
			
			assertThat(newReport.totalSourceCodeFiles(), is(projectFiles.size()));
			assertThat(newReport.getSourceCodeFile(0), is(projectFiles.get(0)));
			assertThat(newReport.getSourceCodeFile(1), is(projectFiles.get(1)));
			assertThat(newReport.getSourceCodeFile(2), is(projectFiles.get(2)));
			assertThat(newReport.getSourceCodeFile(3), is(projectFiles.get(3)));
			
			assertThat(newReport.getSourceCodeFile(0).getFilePath().toString(),
					containsString("hello-world.cpp"));
			assertThat(newReport.getSourceCodeFile(1).getFilePath().toString(),
					containsString("invalid.cpp"));
			assertThat(newReport.getSourceCodeFile(2).getFilePath().toString(),
					containsString("hello-world.cpp"));
			assertThat(newReport.getSourceCodeFile(3).getFilePath().toString(),
					containsString("hello-world.h"));
			assertThat(newReport.getSourceCodeFile(4).getFilePath().toString(),
					containsString("hello-world1.cpp"));
			assertThat(newReport.getSourceCodeFile(5).getFilePath().toString(),
					containsString("moreTestFiles.hpp"));
			
			assertThat(newReport.totalRefactorings(), is(stubbedRefList.size()));
			assertThat(newReport.getRefactoring(0), is(stubbedRefList.get(0)));
			assertThat(newReport.getRefactoring(1), is(stubbedRefList.get(1)));
			assertThat(newReport.getRefactoring(2), is(stubbedRefList.get(2)));
			assertThat(newReport.getRefactoring(3), is(stubbedRefList.get(3)));
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
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
			newReport = new Report(projectFiles, stubbedRefList);
			
			assertThat(newReport.totalSourceCodeFiles(), is(projectFiles.size()));
			assertThat(newReport.getSourceCodeFile(0), is(projectFiles.get(0)));
			assertThat(newReport.getSourceCodeFile(1), is(projectFiles.get(1)));
			assertThat(newReport.getSourceCodeFile(2), is(projectFiles.get(2)));
			
			assertThat(newReport.getSourceCodeFile(0).getFilePath().toString(),
					containsString("empty.txt"));
			assertThat(newReport.getSourceCodeFile(1).getFilePath().toString(),
					containsString("Information.data"));
			assertThat(newReport.getSourceCodeFile(2).getFilePath().toString(),
					containsString("oopsJava.java"));
			
			assertThat(newReport.totalRefactorings(), is(stubbedRefList.size()));
			assertThat(newReport.getRefactoring(0), is(stubbedRefList.get(0)));
			assertThat(newReport.getRefactoring(1), is(stubbedRefList.get(1)));
			assertThat(newReport.getRefactoring(2), is(stubbedRefList.get(2)));
			assertThat(newReport.getRefactoring(3), is(stubbedRefList.get(3)));
		}
		catch(Exception e) {
			fail("Error was not expected: " + e);
		}
	}

}
