package distibuted;

public class Localhost {

	public static void main(String []args) {
	
	Name myclient = new Name();
	try {
	myclient. insertName ("localhost1" , "localhost" , 1000);
//	PortAddr pa = myclient. searchName ("hello1" ) ;
//	System. out . println (pa.getHostName() + ":"+ pa. getport());
//	PortAddr pa1 = myclient. searchName ("server1" ) ;
//	System. out . println (pa1.getHostName() + ":"+ pa1. getport());
	}
	catch(Exception e) {
	System. err. println ("Server aborted :");
	}
	}

}
