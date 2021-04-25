package Main;
import utils.BlockingQueue;


public class testes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> queue = new BlockingQueue<>(3);
		queue.put(1);
		System.out.println(queue.size());
		
	}
}
