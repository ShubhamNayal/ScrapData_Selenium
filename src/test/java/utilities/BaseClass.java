package utilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseClass {
    public ChromeDriver startDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

}
