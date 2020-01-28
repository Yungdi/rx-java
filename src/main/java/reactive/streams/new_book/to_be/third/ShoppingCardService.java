package reactive.streams.new_book.to_be.third;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

import java.util.concurrent.CompletionStage;

interface ShoppingCardService {
    CompletionStage<Output> calculate(Input input);
}
