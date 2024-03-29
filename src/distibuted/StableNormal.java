package distibuted;

import java.io.IOException;
import java.util.Timer;
public class StableNormal extends Process implements Lock {
    int myState = 0;
    int leftState = 0;
    public StableNormal(Linker initComm) {
        super(initComm);
    }
    public synchronized void requestCS() {
        while (leftState == myState) myWait();
    }
    public synchronized void releaseCS() throws IOException {
        myState = leftState;
        sendToken();
    }
    public synchronized void sendToken() throws IOException {
        int next = (myId + 1) % N;
        sendMsg(next, "token", myState);
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        if (tag.equals("token")) {
            leftState = m.getMessageInt();
            notify();
	    Util.mySleep(1000);
            sendToken();
        }
    }
}
