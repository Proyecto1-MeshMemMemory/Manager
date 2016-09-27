/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Lists.CircularLinkedList;
import Lists.MiniNode;
import Lists.NodesOfMemory;
import Lists.SuperNodeMemory;
import Logic.Constantes;
import sockets.ServerListener;
import sockets.ServerApiListener;
import Lists.TokenList;
import java.net.Socket;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import sockets.ServerAndroidListener;

/**
 * clase para instancear el manager para hacer todo el manejo del la 
 * memoria.
 * @author ellioth
 */
public class MeshMemoryManager implements Constantes{
    
    private ServerListener _serverAPI;
    private ServerAndroidListener _serverAndroid;
    private int _countServerListenerAPI;
    private int _countServerListenerAndroid;
    private TokenList _tokenList;
    private CircularLinkedList _MemoryList;
    private int _port;
    
    /**
     * constructor de la clase, recibe un puerto para establecer el 
     * server, Nota: el server de android se instancea en el numero del
     * puerto establecido + uno, ie.(port+1).
     * @param pPort dato tipo entero que es el puerto donde vamos a
     * instacear los servers, tanto para Api's como para Android's.
     */
    public MeshMemoryManager(int pPort){
        _port=pPort;
        _serverAPI= new ServerListener(pPort);
        _serverAndroid= new ServerAndroidListener(pPort+UNO);
        _countServerListenerAPI=0;
        _countServerListenerAndroid=0;
        _tokenList= new TokenList();
        _MemoryList= new CircularLinkedList();
        (new Thread(_tokenList)).start();
        (new Thread(_serverAPI)).start();
        (new Thread(_serverAndroid)).start();
        MainLoop();
    }
    
    /**
     * metodo para crear el ciclo infinito autonomo que va a estar haciendo
     * todo.
     */
    private void MainLoop(){
        while(true){
            //verificamos que hayan nuevas persona agregadas.
            if(_serverAPI.getSizeListListener()>_countServerListenerAPI){
                addNewAPI();
            }
             //verificamos que hayan nuevas persona agregadas.
            if(_serverAndroid.getSizeListListener()>_countServerListenerAndroid){
               addNewAndroid();
            }
            //recorremos todas las personas actuales hasta obtener todos los 
            //mensajes que hayan enviado
            List<ServerApiListener> tempClientListenerList=_serverAPI.
                    getActualListListeners();
            for(int i=0; i<_countServerListenerAPI; i++){
                ServerApiListener temp=tempClientListenerList.get(i);
                //verificamos que haya enviado mensaje nuevo
                if(temp.getFlagListener()){
                    String newJsonToApiClient=decodeJsonFromClient(
                            temp.getMsgFromApiClient());
                    temp.sendToClientApi(newJsonToApiClient);
                    temp.setFlagListenerFalse();
                }
            }
        }
    }
    
    /**
     * metodo para agregar a las nuevas apis que se conectan.
     */
    private void addNewAPI(){
        //creamos el nuevo token y se lo mandamos.
        ServerApiListener temp =_serverAPI.getNewClientApi();
        String newToken=getToken(TOKEN_SIZE);
        String newJsonToken=createJson(CERO,"", "", newToken);
        temp.sendToClientApi(newJsonToken);
        _tokenList.add(newToken);
        temp.setFlagListenerFalse();
        (new Thread(temp)).start();
        cout("Token enviado a nuevo cliente:"+newJsonToken);
        _countServerListenerAPI=_serverAPI.getSizeListListener();
    }
    
    /**
     * metodo para agregara los android's que se conectan contra el manager 
     * para prestar memoria.
     */
    private void addNewAndroid(){
        try {
            //creamos el nuevo token y se lo mandamos.
            Socket temp =_serverAndroid.getNewAndroidSocket(
                    _countServerListenerAndroid);
            String info=_serverAndroid.getMsg(temp);
            JSONObject tempJson= new JSONObject(info);
            int numbrePhone= tempJson.getInt(PHONE_NUMBER);
            int totalBytes= tempJson.getInt(TOTAL_BYTES_NODE);
            MiniNode newNodo= new MiniNode(_countServerListenerAndroid, 
                    numbrePhone, totalBytes, temp);
            int state=_MemoryList.addNode(newNodo);
            if(state==MASTER){
                JSONObject newJsonTosend= new JSONObject();
                newJsonTosend.put(ID, UNO);
                newJsonTosend.put("Master",UNO);
                _serverAndroid.sendJsonToAndroid(
                        newJsonTosend.toString(), temp);
            }else if(state==SLAVE){
                tempJson= new JSONObject();
                tempJson.put(ID, UNO);
                tempJson.put("Master",CERO);
                _serverAndroid.sendJsonToAndroid(
                        tempJson.toString(), temp);
                SuperNodeMemory tempSuperNode=_MemoryList.getHead();
                for(int i=0; tempSuperNode!=_MemoryList.getTail(); i++){
                    if(tempSuperNode.getSlave()==newNodo)
                        break;
                    tempSuperNode=tempSuperNode.getNext();
                }
                if(tempSuperNode==_MemoryList.getTail()){
                    
                }
                tempJson= new JSONObject();
                tempJson.put(ID, 2);
                tempJson.put(PHONE_NUMBER, numbrePhone);
                _serverAndroid.sendJsonToAndroid(tempJson.toString(),
                        tempSuperNode.getMaster().getScoket());
            }else{
                JSONObject newJsonTosend= new JSONObject();
                newJsonTosend.put(ID, -UNO);
                _serverAndroid.sendMsg(newJsonTosend.toString(), temp);
            }
            _countServerListenerAPI=_serverAPI.getSizeListListener();

        } catch (JSONException ex) {
            cout("Error: agregacion de un nuevo nodo android");
        }
    }
    
