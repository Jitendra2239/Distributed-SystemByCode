package distibuted;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class Linker {
	  DataInputStream  dIn ;
	  DataOutputStream[]  dataOut;
	  DataInputStream[]  dataIn ; 
int myId, N;
Connector connector ;
public IntLinkedList neighbors = new IntLinkedList ();
public Linker (String basename, int id, int numProc) throws Exception {
myId = id ;
N = numProc;
dataIn = new DataInputStream[ numProc] ;
dataOut = new DataOutputStream  [ numProc];
Topology.readNeighbors(myId, numProc, neighbors);
connector= new Connector ();
connector.Connect(basename, id, numProc, dataIn, dataOut);
}
public void sendMsg(int destId , String tag, String msg) throws IOException {

dataOut [ destId].writeUTF(myId + " "+ destId + " " +tag + " "+ msg + "#");
dataOut [ destId]. flush ();
}
public void sendMsg( int destId , String tag) throws IOException {
sendMsg( destId , tag, "0" );
}
public void multicast( IntLinkedList destIds , String tag, String msg) throws IOException{
for (int i =0; i<destIds. size (); i++) {
sendMsg(  destIds.getEntry (i ), tag, msg);

}
}
public Msg receiveMsg ( int fromId ) throws IOException {
String result = dataIn [ fromId ].readUTF();
Util. println ("received message " + result );
StringTokenizer st = new StringTokenizer(result);
int srcId = Integer.parseInt (st. nextToken());
int destId = Integer. parseInt (st.nextToken ());
String tag = st. nextToken ();
String msg = st. nextToken("#");
return new Msg( srcId , destId , tag, msg);
}
public int getMyId () { return myId; }
public int getNumProc() { return N; }
public void close () { connector.closesockets();}
}
