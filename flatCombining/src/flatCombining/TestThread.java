package flatCombining;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class TestThread  extends Thread {

	private static int ID_GEN = 0;
	private CGBT bTree;	
	private int id;
	AtomicInteger operations = new AtomicInteger();
	private int add;
	private int remove;
	private int contains;
	private int numOperations;
	
	
	public TestThread(int threadCount,CGBT bTree, int add, int remove, int contains) {
		id = ID_GEN++;
		this.bTree=bTree;
		this.add=0;
		this.remove=0;
		this.contains=0;
		this.numOperations=0;
//	System.out.println(id+"\n");
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			Random random = new Random();
			int number;
		//	System.out.println("In testThread for thread: "+id+";add: "+add+"; remove: "+remove+";contains: "+contains);
			for(int i=0;i<2000;i++){
				number= random.nextInt(100);
			//	System.out.println("Adding "+number);
				bTree.insert(new Integer(number));
				add+=1;
			}
			
			for(int j=0;j<2000;j++){
				number= random.nextInt(100);
			//	System.out.println("Searching for "+number);
				bTree.search(new Integer(number));
				contains+=1;
			}
			
			for(int k=0;k<2000;k++){
				number= random.nextInt(100);	
			//	System.out.println("delete for "+number);
				bTree.remove(new Integer(number));
				remove+=1;
				
			}
		
				numOperations+=add+contains+remove;
			
		}

	
		
	}
	
	public int getNumOperations()
	{
		return numOperations;
	}
	
	
	
	public int getThreadId(){
		return id;
	}
	
	public int getOperations(){
		return operations.get();
		
	}
	
	
}