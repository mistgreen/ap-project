package littlepartypro.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class SearchRequest {

    private String searchQuery;

    public SearchRequest(String searchQuery) {
        this.searchQuery = searchQuery;
    }


    public SearchRequest() {
    }
}