    /**
     * metodo para crear los mensajes que se le enviaran al api.
     * @param pCheck entero que nos indica si la operacion estuvo bien hecha o 
     * no.
     * @param pId string del id.
     * @param pMsg string del mensaje que le queramos adjuntar.
     * @param pToken string del token que vamos adjuntar.
     * @return retorna un string del resultante del Json creado.
     */
    private String createJson(int pCheck,String pId, String pMsg, String pToken){
        try {
            JSONObject newJsonToSend= new JSONObject();
            if(!pToken.isEmpty()){
                newJsonToSend.put(TOKEN, pToken);
                return newJsonToSend.toString();
            }
            if(pCheck==-UNO){
                cout("Error: pedido invalido por parte del cliente");
                return INVALID_OPERATION;
            }
            newJsonToSend.put(CHECK, pCheck);
            if(pId.equals("")){
                newJsonToSend.put(ID, pId);
            }
            else if(!pMsg.isEmpty()){
                newJsonToSend.putOnce(ID, pMsg);
            }
            return newJsonToSend.toString();
        } catch (JSONException ex) {
            cout("Error: no se ha podido crear el Json");
        }
        return INVALID_OPERATION;
    }
    
    /**
     * metodo para crear el Json del id.
     * @param pSuNo recibe el numero del SuperNode donde guardamos el dato 
     * que se guardo el dato.
     * @param pId String de un id unico que se le asigna al dato guardado,
     * este id se genera de manera aleatoria.
     * @return retorna el string del Json equivalente.
     */
    private String createJsonId(int pSuNo, String pId){
        try {
            JSONObject newJsonToSend= new JSONObject();
            newJsonToSend.put(SUPER_NODE, pSuNo);
            newJsonToSend.put(ID, pId);
            return newJsonToSend.toString();
        } catch (JSONException ex) {
            cout("Error: no se ha podido crear el Json");
        }
        return "";
    }
    
    /**
     * metodo para decodificar el pedido del api.
     * @param pJsonFromClient
     * @return retorna el string del Json equivalente que se formo.
     */
    private String decodeJsonFromClient(String pJsonFromClient){
        try {
            //abrir Json del api.
            JSONObject obj = new JSONObject(pJsonFromClient);
            String tokenClient=obj.getString(TOKEN);
            int JsonOperation =obj.getInt(OPERATION);
            //verificamos que se pdia un nuevo token
            if(JsonOperation==OPERATION_NT){
                String newToken=getToken(TOKEN_SIZE);
                String temp=createJson(CERO, "", "", newToken);
                _tokenList.add(newToken);
                cout("Token nuevo enviado");
                return temp;
            }
            //si el token esta malo o no existe le decimos que el token esta 
            //malo.
            if(!_tokenList.find(tokenClient))
                return WRONG_TOKEN;
            
            SuperNodeMemory temp=_MemoryList.getHead();
            //alojamos la memoria.
            if(JsonOperation==OPERATION_AL){
                return alocating(pJsonFromClient);
            }
            JSONObject objId=obj.optJSONObject(ID);
            //hacemos la escritura del dato.
            if(JsonOperation==OPERATION_WR){
                return writing(pJsonFromClient);
            }
            int SuperNodeSpace= objId.getInt(SUPER_NODE);
            String MemNodeId= objId.getString(ID);
            for(int i=0; i<SuperNodeSpace; i++)
                temp=temp.getNext();
            MiniNode tempMiniNode= temp.getMaster();
            NodesOfMemory tempNodeMemory= temp.getListMemory().getHead();
            while(tempNodeMemory!=null){
                if(tempNodeMemory.getID().equals(MemNodeId))
                    break;
                tempNodeMemory= tempNodeMemory.getNext();
            }
            if(tempNodeMemory==null)
                return INVALID_OPERATION;
            JSONObject newTempJson= new JSONObject();
            newTempJson.put(SPACE, tempNodeMemory.getSpaceMemory());
            newTempJson.put(SIZE, tempNodeMemory.getSize());
            //hacemos una lectura.
            if(JsonOperation==OPERATION_RD){
                return reading(pJsonFromClient);
            }
            //hacemos un borrado.
            else if(JsonOperation==OPERATION_DL){
                return deleting(pJsonFromClient);
            }
            //hacemos un incremento de referencias.
            else if(JsonOperation==OPERATION_IR){
                tempNodeMemory.increaseRefCounter();
                return RIGHT_OPERATION;
            }
            //hacemos una disminucio de las referenceias.
            else if(JsonOperation==OPERATION_DR){
                tempNodeMemory.decreaseRefCounter();
                return RIGHT_OPERATION;
            }
            return INVALID_OPERATION;
        } catch (JSONException ex) {
            cout("Error: Json incompleto o con errores de "
                    + "escritura");
        }
        return INVALID_OPERATION;
    }
    
