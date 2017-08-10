package com.epam.pages;

import com.epam.dataUtils.PropertyData;
import com.epam.control.Button;
import com.epam.control.TextInput;
import com.epam.webdriverutils.WebDriverUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class GmailLoginPage extends PageObject{

    @FindBy(name="identifier")
    private TextInput loginInput;

    @FindBy(id="identifierNext")
    private Button nextButtonLogin;

    @FindBy(name="password")
    private TextInput passwordInput;

    @FindBy(id="passwordNext")
    private Button nextButtonPassword;

    public GmailLoginPage() throws IOException {
        super();
        PropertyData.loadData();
        WebDriverUtils.getDriver().get(PropertyData.getURLFromProperty());
    }

    public GmailLoginPage typeLoginAndSubmit(String login){
        loginInput.sendKeys(login);
       new WebDriverWait(WebDriverUtils.getDriver(), 30).until(ExpectedConditions.elementToBeClickable(nextButtonLogin.webElement));

        nextButtonLogin.click();
        return this;
    }

    public GmailPage typePasswordAndSubmit(String password){
        passwordInput.sendKeys(password);
            new WebDriverWait(WebDriverUtils.getDriver(), 30).until(ExpectedConditions.elementToBeClickable(nextButtonPassword.webElement));
     new Actions(WebDriverUtils.getDriver()).moveToElement(nextButtonPassword.webElement).click().build().perform();
       // nextButtonPassword.click();
        return new GmailPage();
    }
}
