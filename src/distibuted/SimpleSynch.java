package distibuted;

import java.io.IOException;
import java.util.LinkedList;
public class SimpleSynch extends Process implements Synchronizer {
    int pulse = 0;
    MsgHandler prog;
    boolean rcvEnabled [];
    IntLinkedList pendingS = new IntLinkedList();
    IntLinkedList pendingR = new IntLinkedList();
    public SimpleSynch(Linker initComm) {
        super(initComm);
        rcvEnabled = new boolean[N];
        for (int i = 0; i < N; i++)
            rcvEnabled[i] = false;
    }
    public synchronized void initialize(MsgHandler initProg) {
        prog = initProg;
        pendingS.addAll(comm.neighbors);
        notifyAll();
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        while (!rcvEnabled[src]) myWait();
        pendingR.removeObject(src);
        if (pendingR.isEmpty()) notifyAll();
        if (!tag.equals("synchNull"))
            prog.handleMsg(m, src, tag);
        rcvEnabled[src] = false;
    }
    public synchronized void sendMessage(int destId, String tag, int msg) throws IOException {     
        if (pendingS.contains(destId)) {
            pendingS.removeObject(destId);
            sendMsg(destId, tag, msg);
        } else
            System.err.println("Error: sending two messages/pulse");
    }
    @SuppressWarnings("unchecked")
	public synchronized void nextPulse() throws IOException {
        while (!pendingS.isEmpty()) { // finish last pulse by sending null
            int dest = (int) pendingS.remove();          
            sendMsg(dest, "synchNull", 0);
        }
        pulse ++;
        Util.println("**** new pulse ****:" + pulse);
        pendingS.addAll(comm.neighbors);
        pendingR.addAll(comm.neighbors);
        for (int i = 0; i < N; i++)
            rcvEnabled[i] = true;
        notifyAll();
        while (!pendingR.isEmpty()) myWait();
    }
}
