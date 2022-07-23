import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Aula1 {
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
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            //("\u001b[32m" + filme.get("image")+ "\u001b[37m");
            String titulo = filme.get("title");
            //("\u001b[36m" + filme.get("title") + "\u001b[0m");


           System.out.println(("\u001b[36m" + titulo + "\u001b[0m"));
           System.out.println(("\u001b[32m" + urlImagem + "\u001b[37m"));
           System.out.println("\u001b[31m" + filme.get("imDbRating")+ "\u001b[37m");
            System.out.println();    
    }
}
}

