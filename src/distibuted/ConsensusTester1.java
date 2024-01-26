package distibuted;

public class ConsensusTester1 {

    public static void main(String[] args) throws Exception {
        String baseName = "server";
        int myId = 1;
        int numProc = 3;
        Linker comm = new Linker(baseName, myId, numProc);
        Consensus sp = new Consensus(comm, 3);
        for (int i = 0; i < numProc; i++)
            if (i != myId) (new ListenerThread(i, sp)).start();
        sp.propose(myId);
        System.out.println("The value decided:" + sp.decide());
    }
}
