package flatCombining;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class TestThreadFCB  extends Thread {

	private static int ID_GEN = 0;
	private FCBTree bTree;	
	private int id;
	AtomicInteger operations = new AtomicInteger();
	private int add;
	private int remove;
	private int contains;
	private int numOperations;
	
	public TestThreadFCB(int threadCount,FCBTree bTree, int add, int remove, int contains) {
		id = ID_GEN++;
		this.bTree=bTree;
		this.add=0;
		this.remove=0;
		this.contains=0;
		this.numOperations=0;
		
//	System.out.println(id+"\n");
	}
	/*
	public void initializeValues()
	{
		add=0;
		contains=0;
		remove=0;
		numOperations=0;
	}
	*/
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
				this.add+=1;
			}
			
			for(int j=0;j<2000;j++){
				number= random.nextInt(100);
			//	System.out.println("Searching for "+number);
				bTree.search(new Integer(number));
				this.contains+=1;
			}
			
			for(int k=0;k<2000;k++){
				number= random.nextInt(100);	
			//	System.out.println("delete for "+number);
				bTree.remove(new Integer(number));
				this.remove+=1;
			}
			
			this.numOperations+=this.add+this.contains+this.remove;
			
		}

	
		
	}
	
	
	public int getTotalOperations()
	{
		return this.numOperations;
		
	}
	
	
	public int getThreadId(){
		return id;
	}
	
	public int getOperations(){
		return operations.get();
		
	}
	
	
}