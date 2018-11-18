/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.adolpho.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Criação
 */
public class InfoCreateAccountPage extends MediaWikiPage{
    
    @FindBy(xpath = "//*[@id=\"wpName2\"]")
    WebElement user;
    
    @FindBy(xpath = "//*[@id=\"wpPassword2\"]")
    WebElement pass;
    
    @FindBy(xpath = "//*[@id=\"wpRetype\"]")
    WebElement confirmPass;
    
    @FindBy(xpath = "//*[@id=\"wpCreateaccount\"]")
    WebElement createYourAccount;
    
    public InfoCreateAccountPage(WebDriver driver) {
        super(driver);
    }
    
    public String getUser() {
        return user.getText();
    }
    
    public String getPass() {
        return pass.getText();
    }
    
    public String getConfirmPass() {
        return confirmPass.getText();
    }
    
    public CreateAccountPage clickCreateYourAccount() {
        createYourAccount.click();
        return new CreateAccountPage(driver);
    }
}
