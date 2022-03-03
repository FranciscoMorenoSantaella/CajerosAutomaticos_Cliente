package org.iesfranciscodelosrios.CajerosAutomaticosCliente;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

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
import model.Client;

public class LoginController {

	@FXML
	private TextField usernametxt;

	@FXML
	private PasswordField passwordtxt;

	@FXML
	private Button loginbtn;

	@FXML
	private Text singuptxt;

	public void Login() {
		if (usernametxt.getLength() > 0 && passwordtxt.getLength() > 0) {
			try {
				Client c = new Client(usernametxt.getText(), passwordtxt.getText());
				Client c1 = new Client("hola","hola");
				if (c.equals(c1)) {
					try {
						switchToClientMenu();
					} catch (Exception e) {
						
					}
				}else {
					showErrorAlert("Datos no válidos");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void prueba() {
		try {
			switchToClientMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo sirve para cambiar de ventana desde el login al menú
	 * @throws IOException
	 */
	@FXML
	private void switchToClientMenu() throws IOException {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
			Parent parent = loader.load();
			MenuController mainMenuController = loader.getController();
			Stage stage = new Stage();
			stage.setScene(new Scene(parent));
			stage.setTitle("Main window");
			stage.setResizable(false);
			Stage currentStage = (Stage) loginbtn.getScene().getWindow();
			currentStage.close();
			stage.show();
		} catch (IOException ex) {
		
		}

	}
	
	
	/**
	 * 
	 * @param texto Este parámetro es el texto que le vamos a pasar a la alerta para que se le muestre al usuario
	 */
	public void showErrorAlert(String texto) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}
