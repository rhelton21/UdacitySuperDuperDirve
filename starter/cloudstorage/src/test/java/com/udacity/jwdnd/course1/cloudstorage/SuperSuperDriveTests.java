package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperSuperDriveTests {

  private static final String FIRST_NAME = "Test";
  private static final String LAST_NAME = "User";
  private static final String USERNAME = "testuser";
  private static final String PASSWORD = "password";

  @LocalServerPort
  private int port;

  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void afterEach() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void getLoginPage() throws InterruptedException {
    driver.get(String.format("http://localhost:%d/login", this.port));
    Thread.sleep(1000);
    Assertions.assertEquals("Login", driver.getTitle());
  }

  @Test
  public void getHomePageUnauthorized() throws InterruptedException {
    driver.get(String.format("http://localhost:%d/home", this.port));
    Thread.sleep(1000);
    Assertions.assertEquals("Login", driver.getTitle());
  }


  private void signUp() throws InterruptedException {
    driver.get(String.format("http://localhost:%d/signup", this.port));
    Thread.sleep(1000);

    WebElement element = driver.findElement(By.id("inputFirstName"));
    element.sendKeys(FIRST_NAME);

    element = driver.findElement(By.id("inputLastName"));
    element.sendKeys(LAST_NAME);

    element = driver.findElement(By.id("inputUsername"));
    element.sendKeys(USERNAME);

    element = driver.findElement(By.id("inputPassword"));
    element.sendKeys(PASSWORD);

    element = driver.findElement(By.tagName("button"));
    element.click();
    Thread.sleep(1000);
  }

  private void login() throws InterruptedException {
    driver.get(String.format("http://localhost:%d/login", this.port));
    Thread.sleep(1000);

    WebElement element = driver.findElement(By.id("inputUsername"));
    element.sendKeys(USERNAME);

    element = driver.findElement(By.id("inputPassword"));
    element.sendKeys(PASSWORD);

    element = driver.findElement(By.tagName("button"));
    element.click();
    Thread.sleep(1000);
  }

  @Test
  public void test_notesFlow() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 1000);

    signUp();
    login();

    WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
    notesTab.click();
	Thread.sleep(500);

    WebElement noteAdd = driver.findElement(By.id("add-note-button"));
    noteAdd.click();
    Thread.sleep(500);	
	
    WebElement noteTitle = driver.findElement(By.id("note-title"));
    noteTitle.sendKeys("TestNoteTitle");

    WebElement noteDescription = driver.findElement(By.id("note-description"));
    noteDescription.sendKeys("TestNoteDescription");

    WebElement saveNote = driver.findElement(By.id("note-save-button"));
    saveNote.click();
    Thread.sleep(500);
    String text = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text);

    driver.get(String.format("http://localhost:%d/home", this.port));

    WebElement notesTab1 = driver.findElement(By.id("nav-notes-tab"));
    notesTab1.click();
	Thread.sleep(500);

    WebElement notesEdit = driver.findElement(By.id("edit-note-button"));
    notesEdit.click();
	Thread.sleep(500);

    WebElement noteTitle1 = driver.findElement(By.id("note-title"));
    noteTitle1.clear();
    noteTitle1.sendKeys("EditNoteTitle");
 
    WebElement noteDescription1 = driver.findElement(By.id("note-description"));
	noteDescription1.clear();
    noteDescription1.sendKeys("EditNoteDescription");

    WebElement saveNote1 = driver.findElement(By.id("note-save-button"));
    saveNote1.click();
	Thread.sleep(500);

    String text2 = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text2);


    driver.get(String.format("http://localhost:%d/home", this.port));

    WebElement notesTab2 = driver.findElement(By.id("nav-notes-tab"));
    notesTab2.click();
 	Thread.sleep(500);

    WebElement notesDelete = driver.findElement(By.id("delete-note-button"));
    notesDelete.click();
    String text3 = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text3);

  }

  @Test
  public void test_credentialFlow() throws InterruptedException {
    signUp();
    login();

    WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
    credentialsTab.click();
    Thread.sleep(500);

    WebElement credsAdd = driver.findElement(By.id("add-cred-button"));
    credsAdd.click();
	Thread.sleep(1000);


    WebElement credentialUrl = driver.findElement(By.id("credential-url"));
    credentialUrl.sendKeys("www.udacity.com");

    WebElement credentialUsername = driver.findElement(By.id("credential-username"));
    credentialUsername.sendKeys("testUsername");

    WebElement credentialPassword = driver.findElement(By.id("credential-password"));
    credentialPassword.sendKeys("testPassword");

    WebElement credentialSubmit = driver.findElement(By.id("save-cred-button"));
    credentialSubmit.click();
    Thread.sleep(1000);

    String text = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text);

    driver.get(String.format("http://localhost:%d/home", this.port));

    WebElement credentialsTab1 = driver.findElement(By.id("nav-credentials-tab"));
    credentialsTab1.click();
    Thread.sleep(500);

    WebElement credEdit = driver.findElement(By.id("edit-cred-button"));
    credEdit.click();
	Thread.sleep(500);


   WebElement credentialUrl1 = driver.findElement(By.id("credential-url"));
    credentialUrl1.sendKeys("www.google.com");

    WebElement credentialUsername1 = driver.findElement(By.id("credential-username"));
	credentialUsername1.clear();
    credentialUsername1.sendKeys("editUsername");

    WebElement credentialPassword1 = driver.findElement(By.id("credential-password"));
	credentialPassword1.clear();
    credentialPassword1.sendKeys("editPassword");

    WebElement credentialSubmit1 = driver.findElement(By.id("save-cred-button"));
    credentialSubmit1.click();
    Thread.sleep(1000);

    String text1 = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text1);

    driver.get(String.format("http://localhost:%d/home", this.port));

    WebElement credentialsTab2 = driver.findElement(By.id("nav-credentials-tab"));
    credentialsTab2.click();
    Thread.sleep(500);

    WebElement credDelete = driver.findElement(By.id("delete-cred-button"));
    credDelete.click();
    Thread.sleep(500);

    String text2 = driver.findElement(By.cssSelector("h1")).getText();
    Assertions.assertEquals("Success", text2);

  }

}
