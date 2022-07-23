import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Aula3{
    public static void main(String[] args) throws Exception {
        
        // fazer uma conexão https e buscar os top 250 filmes 
        //String url = "https://alura-imdb-api.herokuapp.com/movies";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // nasa 
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscadados(url);

        // exibir e manipular os dados  
            
            List<Conteudo> conteudos = extrator.extrairConteudos(json);

            var geradora = new GeradoraDeFigurinhas();
            for (int i = 0; i < 3; i++) {
               Conteudo conteudo = conteudos.get(i);
               
               InputStream inputStream = new URL (conteudo.getUrlImagem()).openStream();
               String nomeArquivo = "../ALURA-STICKERS/finalizadas/aula3/saida3/"+conteudo.getTitulo()+".png";

               geradora.cria(inputStream, nomeArquivo);

               System.out.println(("\u001b[36m" + conteudo.getTitulo() + "\u001b[0m"));
               System.out.println(("\u001b[32m" + conteudo.getUrlImagem() + "\u001b[37m"));
               //System.out.println("\u001b[31m" + filme.get("imDbRating")+ "\u001b[37m");
                System.out.println();

            }       
    }
}