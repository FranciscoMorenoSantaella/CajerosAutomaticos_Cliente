package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Account;
import model.Client;
import model.GesConec;
import model.Package;

public class MenuController {
	Client client = new Client();

	ObservableList<Account> accountList = FXCollections.observableArrayList();

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
		this.accountlist.setItems(accountList);
		actualbalance.setText("0");
	}

	@FXML
	public void selectItem() {
		if (accountlist.getSelectionModel().getSelectedItem() != null) {
			actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance() + "");
		} else {
			showErrorAlert("No se ha podido seleccionar el item");
		}
	}

	/**
	 * Este un método en el que sacamos el dinero que introducimos como parametro
	 * cambiando así el saldo actual a uno menor
	 * 
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	@FXML
	public void substractBalance() {
		GesConec con = new GesConec("127.0.0.1", 9999);

		try {
			Integer balance = Integer.parseInt(this.balance.getText());
			if (balance > 0 && accountlist.getSelectionModel().getSelectedItem() != null && accountlist.getSelectionModel().getSelectedItem().getBalance() > balance) {

				Package<Account> sendPackage = new Package<>();
				sendPackage.setObject(accountlist.getSelectionModel().getSelectedItem());
				sendPackage.setBalance(balance);
				sendPackage.setOption(14);
				con.sendObject(sendPackage);

				accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance() - balance);

				showInfoAlert("Has sacado: " + balance + " de tu cuenta");
				actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance() + "");
			} else {
				showErrorAlert("El dinero que quieres sacar es mayor que el que hay en la cuenta actualmente, ingrese dinero para poder retirarlo");
			}
		} catch (NumberFormatException e) {
			showErrorAlert("'" + this.balance.getText() + "'" + " no es un valor válido");
		}

	}

	/**
	 * Este un método en el que añadimos el dinero que introducimos como parametro
	 * cambiando así el saldo actual a uno mayor
	 * 
	 * @param balance le pasamos la cantidad que queremos restar
	 */
	@FXML
	public void addBalance() {
		GesConec con = new GesConec("127.0.0.1", 9999);

		try {
			Integer balance = Integer.parseInt(this.balance.getText());
			if (balance > 0 && accountlist.getSelectionModel().getSelectedItem() != null) {

				Package<Account> sendPackage = new Package<>();
				sendPackage.setObject(accountlist.getSelectionModel().getSelectedItem());
				sendPackage.setBalance(balance);
				sendPackage.setOption(15);
				con.sendObject(sendPackage);

				accountlist.getSelectionModel().getSelectedItem().setBalance(accountlist.getSelectionModel().getSelectedItem().getBalance() + balance);

				showInfoAlert("Has introducido: " + balance + " a tu cuenta");
				actualbalance.setText(accountlist.getSelectionModel().getSelectedItem().getBalance() + "");
			} else {
				showErrorAlert("El dinero que quieres sacar es mayor que el que hay en la cuenta actualmente, ingrese dinero para poder retirarlo");
			}
		} catch (NumberFormatException e) {
			showErrorAlert("'" + this.balance.getText() + "'" + " no es un valor válido");
		}

	}

	public void loadUser(@SuppressWarnings("exports") Client client) {
		System.out.println(client.getAccounts());
		this.client = client;
		this.accountList.setAll(client.getAccounts());
	}

	/**
	 * 
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta de
	 *              error para que se le muestre al usuario
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
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta
	 *              informativa para que se le muestre al usuario
	 */
	public void showInfoAlert(String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}
