/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockchain;

/**
 *
 * @author Marielos
 */
public class NodeData {
    
    
    private String nombreNodo;
    private String IPAddress;
    private int socketNum;
    
    public NodeData(String pNombreNodo,String pDireccionIP,int pNumSocket){
        this.nombreNodo=pNombreNodo;
        this.IPAddress=pDireccionIP;
        this.socketNum=pNumSocket;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo(String nombreNodo) {
        this.nombreNodo = nombreNodo;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public int getSocketNum() {
        return socketNum;
    }

    public void setSocketNum(int socketNum) {
        this.socketNum = socketNum;
    }
    
    
    
}
