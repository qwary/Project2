package lt.ktu.projektas;


import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterRegistration;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.ktu.project.client.Register;
import lt.ktu.project.client.RestRegister;
import lt.ktu.projektas.utils.User;
import lt.ktu.projektas.utils.User2;
import lt.ktu.project.client.ServerCommunication;;

public class RegisterGUI {
	//Register usr;
	//Client client;
	
	private Stage window;
	private TextField firstname, secondname, email;
	private PasswordField password, repeatPassword;
	public RegisterGUI(){
		
		
	//	client = ClientBuilder.newClient();
	//	usr = new RestRegister(client);
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Register");
		window.setWidth(300);

		window.setResizable(false);
		
		firstname = new TextField();
		secondname = new TextField();
		email = new TextField();
		password = new PasswordField();
		repeatPassword = new PasswordField();
		
		Label firstnameTitle = new Label("First name:");
		Label secondnameTitle = new Label("Second name:");
		Label emailTitle = new Label("E-mail:");
		Label passwordTitle = new Label("Password:");
		Label repeatPasswordTitle = new Label("Re-password:");
		Button submit = new Button("Register");
		
		submit.setOnAction(new onSubmit());
		
		firstname.setMaxWidth(Double.MAX_VALUE);
		secondname.setMaxWidth(Double.MAX_VALUE);
		email.setMaxWidth(Double.MAX_VALUE);
		password.setMaxWidth(Double.MAX_VALUE);
		repeatPassword.setMaxWidth(Double.MAX_VALUE);
		submit.setMaxWidth(Double.MAX_VALUE);
		
		VBox box = new VBox(5);
		box.setPadding(new Insets(5, 5, 0, 5));
		box.getChildren().addAll(firstnameTitle, firstname, secondnameTitle, secondname, emailTitle, email, passwordTitle, password, repeatPasswordTitle, repeatPassword, submit);
		Scene scene = new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	public String getFirstName(){
		return firstname.getText();
	}
	public String getSecondName(){
		return secondname.getText();
	}
	public String getEmail(){
		return email.getText();
	}
	public String getPassword(){
		return password.getText();
	}
	
	private class onSubmit implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {	
			if(firstname.getText().length()==0){
				AlertGUI.show("Fill in  your first name!");
				return;
			}
			if(secondname.getText().length()==0){
				AlertGUI.show("Fill in  your second name!");
				return;
			}
			if(email.getText().length()==0){
				AlertGUI.show("Fill in  your e-mail!");
				return;
			}
			if(password.getText().length()==0){
				AlertGUI.show("Fill in  your password!");
				return;
			}
			if(repeatPassword.getText().length()==0){
				AlertGUI.show("Fill in  your re-password!");
				return;
			}
			if(!repeatPassword.getText().equals(password.getText())){
				AlertGUI.show("Your passwords doesn't match!");
				return;
			}
			if(password.getText().length() < 5){
				AlertGUI.show("Password must contain at least 5 symbols!");
				return;
			}
			System.out.println("testy");
			
			
			User2 user = new User2("Testy8", "Passy3", "name3", "Surname", "Julius");
			
			
			ServerCommunication toServer = new ServerCommunication();
			//String url;
			//toServer.registerUser(user);
			
			
			try {
				toServer.registerUser(user);
			} catch (InternalServerErrorException e) {
//				System.out.println(e.getResponse().getEntity());
//		          ByteArrayInputStream stream =(ByteArrayInputStream) e.getResponse().getEntity();
//		          int n = stream.available();
//		          byte[] bytes = new byte[n];
//		          stream.read(bytes, 0, n);
//		          String s = new String(bytes, StandardCharsets.UTF_8);
//		          System.out.println(s);
		          
		        	  	//window.close();
		          AlertGUI.show("User already used");
		          System.out.println("Username already in use");
				  return;
			}
			
			
			window.close();
		}
	}
}
