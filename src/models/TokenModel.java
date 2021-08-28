package models;


public class TokenModel {

    private String Valor;

    private String Tipo;

    public TokenModel(String Valor,String Tipo)
    {
        this.Tipo = Tipo;
        this.Valor = Valor;
    }
    
    public String getValor()
    {
        return this.Valor;
    }

    public String getTipo()
    {
        return this.Tipo;
    }
}
