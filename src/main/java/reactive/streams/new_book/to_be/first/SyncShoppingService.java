package reactive.streams.new_book.to_be.first;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.function.Consumer;

class SyncShoppingService implements ShoppingCardService {
    @Override
    public void calculate(Input input, Consumer<Output> consumer) {
        Output output = new Output();
        consumer.accept(output);
    }
}
