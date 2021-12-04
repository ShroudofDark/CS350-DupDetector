package edu.odu.cs.cs350.dupedetector;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Has some tests that could go into integration tests, barring a mock of
 * SourceCodeFile.tokenize()
 */
public class SuppliedFilePaths {
    private ArrayList<String> eligibleExtensions;
    // Internal
    private ArrayList<Path> files = new ArrayList<Path>();
    private ArrayList<Path> dirs = new ArrayList<Path>();
    
    /**
     * Sets the file extensions to use when searching for eligible
     * source code files within the directories supplied by the user
     * (if any). The file extension strings should not have periods (.) 
     * @param exts the set of file extensions which identify source code files
     *             when traversing directories 
     */
    public void setEligibleExtensions(ArrayList<String> exts) {
        ArrayList<String> newEligibleExts = new ArrayList<String>();
        if (exts == null) {
            eligibleExtensions = newEligibleExts;
            return;
        }
        for (String e : exts) {
            newEligibleExts.add(new String(e));
        }
        eligibleExtensions = newEligibleExts;
    }

    /**
     * 
     * @return the file extensions considered as source code files in directories
     */
    public ArrayList<String> getEligibleExtensions() {
        ArrayList<String> extsOut = new ArrayList<String>();
        if (eligibleExtensions == null)
            return extsOut;
        for (String ext : eligibleExtensions) {
            extsOut.add(ext);
        }
        return extsOut;
    }

    /**
     * Attempts to add the specified files and the eligible file contents of each
     * supplied directory to the working list of source code returned
     * @return list of all eligible source code found
     */
    public ArrayList<SourceCodeFile> findEligibleSourceCode(ArrayList<String> userInput) {
        setSuppliedFilesAndDirs(userInput);

        ArrayList<SourceCodeFile> sourceCode = new ArrayList<SourceCodeFile>();

        // first step, add all files that were specified in the CLI as eligible;
        // we don't care about their extention if there were supplied thus.
        for (Path file : files) {
            attemptToGatherTokenizedFile(file, sourceCode);
        }
        // Traverse each dir supplied
        for (Path directory: dirs) {
            attemptToAddEligibleFilesFromDirsTo(sourceCode, directory);
        }
        return sourceCode;
    }


    private void setSuppliedFilesAndDirs(ArrayList<String> userInput) {
        files.clear();
        dirs.clear();

        // serialize
        ArrayList<Path> userInputAsPaths = new ArrayList<Path>();
        for(String inputStr : userInput)
            userInputAsPaths.add( Path.of(inputStr) );

        // set values
        splitFilesAndDirsFromList(userInputAsPaths, files, dirs);
        return;
    }



    private void attemptToAddEligibleFilesFromDirsTo(ArrayList<SourceCodeFile> allSourceCode,
        Path currentDir)
    {
        try {
            addEligibleFilesFromDirsTo(allSourceCode, currentDir);
        } catch (Exception e) {
            // Skip that directory
        }
    }

    /**
     * Adds traversal of a given directory tree
     * @param sourceCode
     * @param currentDir
     * @throws IOException when something goes wrong
     */
    private void addEligibleFilesFromDirsTo(ArrayList<SourceCodeFile> allSourceCode,
        Path currentDir) throws IOException
    {
        // Credit: https://www.baeldung.com/java-list-directory-files
        Files.walkFileTree(currentDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException
            {
                if (!Files.isDirectory(file) && hasCorrectExtension(file)) {
                    attemptToGatherTokenizedFile(file, allSourceCode);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }


    /**
     * Attempt to add tokenize a file and add it to the SourceCodeFile list.
     * This method is an error boundary.
     * @param file
     * @param allSourceCode
     */
    private static void attemptToGatherTokenizedFile(Path file, ArrayList<SourceCodeFile> allSourceCode) {
        try {
            SourceCodeFile thisCodeFile = new SourceCodeFile(
                file.toString());
            thisCodeFile.tokenize(); // I'd guess we get disk read benefits by
                                     // doing this here rather than later
            allSourceCode.add(thisCodeFile);
        } catch (Exception e) {
            // just skip the file
        }
    }

    private static void splitFilesAndDirsFromList(ArrayList<Path> main,
        ArrayList<Path> files, ArrayList<Path> dirs)
    {
        Iterator<Path> it = main.iterator();
        while (it.hasNext()) {
            Path thePath = it.next();
            if (isFile(thePath)) {
                files.add(thePath);
                continue;
            } else if (isDir(thePath)) {
                dirs.add(thePath);
            }
            // else, the path doesn't actually point to anything and can be ignored.
        }      
    }

    private static boolean isDir(Path path) {
        if (path == null || !Files.exists(path)) return false;
        else return Files.isDirectory(path);
    }

    private static boolean isFile(Path path) {
        if (path == null || !Files.exists(path)) return false;
        else return Files.isRegularFile(path);
    }

    //Checks against eligible Extensions
    private boolean hasCorrectExtension(Path file) {
    	
    	//Extract the extension of current file
    	String fileName = file.toString();
    	String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
    	
    	//Compare to eligible extensions
    	Iterator<String> it = eligibleExtensions.iterator();   	
    	while(it.hasNext()) {
    		String currExten = it.next();
    		//Matches with eligible extension
    		if(currExten.equals(fileExt)) {
    			return true;
    		}
    	}
    	
    	//Doesn't match eligible extension
        return false;
    }
}
