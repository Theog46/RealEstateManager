package com.openclassrooms.realestatemanager;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.realestatemanager.Repository.PropertieRepository;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final PropertieRepository propertieRepository;
    private final Executor executor;

    public ViewModelFactory(PropertieRepository propertieRepository, Executor executor) {
        this.propertieRepository = propertieRepository;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PropertieViewModel.class)) {
            return (T) new PropertieViewModel(propertieRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
