/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios;

import dao.*;
import entidades.*;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gchang110101
 */
public class FrmCatalogo extends javax.swing.JInternalFrame {

    private String cod;
    private String nom;
    
    private String isbn, titulo, mfn, cod_edi, nom_edi, cod_clasi, nom_clasi;
    
    private int pos = 0;
    private int setEditAdd = 0;
    
    //objetos editorial
    DEditorial dEditorial = new DEditorial();
    ArrayList<Editorial> listaEditoriales = new ArrayList<>();
    
    //objetos autor
    DAutor dAutor = new DAutor();
    ArrayList<Autor> listaAutores = new ArrayList<>();
    
    //objetos clasificacion
    DClasificacion dClasificacion = new DClasificacion();
    ArrayList<Clasificacion> listaClasificaciones = new ArrayList<>();
    
    //objetos ubicacion
    DUbicacion dUbicacion = new DUbicacion();
    ArrayList<Ubicacion> listaUbicaciones = new ArrayList<>();
    
    //objetos libro y ejemplar
    DLibro dLibro = new DLibro();
    DEjemplar dEjemplar = new DEjemplar();
    ArrayList<Libro> listaLibros = new ArrayList<>();
    ArrayList<Ejemplar> listaEjemplares = new ArrayList<>();
    ArrayList<Autor> listaLibroAutores = new ArrayList<>();
    
//ArrayList<Autor> copiaListaLibroAutores = new ArrayList<>();
    
    //objetos filtros tablas
    TableRowSorter filtroTablaEditorial;
    TableRowSorter filtroTablaAutores;
    TableRowSorter filtroTablaClasificaciones;
    TableRowSorter filtroTablaUbicaciones;
    TableRowSorter filtroTablaLibros;
    
    public static String pass_codEdi = "---";
    public static String pass_nomEdi = "---";
    public static String pass_codClasi = "---";
    public static String pass_nomClasi = "---";
    public static String pass_codAut = "---";
    public static String pass_nomAut = "---";
    
    /**
     * Creates new form FrmLibros
     */
    public FrmCatalogo() {
        initComponents();
        llenarTablaEditoriales();
        llenarTablaAutores();
        llenarTablaClasificaciones();
        llenarTablaUbicaciones();
        llenarTablaLibros();
    }
    
    //metodos limpiar tabs
    private void limpiarEditorial() {
        this.TfCodEditorial.setText("");
        this.TfNomEditorial.setText("");
        this.TfCodEditorial.requestFocus();
        this.TfCodEditorial.setEnabled(true);
    }
    
    private void limpiarAutor() {
        this.TfCodAutor.setText("");
        this.TfNomAutor.setText("");
        this.TfCodAutor.requestFocus();
        this.TfCodAutor.setEnabled(true);
    }
    
    private void limpiarClasificacion() {
        this.TfCodClasi.setText("");
        this.TfNomClasi.setText("");
        this.TfCodClasi.requestFocus();
        this.TfCodClasi.setEnabled(true);
    }
    
    private void limpiarUbicacion() {
        this.TfCodUbi.setText("");
        this.TfNomUbi.setText("");
        this.TfCodUbi.requestFocus();
        this.TfCodUbi.setEnabled(true);
    }
    
    private void limpiarLibro() {
        this.TfISBN.setText("");
        this.TfISBN.setEnabled(true);
        this.TfTitulo.setText("");
        this.TfMFN.setText("");
        this.TfMFN.setEnabled(true);
        this.TfEditorialCodEscoger.setText("");
        this.TfEditorialNomEscoger.setText("");
        this.TfClasificacionCodEscoger.setText("");
        this.TfClasificacionNomEscoger.setText("");
        this.TfAutoresCodEscoger.setText("");
        this.TfAutoresNomEscoger.setText("");
        this.listaLibroAutores.clear();
        this.BtnAutoresEscogerEditar.setEnabled(false);
        this.BtnAutoresEscogerDelete.setEnabled(false);
    }
    
    //metodos actualizar tablas tabs
    private void actualizarTablaAutores() {
        llenarTablaAutores();
        this.TpLibros.setSelectedIndex(0);
        limpiarAutor();
    }
    
    private void actualizarTablaEditoriales() {
        llenarTablaEditoriales();
        this.TpLibros.setSelectedIndex(1);
        limpiarEditorial();
    }
    
    private void actualizarTablaClasificaciones() {
        llenarTablaClasificaciones();
        this.TpLibros.setSelectedIndex(2);
        limpiarClasificacion();
    }
    
    private void actualizarTablaUbicaciones() {
        llenarTablaUbicaciones();
        this.TpLibros.setSelectedIndex(3);
        limpiarUbicacion();
    }
    
    private void actualizarTablaLibros() {
        llenarTablaLibros();
        this.TpLibros.setSelectedIndex(4);
        limpiarLibro();
    }
    
    //metodos actualizar botones cuando se hace update o delete en cada tab
    public void actualizarBotonesEditorialesUD() {
        this.BtnAgregarEdit.setEnabled(true);
        this.BtnEliminarEdit.setEnabled(false);
        this.BtnEditarEdit.setEnabled(false);
        this.TfCodEditorial.setEnabled(true);
    }
    
    public void actualizarBotonesAutoresUD() {
        this.BtnAgregarAutor.setEnabled(true);
        this.BtnEliminarAutor.setEnabled(false);
        this.BtnEditarAutor.setEnabled(false);
        this.TfCodAutor.setEnabled(true);
    }
    
    public void actualizarBotonesClasificacionesUD() {
        this.BtnAgregarClasi.setEnabled(true);
        this.BtnEliminarClasi.setEnabled(false);
        this.BtnEditarClasi.setEnabled(false);
        this.TfCodClasi.setEnabled(true);
    }
    
    public void actualizarBotonesUbicacionesUD() {
        this.BtnAgregarUbi.setEnabled(true);
        this.BtnEliminarUbi.setEnabled(false);
        this.BtnEditarUbi.setEnabled(false);
        this.TfCodUbi.setEnabled(true);
    }
    
    public void actualizarBotonesLibroUD() {
        this.BtnAgregarLibro.setEnabled(true);
        this.BtnEliminarLibro.setEnabled(false);
        this.BtnEditarLibro.setEnabled(false);
        this.BtnAutoresEscogerPrev.setEnabled(false);
        this.BtnAutoresEscogerNext.setEnabled(false);
        this.TfISBN.setEnabled(true);
        this.TfMFN.setEnabled(true);
        this.listaAutores.clear();
        pos = 0;
        this.LblAutoresEscogerAmount.setText(pos + " de " + this.listaAutores.size());
    }
    
    //métodos llenar array lists con datos de tablas
    private void llenarListaEditoriales() {
        if(listaEditoriales.isEmpty())
            listaEditoriales.clear();
        
        listaEditoriales = dEditorial.listarEditorial();
    }
    
    private void llenarListaAutores() {
        if(listaAutores.isEmpty())
            listaAutores.clear();
        
        listaAutores = dAutor.listarAutor();
    }
    
    private void llenarListaClasificaciones() {
        if(listaClasificaciones.isEmpty())
            listaClasificaciones.clear();
        
        listaClasificaciones = dClasificacion.listarClasificacion();
    }
    
    private void llenarListaUbicaciones() {
        if(listaUbicaciones.isEmpty())
            listaUbicaciones.clear();
        
        listaUbicaciones = dUbicacion.listarUbicacion();
    }
    
    private void llenarListaLibros() {
        if(listaLibros.isEmpty()) {
            listaLibros.clear();
        }
        
        listaLibros = dLibro.listarLibro();
    }
    
    /*
    private void llenarListaLibroAutores(String x) {
        if(listaLibroAutores.isEmpty()) {
            listaLibroAutores.clear();
        }
        
        listaLibroAutores = dLibro.listarAutor(x);
    }
    */
    
