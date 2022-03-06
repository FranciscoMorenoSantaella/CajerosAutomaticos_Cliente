package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import java.util.List;

import org.iesfranciscodelosrios.CajerosAutomaticosCliente.model.Account;
import org.iesfranciscodelosrios.CajerosAutomaticosCliente.model.Admin;
import org.iesfranciscodelosrios.CajerosAutomaticosCliente.model.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminController {
	List<Account> accountList;
	@FXML
	private TextField namefield;
	
	@FXML
	private TextField surnamefield;
	
	@FXML
	private TextField usernamefield;
	
	@FXML
	private TextField passwordfield;
	
	@FXML
	private TextField numaccountfield;
	
	@FXML
	private ComboBox<Client> clientDrop;
	
	@FXML
	private Text nametxt;
	
	@FXML
	private Text balancetxt;
	
	@FXML
	private Text clientnametxt;
	
	@FXML
	private Text clientsurnametxt;
	
	@FXML
	private Text clientusernametxt;
	
	@FXML
	private Button deletebtn;
	
	@FXML
	private Button createbtn;
	
	@FXML
	private ListView<Account> accountlistview;
	
	@FXML
	private ListView<Client> clientelistview;
	
	/**
	 * Este método sirve para que un administrador cree un nuevo cliente
	 */
	public void createClient() {
		if(namefield.getText()!="" && surnamefield.getText()!="" && usernamefield.getText()!="" && passwordfield.getText()!="" && numaccountfield.getText()!="") {
			//Account get account falta
			Account prueba = new Account();
			//Traer el admin para asignarselo
			Admin admin = new Admin();
			this.accountList.add(prueba);
			Client nclient = new Client(namefield.getText(),surnamefield.getText(),usernamefield.getText(),passwordfield.getText(),accountList,admin);
			//crear cliente falta
			showInfoAlert("El cliente se ha creado correctamente");
			namefield.setText("");
			usernamefield.setText("");
			surnamefield.setText("");
			passwordfield.setText("");
			numaccountfield.setText("");
		}else {
			showErrorAlert("Falta algún campo por rellenar");
		}
	}
	
	/**
	 * Este método se usa para borrar la cuenta que este seleccionada en la listview
	 */
	public void deleteAccount() {
		if(clientelistview.getSelectionModel().getSelectedItem()!=null) {
			//Borrar cuenta
			showInfoAlert("La cuenta se ha eliminado correctamente");
		}else {
			showErrorAlert("No se ha seleccionado ninguna cuenta");
		}
	}
	
	
	/**
	 * 
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta de error para
	 *              que se le muestre al usuario
	 */
	public void showErrorAlert(String texto) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	/**
	 * 
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta informativa para
	 *              que se le muestre al usuario
	 */
	public void showInfoAlert(String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	
}
