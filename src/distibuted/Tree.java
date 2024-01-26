package distibuted;

import java.io.IOException;

public class Tree extends Process {
    int parent = -1;
    int level;
    public Tree(Linker initComm, boolean isRoot) throws IOException {
        super(initComm);
        if (isRoot) initiate();
    }
    public void initiate() throws IOException {
        parent = myId;
        level = 0;
        for (int i = 0; i < N; i++)
            if (isNeighbor(i))
                sendMsg(i, "invite", level + 1);
    }
    public synchronized void handleMsg(Msg m, int src, String tag) throws IOException {
        if (tag.equals("invite")) {
            if (parent == -1) {
                parent = src;
                level = m.getMessageInt();
                for (int i = 0; i < N; i++)
                    if (isNeighbor(i) && (i != src))
                        sendMsg(i, "invite", level + 1);
            }
        }
    }
}