    //métodos llenar tablas tabs con sus datos respectivos
    private void llenarTablaEditoriales() {
        llenarListaEditoriales();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Código", "Nombre"};
        dtm.setColumnIdentifiers(titulos);
        
        for (Editorial e: listaEditoriales) {
            Object[] fila = new Object[] {
                e.getCod_editorial(),
                e.getNombre_editorial()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegEditoriales.setModel(dtm);
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
        
        for (Autor a: listaAutores) {
            Object[] fila = new Object[] {
                a.getCodigo_autor(),
                a.getNombre_autor()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegAutores.setModel(dtm);
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
        
        for (Clasificacion c: listaClasificaciones) {
            Object[] fila = new Object[] {
                c.getCod_clasificacion(),
                c.getNombre_clasificacion()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegClasificaciones.setModel(dtm);
    }
    
    private void llenarTablaUbicaciones() {
        llenarListaUbicaciones();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"Código", "Nombre"};
        dtm.setColumnIdentifiers(titulos);
        
        for (Ubicacion u: listaUbicaciones) {
            Object[] fila = new Object[] {
                u.getCod_ubicacion(),
                u.getNombre_ubi()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegUbicaciones.setModel(dtm);
    }
    
    private void llenarTablaLibros() {
        llenarListaLibros();
        DefaultTableModel dtm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        String titulos[] = {"ISBN", "Titulo", "MFN", "cod_autor", "nombre_autor", 
            "cod_clasificacion", "nombre_clasificacion", "cod_editorial", 
            "nombre_editorial", "copias"};
        
        dtm.setColumnIdentifiers(titulos);
        
        String line_cod;
        String line_nom;
        int cont = 0;
        
        for (Libro lib: listaLibros) {
            //"reset" variables
            line_cod = "";
            line_nom = "";
            cont = 0;
            
            for(Autor a: lib.getAutores()) {
                if (cont == lib.getAutores().size() - 1)
                        break;
                
                line_cod += a.getCodigo_autor();
                line_nom += a.getNombre_autor();
                
                if (cont != lib.getAutores().size() - 2) {
                    line_cod += ", ";
                    line_nom += ", ";
                }
                    
                cont++;
            }
            
            Object[] fila = new Object[] {
                lib.getIsbn(), 
                lib.getTitulo_libro(),
                lib.getMfn(),
                line_cod,
                line_nom, 
                lib.getClasificacion().getCod_clasificacion(),
                lib.getClasificacion().getNombre_clasificacion(),
                lib.getEditorial().getCod_editorial(),
                lib.getEditorial().getNombre_editorial() ,
                lib.getAutores().get(lib.getAutores().size() - 1).getNombre_autor()
            };
            dtm.addRow(fila);
        }
        
        this.TblRegLibros.setModel(dtm);
    }
    
    //métodos filtrar tablas tabs (non-case sensitive)
    private void filtrarTablaEditoriales() {
        filtroTablaEditorial.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarEdit.getText()
                , 1));
    }
    
    private void filtrarTablaAutores() {
        filtroTablaAutores.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarAutor.getText(),
                1));
    }
    
    private void filtrarTablaClasificaciones() {
        filtroTablaClasificaciones.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarClasi.getText(),
                1));
    }
    
    private void filtrarTablaUbicaciones() {
        filtroTablaUbicaciones.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarUbi.getText(),
                1));
    }
    
    private void filtrarTablaLibros() {
        filtroTablaLibros.setRowFilter(RowFilter.regexFilter("(?i)" + this.TfDatoBuscarLibro.getText(),
                1));
    }
    
    //métodos verificar datos vacios tabs
    private boolean verificarDatosVaciosEditorial() {
        if (this.TfCodEditorial.getText().equals("") || this.TfCodEditorial.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código" 
                + " no esté vacío.", "Editorial", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfNomEditorial.getText().equals("") || this.TfNomEditorial.getText().length() == 0) {
            JOptionPane.showMessageDialog(this , "Por favor verifique que el nombre" 
                + " no esté vacío.", "Editorial", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        this.TfNomEditorial.requestFocus();
        return true;
    }
    
    private boolean verificarDatosVaciosAutor() {
        if (this.TfCodAutor.getText().equals("") || this.TfCodAutor.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código" 
                + " no esté vacío.", "Autor", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfNomAutor.getText().equals("") || this.TfNomAutor.getText().length() == 0) {
            JOptionPane.showMessageDialog(this , "Por favor verifique que el nombre" 
                + " no esté vacío.", "Autor", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        this.TfNomAutor.requestFocus();
        return true;
    }
    
    private boolean verificarDatosVaciosClasificacion() {
        if (this.TfCodClasi.getText().equals("") || this.TfCodClasi.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código" 
                + " no esté vacío.", "Clasificacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfNomClasi.getText().equals("") || this.TfNomClasi.getText().length() == 0) {
            JOptionPane.showMessageDialog(this , "Por favor verifique que el nombre" 
                + " no esté vacío.", "Clasificacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        this.TfNomClasi.requestFocus();
        return true;
    }
    
    private boolean verificarDatosVaciosUbicacion() {
        if (this.TfCodUbi.getText().equals("") || this.TfCodUbi.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código" 
                + " no esté vacío.", "Ubicacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfNomUbi.getText().equals("") || this.TfNomUbi.getText().length() == 0) {
            JOptionPane.showMessageDialog(this , "Por favor verifique que el nombre" 
                + " no esté vacío.", "Ubicacion", JOptionPane.WARNING_MESSAGE);
            return false;

        }
        
        this.TfNomUbi.requestFocus();
        return true;
    }
    
    private boolean verificarDatosVaciosLibro() {
        if (this.TfISBN.getText().equals("") || this.TfISBN.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el ISBN" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfTitulo.getText().equals("") || this.TfTitulo.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el titulo" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfMFN.getText().equals("") || this.TfMFN.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el MFN" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfEditorialCodEscoger.getText().equals("") || this.TfEditorialCodEscoger.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código de editorial" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfEditorialNomEscoger.getText().equals("") || this.TfEditorialNomEscoger.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el nombre de editorial" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfClasificacionCodEscoger.getText().equals("") || this.TfClasificacionCodEscoger.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el código de clasificación" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.TfClasificacionNomEscoger.getText().equals("") || this.TfClasificacionNomEscoger.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que el nombre de clasificación" 
                + " no esté vacío.", "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (this.listaLibroAutores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que tenga por lo menos 1 autor", 
                "Libro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    //métodos ubicar datos tabs
    private void ubicarDatosEditorial() {
        int fila = this.TblRegEditoriales.getSelectedRow();
        
        Object id = this.TblRegEditoriales.getValueAt(fila, 0);
        Object name = this.TblRegEditoriales.getValueAt(fila, 1);
        
        cod = String.valueOf(id);
        nom = String.valueOf(name);
        
        this.TfCodEditorial.setEnabled(false);
        this.TfCodEditorial.setText(cod);
        
        this.TfNomEditorial.setText(nom);
        
        this.BtnAgregarEdit.setEnabled(false);
        this.BtnEditarEdit.setEnabled(true);
        this.BtnEliminarEdit.setEnabled(true);
        /*this.TfNomEditorial.requestFocus();*/
    }
    
    private void ubicarDatosAutores() {
        int fila = this.TblRegAutores.getSelectedRow();
        
        Object id = this.TblRegAutores.getValueAt(fila, 0);
        Object name = this.TblRegAutores.getValueAt(fila, 1);
        
        cod = String.valueOf(id);
        nom = String.valueOf(name);
        
        this.TfCodAutor.setEnabled(false);
        this.TfCodAutor.setText(cod);
        
        this.TfNomAutor.setText(nom);
        
        this.BtnAgregarAutor.setEnabled(false);
        this.BtnEditarAutor.setEnabled(true);
        this.BtnEliminarAutor.setEnabled(true);
       /* this.TfNomAutor.requestFocus();*/
    }
    
    private void ubicarDatosClasificacion() {
        int fila = this.TblRegClasificaciones.getSelectedRow();
        
        Object id = this.TblRegClasificaciones.getValueAt(fila, 0);
        Object name = this.TblRegClasificaciones.getValueAt(fila, 1);
        
        cod = String.valueOf(id);
        nom = String.valueOf(name);
        
        this.TfCodClasi.setEnabled(false);
        this.TfCodClasi.setText(cod);
        
        this.TfNomClasi.setText(nom);
        
        this.BtnAgregarClasi.setEnabled(false);
        this.BtnEditarClasi.setEnabled(true);
        this.BtnEliminarClasi.setEnabled(true);
       /* this.TfNomClasi.requestFocus();*/
    }
    
    private void ubicarDatosUbicacion() {
        int fila = this.TblRegUbicaciones.getSelectedRow();
        
        Object id = this.TblRegUbicaciones.getValueAt(fila, 0);
        Object name = this.TblRegUbicaciones.getValueAt(fila, 1);
        
        cod = String.valueOf(id);
        nom = String.valueOf(name);
        
        this.TfCodUbi.setEnabled(false);
        this.TfCodUbi.setText(cod);
        
        this.TfNomUbi.setText(nom);
        
        this.BtnAgregarUbi.setEnabled(false);
        this.BtnEditarUbi.setEnabled(true);
        this.BtnEliminarUbi.setEnabled(true);
       /* this.TfNomUbi.requestFocus();*/
    }
    
    private void ubicarDatosLibro() {
        int fila = this.TblRegLibros.getSelectedRow();
        pos = 0;
        
        Object book_isbn = this.TblRegLibros.getValueAt(fila, 0);
        Object book_title = this.TblRegLibros.getValueAt(fila, 1);
        Object book_mfn = this.TblRegLibros.getValueAt(fila, 2);
        Object book_category_code = this.TblRegLibros.getValueAt(fila, 5);
        Object book_category_name = this.TblRegLibros.getValueAt(fila, 6);
        Object book_editorial_code = this.TblRegLibros.getValueAt(fila, 7);
        Object book_editorial_name = this.TblRegLibros.getValueAt(fila, 8);
        
        isbn = String.valueOf(book_isbn);
        titulo = String.valueOf(book_title);
        mfn = String.valueOf(book_mfn);
        
        //se llena la lista con los autres de cada libro 
        //conseguido con listarAutor de DLibro (parámetro: ISBN)
        //(select * from vista where isbn like...)
        listaLibroAutores = dLibro.listarAutor(isbn);
        
        cod_clasi = String.valueOf(book_category_code);
        nom_clasi = String.valueOf(book_category_name);
        
        cod_edi = String.valueOf(book_editorial_code);
        nom_edi = String.valueOf(book_editorial_name);
        
        
        this.TfISBN.setText(isbn);
        this.TfISBN.setEnabled(false);
        
        this.TfTitulo.setText(titulo);
        
        this.TfMFN.setText(mfn);
        this.TfMFN.setEnabled(false);
        
        this.TfEditorialCodEscoger.setText(cod_edi);
        this.TfEditorialNomEscoger.setText(nom_edi);
        
        this.TfClasificacionCodEscoger.setText(cod_clasi);
        this.TfClasificacionNomEscoger.setText(nom_clasi);
        
        this.TfAutoresCodEscoger.setText(listaLibroAutores.get(0).getCodigo_autor());
        this.TfAutoresNomEscoger.setText(listaLibroAutores.get(0).getNombre_autor());
        
        this.LblAutoresEscogerAmount.setText(pos + 1 + " de " + listaLibroAutores.size());

        this.BtnAgregarLibro.setEnabled(false);
        this.BtnEditarLibro.setEnabled(true);
        this.BtnEliminarLibro.setEnabled(true);
        this.BtnAutoresEscogerPrev.setEnabled(true);
        this.BtnAutoresEscogerNext.setEnabled(true);
        this.BtnAutoresEscogerEditar.setEnabled(true);
        this.BtnAutoresEscogerDelete.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblLibros = new javax.swing.JLabel();
        TpLibros = new javax.swing.JTabbedPane();
        PanelListadoAutores = new javax.swing.JPanel();
        LblBuscarAutor = new javax.swing.JLabel();
        TfDatoBuscarAutor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblRegAutores = new javax.swing.JTable();
        LblCodAutores = new javax.swing.JLabel();
        TfCodAutor = new javax.swing.JTextField();
        LblNombreAutores = new javax.swing.JLabel();
        TfNomAutor = new javax.swing.JTextField();
        ToolbarCRUDEditorial1 = new javax.swing.JToolBar();
        BtnLimpiarAutor = new javax.swing.JButton();
        BtnAgregarAutor = new javax.swing.JButton();
        BtnEditarAutor = new javax.swing.JButton();
        BtnEliminarAutor = new javax.swing.JButton();
        LblAutorIcon = new javax.swing.JLabel();
        LblAutores = new javax.swing.JLabel();
        PanelListadoEditoriales = new javax.swing.JPanel();
        LblBuscarEdit = new javax.swing.JLabel();
        TfDatoBuscarEdit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblRegEditoriales = new javax.swing.JTable();
        LblCodEditorial = new javax.swing.JLabel();
        TfCodEditorial = new javax.swing.JTextField();
        LblNombreEditorial = new javax.swing.JLabel();
        TfNomEditorial = new javax.swing.JTextField();
        ToolbarCRUDEditorial = new javax.swing.JToolBar();
        BtnLimpiarEdit = new javax.swing.JButton();
        BtnAgregarEdit = new javax.swing.JButton();
        BtnEditarEdit = new javax.swing.JButton();
        BtnEliminarEdit = new javax.swing.JButton();
        LblEditorialIcon = new javax.swing.JLabel();
        LblEditoriales = new javax.swing.JLabel();
        PanelListadoClasificaciones = new javax.swing.JPanel();
        LblBuscarClas = new javax.swing.JLabel();
        TfDatoBuscarClasi = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblRegClasificaciones = new javax.swing.JTable();
        LblCodClasi = new javax.swing.JLabel();
        TfCodClasi = new javax.swing.JTextField();
        LblNombreClasi = new javax.swing.JLabel();
        TfNomClasi = new javax.swing.JTextField();
        ToolbarCRUDEditorial2 = new javax.swing.JToolBar();
        BtnLimpiarClasi = new javax.swing.JButton();
        BtnAgregarClasi = new javax.swing.JButton();
        BtnEditarClasi = new javax.swing.JButton();
        BtnEliminarClasi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        LblClasiIcon = new javax.swing.JLabel();
        LblClasificaciones = new javax.swing.JLabel();
        PanelUbicaciones = new javax.swing.JPanel();
        TfDatoBuscarUbi = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TblRegUbicaciones = new javax.swing.JTable();
        LblUbiIcon = new javax.swing.JLabel();
        LblUbi = new javax.swing.JLabel();
        LblCodUbi = new javax.swing.JLabel();
        TfCodUbi = new javax.swing.JTextField();
        LblNombreUbi = new javax.swing.JLabel();
        TfNomUbi = new javax.swing.JTextField();
        ToolbarCRUDEditorial3 = new javax.swing.JToolBar();
        BtnLimpiarUbi = new javax.swing.JButton();
        BtnAgregarUbi = new javax.swing.JButton();
        BtnEditarUbi = new javax.swing.JButton();
        BtnEliminarUbi = new javax.swing.JButton();
        LblBuscarUbi = new javax.swing.JLabel();
        PanelListadoLibros = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TblRegLibros = new javax.swing.JTable();
        LblBuscarLibro = new javax.swing.JLabel();
        TfDatoBuscarLibro = new javax.swing.JTextField();
        LblISBN = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        LblMFN = new javax.swing.JLabel();
        TfISBN = new javax.swing.JTextField();
        TfMFN = new javax.swing.JTextField();
        TfTitulo = new javax.swing.JTextField();
        PanelEditorialEscoger = new javax.swing.JPanel();
        LblEditorialEscoger = new javax.swing.JLabel();
        TfEditorialCodEscoger = new javax.swing.JTextField();
        TfEditorialNomEscoger = new javax.swing.JTextField();
        BtnEditorialEscoger = new javax.swing.JButton();
        BtnChangeEdi = new javax.swing.JButton();
        PanelClasificacionEscoger = new javax.swing.JPanel();
        LblClasificacionEscoger = new javax.swing.JLabel();
        TfClasificacionCodEscoger = new javax.swing.JTextField();
        TfClasificacionNomEscoger = new javax.swing.JTextField();
        BtnClasificacionEscoger = new javax.swing.JButton();
        BtnChangeClasi = new javax.swing.JButton();
        PanelAutoresEscoger = new javax.swing.JPanel();
        LblAutoresEscoger = new javax.swing.JLabel();
        TfAutoresCodEscoger = new javax.swing.JTextField();
        TfAutoresNomEscoger = new javax.swing.JTextField();
        TbCRUDNavAutoresEscoger = new javax.swing.JToolBar();
        BtnAutoresEscogerPrev = new javax.swing.JButton();
        BtnAutoresEscogerAdd = new javax.swing.JButton();
        BtnAutoresEscogerNext = new javax.swing.JButton();
        LblAutoresEscogerAmount = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        BtnAutoresEscogerEditar = new javax.swing.JButton();
        BtnAutoresEscogerSet = new javax.swing.JButton();
        BtnAutoresEscogerDelete = new javax.swing.JButton();
        ToolbarCRUDEditorial4 = new javax.swing.JToolBar();
        BtnLimpiarLibro = new javax.swing.JButton();
        BtnAgregarLibro = new javax.swing.JButton();
        BtnEditarLibro = new javax.swing.JButton();
        BtnEliminarLibro = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Catálogo");

        LblLibros.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblLibros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblLibros.setText("Listado de Catálogo");
        LblLibros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblBuscarAutor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblBuscarAutor.setText("Buscar: ");

        TfDatoBuscarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfDatoBuscarAutorActionPerformed(evt);
            }
        });
        TfDatoBuscarAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarAutorKeyTyped(evt);
            }
        });

        TblRegAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegAutores.getTableHeader().setReorderingAllowed(false);
        TblRegAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegAutoresMouseClicked(evt);
            }
        });
        TblRegAutores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegAutoresKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TblRegAutores);

        LblCodAutores.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblCodAutores.setText("Código: ");

        LblNombreAutores.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblNombreAutores.setText("Nombre: ");

        ToolbarCRUDEditorial1.setRollover(true);

        BtnLimpiarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiarAutor.setToolTipText("Limpiar");
        BtnLimpiarAutor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiarAutor.setFocusable(false);
        BtnLimpiarAutor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiarAutor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarAutorActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial1.add(BtnLimpiarAutor);

        BtnAgregarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregarAutor.setToolTipText("Guardar");
        BtnAgregarAutor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarAutor.setFocusable(false);
        BtnAgregarAutor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarAutor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarAutorActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial1.add(BtnAgregarAutor);

        BtnEditarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditarAutor.setToolTipText("Editar");
        BtnEditarAutor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditarAutor.setEnabled(false);
        BtnEditarAutor.setFocusable(false);
        BtnEditarAutor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditarAutor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarAutorActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial1.add(BtnEditarAutor);

        BtnEliminarAutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminarAutor.setToolTipText("Eliminar");
        BtnEliminarAutor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminarAutor.setEnabled(false);
        BtnEliminarAutor.setFocusable(false);
        BtnEliminarAutor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminarAutor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarAutorActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial1.add(BtnEliminarAutor);

        LblAutorIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/autoricon (1).png"))); // NOI18N

        LblAutores.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblAutores.setText("Autores");

        javax.swing.GroupLayout PanelListadoAutoresLayout = new javax.swing.GroupLayout(PanelListadoAutores);
        PanelListadoAutores.setLayout(PanelListadoAutoresLayout);
        PanelListadoAutoresLayout.setHorizontalGroup(
            PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                        .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblCodAutores)
                            .addComponent(LblNombreAutores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TfCodAutor)
                            .addComponent(TfNomAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ToolbarCRUDEditorial1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(LblAutores)
                        .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                            .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                                    .addComponent(LblBuscarAutor)
                                    .addGap(2, 2, 2))
                                .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                                    .addComponent(LblAutorIcon)
                                    .addGap(40, 40, 40)))
                            .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                .addComponent(TfDatoBuscarAutor)))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        PanelListadoAutoresLayout.setVerticalGroup(
            PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblBuscarAutor)
                            .addComponent(TfDatoBuscarAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(LblAutorIcon)
                        .addGap(8, 8, 8)
                        .addComponent(LblAutores)))
                .addGap(18, 18, 18)
                .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelListadoAutoresLayout.createSequentialGroup()
                        .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblCodAutores)
                            .addComponent(TfCodAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(PanelListadoAutoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblNombreAutores)
                            .addComponent(TfNomAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ToolbarCRUDEditorial1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        TpLibros.addTab("Autores", PanelListadoAutores);

        LblBuscarEdit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblBuscarEdit.setText("Buscar: ");

        TfDatoBuscarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfDatoBuscarEditActionPerformed(evt);
            }
        });
        TfDatoBuscarEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarEditKeyTyped(evt);
            }
        });

        TblRegEditoriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegEditoriales.getTableHeader().setReorderingAllowed(false);
        TblRegEditoriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegEditorialesMouseClicked(evt);
            }
        });
        TblRegEditoriales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegEditorialesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TblRegEditoriales);

        LblCodEditorial.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblCodEditorial.setText("Código: ");

        LblNombreEditorial.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblNombreEditorial.setText("Nombre: ");

        ToolbarCRUDEditorial.setRollover(true);

        BtnLimpiarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiarEdit.setToolTipText("Limpiar");
        BtnLimpiarEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiarEdit.setFocusable(false);
        BtnLimpiarEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiarEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarEditActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial.add(BtnLimpiarEdit);

        BtnAgregarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregarEdit.setToolTipText("Guardar");
        BtnAgregarEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarEdit.setFocusable(false);
        BtnAgregarEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarEditActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial.add(BtnAgregarEdit);

        BtnEditarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditarEdit.setToolTipText("Editar");
        BtnEditarEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditarEdit.setEnabled(false);
        BtnEditarEdit.setFocusable(false);
        BtnEditarEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditarEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarEditActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial.add(BtnEditarEdit);

        BtnEliminarEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminarEdit.setToolTipText("Eliminar");
        BtnEliminarEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminarEdit.setEnabled(false);
        BtnEliminarEdit.setFocusable(false);
        BtnEliminarEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminarEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarEditActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial.add(BtnEliminarEdit);

        LblEditorialIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editorial (1).png"))); // NOI18N

        LblEditoriales.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        LblEditoriales.setText("Editoriales");

        javax.swing.GroupLayout PanelListadoEditorialesLayout = new javax.swing.GroupLayout(PanelListadoEditoriales);
        PanelListadoEditoriales.setLayout(PanelListadoEditorialesLayout);
        PanelListadoEditorialesLayout.setHorizontalGroup(
            PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblCodEditorial)
                            .addComponent(LblNombreEditorial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TfCodEditorial)
                            .addComponent(TfNomEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ToolbarCRUDEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoEditorialesLayout.createSequentialGroup()
                                .addComponent(LblEditorialIcon)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoEditorialesLayout.createSequentialGroup()
                                .addComponent(LblBuscarEdit)
                                .addGap(2, 2, 2))
                            .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(LblEditoriales)
                                .addGap(28, 28, 28)))
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TfDatoBuscarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        PanelListadoEditorialesLayout.setVerticalGroup(
            PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblBuscarEdit)
                            .addComponent(TfDatoBuscarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(LblEditorialIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEditoriales)))
                .addGap(18, 18, 18)
                .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelListadoEditorialesLayout.createSequentialGroup()
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblCodEditorial)
                            .addComponent(TfCodEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(PanelListadoEditorialesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblNombreEditorial)
                            .addComponent(TfNomEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ToolbarCRUDEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        TpLibros.addTab("Editoriales", PanelListadoEditoriales);

        LblBuscarClas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblBuscarClas.setText("Buscar: ");

        TfDatoBuscarClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfDatoBuscarClasiActionPerformed(evt);
            }
        });
        TfDatoBuscarClasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarClasiKeyTyped(evt);
            }
        });

        TblRegClasificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegClasificaciones.getTableHeader().setReorderingAllowed(false);
        TblRegClasificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegClasificacionesMouseClicked(evt);
            }
        });
        TblRegClasificaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegClasificacionesKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TblRegClasificaciones);

        LblCodClasi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblCodClasi.setText("Código: ");

        LblNombreClasi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblNombreClasi.setText("Nombre: ");

        ToolbarCRUDEditorial2.setRollover(true);

        BtnLimpiarClasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiarClasi.setToolTipText("Limpiar");
        BtnLimpiarClasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiarClasi.setFocusable(false);
        BtnLimpiarClasi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiarClasi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiarClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarClasiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial2.add(BtnLimpiarClasi);

        BtnAgregarClasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregarClasi.setToolTipText("Guardar");
        BtnAgregarClasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarClasi.setFocusable(false);
        BtnAgregarClasi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarClasi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarClasiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial2.add(BtnAgregarClasi);

        BtnEditarClasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditarClasi.setToolTipText("Editar");
        BtnEditarClasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditarClasi.setEnabled(false);
        BtnEditarClasi.setFocusable(false);
        BtnEditarClasi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditarClasi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditarClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarClasiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial2.add(BtnEditarClasi);

        BtnEliminarClasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminarClasi.setToolTipText("Eliminar");
        BtnEliminarClasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminarClasi.setEnabled(false);
        BtnEliminarClasi.setFocusable(false);
        BtnEliminarClasi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminarClasi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminarClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarClasiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial2.add(BtnEliminarClasi);

        LblClasiIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clasificacion (1).png"))); // NOI18N

        LblClasificaciones.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblClasificaciones.setText("Clasificaciones");

        javax.swing.GroupLayout PanelListadoClasificacionesLayout = new javax.swing.GroupLayout(PanelListadoClasificaciones);
        PanelListadoClasificaciones.setLayout(PanelListadoClasificacionesLayout);
        PanelListadoClasificacionesLayout.setHorizontalGroup(
            PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addComponent(LblBuscarClas)
                        .addGap(4, 4, 4)
                        .addComponent(TfDatoBuscarClasi, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoClasificacionesLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(LblClasiIcon)
                                .addGap(45, 45, 45))
                            .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(LblClasificaciones)))
                        .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                                        .addComponent(LblCodClasi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TfCodClasi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                                        .addComponent(LblNombreClasi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TfNomClasi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ToolbarCRUDEditorial2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        PanelListadoClasificacionesLayout.setVerticalGroup(
            PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(LblClasiIcon)
                        .addGap(18, 18, 18)
                        .addComponent(LblClasificaciones))
                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel7))
                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TfDatoBuscarClasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblBuscarClas))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                        .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TfCodClasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblCodClasi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelListadoClasificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelListadoClasificacionesLayout.createSequentialGroup()
                                .addComponent(LblNombreClasi)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoClasificacionesLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(TfNomClasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(ToolbarCRUDEditorial2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        TpLibros.addTab("Clasificaciones", PanelListadoClasificaciones);

        TfDatoBuscarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfDatoBuscarUbiActionPerformed(evt);
            }
        });
        TfDatoBuscarUbi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarUbiKeyTyped(evt);
            }
        });

        TblRegUbicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegUbicaciones.getTableHeader().setReorderingAllowed(false);
        TblRegUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegUbicacionesMouseClicked(evt);
            }
        });
        TblRegUbicaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegUbicacionesKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TblRegUbicaciones);

        LblUbiIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/bookshelf (1).png"))); // NOI18N

        LblUbi.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        LblUbi.setText("Ubicaciones");

        LblCodUbi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblCodUbi.setText("Código: ");

        LblNombreUbi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblNombreUbi.setText("Nombre: ");

        ToolbarCRUDEditorial3.setRollover(true);

        BtnLimpiarUbi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiarUbi.setToolTipText("Limpiar");
        BtnLimpiarUbi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiarUbi.setFocusable(false);
        BtnLimpiarUbi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiarUbi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarUbiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial3.add(BtnLimpiarUbi);

        BtnAgregarUbi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregarUbi.setToolTipText("Guardar");
        BtnAgregarUbi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarUbi.setFocusable(false);
        BtnAgregarUbi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarUbi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarUbiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial3.add(BtnAgregarUbi);

        BtnEditarUbi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditarUbi.setToolTipText("Editar");
        BtnEditarUbi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditarUbi.setEnabled(false);
        BtnEditarUbi.setFocusable(false);
        BtnEditarUbi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditarUbi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarUbiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial3.add(BtnEditarUbi);

        BtnEliminarUbi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminarUbi.setToolTipText("Eliminar");
        BtnEliminarUbi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminarUbi.setEnabled(false);
        BtnEliminarUbi.setFocusable(false);
        BtnEliminarUbi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminarUbi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminarUbi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarUbiActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial3.add(BtnEliminarUbi);

        LblBuscarUbi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblBuscarUbi.setText("Buscar: ");

        javax.swing.GroupLayout PanelUbicacionesLayout = new javax.swing.GroupLayout(PanelUbicaciones);
        PanelUbicaciones.setLayout(PanelUbicacionesLayout);
        PanelUbicacionesLayout.setHorizontalGroup(
            PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblCodUbi)
                            .addComponent(LblNombreUbi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TfCodUbi)
                            .addComponent(TfNomUbi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ToolbarCRUDEditorial3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUbicacionesLayout.createSequentialGroup()
                                .addComponent(LblUbiIcon)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUbicacionesLayout.createSequentialGroup()
                                .addComponent(LblBuscarUbi)
                                .addGap(2, 2, 2))
                            .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(LblUbi)
                                .addGap(29, 29, 29)))
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TfDatoBuscarUbi, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        PanelUbicacionesLayout.setVerticalGroup(
            PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblBuscarUbi)
                            .addComponent(TfDatoBuscarUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(LblUbiIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblUbi)))
                .addGap(18, 18, 18)
                .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelUbicacionesLayout.createSequentialGroup()
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblCodUbi)
                            .addComponent(TfCodUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(PanelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblNombreUbi)
                            .addComponent(TfNomUbi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ToolbarCRUDEditorial3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        TpLibros.addTab("Ubicaciones", PanelUbicaciones);

        TblRegLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblRegLibros.getTableHeader().setReorderingAllowed(false);
        TblRegLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblRegLibrosMouseClicked(evt);
            }
        });
        TblRegLibros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblRegLibrosKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(TblRegLibros);

        LblBuscarLibro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblBuscarLibro.setText("Buscar: ");

        TfDatoBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfDatoBuscarLibroActionPerformed(evt);
            }
        });
        TfDatoBuscarLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TfDatoBuscarLibroKeyTyped(evt);
            }
        });

        LblISBN.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblISBN.setText("ISBN:");

        LblTitulo.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblTitulo.setText("Titulo:");

        LblMFN.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        LblMFN.setText("MFN:");

        TfISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TfISBNActionPerformed(evt);
            }
        });

        PanelEditorialEscoger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblEditorialEscoger.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblEditorialEscoger.setText("Editorial");

        TfEditorialCodEscoger.setEnabled(false);

        TfEditorialNomEscoger.setEnabled(false);

        BtnEditorialEscoger.setText("Escoger");
        BtnEditorialEscoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditorialEscogerActionPerformed(evt);
            }
        });

        BtnChangeEdi.setText("Cambiar");
        BtnChangeEdi.setEnabled(false);
        BtnChangeEdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnChangeEdiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEditorialEscogerLayout = new javax.swing.GroupLayout(PanelEditorialEscoger);
        PanelEditorialEscoger.setLayout(PanelEditorialEscogerLayout);
        PanelEditorialEscogerLayout.setHorizontalGroup(
            PanelEditorialEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                .addGroup(PanelEditorialEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TfEditorialCodEscoger))
                    .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(LblEditorialEscoger)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelEditorialEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TfEditorialNomEscoger)
                            .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(PanelEditorialEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BtnEditorialEscoger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnChangeEdi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        PanelEditorialEscogerLayout.setVerticalGroup(
            PanelEditorialEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditorialEscogerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblEditorialEscoger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfEditorialCodEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfEditorialNomEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnEditorialEscoger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnChangeEdi)
                .addContainerGap())
        );

        PanelClasificacionEscoger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblClasificacionEscoger.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblClasificacionEscoger.setText("Clasificacion");
        LblClasificacionEscoger.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TfClasificacionCodEscoger.setEnabled(false);

        TfClasificacionNomEscoger.setEnabled(false);

        BtnClasificacionEscoger.setText("Escoger");
        BtnClasificacionEscoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClasificacionEscogerActionPerformed(evt);
            }
        });

        BtnChangeClasi.setText("Cambiar");
        BtnChangeClasi.setEnabled(false);
        BtnChangeClasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnChangeClasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelClasificacionEscogerLayout = new javax.swing.GroupLayout(PanelClasificacionEscoger);
        PanelClasificacionEscoger.setLayout(PanelClasificacionEscogerLayout);
        PanelClasificacionEscogerLayout.setHorizontalGroup(
            PanelClasificacionEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClasificacionEscogerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelClasificacionEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TfClasificacionNomEscoger, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TfClasificacionCodEscoger, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelClasificacionEscogerLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(PanelClasificacionEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnChangeClasi)
                            .addComponent(BtnClasificacionEscoger))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClasificacionEscogerLayout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(LblClasificacionEscoger)))
                .addContainerGap())
        );
        PanelClasificacionEscogerLayout.setVerticalGroup(
            PanelClasificacionEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClasificacionEscogerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblClasificacionEscoger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfClasificacionCodEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfClasificacionNomEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnClasificacionEscoger)
                .addGap(12, 12, 12)
                .addComponent(BtnChangeClasi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelAutoresEscoger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        LblAutoresEscoger.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LblAutoresEscoger.setText("Autores");

        TfAutoresCodEscoger.setEnabled(false);

        TfAutoresNomEscoger.setEnabled(false);

        TbCRUDNavAutoresEscoger.setRollover(true);

        BtnAutoresEscogerPrev.setText(" < <");
        BtnAutoresEscogerPrev.setFocusable(false);
        BtnAutoresEscogerPrev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerPrev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerPrevActionPerformed(evt);
            }
        });
        TbCRUDNavAutoresEscoger.add(BtnAutoresEscogerPrev);

        BtnAutoresEscogerAdd.setText("Add");
        BtnAutoresEscogerAdd.setFocusable(false);
        BtnAutoresEscogerAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerAddActionPerformed(evt);
            }
        });
        TbCRUDNavAutoresEscoger.add(BtnAutoresEscogerAdd);

        BtnAutoresEscogerNext.setText(" > >");
        BtnAutoresEscogerNext.setFocusable(false);
        BtnAutoresEscogerNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerNextActionPerformed(evt);
            }
        });
        TbCRUDNavAutoresEscoger.add(BtnAutoresEscogerNext);

        LblAutoresEscogerAmount.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        LblAutoresEscogerAmount.setText("0 de 0");

        jToolBar1.setRollover(true);

        BtnAutoresEscogerEditar.setText("Edit");
        BtnAutoresEscogerEditar.setEnabled(false);
        BtnAutoresEscogerEditar.setFocusable(false);
        BtnAutoresEscogerEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnAutoresEscogerEditar);

        BtnAutoresEscogerSet.setText("Set");
        BtnAutoresEscogerSet.setEnabled(false);
        BtnAutoresEscogerSet.setFocusable(false);
        BtnAutoresEscogerSet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerSet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerSetActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnAutoresEscogerSet);

        BtnAutoresEscogerDelete.setText("Delete");
        BtnAutoresEscogerDelete.setEnabled(false);
        BtnAutoresEscogerDelete.setFocusable(false);
        BtnAutoresEscogerDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAutoresEscogerDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAutoresEscogerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAutoresEscogerDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnAutoresEscogerDelete);

        javax.swing.GroupLayout PanelAutoresEscogerLayout = new javax.swing.GroupLayout(PanelAutoresEscoger);
        PanelAutoresEscoger.setLayout(PanelAutoresEscogerLayout);
        PanelAutoresEscogerLayout.setHorizontalGroup(
            PanelAutoresEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                .addGroup(PanelAutoresEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                        .addGroup(PanelAutoresEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(LblAutoresEscoger))
                            .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(LblAutoresEscogerAmount)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelAutoresEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(TfAutoresNomEscoger)
                            .addComponent(TfAutoresCodEscoger)
                            .addComponent(TbCRUDNavAutoresEscoger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelAutoresEscogerLayout.setVerticalGroup(
            PanelAutoresEscogerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAutoresEscogerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblAutoresEscoger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfAutoresCodEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TfAutoresNomEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblAutoresEscogerAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TbCRUDNavAutoresEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        ToolbarCRUDEditorial4.setRollover(true);

        BtnLimpiarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo-producto.png"))); // NOI18N
        BtnLimpiarLibro.setToolTipText("Limpiar");
        BtnLimpiarLibro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnLimpiarLibro.setFocusable(false);
        BtnLimpiarLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiarLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarLibroActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial4.add(BtnLimpiarLibro);

        BtnAgregarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/disquete.png"))); // NOI18N
        BtnAgregarLibro.setToolTipText("Guardar");
        BtnAgregarLibro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarLibro.setFocusable(false);
        BtnAgregarLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarLibroActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial4.add(BtnAgregarLibro);

        BtnEditarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        BtnEditarLibro.setToolTipText("Editar");
        BtnEditarLibro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditarLibro.setEnabled(false);
        BtnEditarLibro.setFocusable(false);
        BtnEditarLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditarLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarLibroActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial4.add(BtnEditarLibro);

        BtnEliminarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        BtnEliminarLibro.setToolTipText("Eliminar");
        BtnEliminarLibro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEliminarLibro.setEnabled(false);
        BtnEliminarLibro.setFocusable(false);
        BtnEliminarLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminarLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarLibroActionPerformed(evt);
            }
        });
        ToolbarCRUDEditorial4.add(BtnEliminarLibro);

        javax.swing.GroupLayout PanelListadoLibrosLayout = new javax.swing.GroupLayout(PanelListadoLibros);
        PanelListadoLibros.setLayout(PanelListadoLibrosLayout);
        PanelListadoLibrosLayout.setHorizontalGroup(
            PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                                .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(LblISBN)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(LblTitulo)
                                            .addComponent(LblMFN))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TfTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(TfMFN))))
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelListadoLibrosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ToolbarCRUDEditorial4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelEditorialEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelClasificacionEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelAutoresEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                            .addComponent(LblBuscarLibro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TfDatoBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelListadoLibrosLayout.setVerticalGroup(
            PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TfDatoBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblBuscarLibro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelListadoLibrosLayout.createSequentialGroup()
                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblISBN)
                            .addComponent(TfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblTitulo)
                            .addComponent(TfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TfMFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblMFN))
                        .addGap(18, 18, 18)
                        .addComponent(ToolbarCRUDEditorial4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelListadoLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PanelEditorialEscoger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelAutoresEscoger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelClasificacionEscoger, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        TpLibros.addTab("Libros", PanelListadoLibros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(LblLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TpLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TpLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEliminarClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarClasiActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Clasificacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dClasificacion.eliminarClasificacion(cod)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Clasificación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Clasificación", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesClasificacionesUD();
            }
        }
        actualizarBotonesClasificacionesUD();
        actualizarTablaClasificaciones();
    }//GEN-LAST:event_BtnEliminarClasiActionPerformed

    private void BtnEditarClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarClasiActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosClasificacion()) {
            Clasificacion c = new Clasificacion(
                this.TfCodClasi.getText(),
                this.TfNomClasi.getText()
            );

            if (dClasificacion.editarClasificacion(c)) {
                JOptionPane.showMessageDialog(this, "Registro Editado.",
                    "Clasificación", JOptionPane.INFORMATION_MESSAGE);
                actualizarBotonesClasificacionesUD();
                actualizarTablaClasificaciones();

            } else {
                JOptionPane.showMessageDialog(this, "Error al editar",
                    "Clasificación", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesClasificacionesUD();
                actualizarTablaClasificaciones();
            }
        }
    }//GEN-LAST:event_BtnEditarClasiActionPerformed

    private void BtnAgregarClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarClasiActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosClasificacion()) {
            try {
                Clasificacion c = new Clasificacion(
                    this.TfCodClasi.getText(),
                    this.TfNomClasi.getText()
                );

                if (dClasificacion.guardarClasificacion(c)) {
                    JOptionPane.showMessageDialog(this, "Registro Guardado.",
                        "Clasificación", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaClasificaciones();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar",
                        "Clasificación", JOptionPane.WARNING_MESSAGE);
                }

            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
        } else {
            this.actualizarBotonesClasificacionesUD();
        }
    }//GEN-LAST:event_BtnAgregarClasiActionPerformed

    private void BtnLimpiarClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarClasiActionPerformed
        // TODO add your handling code here:
        limpiarClasificacion();
        this.actualizarBotonesClasificacionesUD();
    }//GEN-LAST:event_BtnLimpiarClasiActionPerformed

    private void TblRegClasificacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegClasificacionesMouseClicked
        // TODO add your handling code here:
        this.TblRegClasificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ubicarDatosClasificacion();
                }
            }
        });
    }//GEN-LAST:event_TblRegClasificacionesMouseClicked

    private void TfDatoBuscarClasiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarClasiKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarClasi.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaClasificaciones();

            }
        });
        //this.TpLibros.setSelectedIndex(1);

        filtroTablaClasificaciones = new TableRowSorter(this.TblRegClasificaciones.getModel());
        this.TblRegClasificaciones.setRowSorter(filtroTablaClasificaciones);
    }//GEN-LAST:event_TfDatoBuscarClasiKeyTyped

    private void TfDatoBuscarClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfDatoBuscarClasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfDatoBuscarClasiActionPerformed

    private void BtnEliminarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarEditActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Editorial", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dEditorial.eliminarEditorial(cod)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Editorial", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Editorial", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesEditorialesUD();
            }
        }
        actualizarBotonesEditorialesUD();
        actualizarTablaEditoriales();
    }//GEN-LAST:event_BtnEliminarEditActionPerformed

    private void BtnEditarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarEditActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosEditorial()) {
            Editorial e = new Editorial(
                this.TfCodEditorial.getText(),
                this.TfNomEditorial.getText()
            );

            if (dEditorial.editarEditorial(e)) {
                JOptionPane.showMessageDialog(this, "Registro Editado.",
                    "Editorial", JOptionPane.INFORMATION_MESSAGE);
                actualizarBotonesEditorialesUD();
                actualizarTablaEditoriales();

            } else {
                JOptionPane.showMessageDialog(this, "Error al editar",
                    "Editorial", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesEditorialesUD();
                actualizarTablaEditoriales();
            }
        }
    }//GEN-LAST:event_BtnEditarEditActionPerformed

    private void BtnAgregarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarEditActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosEditorial()) {
            try {
                Editorial e = new Editorial(
                    this.TfCodEditorial.getText(),
                    this.TfNomEditorial.getText()
                );

                if (dEditorial.guardarEditorial(e)) {
                    JOptionPane.showMessageDialog(this, "Registro Guardado.",
                        "Editorial", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaEditoriales();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar",
                        "Editorial", JOptionPane.WARNING_MESSAGE);
                    actualizarBotonesEditorialesUD();
                }

            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
        } else {
            this.actualizarBotonesEditorialesUD();
        }
    }//GEN-LAST:event_BtnAgregarEditActionPerformed

    private void BtnLimpiarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarEditActionPerformed
        // TODO add your handling code here:
        limpiarEditorial();
        this.actualizarBotonesEditorialesUD();
    }//GEN-LAST:event_BtnLimpiarEditActionPerformed

    private void TblRegEditorialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegEditorialesMouseClicked
        // TODO add your handling code here:
        this.TblRegEditoriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ubicarDatosEditorial();
                }
            }
        });
        
        if (evt.getClickCount() == 2) {
            ubicarDatosEditorial();
        }

        
    }//GEN-LAST:event_TblRegEditorialesMouseClicked

    private void TfDatoBuscarEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarEditKeyTyped
        // TODO add your handling code here:
        //filtrarTablaEditoriales();
        this.TfDatoBuscarEdit.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaEditoriales();

            }
        });
        //this.TpLibros.setSelectedIndex(1);

        filtroTablaEditorial = new TableRowSorter(this.TblRegEditoriales.getModel());
        this.TblRegEditoriales.setRowSorter(filtroTablaEditorial);
    }//GEN-LAST:event_TfDatoBuscarEditKeyTyped

    private void TfDatoBuscarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfDatoBuscarEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfDatoBuscarEditActionPerformed

    private void BtnEliminarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarAutorActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Autor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dAutor.eliminarAutor(cod)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Autor", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Autor", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesAutoresUD();
            }
        }
        actualizarBotonesAutoresUD();
        actualizarTablaAutores();
    }//GEN-LAST:event_BtnEliminarAutorActionPerformed

    private void BtnEditarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarAutorActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosAutor()) {

            Autor a = new Autor(
                this.TfCodAutor.getText(),
                this.TfNomAutor.getText()
            );

            if (dAutor.editarAutor(a)) {
                JOptionPane.showMessageDialog(this, "Registro Editado.",
                    "Autor", JOptionPane.INFORMATION_MESSAGE);
                actualizarBotonesAutoresUD();
                actualizarTablaAutores();

            } else {
                JOptionPane.showMessageDialog(this, "Error al editar",
                    "Autor", JOptionPane.WARNING_MESSAGE);
                this.actualizarBotonesAutoresUD();
                actualizarTablaAutores();
            }
        } 
    }//GEN-LAST:event_BtnEditarAutorActionPerformed

    private void BtnAgregarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarAutorActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosAutor()) {
            try {
                Autor a = new Autor(
                    this.TfCodAutor.getText(),
                    this.TfNomAutor.getText()
                );

                if (dAutor.guardarAutor(a)) {
                    JOptionPane.showMessageDialog(this, "Registro Guardado.",
                        "Autor", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaAutores();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar",
                        "Autor", JOptionPane.WARNING_MESSAGE);
                    actualizarTablaAutores();

                }

            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
        } else {
            this.actualizarBotonesAutoresUD();
        }
                    
    }//GEN-LAST:event_BtnAgregarAutorActionPerformed

    private void BtnLimpiarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarAutorActionPerformed
        // TODO add your handling code here:
        this.actualizarBotonesAutoresUD();
        this.limpiarAutor();
    }//GEN-LAST:event_BtnLimpiarAutorActionPerformed

    private void TblRegAutoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegAutoresMouseClicked
        // TODO add your handling code here:
        this.TblRegAutores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ubicarDatosAutores();
                }
            }
        });
    }//GEN-LAST:event_TblRegAutoresMouseClicked

    private void TfDatoBuscarAutorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarAutorKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarAutor.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaAutores();

            }
        });
        //this.TpLibros.setSelectedIndex(1);

        filtroTablaAutores = new TableRowSorter(this.TblRegAutores.getModel());
        this.TblRegAutores.setRowSorter(filtroTablaAutores);
    }//GEN-LAST:event_TfDatoBuscarAutorKeyTyped

    private void TfDatoBuscarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfDatoBuscarAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfDatoBuscarAutorActionPerformed

    private void TfDatoBuscarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfDatoBuscarUbiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfDatoBuscarUbiActionPerformed

    private void TfDatoBuscarUbiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarUbiKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarUbi.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaUbicaciones();

            }
        });
        //this.TpLibros.setSelectedIndex(1);

        filtroTablaUbicaciones = new TableRowSorter(this.TblRegUbicaciones.getModel());
        this.TblRegUbicaciones.setRowSorter(filtroTablaUbicaciones);
    }//GEN-LAST:event_TfDatoBuscarUbiKeyTyped

    private void TblRegUbicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegUbicacionesMouseClicked
        // TODO add your handling code here:
        /*this.TblRegUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ubicarDatosUbicacion();
                }
            }
        });*/
        
        if (evt.getClickCount() == 2) {
            ubicarDatosUbicacion();
        }
        
        
        
    }//GEN-LAST:event_TblRegUbicacionesMouseClicked

    private void BtnLimpiarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarUbiActionPerformed
        // TODO add your handling code here:
        limpiarUbicacion();
        this.actualizarBotonesUbicacionesUD();
    }//GEN-LAST:event_BtnLimpiarUbiActionPerformed

    private void BtnAgregarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarUbiActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosUbicacion()) {
            try {
                Ubicacion u = new Ubicacion(
                    this.TfCodUbi.getText(),
                    this.TfNomUbi.getText()
                );

                if (dUbicacion.guardarUbicacion(u)) {
                    JOptionPane.showMessageDialog(this, "Registro Guardado.",
                        "Ubicacion", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaUbicaciones();

                } else {
                    JOptionPane.showMessageDialog(this, "Error al guardar",
                        "Ubicacion", JOptionPane.WARNING_MESSAGE);
                }

            } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
            }
        } else {
            this.actualizarBotonesUbicacionesUD();
        }
    }//GEN-LAST:event_BtnAgregarUbiActionPerformed

    private void BtnEditarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarUbiActionPerformed
        // TODO add your handling code here:
        if(verificarDatosVaciosUbicacion()) {
            Ubicacion u = new Ubicacion(
                this.TfCodUbi.getText(),
                this.TfNomUbi.getText()
            );

            if (dUbicacion.editarUbicacion(u)) {
                JOptionPane.showMessageDialog(this, "Registro Editado.",
                    "Ubicacion", JOptionPane.INFORMATION_MESSAGE);
                actualizarBotonesUbicacionesUD();
                actualizarTablaUbicaciones();

            } else {
                JOptionPane.showMessageDialog(this, "Error al editar",
                    "Ubicacion", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesUbicacionesUD();
            }
        }
    }//GEN-LAST:event_BtnEditarUbiActionPerformed

    private void BtnEliminarUbiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarUbiActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Ubicacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dUbicacion.eliminarUbicacion(cod)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Clasificación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Ubicacion", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesUbicacionesUD();
            }
        }
        actualizarBotonesUbicacionesUD();
        actualizarTablaUbicaciones();
    }//GEN-LAST:event_BtnEliminarUbiActionPerformed

    private void TblRegAutoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegAutoresKeyReleased
        if((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosAutores();
        }
        
        //cuando se toca la tecla UP, DOWN, o ENTER, se ponen los datos de Autores
        
        
    }//GEN-LAST:event_TblRegAutoresKeyReleased

    private void TblRegEditorialesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegEditorialesKeyReleased
        if((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosEditorial();
        }
        
        //cuando se toca la tecla UP, DOWN, o ENTER, se ponen los datos de editorial
        
        
    }//GEN-LAST:event_TblRegEditorialesKeyReleased

    private void TblRegClasificacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegClasificacionesKeyReleased
        if ((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosClasificacion();
        }
        
        //cuando se toca la tecla UP, DOWN, o ENTER, se ponen los datos de Clasificacion
        
        
    }//GEN-LAST:event_TblRegClasificacionesKeyReleased

    private void TblRegUbicacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegUbicacionesKeyReleased
        if ((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosUbicacion();
        }
        
        //cuando se toca la tecla UP, DOWN, o ENTER, se ponen los datos de Ubicaciones
        
    }//GEN-LAST:event_TblRegUbicacionesKeyReleased

    private void TblRegLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblRegLibrosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            ubicarDatosLibro();
        }
    }//GEN-LAST:event_TblRegLibrosMouseClicked

    private void TblRegLibrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblRegLibrosKeyReleased
        // TODO add your handling code here:
        if ((evt.getKeyCode () == KeyEvent.VK_DOWN) || (evt.getKeyCode () == KeyEvent.VK_UP) || (evt.getKeyCode () == KeyEvent.VK_ENTER)) {
            ubicarDatosLibro();
        }
    }//GEN-LAST:event_TblRegLibrosKeyReleased

    private void TfDatoBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfDatoBuscarLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfDatoBuscarLibroActionPerformed

    private void TfDatoBuscarLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TfDatoBuscarLibroKeyTyped
        // TODO add your handling code here:
        this.TfDatoBuscarLibro.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                filtrarTablaLibros();

            }
        });
        
        filtroTablaLibros = new TableRowSorter(this.TblRegLibros.getModel());
        this.TblRegLibros.setRowSorter(filtroTablaLibros);
    }//GEN-LAST:event_TfDatoBuscarLibroKeyTyped

    private void BtnLimpiarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarLibroActionPerformed
        // TODO add your handling code here:
        this.limpiarLibro();
        this.actualizarBotonesLibroUD();
    }//GEN-LAST:event_BtnLimpiarLibroActionPerformed

    private void BtnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarLibroActionPerformed
        // TODO add your handling code here:
        //Tomado de StackOverflow
        
        //parámetros de SpinnerNumberModel: (valor inicial, min, max, incremento)
        SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, 9, 1);
        JSpinner spinner = new JSpinner(sModel);
        
        if(this.verificarDatosVaciosLibro()) {
            
            llenarListaUbicaciones();            
            Object[] codUbis = new Object[listaUbicaciones.size()];
            int cont = 0;
            
            for(Ubicacion ubiTest: listaUbicaciones) {
                codUbis[cont] = ((Object) ubiTest.getCod_ubicacion());
                cont++;
            }
            
            Object obj_selected_codUbi = JOptionPane.showInputDialog(this, "Código: ", 
                    "Escoger ubicación de ejemplares", JOptionPane.QUESTION_MESSAGE, null, codUbis, 
                    codUbis[0]);
            
            String selected_codUbi = String.valueOf(obj_selected_codUbi);
            String correspondingName_selected_codUbi = "-";
            
            for(Ubicacion ubiTest2: listaUbicaciones) {
                if (ubiTest2.getCod_ubicacion().equals(selected_codUbi)) {
                    correspondingName_selected_codUbi = ubiTest2.getNombre_ubi(); 
                }
            }
            
            int option = JOptionPane.showOptionDialog(this, spinner,
                    "Ingrese cantidad de ejemplares", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if (option == JOptionPane.CANCEL_OPTION)
                return;
            
            else if (option == JOptionPane.OK_OPTION) {
                int cant_ejemplares = (int) spinner.getValue();
                
                try {
                    //crea un objeto ubicacion con los datos de la selección
                    Ubicacion ejemplarUbi = new Ubicacion(selected_codUbi, 
                        correspondingName_selected_codUbi);
                    
                    //crea un objeto Editorial para el ejemplar según selección en form
                    Clasificacion ejemplarClasi = new Clasificacion(this.TfClasificacionCodEscoger.getText(),
                        this.TfClasificacionNomEscoger.getText());
                    
                    //crea un objeto Editorial para el ejemplar según selección en form
                    Editorial ejemplarEdit = new Editorial(this.TfEditorialCodEscoger.getText(),
                        this.TfEditorialNomEscoger.getText());
                    
                    //ciclo for para agregar los ejemplares
                    for (int i = 0; i < cant_ejemplares; i++) {
                        
                        //nuevo ejemplar(es) con toditas los datos
                        Ejemplar ejem = new Ejemplar(this.TfMFN.getText() + "00" + (i + 1),
                        true,
                        i + 1,
                        ejemplarUbi,
                        this.TfISBN.getText(),
                        this.TfTitulo.getText(),
                        this.TfMFN.getText(),
                        ejemplarClasi,
                        ejemplarEdit,
                        listaLibroAutores
                        );
                        
                        if (dEjemplar.guardarEjemplar(ejem)) {
                            JOptionPane.showMessageDialog(this, "Registro Guardado.",
                            "Ejemplar de Libro", JOptionPane.INFORMATION_MESSAGE);
                            //actualizarTablaLibros();
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al guardar",
                              "Ejemplar de Libro", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    actualizarTablaLibros();
                    this.actualizarBotonesLibroUD();
                    //System.out.println(obj_selected_codUbi);
                    //System.out.println(cant_ejemplares);
                } catch (HeadlessException ex) {
                System.out.println("Error al intentar guardar: " + ex.getMessage());
                }
            }
        } else {
            this.actualizarBotonesLibroUD();
        }
   
    }//GEN-LAST:event_BtnAgregarLibroActionPerformed

    private void BtnEditarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarLibroActionPerformed
        // TODO add your handling code here:
        if(this.verificarDatosVaciosLibro() && !listaLibroAutores.isEmpty()) {
            Clasificacion editClasificacion = new Clasificacion(this.TfClasificacionCodEscoger.getText(),
                    this.TfClasificacionNomEscoger.getText());

            Editorial editEditorial = new Editorial(this.TfEditorialCodEscoger.getText(),
                    this.TfEditorialNomEscoger.getText());

            Libro editLibro = new Libro(this.TfISBN.getText(),
            this.TfTitulo.getText(),
            this.TfMFN.getText(),
            editClasificacion,
            editEditorial,
            listaLibroAutores); 
            
            if (dLibro.editarLibro(editLibro)) {
                JOptionPane.showMessageDialog(this, "Registro Editado.",
                    "Libro", JOptionPane.INFORMATION_MESSAGE);
                actualizarBotonesLibroUD();
                actualizarTablaLibros();
            } else {
                JOptionPane.showMessageDialog(this, "Error al editar",
                    "Libro", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesLibroUD();
            }
        }
    }//GEN-LAST:event_BtnEditarLibroActionPerformed

    private void BtnEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarLibroActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?",
            "Libro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            if (dLibro.eliminarLibro(isbn)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado satisfactoriamente",
                    "Libro", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.",
                    "Libro", JOptionPane.WARNING_MESSAGE);
                actualizarBotonesLibroUD();
            }
        }
        actualizarBotonesLibroUD();
        actualizarTablaLibros();
    }//GEN-LAST:event_BtnEliminarLibroActionPerformed

    private void TfISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TfISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TfISBNActionPerformed

    private void BtnAutoresEscogerPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerPrevActionPerformed
        // TODO add your handling code here:
        if(pos == 0)
            pos = listaLibroAutores.size() - 1;
        else
            pos--;
        
        this.TfAutoresCodEscoger.setText(listaLibroAutores.get(pos).getCodigo_autor());
        this.TfAutoresNomEscoger.setText(listaLibroAutores.get(pos).getNombre_autor());
        this.LblAutoresEscogerAmount.setText(pos + 1 + " de " + listaLibroAutores.size());
    }//GEN-LAST:event_BtnAutoresEscogerPrevActionPerformed

    private void BtnAutoresEscogerNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerNextActionPerformed
        // TODO add your handling code here:
        if(pos == listaLibroAutores.size() - 1)
            pos = 0;
        else
            pos++;
        
        this.TfAutoresCodEscoger.setText(listaLibroAutores.get(pos).getCodigo_autor());
        this.TfAutoresNomEscoger.setText(listaLibroAutores.get(pos).getNombre_autor());
        this.LblAutoresEscogerAmount.setText(pos + 1 + " de " + listaLibroAutores.size());
    }//GEN-LAST:event_BtnAutoresEscogerNextActionPerformed

    private void BtnEditorialEscogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditorialEscogerActionPerformed
        // TODO add your handling code here:
        //FrmEscogerDatos frmEscoger = new FrmEscogerDatos(0);
        
        FrmEscogerDatos.getObjEdit().setVisible(true);
        
        //frmEscoger.setVisible(true);    
        
        this.BtnChangeEdi.setEnabled(true);
    }//GEN-LAST:event_BtnEditorialEscogerActionPerformed

    private void BtnClasificacionEscogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClasificacionEscogerActionPerformed
        // TODO add your handling code here:
        //FrmEscogerDatos frmEscoger = new FrmEscogerDatos(1);
        
        FrmEscogerDatos.getObjClasi().setVisible(true);
        
        //frmEscoger.setVisible(true);
        
        this.BtnChangeClasi.setEnabled(true);
    }//GEN-LAST:event_BtnClasificacionEscogerActionPerformed

    private void BtnAutoresEscogerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerAddActionPerformed
        // TODO add your handling code here:
        //FrmEscogerDatos frmEscoger = new FrmEscogerDatos(2);
        
        //FrmEscogerDatos.getObjAut().setVisible(true);
        
        //frmEscoger.setVisible(true);
        setEditAdd = 1;
        FrmEscogerDatos.getObjAut().setVisible(true);
        this.BtnAutoresEscogerSet.setEnabled(true);
        this.BtnAutoresEscogerEditar.setEnabled(false);
        this.BtnAutoresEscogerDelete.setEnabled(false);
    }//GEN-LAST:event_BtnAutoresEscogerAddActionPerformed

    private void BtnChangeEdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnChangeEdiActionPerformed
        // TODO add your handling code here:
        if (!pass_codEdi.equals("---") && !pass_nomEdi.equals("---")) {
            this.TfEditorialCodEscoger.setText(pass_codEdi);
            this.TfEditorialNomEscoger.setText(pass_nomEdi);
        } else {
            FrmEscogerDatos.getObjEdit().dispose();
        }
        pass_codEdi = "---";
        pass_nomEdi = "---";
        this.BtnChangeEdi.setEnabled(false);
    }//GEN-LAST:event_BtnChangeEdiActionPerformed

    private void BtnChangeClasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnChangeClasiActionPerformed
        // TODO add your handling code here:
        if (!pass_codClasi.equals("---") && !pass_nomClasi.equals("---")) {
            this.TfClasificacionCodEscoger.setText(pass_codClasi);
            this.TfClasificacionNomEscoger.setText(pass_nomClasi);
        } else {
            FrmEscogerDatos.getObjClasi().dispose();
        }
        pass_codClasi = "---";
        pass_nomClasi = "---";
        this.BtnChangeClasi.setEnabled(false);
    }//GEN-LAST:event_BtnChangeClasiActionPerformed

    private void BtnAutoresEscogerSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerSetActionPerformed
        // TODO add your handling code here:
        if (!pass_codAut.equals("---") && !pass_nomAut.equals("---")) {
            this.TfAutoresCodEscoger.setText(pass_codAut);
            this.TfAutoresNomEscoger.setText(pass_nomAut);
        } else {
            FrmEscogerDatos.getObjAut().dispose();
            this.BtnAutoresEscogerSet.setEnabled(false);
            
            if(listaLibroAutores.isEmpty()) {
                this.BtnAutoresEscogerPrev.setEnabled(false);
                this.BtnAutoresEscogerNext.setEnabled(false);
            }
            
            return;
        }
        pass_codAut = "---";
        pass_nomAut = "---";
        
        this.BtnAutoresEscogerSet.setEnabled(false);
        
        //aqui se edita la lista original
        if(setEditAdd == 1) {
            listaLibroAutores.add(new Autor(this.TfAutoresCodEscoger.getText(),
            this.TfAutoresNomEscoger.getText()));
            pos = listaLibroAutores.size() - 1;
            
            this.LblAutoresEscogerAmount.setText(listaLibroAutores.size()  + 
                " de " + listaLibroAutores.size());
            
        } else {
            //nuevo objeto autor para editar
            Autor aEdit = new Autor(this.TfAutoresCodEscoger.getText(),
            this.TfAutoresNomEscoger.getText());
            
            //cambiar autor 
            listaLibroAutores.set(pos, aEdit);
            
            //actualizar label
            this.LblAutoresEscogerAmount.setText(pos + 1 +
                " de " + listaLibroAutores.size());
        }
        
        //reiniciar variable
        setEditAdd = 0;
        
        this.BtnAutoresEscogerPrev.setEnabled(true);
        this.BtnAutoresEscogerNext.setEnabled(true);
        this.BtnAutoresEscogerEditar.setEnabled(true);
        this.BtnAutoresEscogerDelete.setEnabled(true);
        //System.out.println(listaLibroAutores);
    }//GEN-LAST:event_BtnAutoresEscogerSetActionPerformed

    private void BtnAutoresEscogerEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerEditarActionPerformed
        // TODO add your handling code here:
        FrmEscogerDatos.getObjAut().setVisible(true);
        this.BtnAutoresEscogerSet.setEnabled(true);
        this.BtnAutoresEscogerDelete.setEnabled(false);
    }//GEN-LAST:event_BtnAutoresEscogerEditarActionPerformed

    private void BtnAutoresEscogerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAutoresEscogerDeleteActionPerformed
        // TODO add your handling code here:        
        listaLibroAutores.remove(pos);
        pos = 0;
        
        if(listaLibroAutores.isEmpty()) {
            this.LblAutoresEscogerAmount.setText(0 +
                    " de " + 0);
            this.TfAutoresCodEscoger.setText("");
            this.TfAutoresNomEscoger.setText("");
            this.BtnAutoresEscogerEditar.setEnabled(false);
            this.BtnAutoresEscogerDelete.setEnabled(false);
            this.BtnAutoresEscogerPrev.setEnabled(false);
            this.BtnAutoresEscogerNext.setEnabled(false);
        } else {
            this.LblAutoresEscogerAmount.setText(pos + 1 +
                    " de " + listaLibroAutores.size());
            
            this.TfAutoresCodEscoger.setText(listaLibroAutores.get(pos).getCodigo_autor());
            this.TfAutoresNomEscoger.setText(listaLibroAutores.get(pos).getNombre_autor());
            //System.out.println(listaLibroAutores);
        }
    }//GEN-LAST:event_BtnAutoresEscogerDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarAutor;
    private javax.swing.JButton BtnAgregarClasi;
    private javax.swing.JButton BtnAgregarEdit;
    private javax.swing.JButton BtnAgregarLibro;
    private javax.swing.JButton BtnAgregarUbi;
    private javax.swing.JButton BtnAutoresEscogerAdd;
    private javax.swing.JButton BtnAutoresEscogerDelete;
    private javax.swing.JButton BtnAutoresEscogerEditar;
    private javax.swing.JButton BtnAutoresEscogerNext;
    private javax.swing.JButton BtnAutoresEscogerPrev;
    private javax.swing.JButton BtnAutoresEscogerSet;
    private javax.swing.JButton BtnChangeClasi;
    private javax.swing.JButton BtnChangeEdi;
    private javax.swing.JButton BtnClasificacionEscoger;
    private javax.swing.JButton BtnEditarAutor;
    private javax.swing.JButton BtnEditarClasi;
    private javax.swing.JButton BtnEditarEdit;
    private javax.swing.JButton BtnEditarLibro;
    private javax.swing.JButton BtnEditarUbi;
    private javax.swing.JButton BtnEditorialEscoger;
    private javax.swing.JButton BtnEliminarAutor;
    private javax.swing.JButton BtnEliminarClasi;
    private javax.swing.JButton BtnEliminarEdit;
    private javax.swing.JButton BtnEliminarLibro;
    private javax.swing.JButton BtnEliminarUbi;
    private javax.swing.JButton BtnLimpiarAutor;
    private javax.swing.JButton BtnLimpiarClasi;
    private javax.swing.JButton BtnLimpiarEdit;
    private javax.swing.JButton BtnLimpiarLibro;
    private javax.swing.JButton BtnLimpiarUbi;
    private javax.swing.JLabel LblAutorIcon;
    private javax.swing.JLabel LblAutores;
    private javax.swing.JLabel LblAutoresEscoger;
    private javax.swing.JLabel LblAutoresEscogerAmount;
    private javax.swing.JLabel LblBuscarAutor;
    private javax.swing.JLabel LblBuscarClas;
    private javax.swing.JLabel LblBuscarEdit;
    private javax.swing.JLabel LblBuscarLibro;
    private javax.swing.JLabel LblBuscarUbi;
    private javax.swing.JLabel LblClasiIcon;
    private javax.swing.JLabel LblClasificacionEscoger;
    private javax.swing.JLabel LblClasificaciones;
    private javax.swing.JLabel LblCodAutores;
    private javax.swing.JLabel LblCodClasi;
    private javax.swing.JLabel LblCodEditorial;
    private javax.swing.JLabel LblCodUbi;
    private javax.swing.JLabel LblEditorialEscoger;
    private javax.swing.JLabel LblEditorialIcon;
    private javax.swing.JLabel LblEditoriales;
    private javax.swing.JLabel LblISBN;
    private javax.swing.JLabel LblLibros;
    private javax.swing.JLabel LblMFN;
    private javax.swing.JLabel LblNombreAutores;
    private javax.swing.JLabel LblNombreClasi;
    private javax.swing.JLabel LblNombreEditorial;
    private javax.swing.JLabel LblNombreUbi;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblUbi;
    private javax.swing.JLabel LblUbiIcon;
    private javax.swing.JPanel PanelAutoresEscoger;
    private javax.swing.JPanel PanelClasificacionEscoger;
    private javax.swing.JPanel PanelEditorialEscoger;
    private javax.swing.JPanel PanelListadoAutores;
    private javax.swing.JPanel PanelListadoClasificaciones;
    private javax.swing.JPanel PanelListadoEditoriales;
    private javax.swing.JPanel PanelListadoLibros;
    private javax.swing.JPanel PanelUbicaciones;
    private javax.swing.JToolBar TbCRUDNavAutoresEscoger;
    private javax.swing.JTable TblRegAutores;
    private javax.swing.JTable TblRegClasificaciones;
    private javax.swing.JTable TblRegEditoriales;
    private javax.swing.JTable TblRegLibros;
    private javax.swing.JTable TblRegUbicaciones;
    private javax.swing.JTextField TfAutoresCodEscoger;
    private javax.swing.JTextField TfAutoresNomEscoger;
    private javax.swing.JTextField TfClasificacionCodEscoger;
    private javax.swing.JTextField TfClasificacionNomEscoger;
    private javax.swing.JTextField TfCodAutor;
    private javax.swing.JTextField TfCodClasi;
    private javax.swing.JTextField TfCodEditorial;
    private javax.swing.JTextField TfCodUbi;
    private javax.swing.JTextField TfDatoBuscarAutor;
    private javax.swing.JTextField TfDatoBuscarClasi;
    private javax.swing.JTextField TfDatoBuscarEdit;
    private javax.swing.JTextField TfDatoBuscarLibro;
    private javax.swing.JTextField TfDatoBuscarUbi;
    private javax.swing.JTextField TfEditorialCodEscoger;
    private javax.swing.JTextField TfEditorialNomEscoger;
    private javax.swing.JTextField TfISBN;
    private javax.swing.JTextField TfMFN;
    private javax.swing.JTextField TfNomAutor;
    private javax.swing.JTextField TfNomClasi;
    private javax.swing.JTextField TfNomEditorial;
    private javax.swing.JTextField TfNomUbi;
    private javax.swing.JTextField TfTitulo;
    private javax.swing.JToolBar ToolbarCRUDEditorial;
    private javax.swing.JToolBar ToolbarCRUDEditorial1;
    private javax.swing.JToolBar ToolbarCRUDEditorial2;
    private javax.swing.JToolBar ToolbarCRUDEditorial3;
    private javax.swing.JToolBar ToolbarCRUDEditorial4;
    private javax.swing.JTabbedPane TpLibros;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
