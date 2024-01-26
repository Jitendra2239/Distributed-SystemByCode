package distibuted;

import java.io.IOException;

public interface Synchronizer extends MsgHandler {
	public void initialize(MsgHandler initProg);
    	public void sendMessage(int destId, String tag, int msg) throws IOException;
	public void nextPulse() throws IOException;// block for the next pulse 
}