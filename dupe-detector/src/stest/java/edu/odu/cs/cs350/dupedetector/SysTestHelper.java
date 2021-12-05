package edu.odu.cs.cs350.dupedetector;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Helper methods for system testing
 */
public final class SysTestHelper {
    /**
     * Runs the DupeDetector Jar with the specified arguments, handling (for the
     * most part) correct CLI formatting
     * @param nSuggestions number of suggestions to ask for
     * @param propsFilePath props file path to use, if any
     * @param sourcePaths either files or directories containing source code
     * @return program output as a string
     */
    public static String runJar(String nSuggestions, String propsFilePath,
        List<String> sourcePaths)
    {
        LinkedList<String> args = new LinkedList<String>();
        args.add(nSuggestions);
        if (propsFilePath != null) {
            args.add(propsFilePath);
        }
        for (String path : sourcePaths) {
            args.add(path);
        }
        return SysTestHelper.runJarRaw(args);
    }

    /**
     * Runs the DupeDetector Jar with an arbitrary string as the CLI arguments  
     * @param cliArgs. Best performance if using a LinkedList due to adding to
     * the front of the list in this method
     * @return
     */
    public static String runJarRaw(List<String> cliArgs) {
        String jarPath = (Path.of(System.getProperty("user.dir"), "build", "libs",
            "DupDetector.jar")).toString();
        cliArgs.add(0, jarPath);
        // build for program execution now
        cliArgs.add(0, "-jar");
        cliArgs.add(0, "java");
        return runCommandForOutput(new ProcessBuilder(cliArgs));
    }

    // This method copied from https://stackoverflow.com/a/41292766 and modified
    public static String runCommandForOutput(ProcessBuilder pb) {
        Process p;
        String result = "";
        try {
            p = pb.start();
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream())
            );
    
            StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
            reader.lines().iterator().forEachRemaining(sj::add);
            result = sj.toString();
    
            p.waitFor();
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }
}
