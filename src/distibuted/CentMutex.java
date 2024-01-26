package distibuted;

import java.io.IOException;

public class CentMutex extends Process implements Lock {
    // assumes that P_0 coordinates and does not request locks.
    boolean haveToken;
    final int leader = 0;
    IntLinkedList pendingQ = new IntLinkedList();
    public CentMutex(Linker initComm) {
        super(initComm);
        haveToken = (myId == leader);
    }
    public synchronized void requestCS() throws IOException {
        sendMsg(leader, "request");
        while (!haveToken) myWait();
    }
    public synchronized void releaseCS() throws IOException {
        sendMsg(leader, "release");
        haveToken = false;
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        if (tag.equals("request")) {
            if (haveToken){
                sendMsg(src, "okay");
                haveToken = false;
            }
            else
                pendingQ.add(src);
        } else if (tag.equals("release")) {
            if (!pendingQ.isEmpty()) {
                int pid = pendingQ.renioveHead();
                sendMsg(pid, "okay");
            } else
                haveToken = true;
        } else if (tag.equals("okay")) {
            haveToken = true;
            notify();
        }
    }
}
