package stepDefinitionPackage;
import com.mongodb.client.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bson.Document;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.BaseClass;
import utilities.HelperClass;
import java.io.FileInputStream;
import java.util.Properties;

public class jobDataScrapingStepDefinitions extends BaseClass {
    ChromeDriver driver;
    String url;
    HelperClass helperFunction;
    MongoClient mongoClient ;
    MongoDatabase database;
    MongoCollection<Document> collection;
    Properties properties;
    public jobDataScrapingStepDefinitions() {
        driver = startDriver();
        PageFactory.initElements(driver,this);
        setUp();
    }

    public void setUp()  {
         properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\test\\java\\utilities\\config.properties");
            properties.load(fileInputStream);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        url = properties.getProperty("url");
        helperFunction = new HelperClass(driver);

        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb2");
        collection = database.getCollection("mycollections2");
    }


    @Given("^Go to Tipico job page$")
    public void go_to_tipico_job_page()  {
       driver.get(url);
    }

    @When("^On landing on the jobs page$")
    public void on_landing_on_the_jobs_page()  {
        helperFunction.closeCookiesPage();
           }

    @Then("^fetch all the jobs$")
    public void fetch_all_the_jobs()  {
        String title = properties.getProperty("title");
        Assert.assertEquals(title,helperFunction.getTitle(driver));
    }

    @And("^click on next until all jobs are fetched$")
    public void click_on_next_until_all_jobs_are_fetched() {
        helperFunction.checkNext(driver,collection);
    }

}
