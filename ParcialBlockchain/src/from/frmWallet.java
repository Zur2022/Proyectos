/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package from;

import blockchain.*;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Marielos
 */
public class frmWallet extends javax.swing.JFrame implements Runnable {

    private NodeData nodeData;
    private ArrayList<NodeData> aServers;
    private double dCurrentBalance;
    private ServerSocket clientSocket;
    private Thread tListener;
    private Cifrado oCifrado;
    BlockChain bc= new BlockChain();
    public frmWallet() {
        initComponents();
        this.setResizable(false);
        oCifrado=new Cifrado("ñVbFg-98+*DsHgñ78");
    }
     frmWallet(NodeData nClient) {
        
    }
     
    public void configure(NodeData nodeClient, double pBalance){
        this.nodeData=nodeClient;
        this.dCurrentBalance=pBalance;
        this.jLabel2.setText("Ip "+this.nodeData.getIPAddress()+".socket "+this.nodeData.getSocketNum());
        this.lUser.setText(this.nodeData.getNombreNodo());
        this.lBalance.setText("$ "+Double.toString(this.dCurrentBalance));
        this.startCliente();
        
    }
    
    public void registerServers(ArrayList<NodeData> aNodeServers){
        
        this.aServers=aNodeServers;
        this.jComboBox1.removeAllItems();
        for(int i=0; i<this.aServers.size(); i++){
            this.jComboBox1.addItem(this.aServers.get(i).getNombreNodo());
        }
    }
    
     public boolean sendTransaction() 
    {
            String sNode=this.nodeData.getNombreNodo();
            String sReceiver=this.txtSend.getText().trim().toUpperCase();
            String sArticulo=this.txtarticulo.getText().trim().toUpperCase();
            String sMarca=this.txtmarca.getText().trim().toUpperCase();
            String sTalla=this.txttalla.getText().trim().toUpperCase();
            Double dAmount=Double.parseDouble(this.txtAmount.getText());
            
            int iServer=this.jComboBox1.getSelectedIndex();
            
            if(dAmount<=this.dCurrentBalance)
            {
                this.jLabel7.setText("CurrentBalance:");
                this.dCurrentBalance -= dAmount;
                try
                {
                    sNode=this.oCifrado.encriptar(sNode);
                    sReceiver=this.oCifrado.encriptar(sReceiver);
                    sArticulo=this.oCifrado.encriptar(sArticulo);
                    sMarca=this.oCifrado.encriptar(sMarca);
                    sTalla=this.oCifrado.encriptar(sTalla);
                    
                    Block blk=new Block();
                    blk.setTransaction(sNode,sArticulo,sMarca,sTalla,dAmount,sReceiver);
                    
                    Socket socket=new Socket(
                        this.aServers.get(iServer).getIPAddress(),
                        this.aServers.get(iServer).getSocketNum());
                    ObjectOutputStream oos= new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(blk);
                    
                    this.jLabel7.setText("Current Balance:");
                    this.lBalance.setText("$"+Double.toString(this.dCurrentBalance));
                    
                    return true; 
                    
                }
                catch(Exception ex)
                {
                    this.dCurrentBalance+=dAmount;
                    this.jLabel5.setText(ex.toString());
                    System.out.println(ex.toString());
                }
            }    
            
                else this.jLabel7.setText("-Insufficient Balance:");
                return false;
    }
     
     
    private void startCliente() {
        try {
            InetAddress iAddress = InetAddress.getByName(this.nodeData.getIPAddress());
            InetSocketAddress sNetSckt = new InetSocketAddress(iAddress, this.nodeData.getSocketNum());
            this.clientSocket = new ServerSocket();
            this.clientSocket.bind(sNetSckt);
            this.tListener = new Thread(this);
            this.tListener.start();
        } catch (Exception ee) {

        }

    }
    
       @Override
    public void run()
    {
        while(true)
        {
            try
            {
                    Socket socket = this.clientSocket.accept();
                  
                    InputStream is=socket.getInputStream();
                    ObjectInputStream ois=new ObjectInputStream(is);
                    this.dCurrentBalance+=(Double)ois.readObject();
                    this.jLabel7.setText("Current Balance:");
                    this.lBalance.setText("$"+Double.toString(dCurrentBalance));
                    socket.close();
                
            }
            catch(Exception ee)
            {
                
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lUser = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtarticulo = new javax.swing.JTextField();
        txtSend = new javax.swing.JTextField();
        txtmarca = new javax.swing.JTextField();
        txttalla = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lBalance = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel1.setText("WALLET");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 83, -1, -1));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 10)); // NOI18N
        jLabel2.setText("IP 192.168.1.13.  Socket 0001");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 232, 17));

        lUser.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lUser.setText("Cliente");
        getContentPane().add(lUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 143, -1, -1));

        jLabel4.setText("Send To:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel3.setText("Articulo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 50, -1));

        jLabel9.setText("Marca:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));
        getContentPane().add(txtarticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 130, -1));
        getContentPane().add(txtSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 130, -1));
        getContentPane().add(txtmarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 130, -1));
        getContentPane().add(txttalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 130, -1));

        jLabel10.setText("Talla:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 43, -1));

        jLabel5.setText("Amount: ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));
        getContentPane().add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 132, -1));

        jLabel6.setText("Server:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 134, -1));

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 92, -1));

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel7.setText("Current Balance");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, -1));

        lBalance.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lBalance.setText("$ 0.0");
        getContentPane().add(lBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/from/ipb.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 310, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.sendTransaction();
        this.txtSend.setText("");
        this.txtAmount.setText("");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmWallet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lBalance;
    private javax.swing.JLabel lUser;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtSend;
    private javax.swing.JTextField txtarticulo;
    private javax.swing.JTextField txtmarca;
    private javax.swing.JTextField txttalla;
    // End of variables declaration//GEN-END:variables
}
