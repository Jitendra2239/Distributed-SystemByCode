package distibuted;

import java.io.IOException;

public interface Election extends MsgHandler {
    void startElection() throws IOException;
    int getLeader();//blocks till the leader is known
}
