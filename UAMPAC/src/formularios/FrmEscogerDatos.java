/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formularios;

import dao.DAutor;
import dao.DClasificacion;
import dao.DEditorial;
import entidades.Autor;
import entidades.Clasificacion;
import entidades.Editorial;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gchang110101
 */

public class FrmEscogerDatos extends javax.swing.JFrame {
    
    //4 instancias del mismo form (cada tab)
    private static FrmEscogerDatos objFrmEscogerEditorial = null;
    private static FrmEscogerDatos objFrmEscogerClasificacion = null;
    private static FrmEscogerDatos objFrmEscogerAutor = null;
    private static FrmEscogerDatos objFrmEscogerUbicacion = null;
    
    //objetos editorial
    DEditorial dEdi = new DEditorial();
    ArrayList<Editorial> listaEdi = new ArrayList<>();
    
    //objetos autor
    DAutor dAut = new DAutor();
    ArrayList<Autor> listaAut = new ArrayList<>();
    
    //objetos clasificacion
    DClasificacion dClasi = new DClasificacion();
    ArrayList<Clasificacion> listaClasi = new ArrayList<>();
    
    //objetos filtros tablas
    TableRowSorter filtroTablaEdi;
    TableRowSorter filtroTablaAut;
    TableRowSorter filtroTablaClasi;
    
    /**
     * Creates new form FrmEscogerDatos
     * @param x -> tab a dejar enabled
     */
    private FrmEscogerDatos(int x) {
        initComponents();
        actualizarTabsTrue();
        
        if(x == 0) {
            actualizarTabsFalse();
            this.TpEscoger.setEnabledAt(0, true);
            this.TpEscoger.setSelectedIndex(0);
        }

        if(x == 1) {
            actualizarTabsFalse();
            this.TpEscoger.setEnabledAt(1, true);
            this.TpEscoger.setSelectedIndex(1);
        }

        if(x == 2) {
            actualizarTabsFalse();
            this.TpEscoger.setEnabledAt(2, true);
            this.TpEscoger.setSelectedIndex(2);
        }

        if(x == 3) {
            actualizarTabsFalse();
            this.TpEscoger.setEnabledAt(3, true);
            this.TpEscoger.setSelectedIndex(3);
        }
        
        //llenar las tablas con la base de datos
        llenarTablaEditoriales();
        llenarTablaAutores();
        llenarTablaClasificaciones();
    }
    
    //métodos para obtener instancias (Singleton Design Pattern)
    public static FrmEscogerDatos getObjEdit() {
        if (objFrmEscogerEditorial == null) {
            objFrmEscogerEditorial = new FrmEscogerDatos(0);
        } return objFrmEscogerEditorial;
    }
    
    public static FrmEscogerDatos getObjClasi() {
        if (objFrmEscogerClasificacion == null) {
            objFrmEscogerClasificacion = new FrmEscogerDatos(1);
        } return objFrmEscogerClasificacion;
    }
    
    public static FrmEscogerDatos getObjAut() {
        if (objFrmEscogerAutor == null) {
            objFrmEscogerAutor = new FrmEscogerDatos(2);
        } return objFrmEscogerAutor;
    }
    
    public FrmEscogerDatos() {
        initComponents();
    }

    private void actualizarTabsTrue() {
        this.TpEscoger.setEnabledAt(0, true);
        this.TpEscoger.setEnabledAt(1, true);
        this.TpEscoger.setEnabledAt(2, true);
        this.TpEscoger.setEnabledAt(3, true);
    }
    
    private void actualizarTabsFalse() {
        this.TpEscoger.setEnabledAt(0, false);
        this.TpEscoger.setEnabledAt(1, false);
        this.TpEscoger.setEnabledAt(2, false);
        this.TpEscoger.setEnabledAt(3, false);
    }
    
    //métodos llenar array lists con datos de tablas
    private void llenarListaEditoriales() {
        if(listaEdi.isEmpty())
            listaEdi.clear();
        
        listaEdi = dEdi.listarEditorial();
    }
    
    private void llenarListaAutores() {
        if(listaAut.isEmpty())
            listaAut.clear();
        
        listaAut = dAut.listarAutor();
    }
    
    private void llenarListaClasificaciones() {
        if(listaClasi.isEmpty())
            listaClasi.clear();
        
        listaClasi = dClasi.listarClasificacion();
    }
    
