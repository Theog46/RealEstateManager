package com.openclassrooms.realestatemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.Repository.PropertieRepository;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PropertieViewModelUnitTest {

    private PropertieViewModel propertieViewModel;
    private PropertieRepository propertieRepository;
    private Executor executor;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Before
    public void setUp() {
        propertieRepository = mock(PropertieRepository.class);
        executor = Executors.newSingleThreadExecutor();
        propertieViewModel = new PropertieViewModel(propertieRepository, executor);
    }

    @Test
    public void testGetAllProperties() {
        List<Propertie> properties = new ArrayList<>();
        properties.add(new Propertie(1, "https://images7.alphacoders.com/341/341714.jpg", "type", 100000, 100, 8, 2, 3, "description", "address", "state", "entryDate", "soldDate", "agentName", "agentPic", true, false, true, false, false, true, 154787.2, -15487.5));
        properties.add(new Propertie(2, "https://images7.alphacoders.com/341/341714.jpg", "type", 100000, 100, 8, 2, 3, "description", "address", "state", "entryDate", "soldDate", "agentName", "agentPic", true, false, true, false, false, true, 154787.2, -15487.5));
        properties.add(new Propertie(3, "https://images7.alphacoders.com/341/341714.jpg", "type", 100000, 100, 8, 2, 3, "description", "address", "state", "entryDate", "soldDate", "agentName", "agentPic", true, false, true, false, false, true, 154787.2, -15487.5));

        MutableLiveData<List<Propertie>> liveData = new MutableLiveData<>();
        liveData.setValue(properties);

        when(propertieRepository.getAllProperties()).thenReturn(liveData);

        LiveData<List<Propertie>> result = propertieViewModel.getAllProperties();

        assertNotNull(result);
        assertEquals(properties, result.getValue());
    }

    @Test
    public void testAddPropertie() {
        Propertie propertie = new Propertie(4, "https://images7.alphacoders.com/341/341714.jpg", "type", 100000, 100, 8, 2, 3, "description", "address", "state", "entryDate", "soldDate", "agentName", "agentPic", true, false, true, false, false, true, 154787.2, -15487.5);

        propertieViewModel.addPropertie(propertie);

        verify(propertieRepository, times(1)).addPropertie(eq(propertie));
    }

    @Test
    public void testUpdatePropertie() {
        Propertie propertie = new Propertie(1, "https://images7.alphacoders.com/341/341714.jpg", "typeUPDATED", 100000, 100, 8, 2, 3, "description", "address", "state", "entryDate", "soldDate", "agentName", "agentPic", true, false, true, false, false, true, 154787.2, -15487.5);

        propertieViewModel.updatePropertie(propertie);

        verify(propertieRepository, times(1)).updatePropertie(eq(propertie));
    }

    @Test
    public void testGetAllPhotos() {
        List<Photos> photosList = new ArrayList<>();
        photosList.add(new Photos(1, 1, "url", "legend"));
        photosList.add(new Photos(2, 2, "url", "legend"));
        photosList.add(new Photos(3, 1, "url", "legend"));

        MutableLiveData<List<Photos>> liveData = new MutableLiveData<>();
        liveData.setValue(photosList);

        when(propertieRepository.getAllPhotos()).thenReturn(liveData);

        LiveData<List<Photos>> result = propertieViewModel.getAllPhotos();

        assertNotNull(result);
        assertEquals(photosList, result.getValue());
    }

    @Test
    public void testGetAllAgents() {
        List<Agents> agentsList = new ArrayList<>();
        agentsList.add(new Agents(1, "name", "photo"));
        agentsList.add(new Agents(2, "name", "photo"));
        agentsList.add(new Agents(3, "name", "photo"));

        MutableLiveData<List<Agents>> liveData = new MutableLiveData<>();
        liveData.setValue(agentsList);

        when(propertieRepository.getAllAgents()).thenReturn(liveData);

        LiveData<List<Agents>> result = propertieViewModel.getAllAgents();

        assertNotNull(result);
        assertEquals(agentsList, result.getValue());
    }

    @Test
    public void testAddPhotos() {
        Photos photos = new Photos(1, 1, "url", "legend");

        propertieViewModel.addPhotos(photos);

        verify(propertieRepository, times(1)).addPhotos(eq(photos));
    }

    @Test
    public void testGetAllPhotosByPropertiesId() {
        long propertieId = 1L;

        List<Photos> photosList = new ArrayList<>();
        photosList.add(new Photos(1, 1, "url", "legend"));
        photosList.add(new Photos(2, 2, "url", "legend"));
        photosList.add(new Photos(3, 1, "url", "legend"));

        MutableLiveData<List<Photos>> liveData = new MutableLiveData<>();
        liveData.setValue(photosList);

        when(propertieRepository.getAllPhotosByPropertiesId(eq(propertieId))).thenReturn(liveData);

        LiveData<List<Photos>> result = propertieViewModel.getAllPhotosByPropertiesId(propertieId);

        assertNotNull(result);
        assertEquals(photosList, result.getValue());
    }



}
