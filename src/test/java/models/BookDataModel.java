package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDataModel {

    private String userId;
    private List<Isbn> collectionOfIsbns;

    public BookDataModel(String userId, String isbn) {
        this.userId = userId;
        this.collectionOfIsbns = Collections.singletonList(new Isbn(isbn));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Isbn {
        private String isbn;
    }
}