/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrameAgenda extends JFrame
{
    private JPanel pCaptura, pDatos, pBotones, pInformacion;
    private JTextField txtNombre, txtDireccion, txtEmail, txtNumero;
    private JLabel lbNombre, lbDireccion, lbEmail, lbNumero, lbLetrero;
    private JButton btCrear, btInsertar, btBuscar, btEliminar, btSalir;
    private JTextArea txaDatos;
    private JScrollPane scrollPane;
    private ListaLigada miAgenda;
    private JTable organizador;
    private Object[][] contactos;
    private int filas;
    private Object[] nombresColumnas = {"ID","Nombre","Direccion","Numero","E-Mail"};
    
    public FrameAgenda()
    {
 	super("Agenda de Contactos");
        setLocation(150, 100);
        
        //Creamos paneles
	pCaptura = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pBotones = new JPanel(new GridLayout(8, 1, 8, 8));
        pDatos = new JPanel(new BorderLayout());
        pInformacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //Creamos Labels y Text Fields
 	lbNombre = new JLabel("Nombre: ");
 	txtNombre = new JTextField("", 15);
        lbDireccion = new JLabel("Direccion: ");
 	txtDireccion = new JTextField("", 15);
        lbEmail = new JLabel("Email: ");
 	txtEmail = new JTextField("", 15);
        lbNumero = new JLabel("Numero: ");
 	txtNumero = new JTextField("", 15);
        
        //Personalizamos Labels y text fields
        lbNombre.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtNombre.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        lbDireccion.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtDireccion.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        lbEmail.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtEmail.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        lbNumero.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtNumero.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        
 	pCaptura.add (lbNombre);
 	pCaptura.add(txtNombre);
        pCaptura.add(lbDireccion);
        pCaptura.add(txtDireccion);
        pCaptura.add(lbNumero);
        pCaptura.add(txtNumero);
        pCaptura.add(lbEmail);
        pCaptura.add(txtEmail);
        
        // Inicializar Botones.
      	btCrear= new JButton("Crear Agenda");
        btInsertar = new JButton("Añadir Contacto");
        btBuscar = new JButton("Buscar Contacto");
        btEliminar =new JButton("Eliminar Contacto");
        btSalir = new JButton("Salir");
        
        //Personalizar Botones.
        btCrear.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        btInsertar.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        btBuscar.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        btEliminar.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        btSalir.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        
        //Componentes desactivados al inicio.
        btInsertar.setEnabled(false);
        btBuscar.setEnabled(false);
        btEliminar.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtNumero.setEnabled(false);
        txtEmail.setEnabled(false);
        
     	btCrear.addActionListener(new ActionListener()
        {
            @Override
     	    public void actionPerformed(ActionEvent e)
            {
     	        crear();
      	    }
     	});
        
     	btInsertar.addActionListener(new ActionListener()
        {
            @Override
 	    public void actionPerformed(ActionEvent e)
            {
                if(txtNombre.getText().equals("") || txtDireccion.getText().equals("") || txtEmail.getText().equals("") || txtNumero.getText().equals(""))
                {
                    lbLetrero.setText("Esperando entrada...");
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "Uno o más campos vacíos", JOptionPane.WARNING_MESSAGE);
                }
                
                else
                {
                    try
		    {
                        long test = Long.parseLong(txtNumero.getText());
		        String nombre = txtNombre.getText();
                        String direccion = txtDireccion.getText();
                        String email = txtEmail.getText();
                        String tel = txtNumero.getText();
                    	
		        agregar(nombre, direccion, tel, email);
		        txtNombre.setText("");
                        txtDireccion.setText("");
                        txtNumero.setText("");
                        txtEmail.setText("");
	            }
	 	    catch(NumberFormatException ex)
	 	    {
                        lbLetrero.setText("Ingrese datos válidos");
                        txtNumero.setText("");
		        JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Dato inválido", JOptionPane.ERROR_MESSAGE);
	 	    }
                }
 	    }
 	});
        
        btEliminar.addActionListener(new ActionListener()
        {
            @Override
 	    public void actionPerformed(ActionEvent e)
            {
                String aux;
                
                if(miAgenda.estaVacia())
                {
                    lbLetrero.setText("Agenda Vacía");
                    JOptionPane.showMessageDialog(null, "No hay contactos para eliminar", "Agenda Vacía", JOptionPane.INFORMATION_MESSAGE);
                }
                
                else
                {

                    aux = String.valueOf(JOptionPane.showInputDialog("Introduce Nombre:"));
                        
                    if(aux.equals(""))
                    {
                        lbLetrero.setText("Esperando entrada...");
                        JOptionPane.showMessageDialog(null, "Para eliminar al cliente ingrese su nombre", "Nombre Vacío", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    else
                        eliminar(aux);
                }
 	    }
 	});
        
      	btBuscar.addActionListener(new ActionListener()
        {
            @Override
     	    public void actionPerformed(ActionEvent e)
            {
                if(miAgenda.estaVacia())
                {
                    lbLetrero.setText("Agenda Vacía");
                    JOptionPane.showMessageDialog(null, "No hay contactos para buscar", "Agenda Vacía", JOptionPane.INFORMATION_MESSAGE);
                }
                
                else
                {
		   String nombre = String.valueOf(JOptionPane.showInputDialog("Introduce Nombre:"));
                    
                    if(nombre.equals(""))
                        JOptionPane.showMessageDialog(null, "Ingrese un nombre para buscar", "Campo nombre vacio", JOptionPane.INFORMATION_MESSAGE);
                    
                    else
                    {
                        Nodo encontrado;
                        
                        encontrado = miAgenda.buscar(nombre);
                        
                        if(encontrado == null)
                            JOptionPane.showMessageDialog(null, "Contacto no encontrado.", "Busqueda Finalizada.", JOptionPane.INFORMATION_MESSAGE);
                        
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Se ha encontrado el contacto.", "Busqueda Finalizada.", JOptionPane.INFORMATION_MESSAGE);
                            mostrarContacto(encontrado, new FrameDetallesContacto(FrameAgenda.this));
                        }
                    }
                }
     	    }
     	});
        
     	btSalir.addActionListener(new ActionListener()
        {
            @Override
     	    public void actionPerformed(ActionEvent e)
            {
     	        System.exit(0);
     	    }
     	});
        
        txtNombre.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(e.paramString()+"\n\n");
                System.out.println("ID: "+e.getID());
                System.out.println("Action Command: "+e.getActionCommand());
            }
        });
        
     	//Añadimos botones al panel de botones
 	pBotones.add(btCrear);
 	pBotones.add(btInsertar);
        pBotones.add(btBuscar);
        pBotones.add(btEliminar);
 	pBotones.add(btSalir);
        
	//Creamos y modificamos la tabla donde mostraremos los contactos
        contactos = new Object[filas][nombresColumnas.length];
        organizador = new JTable(contactos, nombresColumnas);
        organizador.setPreferredScrollableViewportSize(new Dimension(500, 70));
        organizador.setFillsViewportHeight(true);
        organizador.setEnabled(false);
        organizador.setVisible(false);
        organizador.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        organizador.setShowGrid(true);
        organizador.setGridColor(Color.gray);
        organizador.setRowHeight(30);
        
        txaDatos = new JTextArea("", 10,20);
        scrollPane = new JScrollPane(organizador);
        pDatos.add(scrollPane, BorderLayout.CENTER);
        
	//Creamos y personalizamos letrero inferior 			
 	lbLetrero = new JLabel("Listo");
        lbLetrero.setFont(new Font("Arial", Font.ITALIC, 14));
	lbLetrero.setForeground(Color.BLACK); 
 	pInformacion.add(lbLetrero);
        
 	//Colocamos paneles en panel de contenido del frame
	add(pCaptura, BorderLayout.NORTH);
	add(pBotones, BorderLayout.EAST);
	add(pInformacion, BorderLayout.SOUTH);
	add(pDatos, BorderLayout.CENTER);
        
	pack();
    }
    
    public void crear()
    {
	miAgenda = new ListaLigada();
        filas = 0;
        contactos = new Object[filas][nombresColumnas.length];
        organizador.setModel(new DefaultTableModel(contactos, nombresColumnas));
	lbLetrero.setText("Lista creada.");
        
        btCrear.setEnabled(false);
     	btInsertar.setEnabled(true);
        btBuscar.setEnabled(true);
        btEliminar.setEnabled(true);
        txtNombre.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtNumero.setEnabled(true);
        txtEmail.setEnabled(true);
        organizador.setVisible(true);
    }

    //Inserta un contacto en la ListaLigada de acuerdo a la opcion deseada.
    public void agregar(String nombre, String direccion, String numero, String email)
    {
        miAgenda.agregarNodo(nombre, direccion, numero, email);
        
        filas++;
        mostrarAgenda();
        
        lbLetrero.setText("Contacto insertado correctamente.");
    }
    
    public void eliminar(String nombre)
    {
        boolean eliminado;
        
        eliminado = miAgenda.eliminarNodo(nombre);
            
        if(eliminado)
        {
            filas--;
            JOptionPane.showMessageDialog(null, "Contacto eliminado correctamente", "Proceso terminado", JOptionPane.INFORMATION_MESSAGE);
            lbLetrero.setText("Contacto eliminado correctamente");
            mostrarAgenda();
        }
            
        else
        {
            JOptionPane.showMessageDialog(null, "No fue posible eliminar contacto.", "Contacto '"+nombre+"' no encontrado", JOptionPane.ERROR_MESSAGE);
            lbLetrero.setText("Esperando entrada...");
        }
    }
    
    public void mostrarAgenda()
    {
        int i;
        Nodo[] contactosActuales;
        
        //Se define un nuevo modelo de la tabla para mostrar los contactos de la agenda.
        contactos = new Object[filas][nombresColumnas.length];
        organizador.setModel(new DefaultTableModel(contactos, nombresColumnas));
        
        // Obtenemos todos los contactos que hay en nuestra agenda.
        contactosActuales = miAgenda.mostrar();
        
        // Mostramos los contactos en la tabla.
        for(i = 0; i < miAgenda.getNumNodos(); i++)
        {
            organizador.setValueAt(contactosActuales[i].getContacto().getId(), i, 0);
            organizador.setValueAt(contactosActuales[i].getContacto().getNombre(), i, 1);
            organizador.setValueAt(contactosActuales[i].getContacto().getDireccion(), i, 2);
            organizador.setValueAt(contactosActuales[i].getContacto().getNumero(), i, 3);
            organizador.setValueAt(contactosActuales[i].getContacto().getEmail(), i, 4);
        }
    }
    
    public void mostrarContacto(Nodo encontrado, FrameDetallesContacto detalleContacto)
    {
        detalleContacto.getTxtNombre().setText(encontrado.getContacto().getNombre());
        detalleContacto.getTxtDireccion().setText(encontrado.getContacto().getDireccion());
        detalleContacto.getTxtNumero().setText(encontrado.getContacto().getNumero());
        detalleContacto.getTxtEmail().setText(encontrado.getContacto().getEmail());
        detalleContacto.setVisible(true);
    }
}