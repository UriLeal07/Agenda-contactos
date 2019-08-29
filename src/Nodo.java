/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

public class Nodo 
{
    private Contacto contacto;
    private Nodo sig;
    
    public Nodo(Contacto contacto) 
    {
        this.contacto = new Contacto();
        this.contacto.setNombre(contacto.getNombre());
        this.contacto.setDireccion(contacto.getDireccion());
        this.contacto.setNumero(contacto.getNumero());
        this.contacto.setEmail(contacto.getEmail());
        sig = null;
    }
    
    public Nodo() {}
    
    public void setSig(Nodo sig) { this.sig = sig; }
    public void setContacto(Contacto contacto) { this.contacto = contacto; }
    
    public Nodo getSig() { return sig; }
    public Contacto getContacto() { return contacto; }
}