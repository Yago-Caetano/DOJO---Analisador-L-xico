import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        readTxt();
    }



    public static void readTxt()
    {
        InputStream inputstream;
             try {
                    inputstream = new FileInputStream(System.getProperty("user.dir") + "/input.txt");

                    Utils.recuperarTokens(Utils.textoSemComentario(inputstream));

             } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("Arquivo de texto não encontrado");
                    e1.printStackTrace();

             } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("Erro de leitura");
                    e.printStackTrace();
             }
    }
}
