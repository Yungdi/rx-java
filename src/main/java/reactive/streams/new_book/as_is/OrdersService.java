package reactive.streams.new_book.as_is;

import reactive.streams.new_book.Input;
import reactive.streams.new_book.Output;

class OrdersService {
    private final ShoppingCardService shoppingCardService;

    public OrdersService(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    void process() {
        Input input = new Input();
        Output output = shoppingCardService.calculate(input);
    }

}
