/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import exposicion.Metodos;
import static exposicion.Metodos.jugador1;
import static exposicion.Metodos.jugador2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ventanas.TablaGanadores.Tabla;

/**
 *
 * @author Usuario
 */
public class ventana2 extends javax.swing.JFrame {

   

    /**
     * Creates new form ventana2
     */
    public ventana2() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar pantalla
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salir = new javax.swing.JButton();
        jugar = new javax.swing.JButton();
        victorias = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 150, 60));

        jugar.setText("Jugar");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });
        getContentPane().add(jugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 150, 60));

        victorias.setText("Victorias");
        victorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                victoriasActionPerformed(evt);
            }
        });
        getContentPane().add(victorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 254, 150, 60));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);//Boton para salir de la ventana
    }//GEN-LAST:event_salirActionPerformed

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        hide();//ocultar ventana
        Metodos met = new Metodos();

        met.llenarBaraja("cartas.txt");
        met.cargarArray();
        ventanaJugar ven = new ventanaJugar();
        ven.setVisible(true);

        met.cambioVentana(jugador1, jugador2, ventanaJugar.texto, ventanaJugar.texto2);
        met.pares(jugador2);

    }//GEN-LAST:event_jugarActionPerformed

    private void victoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_victoriasActionPerformed
        TablaGanadores tab = new TablaGanadores();
        this.setVisible(false);
        tab.setVisible(true);

        Statement stmt = null;
        Connection c = null;
        int a = 0;
        int b = 0;
        try {

            c = DriverManager.getConnection("jdbc:sqlite:BaseCartas.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ganadores order by victorias desc;");
            while (rs.next()) {
                Tabla.setValueAt(String.valueOf(rs.getString("Nombre")), a, b);
                b++;
                String nombre = rs.getString("Nombre");
                

                Tabla.setValueAt(String.valueOf(rs.getInt("Victorias")), a, b);
                a++;
                b = 0;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(TablaGanadores.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_victoriasActionPerformed

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
            java.util.logging.Logger.getLogger(ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventana2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventana2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jugar;
    private javax.swing.JButton salir;
    private javax.swing.JButton victorias;
    // End of variables declaration//GEN-END:variables
}
