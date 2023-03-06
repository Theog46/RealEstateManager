package com.openclassrooms.realestatemanager;

import android.content.Context;

import com.openclassrooms.realestatemanager.Database.RealEstateManagerDB;
import com.openclassrooms.realestatemanager.Repository.PropertieRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static PropertieRepository providePropertieData(Context context) {
        RealEstateManagerDB db = RealEstateManagerDB.getInstance(context);
        return new PropertieRepository(db.propertieDao(), db.photosDao(), db.agentsDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideVMF(Context context) {
        PropertieRepository propertieRepository = providePropertieData(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(propertieRepository, executor);
    }
}
