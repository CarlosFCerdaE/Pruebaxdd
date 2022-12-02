/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios;

import dao.*;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import entidades.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import complementos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Diego
 */
public class FrmRegistroPersonas extends javax.swing.JInternalFrame {
    
    int f = 0;
    private String cod, nom, ape, num, tip, cif;
    
    
    DPersona dPersona = new DPersona();
    DEstudiante dEstudiante = new DEstudiante();
    DDocente dDocente = new DDocente();
    DListadoPersonas dListado = new DListadoPersonas();
    DPersonalActivo dPersonal = new DPersonalActivo();
    DEstudiantexCarrera dExC = new DEstudiantexCarrera();
    DPersonalActivoxCargo dPxC = new DPersonalActivoxCargo();
    DDocentexFacultad dDxF = new DDocentexFacultad();
    ArrayList<ListadoPersonas> listaPersonas = new ArrayList<>();
    ArrayList<String> idcif = new ArrayList<>();
    ArrayList<String> cifcarr = new ArrayList<>();
    ArrayList<Carrera> carr = new ArrayList<>();
    ArrayList<Facultad> fal = new ArrayList<>();
    ArrayList<Cargo> carg = new ArrayList<>();
    
    TableRowSorter filtroTablaPersonas;

    /**
     * Creates new form FrmRegistroPersonas
     */
    public FrmRegistroPersonas() {
        initComponents();
        llenarTablaPersonas();
        
    }
    
