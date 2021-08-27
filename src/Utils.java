import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import models.TokenModel;



public class Utils {


    private static void write(final String s) {

        try(FileWriter fw = new FileWriter("out.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(s);
            //more code
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo de dados");
        }
    }


    public static String textoSemComentario(InputStream dadosBrutos)
    {
        String texto;
        try {
            texto = new String(dadosBrutos.readAllBytes());
            texto = texto.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "").replaceAll("\t","").replaceAll("\r\n", "");
            write(texto);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayList<TokenModel> recuperarTokens(String dados)
    {
        String[] SplitDados = dados.split(" ");
        ArrayList<TokenModel> retorno = new ArrayList<TokenModel>();

        for(int counter = 0; counter < SplitDados.length; counter++)
        {

            retorno.add();
        }

    }

    private TokenModel classificaToken(String Valor)
    {
        TokenModel RetToken;

        //verifica 
        return RetToken;
    }
    
}
