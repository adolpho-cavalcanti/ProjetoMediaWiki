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
public class TalkPage extends MediaWikiPage{

    @FindBy(xpath = "//*[@id=\"ca-edit\"]/span/a")
    WebElement editTopic;
    
    @FindBy(xpath = "//*[@id=\"wpTextbox1\"]")
    WebElement preencheCampo;
    
    @FindBy(xpath = "//*[@id=\"wpSave\"]")
    WebElement change;
    
    @FindBy(xpath = "//*[@id=\"ca-history\"]/span/a")
    WebElement viewHistory;

    public TalkPage (WebDriver driver) {
        super(driver);
    }
    
    public TalkPage setEditTopic() {
        editTopic.click();
        return new TalkPage(driver);
    }
    
    public TalkPage setPreencheCampo(String campo) {
        preencheCampo.clear();
        preencheCampo.sendKeys(campo);
        return new TalkPage(driver);
    }
    
     public TalkPage setChange() {
        change.click();
        return new TalkPage(driver);
    }
     
    public TalkPage setViewHistory() {
        viewHistory.click();
        return new TalkPage(driver);
    }
   
}
