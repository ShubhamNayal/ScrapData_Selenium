package utilities;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HelperClass {
    public HelperClass(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[@class='job-category']")
    private List<WebElement> department;
    @FindBy(xpath = "//h2[@class='card-title']")
    private List<WebElement> jobName;
    @FindBy(xpath = "//p[@class='card-subtitle']")
    private List<WebElement>location;
    @FindBy(xpath = "//span[text()='Next Page']")
    private WebElement nextText;
    @FindBy(id = "_evidon-accept-button" )
    private WebElement element;
    public void closeCookiesPage() {
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("cookies page not visible");
        }

    }
    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }
    public void checkNext(WebDriver driver, MongoCollection<Document> collection) {
        boolean isNextVisible = nextVisible(driver);
        while (isNextVisible) {
            getDataFromWeb(driver, collection);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", nextText);
            isNextVisible = nextVisible(driver);
        }
        getDataFromWeb(driver,collection);
    }
    public boolean nextVisible(WebDriver driver){
        try {
            nextText.isDisplayed();
            return true;
        }catch (Exception NoSuchElementException) {
            return false;
        }
    }
    private void getDataFromWeb(WebDriver driver, MongoCollection<Document> collection){
        for (int i=0;i<department.size();i++){
            addToMongo(department.get(i).getText(),jobName.get(i).getText(),location.get(i).getText(),collection);
        }
    }
    private void addToMongo(String department,String job,String location, MongoCollection<Document> collection){
        Document document = new Document("department", department)
                .append("job", job)
                .append("location", location);
        collection.insertOne(document);
    }




}
