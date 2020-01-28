package reactive.streams.legacy;

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " :" + i + "초");
            }
        });
//        thread.setDaemon(true);
        thread.start();

        System.out.println("메인 스레드 끝났어~~");
    }
}
