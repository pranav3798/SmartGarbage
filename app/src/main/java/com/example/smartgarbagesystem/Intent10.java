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

public class Intent10 extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    View view;

    public static Intent10 newInstance() {
        Intent10 fragment = new Intent10();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_intent10, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

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
        /*expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });*/

        // Listview on child click listener
        /*expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
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
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Aluminium");
        listDataHeader.add("PET Plastic Bottles");
        listDataHeader.add("Newspaper");
        listDataHeader.add("Corrugated Cardboard");
        listDataHeader.add("Steel Cans");
        listDataHeader.add("Glass Containers");
        listDataHeader.add("Magazines And Blended Paper");


        // Adding child data
        List<String> aluminium = new ArrayList<String>();
        aluminium.add("Aluminium cans are One Hundred Percent recyclable, and they can be recycled over and over once again. Even much better, turning recycled cans into brand-new cans takes 95 percent less energy than making new ones. How about beginning with all those soda and juice cans?");

        List<String> pet = new ArrayList<String>();
        pet.add("Making plastic out of recycled resources utilizes about two-thirds less energy than making brand-new plastic. And since PET plastic bottles, more than any other type of plastic, are the most typically utilized type, they are normally the simplest to recycle.");


        List<String> newspaper = new ArrayList<String>();
        newspaper.add("It appears like a no-brainer to set up a recycling bin next to your trash can for newspaper and any other scrap paper. Recycling the paper saves resources, conserves energy, and does not obstruct up the landfills, there’s no factor not to do it.");

        List<String> corrugated = new ArrayList<String>();
        corrugated.add("This is responsible for 13.8 percent of our community waste stream. Next time Amazon or Flipkart provides a huge box to your workplace, be sure to break it down and recycle it– after you have actually cleared it, of course.");

        List<String> steel = new ArrayList<String>();
        steel.add("Simply like aluminium, steel items can be recycled over once again without jeopardizing the quality of the steel. Recycling steel conserves the comparable energy to power 18 million families a year.");

        List<String> glass = new ArrayList<String>();
        glass.add("Recycled glass conserves 50 percent energy versus virgin glass, and recycling simply one glass container conserves enough energy to light a 100-watt bulb for 4 hours. Recycled glass produces 20 percent less air contamination and 50 percent less water pollution, and one lots of glass made from 50 percent recycled products conserves 250 pounds of mining waste. Wow!");

        List<String> magazines = new ArrayList<String>();
        magazines.add("There are so numerous factors to recycle all kinds of paper that it makes no sense not to. Recycling one heap of paper conserves 17 trees and 7,000 gallons of water.");

        listDataChild.put(listDataHeader.get(0), aluminium); // Header, Child data
        listDataChild.put(listDataHeader.get(1), pet);
        listDataChild.put(listDataHeader.get(2), newspaper);
        listDataChild.put(listDataHeader.get(3), corrugated);
        listDataChild.put(listDataHeader.get(4), steel);
        listDataChild.put(listDataHeader.get(5), glass);
        listDataChild.put(listDataHeader.get(6), magazines);


    }
}


