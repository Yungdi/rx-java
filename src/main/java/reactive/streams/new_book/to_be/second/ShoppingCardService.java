package reactive.streams.new_book.to_be.second;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.concurrent.Future;

interface ShoppingCardService {
    Future<Output> calculate(Input input);
}
