package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
		InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<5;i++){
        	 socket = new Socket(host.getHostName(), 9876);
        	 oos = new ObjectOutputStream(socket.getOutputStream());
             System.out.println("Mandando um request");
             if(i==4)oos.writeObject("exit");
             else oos.writeObject(""+i);
             ois = new ObjectInputStream(socket.getInputStream());
             String mensagem = (String) ois.readObject();
             System.out.println("Mensagem: " + mensagem);
             ois.close();
             oos.close();
             Thread.sleep(100);
        }
	}

}
