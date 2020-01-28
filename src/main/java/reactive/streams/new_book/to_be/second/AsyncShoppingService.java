package reactive.streams.new_book.to_be.second;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncShoppingService implements ShoppingCardService {
    @Override
    public Future<Output> calculate(Input input) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        return executorService.submit(Output::new);
    }
}
