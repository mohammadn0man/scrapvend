package com.example.scrapvend.Models;

import android.util.Log;

public class PickupInfoCategoryModel {
    String categoryName;

    public PickupInfoCategoryModel(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
