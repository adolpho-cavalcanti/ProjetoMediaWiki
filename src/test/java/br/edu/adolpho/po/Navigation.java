/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.adolpho.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Criação
 */
public class Navigation extends BasePage{
    
    @FindBy(css = "#pt-mytalk > a")
    WebElement talk;
    
    @FindBy(css = "#pt-mycontris > a")
    private WebElement contributions;
    
    @FindBy(css = "#pt-createaccount > a")
    WebElement createAccount;
    
    @FindBy(css = "#pt-login > a")
    WebElement login;
    
    @FindBy(css = "#pt-preferences > a")
    WebElement preferences;
    
    @FindBy(css = "#ca-talk > span > a")
    WebElement discussion;
    
    @FindBy(css = "#p-personal")
    WebElement menuButton;
    
    public Navigation(WebDriver driver) {
        super(driver);
    }
    
    public TalkPage goToTalkPage() {
        clickMenuOption(talk);
        return new TalkPage(driver);
    }

    public ContributionsPage goToContributionsPage() {
        clickMenuOption(contributions);
        return new ContributionsPage(driver);
    }
    
    public CreateAccountPage goToCreateAccountPage() {
        clickMenuOption(createAccount);
        return new CreateAccountPage(driver);
    }
    
    public LoginPage goToLoginPage() {
        clickMenuOption(login);
        return new LoginPage(driver);
    }
    
    public PreferencesPage goToPreferencesPage() {
        clickMenuOption(preferences);
        return new PreferencesPage(driver);
    }
    
    public DiscussionPage goToDiscussionPage() {
        clickMenuOption(discussion);
        return new DiscussionPage(driver);
    }
    
    private void clickMenuOption(WebElement menuOption) {
        //menu hides the options -- responsive page
        if(! menuOption.isDisplayed()) {
            menuButton.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until( ExpectedConditions.elementToBeClickable(menuOption) );
        menuOption.click();
    }
    
}