    /**
     * metodo para hacer el alojamiento del dato.
     * @return retorna el string del Json equivalente que se formo.
     */
    private String alocating(String pJsonFromClient){
        try {
            JSONObject obj = new JSONObject(pJsonFromClient);
            SuperNodeMemory temp=_MemoryList.getHead();
            int size=obj.getInt(SIZE);
            SuperNodeMemory tail= _MemoryList.getTail();
            int i=0;
            while(temp!=tail){
                if(temp.getListMemory().getSizeLeft()>=size)
                    break;
                temp=temp.getNext();
                i++;
            }
            if(temp.getListMemory().getSizeLeft()>=size){
                String newId= getToken(DIEZ);
                int NumberNode =temp.getListMemory().
                        tryInsertion(pJsonFromClient, newId);
                if(NumberNode>=CERO){
                    String newUUID= createJsonId(i, newId);
                    return createJson(CERO, newUUID,"", "");
                }
            }
        } catch (JSONException ex) {
            cout("Error: no se ha podido completar el alojo del "
                    + "dato");
            return INVALID_OPERATION;
        }
        return INVALID_OPERATION;
    }
    
    /**
     * Metodo para realizar la escritura de los datos que nos envia el 
     * cliente api.
     * @return retorna el string del Json equivalente que se formo.
     */
    private String writing(String pJsonFromClient){
        try {
            JSONObject obj = new JSONObject(pJsonFromClient);
            JSONObject objId=obj.optJSONObject(ID);
            SuperNodeMemory temp=_MemoryList.getHead();
            if(objId!=null){
                int SuperNodeSpace= objId.getInt(SUPER_NODE);
                String MemNodeId= objId.getString(ID);
                int size= objId.getInt(SIZE);
                int space= objId.getInt(SPACE);
                for(int i=0; i<SuperNodeSpace; i++)
                    temp=temp.getNext();
                MiniNode tempMiniNode= temp.getMaster();
                NodesOfMemory tempNodeMemory= temp.getListMemory().getHead();
                while(tempNodeMemory!=null){
                    if(tempNodeMemory.getID().equals(MemNodeId))
                        break;
                    tempNodeMemory= tempNodeMemory.getNext();
                }
                JSONObject newTempJson= new JSONObject();
                newTempJson.put(SPACE, space);
                newTempJson.put(SIZE, size);
                newTempJson.put(ID, CUATRO);
                newTempJson.put(MESSAGE, obj.get(MESSAGE));
                String repsond=_serverAndroid.sendJsonToAndroid(newTempJson.toString(),
                        tempMiniNode.getScoket());
                newTempJson= new JSONObject(repsond);
                if(newTempJson.getInt(STATE)==UNO){
                    return RIGHT_OPERATION;
                }
                return INVALID_OPERATION;
            }
            int size=obj.getInt(SIZE);
            SuperNodeMemory tail= _MemoryList.getTail();
            int i=0;
            while(temp!=tail){
                if(temp.getListMemory().getSizeLeft()>=size)
                    break;
                temp=temp.getNext();
                i++;
            }
            if(temp.getListMemory().getSizeLeft()>=size){
                String newId= getToken(DIEZ);
                int NumberNode =temp.getListMemory().
                        tryInsertion(pJsonFromClient, newId);
                if(NumberNode>=CERO){
                    NodesOfMemory tempNodeMemory= temp.getListMemory().getHead();
                    while(tempNodeMemory!=null){
                        if(tempNodeMemory.getID().equals(newId))
                            break;
                        tempNodeMemory= tempNodeMemory.getNext();
                    }
                    JSONObject newTempJson= new JSONObject();
                    newTempJson.put(SPACE, tempNodeMemory.getSpaceMemory());
                    newTempJson.put(SIZE, size);
                    newTempJson.put(ID, CUATRO);
                    newTempJson.put(MESSAGE, obj.get(MESSAGE));
                    String repsond=_serverAndroid.sendJsonToAndroid(newTempJson.toString(),
                            temp.getMaster().getScoket());
                    newTempJson= new JSONObject(repsond);
                    if(newTempJson.getInt(STATE)==UNO){
                        String newUUID= createJsonId(i, newId);
                        return createJson(CERO, newUUID,"", "");
                    }
                }
            }
        } catch (JSONException ex) {
            cout("Error: en escirtua del dato");
            return INVALID_OPERATION;
        }
        return INVALID_OPERATION;
    }
    
