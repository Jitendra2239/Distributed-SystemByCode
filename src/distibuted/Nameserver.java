package distibuted;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Nameserver {


	NameTable table ;
	public Nameserver () {
	table = new NameTable () ;
	
	}
	void handleclient (Socket theclient ) {
	
	try {
		  DataInputStream din=new DataInputStream(theclient .getInputStream());
	        DataOutputStream dout=new DataOutputStream(theclient .getOutputStream());
			String result = din.readUTF();
			System.out.println(result);
	StringTokenizer st = new StringTokenizer(result);
	String tag = st. nextToken ();
	if (tag. equals ("search")) {
	int index = table. search(st. nextToken());
	if (index == -1) // not found
		dout.writeUTF(-1 + " "+ "nullhost");
	else
		dout.writeUTF(table. getport (index) +" "+ table.getHostName ( index ) ) ;
	
	} else if (tag.equals("insert")) {
	String name = st. nextToken ();
	String hostName = st. nextToken ();
	int port = Integer . parseInt (st. nextToken());
	int retValue = table. insert (name, hostName, port );
	dout.writeInt(retValue );
	}
	dout. flush () ;
	} catch ( IOException e) {
	System. err . println (e);
	}
	}
	public static void main(String []args) {
	Nameserver ns = new Nameserver () ;
	System.out.println ("Nameserver started :" );
	try {
	ServerSocket listener = new ServerSocket (Symbols.Serverport );
	while (true) {
	Socket aClient = listener .accept ();
	ns. handleclient ( aClient );
	aClient . close ();
	}
	} catch (IOException e) {
	System. err. println ("Server aborted :" + e);
	}
	}
}
