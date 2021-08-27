import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        readTxt();
    }



    public static void readTxt()
    {
        InputStream inputstream;
             try {
                    inputstream = new FileInputStream(System.getProperty("user.dir") + "/input.txt");

                    int data = inputstream.read();
                    while (data != -1) {
                        System.out.println(data);
                        data = inputstream.read();
                    }
                    inputstream.close();
             } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("Arquivo de texto não encontrado");
                    e1.printStackTrace();

             } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Erro de leitura");
                    e.printStackTrace();
             }
    }
}
