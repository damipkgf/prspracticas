/*
 * AUTOR: Damian Valle Sese
 * NIA: 719084
 * FICHERO: MessageSystem.java
 * TIEMPO: 10h
 * DESCRIPCION: Implementacion un de sistema de mensajes punto a punto basado en sockets que permite realizar un envio desacoplado
 * entre procesos.
 */

package practica6;

import practica6.MailBox;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class MessageSystem {
	private int pid;
	private ArrayList<InetSocketAddress> peers= new ArrayList<InetSocketAddress>();
	private MailBox mailbox;
	private Sending sending;
	boolean tcp=true;
	
	public MessageSystem(int source, String usersFile, boolean TCP) throws FileNotFoundException {
		pid = source;
		InetSocketAddress localSocketAddr = loadPeerAddresses(usersFile);
		if(TCP){
			sending = new SendingTCP(pid, peers);
			mailbox = new MailBoxTCP(localSocketAddr); 
		}else{
			tcp=false;
			//sending = new SendingUDP(pid, peers);
			//mailbox = new MailBoxUDP(localSocketAddr); 
		}
		mailbox.start();
	}
	
	public void send(int dst, Serializable message) {
		sending.send(dst, message);
	}
	
	public Envelope receive() {
		return mailbox.getNextMessage();
	}
	
	public void stopMailbox() {
		mailbox.closeSocket();
	}
	
	public InetSocketAddress makeSockAddress(String host, int port){
		InetSocketAddress addr = new InetSocketAddress(host, port);
		return addr;
	}

	
	private InetSocketAddress loadPeerAddresses(String networkFile) throws FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(networkFile));
		String line;
		InetSocketAddress addr=null;
		int n = 0;
		try {
			while ((line = in.readLine()) != null) {
				++n;
				int sep = line.indexOf(':');
				if (sep != -1) {
					peers.add(makeSockAddress(line.substring(0, sep),Integer.parseInt(line.substring(sep + 1))));
					if (n == pid) {
						addr = peers.get(peers.size() - 1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {}
		}
		return addr;
	}
	
	
}
