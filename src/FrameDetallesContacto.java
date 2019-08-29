/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameDetallesContacto extends JFrame
{
    private JPanel pInformacion;
    private JButton bSalir;
    private JTextField txtNombre, txtDireccion, txtEmail, txtNumero;
    private JLabel lbNombre, lbDireccion, lbEmail, lbNumero;
    
    public FrameDetallesContacto(JFrame propietario)
    {
        super("Detalles del Contacto.");
        
        //Creamos Labels y Text Fields
 	lbNombre = new JLabel("Nombre:");
 	txtNombre = new JTextField("", 20);
        lbDireccion = new JLabel("Direccion:");
 	txtDireccion = new JTextField("", 20);
        lbEmail = new JLabel("Email:");
 	txtEmail = new JTextField("", 20);
        lbNumero = new JLabel("Numero:");
 	txtNumero = new JTextField("", 20);
        
        bSalir = new JButton("   Listo   ");
        bSalir.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        
        //Personalizamos Labels y text fields
        lbNombre.setFont(new Font("Arial", Font.ROMAN_BASELINE, 17));
        txtNombre.setFont(new Font("Arial", Font.ROMAN_BASELINE, 17));
        lbDireccion.setFont(new Font("Arial", Font.ROMAN_BASELINE, 17));
        txtDireccion.setFont(new Font("Arial", Font.ROMAN_BASELINE, 17));
        lbEmail.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtEmail.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        lbNumero.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        txtNumero.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        
        txtNombre.setEditable(false);
        txtDireccion.setEditable(false);
        txtNumero.setEditable(false);
        txtEmail.setEditable(false);
        
        pInformacion = new JPanel(new GridLayout(4, 2, 1, 5));
        
        pInformacion.add(lbNombre);
        pInformacion.add(txtNombre);
        pInformacion.add(lbDireccion);
        pInformacion.add(txtDireccion);
        pInformacion.add(lbNumero);
        pInformacion.add(txtNumero);
        pInformacion.add(lbEmail);
        pInformacion.add(txtEmail);

        add(pInformacion, BorderLayout.WEST);
        
        bSalir.addActionListener(new ActionListener()
        {
            @Override
     	    public void actionPerformed(ActionEvent e)
            {
     	        
     	    }
     	});
        
        pack();
	setLocation(300, 180);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtNumero() {
        return txtNumero;
    }
}
