- Automation is developed using java programming language and selenium tool.
- Cucumber, TestNG, Webdrivermanager, Extentreports and Slf4j libraries were used in this test automation.
- The purpose of TestNG and Extentreports are to create a Dashboard with the Test Listener. The ability to run tests in parallel is available in TestNG but not in JUnit.
- The purpose of the Cucumber library is to create test scenarios close to colloquial language. These scenarios are transformed into methods. Cucumber's file type is .feature.
- Slf4j library was used to see detailed log information.
- The Webdrivermanager library was used to download the driver of the respective browser automatically. Thus, it is not necessary to download the drivers manually and show the path.
- The created dashboard is stored under the /report file according to the date information.
- When a scenario failed, it was shown on the dashboard along with a screenshot.


Dashboard fail case:

![FailScreenShot](https://raw.githubusercontent.com/AhmetRufai/TrendyolAutomationCase/master/ScreenShots/DashboardFail.JPG)

Dashboard success case:

![FailScreenShot](https://raw.githubusercontent.com/AhmetRufai/TrendyolAutomationCase/master/ScreenShots/DashboardSuccess.PNG)
