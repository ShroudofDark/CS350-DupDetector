package edu.odu.cs.cs350.dupedetector;

import java.util.ArrayList;
import java.util.Iterator;

public class SuppliedFilePaths {

    // private ArrayList<Directory> dirs; // TODO: implement class
    private ArrayList<String> eligibleExtensions;
    
    /**
     * Sets the file extensions to use when searching for eligible
     * source code files within the directories supplied by the user
     * (if any). The file extension strings should not have periods (.) 
     * @param exts the set of file extensions which identify source code files
     *             when traversing directories 
     */
    public void setEligibleExtensions(ArrayList<String> exts) {
        eligibleExtensions = exts;
    }

    public ArrayList<String> getEligibleExtensions() {
        ArrayList<String> extsOut = new ArrayList<String>();
        Iterator<String> it = eligibleExtensions.iterator();
        while(it.hasNext()) {
            extsOut.add(it.next());
        }
        return extsOut;
    }

    public void setSuppliedFilesAndDirs(ArrayList<String> userInput) {
        // TODO: parse out whether input is a file or is a dir; add to internal list
        // of files and directories
        return;
    }

    /**
     * 
     * @return list of all eligible source code found for the specified directories
     */
    public ArrayList<SourceCodeFile> findEligibleSourceCode() {
        // TODO: implement recursive file search

        // first step, add all files that were specified in the CLI as eligible;
        // we don't care about their extention if there were supplied thus.

        // then, search . . .

        // STUB:
        ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();
        files.add(new SourceCodeFile("/foo/bar/baz.cpp"));
        files.add(new SourceCodeFile("/whos/on/first/base.cpp"));
        files.add(new SourceCodeFile("/cs/three/fifty.cpp"));
        files.add(new SourceCodeFile("/home/zeil/projects/cppProject1/src/foo.cpp"));
        files.add(new SourceCodeFile("/home/zeil/projects/cppProject1/src/headers/bar.h"));
        files.add(new SourceCodeFile("H:\\\\cygwin\\\\home\\\\Jacob\\\\someCpp.cpp"));
        files.add(new SourceCodeFile("/home/fakeZeil/projects/cppProject1/src/foo.cp"));
        files.add(new SourceCodeFile("/coolhome/fakeZeil/cppProject1/src/tool.cpp"));
        return files;
    }
}
