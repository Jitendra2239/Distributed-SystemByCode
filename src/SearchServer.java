import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class SearchServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       try {
    	   Search obj=new SearchQuery();
    	   LocateRegistry.createRegistry(1900);
    	   Naming.rebind("rmi://localhost:1900"+
                   "/geeksforgeeks",obj);
       }
       catch(Exception ae)
       {
           System.out.println(ae);
       }
	}

}
