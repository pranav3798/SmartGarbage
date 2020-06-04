package com.example.smartgarbagesystem;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class Intent1 extends AppCompatActivity {

   private ListView lv;
   private TextView title;
    private Toolbar toolbar;
    private ActionBar a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        a = getSupportActionBar();
        assert  a!=null;
        a.setDisplayHomeAsUpEnabled(true);

        lv = (ListView) findViewById(R.id.lv);

        List<String> recycle = new ArrayList<String>();
        recycle.add("Pizza Boxes");
        recycle.add("Bottle Cups");
        recycle.add("Milk and Juice Carton");
        recycle.add("Paper Cups");
        recycle.add("Diapers");
        recycle.add("Aerosol Cans");
        recycle.add("Office Papers");
        recycle.add("Colored Papers");
        recycle.add("Lotion bottles");
        recycle.add("Bleach and detergent containers");
        recycle.add("Ice cream tubs and yogurt cups");
        recycle.add("Milk and juice jugs");
        recycle.add("Household cleaning containers");
        recycle.add("Cooking oil containers");
        recycle.add("Pop and water bottles");
        recycle.add("Hardcover books (remove covers)");
        recycle.add("Fiber board (cereal boxes, soda boxes)");
        recycle.add("Brown paper bags");
        recycle.add("Shiny Papers");
        recycle.add("Envelopes");
        recycle.add("Catalogs");
        recycle.add("Newspapers");
        recycle.add("Magazines");
        recycle.add("Aluminium Cans");
        recycle.add("Plastic Bottles");
        recycle.add("Cardboard");
        recycle.add("Glass Containers");
        recycle.add("Books");
        recycle.add("CDs, DVDs");
        recycle.add("Medical supplies and sharps");
        recycle.add("Styrofoam & packing peanuts");
        recycle.add("Plastic utensils,plates");
        recycle.add("Disposable baby & adult diapers");
        recycle.add("Garden hose");
        recycle.add("Landscape plastic & tile material, PVC pipes");
        recycle.add("Frozen food & juice boxes");
        recycle.add("Dirty paper plates, cups & napkins");
        recycle.add("Food waste (uneaten food)");
        recycle.add("Tissues & toilet paper");
        recycle.add("Paper Towels");
        recycle.add("Wax paper");



        title = findViewById(R.id.listTitle);



        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                recycle){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item

                    TextView tv = (TextView) view.findViewById(android.R.id.text1);
                    tv.setTextSize(25);
                    if(position<21)
                    {
                        tv.setTextColor(Color.GREEN);}
                     else if(position>=21){
                        tv.setTextColor(Color.RED);
                    }

                // Generate ListView Item using TextView
                return view;
            }
        };

        lv.setAdapter(arrayAdapter);

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
