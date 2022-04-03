package com.kys.mohalla.ui.grocery;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kys.mohalla.Model.ItemModel;

import java.util.ArrayList;

public class GroceryViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ItemModel>> mModel;
    int itemImage;
    String itemName;
    String itemPrice;

    public GroceryViewModel() {
        mModel = new MutableLiveData<>();
        //mModel.setValue();
    }

    public String getText() {
        return itemName;
    }

    public String getPrice() {
        return itemPrice;
    }
}