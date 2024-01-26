import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SearchQuery extends UnicastRemoteObject implements Search{
	
	SearchQuery() throws RemoteException
    {
        super();
    }

	@Override
	public String query(String search) throws RemoteException {
		// TODO Auto-generated method stub
		  String result;
	        if (search.equals("Reflection in Java"))
	            result = "Found";
	        else
	            result = "Not Found";
	 
	        return result;
	}

}
