package reactive.streams.new_book.to_be.first;

import reactive.streams.new_book.Input;

class OrderService {
    private final ShoppingCardService shoppingCardService;

    public OrderService(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    void process() {
        Input input = new Input();
        shoppingCardService.calculate(input, System.out::println);
    }


}
