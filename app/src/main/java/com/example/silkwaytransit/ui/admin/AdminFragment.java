package com.example.silkwaytransit.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.R;
import com.example.silkwaytransit.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {

    FragmentMapsBinding binding;
    private List<LatLng> places = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        //Получаем контекст для запросов, mapsApiKey хранит в себе String с ключом для карт
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey("AIzaSyA1PNCAJHL5YFonGX9ZXOuZ7K9iMCS7fHc")
                .build();

        places.add(new LatLng(51.116398692024205, 71.53678253486467));
        places.add(new LatLng(49.986660251734364, 73.22343619402888));
        places.add(new LatLng(43.34289546139241, 76.94848388129272));

//Здесь будет наш итоговый путь состоящий из набора точек
        DirectionsResult result = null;
        try {
            result = DirectionsApi.newRequest(geoApiContext)
                    .origin(places.get(0))//Место старта
                    .destination(places.get(places.size() - 1))//Пункт назначения
                    .waypoints(places.get(1), places.get(2)).await();//Промежуточные точки. Да, не очень красиво, можно через цикл, но зато наглядно
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//Преобразование итогового пути в набор точек
        List<LatLng> path = result.routes[0].overviewPolyline.decodePath();

//Линия которую будем рисовать
        PolylineOptions line = new PolylineOptions();

        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

//Проходимся по всем точкам, добавляем их в Polyline и в LanLngBounds.Builder
        for (int i = 0; i < path.size(); i++) {
            line.add(new com.google.android.gms.maps.model.LatLng(path.get(i).lat, path.get(i).lng));
            latLngBuilder.include(new com.google.android.gms.maps.model.LatLng(path.get(i).lat, path.get(i).lng));
        }

//Делаем линию более менее симпатичное
        line.width(16f).color(R.color.purple_200);

//Добавляем линию на карту
        googleMap.addPolyline(line);
    }
    }
