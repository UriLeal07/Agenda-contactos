/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

public class ListaLigada
{
    private Nodo inicio, fin ;
    private int noNodos;
    private int idNum;

    public ListaLigada()
    {
	inicio = fin = null;
        noNodos = 0;
        idNum = 0;
    }
    
    public boolean estaVacia() { return inicio == null && fin == null; }
    
    public void agregarNodo(String nombre, String direccion, String numero, String email)
    {
        Contacto cNuevo = new Contacto(nombre, direccion, numero, email);
	Nodo nuevo = new Nodo(cNuevo);
        
	if(estaVacia())
            inicio = fin = nuevo;
        
	else
	{
            Nodo actual = inicio;
            
            while(actual != null && actual.getContacto().getNombre().compareTo(nuevo.getContacto().getNombre()) < 0)
                actual = actual.getSig();
            
            if(actual == null)
            {
                fin.setSig(nuevo);
                fin = nuevo;
            }
            
            else
            {
                if(actual == inicio)
                    insertarAlInicio(nuevo);
                
                else
                    insertarAntesDe(actual.getContacto().getId(), nuevo);
            }
	}
        
        nuevo.getContacto().setId(++idNum);
        
	noNodos++;
    }
    
    public boolean eliminarNodo(String nombre)
    {
        Nodo actual = inicio;
        boolean encontrado;
        
        while(actual != null && !actual.getContacto().getNombre().equals(nombre))
	    actual = actual.getSig();
        
        if(actual != null)
        {
            if(inicio == fin)
                inicio = fin = null;
            
            else if(actual == inicio)
                inicio = actual.getSig();
            
            else
                eliminarAntesDe(actual.getContacto().getId());
            
            encontrado = true;
            noNodos--;
        }
        
        else
            encontrado = false;
        
        return encontrado;
    }
    
    private void insertarAlInicio(Nodo nuevo)
    {
	nuevo.setSig(inicio);
        inicio = nuevo;
    }
    
    private void insertarAntesDe(int id, Nodo nuevo)
    {
	Nodo aux = inicio;
        
        while(aux.getSig().getContacto().getId() != id)
            aux = aux.getSig();
        
        nuevo.setSig(aux.getSig());
        aux.setSig(nuevo);
    }
    
    private void eliminarAntesDe(int id)
    {
	Nodo aux = inicio;
        
        while(aux.getSig().getContacto().getId() != id)
            aux = aux.getSig();
        
        if(aux.getSig() == fin)
        {
            fin = aux;
            fin.setSig(null);
        }
        
        else
            aux.setSig(aux.getSig().getSig());
    }
    
    public Nodo buscar(String nombre)
    {
        Nodo aux = inicio;
        
        while(aux != null && !aux.getContacto().getNombre().equals(nombre))
            aux = aux.getSig();
        
        return aux;
    }

    public Nodo[] mostrar()
    {
	Nodo[] nodos = new Nodo[noNodos];
	Nodo aux = inicio;
        int i = 0;

	while (aux != null)
	{
            nodos[i] = aux;
            aux = aux.getSig();
            i++;
	}
        
        return nodos;
    }

    public int getNumNodos() {return noNodos;}
}