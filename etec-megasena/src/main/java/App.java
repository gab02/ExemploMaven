
public class App {
	public static void main(String[] args) {
		String [] resultado = ResultadoMegasena.obtemUltimoResultado();
		for (String string : resultado) {
			System.out.println(string + "");
		}
	}
	
}
