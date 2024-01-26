package distibuted;

public class ConsensusTester2 {
    public static void main(String[] args) throws Exception {
        String baseName = "server";
        int myId = 2;
        int numProc = 3;
        Linker comm = new Linker(baseName, myId, numProc);
        Consensus sp = new Consensus(comm, 3);
        for (int i = 0; i < numProc; i++)
            if (i != myId) (new ListenerThread(i, sp)).start();
        sp.propose(myId);
        System.out.println("The value decided:" + sp.decide());
    }
}
