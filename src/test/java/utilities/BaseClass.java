package utilities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseClass {
    MongoClient mongoClient ;
    MongoDatabase database;
    MongoCollection<Document> collection;
    Properties properties;
    public ChromeDriver startDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
    public  MongoCollection<Document> startDb(){
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("mycollections");
        return collection;
    }
    public Properties setUpPrerequisiteProperties()  {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\test\\java\\utilities\\config.properties");
            properties.load(fileInputStream);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return properties;
    }
}
