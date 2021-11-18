package edu.odu.cs.cs350.dupedetector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

public class SuppliedFilePathsTest extends SuppliedFilePaths {
    @Test
    void testEligibleFileExtensionsNullPassage() {
        ArrayList<String> exts = new ArrayList<String>();
        SuppliedFilePaths sut = new SuppliedFilePaths();

        assertNotNull(sut.getEligibleExtensions()); // empty list
        assertThat(sut.getEligibleExtensions(), is(new ArrayList<String>()));

        exts.add("cpp"); exts.add("hpp");
        sut.setEligibleExtensions(exts);
        assertThat(sut.getEligibleExtensions(), containsInAnyOrder("cpp", "hpp"));   
    }

    @Test
    void testEligibleFileExtensionsDeepCopy() {
        
        // initial state
        ArrayList<String> extsCaptured = new ArrayList<String>();
        extsCaptured.add("cpp"); extsCaptured.add("hpp");

        ArrayList<String> extsSuppled = new ArrayList<String>();
        extsSuppled.add("cpp"); extsSuppled.add("hpp");


        SuppliedFilePaths sut = new SuppliedFilePaths();
        sut.setEligibleExtensions(extsSuppled);
        assertThat(sut.getEligibleExtensions(), containsInAnyOrder("cpp", "hpp"));
        // mutate
        extsSuppled.clear();
        // test deep copy
        assertThat(sut.getEligibleExtensions(), containsInAnyOrder("cpp", "hpp"));
        // expected change
    }

    @Test
    void addsFilesSpecifiedOutright() {
        ArrayList<String> extsSupplied = new ArrayList<String>();
        extsSupplied.add("cpp"); extsSupplied.add("hpp");

        SuppliedFilePaths sut = new SuppliedFilePaths();
        sut.setEligibleExtensions(extsSupplied);

        ArrayList<String> userInput = new ArrayList<String>();
        userInput.add(getPathStringForTest("hello-world.cpp"));
        userInput.add(getPathStringForTest("invalid.cpp"));
        ArrayList<SourceCodeFile> files = sut.findEligibleSourceCode(userInput);

        ArrayList<String> filePaths = new ArrayList<String>();
        for (SourceCodeFile f : files) {
            filePaths.add(f.getPath());
        }

        assertThat(filePaths, containsInAnyOrder(
            getPathStringForTest("hello-world.cpp"),
            getPathStringForTest("invalid.cpp")
        ));
    }

    @Test
    void includesFromFlatDirStructure() {
        ArrayList<String> extsSupplied = new ArrayList<String>();
        extsSupplied.add("cpp"); extsSupplied.add("hpp");

        SuppliedFilePaths sut = new SuppliedFilePaths();
        sut.setEligibleExtensions(extsSupplied);

        ArrayList<String> userInput = new ArrayList<String>();
        userInput.add(getPathStringForTest("test-recursion/1/a"));
        ArrayList<SourceCodeFile> files = sut.findEligibleSourceCode(userInput);

        ArrayList<String> filePaths = new ArrayList<String>();
        for (SourceCodeFile f : files) {
            filePaths.add(f.getPath());
        }

        assertThat(filePaths, containsInAnyOrder(
            getPathStringForTest("test-recursion/1/a/hello-world1a.cpp"),
            getPathStringForTest("test-recursion/1/a/invalid1a.cpp")
        ));
    }

    @Test
    void includesFromDeepDirStructure() {
        ArrayList<String> extsSupplied = new ArrayList<String>();
        extsSupplied.add("cpp"); extsSupplied.add("hpp");

        SuppliedFilePaths sut = new SuppliedFilePaths();
        sut.setEligibleExtensions(extsSupplied);

        ArrayList<String> userInput = new ArrayList<String>();
        userInput.add(getPathStringForTest("test-recursion/"));
        ArrayList<SourceCodeFile> files = sut.findEligibleSourceCode(userInput);

        ArrayList<String> filePaths = new ArrayList<String>();
        for (SourceCodeFile f : files) {
            filePaths.add(f.getPath());
        }

        assertThat(filePaths, containsInAnyOrder(
            getPathStringForTest("test-recursion/1/a/hello-world1a.cpp"),
            getPathStringForTest("test-recursion/1/a/invalid1a.cpp"),
            getPathStringForTest("test-recursion/1/hello-world1.cpp"),
            getPathStringForTest("test-recursion/2/hello-world2.cpp"),
            getPathStringForTest("test-recursion/hello-world-root.cpp"),
            getPathStringForTest("test-recursion/invalid-root.cpp")
        ));
    }

    @Test
    void includesOnlySpecified() {
        fail("Implementation stubbed");
    }

    String getPathStringForTest(String fileName) {
        Path path = Paths.get("src","test","data", "cpp", fileName);
        return path.toString();
    }

}
