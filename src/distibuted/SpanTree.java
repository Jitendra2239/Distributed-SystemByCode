package distibuted;

import java.io.IOException;

public class SpanTree extends Process {
    public int parent = -1; // no parent yet
    public IntLinkedList children = new IntLinkedList();
    int numReports = 0;
    boolean done = false;
    public SpanTree(Linker initComm, boolean isRoot) throws IOException {
        super(initComm);
        if (isRoot) {
            parent = myId;
	    if (initComm.neighbors.size() == 0) 
		done = true;
            else 
		sendToNeighbors( "invite", myId);
        }
    }
    public synchronized void waitForDone() { // block till children known
	while (!done) myWait();
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        if (tag.equals("invite")) {
            if (parent == -1) {
            	numReports++;
                parent = src;
                sendMsg(src, "accept");
                for (int i = 0; i < N; i++)
                    if ((i != myId) && (i != src) && isNeighbor(i))
                        sendMsg(i, "invite");
            } else
                sendMsg(src, "reject");
        } else if ((tag.equals("accept")) || (tag.equals("reject"))) {
            if (tag.equals("accept")) children.add(src);
            numReports++;
            if (numReports == comm.neighbors.size()) {
		done = true;
		notify();
	    }
        }
    }
}