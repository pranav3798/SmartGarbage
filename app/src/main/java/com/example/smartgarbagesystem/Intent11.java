package com.example.smartgarbagesystem;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intent11 extends Fragment {

    ExpandableListAdapter listAdapter1;
    ExpandableListView expListView1;
    List<String> listDataHeader1;
    HashMap<String, List<String>> listDataChild1;
    View view;

    public static Intent11 newInstance() {
        Intent11 fragment = new Intent11();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_intent11, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // get the listview
        expListView1 = (ExpandableListView) view.findViewById(R.id.lvExp1);

        // preparing list data
        prepareListData();

        listAdapter1 = new ExpandableListAdapter(getActivity(), listDataHeader1, listDataChild1);

        // setting list adapter
        expListView1.setAdapter(listAdapter1);

        // Listview Group click listener
        expListView1.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        /*expListView1.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader1.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView1.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader1.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        listDataHeader1.get(groupPosition)
                                + " : "
                                + listDataChild1.get(
                                listDataHeader1.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });*/
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader1 = new ArrayList<String>();
        listDataChild1 = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader1.add("Pizza Boxes");
        listDataHeader1.add("Home Glass");
        listDataHeader1.add("Bottle Caps");
        listDataHeader1.add("Wet Paper");
        listDataHeader1.add("Milk And Juice Cartons");
        listDataHeader1.add("Paper Coffee Cups");
        listDataHeader1.add("Utilized Baby Diapers");
        listDataHeader1.add("Aerosol Cans");
        listDataHeader1.add("Ceramics And Pottery");



        // Adding child data
        List<String> pizza = new ArrayList<String>();
        pizza.add("This is among the most frequently incorrect products. They are made of cardboard, the grease from the pizza pollutes the raw product. You can tear off the untarnished parts and recycle them!");

        List<String> home = new ArrayList<String>();
        home.add("Products like window panes, mirrors, light bulbs and meals are not practical and must be neglected of your recycling.");

        List<String> bottle = new ArrayList<String>();
        bottle.add("They cannot be recycled with the bottles due to the fact that the caps of bottles are a various type of plastic. Due to the fact that it will not melt, the issue comes down to the melting points of the plastics; if a cap gets into the mix it can infect an entire batch of plastic.");

        List<String> wetpaper = new ArrayList<String>();
        wetpaper.add("Paper that has actually gotten wet can have harmed fibres that make recycling difficult or hard. Make sure to cover your recyclable items to keep them safe from the components.");

        List<String> milk = new ArrayList<String>();
        milk.add("Due to the fact that these are frequently covered with a thin layer of wax they cannot be recycled.");

        List<String> coffeecups = new ArrayList<String>();
        coffeecups.add("Frequently believed of as a much better option to Styrofoam, paper cups posture concerns to recycling as well due to the plastic finishing used to avoid dripping. Your best option is to bring your very own mug!");

        List<String> diapers = new ArrayList<String>();
        diapers.add("The plastic and paper from them cannot be restored by many recycling centres.");

        List<String> aerosol = new ArrayList<String>();
        aerosol.add("While these are made from metal, because of the chemicals utilized to pressurize the cans they are categorized as a home contaminated materials.");

        List<String> ceramics = new ArrayList<String>();
        ceramics.add("This consists of things like coffee mugs and old flower pots. Check out contributing products like this if they remain in alright shape, another person might have the ability to recycle them!");

        listDataChild1.put(listDataHeader1.get(0), pizza); // Header, Child data
        listDataChild1.put(listDataHeader1.get(1), home);
        listDataChild1.put(listDataHeader1.get(2), bottle);
        listDataChild1.put(listDataHeader1.get(3), wetpaper);
        listDataChild1.put(listDataHeader1.get(4), milk);
        listDataChild1.put(listDataHeader1.get(5), coffeecups);
        listDataChild1.put(listDataHeader1.get(6), diapers);
        listDataChild1.put(listDataHeader1.get(7), aerosol);
        listDataChild1.put(listDataHeader1.get(8), ceramics);
    }
}


