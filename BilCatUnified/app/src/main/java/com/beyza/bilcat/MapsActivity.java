package com.beyza.bilcat;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public final static LatLng SA_BUILDING = new LatLng(39.867728811561996, 32.748151063092564);
    public final static LatLng SB_BUILDING = new LatLng(39.868321704800096, 32.748177885183544);
    public final static LatLng B_BUILDING = new LatLng(39.868766371366014, 32.748086690074224);
    public final static LatLng G_BUILDING = new LatLng(39.86870049503017, 32.749669193377805);
    public final static LatLng A_BUILDING = new LatLng(39.867955264444824, 32.74962091361405);
    public final static LatLng MA_BUILDING = new LatLng(39.86737883745595, 32.750130533345754);
    public final static LatLng T_BUILDING = new LatLng(39.868321704798696, 32.74920248898774);
    public final static LatLng FF_BUILDING = new LatLng(39.86584716502896, 32.74895036135986);
    public final static LatLng BILKENT_MAIN_CAMPUS = new LatLng(39.86793467785359, 32.748784064394805);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_generalmap);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng bilkent = new LatLng(39.867803, 32.748827);
        mMap.addMarker(new MarkerOptions().position(bilkent).title("Bilkent Main Campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bilkent));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BILKENT_MAIN_CAMPUS,17));
        createGeneralNeighborhood();
    }

    public void createGeneralNeighborhood(){
        Circle Neigh_SA = mMap.addCircle(new CircleOptions()
                .center(SA_BUILDING)
                .radius(15)
                .strokeColor(Color.RED)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_SB = mMap.addCircle(new CircleOptions()
                .center(SB_BUILDING)
                .radius(15)
                .strokeColor(Color.BLUE)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_B = mMap.addCircle(new CircleOptions()
                .center(B_BUILDING)
                .radius(15)
                .strokeColor(Color.GREEN)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_G = mMap.addCircle(new CircleOptions()
                .center(G_BUILDING)
                .radius(15)
                .strokeColor(Color.CYAN)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_A = mMap.addCircle(new CircleOptions()
                .center(A_BUILDING)
                .radius(15)
                .strokeColor(Color.MAGENTA)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_MA = mMap.addCircle(new CircleOptions()
                .center(MA_BUILDING)
                .radius(15)
                .strokeColor(Color.YELLOW)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_T = mMap.addCircle(new CircleOptions()
                .center(T_BUILDING)
                .radius(15)
                .strokeColor(Color.GRAY)
                .clickable(true)//retrieve cat's name and building name on click
        );
        Circle Neigh_FF = mMap.addCircle(new CircleOptions()
                .center(FF_BUILDING)
                .radius(15)
                .strokeColor(Color.LTGRAY)
                .clickable(true)//retrieve cat's name and building name on click
        );
    }

    public void createSpecificNeighborhood(){
        //get cat neigbourhood

        //get cat comments


    }


}
