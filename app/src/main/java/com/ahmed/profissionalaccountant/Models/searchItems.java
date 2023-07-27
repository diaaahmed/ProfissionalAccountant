package com.ahmed.profissionalaccountant.Models;

public class searchItems {
    public int SearchCriteria;
    public String SearchString;

    public searchItems(int searchCriteria, String searchString) {
        SearchCriteria = searchCriteria;
        SearchString = searchString;
    }

    public int getSearchCriteria() {
        return SearchCriteria;
    }

    public void setSearchCriteria(int searchCriteria) {
        SearchCriteria = searchCriteria;
    }

    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String searchString) {
        SearchString = searchString;
    }
}
