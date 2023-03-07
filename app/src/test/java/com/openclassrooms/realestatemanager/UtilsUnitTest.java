package com.openclassrooms.realestatemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsUnitTest {

    @Test
    public void testConvertDollarToEuro() {
        assertEquals(1, Utils.convertDollarToEuro(1));
        assertEquals(186, Utils.convertDollarToEuro(200));
    }

    @Test
    public void testConvertEuroToDollar() {
        assertEquals(1, Utils.convertEuroToDollar(1));
        assertEquals(214, Utils.convertEuroToDollar(200));
    }

    @Test
    public void testGetTodayDate() {
        String today = Utils.getTodayDate();
        assertNotNull(today);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(dateFormat.format(new Date()), today);
    }

    @Test
    public void testIsInternetAvailable() throws Exception {
        ConnectivityManager connectivityManager = mock(ConnectivityManager.class);
        WifiManager wifiManager = mock(WifiManager.class);
        Context context = mock(Context.class);

        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        when(context.getSystemService(Context.WIFI_SERVICE)).thenReturn(wifiManager);

        NetworkInfo wifiInfo = mock(NetworkInfo.class);
        when(wifiInfo.isConnected()).thenReturn(true);

        NetworkInfo mobileInfo = mock(NetworkInfo.class);
        when(mobileInfo.isConnectedOrConnecting()).thenReturn(true);

        when(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).thenReturn(wifiInfo);
        when(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).thenReturn(mobileInfo);

        assertTrue(Utils.isInternetAvailable(context));
    }
}