    private boolean verificarDatosVacios() {
        
        if (jCBRoleID.getSelectedIndex()==0 
                && this.TfCedula.getText().equals("") || this.TfCedula.getText().length() == 0
                && this.TfNombres.getText().equals("") || this.TfNombres.getText().length() == 0
                && this.TfApellidos.getText().equals("") || this.TfApellidos.getText().length() == 0
                && this.TfTelefono.getText().equals("") || this.TfTelefono.getText().length() == 0
                && this.TfCatId.getText().equals("") || this.TfCatId.getText().length() == 0) {
            JOptionPane.showMessageDialog(this , "Por favor no dejar espacios vacios.",
                    "Estudiante", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        this.TfCedula.requestFocus();
        
        return true;
    }
    
    private void llenarListaPersonas() {
        if(listaPersonas.isEmpty())
            listaPersonas.clear();
        
        listaPersonas = dListado.listarListadoPersonas();
    }
    
    private void actualizarTablaPersonas() {
        llenarTablaPersonas();
        this.TpPersonas.setSelectedIndex(0);
        limpiar();
    }
    
    private void limpiarbuton(){
        this.BgCategoria.clearSelection();      //limpia selección de los tres radio buttons (button group)

        //reestablecer campo extra opcional (categoria persona)
        this.LblCatID.setEnabled(false);
        this.LblRoleID.setEnabled(false);
        
        this.LblCatID.setText("Sin Categoría...");
        this.LblRoleID.setText("Sin Categoría...");
        
        this.TfCatId.setText("");
        this.TfCatId.setEnabled(false);
        
        this.jCBRoleID.setSelectedIndex(0);
        this.jCBRoleID.setEnabled(false);
        this.jCBRoleID1.setSelectedIndex(0);
        this.jCBRoleID1.setEnabled(false);
    }
    
    private void limpiar() {
        this.TfCedula.setText("");
        this.TfNombres.setText("");
        this.TfApellidos.setText("");
        this.TfTelefono.setText("");
        limpiarbuton();
        this.TfCedula.requestFocus();
        this.TfCedula.setEnabled(true);
        this.BtnAgregar.setEnabled(true);
        this.BtnEditar.setEnabled(false);
        this.BtnEliminar.setEnabled(false);
    }
    
    private void llenarTablaPersonas() {
        llenarListaPersonas();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"ID", "Nombre", "Apellido", "Telefono", "Tipo"};
        
        dtm.setColumnIdentifiers(titulos);
        
        for (ListadoPersonas pers: listaPersonas) {
            
            Object[] fila = new Object[] {
                pers.getId_persona(), 
                pers.getNombres(),
                pers.getApellidos(),
                pers.getTelefono(),
                pers.getCif()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegistroPersonas.setModel(dtm);
    }
    
    private void ubicarDatosPersonas() {
        int fila = this.TblRegistroPersonas.getSelectedRow();
        
        Object id_persona = this.TblRegistroPersonas.getValueAt(fila, 0);
        Object nombres = this.TblRegistroPersonas.getValueAt(fila, 1);
        Object apellidos = this.TblRegistroPersonas.getValueAt(fila, 2);
        Object telefono = this.TblRegistroPersonas.getValueAt(fila, 3);
        Object tipo = this.TblRegistroPersonas.getValueAt(fila, 4);
        
        cod = String.valueOf(id_persona);
        nom = String.valueOf(nombres);
        ape = String.valueOf(apellidos);
        num = String.valueOf(telefono);
        tip = String.valueOf(tipo);
        
        
        
        
        //this.TfCodClasi.setEnabled(false);
        this.TfCedula.setText(cod);
        this.TfNombres.setText(nom);
        this.TfApellidos.setText(ape);
        this.TfTelefono.setText(num);
        
        if(tip.equals("Estudiante")){
            this.RbEstudiante.setSelected(true);
            this.jCBRoleID.setEnabled(true);
            this.jCBRoleID1.setEnabled(true);
            for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
            }
            for(int i=jCBRoleID1.getItemCount()-1;i>0;i--){
            jCBRoleID1.removeItemAt(i);
            }
            carreraCBllenar();
            this.LblCatID.setText("CIF Estudiante:");
            this.LblRoleID.setText("Carrera:");
            
            //obteniendo cif
            idcif = dEstudiante.listarCif(cod);
            cif = String.valueOf(idcif);
            String cifNew = cif.replace("[", "");
            String cifNew2 = cifNew.replace("]", "");
            this.TfCatId.setText(cifNew2);
            
            //obteniendo carreras
            cifcarr = dExC.listarCarrera(cifNew2);
            
            String carr1 = String.valueOf(cifcarr.get(0));
            int car1 = Integer.valueOf(carr1);
            this.jCBRoleID.setSelectedIndex(car1);
            
            if(cifcarr.size()>1){
                String carr2 = String.valueOf(cifcarr.get(1));
                int car2 = Integer.valueOf(carr2);
                this.jCBRoleID1.setSelectedIndex(car2);
            }
            
            
        }
        
        if(tip.equals("Docente")){
            this.RbDocente.setSelected(true);
            this.jCBRoleID.setEnabled(true);
            this.jCBRoleID1.setEnabled(true);
            for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
            }
            for(int i=jCBRoleID1.getItemCount()-1;i>0;i--){
            jCBRoleID1.removeItemAt(i);
            }
            facultadCBllenar();
            this.LblCatID.setText("CIF Docente:");
            this.LblRoleID.setText("Facultad:");
            
            //obteniendo cif
            idcif = dDocente.listarCif(cod);
            cif = String.valueOf(idcif);
            String cifNew = cif.replace("[", "");
            String cifNew2 = cifNew.replace("]", "");
            this.TfCatId.setText(cifNew2);
            
            
            //obteniendo carreras
            cifcarr = dDxF.listarFacultad(cifNew2);
            
            String carr1 = String.valueOf(cifcarr.get(0));
            int car1 = Integer.valueOf(carr1);
            this.jCBRoleID.setSelectedIndex(car1);
            if(cifcarr.size()>1){
                String carr2 = String.valueOf(cifcarr.get(1));
                int car2 = Integer.valueOf(carr2);
                this.jCBRoleID1.setSelectedIndex(car2);
            }
        }
        
        if(tip.equals("Personal Activo")){
            this.RbPersonal.setSelected(true);
            this.jCBRoleID.setEnabled(true);
            this.jCBRoleID1.setEnabled(true);
            for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
            }
            for(int i=jCBRoleID1.getItemCount()-1;i>0;i--){
            jCBRoleID1.removeItemAt(i);
            }
            cargoCBllenar();
            this.LblCatID.setText("ID Personal:");
            this.LblRoleID.setText("Cargo:");
            
            //obteniendo cif
            idcif = dPersonal.listarCif(cod);
            cif = String.valueOf(idcif);
            String cifNew = cif.replace("[", "");
            String cifNew2 = cifNew.replace("]", "");
            this.TfCatId.setText(cifNew2);
            
            //obteniendo carreras
            cifcarr = dPxC.listarCargo(cifNew2);
            String carr1 = String.valueOf(cifcarr.get(0));
            int car1 = Integer.valueOf(carr1);
            this.jCBRoleID.setSelectedIndex(car1);
            
            if(cifcarr.size()>1){
                String carr2 = String.valueOf(cifcarr.get(1));
                int car2 = Integer.valueOf(carr2);
                this.jCBRoleID1.setSelectedIndex(car2);
            }
        }
        
        
        this.TfCedula.setEnabled(false);
        this.TfCatId.setEnabled(false);
        
        
        this.BtnAgregar.setEnabled(false);
        this.BtnEditar.setEnabled(true);
        this.BtnEliminar.setEnabled(true);
    }
    
