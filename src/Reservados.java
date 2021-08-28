import java.util.ArrayList;

import models.PalavraReservadaModel;

public class Reservados {

    private static String[] variaveisPrimitivas = {"boolean","byte","char","int","let","long","var","null","true","false", "yield","yield*","double","float","short","number"};

    private static String[] modificadoresAcesso = {"private","protected","public"};
    
    private static String[] modificadoresClasse = {"abstract","class","extends","final",	"implements","interface","native",	"new",	"static","synchronized","transient","volatile","super","this",  "with","enum","each"};
    
    private static String[] modificadoresControleFluxo = {"break","case","continue","do","else","for","function","if","instanceof","delete","in","while","switch","await","indexOf","replace"};
    
    private static String[] RetornoMetodo = {"void","return"};
    
    private static String[] TratamentoErros = {"assert","catch","finally","throw","throws","try","Error"};
    
    private static String[] NaoUtilizados = {"const","goto","object","type"};
    
    private static String[] ControlePacotes = {"import","package","export","module","default","exports"};
    
    private static String[] ProprietesMetodos = {"Array","Date",	"eval",	"function",	"hasOwnProperty","Infinity",	"isFinite",	"isNaN",	"isPrototypeOf",	"length","Math",	"NaN",	"name",	"Number",	"Object","prototype",	"String",	"toString",	"undefined",	"valueOf","typeof","map","forEach"};
    
    private static String[] Identifiers = {"getClass",	"java",	"JavaArray",	"javaClass",	"JavaObject",	"JavaPackage"};
    
    private static String[] ManipuladoresJanelas = {"alert","all",	"anchor",	"anchors",	"area","assign",	"blur",	"button",	"checkbox","clearInterval","clearTimeout",	"clientInformation",	"close",	"closed",	"confirm","constructor",	"crypto",	"decodeURI",	"decodeURIComponent",	"defaultStatus","document",	"element","elements",	"embed",	"embeds","encodeURI",	"encodeURIComponent",	"escape",	"event",	"fileUpload","focus",	"form",	"forms",	"frame",	"innerHeight","innerWidth",	"layer",	"layers",	"link",	"location","mimeTypes",	"navigate",	"navigator",	"frames",	"frameRate","hidden",	"history",	"image",	"images",	"offscreenBuffering","open",	"opener",	"option",	"outerHeight",	"outerWidth","packages",	"pageXOffset",	"pageYOffset",	"parent",	"parseFloat","parseInt",	"password",	"pkcs11",	"plugin",	"prompt","propertyIsEnum",	"radio",	"reset",	"screenX",	"screenY","scroll",	"secure",	"select",	"self",	"setInterval","setTimeout",	"status",	"submit",	"taint","text","textarea",	"top",	"unescape",	"untaint",	"window","debugger"};
    
    private static String[] ManipuladoresEventos = {"onblur","onclick",	
    "onerror",	"onfocus","onkeydown",	"onkeypress",	"onkeyup",	"onmouseover","onload",	"onmouseup",	"onmousedown",	"onsubmit"};
    
    private static String[] Simbolos = {"{","}",";","(",")",":",",",".","[","]"};

    private static String[] Operadores = {"===","==","=","&&","||","|","&","+","-","/","*","%","^","!=","!==","!","?",">",">=","<=","<"};



    public static String[] MascaraDeComparacao = {"{","}",";","(",")",":",",",".","[","]","===","==","=","&&","||","|","&","+","-","/","*","%","^","!=","!==","!","?",">",">=","<=","<"};
    
    
    public static ArrayList<PalavraReservadaModel> getPalavrasReservadas()
    {
        ArrayList<PalavraReservadaModel> Retorno = new ArrayList<PalavraReservadaModel>();

        adcionaNaLista(Retorno,Simbolos,"Simbolo");
        adcionaNaLista(Retorno,ManipuladoresEventos,"Manipulador de Eventos");
        adcionaNaLista(Retorno,ManipuladoresJanelas,"Manipuladores Janelas");
        adcionaNaLista(Retorno,Identifiers,"Identifiers");

        adcionaNaLista(Retorno,ProprietesMetodos,"Proprietes Metodos");
        adcionaNaLista(Retorno,ControlePacotes,"Controle Pacotes");
        adcionaNaLista(Retorno,NaoUtilizados,"Nao Utilizados");
        adcionaNaLista(Retorno,TratamentoErros,"TratamentoErros");

        adcionaNaLista(Retorno,RetornoMetodo,"Retorno Metodo");
        adcionaNaLista(Retorno,modificadoresControleFluxo,"Modificadores Controle Fluxo");
        adcionaNaLista(Retorno,modificadoresClasse,"Modificadores Classe");
        adcionaNaLista(Retorno,modificadoresAcesso,"Modificadores Acesso");
        adcionaNaLista(Retorno,variaveisPrimitivas,"variaveis Primitivas");
        adcionaNaLista(Retorno, Operadores, "Operador");

        return Retorno;

    }

    private static void adcionaNaLista(ArrayList<PalavraReservadaModel> lista, String[] arrayBruto, String tipo)
    {
        for(int i = 0; i < arrayBruto.length; i++)
        {
            lista.add(new PalavraReservadaModel(arrayBruto[i],tipo));
        }
    }
}
