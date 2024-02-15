package littlepartypro.dto;

import java.util.Arrays;
import java.util.List;

public class SearchRequest {

    private String searchQuery;

    public SearchRequest(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(final String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public SearchRequest() {
    }
}
