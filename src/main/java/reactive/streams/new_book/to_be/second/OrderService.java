package reactive.streams.new_book.to_be.second;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class OrderService {
    private final ShoppingCardService shoppingCardService;

    public OrderService(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    Optional<Output> process() {
        Input input = new Input();
        Future<Output> future = shoppingCardService.calculate(input);
        Output output = null;
        try {
            output = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(output);
    }

}
