---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
---

<!-- HTML source retrieved from an example at
https://www.cs.odu.edu/~zeil/gitlab/reportAccumulator/reports/ -->
<link rel="stylesheet" type="text/css" href="assets/projectReports.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js" type="text/javascript"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="assets/projectReports.js"></script>

<table>
    <tr>
    <th colspan="2">General</th>
    </tr>
    <tr>
    <td>
        <a href="docs/javadoc/">JavaDoc</a>
    </td>
    </tr>
    <!-- <td>
        <a href="project/dependencies/root.html">Project
        Dependencies</a>
    </td> -->
    <tr>
    <th colspan="2">Testing</th>
    </tr>
    <tr>
    <td>
        <a href="reports/tests/test/">JUnit unit test report</a>
    </td>
    <td>
        <!-- For each graph to be displayed, 
            create a named div element. -->
        <div id="junitGraph" class="graph">junit</div>
    </td>
    </tr>
    <tr>
    <td>
        <a href="reports/jacoco/test/html/index.html">Unit Test
        Coverage</a>
    </td>
    <td>
        <div id="jacocoGraph" class="graph">jacoco</div>
    </td>
    </tr>
    <tr>
        <td>
            <a href="reports/tests/iTest/index.html">Integration test report</a>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            <a href="reports/tests/sTest/index.html">System test report</a>
        </td>
        <td></td>
    </tr>
    <tr>
    <th colspan="2">Analysis</th>
    </tr>
    <tr>
    <td>
        <a href="reports/checkstyle/main.html">CheckStyle</a>
    </td>
    <td>
        <div id="checkstyleGraph" class="graph">checkstyle</div>
    </td>
    </tr>
    <tr>
    <td>
        <a href="reports/spotbugs/main.html">SpotBugs</a>
    </td>
    <td>
        <div id="spotbugsGraph" class="graph">SpotBugs</div>
    </td>
    </tr>
    <tr>
    <td>
        <a href="reports/pmd/main.html">PMD</a>
    </td>
    <td>			  
        <div id="pmdGraph" class="graph">PMD</div>
    </td>
    </tr>
</table>
    

<!-- For each graph to be displayed, call register1 or 
        register2 (depending on the number of data series
        being plotted.  -->
<script type="text/javascript">
    register2("junitGraph", "reports/tests.csv", "JUnit Tests", "Test Cases");
    register2("jacocoGraph", "reports/jacoco.csv", "Test Coverage", "# Branches");
    register1("pmdGraph", "reports/pmd.csv", "PMD", "Warnings");
    register1("checkstyleGraph", "reports/checkstyle.csv", "Checkstyle", "Warnings");
    // register2("findbugsGraph", "reports/findbugs.csv", "FindBugs", "Warnings");
    register2("spotbugsGraph", "reports/spotbugs.csv", "SpotBugs", "Warnings");
</script>