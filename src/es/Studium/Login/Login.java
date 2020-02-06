package es.Studium.Login;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.event.WindowAdapter;

public class Login extends WindowAdapter implements ActionListener
{
	//Creamos la ventana Login
	Frame login1 =new Frame("login"); 
	//Creamos la ventana Principal
	Frame menuPrincipal = new Frame ("Menu Principal"); 
	//Creamos la Ventana de dialogo
	Dialog errorLogin=new Dialog  (login1, "ERROR",true); 
	//Creamos las etiquetas y campos de texto del Login
	Label lblUsuario = new Label("Usuario"); 
	TextField txtUsuario = new TextField(20);//Tamaño visual del campo, ojo no es el tamaño de los datos.
	Label lblClave = new Label("Clave");
	TextField txtClave = new TextField(22);//Tamaño visual del campo, ojo no es el tamaño de los datos.
	//Creamos los botones
	Button Aceptar = new Button("Aceptar");
	Button Limpiar = new Button("Limpiar");
//Acceso a la base de datos
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://162.168.0.114:3306/Studium?useSSL=false";
	String login = "root";
	String password = "Studium2019;";
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Login()
	{

		login1.setLayout (new FlowLayout());
		login1.setTitle ("Login" ); //Pone el titulo a la ventana
		login1.setSize(280,200); //Establece el tamaño
		login1.setResizable(false); //Evita que se pueda maximizar
		login1.setLocationRelativeTo(null); //Establece que la pocicion de la ventana sea en el centro
		//Añadimos las etiquetas y campos de texto y los botonesa la ventana correspondiente
		login1.add(lblUsuario);
		login1.add(txtUsuario);
		login1.add(lblClave);
		login1.add(txtClave);
		login1.add(Aceptar);
		login1.add(Limpiar);
		txtClave.setEchoChar('*');
		login1.addWindowListener(this); //Añade el cierre

		Aceptar.addActionListener(this);
		Limpiar.addActionListener(this);




		//Hace visible la ventana
		login1.setVisible(true);


	}

	public static void main(String[] args)
	{
		new Login();
	}
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource().equals(Aceptar))
		{
			sentencia ="SELECT * FROM usuarios WHERE ";
			sentencia +="nombreUsuario = +txtUsuario"
			if ((txtUsuario.getText().equals("User")&&(txtClave.getText().equals("Studium2019;"))))
			{
				menuPrincipal.setLayout(new FlowLayout());
				menuPrincipal.setSize(300, 200);
				menuPrincipal.setResizable(false);
				menuPrincipal.addWindowListener(this);
				menuPrincipal.setLocationRelativeTo(null);
				menuPrincipal.setVisible(true);
				login1.setVisible(false);
			}
			else
			{
				errorLogin.setLayout(new FlowLayout());
				errorLogin.setSize(160, 90);
				errorLogin.setResizable(false);
				errorLogin.addWindowListener(this);
				errorLogin.add(new Label("Credenciales Incorrectas"));
				Button btnVolver = new Button("Volver");
				btnVolver.addActionListener(this);
				errorLogin.add(btnVolver);
				errorLogin.setLocationRelativeTo(null);
				errorLogin.setVisible(true);
			}
		}
		else if (ae.getSource().equals(Limpiar))
		{
			txtUsuario.selectAll();
			txtUsuario.setText("");//opción2
			txtClave.selectAll();
			txtClave.setText("");//opción2
			txtUsuario.requestFocus();
		}
		else
		{
			errorLogin.setVisible(false); 
		}
	}
	@Override
	public void windowClosing(WindowEvent we) {
		if(errorLogin.isActive()) 
		{
			errorLogin.setVisible(false); 
		}

		else {
			System.exit(0); 
		}

	}
}
