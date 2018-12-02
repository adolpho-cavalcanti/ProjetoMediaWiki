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
public class ForgotPassword extends MediaWikiPage {
    
    @FindBy(xpath = "//*[@id=\"ooui-php-1\"]")
    WebElement user;
    
    @FindBy(xpath = "//*[@id=\"ooui-php-2\"]")
    WebElement email;
    
    @FindBy(xpath = "//*[@id=\"ooui-php-5\"]/button/span[2]")
    WebElement reset;
    
    public ForgotPassword(WebDriver driver) {
        super(driver);
    }
    
    public ForgotPassword setUser(String userName) {
        user.clear();
        user.sendKeys(userName);
        return new ForgotPassword(driver);
    }
    
    public ForgotPassword setEmail(String emailName) {
        email.clear();
        email.sendKeys(emailName);
        return new ForgotPassword(driver);
    }
    
     public ForgotPassword setReset() {
        reset.click();
        return new ForgotPassword(driver);
    }
     
}
