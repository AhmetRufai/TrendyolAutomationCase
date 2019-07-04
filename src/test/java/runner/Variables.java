package runner;
/* Global variables are defined in this class. */

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Variables {
    public static WebDriver driver;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    public static String reportLocation = getProjectLocation() + "/report/" + getDate() + "/";


    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }

    public static String getProjectLocation() {
        Properties p = new Properties(System.getProperties());
        String location = p.getProperty("user.dir");
        String replaceLocation = location.replace('\\', '/');
        return replaceLocation;
    }

}
