package com.example.silkwaytransit.ui.home.map;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.silkwaytransit.R;
import com.example.silkwaytransit.network.stations.StationNames;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.maps.GeoApiContext;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment{

    private List<com.google.maps.model.LatLng> places1 = new ArrayList<>();
    List<Marker> markers = new ArrayList<Marker>();
    ArrayList<StationNames> dataModalArrayList = new ArrayList<>();
    FirebaseFirestore db;
    public String shymkent;
    public String taraz;
    public String almaty1;
    LatLng geo0;
    LatLng geo1;
    LatLng geo2;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            db = FirebaseFirestore.getInstance();
            dataModalArrayList = new ArrayList<>();
            db.collection("StationNames").get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            new Handler().postDelayed(() ->{
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d : list) {
                                        System.out.println();
                                        StationNames dataModal = d.toObject(StationNames.class);
                                        dataModalArrayList.add(dataModal);
                                    }
                                    shymkent = dataModalArrayList.get(0).getGeolocation();
                                    System.out.println(shymkent);
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    taraz = dataModalArrayList.get(1).getGeolocation();
                                    almaty1 = dataModalArrayList.get(2).getGeolocation();

                                    String[] latlong =  shymkent.split(",");
                                    double lat0 = Double.parseDouble(latlong[0]);
                                    double long0 = Double.parseDouble(latlong[1]);

                                    latlong =  taraz.split(",");
                                    double lat1 = Double.parseDouble(latlong[0]);
                                    double long1 = Double.parseDouble(latlong[1]);

                                    latlong =  almaty1.split(",");
                                    double lat2 = Double.parseDouble(latlong[0]);
                                    double long2 = Double.parseDouble(latlong[1]);

                                    geo0 = new LatLng(lat0, long0);
                                    geo1 = new LatLng(lat1, long1);
                                    geo2 = new LatLng(lat2, long2);

                                    markers.add(googleMap.addMarker(new MarkerOptions().position(geo0).title("Шымкент")));
                                    markers.add(googleMap.addMarker(new MarkerOptions().position(geo1).title("Тараз")));
                                    markers.add(googleMap.addMarker(new MarkerOptions().position(geo2).title("Алматы 1")));

                                } else {
                                    Toast.makeText(getActivity(), "No data found in Database", Toast.LENGTH_SHORT).show();
                                }
                            }, 10000);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Fail to load data..", Toast.LENGTH_SHORT).show();
                        }
                    });



            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }

            LatLngBounds bounds = builder.build();

            int padding = 150;
            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 40));
                }
            });

            //Получаем контекст для запросов, mapsApiKey хранит в себе String с ключом для карт
            GeoApiContext geoApiContext = new GeoApiContext.Builder()
                    .apiKey("AIzaSyA1PNCAJHL5YFonGX9ZXOuZ7K9iMCS7fHc")
                    .build();

            PolylineOptions line = new PolylineOptions();

            LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

            line.add(geo0, geo1, geo2);

            line.width(16f).color(R.color.purple_200);

            googleMap.addPolyline(line);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                    System.out.println("Context" + getContext());
                    bottomSheetDialog.setContentView(R.layout.dialog);

                    ImageView dialogButton = bottomSheetDialog.findViewById(R.id.cancel_button);

                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });

                    bottomSheetDialog.show();

                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void listOfGeolocations() {

    }
}

