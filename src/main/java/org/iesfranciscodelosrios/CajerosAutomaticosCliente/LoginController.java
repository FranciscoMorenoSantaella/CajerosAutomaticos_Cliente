package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;
import model.Admin;
import model.Client;
import model.GesConec;
import model.Package;

public class LoginController {

	@FXML
	private TextField usernametxt;

	@FXML
	private PasswordField passwordtxt;

	@FXML
	private Button loginbtn;
	
	@FXML
	private Button adminbtn;

	@FXML
	private Text singuptxt;

	@FXML
	private void logInAdmin() {
		GesConec con = new GesConec("127.0.0.1", 9999);

		if (!usernametxt.getText().isEmpty() && !passwordtxt.getText().isEmpty()) {
			Package<Admin> sendPackage = new Package<>();
			sendPackage.setObject(new Admin("", "", usernametxt.getText(), passwordtxt.getText(), new ArrayList<Client>()));
			sendPackage.setOption(9);
			con.sendObject(sendPackage);

			@SuppressWarnings("unchecked")
			Package<Admin> getPackage = (Package<Admin>) con.getObject();
			if (getPackage.getResult()) {
				if (getPackage.getObject() != null) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
						Parent parent;
						parent = loader.load();
						AdminController adminController = loader.getController();
						adminController.loadAdmin(getPackage.getObject());
						Stage stage = new Stage();
						stage.setScene(new Scene(parent));
						stage.setTitle("Admin window");
						stage.setResizable(false);
						Stage currentStage = (Stage) loginbtn.getScene().getWindow();
						currentStage.close();
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					showErrorAlert("Valores introducidos incorrectos");
				}
			} else {
				showErrorAlert("asdasdsadasdasd");
			}
		} else {
			showErrorAlert("Campos vacios");
		}
	}
	
	
	
	
	
	@FXML
	private void logInClient() {
		GesConec con = new GesConec("127.0.0.1", 9999);

		if (!usernametxt.getText().isEmpty() && !passwordtxt.getText().isEmpty()) {
			Package<Client> sendPackage = new Package<>();
			sendPackage.setObject(new Client("", "", usernametxt.getText(), passwordtxt.getText(), new ArrayList<Account>(), new Admin()));
			sendPackage.setOption(5);
			con.sendObject(sendPackage);

			@SuppressWarnings("unchecked")
			Package<Client> getPackage = (Package<Client>) con.getObject();
			if (getPackage.getResult()) {
				if (getPackage.getObject() != null) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
						Parent parent = loader.load();
						MenuController mainMenuController = loader.getController();
						mainMenuController.loadUser(getPackage.getObject());
						Stage stage = new Stage();
						stage.setScene(new Scene(parent));
						stage.setTitle("Main window");
						stage.setResizable(false);
						Stage currentStage = (Stage) loginbtn.getScene().getWindow();
						currentStage.close();
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					showErrorAlert("Valores introducidos incorrectos");
				}
			} else {
				showErrorAlert("sdafsadfasdfdsaf");
			}
		} else {
			showErrorAlert("Campos vacios");
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

}
