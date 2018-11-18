/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.adolpho.po;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author Criação
 */
public class MainPage extends MediaWikiPage{
    
    public MainPage(WebDriver driver) {
        super(driver);
        driver.get("http://192.168.0.129/");
    }
    
}
