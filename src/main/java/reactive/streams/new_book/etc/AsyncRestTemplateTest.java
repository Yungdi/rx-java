package reactive.streams.new_book.etc;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;

public class AsyncRestTemplateTest {
    public static void main(String[] args) {
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        SuccessCallback<ResponseEntity<String>> onSuccess = r -> {
            System.out.println(r.toString());
        };
        FailureCallback onFailure = f -> {
            System.out.println(f.getMessage());
        };
        ListenableFuture<ResponseEntity<String>> forEntity = asyncRestTemplate.getForEntity("localhost:8080", String.class);
        forEntity.addCallback(onSuccess, onFailure);
    }
}