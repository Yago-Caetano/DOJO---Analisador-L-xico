package models;


public class TokenModel {

    private String Valor;

    private String Tipo;

    private int Coluna;

    public TokenModel(String Valor,String Tipo,int Coluna)
    {
        this.Tipo = Tipo;
        this.Valor = Valor;
        this.Coluna = Coluna;
    }
    
    public String getValor()
    {
        return this.Valor;
    }

    public String getTipo()
    {
        return this.Tipo;
    }

    public int getColuna()
    {
        return this.Coluna;
    }

    
    public String toString()
    {
        String ret = "#" + this.Coluna + " " + this.Valor + " " + this.Tipo;
        return ret;
    }
}
