package reactive.streams.legacy.async;

import java.util.concurrent.*;

public class AtomicCounterExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final AtomicCounter counter = new AtomicCounter();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = executorService.submit(task, true);
        Future<Boolean> future2 = executorService.submit(task, true);

        if (future1.get() && future2.get())
            System.out.println(counter.get());
        else
            System.err.println("실패");

        executorService.shutdown();
    }
}
