package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Account;
import model.Admin;
import model.Client;
import model.GesConec;
import model.Package;

public class AdminController {
	Admin admin = new Admin();
	ObservableList<Client> clientList = FXCollections.observableArrayList();
	ObservableList<Account> accountList = FXCollections.observableArrayList();

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
	private TextField numaccountfield2;

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
	private Button createbtn2;

	@FXML
	private ListView<Account> accountlistview;

	@FXML
	private ListView<Client> clientelistview;

	public void initialize() {
		this.clientelistview.setItems(clientList);
		this.accountlistview.setItems(accountList);
		this.clientDrop.setItems(clientList);
	}

	/*
	 * Este método sirve para seleccionar la lista de cuentas
	 */
	@FXML
	public void selectItem() {
		if (accountlistview.getSelectionModel().getSelectedItem() != null) {
			nametxt.setText(accountlistview.getSelectionModel().getSelectedItem().getNumber());
			balancetxt.setText(accountlistview.getSelectionModel().getSelectedItem().getBalance() + "");
		} else {
			showErrorAlert("No se ha podido seleccionar el item");
		}
	}

	/*
	 * Este método sirve para seleccionar la lista de clientes
	 */
	@FXML
	public void selectItem2() {
		if (clientelistview.getSelectionModel().getSelectedItem() != null) {
			clientnametxt.setText(clientelistview.getSelectionModel().getSelectedItem().getName());
			clientsurnametxt.setText(clientelistview.getSelectionModel().getSelectedItem().getSurname());
			clientusernametxt.setText(clientelistview.getSelectionModel().getSelectedItem().getUsername());
		} else {
			showErrorAlert("No se ha podido seleccionar el item");
		}
	}

	/**
	 * Este método sirve para que un administrador cree un nuevo cliente
	 */
	public void createClient() {
		GesConec con = new GesConec("127.0.0.1", 9999);
		if (namefield.getText() != "" && surnamefield.getText() != "" && usernamefield.getText() != ""
				&& passwordfield.getText() != "") {
			Package<Client> sendPackage = new Package<>();
			Client c = new Client(namefield.getText(), surnamefield.getText(), usernamefield.getText(),
					passwordfield.getText(), new ArrayList<Account>(), this.admin);
			sendPackage.setObject(c);
			sendPackage.setOption(1);
			con.sendObject(sendPackage);

			namefield.setText("");
			usernamefield.setText("");
			surnamefield.setText("");
			passwordfield.setText("");
			showInfoAlert("El cliente se ha creado correctamente");
			clientList.add(c);
			clientelistview.setItems(clientList);

		} else {
			showErrorAlert("Falta algún campo por rellenar");
		}
	}

	/**
	 * Este método se usa para borrar la cuenta que este seleccionada en la listview
	 */
	public void deleteAccount() {
		GesConec con = new GesConec("127.0.0.1", 9999);
		if (accountlistview.getSelectionModel().getSelectedItem() != null) {
			Package<Account> sendPackage = new Package<>();
			sendPackage.setObject(accountlistview.getSelectionModel().getSelectedItem());
			sendPackage.setOption(12);
			con.sendObject(sendPackage);
			showInfoAlert("La cuenta se ha eliminado correctamente");
			accountList.remove(accountlistview.getSelectionModel().getSelectedItem());
			accountlistview.setItems(accountList);
		} else {
			showErrorAlert("No se ha seleccionado ninguna cuenta");
		}
	}

	/**
	 * Este método se usa para borrar la cuenta que este seleccionada en la listview
	 */
	public void createAccount() {
		GesConec con = new GesConec("127.0.0.1", 9999);
		if (clientDrop.getSelectionModel().getSelectedItem() != null) {
			Package<Account> sendPackage = new Package<>();
			Package<Client> sendPackage2 = new Package<>();
			Client c = new Client();
			c.setId(clientDrop.getSelectionModel().getSelectedItem().getId());
			sendPackage2.setObject(c);
			sendPackage2.setOption(4);
			con.sendObject(sendPackage2);
			Package<Client> obtainPackage = (Package<Client>) con.getObject();
			Client c1 = obtainPackage.getObject();
			Account ac = new Account(numaccountfield2.getText(), 0.0, c1);
			sendPackage.setObject(ac);
			sendPackage.setOption(10);
			con.sendObject(sendPackage);
			showInfoAlert("La cuenta se ha creado correctamente");
			accountList.add(ac);
			accountlistview.setItems(accountList);
		} else {
			showErrorAlert("No se ha podido crear la cuenta");
		}
	}

	public void loadAdmin(@SuppressWarnings("exports") Admin admin) {
		this.admin = admin;
		this.clientList.setAll(this.admin.getClients());
		for (Client c : clientList) {
			this.accountList.addAll(c.getAccounts());
		}
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
