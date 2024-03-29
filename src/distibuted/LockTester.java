package distibuted;

public class LockTester {
    public static void main(String[] args) throws Exception {
        Linker comm = null;
        try {
            String baseName = "server";
            int myId = 0;
            int numProc = 3;
            comm = new Linker(baseName, myId, numProc);
            Lock lock = null;
            lock = new LamportMutex(comm);
//            if (args[3].equals("Lamport"))
//                lock = new LamportMutex(comm);
//            if (args[3].equals("RicartAgrawala"))
//                lock = new RAMutex(comm);
//            if (args[3].equals("DiningPhil"))
//                lock = new DinMutex(comm);
//            if (args[3].equals("CircToken"))
//                lock = new CircToken(comm,0);
            for (int i = 0; i < numProc; i++)
               if (i != myId)
                  (new ListenerThread(i, (MsgHandler)lock)).start();
            while (true) {
                System.out.println(myId + " is not in CS");
                Util.mySleep(2000);
                lock.requestCS();
                Util.mySleep(2000);
                System.out.println(myId + " is in CS *****");
                lock.releaseCS();
            }
        }
        catch (InterruptedException e) {
            if (comm != null) comm.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}