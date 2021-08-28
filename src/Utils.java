import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import models.ComparadorPalavraReservadas;
import models.PalavraReservadaModel;
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
        String texto = "";
        try {
            texto = new String(dadosBrutos.readAllBytes());
            texto = texto.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "").replaceAll("\t","").replaceAll("\r\n", "");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texto;
    }

    public static ArrayList<TokenModel> recuperarTokens(String dados)
    {
        String[] SplitDados = dados.split(" ");
        ArrayList<TokenModel> retorno = new ArrayList<TokenModel>();

        ArrayList<TokenModel> palarvrasIgnoradas = new ArrayList<TokenModel>();


        //token
        ArrayList<PalavraReservadaModel> tokens = Reservados.getPalavrasReservadas();
        tokens.sort(new ComparadorPalavraReservadas());


        for(int counter = 0; counter < SplitDados.length; counter++)
        {
            verificaIndiceListaReservado(tokens,retorno,SplitDados[counter],palarvrasIgnoradas);
        }

        return retorno;
    }

    private static void verificaIndiceListaReservado(ArrayList<PalavraReservadaModel> listaReservados,ArrayList<TokenModel> listaRetorno,String valor,ArrayList<TokenModel> ListaTemp)
    {
         

         int indexTokenPalavraReservada = Collections.binarySearch(listaReservados,new PalavraReservadaModel(valor, ""));

            if(indexTokenPalavraReservada>=0)
            {
                listaRetorno.add(new TokenModel(listaReservados.get(indexTokenPalavraReservada).getValor(),listaReservados.get(indexTokenPalavraReservada).getTipo()));
            }
            else
            {
                boolean Flag_Encontrou_Reservado = false;

                for(int i = 0; i < Reservados.Simbolos.length; i++)
                {
                    int indice = valor.indexOf(Reservados.Simbolos[i]);
                    if(indice >=0)
                    {
                        String subvalor = "";
                        if(indice == valor.length()-1)
                            subvalor = valor.substring(0,indice);
                        else
                        subvalor = valor.substring(1);

                        verificaIndiceListaReservado(listaReservados,listaRetorno,subvalor,ListaTemp);

                        listaRetorno.add(new TokenModel(Reservados.Simbolos[i], "Simbolo"));
                        Flag_Encontrou_Reservado = true;
                        break;
                    }

  
                }
                if(!Flag_Encontrou_Reservado)
                {
                    ListaTemp.add(new TokenModel(valor, "Variavel"));
                }
                
            }
    }

    /*private TokenModel classificaToken(String Valor)
    {
        TokenModel RetToken;

        //verifica 
        return RetToken;
    }*/
    
}
