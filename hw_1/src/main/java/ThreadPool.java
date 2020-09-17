import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {
    private Deque<Runnable> tasks;

    private PoolWorker[] pool;

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.pool = new PoolWorker[threadsCount];

        for (int i = 0; i < this.pool.length; i++) {
            this.pool[i] = new PoolWorker();
            this.pool[i].start();
        }
    }


//    public static ThreadPool newPool(int threadsCount) {
//        ThreadPool threadPool = new ThreadPool();
//        threadPool.tasks = new ConcurrentLinkedDeque<>();
//        threadPool.pool = new PoolWorker[threadsCount];
//
//        for (int i = 0; i < threadPool.pool.length; i++) {
//            threadPool.pool[i].start();
//        }
//        return threadPool;
//    }



    public void submit(Runnable task){
        tasks.offer(task);
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            while (true){
                Runnable nextTask = tasks.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}