    public void carreraCBllenar(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String databaseURL = "jdbc:sqlserver://localhost;"
            +"databaseName=PRESTAMOSBIBLIOTECAUAMPAC;"
            +"Persist Security Info=True;";
            Connection con = DriverManager.getConnection(databaseURL, "sa", "123");
            Statement stat = con.createStatement();
            String selectQuery="select nombre_carrera from [RRHH].[Carrera]";
            ResultSet rs=stat.executeQuery(selectQuery);
            while(rs.next()){
                jCBRoleID.addItem(rs.getString("nombre_carrera"));
                jCBRoleID1.addItem(rs.getString("nombre_carrera"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void cargoCBllenar(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String databaseURL = "jdbc:sqlserver://localhost;"
            +"databaseName=PRESTAMOSBIBLIOTECAUAMPAC;"
            +"Persist Security Info=True;";
            Connection con = DriverManager.getConnection(databaseURL, "sa", "123");
            Statement stat = con.createStatement();
            String selectQuery="select nombre_cargo from [RRHH].[Cargo]";
            ResultSet rs=stat.executeQuery(selectQuery);
            while(rs.next()){
                jCBRoleID.addItem(rs.getString("nombre_cargo"));
                jCBRoleID1.addItem(rs.getString("nombre_cargo"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void facultadCBllenar(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String databaseURL = "jdbc:sqlserver://localhost;"
            +"databaseName=PRESTAMOSBIBLIOTECAUAMPAC;"
            +"Persist Security Info=True;";
            Connection con = DriverManager.getConnection(databaseURL, "sa", "123");
            Statement stat = con.createStatement();
            String selectQuery="select nombre_facultad from [RRHH].[facultad]";
            ResultSet rs=stat.executeQuery(selectQuery);
            while(rs.next()){
                jCBRoleID.addItem(rs.getString("nombre_facultad"));
                jCBRoleID1.addItem(rs.getString("nombre_facultad"));
            }
        }
        catch(Exception e){
            System.out.println(e);
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
        LblDatos = new javax.swing.JLabel();
        LblCatID = new javax.swing.JLabel();
        TfCatId = new javax.swing.JTextField();
        ToolBarClud = new javax.swing.JToolBar();
        BtnLimpiar = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        LblRoleID = new javax.swing.JLabel();
        jCBRoleID = new javax.swing.JComboBox<>();
        jCBRoleID1 = new javax.swing.JComboBox<>();

        setClosable(true);
        setResizable(true);
        setTitle("Gestión Personas");

        LblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTitulo.setText("Gestión de Personas");
        LblTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblBuscarPersona.setText("Buscar:");

        TfDatoPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoPersonaKeyTyped(evt);
            }
        });

        TblRegistroPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegistroPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegistroPersonasMouseClicked(evt);
            }
        });
        TblRegistroPersonas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegistroPersonasKeyReleased(evt);
            }
        });
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

