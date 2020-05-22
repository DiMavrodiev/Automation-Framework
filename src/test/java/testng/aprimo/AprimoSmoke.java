package testng.aprimo;

import com.automationframework.api.driver.Driver;
import com.automationframework.core.utils.TestUtils;
import com.automationframework.pageobject.aprimo.smoke.Idea;
import com.automationframework.pageobject.aprimo.smoke.Login;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 5/10/18
 */

public class AprimoSmoke {


    @BeforeTest
    public void setUp() {
        try {
            Driver driver = new Driver();
            driver.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



   @AfterTest
    public void teardown() {
        Driver driver = new Driver();
        TestUtils.sleep(1000, "Error occurs if there is no wait, this is a workaround");
        driver.closeDriver();
    }
}