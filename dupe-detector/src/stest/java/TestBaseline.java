import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs350.dupedetector.test.SysTestHelper;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public final class TestBaseline {
    @Test
    void testNoArgsErrorMessage() {
        LinkedList<String> args = new LinkedList<String>();
        String output = SysTestHelper.runJarRaw(args);
        assertThat(output, is("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]"));
    }

    @Test
    void testSystemCppPlaceholder() {
        LinkedList<String> paths = new LinkedList<String>();
        paths.add("dupe-detector/src/test/data/cpp");
        String output = SysTestHelper.runJar("52", null, paths);
        assertThat(output, not(is("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]")));
    }

}