        javax.swing.GroupLayout PanelCatLayout = new javax.swing.GroupLayout(PanelCat);
        PanelCat.setLayout(PanelCatLayout);
        PanelCatLayout.setHorizontalGroup(
            PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCatLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RbDocente)
                    .addComponent(RbPersonal)
                    .addGroup(PanelCatLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(LblCat))
                    .addComponent(RbEstudiante))
                .addGap(51, 51, 51))
        );
        PanelCatLayout.setVerticalGroup(
            PanelCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LblCat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbEstudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(RbDocente)
                .addGap(11, 11, 11)
                .addComponent(RbPersonal)
                .addContainerGap())
        );

        LblDatos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblDatos.setText("Datos de Persona");

        LblCatID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblCatID.setText("Sin Categoría...");
        LblCatID.setEnabled(false);

        TfCatId.setEnabled(false);
        TfCatId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfCatIdActionPerformed(evt);
            }
        });

        ToolBarClud.setRollover(true);

        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiar.setToolTipText("Limpiar");
        BtnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnLimpiar);

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregar.setToolTipText("Guardar");
        BtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnAgregar);

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditar.setToolTipText("Editar");
        BtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnEditar.setEnabled(false);
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnEditar);

        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminar.setToolTipText("Eliminar");
        BtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnEliminar.setEnabled(false);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        ToolBarClud.add(BtnEliminar);

        LblRoleID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblRoleID.setText("Sin Categoría...");
        LblRoleID.setEnabled(false);

        jCBRoleID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escojer..." }));
        jCBRoleID.setEnabled(false);
        jCBRoleID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCBRoleIDMouseClicked(evt);
            }
        });
        jCBRoleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBRoleIDActionPerformed(evt);
            }
        });

        jCBRoleID1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escojer..." }));
        jCBRoleID1.setEnabled(false);
        jCBRoleID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBRoleID1ActionPerformed(evt);
            }
        });

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
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(LblDatos))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAgregarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LblCatID)
                            .addComponent(TfCatId, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(LblRoleID)
                            .addComponent(jCBRoleID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBRoleID1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(PanelAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(PanelCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAgregarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ToolBarClud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
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
                        .addComponent(LblCatID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TfCatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ToolBarClud, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(LblRoleID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBRoleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCBRoleID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
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
        for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
        }
        for(int i=jCBRoleID1.getItemCount()-1;i>0;i--){
            jCBRoleID1.removeItemAt(i);
        }
        
        this.LblCatID.setEnabled(true);
        this.LblCatID.setText("CIF Estudiante:");
        this.TfCatId.setEnabled(true);
        
        this.LblRoleID.setEnabled(true);
        this.LblRoleID.setText("Carrera:");
        this.jCBRoleID.setEnabled(true);
        this.jCBRoleID1.setEnabled(true);
        //this.jCBRoleID1.setEnabled(true);
        carreraCBllenar();
        
    }//GEN-LAST:event_RbEstudianteActionPerformed

    private void RbDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbDocenteActionPerformed
        // TODO add your handling code here:
        for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
        }
        for(int i=jCBRoleID1.getItemCount()-1;i>0;i--){
            jCBRoleID1.removeItemAt(i);
        }
        
        this.LblCatID.setEnabled(true);
        this.LblCatID.setText("CIF Docente:");
        this.TfCatId.setEnabled(true);
        
        this.LblRoleID.setEnabled(true);
        this.LblRoleID.setText("Facultad:");
        this.jCBRoleID.setEnabled(true);
        this.jCBRoleID1.setEnabled(true);
        //this.jCBRoleID1.setEnabled(false);
        facultadCBllenar();
    }//GEN-LAST:event_RbDocenteActionPerformed

    private void RbPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbPersonalActionPerformed
        // TODO add your handling code here:
        for(int i=jCBRoleID.getItemCount()-1;i>0;i--){
            jCBRoleID.removeItemAt(i);
        }
        this.LblCatID.setEnabled(true);
        this.LblCatID.setText("ID Personal:");
        this.TfCatId.setEnabled(true);
        
        this.LblRoleID.setEnabled(true);
        this.LblRoleID.setText("Cargo:");
        this.jCBRoleID.setEnabled(true);
        this.jCBRoleID1.setEnabled(true);
        cargoCBllenar();
        
    }//GEN-LAST:event_RbPersonalActionPerformed

    private void TfCatIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfCatIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfCatIdActionPerformed

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        // TODO add your handling code here:
        ArrayList<Carrera> carrera = new ArrayList<>();
        ArrayList<Facultad> facultad = new ArrayList<>();
        ArrayList<Cargo> cargo = new ArrayList<>();
        this.verificarDatosVacios();
        
        if (RbEstudiante.isSelected()){
            f = 1;
        }
        
        if (RbDocente.isSelected()){
            f = 2;
        }
        
        if (RbPersonal.isSelected()){
            f = 3;
        }
        
        if (f == 1){
            try {
                //Estudiante
                if(this.verificarDatosVacios()==true){
                    if(this.jCBRoleID1.getSelectedIndex()!=this.jCBRoleID.getSelectedIndex() && this.jCBRoleID.getSelectedIndex()!=0){
                        int l = jCBRoleID.getSelectedIndex();
                        Carrera test1 = new Carrera(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                        //test1.setNombre_carrera(jCBRoleID.getSelectedItem().toString());
                        //test1.setCod_carrera(Integer.toString(l));
                        String n = test1.getNombre_carrera()+ " " + test1.getCod_carrera();
                        System.out.println(n);
                        carrera.add(0, test1);

                        if(this.jCBRoleID1.getSelectedIndex()!= 0){
                            int k = jCBRoleID1.getSelectedIndex();
                            Carrera test2 = new Carrera(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                            //test1.setNombre_carrera(jCBRoleID.getSelectedItem().toString());
                            //test1.setCod_carrera(Integer.toString(l));
                            String m = test2.getNombre_carrera()+ " " + test2.getCod_carrera();
                            System.out.println(m);
                            carrera.add(0, test2);
                        }




                        Estudiante a = new Estudiante(
                            this.TfCatId.getText(),
                            carrera,
                            this.TfCedula.getText(),
                            this.TfNombres.getText(),
                            this.TfApellidos.getText(),
                            this.TfTelefono.getText()
                        );

                        if (dEstudiante.guardarEstudiante(a)) {
                            JOptionPane.showMessageDialog(this, "Registro Guardado.",
                                "Estudiante", JOptionPane.INFORMATION_MESSAGE);
                            actualizarTablaPersonas();

                        } else {
                            JOptionPane.showMessageDialog(this, "Error al guardar",
                                "Estudiante", JOptionPane.WARNING_MESSAGE);
                            actualizarTablaPersonas();
                        }
                    }else{
                                JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion en el primer cuadro y que no sea repetida.",
                            "Estudiante", JOptionPane.WARNING_MESSAGE);
                            }
                }
            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
            
        }
        
        if(f==2){
            try {
                
                    //Docente
                    if(this.verificarDatosVacios()==true){
                        if(this.jCBRoleID1.getSelectedIndex()!=this.jCBRoleID.getSelectedIndex() && this.jCBRoleID.getSelectedIndex()!=0){
                    int l = jCBRoleID.getSelectedIndex();
                    Facultad test1 = new Facultad(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                    String n = test1.getNombre_facultad() + " " + test1.getCod_facultad();
                    System.out.println(n);
                    facultad.add(0, test1);
                    
                    if(this.jCBRoleID1.getSelectedIndex()!= 0){
                        int k = jCBRoleID1.getSelectedIndex();
                        Facultad test2 = new Facultad(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                        String m = test2.getNombre_facultad()+ " " + test2.getCod_facultad();
                        System.out.println(m);
                        facultad.add(0, test2);
                    }


                    Docente a = new Docente(
                        this.TfCatId.getText(),
                        facultad,
                        this.TfCedula.getText(),
                        this.TfNombres.getText(),
                        this.TfApellidos.getText(),
                        this.TfTelefono.getText()
                    );

                    if (dDocente.guardarDocente(a)) {
                        JOptionPane.showMessageDialog(this, "Registro Guardado.",
                            "Docente", JOptionPane.INFORMATION_MESSAGE);
                        actualizarTablaPersonas();

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar",
                            "Docente", JOptionPane.WARNING_MESSAGE);
                        actualizarTablaPersonas();
                    }
                    }else{
                            JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion en el primer cuadro y que no sea repetida.",
                    "Docente", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (HeadlessException ex) {
                    System.out.println("Error al intentar guardar: " + ex.getMessage());
                }
        }
            if(f==3){
                try {
                    if(this.verificarDatosVacios()==true){
                        if(this.jCBRoleID1.getSelectedIndex()!=this.jCBRoleID.getSelectedIndex() && this.jCBRoleID.getSelectedIndex()!=0){
                    //PersonalActivo
                    int l = jCBRoleID.getSelectedIndex();
                    Cargo test1 = new Cargo(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                    //test1.setNombre_cargo(jCBRoleID.getSelectedItem().toString());
                    //test1.setCod_cargo(Integer.toString(l));
                    String n = test1.getNombre_cargo()+ " " + test1.getCod_cargo();
                    System.out.println(n);
                    cargo.add(0, test1);
                    
                    if(this.jCBRoleID1.getSelectedIndex()!= 0){
                        int k = jCBRoleID1.getSelectedIndex();
                        Cargo test2 = new Cargo(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                        String m = test2.getNombre_cargo()+ " " + test2.getCod_cargo();
                        System.out.println(m);
                        cargo.add(0, test2);
                    }


                    PersonalActivo a = new PersonalActivo(
                        this.TfCatId.getText(),
                        cargo,
                        this.TfCedula.getText(),
                        this.TfNombres.getText(),
                        this.TfApellidos.getText(),
                        this.TfTelefono.getText()
                    );

                    if (dPersonal.guardarPersonalActivo(a)) {
                        JOptionPane.showMessageDialog(this, "Registro Guardado.",
                            "Cargo", JOptionPane.INFORMATION_MESSAGE);
                        actualizarTablaPersonas();

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar",
                            "Cargo", JOptionPane.WARNING_MESSAGE);
                        actualizarTablaPersonas();
                    }
                    } else{
                            JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion de Cargo.",
                    "Personal Activo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (HeadlessException ex) {
                    System.out.println("Error al intentar guardar: " + ex.getMessage());
                }
            }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
        actualizarTablaPersonas();
        TpPersonas.setSelectedIndex(0);
        
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void jCBRoleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBRoleIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBRoleIDActionPerformed

    private void TfDatoPersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoPersonaKeyTyped
        // TODO add your handling code here:
        this.TfDatoPersona.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaPersonas();

            }
        });
        //this.TpLibros.setSelectedIndex(1);

        filtroTablaPersonas = new TableRowSorter(this.TblRegistroPersonas.getModel());
        this.TblRegistroPersonas.setRowSorter(filtroTablaPersonas);
    }//GEN-LAST:event_TfDatoPersonaKeyTyped

    private void TblRegistroPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegistroPersonasMouseClicked
        // TODO add your handling code here:
        this.TblRegistroPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TpPersonas.setSelectedIndex(1);
                    ubicarDatosPersonas();
                }
            }
        });
    }//GEN-LAST:event_TblRegistroPersonasMouseClicked

    private void TblRegistroPersonasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegistroPersonasKeyReleased
        // TODO add your handling code here:
        if ((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosPersonas();
        }
    }//GEN-LAST:event_TblRegistroPersonasKeyReleased

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Persona", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dPersona.eliminarPersona(cod)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Persona", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Libro", JOptionPane.WARNING_MESSAGE);
                limpiar();
            }
        }
        limpiar();
        actualizarTablaPersonas();
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        // TODO add your handling code here:
        ArrayList<Carrera> carrera = new ArrayList<>();
        ArrayList<Facultad> facultad = new ArrayList<>();
        ArrayList<Cargo> cargo = new ArrayList<>();
        this.verificarDatosVacios();
        
        if (RbEstudiante.isSelected()){
            f = 1;
        }
        
        if (RbDocente.isSelected()){
            f = 2;
        }
        
        if (RbPersonal.isSelected()){
            f = 3;
        }
        
        if(f==1){
            try {
                //Estudiante
                if(this.verificarDatosVacios()==true){
                    if(this.jCBRoleID1.getSelectedIndex()!=this.jCBRoleID.getSelectedIndex() && this.jCBRoleID.getSelectedIndex()!=0){
                        int l = jCBRoleID.getSelectedIndex();
                        Carrera test1 = new Carrera(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                        //test1.setNombre_carrera(jCBRoleID.getSelectedItem().toString());
                        //test1.setCod_carrera(Integer.toString(l));
                        String n = test1.getNombre_carrera()+ " " + test1.getCod_carrera();
                        System.out.println(n);
                        carrera.add(0, test1);

                        if(this.jCBRoleID1.getSelectedIndex()!= 0){
                            int k = jCBRoleID1.getSelectedIndex();
                            Carrera test2 = new Carrera(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                            //test1.setNombre_carrera(jCBRoleID.getSelectedItem().toString());
                            //test1.setCod_carrera(Integer.toString(l));
                            String m = test2.getNombre_carrera()+ " " + test2.getCod_carrera();
                            System.out.println(m);
                            carrera.add(0, test2);
                        }




                        Estudiante a = new Estudiante(
                            this.TfCatId.getText(),
                            carrera,
                            this.TfCedula.getText(),
                            this.TfNombres.getText(),
                            this.TfApellidos.getText(),
                            this.TfTelefono.getText()
                        );

                        if (dEstudiante.editarEstudiante(a)) {
                            JOptionPane.showMessageDialog(this, "Registro Guardado.",
                                "Estudiante", JOptionPane.INFORMATION_MESSAGE);
                            actualizarTablaPersonas();

                        } else {
                            JOptionPane.showMessageDialog(this, "Error al guardar",
                                "Estudiante", JOptionPane.WARNING_MESSAGE);
                            actualizarTablaPersonas();
                        }
                    }else{
                                JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion en el primer cuadro y que no sea repetida.",
                            "Estudiante", JOptionPane.WARNING_MESSAGE);
                            }
                }
            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
        }
        if(f==2){
            try {
                
                    //Docente
                    if(this.verificarDatosVacios()==true){
                        if(this.jCBRoleID1.getSelectedIndex()!=this.jCBRoleID.getSelectedIndex() && this.jCBRoleID.getSelectedIndex()!=0){
                    int l = jCBRoleID.getSelectedIndex();
                    Facultad test1 = new Facultad(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                    String n = test1.getNombre_facultad() + " " + test1.getCod_facultad();
                    System.out.println(n);
                    facultad.add(0, test1);
                    
                    if(this.jCBRoleID1.getSelectedIndex()!= 0){
                        int k = jCBRoleID1.getSelectedIndex();
                        Facultad test2 = new Facultad(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                        String m = test2.getNombre_facultad()+ " " + test2.getCod_facultad();
                        System.out.println(m);
                        facultad.add(0, test2);
                    }


                    Docente a = new Docente(
                        this.TfCatId.getText(),
                        facultad,
                        this.TfCedula.getText(),
                        this.TfNombres.getText(),
                        this.TfApellidos.getText(),
                        this.TfTelefono.getText()
                    );

                    if (dDocente.editarDocente(a)) {
                        JOptionPane.showMessageDialog(this, "Registro Guardado.",
                            "Docente", JOptionPane.INFORMATION_MESSAGE);
                        actualizarTablaPersonas();

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar",
                            "Docente", JOptionPane.WARNING_MESSAGE);
                        actualizarTablaPersonas();
                    }
                    }else{
                            JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion en el primer cuadro y que no sea repetida.",
                    "Docente", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (HeadlessException ex) {
                    System.out.println("Error al intentar guardar: " + ex.getMessage());
                }
        }
        if(f==3){
            try {
                    if(this.verificarDatosVacios()==true){
                        if(this.jCBRoleID.getSelectedIndex()!=0){
                    //PersonalActivo
                    int l = jCBRoleID.getSelectedIndex();
                    Cargo test1 = new Cargo(Integer.toString(l), jCBRoleID.getSelectedItem().toString());
                    String n = test1.getNombre_cargo()+ " " + test1.getCod_cargo();
                    System.out.println(n);
                    cargo.add(0, test1);
                    
                    if(this.jCBRoleID1.getSelectedIndex()!= 0){
                        int k = jCBRoleID1.getSelectedIndex();
                        Cargo test2 = new Cargo(Integer.toString(k), jCBRoleID1.getSelectedItem().toString());
                        String m = test2.getNombre_cargo()+ " " + test2.getCod_cargo();
                        System.out.println(m);
                        cargo.add(0, test2);
                    }
                    
                    


                    PersonalActivo a = new PersonalActivo(
                        this.TfCatId.getText(),
                        cargo,
                        this.TfCedula.getText(),
                        this.TfNombres.getText(),
                        this.TfApellidos.getText(),
                        this.TfTelefono.getText()
                    );

                    if (dPersonal.editarPersonalActivo(a)) {
                        JOptionPane.showMessageDialog(this, "Registro Guardado.",
                            "Cargo", JOptionPane.INFORMATION_MESSAGE);
                        actualizarTablaPersonas();

                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar",
                            "Cargo", JOptionPane.WARNING_MESSAGE);
                        actualizarTablaPersonas();
                    }
                    } else{
                            JOptionPane.showMessageDialog(this , "Por favor, seleccionar una opcion de Cargo.",
                    "Personal Activo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (HeadlessException ex) {
                    System.out.println("Error al intentar guardar: " + ex.getMessage());
                }
        }
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void jCBRoleID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBRoleID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBRoleID1ActionPerformed

    private void jCBRoleIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBRoleIDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBRoleIDMouseClicked

    private void filtrarTablaPersonas() {
        filtroTablaPersonas.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoPersona.getText()
                , 1));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BgCategoria;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JLabel LblApe;
    private javax.swing.JLabel LblBuscarPersona;
    private javax.swing.JLabel LblCat;
    private javax.swing.JLabel LblCatID;
    private javax.swing.JLabel LblCedula;
    private javax.swing.JLabel LblDatos;
    private javax.swing.JLabel LblNom;
    private javax.swing.JLabel LblRoleID;
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
    private javax.swing.JComboBox<String> jCBRoleID;
    private javax.swing.JComboBox<String> jCBRoleID1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
