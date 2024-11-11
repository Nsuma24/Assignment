package pac;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestions {
	
	public static void main(String[] args) {
		/**
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//switch to new tab
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.google.com/");
		WebElement search = driver.findElement(By.xpath("//textarea[@aria-controls='Alh6id']"));
		search.sendKeys("java");	**/
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// driver.get("https://www.google.co.in/");

		driver.switchTo().newWindow(WindowType.TAB);

		driver.get("https://www.google.co.in/");

		driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("restassured");

		List<WebElement> allAutoSugs = driver.findElements(By.xpath("//ul[@class='G43f7e']/li/div"));

		for (WebElement sug : allAutoSugs) 
		{

			//String sugText = driver.findElement(By.xpath("//div[@aria-label=\"restassured documentation\"]")).getText();
             String sugText=sug.getText();
             System.out.println(sugText);
			
             if (sugText.contains("documentation")) 
             {

				sug.click();
				break;

			}
		}
	}

}
