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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private DatabaseReference dataBase= FirebaseDatabase.getInstance().getReference();
    public static String catList;
    private ArrayList<CatData> actualCatList;
    private ArrayList<String> neighbourhoodList;

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
        createGeneralNeighborhood();
        LatLng bilkent = new LatLng(39.867803, 32.748827);
        mMap.addMarker(new MarkerOptions().position(bilkent).title("Bilkent Main Campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bilkent));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BILKENT_MAIN_CAMPUS,17));

    }

    /**
     * Checks the neighborhoods of the cats in the database and updates the build boolean values
     */
    public void checkNeighborhoods(){
        //dataBase.startAt("cat 1");
        /*
        //DataSnapshot catsSnap = dataBase.child("cats").get().getResult(); //TASK SI NOT YET COMPLETE ERROR
        Query catsQuery = dataBase.child("cats");
        catsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: catsSnap.getChildren()) {
                    // TODO: handle the post
                    Log.d("didItWOrk", postSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dataBase.child("cats").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Log.d("plswork", dataSnapshot.getChildren().spliterator());
            }
        });*/
        if (CatList.list != null) {
            for (CatData c: CatList.list) {
                actualCatList.add(new CatData(c.getNeighbourhood(),c.getAge(),c.getName()));
            }
            //CatList.list.forEach(CatData c;);
            Log.d("emre's list", CatList.list.toString());
            Log.d("my list", actualCatList.toString());
        }
        if (actualCatList!= null){
            for (CatData c: actualCatList) {
                if (c.getNeighbourhood().equals("SA Building"));
                buildSA = true;
            }
        }
        /*
        dataBase.child("cats").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    MapsActivity.catList = String.valueOf(task.getResult().getValue());
                }
            }
        });
        if (catList != null) {
            if (catList.contains(String.valueOf("SA Building")))
                buildSA = true;
            if (catList.contains(String.valueOf("SB Building")))
                buildSB = true;
            if (catList.contains(String.valueOf("B Building")))
                buildB = true;
            if (catList.contains(String.valueOf("G Building")))
                buildG = true;
            if (catList.contains(String.valueOf("A Building")))
                buildA = true;
            if (catList.contains(String.valueOf("MA Building")))
                buildMA = true;
            if (catList.contains(String.valueOf("T Building")))
                buildT = true;
            if (catList.contains(String.valueOf("FF Building")))
                buildFF = true;
            if (catList.contains(String.valueOf("Dorm 76")))
                buildDORM76 = true;
            if (catList.contains(String.valueOf("Dorm 77")))
                buildDORM77 = true;
            if (catList.contains(String.valueOf("Dorm 78")))
                buildDORM78 = true;
        }

         */
    }

    public void createGeneralNeighborhood(){
        checkNeighborhoods();
        if (buildSA) {
            Circle Neigh_SA = mMap.addCircle(new CircleOptions()
                    .center(SA_BUILDING)
                    .radius(15)
                    .strokeColor(Color.RED)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildSB) {
            Circle Neigh_SB = mMap.addCircle(new CircleOptions()
                    .center(SB_BUILDING)
                    .radius(15)
                    .strokeColor(Color.BLUE)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildB){
        Circle Neigh_B = mMap.addCircle(new CircleOptions()
                .center(B_BUILDING)
                .radius(15)
                .strokeColor(Color.GREEN)
                .clickable(true)//retrieve cat's name and building name on click
        );
        }
        if (buildG) {
            Circle Neigh_G = mMap.addCircle(new CircleOptions()
                    .center(G_BUILDING)
                    .radius(15)
                    .strokeColor(Color.CYAN)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildA) {
            Circle Neigh_A = mMap.addCircle(new CircleOptions()
                    .center(A_BUILDING)
                    .radius(15)
                    .strokeColor(Color.MAGENTA)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildMA) {
            Circle Neigh_MA = mMap.addCircle(new CircleOptions()
                    .center(MA_BUILDING)
                    .radius(15)
                    .strokeColor(Color.YELLOW)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildT) {
            Circle Neigh_T = mMap.addCircle(new CircleOptions()
                    .center(T_BUILDING)
                    .radius(15)
                    .strokeColor(Color.GRAY)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildFF) {
            Circle Neigh_FF = mMap.addCircle(new CircleOptions()
                    .center(FF_BUILDING)
                    .radius(15)
                    .strokeColor(Color.LTGRAY)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildDORM76){
            Circle Neigh_DORM76 = mMap.addCircle(new CircleOptions()
                    .center(DORM_76)
                    .radius(15)
                    .strokeColor(Color.GREEN)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildDORM77){
            Circle Neigh_DORM77 = mMap.addCircle(new CircleOptions()
                    .center(DORM_77)
                    .radius(15)
                    .strokeColor(Color.CYAN)
                    .clickable(true)//retrieve cat's name and building name on click
            );
        }
        if (buildDORM78){
            Circle Neigh_DORM78 = mMap.addCircle(new CircleOptions()
                    .center(DORM_78)
                    .radius(15)
                    .strokeColor(Color.RED)
                    .clickable(true)//retrieve cat's name and building name on click
            );
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
