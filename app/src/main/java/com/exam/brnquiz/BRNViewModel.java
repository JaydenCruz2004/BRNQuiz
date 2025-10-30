package com.exam.brnquiz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BRNViewModel extends ViewModel {
    private final MutableLiveData<List<String>> qs = new MutableLiveData<>(new ArrayList<>());
    public LiveData<List<String>> getQs(){
        return qs;
    }
    public void addQ(String question){
        List<String> current = new ArrayList<>(qs.getValue());
        current.add(question);
        qs.setValue(current);

    }

}
