module org.iesfranciscodelosrios.CajerosAutomaticosCliente {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.persistence;

	opens org.iesfranciscodelosrios.CajerosAutomaticosCliente to javafx.fxml;

	exports org.iesfranciscodelosrios.CajerosAutomaticosCliente;
}
