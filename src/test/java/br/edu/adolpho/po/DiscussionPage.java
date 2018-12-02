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
public class DiscussionPage extends MediaWikiPage{
    
    @FindBy(xpath = "//*[@id=\"ca-addsection\"]/span/a")
    WebElement addTema;
    
    @FindBy(xpath = "//*[@id=\"wpSummary\"]")
    WebElement preencheTitulo;
    
    @FindBy(xpath = "//*[@id=\"wpTextbox1\"]")
    WebElement conteudo;
    
    @FindBy(xpath = "//*[@id=\"wpSave\"]")
    WebElement saveTema;
    
    //Abaixo referem-se ao caso de teste 9
    //Item 1- clicar no 3 pontinhos ...
    @FindBy(xpath = "//*[@id=\"flow-topic-um3ehbia5ib9sbbv\"]/div[1]/div[4]/div/a/span")
    WebElement tresPont;
    
    //Item 2- clicar em ocultar tema
    @FindBy(xpath = "//*[@id=\"flow-topic-ucb0syjm6ogxxkle\"]/div[1]/div[4]/ul/section[2]/li/a")
    WebElement ocultarTema;
    
    @FindBy(xpath = "/html/body/div[8]/div/div[2]/form/input[2]")
    WebElement motivo;
    
    @FindBy(xpath = "/html/body/div[8]/div/div[2]/form/div[2]/button")
    WebElement ocultar;

    public DiscussionPage(WebDriver driver) {
        super(driver);
    }
    
    public DiscussionPage setAddTema() {
        addTema.click();
        return new DiscussionPage(driver);
    }
    
    public DiscussionPage setPreencheTitulo(String pTitulo) {
        preencheTitulo.clear();
        preencheTitulo.sendKeys(pTitulo);
        return new DiscussionPage(driver);
    }
    
    public DiscussionPage setConteudo(String cont) {
        conteudo.sendKeys(cont);
        return new DiscussionPage(driver);
    }
    
     public DiscussionPage setSaveTema() {
        saveTema.click();
        return new DiscussionPage(driver);
    }
    
    public DiscussionPage setTresPont() {
        tresPont.click();
        return new DiscussionPage(driver);
    }
    
    public DiscussionPage setOcultarTema() {
        ocultarTema.click();
        return new DiscussionPage(driver);
    }
    
    public DiscussionPage setMotivo(String nomeMotivo) {
        motivo.sendKeys(nomeMotivo);
        return new DiscussionPage(driver);
    }
    
     public DiscussionPage setaOcultar() {
        ocultar.click();
        return new DiscussionPage(driver);
    }
    
}
