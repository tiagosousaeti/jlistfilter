/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.tiagosousa.jlistfilter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * @data 20/06/2015
 * @author Tiago Sousa
 * @blog tiagosousa.eti.br
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        adicionarElementos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JListFilter");

        jTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldKeyReleased(evt);
            }
        });

        jList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldKeyPressed
        fieldLength = jTextField.getText().length();
    }//GEN-LAST:event_jTextFieldKeyPressed

    private void jTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldKeyReleased
        if (fieldLength > jTextField.getText().length()) {
            jList.setModel(model);
            filterList();
        } else {
            filterList();
        }
    }//GEN-LAST:event_jTextFieldKeyReleased

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField;
    // End of variables declaration//GEN-END:variables

    private DefaultListModel model = null;
    private DefaultListModel filteredModel = null;
    private int fieldLength = 0;

    private void adicionarElementos(){
        model = new DefaultListModel();
        model.addElement("Um");
        model.addElement("Dois");
        model.addElement("Três");
        model.addElement("Quatro");
        model.addElement("Cinco");
        model.addElement("Seis");
        model.addElement("Sete");
        model.addElement("Oito");
        model.addElement("Nove");
        model.addElement("Dez");
        
        jList.setModel(model);
    }
    
    // Método para criar a lista filtrada
    private void filterList() {
        
        int start = 0;
        int itemIx = 0;
        
        Set resultSet = new HashSet();

        filteredModel = new DefaultListModel();

        String prefix = jTextField.getText();

        javax.swing.text.Position.Bias direction = javax.swing.text.Position.Bias.Forward;

        for (int i = 0; i < jList.getModel().getSize(); i++) {
            itemIx = jList.getNextMatch(prefix, start, direction);

            try {
                resultSet.add(jList.getModel().getElementAt(itemIx));
            } catch(ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "Índice inválido");
                jTextField.setText("");
                return;
            }

            start++;
        }

        Iterator itr = resultSet.iterator();
        
        while (itr.hasNext()) {
            filteredModel.addElement(itr.next());
        }

        jList.setModel(filteredModel);
    }
}