/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios;

/**
 *
 * @author Yamila Karim Conrado
 */
public class FrmRegistroPersonas extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmRegistroPersonas
     */
    public FrmRegistroPersonas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgCategoria = new javax.swing.ButtonGroup();
        LblTitulo = new javax.swing.JLabel();
        TpPersonas = new javax.swing.JTabbedPane();
        PanelRegistro = new javax.swing.JPanel();
        LblBuscarPersona = new javax.swing.JLabel();
        TfDatoPersona = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblRegistroPersonas = new javax.swing.JTable();
        PanelAgregar = new javax.swing.JPanel();
        LblCedula = new javax.swing.JLabel();
        LblNom = new javax.swing.JLabel();
        LblApe = new javax.swing.JLabel();
        LblTel = new javax.swing.JLabel();
        TfCedula = new javax.swing.JTextField();
        TfNombres = new javax.swing.JTextField();
        TfApellidos = new javax.swing.JTextField();
        TfTelefono = new javax.swing.JTextField();
        PanelCat = new javax.swing.JPanel();
        RbEstudiante = new javax.swing.JRadioButton();
        RbDocente = new javax.swing.JRadioButton();
        RbPersonal = new javax.swing.JRadioButton();
        LblCat = new javax.swing.JLabel();
        BtnResetRbs = new javax.swing.JButton();
        LblDatos = new javax.swing.JLabel();
        LblChangeCat = new javax.swing.JLabel();
        TfCatId = new javax.swing.JTextField();
        ToolBarClud = new javax.swing.JToolBar();
        BtnLimpiar = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Gestión Personas");

        LblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTitulo.setText("Gestión de Personas");
        LblTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblBuscarPersona.setText("Buscar:");

        TblRegistroPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TblRegistroPersonas);

        javax.swing.GroupLayout PanelRegistroLayout = new javax.swing.GroupLayout(PanelRegistro);
        PanelRegistro.setLayout(PanelRegistroLayout);
        PanelRegistroLayout.setHorizontalGroup(
            PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(LblBuscarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(TfDatoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRegistroLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelRegistroLayout.setVerticalGroup(
            PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblBuscarPersona)
                    .addComponent(TfDatoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TpPersonas.addTab("Registro", PanelRegistro);

        PanelAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblCedula.setText("Cédula: ");

        LblNom.setText("Nombres: ");

        LblApe.setText("Apellidos: ");

        LblTel.setText("Teléfono: ");

        TfCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfCedulaActionPerformed(evt);
            }
        });

        PanelCat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        BgCategoria.add(RbEstudiante);
        RbEstudiante.setText("Estudiante");
        RbEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbEstudianteActionPerformed(evt);
            }
        });

        BgCategoria.add(RbDocente);
        RbDocente.setText("Docente");
        RbDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbDocenteActionPerformed(evt);
            }
        });

        BgCategoria.add(RbPersonal);
        RbPersonal.setText("Personal Activo");
        RbPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbPersonalActionPerformed(evt);
            }
        });

        LblCat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblCat.setText("Categoría");

        BtnResetRbs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refresh-button (1).png"))); // NOI18N
        BtnResetRbs.setToolTipText("Reestablecer ");
        BtnResetRbs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnResetRbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetRbsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCatLayout = new javax.swing.GroupLayout(PanelCat);
        PanelCat.setLayout(PanelCatLayout);
        PanelCatLayout.setHorizontalGroup(
            PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCatLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelCatLayout.createSequentialGroup()
                        .addGroup(PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RbDocente)
                            .addComponent(RbPersonal))
                        .addGap(51, 51, 51))
                    .addGroup(PanelCatLayout.createSequentialGroup()
                        .addGroup(PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCatLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(LblCat))
                            .addComponent(RbEstudiante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnResetRbs, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        PanelCatLayout.setVerticalGroup(
            PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCatLayout.createSequentialGroup()
                        .addComponent(LblCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbEstudiante))
                    .addComponent(BtnResetRbs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(RbDocente)
                .addGap(11, 11, 11)
                .addComponent(RbPersonal)
                .addContainerGap())
        );

        LblDatos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblDatos.setText("Datos de Persona");

        LblChangeCat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblChangeCat.setText("Sin Categoría...");
        LblChangeCat.setEnabled(false);

        TfCatId.setEnabled(false);
        TfCatId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfCatIdActionPerformed(evt);
            }
        });

        ToolBarClud.setRollover(true);

        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiar.setToolTipText("Limpiar");
        BtnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnLimpiar);

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregar.setToolTipText("Guardar");
        BtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnAgregar);

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditar.setToolTipText("Editar");
        BtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditar.setEnabled(false);
        ToolBarClud.add(BtnEditar);

        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminar.setToolTipText("Eliminar");
        BtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminar.setEnabled(false);
        ToolBarClud.add(BtnEliminar);

        javax.swing.GroupLayout PanelAgregarLayout = new javax.swing.GroupLayout(PanelAgregar);
        PanelAgregar.setLayout(PanelAgregarLayout);
        PanelAgregarLayout.setHorizontalGroup(
            PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarLayout.createSequentialGroup()
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelAgregarLayout.createSequentialGroup()
                                .addComponent(LblNom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAgregarLayout.createSequentialGroup()
                                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblApe)
                                    .addComponent(LblTel)
                                    .addComponent(LblCedula, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TfCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(TfApellidos)
                                    .addComponent(TfTelefono)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAgregarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblChangeCat)
                            .addComponent(TfCatId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(LblDatos)))
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(PanelCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ToolBarClud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        PanelAgregarLayout.setVerticalGroup(
            PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarLayout.createSequentialGroup()
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(LblDatos)
                        .addGap(18, 18, 18)
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TfCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblCedula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblNom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblApe)
                            .addComponent(TfApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblTel)
                            .addComponent(TfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(PanelCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addComponent(LblChangeCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TfCatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ToolBarClud, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        TpPersonas.addTab("Agregar/Editar", PanelAgregar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(TpPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(TpPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TfCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfCedulaActionPerformed

    private void RbEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbEstudianteActionPerformed
        // TODO add your handling code here:
        if (this.RbEstudiante.isSelected()) {
            this.LblChangeCat.setEnabled(true);
            this.LblChangeCat.setText("CIF Estudiante:");
            this.TfCatId.setEnabled(true);
        }
    }//GEN-LAST:event_RbEstudianteActionPerformed

    private void RbDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbDocenteActionPerformed
        // TODO add your handling code here:
        if (this.RbDocente.isSelected()) {
            this.LblChangeCat.setEnabled(true);
            this.LblChangeCat.setText("CIF Docente:");
            this.TfCatId.setEnabled(true);
        }
    }//GEN-LAST:event_RbDocenteActionPerformed

    private void RbPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbPersonalActionPerformed
        // TODO add your handling code here:
        if (this.RbPersonal.isSelected()) {
            this.LblChangeCat.setEnabled(true);
            this.LblChangeCat.setText("ID Personal:");
            this.TfCatId.setEnabled(true);
        }
    }//GEN-LAST:event_RbPersonalActionPerformed

    private void BtnResetRbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetRbsActionPerformed
        // TODO add your handling code here:
        this.BgCategoria.clearSelection();      //limpia selección de los tres radio buttons (button group)

        //reestablecer campo extra opcional (categoria persona)
        this.LblChangeCat.setEnabled(false);
        this.LblChangeCat.setText("Sin Categoría...");
        this.TfCatId.setText("");
        this.TfCatId.setEnabled(false);

    }//GEN-LAST:event_BtnResetRbsActionPerformed

    private void TfCatIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfCatIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfCatIdActionPerformed

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void limpiar() {
        this.TfCedula.setText("");
        this.TfNombres.setText("");
        this.TfApellidos.setText("");
        this.TfTelefono.setText("");
        this.TfCedula.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BgCategoria;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnResetRbs;
    private javax.swing.JLabel LblApe;
    private javax.swing.JLabel LblBuscarPersona;
    private javax.swing.JLabel LblCat;
    private javax.swing.JLabel LblCedula;
    private javax.swing.JLabel LblChangeCat;
    private javax.swing.JLabel LblDatos;
    private javax.swing.JLabel LblNom;
    private javax.swing.JLabel LblTel;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelCat;
    private javax.swing.JPanel PanelRegistro;
    private javax.swing.JRadioButton RbDocente;
    private javax.swing.JRadioButton RbEstudiante;
    private javax.swing.JRadioButton RbPersonal;
    private javax.swing.JTable TblRegistroPersonas;
    private javax.swing.JTextField TfApellidos;
    private javax.swing.JTextField TfCatId;
    private javax.swing.JTextField TfCedula;
    private javax.swing.JTextField TfDatoPersona;
    private javax.swing.JTextField TfNombres;
    private javax.swing.JTextField TfTelefono;
    private javax.swing.JToolBar ToolBarClud;
    private javax.swing.JTabbedPane TpPersonas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
