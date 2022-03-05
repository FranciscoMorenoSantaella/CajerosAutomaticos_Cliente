package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import org.iesfranciscodelosrios.CajerosAutomaticosCliente.model.Account;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuController {
	@FXML
	private ListView<Account> accountlist;
	
	@FXML 
	private TextField balance;
	
	@FXML
	private Text actualbalance;
	
	@FXML 
	private ImageView addbalance;
	
	@FXML 
	private ImageView substractbalance;
	
	@FXML
	private Text balancetxt;
	
	@FXML
	public void initialize() {
		actualbalance.setText("0");
	}
	
	
	
	/**
	 * Este un método en el que sacamos el dinero que introducimos como parametro cambiando así el saldo actual a uno menor
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	public void substractBalance(double balance) {
		if(balance >0 && accountlist.getSelectionModel().getSelectedItem().getBalance() > balance) {
			accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance()-balance);
			showInfoAlert("Has sacado: "+balance+" a tu cuenta");
		}else {
			showErrorAlert("El dinero que quieres sacar es mayor que el que hay en la cuenta actualmente, ingrese dinero para poder retirarlo");
		}
	}
	
	/**
	 * Este un método en el que añadimos el dinero que introducimos como parametro cambiando así el saldo actual a uno mayor
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	public void addBalance(double balance) {
		if(balance >0 && accountlist.getSelectionModel().getSelectedItem().getBalance() > balance) {
			accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance()-balance);
		}else {
			showInfoAlert("Has introducido: "+balance+" a tu cuenta");
		}
	}
	
	/**
	 * 
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta para
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
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta para
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
