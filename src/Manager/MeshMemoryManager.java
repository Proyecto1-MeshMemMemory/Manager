/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Logic.Constantes;
import sockets.ServerListener;
import sockets.ServerApiListener;
import Logic.TokenList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ellioth
 */
public class MeshMemoryManager implements Constantes{
    
    private ServerListener _server;
    private int _countServerListener;
    private TokenList _tokenList;
    
    public MeshMemoryManager(int pPort){
        _server= new ServerListener(pPort);
        _countServerListener=0;
        _tokenList= new TokenList();
        (new Thread(_tokenList)).start();
        (new Thread(_server)).start();
        MainLoop();
    }
    
    private void MainLoop(){
        while(true){
            //verificamos que hayan nuevas persona agregadas.
            if(_server.getSizeListListener()>_countServerListener){
                //creamos el nuevo token y se lo mandamos.
                ServerApiListener temp =_server.getNewClientApi();
                String newToken=getToken();
                String newJsonToken=createJson(CERO,CERO, "", newToken);
                temp.sendToClientApi(newJsonToken);
                _tokenList.add(newToken);
                temp.setFlagListenerFalse();
                (new Thread(temp)).start();
                System.out.println("Token enviado a nuevo cliente:"+newJsonToken);
                _countServerListener=_server.getSizeListListener();
            }
            //recorremos todas las personas actuales hasta obtener todos los 
            //mensajes que hayan enviado
            List<ServerApiListener> tempClientListenerList=_server.
                    getActualListListeners();
            for(int i=0; i<_countServerListener; i++){
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
    
    private String createJson(int pCheck,int pId, String pMsg, String pToken){
        try {
            JSONObject newJsonToSend= new JSONObject();
            if(!pToken.isEmpty()){
                newJsonToSend.put(TOKEN, pToken);
                return newJsonToSend.toString();
            }
            if(pCheck==-UNO){
                System.out.println("Error: pedido invalido por parte del cliente");
                return INVALID_OPERATION;
            }
            newJsonToSend.put(CHECK, pCheck);
            if(pId>=CERO){
                newJsonToSend.put(ID, pId);
            }
            else if(!pMsg.isEmpty()){
                newJsonToSend.putOnce(ID, pMsg);
            }
            return newJsonToSend.toString();
        } catch (JSONException ex) {
            System.out.println("Error: no se ha podido crear el Json");
        }
        return INVALID_OPERATION;
    }
    
    private String decodeJsonFromClient(String pJsonFromClient){
        try {
            JSONObject obj = new JSONObject(pJsonFromClient);
            String tokenClient=obj.getString(TOKEN);
            //if()
            int JsonOperation =obj.getInt(OPERATION);
            if(JsonOperation==OPERATION_WR){
                
            }
            else if(JsonOperation==OPERATION_AL){
                
            }
            else if(JsonOperation==OPERATION_RD){
                
            }
            else if(JsonOperation==OPERATION_DL){
                
            }
            else if(JsonOperation==OPERATION_IR){
                
            }
            else if(JsonOperation==OPERATION_DR){
                
            }
            else if(JsonOperation==OPERATION_NT){
                String newToken=getToken();
                String temp=createJson(CERO, CERO, "", newToken);
                _tokenList.add(newToken);
                System.out.println("Token nuevo enviado");
                return temp;
            }else{
                return INVALID_OPERATION;
            }
        } catch (JSONException ex) {
            System.out.println("Error: Json incompleto o con errores de "
                    + "escritura");
        }
        return INVALID_OPERATION;
    }
    
    public void GarbageCollector(){
        
    }
    
    private void burping(){
        
    }
    
    public void makePing(){
        
    }
}
