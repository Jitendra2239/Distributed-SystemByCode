package distibuted;

public class GlobalFuncTester implements FuncUser {
    public int func(int x, int y) {
        return x + y;
    }
    public static void main(String[] args) throws Exception {
        int myId = 0;
        int numProc = 3;
        Linker comm = new Linker("server", myId, numProc);
        GlobalFunc g = new GlobalFunc(comm, (myId == 0));
        for (int i = 0; i < numProc; i++)
            if (i != myId) 
                (new ListenerThread(i, g)).start();
        int myValue = 1;
        GlobalFuncTester h = new GlobalFuncTester();
        g.initialize(myValue, h);
        int globalSum = g.computeGlobal();
        System.out.println("The global sum is " + globalSum);
    }
}