    private void llenarTablaEditoriales() {
        llenarListaEditoriales();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Código", "Nombre"};
        dtm.setColumnIdentifiers(titulos);
        
        for (Editorial e: listaEdi) {
            Object[] fila = new Object[] {
                e.getCod_editorial(),
                e.getNombre_editorial()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegEdi.setModel(dtm);
    }
    
    private void llenarTablaAutores() {
        llenarListaAutores();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Código", "Nombre"};
        dtm.setColumnIdentifiers(titulos);
        
        for (Autor a: listaAut) {
            Object[] fila = new Object[] {
                a.getCodigo_autor(),
                a.getNombre_autor()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegAut.setModel(dtm);
    }
    
    private void llenarTablaClasificaciones() {
        llenarListaClasificaciones();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Código", "Nombre"};
        dtm.setColumnIdentifiers(titulos);
        
        for (Clasificacion c: listaClasi) {
            Object[] fila = new Object[] {
                c.getCod_clasificacion(),
                c.getNombre_clasificacion()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegClasi.setModel(dtm);
    }
    
    //métodos filtrar tablas tabs (non-case sensitive)
    private void filtrarTablaEdi() {
        filtroTablaEdi.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarEditorialEsc.getText()
                , 1));
    }
    
    private void filtrarTablaAut() {
        filtroTablaAut.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarAutorEsc.getText(),
                1));
    }
    
    private void filtrarTablaClasi() {
        filtroTablaClasi.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarClasificacionEsc.getText(),
                1));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TpEscoger = new javax.swing.JTabbedPane();
        PanelEditorial = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblRegEdi = new javax.swing.JTable();
        LblbuscarEditorial = new javax.swing.JLabel();
        TfDatoBuscarEditorialEsc = new javax.swing.JTextField();
        PanelClasificacion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblRegClasi = new javax.swing.JTable();
        LblbuscarEditorial1 = new javax.swing.JLabel();
        TfDatoBuscarClasificacionEsc = new javax.swing.JTextField();
        PanelAutor = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblRegAut = new javax.swing.JTable();
        LblbuscarEditorial2 = new javax.swing.JLabel();
        TfDatoBuscarAutorEsc = new javax.swing.JTextField();
        PanelUbicacion = new javax.swing.JPanel();
        LblbuscarEditorial3 = new javax.swing.JLabel();
        TfDatoBuscarEditorial3 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblRegUbi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escoger Datos");
        setResizable(false);

        TblRegEdi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegEdi.getTableHeader().setReorderingAllowed(false);
        TblRegEdi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegEdiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblRegEdi);

        LblbuscarEditorial.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblbuscarEditorial.setText("Buscar:");

        TfDatoBuscarEditorialEsc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarEditorialEscKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelEditorialLayout = new javax.swing.GroupLayout(PanelEditorial);
        PanelEditorial.setLayout(PanelEditorialLayout);
        PanelEditorialLayout.setHorizontalGroup(
            PanelEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditorialLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PanelEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelEditorialLayout.createSequentialGroup()
                        .addComponent(LblbuscarEditorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TfDatoBuscarEditorialEsc))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelEditorialLayout.setVerticalGroup(
            PanelEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditorialLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelEditorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblbuscarEditorial)
                    .addComponent(TfDatoBuscarEditorialEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TpEscoger.addTab("Editorial", PanelEditorial);

        TblRegClasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegClasi.getTableHeader().setReorderingAllowed(false);
        TblRegClasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegClasiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblRegClasi);

        LblbuscarEditorial1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblbuscarEditorial1.setText("Buscar:");

        TfDatoBuscarClasificacionEsc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarClasificacionEscKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelClasificacionLayout = new javax.swing.GroupLayout(PanelClasificacion);
        PanelClasificacion.setLayout(PanelClasificacionLayout);
        PanelClasificacionLayout.setHorizontalGroup(
            PanelClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClasificacionLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PanelClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelClasificacionLayout.createSequentialGroup()
                        .addComponent(LblbuscarEditorial1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TfDatoBuscarClasificacionEsc))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelClasificacionLayout.setVerticalGroup(
            PanelClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClasificacionLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblbuscarEditorial1)
                    .addComponent(TfDatoBuscarClasificacionEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TpEscoger.addTab("Clasificación", PanelClasificacion);

        TblRegAut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegAut.getTableHeader().setReorderingAllowed(false);
        TblRegAut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegAutMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TblRegAut);

        LblbuscarEditorial2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblbuscarEditorial2.setText("Buscar:");