    /**
     * Metodo para hacer la lectura del dato pedido.
     * @return retorna el string del Json equivalente que se formo.
     */
    private String reading(String pJsonFromClient){
        try {
            JSONObject obj = new JSONObject(pJsonFromClient);
            JSONObject objId=obj.optJSONObject(ID);
            SuperNodeMemory temp=_MemoryList.getHead();
            int SuperNodeSpace= objId.getInt(SUPER_NODE);
            String MemNodeId= objId.getString(ID);
            for(int i=0; i<SuperNodeSpace; i++)
                temp=temp.getNext();
            MiniNode tempMiniNode= temp.getMaster();
            NodesOfMemory tempNodeMemory= temp.getListMemory().getHead();
            while(tempNodeMemory!=null){
                if(tempNodeMemory.getID().equals(MemNodeId))
                    break;
                tempNodeMemory= tempNodeMemory.getNext();
            }
            if(tempNodeMemory==null)
                return INVALID_OPERATION;
            JSONObject newTempJson= new JSONObject();
            newTempJson.put(SPACE, tempNodeMemory.getSpaceMemory());
            newTempJson.put(SIZE, tempNodeMemory.getSize());
            newTempJson.put(ID, CINCO);
            String repsond=_serverAndroid.sendJsonToAndroid(newTempJson.toString(),
                    tempMiniNode.getScoket());
            newTempJson= new JSONObject(repsond);
            if(newTempJson.getInt(STATE)==UNO){
                String msg= newTempJson.getString(MESSAGE);
                newTempJson= new JSONObject();
                newTempJson.put(CHECK, CERO);
                newTempJson.put(MESSAGE, msg);
                return newTempJson.toString();
            }
        } catch (JSONException ex) {
            cout("Error: no se ha podido completar la lectura del "
                    + "llamado del:"+ pJsonFromClient);
            return INVALID_OPERATION;
        }
        return INVALID_OPERATION;
    }
    
    /**
     * metodo apra hacer el borrado del dato pedido.
     * @return retorna el string del Json equivalente que se formo.
     */
    private String deleting(String pJsonFromClient){
        try {
            JSONObject obj = new JSONObject(pJsonFromClient);
            JSONObject objId=obj.optJSONObject(ID);
            SuperNodeMemory temp=_MemoryList.getHead();
            int SuperNodeSpace= objId.getInt(SUPER_NODE);
            String MemNodeId= objId.getString(ID);
            for(int i=0; i<SuperNodeSpace; i++)
                temp=temp.getNext();
            MiniNode tempMiniNode= temp.getMaster();
            NodesOfMemory tempNodeMemory= temp.getListMemory().getHead();
            while(tempNodeMemory!=null){
                if(tempNodeMemory.getID().equals(MemNodeId))
                    break;
                tempNodeMemory= tempNodeMemory.getNext();
            }
            if(tempNodeMemory==null)
                return INVALID_OPERATION;
            JSONObject newTempJson= new JSONObject();
            newTempJson.put(SPACE, tempNodeMemory.getSpaceMemory());
            newTempJson.put(SIZE, tempNodeMemory.getSize());
            newTempJson.put(ID, SEIS);
            String repsond=_serverAndroid.sendJsonToAndroid(newTempJson.toString(),
                    tempMiniNode.getScoket());
            newTempJson= new JSONObject(repsond);
            if(newTempJson.getInt(STATE)==UNO){
                return RIGHT_OPERATION;
            }
        } catch (JSONException ex) {
            cout("Error: no se ha podido completar el borrado del"
                    + "llamado del:"+ pJsonFromClient);
            return INVALID_OPERATION;
        }
        return INVALID_OPERATION;
    }
    
    /**
     * metodo apra realizarle ping a los android conectados dando memoria para
     * verificar que aun estan en linea.
     */
    private void makePing(){
        
    }
}