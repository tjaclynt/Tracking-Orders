package trackingOrders;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Item {
	String name, store, status, trackingNum;
	
	// Constructor for an item with empty parameters, used as a reference
	public Item() {
		
	}
	
	// Constructor for an item initialized by a name, store, and tracking number
	public Item(String name, String store, String trackingNum) throws InterruptedException {
		this.name = name;
		this.store = store;
		this.trackingNum = trackingNum;
		
		// If order has not been shipped yet, status will display "In Progress"
		if (trackingNum.equalsIgnoreCase("none")) {
			this.status = "In Progress";
		} else {
			
			// Path to driver executable
	        File file = new File("C:\\Users\\tjacl\\103chromedriver\\chromedriver.exe");
	        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
	
			// Deleting all the cookies
			driver.manage().deleteAllCookies();
	
			// Specifying pageLoadTimeout and Implicit wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			
	        // Goes to website that tracks the status of the order
	        Thread.sleep(9000);
	        String URL = "https://www.ordertracker.com/track/" + trackingNum;
			driver.get(URL);
			
			// Obtains the status of item in a string
			Thread.sleep(9000);
			String details = driver.findElement(By.xpath("//*[@id=\"summary\"]/h1/span[2]/span[1]/span[2]/span[2]")).getText();
			
			Thread.sleep(9000);
			this.status = details;
			driver.quit();
		}
	}
	
	// Finds and updates the status for an item that has been shipped (tracking number was not initially provided)
	public void updateStatus(String trackingNum) throws InterruptedException {
		this.trackingNum = trackingNum;
		
		// Path to driver executable
        File file = new File("C:\\Users\\tjacl\\103chromedriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Deleting all the cookies
		driver.manage().deleteAllCookies();

		// Specifying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
        // Goes to website that tracks the status of the order
        Thread.sleep(9000);
        String URL = "https://www.ordertracker.com/track/" + trackingNum;
		driver.get(URL);
		
		// Obtains the status of item in a string
		Thread.sleep(9000);
		String details = driver.findElement(By.xpath("//*[@id=\"summary\"]/h1/span[2]/span[1]/span[2]/span[2]")).getText();
		
		Thread.sleep(9000);
		this.status = details;
		driver.quit();
	}
	
	// Returns name of item
	public String getName() {
		return name;
	}
	
	// Returns store of where item was purchased
	public String getStore() {
		return store;
	}
	
	// Returns status of item
	public String getStatus() {
		return status;
	}
	
	// Returns tracking number of item
	public String getTrackingNum() {
		return trackingNum;
	}
	
}
