package reactive.streams.new_book.to_be.third;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.concurrent.CompletionStage;

class OrderService {
    private final ShoppingCardService shoppingCardService;

    public OrderService(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    void process() {
        CompletionStage<Output> completionStage = shoppingCardService.calculate(new Input());
        completionStage.thenAccept(System.out::println);
    }

}
