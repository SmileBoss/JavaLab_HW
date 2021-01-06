package ru.itis;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {
    private Deque<Runnable> tasks;

    PoolWorker[] pool;

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.pool = new PoolWorker[threadsCount];

        for (int i = 0; i < this.pool.length; i++) {
            this.pool[i] = new PoolWorker();
            this.pool[i].start();
        }
    }


//    public static ru.itis.ThreadPool newPool(int threadsCount) {
//        ru.itis.ThreadPool threadPool = new ru.itis.ThreadPool();
//        threadPool.tasks = new ConcurrentLinkedDeque<>();
//        threadPool.pool = new PoolWorker[threadsCount];
//
//        for (int i = 0; i < threadPool.pool.length; i++) {
//            threadPool.pool[i].start();
//        }
//        return threadPool;
//    }



    public void submit(Runnable task){
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable task;
            while(true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }
}
