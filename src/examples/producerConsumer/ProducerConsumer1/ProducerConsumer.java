import genclass.GenericIO;

/**
 *    General description:
 *       producers / consumers problem - monitor-based solution.
 */

public class ProducerConsumer
{
  /**
   *    Main method.
   *
   *    @param args runtime arguments
   */

   public static void main (String [] args)
   {
     int nElem = 2;                                    // size of the data transfer buffer
     MemFIFO<Integer> fifo =                           // data transfer buffer
             new MemFIFO<> (new Integer [nElem]);
     int nProd = 4,                                    // number of producer threads
         nCons = 4;                                    // number of consumer threads
     Producer [] prod = new Producer [nProd];          // array of producer threads
     Consumer [] cons = new Consumer [nCons];          // array of consumer threads
     int nIter = 6;                                    // number of iterations of the life cycle

     GenericIO.writelnString ();
     GenericIO.writelnString ("Main thread ID is " + Thread.currentThread ().getId ());
     GenericIO.writelnString ("Main thread name is " + Thread.currentThread ().getName ());
     GenericIO.writelnString ("Current state of the main thread is " +
                              Thread.currentThread ().getState ().toString ());
     GenericIO.writelnString ();

     /* problem initialization */

     for (int i = 0; i < nProd; i++)
     { prod[i] = new Producer ("Producer_" + i, i, nIter, fifo);
       GenericIO.writelnString ("Producer " + i + " thread ID " + " is " + prod[i].getId ());
       GenericIO.writelnString ("Producer " + i + " thread name " + " is " + prod[i].getName ());
       GenericIO.writelnString ("Current state of the producer " + i + " thread is " + prod[i].getState ().toString ());
     }

     for (int i = 0; i < nCons; i++)
     { cons[i] = new Consumer ("Consumer_" + i, i, nIter, fifo);
       GenericIO.writelnString ("Consumer " + i + " thread ID " + " is " + cons[i].getId ());
       GenericIO.writelnString ("Consumer " + i + " thread name " + " is " + cons[i].getName ());
       GenericIO.writelnString ("Current state of the consumer " + i + " thread is " + cons[i].getState ().toString ());
     }
     GenericIO.writelnString ();

     /* start of the simulation */

     for (int i = 0; i < nProd; i++)
     { prod[i].start ();
       GenericIO.writelnString ("Current state of the producer " + i + " thread is " + prod[i].getState ().toString ());
     }

     for (int i = 0; i < nCons; i++)
     { cons[i].start ();
       GenericIO.writelnString ("Current state of the consumer " + i + " thread is " + cons[i].getState ().toString ());
     }
     GenericIO.writelnString ();

     /* wait for the end of the simulation */

     for (int i = 0; i < nProd; i++)
     { try
       { prod[i].join ();
         GenericIO.writelnString ("Current state of the producer " + i + " thread is " + prod[i].getState ().toString ());
       }
       catch (InterruptedException e) {}
       GenericIO.writelnString ("Producer thread " + i + " has ended.");
     }

     for (int i = 0; i < nCons; i++)
     { try
       { cons[i].join ();
         GenericIO.writelnString ("Current state of the consumer " + i + " thread is " + cons[i].getState ().toString ());
       }
       catch (InterruptedException e) {}
       GenericIO.writelnString ("Consumer thread " + i + " has ended.");
     }

     GenericIO.writelnString ();
  }
}