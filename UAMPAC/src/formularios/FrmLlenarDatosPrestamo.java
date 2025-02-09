/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import dao.*;
import entidades.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author cfco5
 */

public class FrmLlenarDatosPrestamo extends javax.swing.JFrame {
    Persona persona = new Persona();
    DPersona dPersona = new DPersona();
    DLibro dLibro = new DLibro();
    DEjemplar dejemplar = new DEjemplar();
    DPrestamo dprestamo = new DPrestamo();
    ArrayList<Persona> listaPersonas = new ArrayList<>();
    ArrayList<Libro> listaLibros = new ArrayList<>();
    ArrayList<Ejemplar> listaEjemplarAPrestar = new ArrayList<>();
    int numejemplar = 0;

    /**
     * Creates new form FrmLlenarDatosPrestamo
     */
    
    public FrmLlenarDatosPrestamo() {
        initComponents();
        llenarTablaPersonas();
        llenarTablaLibros();
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
        jTblLibro = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblPersona = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblPrestamo = new javax.swing.JTable();
        jTfNombrePersona = new javax.swing.JTextField();
        jBtnCancelar = new javax.swing.JButton();
        jBtnAceptar = new javax.swing.JButton();
        jTfCodigoPrestamo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo");

        jTblLibro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblLibro.getTableHeader().setReorderingAllowed(false);
        jTblLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblLibroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblLibro);

        jTblPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblPersona.getTableHeader().setReorderingAllowed(false);
        jTblPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblPersonaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblPersona);

        jLabel1.setText("Persona: ");

        jLabel2.setText("Libro:");

        jTblPrestamo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTblPrestamo.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTblPrestamo);

        jTfNombrePersona.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTfNombrePersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfNombrePersonaActionPerformed(evt);
            }
        });

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnAceptar.setText("Aceptar");
        jBtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAceptarActionPerformed(evt);
            }
        });

        jTfCodigoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfCodigoPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(jTfNombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(jTfCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTfNombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jTfCodigoPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblLibroMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            ubicarDatosLibro();
        }
    }//GEN-LAST:event_jTblLibroMouseClicked

    private void jTfNombrePersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfNombrePersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfNombrePersonaActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAceptarActionPerformed
        // TODO add your handling code here:
        if((!jTfNombrePersona.getText().equals(""))&&(!jTfCodigoPrestamo.getText().equals(""))){
            java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(todaysDate);
            calendario.add(Calendar.DATE, 20);
            Date emision = new Date(System.currentTimeMillis());
            Date devolucion = new Date(calendario.getTimeInMillis());
            System.out.println("emision "+emision + "   Devolucion "+ devolucion);
            dprestamo.guardarPrestamo(new Prestamo(jTfCodigoPrestamo.getText(), emision, devolucion ,new BigDecimal(0) , false, persona, listaEjemplarAPrestar));
            
            this.dispose();
        }
        else JOptionPane.showMessageDialog(this, "Debe seleccionar a una persona y un codigo de prestamo","Error",JOptionPane.WARNING_MESSAGE);
                
        
       
    }//GEN-LAST:event_jBtnAceptarActionPerformed

    private void jTblPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblPersonaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            ubicarDatosPersona();
        }
    }//GEN-LAST:event_jTblPersonaMouseClicked

    private void jTfCodigoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfCodigoPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfCodigoPrestamoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLlenarDatosPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLlenarDatosPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLlenarDatosPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLlenarDatosPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLlenarDatosPrestamo().setVisible(true);
            }
        });
    }
    private void llenarListaPersonas() {
        if(listaPersonas.isEmpty())
            listaPersonas.clear();
        
        listaPersonas = dPersona.listarPersona();
    }
    
    private void llenarListaLibros() {
        if(listaLibros.isEmpty()) {
            listaLibros.clear();
        }
        
        listaLibros = dLibro.listarLibro();
    }
    
    
    private void llenarTablaPersonas() {
        llenarListaPersonas();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"ID", "Nombre", "Apellido", "Telefono"};
        
        dtm.setColumnIdentifiers(titulos);
        
        for (Persona pers: listaPersonas) {
            
            Object[] fila = new Object[] {
                pers.getId_pers(), 
                pers.getNombre_pers(),
                pers.getApellidos_pers(),
                pers.getTelefono_pers()
            };
            dtm.addRow(fila);
        }
        
        this.jTblPersona.setModel(dtm);
    }
    
    private void llenarTablaLibros() {
        llenarListaLibros();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"ISBN", "Titulo", "MFN","nombre_autor", 
             "nombre_clasificacion", 
            "nombre_editorial", "copias"};
        
        dtm.setColumnIdentifiers(titulos);
       
        String line_nom;
        int cont = 0;
        
        for (Libro lib: listaLibros) {
            //"reset" variables
            line_nom = "";
            cont = 0;
            
            for(Autor a: lib.getAutores()) {
                if (cont == lib.getAutores().size() - 1)
                        break;
                
                line_nom += a.getNombre_autor();
                
                if (cont != lib.getAutores().size() - 2) {
                    line_nom += ", ";
                }
                    
                cont++;
            }
            
            Object[] fila = new Object[] {
                lib.getIsbn(), 
                lib.getTitulo_libro(),
                lib.getMfn(),
                line_nom,
                lib.getClasificacion().getNombre_clasificacion(),
                lib.getEditorial().getNombre_editorial() ,
                lib.getAutores().get(lib.getAutores().size() - 1).getNombre_autor()
            };
            dtm.addRow(fila);
        }
        
        this.jTblLibro.setModel(dtm);
    }
    
    private void ubicarDatosLibro() {
        boolean flag = false;
        int fila = this.jTblLibro.getSelectedRow();
        ArrayList<Ejemplar> ejemplaresporlibro = dejemplar.listarEjemplar("Select * from [CATALOGO].[VW_EJEMPLAR]" +
                    " WHERE ISBN LIKE '" + listaLibros.get(fila).getIsbn() + "'");
        
        if(numejemplar<3){
            for(Ejemplar e: ejemplaresporlibro){
                if(e.isEstado()){
                    if(!listaEjemplarAPrestar.contains(e)){
                        listaEjemplarAPrestar.add(e);
                        flag=true;
                        numejemplar++;
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Ya se añadió este libro, seleccione otro","Error",JOptionPane.WARNING_MESSAGE);
                    }
                    
                }
            }
            if(!flag){
                JOptionPane.showMessageDialog(this,"No hay ejemplares disponibles para este libro","Error",JOptionPane.WARNING_MESSAGE);
            }
            else{
                llenarTablaLibrosAPrestar();
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Solo puede prestar 3 libros a la vez","Error",JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }
    private void llenarTablaLibrosAPrestar() {
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"ISBN", "Titulo", "MFN","Autores", 
             "nombre_clasificacion", 
            "nombre_editorial", "Numero de copia","Ubicacion"};
        
        dtm.setColumnIdentifiers(titulos);
        String line_nom;
        int cont = 0;
        
        for (Ejemplar ej: listaEjemplarAPrestar) {
            //"reset" variables
            line_nom = "";
            cont = 0;
            
            for(Autor a: ej.getAutores()) {
                if (cont == ej.getAutores().size() - 1)
                        break;
                
                line_nom += a.getNombre_autor();
                
                if (cont != ej.getAutores().size() - 2) {
                    line_nom += ", ";
                }
                    
                cont++;
            }
            
           
            Object[] fila = new Object[] {
                ej.getIsbn(), 
                ej.getTitulo_libro(),
                ej.getMfn(),
                line_nom,
                ej.getClasificacion().getNombre_clasificacion(),
                ej.getEditorial().getNombre_editorial() ,
                ej.getNum_copia(),
                ej.getUbicacion().getNombre_ubi()
            };
            dtm.addRow(fila);
        }
        
        this.jTblPrestamo.setModel(dtm);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAceptar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTblLibro;
    private javax.swing.JTable jTblPersona;
    private javax.swing.JTable jTblPrestamo;
    private javax.swing.JTextField jTfCodigoPrestamo;
    private javax.swing.JTextField jTfNombrePersona;
    // End of variables declaration//GEN-END:variables

    private void ubicarDatosPersona() {
        int x = jTblPersona.getSelectedRow();
        jTfNombrePersona.setText(listaPersonas.get(x).getNombre_pers());
        persona = listaPersonas.get(x);
    }
}
