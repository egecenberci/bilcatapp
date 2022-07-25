package com.beyza.bilcat;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference dataBase= FirebaseDatabase.getInstance().getReference();
    private ArrayList<CatData> actualCatList = CatList.list;
    private ArrayList<String> neighbourhoodList = new ArrayList<String>();

    public final static LatLng SA_BUILDING = new LatLng(39.867728811561996, 32.748151063092564);
    public final static LatLng SB_BUILDING = new LatLng(39.868321704800096, 32.748177885183544);
    public final static LatLng B_BUILDING = new LatLng(39.868766371366014, 32.748086690074224);
    public final static LatLng G_BUILDING = new LatLng(39.86870049503017, 32.749669193377805);
    public final static LatLng A_BUILDING = new LatLng(39.867955264444824, 32.74962091361405);
    public final static LatLng MA_BUILDING = new LatLng(39.86737883745595, 32.750130533345754);
    public final static LatLng T_BUILDING = new LatLng(39.868321704798696, 32.74920248898774);
    public final static LatLng FF_BUILDING = new LatLng(39.86584716502896, 32.74895036135986);
    public final static LatLng DORM_78 = new LatLng(39.8653558616064, 32.74612900862697);
    public final static LatLng DORM_76 = new LatLng(39.86463164554874, 32.74756737347474);
    public final static LatLng DORM_77 = new LatLng(39.864476310565394, 32.746597661993825);
    public final static LatLng BILKENT_MAIN_CAMPUS = new LatLng(39.86793467785359, 32.748784064394805);

    boolean buildSA = false;
    boolean buildSB = false;
    boolean buildB = false;
    boolean buildG = false;
    boolean buildA = false;
    boolean buildMA = false;
    boolean buildT = false;
    boolean buildFF = false;
    boolean buildDORM78 = false;
    boolean buildDORM76 = false;
    boolean buildDORM77 = false;

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
        LatLng specificNeigh;
        CatData currentCat;
        populateNeighbourhoodList();
        System.out.println("QWERTY" + neighbourhoodList);
        if(CatList.mapClicked){
            currentCat = CatList.list.get(CatList.currentCat);
            specificNeigh = checkSpecificNeighbourhood(currentCat);
            createGeneralNeighborhood();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(specificNeigh));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificNeigh, 17));
        }
        else if (CatList.pingClicked){
            currentCat = CatList.list.get(CatList.currentCat);
            checkSpecificNeighbourhood(currentCat);
            specificNeigh = checkSpecificNeighbourhood(currentCat);
            createGeneralNeighborhood();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(specificNeigh));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(specificNeigh, 17));
        }
        else {
            checkNeighborhoods();
            createGeneralNeighborhood();
            LatLng bilkent = new LatLng(39.867803, 32.748827);
            mMap.addMarker(new MarkerOptions().position(bilkent).title("Bilkent Main Campus"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bilkent));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BILKENT_MAIN_CAMPUS, 17));
        }
    }

    /**
     * Takes CatData object, gets its neighbourhood and changes the build boolean to true, returns the neighbourhood's center point as LatLng object
     * @param cat CatData object whose neighbourhood will be checked
     * @return the Latlng of the center of the neighbourhood
     */
    public LatLng checkSpecificNeighbourhood(CatData cat){
        String catNeigh = cat.getNeighbourhood();
        if (catNeigh.equals("SA Building")){
            buildSA = true;
            return SA_BUILDING;
        }
        if (catNeigh.equals("SB Building")) {
            buildSB = true;
            return SB_BUILDING;
        }
        if (catNeigh.equals("B Building")) {
            buildB = true;
            return B_BUILDING;
        }
        if (catNeigh.equals("G Building")) {
            buildG = true;
            return G_BUILDING;
        }
        if (catNeigh.equals("A Building")) {
            buildA = true;
            return A_BUILDING;
        }
        if (catNeigh.equals("MA Building")) {
            buildMA = true;
            return MA_BUILDING;
        }
        if (catNeigh.equals("T Building")) {
            buildT = true;
            return T_BUILDING;
        }
        if (catNeigh.equals("FF Building")) {
            buildFF = true;
            return FF_BUILDING;
        }
        if (catNeigh.equals("Dorm 76")) {
            buildDORM76 = true;
            return DORM_76;
        }
        if (catNeigh.equals("Dorm 77")) {
            buildDORM77 = true;
            return DORM_77;
        }
        if (catNeigh.equals("Dorm 78")) {
            buildDORM78 = true;
            return DORM_78;
        }
        else return new LatLng(0,0);
    }

    /**
     * Populate the neighbourhoodList with the neighbourhoods of the cats in the database
     */
    public void populateNeighbourhoodList(){
        for (CatData c: actualCatList) {
            neighbourhoodList.add(c.getNeighbourhood());
        }
    }
    /**
     * Checks all the neighborhoods of the cats in the database and updates the build boolean values
     */
    public void checkNeighborhoods(){
        if (actualCatList!= null){
            if (neighbourhoodList.contains("SA Building"));
                buildSA = true;
            if (neighbourhoodList.contains("SB Building"))
                buildSB = true;
            if (neighbourhoodList.contains("B Building"))
                buildB = true;
            if (neighbourhoodList.contains("G Building"))
                buildG = true;
            if (neighbourhoodList.contains("A Building"))
                buildA = true;
            if (neighbourhoodList.contains("MA Building"))
                buildMA = true;
            if (neighbourhoodList.contains("T Building"))
                buildT = true;
            if (neighbourhoodList.contains("FF Building"))
                buildFF = true;
            if (neighbourhoodList.contains("Dorm 76"))
                buildDORM76 = true;
            if (neighbourhoodList.contains("Dorm 77"))
                buildDORM77 = true;
            if (neighbourhoodList.contains("Dorm 78"))
                buildDORM78 = true;
        }
    }

    /**
     * Builds the existing neighbourhoods on the general map
     */
    public void createGeneralNeighborhood(){
        if (buildSA) {
            Circle neigh_SA = mMap.addCircle(new CircleOptions()
                    .center(SA_BUILDING)
                    .radius(15)
                    .strokeColor(Color.MAGENTA)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_SA_M = mMap.addMarker(new MarkerOptions()
                    .position(SA_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("SA Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("SA Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            );
            neigh_SA.setTag(actualCatList.get(neighbourhoodList.indexOf("SA Building")));
        }
        if (buildSB) {
            Circle neigh_SB = mMap.addCircle(new CircleOptions()
                    .center(SB_BUILDING)
                    .radius(15)
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_SB_M = mMap.addMarker(new MarkerOptions()
                    .position(SB_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("SB Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("SB Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            );
            neigh_SB.setTag(actualCatList.get(neighbourhoodList.indexOf("SB Building")));
        }
        if (buildB){
            Circle neigh_B = mMap.addCircle(new CircleOptions()
                .center(B_BUILDING)
                .radius(15)
                .strokeColor(Color.GREEN)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_B_M = mMap.addMarker(new MarkerOptions()
                    .position(B_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("B Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("B Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );
            neigh_B.setTag(actualCatList.get(neighbourhoodList.indexOf("B Building")).getName());
        }
        if (buildG) {
            Circle neigh_G = mMap.addCircle(new CircleOptions()
                    .center(G_BUILDING)
                    .radius(15)
                    .strokeColor(Color.CYAN)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_G_M = mMap.addMarker(new MarkerOptions()
                    .position(G_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("G Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("G Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            );
            neigh_G.setTag(actualCatList.get(neighbourhoodList.indexOf("G Building")));
        }
        if (buildA) {
            Circle neigh_A = mMap.addCircle(new CircleOptions()
                    .center(A_BUILDING)
                    .radius(15)
                    .strokeColor(Color.MAGENTA)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_A_M = mMap.addMarker(new MarkerOptions()
                    .position(A_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("A Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("A Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            );
            neigh_A.setTag(actualCatList.get(neighbourhoodList.indexOf("A Building")));
        }
        if (buildMA) {
            Circle neigh_MA = mMap.addCircle(new CircleOptions()
                    .center(MA_BUILDING)
                    .radius(15)
                    .strokeColor(Color.YELLOW)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_MA_M = mMap.addMarker(new MarkerOptions()
                    .position(MA_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("MA Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("MA Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            );
            neigh_MA.setTag(actualCatList.get(neighbourhoodList.indexOf("MA Building")));
        }
        if (buildT) {
            Circle neigh_T = mMap.addCircle(new CircleOptions()
                    .center(T_BUILDING)
                    .radius(15)
                    .strokeColor(Color.GREEN)
                    .fillColor(Color.argb(50,0,0,0))
                    .clickable(true)//retrieve cat's name and building name on click
            );
            Marker neigh_T_M = mMap.addMarker(new MarkerOptions()
                    .position(T_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("T Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("T Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );
            neigh_T.setTag(actualCatList.get(neighbourhoodList.indexOf("T Building")));
        }
        if (buildFF) {
            Circle neigh_FF = mMap.addCircle(new CircleOptions()
                    .center(FF_BUILDING)
                    .radius(15)
                    .strokeColor(Color.GREEN)
                    .fillColor(Color.argb(50,0,0,0))
                    .clickable(true)//retrieve cat's name and building name on click
            );
            Marker neigh_FF_M = mMap.addMarker(new MarkerOptions()
                    .position(FF_BUILDING)
                    .title(actualCatList.get(neighbourhoodList.indexOf("FF Building")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("FF Building")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            );
            neigh_FF.setTag(actualCatList.get(neighbourhoodList.indexOf("FF Building")));
        }
        if (buildDORM76){
            Circle neigh_DORM76 = mMap.addCircle(new CircleOptions()
                    .center(DORM_76)
                    .radius(15)
                    .strokeColor(Color.YELLOW)
                    .fillColor(Color.argb(50,0,0,0))
                    .clickable(true)//retrieve cat's name and building name on click
            );
            Marker neigh_DORM76_M = mMap.addMarker(new MarkerOptions()
                    .position(DORM_76)
                    .title(actualCatList.get(neighbourhoodList.indexOf("Dorm 76")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("Dorm 76")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            );
            neigh_DORM76.setTag(actualCatList.get(neighbourhoodList.indexOf("Dorm 76")));
        }
        if (buildDORM77){
            Circle neigh_DORM77 = mMap.addCircle(new CircleOptions()
                    .center(DORM_77)
                    .radius(15)
                    .strokeColor(Color.CYAN)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_DORM77_M = mMap.addMarker(new MarkerOptions()
                    .position(DORM_77)
                    .title(actualCatList.get(neighbourhoodList.indexOf("Dorm 77")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("Dorm 77")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            );
            neigh_DORM77.setTag(actualCatList.get(neighbourhoodList.indexOf("Dorm 77")));
        }
        if (buildDORM78){
            Circle neigh_DORM78 = mMap.addCircle(new CircleOptions()
                    .center(DORM_78)
                    .radius(15)
                    .strokeColor(Color.BLUE)
                    .fillColor(Color.argb(50,0,0,0))
            );
            Marker neigh_DORM78_M = mMap.addMarker(new MarkerOptions()
                    .position(DORM_78)
                    .title(actualCatList.get(neighbourhoodList.indexOf("Dorm 78")).getName())
                    .snippet(actualCatList.get(neighbourhoodList.indexOf("Dorm 78")).getNeighbourhood())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            );
            neigh_DORM78.setTag(actualCatList.get(neighbourhoodList.indexOf("Dorm 78")));
        }

    }

    public void createSpecificNeighborhood(){
        //get cat neigbourhood
        //String catNeighbourhood = CatData.get

        //get cat comments
    }

    public void addPing(){

    }

}
