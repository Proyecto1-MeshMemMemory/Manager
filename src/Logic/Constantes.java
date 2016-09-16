/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author betolan
 */
public interface Constantes {
    public static final int TOTAL_BYTES=64;
    
    
    /**
     * letras para uso en el generador tokens
     */
    public static final String LETTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklm"
            + "nopqrstuvwxyz1234567890";
    
    /**
     * keywords para ir abriendo los Json que nos envia el API
     */
    
    public static final String CHECK="check";
    public static final String ID="id";
    public static final String OPERATION="operation";
    public static final String MESSAGE="message";
    public static final String TOKEN="token";
    public static final String SIZE="size";
    
    /*constantes para el manejo de Json*/
    /**
     * write data
     */
    public static final int OPERATION_WR=0;
    /**
     * read memory
     */
    public static final int OPERATION_RD=1;
    /**
     * delete memory
     */
    public static final int OPERATION_DL=2;
    /**
     * allocate memory
    */
    public static final int OPERATION_AL=3;
    /**
     * increment reference counter
     */
    public static final int OPERATION_IR=4;
    /**
     * decrement reference counter
     */
    public static final int OPERATION_DR=5;
    /**
     * get a new token
     */
    public static final int OPERATION_NT=6;
    
    /*numeros de multiproposito*/
    
    public static final int CERO=0;
    public static final int UNO=1;
    public static final int DOS=2;
    public static final int TRES=3;
    public static final int CUATRO=4;
    public static final int CINCO=5;
    public static final int SEIS=6;
    public static final int SIETE=7;
    public static final int OCHO=8;
    public static final int DIEZ=10;
    public static final long ALIVE_TIME_FOR_TOKENS=120;
    
    /* mensajes de retorno para el API*/
    
    public static final String WRONG_TOKEN="{\"check\":2}";
    public static final String RIGHT_OPERATION="{\"check\":0}";
    public static final String INVALID_OPERATION="{\"check\":1}";
    
    /**
     * metodo para generar numeros al azar en un intervalo de numeros 
     * inclusivos.
     * @param pMinimo dato del tipo entero que es el numero minimo del 
     * intervalo.
     * @param pMaximo dato del tipo entero que es el numero maximo del 
     * intervalo.
     * @return retorna un numero del tipo entero que es el numero al azar
     * generado.
     */
    default int generaNumeroAleatorio(int pMinimo, int pMaximo){
        
        int num=(int)Math.floor(Math.random()*(pMinimo-(pMaximo+1))+(pMaximo+1));
        return num;
    }
    
    /**
     * metodo implementado para obtener un token para enviarselo a la 
     * persona que se conecta
     * @return retorna un dato del tipo string
     */
    default String getToken(){
        char[] temp;
        temp=new char[64];
        for(int i=0; i <64; i++){
            int number=generaNumeroAleatorio(0,LETTERS.length()-1);
            temp[i]=LETTERS.charAt(number);
        }
        return new String(temp);
    }
}