        TfDatoBuscarAutorEsc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarAutorEscKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelAutorLayout = new javax.swing.GroupLayout(PanelAutor);
        PanelAutor.setLayout(PanelAutorLayout);
        PanelAutorLayout.setHorizontalGroup(
            PanelAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAutorLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PanelAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelAutorLayout.createSequentialGroup()
                        .addComponent(LblbuscarEditorial2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TfDatoBuscarAutorEsc))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelAutorLayout.setVerticalGroup(
            PanelAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAutorLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelAutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblbuscarEditorial2)
                    .addComponent(TfDatoBuscarAutorEsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TpEscoger.addTab("Autor", PanelAutor);

        LblbuscarEditorial3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblbuscarEditorial3.setText("Buscar:");

        TblRegUbi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegUbi.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(TblRegUbi);

        javax.swing.GroupLayout PanelUbicacionLayout = new javax.swing.GroupLayout(PanelUbicacion);
        PanelUbicacion.setLayout(PanelUbicacionLayout);
        PanelUbicacionLayout.setHorizontalGroup(
            PanelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUbicacionLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PanelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelUbicacionLayout.createSequentialGroup()
                        .addComponent(LblbuscarEditorial3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TfDatoBuscarEditorial3))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelUbicacionLayout.setVerticalGroup(
            PanelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUbicacionLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelUbicacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblbuscarEditorial3)
                    .addComponent(TfDatoBuscarEditorial3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TpEscoger.addTab("Ubicación", PanelUbicacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(TpEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TpEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TfDatoBuscarEditorialEscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarEditorialEscKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarEditorialEsc.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaEdi();
            }
        });
        
        filtroTablaEdi = new TableRowSorter(this.TblRegEdi.getModel());
        this.TblRegEdi.setRowSorter(filtroTablaEdi);
    }//GEN-LAST:event_TfDatoBuscarEditorialEscKeyTyped

    private void TfDatoBuscarClasificacionEscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarClasificacionEscKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarClasificacionEsc.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaClasi();
            }
        });
        
        filtroTablaClasi = new TableRowSorter(this.TblRegClasi.getModel());
        this.TblRegClasi.setRowSorter(filtroTablaClasi);        
    }//GEN-LAST:event_TfDatoBuscarClasificacionEscKeyTyped

    private void TfDatoBuscarAutorEscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarAutorEscKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarAutorEsc.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaAut();
            }
        });
        
        filtroTablaAut = new TableRowSorter(this.TblRegAut.getModel());
        this.TblRegAut.setRowSorter(filtroTablaAut);
    }//GEN-LAST:event_TfDatoBuscarAutorEscKeyTyped

    private void TblRegEdiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegEdiMouseClicked
        // TODO add your handling code here:
        this.TblRegEdi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = TblRegEdi.getSelectedRow();
                    FrmCatalogo.pass_codEdi = String.valueOf(TblRegEdi.getValueAt(fila , 0));
                    FrmCatalogo.pass_nomEdi = String.valueOf(TblRegEdi.getValueAt(fila, 1));
                }
            }
        });
        
        if(evt.getClickCount() == 2) {
            this.dispose();
        }
    }//GEN-LAST:event_TblRegEdiMouseClicked

    private void TblRegClasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegClasiMouseClicked
        // TODO add your handling code here:
        this.TblRegClasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = TblRegClasi.getSelectedRow();
                    FrmCatalogo.pass_codClasi = String.valueOf(TblRegClasi.getValueAt(fila , 0));
                    FrmCatalogo.pass_nomClasi = String.valueOf(TblRegClasi.getValueAt(fila, 1));
                }
            }
        });
        
        if(evt.getClickCount() == 2) {
            this.dispose();
        }
    }//GEN-LAST:event_TblRegClasiMouseClicked

    private void TblRegAutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegAutMouseClicked
        // TODO add your handling code here:
        this.TblRegAut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = TblRegAut.getSelectedRow();
                    FrmCatalogo.pass_codAut = String.valueOf(TblRegAut.getValueAt(fila , 0));
                    FrmCatalogo.pass_nomAut = String.valueOf(TblRegAut.getValueAt(fila, 1));
                }
            }
        });
        
        if(evt.getClickCount() == 2) {
            this.dispose();
        }
    }//GEN-LAST:event_TblRegAutMouseClicked

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
            java.util.logging.Logger.getLogger(FrmEscogerDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEscogerDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEscogerDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEscogerDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEscogerDatos(5).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblbuscarEditorial;
    private javax.swing.JLabel LblbuscarEditorial1;
    private javax.swing.JLabel LblbuscarEditorial2;
    private javax.swing.JLabel LblbuscarEditorial3;
    private javax.swing.JPanel PanelAutor;
    private javax.swing.JPanel PanelClasificacion;
    private javax.swing.JPanel PanelEditorial;
    private javax.swing.JPanel PanelUbicacion;
    private javax.swing.JTable TblRegAut;
    private javax.swing.JTable TblRegClasi;
    private javax.swing.JTable TblRegEdi;
    private javax.swing.JTable TblRegUbi;
    private javax.swing.JTextField TfDatoBuscarAutorEsc;
    private javax.swing.JTextField TfDatoBuscarClasificacionEsc;
    private javax.swing.JTextField TfDatoBuscarEditorial3;
    private javax.swing.JTextField TfDatoBuscarEditorialEsc;
    private javax.swing.JTabbedPane TpEscoger;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
