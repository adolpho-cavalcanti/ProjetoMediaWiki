/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.adolpho;


import br.edu.adolpho.po.CreateAccountPage;
import br.edu.adolpho.po.ContributionsPage;
import br.edu.adolpho.po.DiscussionPage;
import br.edu.adolpho.po.InfoCreateAccountPage;
import br.edu.adolpho.po.LoginPage;
import br.edu.adolpho.po.MainPage;
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

        CreateAccountPage infoCria = criaConta.setUser("santos18").setPass("pass12").setConfirmPass("pass12").setCreateYourAccount();
        
        takeScreenShot();
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Welcome, Santos18!"));
    }
    
    @Test
    public void CT2testFazerLogin() {
        MainPage mainPage = new MainPage(driver);
       
        LoginPage login = mainPage.getNavigation().goToLoginPage().setUser("santos11").setPass("pass12").setLogin();
        
        assertThat(driver.findElement(By.cssSelector("#pt-userpage > a")).getText(),containsString("Santos11"));
        takeScreenShot();
        
    }    
    
    @Test
    public void CT3testFazerLogout() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage().setUser("santos11").setPass("pass12").setLogin();
        
        driver.findElement(By.xpath("//*[@id=\"pt-logout\"]/a")).click();
        
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
        //Clica no link "Forgot your password?"
        driver.findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div/div[6]/div/a")).click();
        takeScreenShot();
        //Preenche o campo com nome de usuário e fornece um email para recuperação de senha
        driver.findElement(By.xpath("//*[@id=\"ooui-php-1\"]")).sendKeys("santos11");
        driver.findElement(By.xpath("//*[@id=\"ooui-php-2\"]")).sendKeys("adolphon@alunos.utfpr.edu.br");
        takeScreenShot();
        //Clica no botão reset password
        driver.findElement(By.xpath("//*[@id=\"ooui-php-5\"]/button/span[2]")).click();
        takeScreenShot();
        assertThat(driver.findElement(By.cssSelector("#mw-content-text > div > form > div.mw-htmlform-ooui-header.mw-htmlform-ooui-header-errors.oo-ui-layout.oo-ui-fieldLayout.oo-ui-fieldLayout-align-top > ul > li > label"))
                .getText(),containsString("There is no email address recorded for user \"Santos11\"."));
        takeScreenShot();
        
    }
    
    @Test
    public void CT5EditarTopicoConversa() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("santos11")
                .setPass("pass12")
                .setLogin();
      
        TalkPage tp = mainPage.getNavigation().goToTalkPage();
        
        //clicar em edit
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/h2/span[2]/a")).click();
        //preencher com futebol total
        driver.findElement(By.xpath("//*[@id=\"wpTextbox1\"]")).sendKeys("== Futebol Total ==");
        //save
        driver.findElement(By.xpath("//*[@id=\"wpSave\"]")).click();
        
        assertThat(driver.findElement(By.cssSelector("#Futebol_Total")).getText(),containsString("Futebol Total"));

        takeScreenShot();
        
    }
    
    @Test
    public void CT6MudarIdioma() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("santos11")
                .setPass("pass12")
                .setLogin();
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage();
        
        //clicar em language
        driver.findElement(By.xpath("//*[@id=\"mw-input-wplanguage\"]")).click();
        //Selecionar língua espanhola
        driver.findElement(By.xpath("//*[@id=\"mw-input-wplanguage\"]/option[99]")).click();
        
        //save
        driver.findElement(By.xpath("//*[@id=\"prefcontrol\"]")).click();
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Preferencias"));
        takeScreenShot();
    }
    
    //Este teste tem um bug de mensagem após fazer a inserção do email, ele retorna uma mensagem de erro,
    //porém, faz a inserção do e-mail, isso pode ser observado ao entrar em preferences com o usuário logado no sistema.
    @Test
    public void CT7InserirEmailContaCadastrada() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("santos17")
                .setPass("pass12")
                .setLogin();
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage();
        
        //Set an email address
        driver.findElement(By.xpath("//*[@id=\"mw-htmlform-email\"]/tbody/tr[1]/td[2]/a")).click();
        //Preencher o campo com o email
        
        driver.findElement(By.xpath("//*[@id=\"ooui-php-1\"]")).sendKeys("adolphon@alunos.utfpr.edu.br");
        
        //Change email
        driver.findElement(By.xpath("//*[@id=\"ooui-php-7\"]/button/span[2]")).click();
        
        assertThat(driver.findElement(By.cssSelector("#mw-changeemail-form > div.mw-htmlform-ooui-header.mw-htmlform-ooui-header-errors.oo-ui-layout.oo-ui-fieldLayout.oo-ui-fieldLayout-align-top > ul > li"))
                .getText(),containsString("Unknown error in PHP's mail() function."));
        takeScreenShot();
    }
    
    @Test
    public void CT8CriarUmaDiscussão() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("santos12")
                .setPass("pass12")
                .setLogin();
      
        DiscussionPage pp = mainPage.getNavigation().goToDiscussionPage();
        
        //Adicionar Tema
        driver.findElement(By.xpath("//*[@id=\"ca-addsection\"]/span/a")).click();
        //Preencher o título
        driver.findElement(By.xpath("//*[@id=\"wpSummary\"]")).sendKeys("Temas novos");
        
        driver.findElement(By.xpath("//*[@id=\"wpTextbox1\"]")).sendKeys("Temas novos conteudo");
        
        driver.findElement(By.xpath("//*[@id=\"wpSave\"]")).click();
        
        
        assertThat(driver.findElement(By.cssSelector("#Temas_novos"))
               .getText(),containsString("Temas novos"));
        takeScreenShot();
    }
    
    //Este caso de teste requer trocar o xpath do que será oculto, pois uma vez que este teste passou,
    //o elemento está oculto. Caso queira fazer este teste passar, favor pegar o novo xpath dos itens 1 e 2 que
    //estão comentados.
    @Test
    public void CT9Ocultar() {
        MainPage mainPage = new MainPage(driver);
        
        //media FAQ
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/ul/li[2]/a")).click();
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("utfpr")
                .setPass("pass12")
                .setLogin();
      
        DiscussionPage dp = mainPage.getNavigation().goToDiscussionPage();
       
        //Item 1- clicar no 3 pontinhos ...
        driver.findElement(By.xpath("//*[@id=\"flow-topic-ucb0syjm6ogxxkle\"]/div[1]/div[4]/div/a/span")).click();
            
        //Item 2- clicar em ocultar tema
        driver.findElement(By.xpath("//*[@id=\"flow-topic-ucb0syjm6ogxxkle\"]/div[1]/div[4]/ul/section[2]/li/a"))
                .click(); 
        //motivo de ocultar tema
        driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/form/input[2]"))
                .sendKeys("sem motivos");
        //clicar botão ocultar
        driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/form/div[2]/button")).click();
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("Manual talk:FAQ"));
        takeScreenShot();
    }
    
    @Test
    public void CT10PesquisarContribuicoes() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage()
                .setUser("santos13")
                .setPass("pass12")
                .setLogin();
      
        ContributionsPage cp = mainPage.getNavigation().goToContributionsPage();
        
        //Pesquisar usuário
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/form/fieldset/div[1]/input[3]"))
                .clear();
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/form/fieldset/div[1]/input[3]"))
                .sendKeys("Santos11");
        //clicar botão search
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/form/fieldset/div[6]/input")).click();
        
        
        assertThat(driver.findElement(By.tagName("h1")).getText(),containsString("User contributions"));
        takeScreenShot();
    }
    
}
