package distibuted;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		VCLinker  linker=new VCLinker ("server",0,3);
	   linker.sendMsg(1, "tag", "hello sever1");
	   linker.sendMsg(2, "tag", "hello sever2");
	}

}
