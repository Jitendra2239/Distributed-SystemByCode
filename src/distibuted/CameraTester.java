package distibuted;

import java.util.Random;
public class CameraTester {
    public static void main(String[] args) throws Exception {
	String baseName = "server";
	int myId = 0;
	int numProc = 3;
	String aglo="SenderCamera";
        Camera camera = null;
        CamCircToken sp = null;
        if (aglo.equals("RecvCamera")) {
	    Linker comm = new Linker(baseName, myId, numProc);
            sp = new CamCircToken(comm, 0);
            camera = new RecvCamera(comm, sp);
        }
        if (aglo.equals("SenderCamera")) {
	    CameraLinker comm = new CameraLinker(baseName, myId, numProc);
            sp = new CamCircToken(comm, 0);
            camera = new SenderCamera(comm, sp);
        }        
        sp.initiate();       
        for (int i = 0; i < numProc; i++)
            if (i != myId) (new ListenerThread(i, camera)).start();
        if (myId == 0) camera.globalState();
    }
}