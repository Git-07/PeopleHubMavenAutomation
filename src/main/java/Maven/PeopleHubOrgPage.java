package Maven;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeopleHubOrgPage {
	// private static Logger log =
	// LogManager.getLogger(PeopleHub_ORGPage.class.getClass());
	static WebDriverWait wait;
	static WebDriver driver;

	public void setTheWebDriverWait(WebDriver driver, long time) {
		wait = new WebDriverWait(driver, time);
	}

	public static WebElement getTheCesElement() {
		WebElement cesElement = null;
		try {
			cesElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(("slides_container"))));
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		return cesElement;
	}

	public List<WebElement> getTheImageElement() {
		List<WebElement> imageElement = null;
		try {
			imageElement = PeopleHubOrgPage.getTheCesElement().findElements(By.tagName("img"));
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		return imageElement;
	}

	public List<WebElement> getImmerseElement() {
		List<WebElement> immerseElement = null; 
		try{
		immerseElement = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='MT-tweets'] div ul li img")));
		}catch (NoSuchElementException e){
			e.getMessage();
		} 
		return immerseElement;
	}
	
	public WebElement getUploadElement(){
		WebElement fileUpload = null;
		try{
			fileUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(("//input[@name='uploadfile_0']"))));
		}catch(NoSuchElementException e){

		}
		return fileUpload;
	}
	
	public WebElement getSubmitUploadElement(){
		WebElement fileUpload = null;
		try{
			fileUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='button']")));
		}catch(NoSuchElementException e){

		}
		return fileUpload;
	}
	
	public WebElement submit(){
		WebElement sub = null;
		try{
			sub = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
		}catch(NoSuchElementException e){

		}
		return sub;
	}
	
	public WebElement userNameElement(){
		WebElement user = null;
		try{
			user = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
		}catch(NoSuchElementException e){

		}
		return user;
	}
	
	public WebElement passwordElement(){
		WebElement pwd = null;
		try{
			pwd = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
		}catch(NoSuchElementException e){

		}
		return pwd;
	}
}

