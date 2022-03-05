package org.iesfranciscodelosrios.CajerosAutomaticosCliente.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GesConec {
	private String server;
	private Integer port;

	private Object object;
	private Socket socket = null;

	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;

	public GesConec(String server, Integer port) {
		this.server = server;
		this.port = port;
	}

	public void sendObject(Object object) {
		try {
			this.socket = new Socket(server, port);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(object);
			outputStream.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Object getObject() {
		try {
			inputStream = new ObjectInputStream(socket.getInputStream());
			object = inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}
}
