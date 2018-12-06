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
    
    
    @Test
    public void CT1testCriarConta() {
        MainPage mainPage = new MainPage(driver);
        
        CreateAccountPage criaConta = mainPage.getNavigation().goToCreateAccountPage();
     
        CreateAccountPage cria = criaConta.setUser("elninocavalcanti10")
                                          .setPass("senha123")
                                          .setConfirmPass("senha123")
                                          .setCreateYourAccount();
                                        
                    
        assertEquals("Welcome, Elninocavalcanti10!", criaConta.getTitle());
        
    } 
     
    @Test
    public void CT2testFazerLogin() {
        MainPage mainPage = new MainPage(driver);
       
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        
        assertEquals("Elninocavalcanti10", login.getAssertiva());
        
    }   
    
    @Test
    public void CT3testFazerLogout() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
        
        Logout logout = mainPage.getNavigation().goToLogout();
        
        assertEquals("Log out", logout.getTitle());
        
        
    }    
    
   
    //Este teste faz uma assertiva informando que não exite vinculado a este usuário,
    //que anteriormente foi cadastrado sem uma conta de e-mail vinculada.
    @Test
    public void CT4EsqueceuAsenha() {
        MainPage mainPage = new MainPage(driver);
       
        LoginPage login = mainPage.getNavigation().goToLoginPage();      
        
        //Clica no link "Forgot your password?", depois seta os valores definidos na primeira fase do projeto
        ForgotPassword fgotPass = login.getNavigation().goToForgotPassword();
        
        ForgotPassword esqueceu = fgotPass.setUser("elninocavalcanti34")
                                          .setEmail("acn2011@outlook.com")
                                          .setReset();
        
        assertEquals("Reset password", fgotPass.getTitle());                
        
    }   
    
    //Lembrando que para editar o tópico é necessário ter criado o tópico previamente.
    @Test
    public void CT5EditarTopicoConversa() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
      
        TalkPage tp = mainPage.getNavigation().goToTalkPage();
        
        TalkPage editar = tp.setEditTopic()
                            .setPreencheCampo("Futebol Total")
                            .setChange()
                            .setViewHistory();
        
        assertEquals("Revision history of \"User talk:Elninocavalcanti10\"", tp.getTitle());       
        
    } 
    
    @Test
    public void CT6MudarIdioma() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage();
        
        PreferencesPage mudarIdioma = pp.setCliqLang()
                                        .setSelecLangEsp()
                                        .setSaveLang();
        
        assertEquals("Preferencias", pp.getTitle());
        
       
//Voltando para o idioma inglês, como solicitado na correção da fase1 deste projeto.
        PreferencesPage VoltarIdiomaEnglish = mainPage.getNavigation().goToPreferencesPage();
        
        PreferencesPage ingles = VoltarIdiomaEnglish.setCliqLang()
                                                    .setSelecLangEng()
                                                    .setSaveLang();
        
        assertEquals("Preferences", pp.getTitle());
        
    } 
    
    //Este teste tem um bug de mensagem após fazer a inserção do email, ele retorna uma mensagem de erro,
    //porém, faz a inserção do e-mail, isso pode ser observado ao entrar em preferences com o usuário logado no sistema.
    @Test
    public void CT7InserirEmailContaCadastrada() {
        MainPage mainPage = new MainPage(driver);
        
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
      
        PreferencesPage pp = mainPage.getNavigation().goToPreferencesPage();
        
        PreferencesPage inserirEmail = pp.setSetEmail()
                                        .setPreencheEmail("acn2011@outlook.com")
                                        .setChangeEmail();
     
        assertEquals("Unknown error in PHP's mail() function.", pp.getAssertivaPP());
        
    }
    
    @Test
    public void CT8CriarUmaDiscussão() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
      
        DiscussionPage dp = mainPage.getNavigation().goToDiscussionPage();
         
        DiscussionPage criaDisc = dp.setAddTema()
                                    .setPreencheTitulo("Temas novos")
                                    .setConteudo("Esse é o novo tema")
                                    .setSaveTema();
        
        assertEquals("Talk:Main Page", dp.getTitle());
        
    }
    
    
    /*
    //Como acordado com o professor, este caso de teste CT09OcultarTemas foi trocado, pelo fato do mesmo acessar mediawiki.org
    //Fui banido por vandalismo, segundo o mediawiki.org
    //não tinha notado quando criei este CT na Fase1 do projeto
    //que o mesmo acessava o mediawiki.org, e como ocultei muitos temas fui banido.
    //A solução seria criar outro usuário no mediawiki.org para realzar este teste.
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
                                .setTresPont()
                                .setOcultarTema()
                                .setMotivo("sem motivos")
                                .setaOcultar();
       
        assertEquals("Manual talk:FAQ", dp.getTitle());
         
    }*/
    
    @Test
    public void CT9ErroAoFazerLogin() {
        MainPage mainPage = new MainPage(driver);
       
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("messi123")
                               .setPass("senha1")
                               .setLogin();
        
        assertEquals("Incorrect username or password entered. Please try again.", login.getErroLogar());
        
        
    }   
    
   
    @Test
    public void CT10PesquisarContribuicoes() {
        MainPage mainPage = new MainPage(driver);
        
        LoginPage login = mainPage.getNavigation().goToLoginPage();
        
        LoginPage logar = login.setUser("elninocavalcanti10")
                               .setPass("senha123")
                               .setLogin();
        assertEquals("Elninocavalcanti10", login.getAssertiva());
      
        ContributionsPage cp = mainPage.getNavigation().goToContributionsPage();
         
        ContributionsPage pesquisar = cp.setPesqUser("elninocavalcanti10")
                                        .setSearch();
        
        assertEquals("User contributions", cp.getTitle());
        
    }
    
}
