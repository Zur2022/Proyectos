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
import java.net.ServerSocket;
import java.util.ArrayList;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marielos
 */
public class frmServer extends javax.swing.JFrame implements Runnable {

    private Thread tListener;
    private NodeData currentNode;
    private ArrayList<NodeData> a0therServers;
    private ArrayList<frmWallet> frmWallets;
    private ArrayList<NodeData> aClients;
    private ServerSocket SvrSocket;
    public BlockChain bc;
    private Cifrado oCifrado;
    
    public frmServer() {
        initComponents();
    }
    
      public frmServer(NodeData pnodeData) {
        initComponents();
        this.oCifrado = new Cifrado("ñVbFg-98+*DsHgñ78");
        this.currentNode =pnodeData;
        this.jLabel2.setText(this.currentNode.getNombreNodo());
        this.jLabel3.setText("Network IP Adrress: "+this.currentNode.getIPAddress()+" Port number: "+Integer.toString(this.currentNode.getSocketNum()));
        this.a0therServers= new ArrayList<>();
        this.aClients= new ArrayList<>();
        this.frmWallets= new ArrayList<>();
        this.bc=new BlockChain();
        this.startServer();
    }
    private void startServer() {
        this.bc = new BlockChain(3, "0", "SHA-256");
        this.bc.createGenesis();
        try {
            InetAddress iAddress = InetAddress.getByName(this.currentNode.getIPAddress());
            InetSocketAddress sNetServer = new InetSocketAddress(iAddress, this.currentNode.getSocketNum());
            SvrSocket = new ServerSocket();
            SvrSocket.bind(sNetServer);
            tListener = new Thread(this);
            tListener.start();
        } catch (Exception ee) {

        }
    }
    public void registerClients(ArrayList<NodeData> aNClients){
        this.aClients=aNClients;
        this.listBalances();
    }
    
      public void listBalances(){
        jTextArea1.removeAll();
        String sCad="";
        for(int i=0;i<this.aClients.size();i++){
            sCad+=this.aClients.get(i).getNombreNodo()+
                  "= $ "+
                  Double.toString(this.bc.getBalance(this.aClients.get(i).getNombreNodo()))
                  +"\n";
        }
        this.jTextArea1.setText(sCad);
    }
      
    public boolean broadcastBlock(Block pBlk) {
        {
            try {
                for (int i = 0; i < this.a0therServers.size(); i++) {
                    Socket socket
                            = new Socket(this.a0therServers.get(i).getIPAddress(),
                                    this.a0therServers.get(i).getSocketNum());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(pBlk);
                    socket.close();
                }
                return true;
            } catch (Exception e) {
            }
            return false;
        }
        
    }
    
    public void reportNewBalance(String receiver, double amount) {
        for (int i = 0; i < this.aClients.size(); i++) {
            if (this.aClients.get(i).getNombreNodo().equals(receiver)) {
                try {
                    Socket socket = new Socket(
                            this.aClients.get(i).getIPAddress(),
                            this.aClients.get(i).getSocketNum());
                    ObjectOutputStream oos
                            = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(amount);
                    socket.close();
                } catch (Exception e) {

                }
            }
        }
    }
    
    public void registerNet(ArrayList<NodeData> pNodes) {
        String sCad = "";
        for (int i = 0; i < pNodes.size(); i++) {
            if (!pNodes.get(i).getNombreNodo().equals(this.currentNode.getNombreNodo())) {
                this.a0therServers.add(pNodes.get(i));
                sCad += pNodes.get(i).getNombreNodo() + ", ";
            }
        }
        if (sCad.length() < 1) {
            sCad = "none";
        }
        this.jLabel4.setText(sCad);
    }
    
    public void setBlockChainCopy(BlockChain copyBlockChain) {
        this.bc = copyBlockChain;
    }

    public BlockChain getBlockChainCopy() {
        return this.bc;
    }

    public int getBlockChainSize() {

        return this.bc.size();

    }
    
    public void startClientAccount(NodeData nodeClient, double initialBalance) {
        this.bc.createBlock();
        this.bc.getLastBlock().setTransaction("0000WALLET000INITIAL000BALANCE","null","null","null",initialBalance, nodeClient.getNombreNodo());
        this.bc.mineBlock();

    }
    
        @Override
    public void run(){
        while(true){
            try{
                Socket socket = this.SvrSocket.accept();
                //
                InputStream is= socket.getInputStream();
                ObjectInputStream ois= new ObjectInputStream(is);
                Block blk=(Block)ois.readObject();
                //error2
                socket.close();
                if(blk.getId()<0){
                    String sSender=this.oCifrado.desencriptar(blk.getTransaction(0).getSender());
                    String sArticulo=this.oCifrado.desencriptar(blk.getTransaction(0).getArticulo());
                    String sMarca=this.oCifrado.desencriptar(blk.getTransaction(0).getMarca());
                    String sTalla=this.oCifrado.desencriptar(blk.getTransaction(0).getTalla());
                    String sReceiver= this.oCifrado.desencriptar(blk.getTransaction(0).getReceiver());
                    
                    double dAmount= blk.getTransaction(0).getAmount();
                    Block tBlk= new Block();
                    tBlk.setTransaction(sSender,sArticulo,sTalla,sMarca,dAmount, sReceiver);
                    if(this.bc.getBalance(sSender)>=dAmount){
                        this.bc.createBlock();
                        this.bc.getLastBlock().setTransaction(tBlk.getTransaction(0));
                        //error3
                        this.bc.mineBlock();
                        this.broadcastBlock(this.bc.getLastBlock());
                        this.reportNewBalance(sReceiver, dAmount);
                        this.listBalances();
                    }else{
                        this.txtMessages.setText("Insufficiente funds");
                    }
                }else{
                    this.bc.addProvedBlock(blk);
                }
                
            }catch (Exception ee){
                System.out.println(ee.toString());
            }
        }
            
    }
    
    
    
    
    
    


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtMessages = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel1.setText("BLOCKCHAIN");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("Server Node Location: SV");

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel3.setText("Network IP Address: 192.168.1.13  and Port 7000");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel4.setText("Available nodes: none");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel5.setText("Blockchain actions and messages");

        jButton1.setText("Summary");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Balances");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMessages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          jTextArea1.removeAll();
        String sCad="";
        for(int i=1;i<this.bc.size();i++){
            sCad+= "Block "+this.bc.getBlock(i).getId()+". "+this.bc.transactionReport(this.bc.getBlock(i).getId())+"-------------------\n";
        }
        this.jTextArea1.setText(sCad); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         this.listBalances();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtMessages;
    // End of variables declaration//GEN-END:variables
}
