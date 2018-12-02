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
public class PreferencesPage extends MediaWikiPage{
    
    @FindBy(xpath = "//*[@id=\"mw-input-wplanguage\"]")
    WebElement cliqLang;
    
    @FindBy(xpath = "//*[@id=\"mw-input-wplanguage\"]/option[99]")
    WebElement selecLangEsp;
    
    @FindBy(xpath = "//*[@id=\"prefcontrol\"]")
    WebElement saveLang;
    
    @FindBy(xpath = "//*[@id=\"mw-input-wplanguage\"]/option[95]")
    WebElement selecLangEng;
    
    //Os próximos referem-se ao caso de teste 7
    @FindBy(xpath = "//*[@id=\"mw-htmlform-email\"]/tbody/tr[1]/td[2]/a")
    WebElement setEmail;
    
    @FindBy(xpath = "//*[@id=\"ooui-php-1\"]")
    WebElement preencheEmail;
    
    @FindBy(xpath = "//*[@id=\"ooui-php-7\"]/button/span[2]")
    WebElement changeEmail;

    public PreferencesPage(WebDriver driver) {
        super(driver);
    }
    
    public PreferencesPage setCliqLang() {
        cliqLang.click();
        return new PreferencesPage(driver);
    }
    
    public PreferencesPage setSelecLangEsp() {
        selecLangEsp.click();
        return new PreferencesPage(driver);
    }
    
     public PreferencesPage setSaveLang(){
        saveLang.click();
        return new PreferencesPage(driver);
    }
     
    public PreferencesPage setSelecLangEng() {
        selecLangEng.click();
        return new PreferencesPage(driver);
    }
    
    public PreferencesPage setSetEmail() {
        setEmail.click();
        return new PreferencesPage(driver);
    }
    
     public PreferencesPage setPreencheEmail(String nomeEmail){
        preencheEmail.sendKeys(nomeEmail);
        return new PreferencesPage(driver);
    }
     
    public PreferencesPage setChangeEmail() {
        changeEmail.click();
        return new PreferencesPage(driver);
    }
}
