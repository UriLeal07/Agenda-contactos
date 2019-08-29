/**********************************************
 * Programador: Uri Leal C.                   *
 * Aplicacion: Agenda de Contactos            *
 *********************************************/

public class TestAgenda
{
    public static void main(String [] args)
    {
        //Establecemos look and Feel.
        try
        {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            
            for (int idx = 0; idx < installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
            
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrameAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
	FrameAgenda marco = new FrameAgenda();
	marco.setDefaultCloseOperation(FrameAgenda.EXIT_ON_CLOSE);
	marco.setVisible(true);
    }
}