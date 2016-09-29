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
    
    /**
     * letras para uso en el generador tokens
     */
    public static final String LETTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklm"
            + "nopqrstuvwxyz1234567890";
    
    /**
     * keywords para ir abriendo los Json que nos envia el API y 
     * los UUIDs
     */
    
    public static final String CHECK="check";
    public static final String ID="id";
    public static final String OPERATION="operation";
    public static final String MESSAGE="message";
    public static final String TOKEN="token";
    public static final String SUPER_NODE="SuNd";
    public static final String SPACE="espacio";
    public static final String SIZE="largo";
    public static final String IP="ip";
    public static final String PORT="port";
    public static final String PHONE_NUMBER="numTel";
    public static final String TOTAL_BYTES_NODE="cantBytes";
    public static final String STATE="estado";
    
    
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
    
    /*numeros de multi uso en el proyecto*/
    public static final int ALIVE_TIME_FOR_TOKENS=120000;
    public static final int SLEEPING_THREAD_TIME=30000;
    public static final int CHECK_TIME_FOR_NODES=10000;
    public static final int MASTER=0;
    public static final int SLAVE=1;
    public static final int TOKEN_SIZE=64;
    public static final int CONNECTED=0;
    public static final int DESCONNECTED=1;
    public static final int DELETED=0;
    public static final int NOTHING_DELETED=1;
    
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
     * @param pTokenSize
     * @return retorna un dato del tipo string
     */
    default String getToken(int pTokenSize){
        char[] temp;
        temp=new char[pTokenSize];
        for(int i=0; i <pTokenSize; i++){
            int number=generaNumeroAleatorio(0,LETTERS.length()-1);
            temp[i]=LETTERS.charAt(number);
        }
        return new String(temp);
    }
    
    /**
     * metodo para realizar la impresion de una cadena de chars.
     * @param pMsg dato tipo string 
     */
    default void cout(String pMsg){
        synchronized(this){
            System.out.println(pMsg);
        }
    }
}
