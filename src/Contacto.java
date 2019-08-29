/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

public class Contacto
{
    private String numero, nombre, direccion, email;
    private int id;
    
    public Contacto(String nombre, String direccion, String numero, String email)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.email = email;
        id = 0;
    }
    
    public Contacto()
    {
        id = 0;
        nombre = direccion = numero = email = "";
    }
    
    public String getNumero() {return numero;}
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getDireccion() {return direccion;}
    public String getEmail() {return email;}

    public void setNumero(String numero) {this.numero = numero;}
    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setEmail(String email) {this.email = email;}
}