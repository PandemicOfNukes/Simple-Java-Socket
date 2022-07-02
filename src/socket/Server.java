package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket server;
	private static int port = 9876;
	
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		server = new ServerSocket(port);
		while(true) {
			System.out.println("Esperando por um cliente");
			Socket socket = server.accept();
			ObjectInputStream ois =  new ObjectInputStream(socket.getInputStream());
			String mensagem = (String) ois.readObject();
            System.out.println("Message Received: " + mensagem);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Ola "+ mensagem);
            ois.close();
            oos.close();
            socket.close();
            if(mensagem.equalsIgnoreCase("exit")) break;
		}
		 System.out.println("Desligando");
		 server.close();
	}
}
