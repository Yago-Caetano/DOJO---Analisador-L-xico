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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.ComparadorPalavraReservadas;
import models.PalavraReservadaModel;
import models.TokenModel;



public class Utils {

    public static int Colunas;

    public static void write(final ArrayList<TokenModel> s) {

        try(FileWriter fw = new FileWriter("out.lex", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            for(TokenModel tk : s)
            {
                out.println(tk.toString());
            }
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
        Colunas = 0;

        String[] SplitDados = dados.split(" ");
        ArrayList<TokenModel> retorno = new ArrayList<TokenModel>();


        //token
        ArrayList<PalavraReservadaModel> tokens = Reservados.getPalavrasReservadas();
        tokens.sort(new ComparadorPalavraReservadas());


        for(int counter = 0; counter < SplitDados.length; counter++)
        {
            verificaIndiceListaReservado(tokens,retorno,SplitDados[counter]);
        }

        return retorno;
    }

    private static void verificaIndiceListaReservado(ArrayList<PalavraReservadaModel> listaReservados,ArrayList<TokenModel> listaRetorno,String valor)
    {
        
        
         int indexTokenPalavraReservada = Collections.binarySearch(listaReservados,new PalavraReservadaModel(valor, ""));

            if(indexTokenPalavraReservada>=0)
            {
                listaRetorno.add(new TokenModel(listaReservados.get(indexTokenPalavraReservada).getValor(),listaReservados.get(indexTokenPalavraReservada).getTipo(),Colunas++));
            }
            else
            {
                boolean Flag_Encontrou_Reservado = false;
                
                for(int i = 0; i < Reservados.MascaraDeComparacao.length; i++)
                {
                    int indice = valor.indexOf(Reservados.MascaraDeComparacao[i]);
                    if(indice >=0)
                    {
                        String subvalor = "";
                        if(indice == valor.length()-1)
                        {
                            subvalor = valor.substring(0,indice);
                            verificaIndiceListaReservado(listaReservados,listaRetorno,subvalor);
                        
                            listaRetorno.add(new TokenModel(Reservados.MascaraDeComparacao[i], "Simbolo",Colunas++));

                        }
                        else if(indice == 0)
                        {
                            listaRetorno.add(new TokenModel(Reservados.MascaraDeComparacao[i], "Simbolo",Colunas++));
                            subvalor = valor.substring(1);
                            verificaIndiceListaReservado(listaReservados,listaRetorno,subvalor);

                        }
                        else
                        {
                            subvalor = valor.substring(0, indice);
                            verificaIndiceListaReservado(listaReservados,listaRetorno,subvalor);
                        
                            listaRetorno.add(new TokenModel(Reservados.MascaraDeComparacao[i], "Simbolo",Colunas++));
                            
                            subvalor = valor.substring(indice+1);
                            verificaIndiceListaReservado(listaReservados,listaRetorno,subvalor);
                        }

                        Flag_Encontrou_Reservado = true;
                        break;
                    }

  
                }
                if(!Flag_Encontrou_Reservado)
                {
                    Pattern p =  Pattern.compile("[0-9]+");

                    Matcher m = p.matcher(valor);

                    while(m.find())
                    {
                        try {
                            Integer.parseInt(valor);
                            listaRetorno.add(new TokenModel(valor, "Numero",Colunas++));
                            return;
                        } catch (Exception e) {

                        }
                    }


                    //verificar float 
                    p = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");

                    m = p.matcher(valor);

                    while(m.find())
                    {
                        try{
                            Double.parseDouble(valor);
                            listaRetorno.add(new TokenModel(valor, "Numero",Colunas++));
                            return;
                        }
                        catch(Exception e)
                        {

                        }
                    }


                    listaRetorno.add(new TokenModel(valor, "Variavel",Colunas++));

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
