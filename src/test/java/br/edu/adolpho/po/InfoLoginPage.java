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
public class InfoLoginPage extends MediaWikiPage{
    @FindBy(css = "#wpName1")
    WebElement user;
    
    @FindBy(css = "#wpPassword1")
    WebElement pass;
    
    @FindBy(css = "#wpLoginAttempt")
    WebElement login;

    public InfoLoginPage(WebDriver driver){
        super(driver);
    }
    
    public String getUser() {
        return user.getText();
    }
    
    public String getPass() {
        return pass.getText();
    }
    
    public LoginPage clickLogin(){
        login.click();
        return new LoginPage(driver);
    }

}
