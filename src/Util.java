import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	/**
	 * Guarda a fichero.
	 * @param htmlBody
	 */
	public static void visualizarPaginaEnNavegador(String htmlBody) {
		try {
			Thread.sleep(1001);

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Calendar cal = Calendar.getInstance();
			String fecha = dateFormat.format(cal.getTime());

			String path = Paths.get("out").toAbsolutePath().normalize().toString();

			File fichero = new File(path + "\\" + fecha + "_out.html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
			bw.write(htmlBody);
			// String pathFichero=fichero.getAbsolutePath();
			bw.close();

			// String cmd = System.getenv("ProgramFiles") + "\\Internet Explorer\\iexplore.exe "+pathFichero;
			// Runtime.getRuntime().exec(cmd);
		} catch (InterruptedException e) {
			System.err.println("Error al realizar el sleep: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error al guardar a fichero: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
