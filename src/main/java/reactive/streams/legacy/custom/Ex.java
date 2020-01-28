package reactive.streams.legacy.custom;

public class Ex {
    public static void main(String[] args) {
        Publisher publisher = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {
                Subscription subscription = new Subscription() {
                    @Override
                    public void request(long num) {

                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        };
    }
}
