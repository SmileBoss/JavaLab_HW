public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3);
        Runnable task1 = () -> {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " A");
        }
    };

    Runnable task2 = () -> {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " B");
        }
    };

    Runnable task3 = () -> {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " C");
        }
    };


        pool.submit(task1);
        pool.submit(task2);
        pool.submit(task3);
    }
}
