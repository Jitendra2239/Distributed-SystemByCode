package distibuted;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Connector {

ServerSocket listener ;
Socket [] link ;
public void Connect (String basename, int myId, int numProc,DataInputStream [] dataIn , DataOutputStream []dataOut) throws Exception {
	

Name myNameclient = new Name();
link = new Socket [numProc];

int localport = getLocalPort (myId);
listener = new ServerSocket (localport );
/* register in the name server */
myNameclient.insertName ( basename + myId,(InetAddress.getLocalHost().getHostName()), localport );
/* accept connections from all the smaller processes */
for (int i = 0; i <myId; i++) {
Socket s = listener . accept ();

DataInputStream dIn=new DataInputStream(s.getInputStream());
String result= dIn.readUTF();
System.out.println(result);
StringTokenizer st = new StringTokenizer(result );
int hisId = Integer . parseInt (st. nextToken());
int destId = Integer. parseInt (st. nextToken());
String tag = st. nextToken();
if (tag. equals ("hello")) {
link [ hisId ] = s;
dataIn [ hisId ] = dIn;
dataOut[ hisId ] = new DataOutputStream(s.getOutputStream());
//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//String str="",str2="";  
//while(!str.equals("stop")){  
//str=br.readLine();  
//dataOut[ hisId ] .writeUTF(str);  
//dataOut[ hisId ] .flush();  
//str2=dataIn [ hisId ].readUTF();  
//System.out.println("Server says: "+str2);  
//} 
}
}
 /* contact all the bigger processes */
for ( int i = myId + 1; i <numProc; i ++) {
   PortAddr addr ;

do {
	addr = myNameclient.searchName( basename + i );
	Thread. sleep (100) ;

} while (addr. getport () == -1);
link[i] = new Socket(addr.getHostName(), addr.getport());
dataOut [ i] = new DataOutputStream(link [ i].getOutputStream());
dataIn [ i ] = new DataInputStream(link [ i].getInputStream());
/* send a hello messnge to P-a */

dataOut [ i].writeUTF(myId +" " + i +" " + "hello " + "null");
dataOut [ i ]. flush ();
//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//String str="",str2="";
//while(!str.equals("stop")) {
//	str=dataIn [ i ] .readUTF();
//	System.out.println("client says:"+str);
//	str2=br.readLine();
//	dataOut [ i] .writeUTF(str2);
//	dataOut [ i] .flush();
//}
}
}
int getLocalPort (int id) { return Symbols. Serverport + 10 + id ; }
public void closesockets (){
try {
listener . close ();
for (int i=0;i<link. length; i++) link [ i]. close ();
} 
catch ( Exception e) { System. err . println (e);}
}
}
