package Main;
import utils.CFifo;

public class testes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CFifo fifo = new CFifo(3);
		fifo.write(2);
		System.out.println(fifo.toString());
		System.out.println(fifo.read());
		fifo.write(3);
		System.out.println(fifo.toString());
		fifo.write(5);
		System.out.println(fifo.toString());
		System.out.println(fifo.read());
		System.out.println(fifo.read());
		System.out.println(fifo.read()==null);
		
	}
}
