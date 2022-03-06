package org.iesfranciscodelosrios.CajerosAutomaticosCliente;



import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Account;
import model.Admin;
import model.Client;
import model.GesConec;
import model.Package;

public class MenuController {
	ObservableList<Account> a = FXCollections.observableArrayList();

	Account b = new Account();
	
	
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
		a.add(b);
		accountlist.setItems(a);
	}
	
	@FXML
	public void selectItem() {
		if(accountlist.getSelectionModel().getSelectedItem()!=null) {
			actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance()+"");
		}else {
			showErrorAlert("No se ha podido seleccionar el item");
		}
	}
	
	
	/**
	 * Este un método en el que sacamos el dinero que introducimos como parametro cambiando así el saldo actual a uno menor
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	@FXML
	public void substractBalance() {
		GesConec con = new GesConec("127.0.0.1", 9999);
		int balance = Integer.parseInt(this.balance.getText());
		if(balance >0 && accountlist.getSelectionModel().getSelectedItem().getBalance() > balance && con!=null) {
			accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance()-balance);
			Package<Account> sendPackage = new Package<>();
			sendPackage.setObject(accountlist.getSelectionModel().getSelectedItem());
			sendPackage.setOption(13);
			con.sendObject(sendPackage);
			showInfoAlert("Has sacado: "+balance+" de tu cuenta");
			actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance()+"");
		}else {
			showErrorAlert("El dinero que quieres sacar es mayor que el que hay en la cuenta actualmente, ingrese dinero para poder retirarlo");
		}
	}
	
	/**
	 * Este un método en el que añadimos el dinero que introducimos como parametro cambiando así el saldo actual a uno mayor
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	@FXML
	public void addBalance() {
		GesConec con = new GesConec("127.0.0.1", 9999);
		int balance = Integer.parseInt(this.balance.getText());
		
		if(balance >0 && accountlist.getSelectionModel().getSelectedItem()!= null) {
			accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance()+balance);
			Package<Account> sendPackage = new Package<>();
			sendPackage.setObject(accountlist.getSelectionModel().getSelectedItem());
			sendPackage.setOption(14);
			con.sendObject(sendPackage);
			actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance()+"");
			showInfoAlert("Has introducido: "+balance+" a tu cuenta");
		}else {
			showErrorAlert("Error al introducir dinero");
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
