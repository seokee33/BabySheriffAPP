package com.sheriffs.babysheriff.view.main;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.adapter.HospitalListAdapter;
import com.sheriffs.babysheriff.databinding.MenuHealthBinding;
import com.sheriffs.babysheriff.model.Hospital;
import com.sheriffs.babysheriff.util.GetHospitalDataXml;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu_hospital extends Fragment implements
                                                GoogleMap.OnMyLocationButtonClickListener,
                                                GoogleMap.OnMyLocationClickListener
{
    String address;
    View view;

    private int Zoom = 14;
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationClient;
    private ArrayList<Hospital> hospitals;

    //리사이클러뷰
    private HospitalListAdapter diaryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private GetHospitalDataXml task;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_hospital,container,false);
        hospitals = new ArrayList<>();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        // 다이어리 리스트
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_Hospital_List);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment = SupportMapFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.map_Hospital,mapFragment)
                .commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                Log.i("MyLocTest","지도 준비됨");
                map = googleMap;
                getNowLocation();
                ArrayList<LatLng> locations = new ArrayList<>();
                Log.i("location",String.valueOf(hospitals.size()));

                map.setMyLocationEnabled(true);
                onMyLocationButtonClick();
            }
        });

        task = new GetHospitalDataXml(this);
        //task.execute("https://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire?serviceKey=iXusHy2%2FZz7qRhjkwpWBlGWiJaRpGZgroGBlBBow45q4oibueEQnnwMUkAUsOVG3PSlRqQKOYf2SGRxHRJAqoQ%3D%3D&QD=D002&Q0=대구광역시&Q1=북구");

        return view;
    }
    public void mapMarker(ArrayList<Hospital> arrayList){
        for(Hospital hospital : hospitals){
            Log.i("location",String.valueOf(hospital.getLat())+hospital.getLon());
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(hospital.getLat(),hospital.getLon()))
                    .title(hospital.getDutyName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }
    public void getHosData(ArrayList<Hospital> arrayList)
    {
        this.hospitals = new ArrayList<>();
        for(Hospital h : arrayList)
            hospitals.add(h);

        diaryAdapter = new HospitalListAdapter(hospitals,this);
        recyclerView.setAdapter(diaryAdapter);
        diaryAdapter.notifyDataSetChanged();
    }

    public void moveCamera(LatLng latLng){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , Zoom));
    }


    @SuppressLint("MissingPermission")
    private void getNowLocation(){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener( getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()) , Zoom));
                            double d1 = location.getLatitude();
                            double d2 = location.getLongitude();
                            ArrayList<String> area = getAddress(d1, d2);
                            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire"); /*URL*/
                            try {
                                urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=iXusHy2%2FZz7qRhjkwpWBlGWiJaRpGZgroGBlBBow45q4oibueEQnnwMUkAUsOVG3PSlRqQKOYf2SGRxHRJAqoQ%3D%3D"); /*Service Key*/
                                urlBuilder.append("&" + URLEncoder.encode("Q0","UTF-8") + "=" + URLEncoder.encode(area.get(0), "UTF-8")); /*주소(시도)*/
                                urlBuilder.append("&" + URLEncoder.encode("Q1","UTF-8") + "=" + URLEncoder.encode(area.get(1), "UTF-8")); /*주소(시군구)*/
                                urlBuilder.append("&" + URLEncoder.encode("QD","UTF-8") + "=" + URLEncoder.encode("D002", "UTF-8")); /*CODE_MST의'D000' 참조(D001~D029)*/
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            task.execute(urlBuilder.toString());
                        }
                    }
                });
    }


    public ArrayList<String> getAddress(double d1, double d2){
        TextView tv = view.findViewById(R.id.tv_address);
        List<Address> list = null;
        ArrayList<String> result = new ArrayList<>();
        final Geocoder geocoder = new Geocoder(this.getContext());
        try {
            list = geocoder.getFromLocation(d1,d2,1);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if(list != null){
            if(list.size()==0){
                tv.setText("해당되는 주소 정보는 없습니다");
            }else{
                result.add((String)list.get(0).getAdminArea());
                result.add((String)list.get(0).getSubLocality());
                tv.setText(result.get(0)+","+result.get(1));
            }
        }
        return result;
    }


    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }

}
