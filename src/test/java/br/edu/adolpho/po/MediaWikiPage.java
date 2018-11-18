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
public class MediaWikiPage extends BasePage{
    
    @FindBy(tagName = "h1")
    WebElement title;
    
    Navigation navi;    
    
    public MediaWikiPage(WebDriver driver) {
        super(driver);
        navi = new Navigation(driver);
    }
    
    public Navigation getNavigation() {
        return navi;
    }
    
    public String getTitle() {
        return title.getText();
    }    
    
}
