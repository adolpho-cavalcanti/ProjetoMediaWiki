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
public class LoginPage extends MediaWikiPage{
    
    @FindBy(css = "#wpName1")
    WebElement user;
    
    @FindBy(css = "#wpPassword1")
    WebElement pass;
    
    @FindBy(css = "#wpLoginAttempt")
    WebElement login;
    
    @FindBy(css = "#pt-userpage > a")
    WebElement assertiva;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public LoginPage setUser(String userName) {
        user.clear();
        user.sendKeys(userName);
        return new LoginPage(driver);
    }
    
    public LoginPage setPass(String passName) {
        pass.clear();
        pass.sendKeys(passName);
        return new LoginPage(driver);
    }
    
     public LoginPage setLogin() {
        login.click();
        return new LoginPage(driver);
    }
     
    public String getAssertiva() {
        return assertiva.getText();
    }
    
}
