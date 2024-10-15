import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.*

fun getNewChromeDriver(): ChromeDriver {
    System.setProperty("webdriver.chrome.driver","/home/navn/aur/chromedriver/chromedriver105");
    val chromeOptions = ChromeOptions()
    chromeOptions.setBinary("/home/navn/aur/google-chrome/google-chrome105/opt/google/chrome/google-chrome")
    chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
    chromeOptions.setExperimentalOption("useAutomationExtension", false);
    chromeOptions.addArguments("--disable-blink-features=AutomationControlled")
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
    return ChromeDriver(chromeOptions)
}