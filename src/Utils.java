import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;



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



    public static ArrayList<Integer> textoSemComentarios(InputStream dadosBrutos)
    {
        /*comentario simples
            comeca com // e termina com \r\n
        */
        boolean EncontrouInicio = false;
        boolean EncontrouFim = false;

        boolean EncontrouBarra = false;
        boolean ComentarioComposto = false;
        boolean Ignorar = false;

        StringBuffer buffer = new StringBuffer();
       
        ArrayList<Integer> ReturnList = new ArrayList<Integer>();

        try{
        
            int data = dadosBrutos.read();
        
            while (data != -1) {


                    if(Ignorar)
                    {
                        if(ComentarioComposto)
                        {
                            
                            if(EncontrouFim && data != '/')
                                EncontrouFim = false;

                            if(data == '/' && EncontrouFim)
                            {
                                Ignorar = false;
                                EncontrouFim = false;
                                ComentarioComposto = false;
                                EncontrouBarra = false;

                            }

                            if(data == '*' && !EncontrouFim)
                            {
                                EncontrouFim = true;
                            }

                        }
                        else
                        {
                            //terminou comentario de uma linha
                            if(data == '\n')
                            {
                                Ignorar = false;
                                EncontrouBarra = false;

                            }
                        }

                    }
                    else
                    {

                        if(EncontrouBarra && data != '/')
                        {
                            if(data == '*')
                            {
                                ComentarioComposto = true;
                                Ignorar = true;
                            }
                            else
                            {
                                EncontrouBarra = false;
                                ReturnList.add((int)'/');
                                buffer.append('/');
                            }

                        }

                        if(!EncontrouBarra && data != '/')
                        {
                            ReturnList.add(data);
                            buffer.append((char) data);
                        }
                        else
                        {
                            if(data == '/' && EncontrouBarra)
                            {
                                Ignorar = true;
                                EncontrouBarra = false;

                            }
                            
                            if(data == '/' && !EncontrouBarra)
                            {
                                EncontrouBarra = true;
                            }
                        }
                    }


                    data = dadosBrutos.read();

                    // exceção divisão
        
                }
                
                dadosBrutos.close();

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            System.out.println("Arquivo de texto não encontrado");
            e1.printStackTrace();

    } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.printf("Erro de leitura");
            e.printStackTrace();
    }

        /* comentarios multilinhas 
            comeca com /* e termina com 
        */
       /* for(int a : ReturnList)
        {
            System.out.print((char) a);
        }*/
        write(buffer.toString());
        return ReturnList;
    }
    
}
