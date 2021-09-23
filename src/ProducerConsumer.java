class Sample
{
    int num;
    boolean flag=false;
    public synchronized void set(int num)
    {
        while (flag)
        {
            try {
                wait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Set:"+num);
        this.num=num;
        flag=true;
        notify();
    }
    public synchronized void get()
    {
        while (!flag)
        {
            try {
                wait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Get:"+num);
        flag=false;
        notify();
    }
}
class Producer extends Thread
{
    Sample sample;

    public Producer(Sample sample) {
        this.sample = sample;
        Thread t=new Thread(this,"Producer");
        t.start();
    }
    public void run()
    {
        int i=0;
        while (true)
        {
            sample.set(i++);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
class Consumer extends Thread{
    Sample sample;

    public Consumer(Sample sample) {
        this.sample = sample;
        Thread t=new Thread(this,"Producer");
        t.start();
    }
    public void run()
    {
        while (true)
        {
            sample.get();
            try
            {
                Thread.sleep(10000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args)
    {
        Sample sample= new Sample();
        new Producer(sample);
        new Consumer(sample);
    }
}
