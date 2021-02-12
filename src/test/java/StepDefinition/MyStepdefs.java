package StepDefinition;

import Utility.BrowserHandler;
import Utility.ReadPropertiesUtility;
import Utility.RestAssuredHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.testng.asserts.SoftAssert;


public class MyStepdefs {


    WebDriver driver;
    Properties locator_reader;
    Properties applicationProp_reader;
    List<String> ui_repos = new ArrayList<>();
    SoftAssert softAssert= new SoftAssert();
    RestAssuredHelper restAssuredHelper = new RestAssuredHelper();
    Logger log = Logger.getLogger(MyStepdefs.class);


    @Before
    public void loadFiles(){
        log.info("Loading Files and Reading Properties");
         locator_reader =  new ReadPropertiesUtility().readPropertyFiles("src/test/resources/xpath.properties");
        applicationProp_reader =  new ReadPropertiesUtility().readPropertyFiles("src/test/resources/application.properties");
        RestAssured.baseURI  = locator_reader.getProperty("github_api.get_repo_api");
    }


    @Given("Launch the browser {string}")
    public void launchBrowser(String browser){
        driver = BrowserHandler.getBrowserInstance(browser);
        assert driver != null;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @When("I navigate to github django page")
    public void i_navigate_to_github_django_page() {
        driver.get(locator_reader.getProperty("github_home.url"));
    }

    @Then("I fetch all repositories in UI")
    public void i_fetch_all_repositories_in_UI() {
        List<WebElement> repo_list = driver.findElements(By.xpath(locator_reader.getProperty("github_page.repository_list")));
        log.info("Repo List Size = "+ repo_list.size());
        List<WebElement> rep = driver.findElements(By.xpath(locator_reader.getProperty("github_page.reporitory_list3")));
        for (WebElement webElement_rep : rep) {
            String webString_repository = webElement_rep.getText();
            ui_repos.add(webString_repository.trim());
        }
        Collections.sort(ui_repos);


    }

    @And("I fetch all {string} repositories using API")
    public void i_fetch_all_repositories_using_API(String repoName) {
        restAssuredHelper.getRepoList(repoName);
    }

    @And("I verify {string} of repositories in UI and API")
    public void i_verify_repositories_in_UI_and_API(String dataName) {
        List<String> list_api = restAssuredHelper.getRepoData(dataName);
        Collections.sort(list_api);
        softAssert.assertTrue(list_api.equals(ui_repos), "Repository List from UI does not match with Repository list from API");
        softAssert.assertAll();
    }

    @After
    public void cleanup(){
        driver.close();
        driver.quit();
    }
}
