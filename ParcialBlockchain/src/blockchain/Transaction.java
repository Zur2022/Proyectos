/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockchain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Marielos
 */
public class Transaction implements Serializable {
    
    private int id;
    private long timeStamp;
    private String sender;
    private String receiver;
    private double amount;
    private String articulo;
    private String marca;
    private String talla;

    public Transaction(int pId, String pSender,String pArticulo,String  pMarca,String pTalla,String pReceiver, double pAmount)
    {
    this.id=pId;
    this.timeStamp= new Date().getTime();
    this.sender=pSender;
    this.receiver=pReceiver;
    this.amount=pAmount;
    this.articulo=pArticulo;
    this.marca=pMarca;
    this.talla=pTalla;
    }

    public String getArticulo() {
        return articulo;
    }

    public String getMarca() {
        return marca;
    }

    public String getTalla() {
        return talla;
    }
    
    @Override
    public String toString() {
    return Integer.toString(id)+Long.toString(timeStamp)+sender+articulo+marca+talla+receiver+Double.toString(amount);
    }

    /**
    * @return the id
    */
    public int getId() {
    return id;
    }
    /**
    * @return the timeStamp
    */
    public long getTimeStamp() {
    return timeStamp;
    }
    /**
     * @return the sender
     */
     public String getSender() {
     return sender;
     }
     /**
     * @return the receiver
     */
     public String getReceiver() {
     return receiver;
     }
     /**
     * @return the amount
     */
     public double getAmount() {
     return amount;
     }
    
}
