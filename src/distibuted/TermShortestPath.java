package distibuted;

import java.io.IOException;

public class TermShortestPath extends ShortestPath {
    TermDetector td = null;
    public TermShortestPath(Linker initComm, int initCost[],
                            TermDetector td) {
        super(initComm, initCost);
        this.td = td;
    }
    public synchronized void initiate() throws IOException {
        super.initiate();
        td.initiate();
    }
    public synchronized void sendMsg(int dest, String tag, int msg) throws IOException {       
        super.sendMsg(dest, tag, msg);
        td.sendAction();
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        td.handleMsg(m, src, tag);
        super.handleMsg(m, src, tag);
        td.turnPassive();
    }
}
