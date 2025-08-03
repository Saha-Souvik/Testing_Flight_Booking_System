package hook;

import java.lang.reflect.Field;
import java.util.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;

public class ExtentHooks {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static Map<String, ExtentTest> parentTests = new HashMap<>();
    private static ThreadLocal<Set<String>> executedSteps = ThreadLocal.withInitial(HashSet::new);

    @BeforeAll
    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Test Report");
        spark.config().setReportName("Flight Booking System Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void createTest(Scenario scenario) {
        String featureName = scenario.getUri().toString()
                .substring(scenario.getUri().toString().lastIndexOf("/") + 1)
                .replace(".feature", "");

        ExtentTest parent = parentTests.computeIfAbsent(featureName,
                k -> extent.createTest(k));

        ExtentTest child = parent.createNode(scenario.getName());
        test.set(child);
        executedSteps.get().clear();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        String stepText = getCurrentStepText(scenario);

        if (executedSteps.get().contains(stepText)) return;
        executedSteps.get().add(stepText);

        if (scenario.isFailed()) {
            WebDriver driver = ApplicationHooks.driver;
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.get().fail("❌ " + stepText)
                     .addScreenCaptureFromBase64String(base64, "Screenshot on Failure");
        } else {
            test.get().pass("✅ " + stepText);
        }
    }

    @After
    public void logScenarioStatus(Scenario scenario) {
        if (scenario.isFailed()) {
            test.get().log(Status.FAIL, "Scenario Failed: " + scenario.getName());
        } else {
            test.get().log(Status.PASS, "Scenario Passed: " + scenario.getName());
        }
    }

    @AfterAll
    public static void flushReport() {
        extent.flush();
    }

    private String getCurrentStepText(Scenario scenario) {
        try {
            Field delegateField = scenario.getClass().getDeclaredField("delegate");
            delegateField.setAccessible(true);
            Object delegate = delegateField.get(scenario);

            Field testCaseField = delegate.getClass().getDeclaredField("testCase");
            testCaseField.setAccessible(true);
            Object testCase = testCaseField.get(delegate);

            Field testStepsField = testCase.getClass().getDeclaredField("testSteps");
            testStepsField.setAccessible(true);
            List<?> testSteps = (List<?>) testStepsField.get(testCase);

            for (Object step : testSteps) {
                if (step instanceof PickleStepTestStep) {
                    PickleStepTestStep pickleStep = (PickleStepTestStep) step;
                    String stepText = pickleStep.getStep().getText();
                    if (!executedSteps.get().contains(stepText)) {
                        return stepText;
                    }
                }
            }
        } catch (Exception e) {
            return "Unable to retrieve step";
        }
        return "Unknown Step";
    }
}
