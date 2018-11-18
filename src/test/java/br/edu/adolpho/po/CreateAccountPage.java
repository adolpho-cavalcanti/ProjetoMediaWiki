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
public class CreateAccountPage extends MediaWikiPage{

    @FindBy(xpath = "//*[@id=\"wpName2\"]")
    WebElement user;
    
    @FindBy(xpath = "//*[@id=\"wpPassword2\"]")
    WebElement pass;
    
    @FindBy(xpath = "//*[@id=\"wpRetype\"]")
    WebElement confirmPass;
    
    @FindBy(xpath = "//*[@id=\"wpCreateaccount\"]")
    WebElement createYourAccount;
    
    
    
    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }
    
    public CreateAccountPage setUser(String userName) {
        user.clear();
        user.sendKeys(userName);
        return new CreateAccountPage(driver);
    }
    
    public CreateAccountPage setPass(String passName) {
        pass.clear();
        pass.sendKeys(passName);
        return new CreateAccountPage(driver);
    }
    
    public CreateAccountPage setConfirmPass(String confirmPassName) {
        confirmPass.clear();
        confirmPass.sendKeys(confirmPassName);
        return new CreateAccountPage(driver);
    }
    
    public CreateAccountPage setCreateYourAccount() {
        createYourAccount.click();
        return new CreateAccountPage(driver);
    }
    
}
