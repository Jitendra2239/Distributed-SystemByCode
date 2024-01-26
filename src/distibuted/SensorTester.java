package distibuted;

public class SensorTester {
    public static void main(String[] args) throws Exception {
	String baseName = "server";
	int myId = 0;
	int numProc = 3;
	VCLinker comm = new VCLinker(baseName, myId, numProc);
        int algoCode = 0;       
        SensorCircToken sp = new SensorCircToken(
                     comm, Symbols.coordinator, algoCode);
        sp.initiate();
        for (int i = 0; i < numProc; i++)
            if (i != myId) (new ListenerThread(i, sp)).start();
    }
}
