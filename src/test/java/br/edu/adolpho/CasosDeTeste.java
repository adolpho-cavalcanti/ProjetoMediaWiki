/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.adolpho;


import br.edu.adolpho.po.CreateAccountPage;
import br.edu.adolpho.po.ContributionsPage;
import br.edu.adolpho.po.DiscussionPage;
import br.edu.adolpho.po.ForgotPassword;
import br.edu.adolpho.po.InfoCreateAccountPage;
import br.edu.adolpho.po.LoginPage;
import br.edu.adolpho.po.Logout;
import br.edu.adolpho.po.MainPage;
import br.edu.adolpho.po.MediaFAQ;
import br.edu.adolpho.po.PreferencesPage;
import br.edu.adolpho.po.TalkPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Criação
 */
public class CasosDeTeste {
    
     
    private WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("lang=en-US");
        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);     
    }
    
    @After
    public void after() {
        driver.close();
    }    
    
    private void takeScreenShot() {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            int scId = 0;
            scId++;
            FileUtils.copyFile(sourceFile, new File("./res/" + scId + ".png"));
        } catch(IOException e) {}
    }
    
    @Test
    public void CT1testCriarConta() {
        MainPage mainPage = new MainPage(driver);
        
        CreateAccountPage criaConta = mainPage.getNavigation().goToCreateAccountPage();
                                                                                    //senha123
        CreateAccountPage infoCria = criaConta.setUser("elninocavalcanti10").setPass("pass12").setConfirmPass("pass12").setCreateYourAccount();
                    
        takeScreenShot();
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Welcome, Elninocavalcanti10!"));
    }
    
    @Test
    public void CT2testFazerLogin() {
        MainPage mainPage = new MainPage(driver);
       
        LoginPage login = mainPage.getNavigation().goToLoginPage().setUser("elninocavalcanti10").setPass("pass12").setLogin();
        
        assertThat(driver.findElement(By.cssSelector("#pt-userpage > a")).getText(),containsString("Elninocavalcanti10"));
        takeScreenShot();
        
    }   
    
    @Test
    public void CT3testFazerLogout() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage().setUser("elninocavalcanti10").setPass("pass12").setLogin();
        
        Logout logout = mainPage.getNavigation().goToLogout();
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Log out"));
        takeScreenShot();
        
    }    
    
    //Este teste faz uma assertiva informando que não exite vinculado a este usuário,
    //que anteriormente foi cadastrado sem uma conta de e-mail vinculada.
    @Test
    public void CT4EsqueceuAsenha() {
        MainPage mainPage = new MainPage(driver);
        takeScreenShot();
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        takeScreenShot();
        
        //Clica no link "Forgot your password?", depois seta os valores definidos na primeira fase do projeto
        ForgotPassword fgotPass = mainPage.getNavigation().goToForgotPassword()
                .setUser("elninocavalcanti10")
                .setEmail("acn2011@outlook.com")
                .setReset();
        
        takeScreenShot();
        assertThat(driver.findElement(By.cssSelector("#mw-content-text > div > form > div.mw-htmlform-ooui-header.mw-htmlform-ooui-header-errors.oo-ui-layout.oo-ui-fieldLayout.oo-ui-fieldLayout-align-top > ul > li > label"))
                .getText(),containsString("There is no email address recorded for user \"Elninocavalcanti10\"."));
        takeScreenShot();
        
    }  
    
    @Test
    public void CT5EditarTopicoConversa() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("pass12")
                .setLogin();
      
        TalkPage tp = mainPage.getNavigation().goToTalkPage()
                .setEditTopic()
                .setPreencheCampo("Futebol Total")
                .setChange()
                .setViewHistory();
               
        assertThat(driver.findElement(By.tagName("h1"))
                .getText(),containsString("Revision history of \"User talk:Elninocavalcanti10\""));

        takeScreenShot();
        
    } 
    
    @Test
    public void CT6MudarIdioma() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("pass12")
                .setLogin();
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage()
                .setCliqLang()
                .setSelecLangEsp()
                .setSaveLang();
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Preferencias")); 
        takeScreenShot();
       
//Voltando para o idioma inglês, como solicitado na correção da fase1 deste projeto.
        PreferencesPage VoltarIdiomaEnglish = mainPage.getNavigation().goToPreferencesPage()
                .setCliqLang()
                .setSelecLangEng()
                .setSaveLang();
       
        //assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Preferences")); 
       //takeScreenShot(); 
    } 
    
    //Este teste tem um bug de mensagem após fazer a inserção do email, ele retorna uma mensagem de erro,
    //porém, faz a inserção do e-mail, isso pode ser observado ao entrar em preferences com o usuário logado no sistema.
    @Test
    public void CT7InserirEmailContaCadastrada() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("pass12")
                .setLogin();
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage()
                .setSetEmail()
                .setPreencheEmail("acn2011@outlook.com")
                .setChangeEmail();
     
        assertThat(driver.findElement(By.cssSelector("#mw-changeemail-form > div.mw-htmlform-ooui-header.mw-htmlform-ooui-header-errors.oo-ui-layout.oo-ui-fieldLayout.oo-ui-fieldLayout-align-top > ul > li"))
                .getText(),containsString("Unknown error in PHP's mail() function."));
        takeScreenShot();
    } 
    
    @Test
    public void CT8CriarUmaDiscussão() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("pass12")
                .setLogin();
      
        DiscussionPage pp = mainPage.getNavigation().goToDiscussionPage()
                .setAddTema()
                .setPreencheTitulo("Temas novos")
                .setConteudo("Esse é o novo tema")
                .setSaveTema();
        
        assertThat(driver.findElement(By.cssSelector("#mw-content-text > div > p:nth-child(1)"))
               .getText(),containsString("Esse é o novo tema"));
        takeScreenShot();
    }
    
    
    
    //Este caso de teste requer trocar o xpath do que será oculto, pois uma vez que este teste passou,
    //o elemento está oculto. Caso queira fazer este teste passar, favor pegar o novo xpath dos itens 1 e 2 que
    //estão comentados na classe DiscussionPege.
    @Test
    public void CT9OcultarTemas() {
        MainPage mainPage = new MainPage(driver);
        
        //media FAQ
        MediaFAQ faq = mainPage.getNavigation().goToMediaFaq();
        takeScreenShot();
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("senha123")
                .setLogin();
      
        DiscussionPage dp = mainPage.getNavigation().goToDiscussionPage()
                .setTresPont().setOcultarTema().setMotivo("sem motivos").setaOcultar();
       
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Manual talk:FAQ"));
        takeScreenShot(); 
    }
    
    
    @Test
    public void CT10PesquisarContribuicoes() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("elninocavalcanti10")
                .setPass("pass12")
                .setLogin();
      
        ContributionsPage cp = mainPage.getNavigation().goToContributionsPage()
                .setPesqUser("elninocavalcanti10")
                .setSearch();
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("User contributions"));
        takeScreenShot();
    }
    
}
