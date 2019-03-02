import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class ResultadoMegasena {

	private final static String URL = "http://www1.caixa.gov.br/_newincludes/home_2011/resultado_megasena.asp";

	private final static String MARCA_INICIAL_RETORNO_NAO_UTIL = "<div id='concurso_resultado'>";

	private final static String MARCA_FINAL_RETORNO_NAO_UTIL = "</div>";

	public static String[] obtemUltimoResultado() {
		// Cria��o do cliente HTTP que far� a conex�o com o site
		HttpClient httpclient = new DefaultHttpClient();
		try {
			// Defini��o da URL a ser utilizada
			HttpGet httpget = new HttpGet(URL);
			// Manipulador da resposta da conex�o com a URL
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// Resposta propriamente dita
			String html = httpclient.execute(httpget, responseHandler);
			// Retorno das dezenas, ap�s tratamento
			return obterDezenas(html);
		} catch (Exception e) {
			// Caso haja erro, dispara exce��o.
			throw new RuntimeException("Um erro inesperado ocorreu.", e);
		} finally {
			// Destrui��o do cliente para libera��o dos recursos do sistema.
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	private static String[] obterDezenas(String html) {
        // Posi��o inicial de onde come�am as dezenas
        Integer parteInicial = html.indexOf(MARCA_INICIAL_RETORNO_NAO_UTIL) + MARCA_INICIAL_RETORNO_NAO_UTIL.length();
        // Posi��o final de onde come�am as dezenas
        Integer parteFinal = html.indexOf(MARCA_FINAL_RETORNO_NAO_UTIL);
        // Substring montada com base nas posi��es, com remo��o de espa�os.
        String extracao = html.substring(parteInicial, parteFinal).replaceAll(" ", "");
        // Cria��o de array, com base no m�todo split(), separando por hifen.
        String[] numeros = extracao.split("-");
        return numeros;
    }

}
