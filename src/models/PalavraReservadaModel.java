package models;

import java.util.Comparator;

public class PalavraReservadaModel implements Comparable <PalavraReservadaModel>{

    String Valor; 

    String Tipo; 

    public PalavraReservadaModel(String Valor,String Tipo)
    {
        this.Valor = Valor;
        this.Tipo = Tipo;
    }

    public String getValor()
    {
        return this.Valor;
    }

    public String getTipo()
    {
        return this.Tipo;
    }


    @Override
    public int compareTo(PalavraReservadaModel o) {
        // TODO Auto-generated method stub
        return new ComparadorPalavraReservadas().compare(this, o);
    }

    
}
