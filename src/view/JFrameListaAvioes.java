/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AvioesDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Avioes;

/**
 *
 * @author Alunos
 */
public class JFrameListaAvioes extends javax.swing.JFrame {

    /**
     * Creates new form JFrameListaAvioes
     */
    public JFrameListaAvioes() {
        initComponents();
        onClicktabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAvioes = new javax.swing.JTable();
        jButtonNovo = new javax.swing.JButton();
        jButtonexcluir = new javax.swing.JButton();
        jButtoneditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Avioes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTableAvioes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAvioes);

        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novo.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jButtonexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/excluir.png"))); // NOI18N
        jButtonexcluir.setText("Excluir");
        jButtonexcluir.setEnabled(false);
        jButtonexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonexcluirActionPerformed(evt);
            }
        });

        jButtoneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/editar.png"))); // NOI18N
        jButtoneditar.setText("Editar");
        jButtoneditar.setEnabled(false);
        jButtoneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoneditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtoneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jButtonexcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtoneditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        new JFrameCadastroAviao().setVisible(true);
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonexcluirActionPerformed
        if (jTableAvioes.getSelectedRow() > -1) {
            int codigoAviao = Integer.parseInt(jTableAvioes.getValueAt(jTableAvioes.getSelectedRow(), 0).toString());
            boolean excluido = new AvioesDAO().exlcuir(codigoAviao);
            if (excluido) {
                popularListaAvioes();
                jButtonexcluir.setEnabled(false);
                jButtoneditar.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Avião excluido com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir avião!","Aviso", JOptionPane.ERROR);
            }
        }
    }//GEN-LAST:event_jButtonexcluirActionPerformed

    private void jButtoneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoneditarActionPerformed
        int linhaSelecionada = jTableAvioes.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int codigoAviao = Integer.parseInt(jTableAvioes.getValueAt(linhaSelecionada, 0).toString());
             
            Avioes aviao = new AvioesDAO().buscarAviaoPorId(codigoAviao);
            if (aviao == null) {
                JOptionPane.showMessageDialog(null, "Avião não existe mais");
                popularListaAvioes();
            }else{
                new JFrameCadastroAviao(aviao).setVisible(true);
            }
            
        }
    }//GEN-LAST:event_jButtoneditarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        popularListaAvioes();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(JFrameListaAvioes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameListaAvioes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameListaAvioes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameListaAvioes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameListaAvioes().setVisible(true);
            }
        });
    }

    private void popularListaAvioes(){
        ArrayList<Avioes> avioes = new AvioesDAO().retornarListaAviao();
        DefaultTableModel dtm = (DefaultTableModel) jTableAvioes.getModel();
        dtm.setRowCount(0);
        for (Avioes avioe : avioes) {
            dtm.addRow(new Object[]{
               avioe.getCodigo(),
                avioe.getNome(),
                avioe.getCategoria()
            });
        }
    }    
    
    private void onClicktabela(){
        jTableAvioes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTableAvioes.getSelectedRow()> -1) {
                    jButtoneditar.setEnabled(true);
                    jButtonexcluir.setEnabled(true);
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtoneditar;
    private javax.swing.JButton jButtonexcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAvioes;
    // End of variables declaration//GEN-END:variables
}
