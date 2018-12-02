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
public class ContributionsPage extends MediaWikiPage{

    @FindBy(xpath = "//*[@id=\"mw-content-text\"]/form/fieldset/div[1]/input[3]")
    WebElement pesqUser;
    
    @FindBy(xpath = "//*[@id=\"mw-content-text\"]/form/fieldset/div[6]/input")
    WebElement search;

    public ContributionsPage(WebDriver driver) {
        super(driver);
    }
    
    public ContributionsPage setPesqUser(String userName) {
        pesqUser.clear();
        pesqUser.sendKeys(userName);
        return new ContributionsPage(driver);
    }
    
     public ContributionsPage setSearch() {
        search.click();
        return new ContributionsPage(driver);
    }
    
}
