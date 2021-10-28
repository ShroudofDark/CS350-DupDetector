package edu.odu.cs.cs350.dupedetector;

import java.util.Vector;

/**
 * The FilePaths sorts the file paths alphabetically and provides a way for the report class to print 
 * out the sorted files via two methods:
 * 
 * sortFilePaths ... which handles the sorting
 * printFilePaths ... provides a way to output
 * 
 * Added Comparable to Class for "collections.sort"
 *
 * @author Isaac Replogle: created file path sort and print
 */

public class FilePaths {

    /* Stores total number of files */
    private int fileCount;
    /* Stores the unsorted file paths */
    private Vector<String> filePaths = new Vector<>();
    /* Stores the sorted file paths */
    private Vector<String> sortedFilePaths = new Vector<>();

    /* Creates a FilePaths object */
	public FilePaths() {

	}

    /* Prints out the requested files
    *  @param positiveInt
    */ 
	public void printFilePaths(int count) {

        fileCount = count;

        System.out.println(sortedFilePaths.get(fileCount));
    
    }
    

    /* Sorts the file Paths Alphabetically 
    *  No Parameters
    */
	private void sortedFilePaths() {

        /* creates holding vector for files */
        Vector<String> fileToBeSorted = new Vector<>();

        /* adds file paths to holding vector */
        fileToBeSorted.addAll(filePaths);

        /* alphabetically sorts file paths */
       /* collections.sort(fileToBeSorted);*/

        /* stores sorted files into final holding place */
        sortedFilePaths.addAll(fileToBeSorted);

	}


}

/* https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List) */