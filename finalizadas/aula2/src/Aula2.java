import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Aula2 {
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão https e buscar os top 250 filmes 
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereço = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereço).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
 
        // pegar so os dados que interessam (titulo,poster,classificação)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados  
        //for (Map<String,String> filme : listaDeFilmes) {

            var geradora = new GeradoraDeFigurinhas2();
            for (int i = 0; i < 10; i++) {
               Map<String,String> filme = listaDeFilmes.get(i);

               String urlImagem = filme.get("image")
               .replaceAll("(@+) (.*).jpg$", "$1.jpg");
              
               String titulo = filme.get("title");
               
               InputStream inputStream = new URL (urlImagem).openStream();
               String nomeArquivo = "../ALURA-STICKERS/finalizadas/aula2/saida2/" + titulo + ".png";

               geradora.cria(inputStream, nomeArquivo);

               System.out.println(("\u001b[36m" + titulo + "\u001b[0m"));
               //System.out.println(("\u001b[32m" + urlImagem + "\u001b[37m"));
               //System.out.println("\u001b[31m" + filme.get("imDbRating")+ "\u001b[37m");
                System.out.println();

            }       
        //}
    }
}

//for (Map<String,String> filme : listaDeFilmes) {

    //String urlImagem = filme.get("image");
    //("\u001b[32m" + filme.get("image")+ "\u001b[37m");
    //String titulo = filme.get("title");
    //("\u001b[36m" + filme.get("title") + "\u001b[0m");
    //String nomeArquivo = titulo + ".png";

   //InputStream inputStream = new URL (urlImagem).openStream();

   //var geradora = new GeradoraDeFigurinhas();
    //geradora.cria(inputStream, nomeArquivo);
