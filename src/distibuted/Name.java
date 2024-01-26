package distibuted;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class Name {
	  DataInputStream  din ;
	  DataOutputStream  pout;
public void getsocket () throws IOException {
Socket server = new Socket (Symbols. nameserver,Symbols. Serverport ) ;
din =new DataInputStream(server .getInputStream());
pout =new DataOutputStream(server .getOutputStream());
}
public int insertName( String name, String hname, int portnum)throws IOException {
getsocket ();
pout.writeUTF("insert"+" " + name + " " + hname + " " + portnum ) ;
pout. flush ();
return din.readInt();
}
public PortAddr searchName ( String name) throws IOException {
getsocket ();
pout.writeUTF("search"+" " + name);
pout . flush ();
String result = din.readUTF();
StringTokenizer st = new StringTokenizer(result );
int portnum = Integer. parseInt (st. nextToken());
String hname = st. nextToken ();
return new PortAddr (hname, portnum ) ;
}
//public static void main(String []args) {
//
//Name myclient = new Name();
//try {
//myclient. insertName ("hellol" , "o a k . e c e . u t e x a s . edu" , 1000);
//PortAddr pa = myclient. searchName ("hellol" ) ;
//System. out . println (pa.getHostName() + ":"+ pa. getport());
//}
//catch(Exception e) {
//System. err. println ("Server aborted :");
//}
//}
}
