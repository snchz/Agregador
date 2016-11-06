import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Agregador{
	
	/**
	 * (Para obtener los xpath puede usarse el inspeccionar elemento de Chrome.)
	 * 
	 * @param urlLogin
	 * @param xpathBoton
	 * @param xpathUsuario
	 * @param xpatPassword
	 * @param sUsuario
	 * @param sPassword
	 * @return
	 */
	public void login(String urlLogin, String xpathBoton, String xpathUsuario, String xpathPassword, String sUsuario,
			String sPassword) {
		try {
			HtmlUnitDriver driver = new HtmlUnitDriver(true);
			driver.get(urlLogin);
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			System.out.println("Titulo de la pagina de login: " + driver.getTitle());
			Util.visualizarPaginaEnNavegador(driver.getPageSource());

			driver.findElement(By.xpath(xpathUsuario)).sendKeys(sUsuario);
			
			//Bucle para habilitar cookies, si no están habilitadas se vuelve a ejecutar
			//Si se vuelve a jecutar y sigue fallando se deja de intentar
			boolean bucle=true;
			int intentos=0;
			while(bucle){
				intentos++;
				driver.findElement(By.xpath(xpathPassword)).sendKeys(sPassword);
				driver.findElement(By.xpath(xpathBoton)).click(); 
				Thread.sleep(2000);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				if (!driver.getPageSource().contains("Cookies Required") || intentos==2)
					bucle=false;
				System.out.println("Titulo de la pagina tras login: " + driver.getTitle());
				Util.visualizarPaginaEnNavegador(driver.getPageSource());
			}
		} catch (Exception e) {
			System.err.println("Error en la funcion login: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Agregador a = new Agregador();
		String urlLogin, xpathBoton, xpathUsuario, xpathPassword, sUsuario, sPassword;

		Properties prop = new Properties();
		
		String file="";
		if (args.length>0)
			file=args[0];
		else
			file=System.console().readLine();
		InputStream is=new FileInputStream(file);
		prop.load(is);

		urlLogin=prop.getProperty("urlLogin");
		xpathBoton=prop.getProperty("xpathBoton");
		xpathUsuario=prop.getProperty("xpathUsuario");
		xpathPassword=prop.getProperty("xpathPassword");
		sUsuario=prop.getProperty("sUsuario");
		sPassword=prop.getProperty("sPassword");

		a.login(urlLogin, xpathBoton, xpathUsuario, xpathPassword, sUsuario, sPassword);
	}
}
