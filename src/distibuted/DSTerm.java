package distibuted;

import java.io.IOException;

public class DSTerm extends Process implements TermDetector {
    final static int passive = 0, active = 1;
    int state = passive;
    int D = 0;
    int parent = -1;
    boolean envtFlag;
    public DSTerm(Linker initComm) {
        super(initComm);
        envtFlag = (myId == Symbols.coordinator);
    }
    public synchronized void initiate() {
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        if (tag.equals("signal")) {
            D = D - 1;        
            if (D == 0) {
                if (envtFlag)
                    System.out.println("Termination Detected");
                else if (state == passive) {
                    sendMsg(parent, "signal");
                    parent = -1;
                }
            }
        } else { // application message
            state = active;
            if ((parent == -1) && !envtFlag) {
                parent = src;        
            } else
                sendMsg(src, "signal");
        }
    }
    public synchronized void sendAction() {
        D = D + 1;
    }
    public synchronized void turnPassive() throws IOException {
        state = passive;
        if ((D == 0) && (parent != -1)) {
            sendMsg(parent, "signal");
            parent = -1;
        }
    }
}
