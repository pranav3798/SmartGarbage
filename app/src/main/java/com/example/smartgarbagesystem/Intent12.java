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

public class Intent12 extends Fragment {

    ExpandableListAdapter listAdapter2;
    ExpandableListView expListView2;
    List<String> listDataHeader2;
    HashMap<String, List<String>> listDataChild2;
    View view;

    public static Intent12 newInstance() {
        Intent12 fragment = new Intent12();
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_intent12, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // get the listview
        expListView2 = (ExpandableListView) view.findViewById(R.id.lvExp2);

        // preparing list data
        prepareListData();

        listAdapter2 = new ExpandableListAdapter(getActivity(), listDataHeader2, listDataChild2);

        // setting list adapter
        expListView2.setAdapter(listAdapter2);

        // Listview Group click listener
        expListView2.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

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
        listDataHeader2 = new ArrayList<String>();
        listDataChild2 = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader2.add("Landfills");
        listDataHeader2.add("Incineration");
        listDataHeader2.add("Methods For Recycling");
        listDataHeader2.add("Biological Reprocessing");
        listDataHeader2.add("Recovery Of Energy");
        listDataHeader2.add("Reduction And Avoidance Methods");

        // Adding child data
        List<String> landfill = new ArrayList<String>();
        landfill.add("This method involves burying off the waste and this is the most common practice for the disposal of waste around the Globe. These landfills are quite often conventional with deserted and vacant locations around the cities. In case, landfills or borrow pits are designed carefully they can serve as economical and quite sanitized method for waste dumping. However, not much effectively designed and older landfills can cost a big amount to the government not just in terms of money but also in the environmental and health issues. Apart from the general poorly designed landfill’s common problems like wind-blown debris and generation of liquid, it can also cause production of gas, which is extremely hazardous. This gas can be a reason for production of odor, killing surface vegetation and greenhouse effects.\n" +
                "The characteristic, which is must for an up to date landfill, is inclusion of clay or leachate lining. The waste that is deposited is generally compressed for increasing the density and stability and later it is covered to have it prevented from vermin. One thing, which is addition to modern landfills, is the “gas extraction system” installation. This system is included to have the gas extracted from the borrow pit.");
        List<String> incineration = new ArrayList<String>();
        incineration.add("This is the dumping off method, which involves combustion for waste materials. This sort of dumping off for waste materials through incineration and temperature is known as “thermal treatment”. This method is utilized to convert waste materials in to gas, heat, ash and steam.\n" +
                "Incineration is conducted on both individual and industrial scale. This method is used for disposing off all sorts of matters. This generally is the most recognized practical method for disposing off perilous material. This however, is the conflict-ridden method for it causes the emission of perilous gases.\n" +
                "Incineration is a common practice in Japan because of scarcity of land, which facilitates through not requiring landfill for waste dumping. Two widely used terms, which are facilitating burning of waste material in furnace and boiler for generation of heat, electricity and steam, are (Waste-to-energy) WtW and (energy-from-waste)EfW.\n" +
                "The burning procedure in this method for waste disposal is never perfect so, fear for gas pollutants is mounting. Special concerns have been focused over some extremely importunate organics as dioxins. These organic products are created with the incinerator and they are causations for serious consequences affecting environment.");

        List<String> methods = new ArrayList<String>();
        methods.add("Products like PVC, LDEP, PP and PS are recyclable though they are not collected for recycling. The material, which is composed of a single type, is recyclables and is much easy to work with. However, complex products are difficult to treat and so are complex for recycling.");

        List<String> bio = new ArrayList<String>();
        bio.add("Waste materials, which come in organic nature are treated through biological reprocessing. The waste materials with organic nature are plant, food and paper products. This reprocessing or recycling of this organic matter is put to biological decomposition which later if recycled in form of mulch or compost for landscaping and agricultural purposes. Additionally, the waste gas, which is collected from the process, is used for the production of electricity. The goal behind biological reprocessing is to control and speed up the natural decomposition for organic matter.\n" +
                "A numerous sort of composting techniques and methods for digestion are employed depending upon the requirement as if digestion is required for household heaps or industrial materials. There are diverse methods for biological reprocessing like anaerobic and aerobic techniques.");

        List<String> recovery = new ArrayList<String>();
        recovery.add("Waste materials can directly be combusted for the generation of energy as fuel or other method, indirect combustion can also be adopted for energy generation. Thermal treatment for recycling purpose included burning of waste for the generation of energy used for household purpose i.e. cooking and heating while the energy from recycling can also be produced at industrial level from boilers. Among thermal treatments you have two related kinds i.e. Pyrolysis and gasification. In these sorts of methods, materials are heated  with little supply of oxygen at high temperature. This process is conducted in sealed vessels with high pressure. In Pyrolysis, the solid is converted in to liquid state and liquid is converted in to gas. These products of treatment can then be used for the production of energy. The residue that is left behind is generally known as “char”, which is further treated for the production of more useable products. In Gasification however, the material to be treated is directly converted in to SynGas (synthetic gas) which has hydrogen and carbon dioxide as its components.");

        List<String> reduction = new ArrayList<String>();
        reduction.add("Another method for the management of the waste material is the avoidance for it being created and this method is generally named as “waste reduction”. The avoidance for waste production includes using the second-hand product and repairing the products you have broken in place of buying new things. Products are designed for refilling and reusing. Cutting down use of disposable things and producing products that are more complex.");

        listDataChild2.put(listDataHeader2.get(0), landfill); // Header, Child data
        listDataChild2.put(listDataHeader2.get(1), incineration);
        listDataChild2.put(listDataHeader2.get(2), methods);
        listDataChild2.put(listDataHeader2.get(3), bio);
        listDataChild2.put(listDataHeader2.get(4), recovery);
        listDataChild2.put(listDataHeader2.get(5), reduction);
    }
}


