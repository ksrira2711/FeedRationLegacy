package ration_formulation.feed_ration_latest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import Bean.FeedBean;
import Bean.NutrientsSuppliedAndTotalCostBean;
import InputFilters.NumericalInputFilterEditText;
import constants.FeedNameConstants;
import constants.FeedTypePropertyConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FeedMixture extends Activity {
    String DM_str=null,TDN_str=null,CP_str=null,CA_str=null,P_str=null,milk_str=null,weight_animal="";
    String farmerName=null,farmerAddress=null,animalNumber = null,animalType=null,fat_content = null;
    String final_output = "";
    int added_for_release = 0;
    double DM[] = {25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,90,90,90,90,90,90,25,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,30,85,90,90,90,10,90,7,90,90,90};
    double CP[] = {5.3,4.9,7.8,5.0,7.9,7.2,6.2,7.3,4.9,16.2,14.2,16.2,13.1,20.3,20.0,4.0,4.2,3.7,3.3,13.0,12.0,15.3,9.3,10.5,3.2,9.0,12.0,10.0,22.0,12.5,41,44,32,35,35,12,14,12,18,17,30,30,0,280,5.8,10,10,20.6,22,20,18};
    double TDN[] = {46,52,58,50,47,53,55,49,53,59.7,55.1,53.9,72.1,50,67,40,42,45,45,56,50,50.5,49,49,45,85,80,85,80,85,71,77,60,65,65,65,61,70,62,60,80,80,0,0,80,70,45,45,70,70,70};
    double CA[] = {25,25,25,25,25,90,25,25,25,25,25,25,25,25,25,90,90,90,90,90,90,25,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,30,85,90,90,90,10,90,7,90,90,90};
    double P[] = {25,25,25,25,25,90,25,25,25,25,25,25,25,25,25,90,90,90,90,90,90,25,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,90,30,85,90,90,90,10,90,7,90,90,90};
    private static Context context;
    ArrayList<String> straws_selected = new ArrayList<>();
    ArrayList<String> grass_selected = new ArrayList<>();
    ArrayList<String> concmix_selected = new ArrayList<>();
    ArrayList<Double> grass_cp = new ArrayList<>();
    ArrayList<Double> straw_cp = new ArrayList<>();
    ArrayList<Double> concmix_cp = new ArrayList<>();
    ArrayList<Double> grass_tdn = new ArrayList<>();
    ArrayList<Double> straw_tdn = new ArrayList<>();
    ArrayList<Double> concmix_tdn = new ArrayList<>();
    ArrayList<Double> grass_dm = new ArrayList<>();
    ArrayList<Double> straw_dm = new ArrayList<>();
    ArrayList<Double> concmix_dm = new ArrayList<>();
    ArrayList<Double> grass_ca = new ArrayList<>();
    ArrayList<Double> straw_ca = new ArrayList<>();
    ArrayList<Double> concmix_ca = new ArrayList<>();
    ArrayList<Double> grass_p = new ArrayList<>();
    ArrayList<Double> straw_p = new ArrayList<>();
    ArrayList<Double> concmix_p = new ArrayList<>();
    ArrayList<Double> grass_indi = new ArrayList<>();
    ArrayList<Double> straw_indi = new ArrayList<>();
    ArrayList<Double> concmix_indi = new ArrayList<>();
    boolean straw_flag = false;
    boolean grass_flag = false;
    boolean concmix_flag = false;

    double grass_cp_avg = 0;
    double grass_tdn_avg = 0;
    double straw_cp_avg = 0;
    double straw_tdn_avg = 0;
    double concmix_cp_avg = 0;
    double concmix_tdn_avg = 0;
    double weight =0,dm=0,tdn=0,cp=0;

    String language = "";
    int file_name_index = 0;


    //NEW VARIABLES.
    ArrayList<Double> dm_list = new ArrayList<Double>();
    ArrayList<Double> cp_list = new ArrayList<Double>();
    ArrayList<Double> tdn_list = new ArrayList<Double>();
    String static_path = "";

    boolean azolla_flag = false; boolean bdgs_wet_flag = false; boolean bdgs_dry_flag = false; boolean bypassfat_flag = false,urea_flag=false;

    double azolla_dm=0; double azolla_cp=0; double azolla_tdn=0;double azolla_fresh=0,azolla_cost=0,azolla_ca=0,azolla_p=0;
    double azolla_weight = 0,cp_from_azolla=0,tdn_from_azolla=0,dm_from_azolla=0,ca_from_azolla=0,p_from_azolla=0;

    double bdgs_dry_dm=0; double bdgs_dry_cp=0; double bdgs_dry_tdn=0;double bdgs_dry_fresh=0,bdgs_dry_cost = 0;
    double bdgs_dry_weight = 0,cp_from_bdgs_dry=0,tdn_from_bdgs_dry=0,dm_from_bdgs_dry=0;

    double bdgs_wet_dm=0; double bdgs_wet_cp=0; double bdgs_wet_tdn=0;double bdgs_wet_fresh=0,bdgs_wet_cost=0,bdgs_wet_ca=0,bdgs_wet_p=0;
    double bdgs_wet_weight = 0,cp_from_bdgs_wet=0,tdn_from_bdgs_wet=0,dm_from_bdgs_wet=0,ca_from_bdgs_wet=0,p_from_bdgs_wet=0;

    double bypassfat_dm=0; double bypassfat_cp=0; double bypassfat_tdn=0;double bypassfat_fresh=0,bypassfat_cost=0,bypassfat_ca=0,bypassfat_p=0;
    double bypassfat_weight = 0,cp_from_bypassfat=0,tdn_from_bypassfat=0,dm_from_bypassfat=0,ca_from_bypassfat=0,p_from_bypassfat=0;

    double urea_dm=0; double urea_cp=0; double urea_tdn=0;double urea_fresh=0,urea_cost=0,urea_ca=0,urea_p=0;
    double urea_weight = 0,cp_from_urea=0,tdn_from_urea=0,dm_from_urea=0,ca_from_urea=0,p_from_urea=0;

    double roughage = 0;
    double concmix = 0;

    boolean file_found = false;

    //Very new Variables.
    ArrayList<Double> cereal_straws_dm_list = new ArrayList<Double>();
    ArrayList<Double> cereal_straws_cp_list = new ArrayList<Double>();
    ArrayList<Double> cereal_straws_tdn_list = new ArrayList<Double>();
    ArrayList<Double> cereal_straws_ca_list = new ArrayList<Double>();
    ArrayList<Double> cereal_straws_p_list = new ArrayList<Double>();

    ArrayList<Double> legume_straws_dm_list = new ArrayList<Double>();
    ArrayList<Double> legume_straws_cp_list = new ArrayList<Double>();
    ArrayList<Double> legume_straws_tdn_list = new ArrayList<Double>();
    ArrayList<Double> legume_straws_ca_list = new ArrayList<Double>();
    ArrayList<Double> legume_straws_p_list = new ArrayList<Double>();

    ArrayList<Double> native_and_improved_grasses_dm_list = new ArrayList<Double>();
    ArrayList<Double> native_and_improved_grasses_cp_list = new ArrayList<Double>();
    ArrayList<Double> native_and_improved_grasses_tdn_list = new ArrayList<Double>();
    ArrayList<Double> native_and_improved_grasses_ca_list = new ArrayList<Double>();
    ArrayList<Double> native_and_improved_grasses_p_list = new ArrayList<Double>();

    ArrayList<Double> legume_variety_dm_list = new ArrayList<Double>();
    ArrayList<Double> legume_variety_cp_list = new ArrayList<Double>();
    ArrayList<Double> legume_variety_tdn_list = new ArrayList<Double>();
    ArrayList<Double> legume_variety_ca_list = new ArrayList<Double>();
    ArrayList<Double> legume_variety_p_list = new ArrayList<Double>();

    ArrayList<String> cakes_list = new ArrayList<String>();
    ArrayList<Double> cakes_dm_list = new ArrayList<Double>();
    ArrayList<Double> cakes_cp_list = new ArrayList<Double>();
    ArrayList<Double> cakes_tdn_list = new ArrayList<Double>();
    ArrayList<Double> cakes_ca_list = new ArrayList<Double>();
    ArrayList<Double> cakes_p_list = new ArrayList<Double>();

    ArrayList<Double> byproducts_dm_list = new ArrayList<Double>();
    ArrayList<Double> byproducts_cp_list = new ArrayList<Double>();
    ArrayList<Double> byproducts_tdn_list = new ArrayList<Double>();
    ArrayList<Double> byproducts_ca_list = new ArrayList<Double>();
    ArrayList<Double> byproducts_p_list = new ArrayList<Double>();

    ArrayList<Double> miscellaneous_dm_list = new ArrayList<Double>();
    ArrayList<Double> miscellaneous_cp_list = new ArrayList<Double>();
    ArrayList<Double> miscellaneous_tdn_list = new ArrayList<Double>();
    ArrayList<Double> miscellaneous_ca_list = new ArrayList<Double>();
    ArrayList<Double> miscellaneous_p_list = new ArrayList<Double>();

    ArrayList<Double> cereal_grains_dm_list = new ArrayList<Double>();
    ArrayList<Double> cereal_grains_cp_list = new ArrayList<Double>();
    ArrayList<Double> cereal_grains_tdn_list = new ArrayList<Double>();
    ArrayList<Double> cereal_grains_ca_list = new ArrayList<Double>();
    ArrayList<Double> cereal_grains_p_list = new ArrayList<Double>();


    ArrayList<Double> concentrate_mixture_dm_list = new ArrayList<Double>();
    ArrayList<Double> concentrate_mixture_cp_list = new ArrayList<Double>();
    ArrayList<Double> concentrate_mixture_tdn_list = new ArrayList<Double>();
    ArrayList<Double> concentrate_mixture_ca_list = new ArrayList<Double>();
    ArrayList<Double> concentrate_mixture_p_list = new ArrayList<Double>();

    ArrayList<Double> silage_dm_list = new ArrayList<Double>();
    ArrayList<Double> silage_cp_list = new ArrayList<Double>();
    ArrayList<Double> silage_tdn_list = new ArrayList<Double>();
    ArrayList<Double> silage_ca_list = new ArrayList<Double>();
    ArrayList<Double> silage_p_list = new ArrayList<Double>();

    ArrayList<Double> hay_dm_list = new ArrayList<Double>();
    ArrayList<Double> hay_cp_list = new ArrayList<Double>();
    ArrayList<Double> hay_tdn_list = new ArrayList<Double>();
    ArrayList<Double> hay_ca_list = new ArrayList<Double>();
    ArrayList<Double> hay_p_list = new ArrayList<Double>();

    Map<String, FeedBean> strawMap = new HashMap<String, FeedBean>();
    Map<String, FeedBean> grassMap = new HashMap<String, FeedBean>();
    Map<String, FeedBean> concMixMap = new HashMap<String, FeedBean>();
    Map<String, FeedBean> specialFeedMap = new HashMap<String, FeedBean>();

    public void calculateIndex(int id){
        //////////////////////////Toast.makeText(getApplicationContext(), "Calculating index", //////////////////////////Toast.LENGTH_SHORT).show();
        CheckBox feeds = (CheckBox) findViewById(id);
    //    LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout1);
        int index = ((LinearLayout)feeds.getParent()).indexOfChild(feeds);
        //////////////////////////Toast.makeText(getApplicationContext(), "The index is "+index, //////////////////////////Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_mixture);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            DM_str = extras.getString("DM");
            CP_str = extras.getString("CP");
            TDN_str = extras.getString("TDN");
            CA_str = extras.getString("CA");
            P_str = extras.getString("P");
            milk_str = extras.getString("milk");
            weight_animal = extras.getString("body_weight");
            language = extras.getString("language");
            farmerName = extras.getString("FarmerName");
            farmerAddress = extras.getString("FarmerAddress");
            animalNumber = extras.getString("AnimalNumber");
            animalType = extras.getString("AnimalType");
            fat_content = extras.getString("fatContent");
        }

        if(language.equalsIgnoreCase("telugu")){
            Button calculate = (Button)findViewById(R.id.button3);
            calculate.setText("లెక్కించు >>>");
        }

        weight = Double.parseDouble(weight_animal);
        dm = Double.parseDouble(DM_str);
        tdn = Double.parseDouble(TDN_str);
        cp = Double.parseDouble(CP_str);

        /*//Toast.makeText(getApplicationContext(),"retreived fat content111 : "+fat_content,//Toast.LENGTH_LONG).show();

        getDirectory();

        if(!file_found)
        syncAssets();

        //Check for telugu.
        if(language.equalsIgnoreCase("telugu")){
            boolean flag = check_for_telugu();
            if(flag){
                syncAssets();
            }
        }
        else if(language.equalsIgnoreCase("english")){
            boolean flag = check_for_english();
            if(flag){
                syncAssets();
            }
        }


        getDirectory();
*/
        syncAssets();
        generate_feeds();

        /*LinearLayout l = (LinearLayout)findViewById(R.id.linearLayout1);
        int no_of_children = l.getChildCount();

        for(int i=0;i<no_of_children;i++){
            try {
                LinearLayout l1 = (LinearLayout) l.getChildAt(i);
                no_of_children += l1.getChildCount();
            }
            catch(Exception e){

            }
        }

        //////////////////////////Toast.makeText(getApplicationContext(),Integer.toString(no_of_children),//////////////////////////Toast.LENGTH_SHORT).show();

        if(no_of_children>67) {

        }
        else{
            ////////////////////////Toast.makeText(this,"Copying files",////////////////////////Toast.LENGTH_SHORT).show();
            LinearLayout s1 = (LinearLayout)findViewById(R.id.linearLayout1);
            s1.removeAllViews();
            syncAssets();
            generate_feeds();
        }*/
    }

    private void generate_feeds() {
        AssetManager assetManager = getAssets();
        String contents = readFromFile("cereal_straws");
        createlayout(contents,"cereal_straws","ఎండు గడ్డి రకాలు");

         contents = readFromFile("legume_straws");
        createlayout(contents,"legume_straws","ధాన్యపు జాతి ఎండు గడ్డి");
        ////////////////////////Toast.makeText(this,contents,////////////////////////Toast.LENGTH_LONG).show();

         contents = readFromFile("native_and_improved_grasses");
        createlayout(contents,"native_and_improved_grasses","పచ్చి గడ్డి రకాలు");
      ////////////////Toast.makeText(this,contents,////////////////Toast.LENGTH_LONG).show();


         contents = readFromFile("legume_variety");
        createlayout(contents,"legume_variety","పప్పు జాతి పచ్చి గడ్డి రకాలు");

         contents = readFromFile("cakes");
        createlayout(contents,"cakes","చెక్కలు");

         contents = readFromFile("byproducts");
        createlayout(contents,"byproducts","ఉప ఉత్పత్తులు");

         contents = readFromFile("miscellaneous");
        createlayout(contents,"miscellaneous","ఇతర మేత రకాలు");

         contents = readFromFile("cereal_grains");
        createlayout(contents,"cereal_grains","ధాన్యపు గింజలు");

         contents = readFromFile("concentrate_mixture");
        createlayout(contents,"concentrate_mixture","దాణ మిశ్రమము");

        contents = readFromFile("hay");
        createlayout(contents,"hay","కోసి యెండవేసినగడ్డి");

        contents = readFromFile("silage");
        createlayout(contents,"silage","పాతర గడ్డి రకాలు(సైలేజ్)");

       //////////////////////////Toast.makeText(this,contents,//////////////////////////Toast.LENGTH_LONG).show();
    }

    private void createlayout(String contents,String tag_layout,String telugu_tag_layout) {

        try {
            final String[] contents1 = contents.split("\n");
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
            layout.setTag(tag_layout);

            TextView t = new TextView(this);
            if(language.equalsIgnoreCase("telugu"))
            t.setText(telugu_tag_layout);
            else
            t.setText(tag_layout.replace("_", " "));
            t.setGravity(Gravity.LEFT);
            t.setPadding(0, 0, 0, 10);
            t.setTextColor(Color.BLUE);
            t.setTextSize(15);

            layout.addView(t);

            loop:for (int i = 0; i < contents1.length; i++) {
                final String feedCp = contents1[i].split(",")[2], feedDm = contents1[i].split(",")[1], feedTdn = contents1[i].split(",")[3];
                try{
                    CheckBox c = null;
                    final LinearLayout innerVerticalLayout = new LinearLayout(this);
                    innerVerticalLayout.setOrientation(LinearLayout.VERTICAL);
                    innerVerticalLayout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
                    innerVerticalLayout.setPadding(15, 0, 0, 0);

                  final LinearLayout layout1 = new LinearLayout(this);
                try {
                    layout1.setOrientation(LinearLayout.HORIZONTAL);
                    layout1.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
//                    layout1.setPadding(15, 0, 0, 0);

                    if(language.equalsIgnoreCase("telugu")) {
                        c = new CheckBox(this);
                        c.setTag(tag_layout + Integer.toString(i));

                        String name = contents1[i].split(",")[6];
                        if(name.equals("")||name.equals(" ")){
                            continue loop;
                        }
                        c.setText(contents1[i].split(",")[6]);
                        Log.d("CHeckbox", contents1[i].split(",")[6]+" : "+tag_layout + Integer.toString(i));
                        c.setPadding(15, 0, 0, 0);
                    }
                    else if(language.equalsIgnoreCase("english")){
                        c = new CheckBox(this);
                        c.setTag(tag_layout + Integer.toString(i));

                        String name = contents1[i].split(",")[0];
                        if(name.equals("")||name.equals(" ")){
                            continue loop;
                        }

                        c.setText(contents1[i].split(",")[0]);
                        Log.d("CHeckbox", contents1[i].split(",")[0]+" : "+tag_layout + Integer.toString(i));
                        c.setPadding(15, 0, 0, 0);
                    }

                    //Set onclick functionality for the feeds.
                    c.setMaxWidth((int) (getMaxWidth()*0.45));
                    c.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckBox checkBox = (CheckBox) v;
                            int id = checkBox.getId();
                            //  //////////////////////////Toast.makeText(getApplicationContext(), "Entered onclick function", //////////////////////////Toast.LENGTH_SHORT).show();
                            if (checkBox.isChecked()) {
                                //////////////////////////Toast.makeText(getApplicationContext(), "in mixture clicked on a checkbox", //////////////////////////Toast.LENGTH_SHORT).show();
                                LinearLayout layout = (LinearLayout) checkBox.getParent();
                                int index = layout.indexOfChild(checkBox);
                                //////////////////////////Toast.makeText(getApplicationContext(), "The index is " + index, //////////////////////////Toast.LENGTH_SHORT).show();

                                ////////////////////////Toast.makeText(getApplicationContext(),checkBox.getText(),////////////////////////Toast.LENGTH_LONG).show();
                                TextView tr = new TextView(getApplicationContext());
                                tr.setText("Cost:");
                                tr.setTextColor(Color.BLACK);
                                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                buttonLayoutParams.setMargins(40, 0, 0, 0);
                                tr.setLayoutParams(buttonLayoutParams);
                                tr.setVisibility(View.INVISIBLE);

                                EditText ed = new EditText(getApplicationContext());
                                ed.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT + 10));
                                ed.setTextColor(Color.BLACK);
                                ed.setText(null);
                             //   ed.setHint("Enter the cost of the feed");
                                ed.setHintTextColor(Color.parseColor("#FFAB1212"));
                              //  ed.setGravity(Gravity.CENTER_HORIZONTAL);
                                int type = InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL;
                                ed.setInputType(type);
                                if(language.equalsIgnoreCase("telugu"))
                                ed.setHint("ధర (రూ.)");
                                else if(language.equalsIgnoreCase("english")){
                                    ed.setHint("Enter Cost(Rs.)");
                                }
                                ed.setFocusable(true);
                                //ed.setText("0.75");

                                ed.setPadding(0, 10, 30,0);

                                ed.setGravity(Gravity.RIGHT);
                                ed.setTextSize(12);
                                //  ed.setEllipsize(TextUtils.TruncateAt.START);
                                layout1.addView(tr);
                                layout1.addView(ed);

                                TextView feedDetailsView = new TextView(getApplicationContext());
                                String feedDetailsText = getString(R.string.feed_dm)+" "+feedDm+"%, "+
                                        getString(R.string.feed_cp)+" "+feedCp+"%, "+
                                        getString(R.string.feed_tdn)+" "+feedTdn+"%";
                                feedDetailsView.setText(feedDetailsText);
                                feedDetailsView.setTag("feedDetailsView");
                                feedDetailsView.setTextColor(Color.parseColor("#996600"));
                                giveBorder(feedDetailsView);
                                innerVerticalLayout.addView(feedDetailsView);

                            } else {
                                try {
                                    LinearLayout layout = (LinearLayout) checkBox.getParent();
                                    LinearLayout parentVerticalLayout = (LinearLayout) layout.getParent();

                                    int index = layout.indexOfChild(checkBox);

                                    TextView tr = (TextView) layout.getChildAt(index + 1);

                                    layout.removeView(tr);

                                    EditText ed = (EditText) layout.getChildAt(index + 1);

                                    layout.removeView(ed);

                                    TextView textView = (TextView)parentVerticalLayout.findViewWithTag("feedDetailsView");
                                    parentVerticalLayout.removeView(textView);

                                } catch (Exception e) {
                                    System.out.println("Exception encountered");
                                }
                            }

                        }

                    });

                    c.setOnDragListener(new View.OnDragListener() {
                        @Override
                        public boolean onDrag(View view, DragEvent dragEvent) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    //Toast.makeText(getApplicationContext(),"Check Box Dragged",//Toast.LENGTH_LONG).show();
                                }
                            });
                            return false;
                        }
                    });

                }
                catch(Exception e){
                    ////////////////////////Toast.makeText(this,"Exception occurred beginning",////////////////////////Toast.LENGTH_LONG).show();
                }

                //UPDATE dm-list,cp-list,tdn-list.
                if (tag_layout.equalsIgnoreCase("cereal_straws")) {
                    cereal_straws_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    cereal_straws_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    cereal_straws_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    cereal_straws_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    cereal_straws_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));

                }
                if (tag_layout.equalsIgnoreCase("legume_straws")) {
                    legume_straws_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    legume_straws_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    legume_straws_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    legume_straws_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    legume_straws_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));

                }
                if (tag_layout.equalsIgnoreCase("native_and_improved_grasses")) {
                    native_and_improved_grasses_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    native_and_improved_grasses_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    native_and_improved_grasses_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    native_and_improved_grasses_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    native_and_improved_grasses_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                if (tag_layout.equalsIgnoreCase("legume_variety")) {
                    legume_variety_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    legume_variety_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    legume_variety_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    legume_variety_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    legume_variety_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                if (tag_layout.equalsIgnoreCase("cakes")) {
                    cakes_list.add(contents1[i].split(",")[0]);
                    cakes_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    cakes_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    cakes_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    cakes_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    cakes_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                if (tag_layout.equalsIgnoreCase("byproducts")) {
                    byproducts_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    byproducts_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    byproducts_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    byproducts_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    byproducts_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                if (tag_layout.equalsIgnoreCase("miscellaneous")) {
                    miscellaneous_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    miscellaneous_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    miscellaneous_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    miscellaneous_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    miscellaneous_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));

                }
                if (tag_layout.equalsIgnoreCase("cereal_grains")) {
                    cereal_grains_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    cereal_grains_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    cereal_grains_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                    cereal_grains_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                    cereal_grains_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                if (tag_layout.equalsIgnoreCase("concentrate_mixture")) {
                    concentrate_mixture_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                    concentrate_mixture_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                    concentrate_mixture_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                   concentrate_mixture_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                   concentrate_mixture_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                }
                    if (tag_layout.equalsIgnoreCase("silage")) {
                        ////////////////////////Toast.makeText(this,"Entered hare for silage",////////////////////////Toast.LENGTH_LONG).show();
                        silage_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                        silage_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                        silage_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                        silage_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                        silage_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                    }
                    if (tag_layout.equalsIgnoreCase("hay")) {
                   //     //////////////////Toast.makeText(this,"Entered here for hay",//////////////////Toast.LENGTH_LONG).show();
                        hay_dm_list.add(Double.parseDouble(contents1[i].split(",")[1]));
                        hay_cp_list.add(Double.parseDouble(contents1[i].split(",")[2]));
                       //////////////////Toast.makeText(getApplicationContext(),contents1[i].split(",")[2],//////////////////Toast.LENGTH_LONG).show();
                        hay_tdn_list.add(Double.parseDouble(contents1[i].split(",")[3]));
                        hay_ca_list.add(Double.parseDouble(contents1[i].split(",")[4]));
                        hay_p_list.add(Double.parseDouble(contents1[i].split(",")[5]));
                    }


                //   //////////////////////////Toast.makeText(this, contents1[i].split(",")[0], //////////////////////////Toast.LENGTH_LONG).show();
                layout1.addView(c);
                    innerVerticalLayout.addView(layout1);
                layout.addView(innerVerticalLayout);
            }
               catch(Exception e){
                   ////////////////////////Toast.makeText(this,"Exception in creating layout first catch",////////////////////////Toast.LENGTH_LONG).show();
                }
            }
         //   //////////////////////////Toast.makeText(this,"Adding the layout",//////////////////////////Toast.LENGTH_LONG).show();
            LinearLayout s = (LinearLayout) findViewById(R.id.linearLayout1);
        //    //////////////////////////Toast.makeText(this,"Got the scroll view",//////////////////////////Toast.LENGTH_LONG).show();
            s.addView(layout);
        //    //////////////////////////Toast.makeText(this,"Added the layout",//////////////////////////Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            ////////////////////////Toast.makeText(this,"Exception in creating layout",////////////////////////Toast.LENGTH_LONG).show();

        }
    }

    private String readFromFile(String FILENAME) {
        String ret = "";
        BufferedReader reader = null;
        try {
         //   reader = new BufferedReader(
           //         new InputStreamReader(getAssets().open(FILENAME+".txt")));

           File f= new File(Environment.getExternalStorageDirectory().getAbsolutePath(),FILENAME+".txt");
          //  File f= new File(FILENAME+".txt");
            reader = new BufferedReader(
                    new FileReader(f));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                ret+=mLine+"\n";
            }
        } catch (IOException e) {
            //////////////////////////Toast.makeText(this,"File not found o IO Exception",//////////////////////////Toast.LENGTH_LONG).show();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return ret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ration_formulator_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.previousReports:
                Intent i = new Intent(FeedMixture.this,Reports.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void calculate_for_telugu(View v){
        {
            boolean ration_balanced = true;
            roughage = 0;
            concmix = 0;
            azolla_flag = false;
            bdgs_wet_flag = false;
            bdgs_dry_flag = false;

            azolla_flag = false;  bdgs_wet_flag = false;  bdgs_dry_flag = false;  bypassfat_flag = false;
            urea_flag = false;

            azolla_dm=0;  azolla_cp=0;  azolla_tdn=0; azolla_fresh=0;azolla_cost=0;
            azolla_weight = 0;cp_from_azolla=0;tdn_from_azolla=0;dm_from_azolla=0;

            bdgs_dry_dm=0;  bdgs_dry_cp=0;  bdgs_dry_tdn=0; bdgs_dry_fresh=0;bdgs_dry_cost = 0;
            bdgs_dry_weight = 0;cp_from_bdgs_dry=0;tdn_from_bdgs_dry=0;dm_from_bdgs_dry=0;

            bdgs_wet_dm=0;  bdgs_wet_cp=0;  bdgs_wet_tdn=0; bdgs_wet_fresh=0;bdgs_wet_cost=0;
            bdgs_wet_weight = 0;cp_from_bdgs_wet=0;tdn_from_bdgs_wet=0;dm_from_bdgs_wet=0;

            bypassfat_dm=0;  bypassfat_cp=0;  bypassfat_tdn=0; bypassfat_fresh=0;bypassfat_cost=0;
            bypassfat_weight = 0;cp_from_bypassfat=0;tdn_from_bypassfat=0;dm_from_bypassfat=0;


            //Declaring the variables.
            boolean straw_flag = false;
            boolean grass_flag = false;
            boolean concmix_flag = false;

            double grass_cp_avg = 0;
            double grass_tdn_avg = 0;
            double grass_ca_avg = 0;
            double grass_p_avg = 0;
            double straw_cp_avg = 0;
            double straw_tdn_avg = 0;
            double straw_ca_avg = 0;
            double straw_p_avg = 0;
            double concmix_cp_avg = 0;
            double concmix_tdn_avg = 0;
            double concmix_ca_avg = 0;
            double concmix_p_avg = 0;

            straws_selected = new ArrayList<>();
            grass_selected = new ArrayList<>();
            concmix_selected = new ArrayList<>();
            grass_cp = new ArrayList<>();
            straw_cp = new ArrayList<>();
            concmix_cp = new ArrayList<>();
            grass_tdn = new ArrayList<>();
            straw_tdn = new ArrayList<>();
            concmix_tdn = new ArrayList<>();
            grass_dm = new ArrayList<>();
            straw_dm = new ArrayList<>();
            concmix_dm = new ArrayList<>();
            grass_ca = new ArrayList<>();
            straw_ca = new ArrayList<>();
            concmix_ca = new ArrayList<>();
            grass_p = new ArrayList<>();
            straw_p = new ArrayList<>();
            concmix_p = new ArrayList<>();

            grass_indi = new ArrayList<>();
            straw_indi = new ArrayList<>();
            concmix_indi = new ArrayList<>();

            //cost
            ArrayList<Double> grass_cost = new ArrayList<Double>();
            ArrayList<Double> straw_cost = new ArrayList<Double>();
            ArrayList<Double> concmix_cost = new ArrayList<Double>();
            double total_cost = 0;

            //Variables created for the sake of edit option. This code written using these Maps are far better
//        than the one wrote without them with just concrete mathematics.
            this.strawMap = new HashMap<String, FeedBean>();
            this.grassMap = new HashMap<String, FeedBean>();
            this.concMixMap = new HashMap<String, FeedBean>();
            this.specialFeedMap = new HashMap<String, FeedBean>(); //These store the feeds that are selected by the user.

//        To store the feed_properties like dmFromStraw, cpFromStraw etc.
            Map<String, Double> feedTypeEnergyMap = new HashMap<>();

            LinearLayout layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cereal_straws");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            int count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){
                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cereal_straws"+i);
                Log.d("Count : ",Integer.toString(count));
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    straw_flag = true;
                    straws_selected.add(c.getText().toString());
                    straw_cp.add(cereal_straws_cp_list.get(count));
                    straw_tdn.add(cereal_straws_tdn_list.get(count));
                    straw_dm.add(cereal_straws_dm_list.get(count));
                    straw_ca.add(cereal_straws_ca_list.get(count));
                    straw_p.add(cereal_straws_p_list.get(count));

                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);


                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);
                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    straw_cost.add(cost_indi);

                    strawMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cereal_straws_dm_list.get(count))
                            .cpAndTdn(cereal_straws_cp_list.get(count), cereal_straws_tdn_list.get(count))
                            .caAndPh(cereal_straws_ca_list.get(count), cereal_straws_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("legume_straws");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){
                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("legume_straws"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    straw_flag = true;
                    straws_selected.add(c.getText().toString());
                    straw_cp.add(legume_straws_cp_list.get(count));
                    straw_tdn.add(legume_straws_tdn_list.get(count));
                    straw_dm.add(legume_straws_dm_list.get(count));
                    straw_ca.add(legume_straws_ca_list.get(count));
                    straw_p.add(legume_straws_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);
                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    straw_cost.add(cost_indi);

                    strawMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(legume_straws_dm_list.get(count))
                            .cpAndTdn(legume_straws_cp_list.get(count), legume_straws_tdn_list.get(count))
                            .caAndPh(legume_straws_ca_list.get(count), legume_straws_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("native_and_improved_grasses");
            //  ////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){
                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("native_and_improved_grasses"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    ////////////////////////Toast.makeText(this,c.getText(),////////////////////////Toast.LENGTH_LONG).show();

                    grass_flag = true;
                    grass_selected.add(c.getText().toString());
                    grass_cp.add(native_and_improved_grasses_cp_list.get(count));
                    grass_tdn.add(native_and_improved_grasses_tdn_list.get(count));
                    grass_dm.add(native_and_improved_grasses_dm_list.get(count));
                    grass_ca.add(native_and_improved_grasses_ca_list.get(count));
                    grass_p.add(native_and_improved_grasses_p_list.get(count));

                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    grass_cost.add(cost_indi);
                    grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(native_and_improved_grasses_dm_list.get(count))
                            .cpAndTdn(native_and_improved_grasses_cp_list.get(count), native_and_improved_grasses_tdn_list.get(count))
                            .caAndPh(native_and_improved_grasses_ca_list.get(count), native_and_improved_grasses_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }


            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("legume_variety");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("legume_variety"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    grass_flag = true;
                    grass_selected.add(c.getText().toString());
                    grass_cp.add(legume_variety_cp_list.get(count));
                    grass_tdn.add(legume_variety_tdn_list.get(count));
                    grass_dm.add(legume_variety_dm_list.get(count));
                    grass_ca.add(legume_variety_ca_list.get(count));
                    grass_p.add(legume_variety_p_list.get(count));

                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.

                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    grass_cost.add(cost_indi);
                    grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(legume_variety_dm_list.get(count))
                            .cpAndTdn(legume_variety_cp_list.get(count), legume_variety_tdn_list.get(count))
                            .caAndPh(legume_variety_ca_list.get(count), legume_variety_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cakes");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cakes"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    concmix_flag = true;
                    concmix_selected.add(c.getText().toString());
                    concmix_cp.add(cakes_cp_list.get(count));
                    concmix_tdn.add(cakes_tdn_list.get(count));
                    concmix_dm.add(cakes_dm_list.get(count));
                    concmix_ca.add(cakes_ca_list.get(count));
                    concmix_p.add(cakes_p_list.get(count));

                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    concmix_cost.add(cost_indi);
                    concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cakes_dm_list.get(count))
                            .cpAndTdn(cakes_cp_list.get(count), cakes_tdn_list.get(count))
                            .caAndPh(cakes_ca_list.get(count), cakes_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("byproducts");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("byproducts"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;

                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    String text = c.getText().toString().toLowerCase();
                    if(text.contains("తడి బీరు పొట్టు")){
                        bdgs_wet_flag = true;

                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText) layout1.getChildAt(index + 2);
                        try {
                            bdgs_wet_cost = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            bdgs_wet_cost = 0;
                        }

                        bdgs_wet_dm = byproducts_dm_list.get(count);
                        bdgs_wet_cp = byproducts_cp_list.get(count);
                        bdgs_wet_tdn = byproducts_tdn_list.get(count);
                        bdgs_wet_ca = byproducts_ca_list.get(count);
                        bdgs_wet_p = byproducts_p_list.get(count);

                        specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(byproducts_dm_list.get(count))
                                .cpAndTdn(byproducts_cp_list.get(count), byproducts_tdn_list.get(count))
                                .caAndPh(byproducts_ca_list.get(count), byproducts_p_list.get(count))
                                .cost(bdgs_wet_cost)
                                .build());
                    }

                    else {
                        concmix_flag = true;
                        concmix_selected.add(c.getText().toString());
                        concmix_cp.add(byproducts_cp_list.get(count));
                        concmix_tdn.add(byproducts_tdn_list.get(count));
                        concmix_dm.add(byproducts_dm_list.get(count));
                        concmix_ca.add(byproducts_ca_list.get(count));
                        concmix_p.add(byproducts_p_list.get(count));


                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText)layout1.getChildAt(index+2);

                        double cost_indi = 0;
                        try{
                            cost_indi = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            cost_indi = 0;
                        }
                        concmix_cost.add(cost_indi);
                        concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(byproducts_dm_list.get(count))
                                .cpAndTdn(byproducts_cp_list.get(count), byproducts_tdn_list.get(count))
                                .caAndPh(byproducts_ca_list.get(count), byproducts_p_list.get(count))
                                .cost(cost_indi)
                                .build());
                    }
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("miscellaneous");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop: for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("miscellaneous"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c==null){
                    continue loop;
                }
                if(c!=null&&c.isChecked()) {
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();
                    String text = c.getText().toString().toLowerCase();
                    if (text.equalsIgnoreCase("అజోల్ల")) {
                        azolla_flag = true;

                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText) layout1.getChildAt(index + 2);
                        try {
                            azolla_cost = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            azolla_cost = 0;
                        }

                        azolla_dm = miscellaneous_dm_list.get(count);
                        azolla_cp = miscellaneous_cp_list.get(count);
                        azolla_tdn = miscellaneous_tdn_list.get(count);
                        azolla_ca = miscellaneous_ca_list.get(count);
                        azolla_p = miscellaneous_p_list.get(count);

                        specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                                .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                                .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                                .cost(azolla_cost)
                                .build());
                    }
                    else if (text.equalsIgnoreCase("బైపాస్ ఫాట్")) {
                        bypassfat_flag = true;

                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText) layout1.getChildAt(index + 2);
                        try {
                            bypassfat_cost = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            bypassfat_cost  = 0;
                        }

                        bypassfat_dm = miscellaneous_dm_list.get(count);
                        bypassfat_cp = miscellaneous_cp_list.get(count);
                        bypassfat_tdn = miscellaneous_tdn_list.get(count);
                        bypassfat_ca = miscellaneous_ca_list.get(count);
                        bypassfat_p = miscellaneous_p_list.get(count);

                        specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                                .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                                .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                                .cost(bypassfat_cost)
                                .build());
                    }
                    else if (text.equalsIgnoreCase("యూరియా")) {
                        urea_flag = true;

                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText) layout1.getChildAt(index + 2);
                        try {
                            urea_cost = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            urea_cost = 0;
                        }

                        urea_dm = miscellaneous_dm_list.get(count);
                        urea_cp = miscellaneous_cp_list.get(count);
                        urea_tdn = miscellaneous_tdn_list.get(count);
                        urea_ca = miscellaneous_ca_list.get(count);
                        urea_p = miscellaneous_p_list.get(count);

                        specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                                .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                                .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                                .cost(urea_cost)
                                .build());
                    }
                    else if(text.equalsIgnoreCase("Mineral Mixture(Type 1)")){

                    }
                    else {
                        concmix_flag = true;
                        concmix_selected.add(c.getText().toString());
                        concmix_cp.add(miscellaneous_cp_list.get(count));
                        concmix_tdn.add(miscellaneous_tdn_list.get(count));
                        concmix_dm.add(miscellaneous_dm_list.get(count));
                        concmix_ca.add(miscellaneous_ca_list.get(count));
                        concmix_p.add(miscellaneous_p_list.get(count));


                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText)layout1.getChildAt(index+2);

                        double cost_indi = 0;
                        try{
                            cost_indi = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            cost_indi = 0;
                        }
                        concmix_cost.add(cost_indi);
                        concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                                .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                                .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                                .cost(cost_indi)
                                .build());
                    }

                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cereal_grains");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cereal_grains"+i);

                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    concmix_flag = true;
                    concmix_selected.add(c.getText().toString());
                    concmix_cp.add(cereal_grains_cp_list.get(count));
                    concmix_tdn.add(cereal_grains_tdn_list.get(count));
                    concmix_dm.add(cereal_grains_dm_list.get(count));
                    concmix_ca.add(cereal_grains_ca_list.get(count));
                    concmix_p.add(cereal_grains_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    concmix_cost.add(cost_indi);
                    concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cereal_grains_dm_list.get(count))
                            .cpAndTdn(cereal_grains_cp_list.get(count), cereal_grains_tdn_list.get(count))
                            .caAndPh(cereal_grains_ca_list.get(count), cereal_grains_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("concentrate_mixture");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            loop:for(int i=0;i<Integer.MAX_VALUE;i++){

                CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("concentrate_mixture"+i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if(c!=null&&c.isChecked()){
                    //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                    concmix_flag = true;
                    concmix_selected.add(c.getText().toString());
                    concmix_cp.add(concentrate_mixture_cp_list.get(count));
                    concmix_tdn.add(concentrate_mixture_tdn_list.get(count));
                    concmix_dm.add(concentrate_mixture_dm_list.get(count));
                    concmix_ca.add(concentrate_mixture_ca_list.get(count));
                    concmix_p.add(concentrate_mixture_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout)c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    concmix_cost.add(cost_indi);
                    concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(concentrate_mixture_dm_list.get(count))
                            .cpAndTdn(concentrate_mixture_cp_list.get(count), concentrate_mixture_tdn_list.get(count))
                            .caAndPh(concentrate_mixture_ca_list.get(count), concentrate_mixture_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

            try {
                layout = (LinearLayout) findViewById(R.id.rel1).findViewWithTag("hay");
                //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
                count = -1;
                loop:for (int i = 0; i < layout.getChildCount() - 1; i++) {

                    CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("hay"+i);
                    if(count==layout.getChildCount()-1)
                        break;
                    if(c!=null)
                        count++;
                    if(c!=null&&c.isChecked()) {
                        ////////////////////////Toast.makeText(this, c.getText(), ////////////////////////Toast.LENGTH_LONG).show();

                        try {
                            grass_flag = true;
                            grass_selected.add(c.getText().toString());
                            grass_cp.add(hay_cp_list.get(count));
                            grass_tdn.add(hay_tdn_list.get(count));
                            grass_dm.add(hay_dm_list.get(count));
                            grass_ca.add(hay_ca_list.get(count));
                            grass_p.add(hay_p_list.get(count));
                        }
                        catch (Exception e){

                            //////////////////Toast.makeText(getApplicationContext(),"Error1",//////////////////Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            return;
                        }

                        LinearLayout layout1 = null;
                        int index  =0;
                        try {

                            layout1 = (LinearLayout) c.getParent();
                            index = layout1.indexOfChild(c);

                        }
                        catch(Exception e){
                            //////////////////Toast.makeText(getApplicationContext(),"Error2",//////////////////Toast.LENGTH_LONG).show();
                            return;
                        }
                        EditText ed = (EditText)layout1.getChildAt(index+2);

                        double cost_indi = 0;
                        try{
                            cost_indi = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            cost_indi = 0;
                        }
                        grass_cost.add(cost_indi);
                        grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(hay_dm_list.get(count))
                                .cpAndTdn(hay_cp_list.get(count), hay_tdn_list.get(count))
                                .caAndPh(hay_ca_list.get(count), hay_p_list.get(count))
                                .cost(cost_indi)
                                .build());
                    }
                    if(count==layout.getChildCount()-2)
                        break;
                }

            }catch(Exception e){
                //////////////////Toast.makeText(getApplicationContext(),"Exception in considering hay",//////////////////Toast.LENGTH_LONG).show();
                return;
            }

            try {
                layout = (LinearLayout) findViewById(R.id.rel1).findViewWithTag("silage");
                //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
                count = -1;
                loop:for (int i = 0; i < layout.getChildCount() - 1; i++) {

                    CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("silage"+i);
                    if(count==layout.getChildCount()-1)
                        break;
                    if(c!=null)
                        count++;
                    if(c!=null&&c.isChecked()) {
                        ////////////////////////Toast.makeText(this, c.getText(), ////////////////////////Toast.LENGTH_LONG).show();

                        grass_flag = true;
                        grass_selected.add(c.getText().toString());
                        grass_cp.add(silage_cp_list.get(count));
                        grass_tdn.add(silage_tdn_list.get(count));
                        grass_dm.add(silage_dm_list.get(count));
                        grass_ca.add(silage_ca_list.get(count));
                        grass_p.add(silage_p_list.get(count));


                        LinearLayout layout1 = (LinearLayout) c.getParent();
                        int index = layout1.indexOfChild(c);

                        //Get the Edittext.
                        EditText ed = (EditText)layout1.getChildAt(index+2);

                        double cost_indi = 0;
                        try{
                            cost_indi = Double.parseDouble(ed.getText().toString());
                        }
                        catch(Exception e){
                            cost_indi = 0;
                        }
                        grass_cost.add(cost_indi);
                        grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(silage_dm_list.get(count))
                                .cpAndTdn(silage_cp_list.get(count), silage_tdn_list.get(count))
                                .caAndPh(silage_ca_list.get(count), silage_p_list.get(count))
                                .cost(cost_indi)
                                .build());
                    }
                    if(count==layout.getChildCount()-2)
                        break;
                }
            }
            catch(Exception e){
                ////////////////////////Toast.makeText(getApplicationContext(),"Exception in considering silage",////////////////////////Toast.LENGTH_LONG).show();
                return;
            }

            //Now do all the calculations.
            double grass_cp_total=0,grass_tdn_total=0,straw_cp_total=0,straw_tdn_total=0,concmix_cp_total=0,concmix_tdn_total=0,grass_ca_total=0,grass_p_total=0,straw_ca_total=0,straw_p_total=0,concmix_ca_total=0,concmix_p_total=0;
            for(int i=0;i<grass_cp.size();i++){
                grass_cp_avg = (double)grass_cp_avg+grass_cp.get(i);
                grass_tdn_avg =  (double)grass_tdn_avg+grass_tdn.get(i);
            }

            for(int i=0;i<straw_cp.size();i++){
                straw_cp_avg = (double)straw_cp_avg+straw_cp.get(i);
                straw_tdn_avg =  (double)straw_tdn_avg+straw_tdn.get(i);
            }

            for(int i=0;i<concmix_cp.size();i++){
                concmix_cp_avg = (double)concmix_cp_avg+concmix_cp.get(i);
                concmix_tdn_avg =  (double)concmix_tdn_avg+concmix_tdn.get(i);
            }

            grass_cp_total = grass_cp_avg;
            straw_cp_total = straw_cp_avg;
            concmix_cp_total = concmix_cp_avg;

            grass_tdn_total = grass_tdn_avg;
            straw_tdn_total = straw_tdn_avg;
            concmix_tdn_total = concmix_tdn_avg;

            grass_cp_avg = (double) grass_cp_avg/grass_cp.size();
            straw_cp_avg = (double) straw_cp_avg/straw_cp.size();
            concmix_cp_avg = (double) concmix_cp_avg/concmix_cp.size();

            grass_tdn_avg = (double) grass_tdn_avg/grass_tdn.size();
            straw_tdn_avg = (double) straw_tdn_avg/straw_tdn.size();
            concmix_tdn_avg = (double) concmix_tdn_avg/concmix_tdn.size();

            //Initialize variables.
            double dm_from_straw =0, cp_from_straw = 0, tdn_from_straw = 0;
            double dm_from_grass =0, cp_from_grass = 0, tdn_from_grass = 0;
            double dm_from_concmix =0, cp_from_concmix = 0, tdn_from_concmix = 0;

            double dmDup = Double.parseDouble(DM_str);
            double cpDup = Double.parseDouble(CP_str);
            double tdnDup = Double.parseDouble(TDN_str);

            if(bdgs_wet_flag && tdnDup > 0){
            /*if(this.concMixMap.size() > 1) {
                tdn_from_bdgs_wet = tdn_from_concmix*0.65;
                bdgs_wet_weight = tdn_from_bdgs_wet/bdgs_wet_tdn*100;
                if(bdgs_wet_weight<0)
                    bdgs_wet_weight = 0;
                bdgs_wet_fresh = (double) bdgs_wet_weight / bdgs_wet_dm * 100;

                cp_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_cp / 100;
                ca_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_ca*10;
                p_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_p*10;
            }
            else {
            */    bdgs_wet_weight = (double) Double.parseDouble(DM_str) * 0.2;
                bdgs_wet_fresh = (double) bdgs_wet_weight / bdgs_wet_dm * 100;

                cp_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_cp / 100;
                tdn_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_tdn / 100;
                ca_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_ca*10;
                p_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_p*10;
//            }

                updateMap(specialFeedMap, FeedNameConstants.ddgsWetTelugu, bdgs_wet_fresh);
                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;
            }

            if(straw_flag && tdnDup > 0){
                dm_from_straw = (double) weight*0.014;
                cp_from_straw = (double) dm_from_straw*straw_cp_avg/100;
                tdn_from_straw = (double) dm_from_straw*straw_tdn_avg/100;

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                Log.d("Calculations" , "CP Straw : "+cp_from_straw);
                Log.d("Calculations" , "TDN Straw : "+tdn_from_straw);
            }

            if(grass_flag && tdnDup > 0){
                dm_from_grass = (double) weight*0.0075;
                cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
                tdn_from_grass = (double) dm_from_grass*grass_tdn_avg/100;

                if(tdn_from_grass > tdnDup){
                    tdn_from_grass = tdnDup;
                    dm_from_grass = (double) tdn_from_grass*100/grass_tdn_avg;
                    cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
                }

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                Log.d("Calculations" , "CP grass : "+cp_from_grass);
                Log.d("Calculations" , "TDN grass : "+tdn_from_grass);
            }

            if(concmix_flag && tdnDup > 0){
                if(tdnDup > 0){
                    tdn_from_concmix = tdnDup;
                    dm_from_concmix = (double) tdn_from_concmix/concmix_tdn_avg*100;
                    cp_from_concmix = (double) dm_from_concmix*concmix_cp_avg/100;

                    tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                    cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                    dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                    Log.d("Calculations" , "CP Concmix : "+cp_from_concmix);
                    Log.d("Calculations" , "TDN Concmix : "+tdn_from_concmix);
                }
                else{
                    Log.d("Calculations" , "TDN already met : "+tdnDup);
                }
            }

            Log.d("Calculations" , "CP and TDN not within the range");
            Log.d("Calculations" , "CP remaining : "+cpDup);
            Log.d("Calculations" , "TDN Remaining : "+tdnDup);
            if(Math.abs(cpDup) > 50 || (tdnDup > 300)){

            }


            if(!(straw_flag)){
                cp_from_straw=0;
                tdn_from_straw=0;
            }

            if(!(grass_flag)){
                cp_from_grass=0;
                tdn_from_grass=0;
            }

            if(!(straw_flag) && (tdnDup > 0)){
                dm_from_grass=weight*0.014+weight*0.0075;
                tdn_from_grass = (double)dm_from_grass*grass_tdn_avg/100;
                cp_from_grass = (double)dm_from_grass*grass_cp_avg/100;

                if(tdn_from_grass > Double.parseDouble(TDN_str)-bdgs_wet_tdn){
                    tdn_from_grass = Double.parseDouble(TDN_str)-bdgs_wet_tdn;
                    dm_from_grass = (double) tdn_from_grass*100/grass_tdn_avg;
                    cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
                }

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                tdn_from_straw = 0;
                cp_from_straw = 0;
                dm_from_straw = 0;
            }

            if((!(concmix_flag))){
                if(!concmix_flag) {
                    dm_from_concmix = 0;
                    cp_from_concmix = 0;
                }
                else if(tdn_from_concmix < 0){
                    cp_from_concmix = (double) cp-cp_from_grass-cp_from_straw;

                    if(cp_from_concmix > 0) {
                        dm_from_concmix = (double) cp_from_concmix / concmix_cp_avg * 100;
                        tdn_from_concmix = (double) dm_from_concmix * concmix_tdn_avg / 100;
                    }
                    else{
                        dm_from_concmix = 0;
                        cp_from_concmix = 0;
                    }
                }
            }


            if((!(concmix_flag)) && (tdnDup > 0)){
                tdn_from_grass+=(double)tdnDup;
                dm_from_grass = (double)tdn_from_grass/grass_tdn_avg*100;
                cp_from_grass = (double)dm_from_grass*grass_cp_avg/100;

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                dm_from_concmix=0;
                cp_from_concmix=0;
                tdn_from_concmix=0;
            }

            if(!(grass_flag) && tdnDup > 0){
                tdn_from_concmix = tdnDup;
                dm_from_concmix = tdn_from_concmix/(concmix_tdn_avg/100);
                cp_from_concmix = dm_from_concmix*concmix_cp_avg/100;

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                dm_from_grass=0;
                cp_from_grass=0;
                tdn_from_grass=0;
            }


            if(azolla_flag){
                azolla_weight = (double)Double.parseDouble(DM_str)*0.01;
                azolla_fresh = (double)azolla_weight/azolla_dm*100;
                cp_from_azolla = azolla_weight*azolla_cp/100;
                tdn_from_azolla = azolla_weight*azolla_tdn/100;
                ca_from_azolla = azolla_weight*azolla_ca*10;
                p_from_azolla = azolla_weight*azolla_p*10;

                updateMap(specialFeedMap, FeedNameConstants.azollaTelugu, azolla_fresh);
            }

            if(bdgs_dry_flag){
                bdgs_dry_weight = (double)Double.parseDouble(DM_str)*0.2;
                bdgs_dry_fresh = (double)bdgs_wet_weight/bdgs_wet_dm*100;

                cp_from_bdgs_dry = bdgs_dry_weight*bdgs_dry_cp/100;
                tdn_from_bdgs_dry = bdgs_dry_weight*bdgs_dry_tdn/100;
            }

            if(bypassfat_flag){
                bypassfat_weight = (double)Double.parseDouble(DM_str)*2.5/100;
                bypassfat_fresh = (double)bypassfat_weight/bypassfat_dm*100;

                cp_from_bypassfat = bypassfat_weight*bypassfat_cp/100;
                tdn_from_bypassfat = bypassfat_weight*bypassfat_tdn/100;
                ca_from_bypassfat = bypassfat_weight*bypassfat_ca*10;
                p_from_bypassfat = bypassfat_weight*bypassfat_p*10;

                updateMap(specialFeedMap, FeedNameConstants.bypassFatTelugu, bypassfat_fresh);
            }

            if(urea_flag){
                urea_weight = (double)Double.parseDouble(DM_str)*0.01;
                urea_weight = (urea_weight > 0.1)? 0.1 : urea_weight;
                urea_fresh = (double)urea_weight/urea_dm*100;


                cp_from_urea = urea_weight*urea_cp/100;
                tdn_from_urea = urea_weight*urea_tdn/100;
                ca_from_urea = urea_weight*urea_ca*10;
                p_from_urea = urea_weight*urea_p*10;

                updateMap(specialFeedMap, FeedNameConstants.ureaTelugu, urea_fresh);
            }

            if(concmix_flag && tdnDup > 0){
                tdn_from_concmix = tdnDup;
                dm_from_concmix = (double) tdn_from_concmix/concmix_tdn_avg*100;
                cp_from_concmix = (double) dm_from_concmix*concmix_cp_avg/100;
            }

            double total_cp = cp_from_concmix+cp_from_grass+cp_from_straw+cp_from_azolla+cp_from_bdgs_dry+cp_from_bdgs_wet+cp_from_bypassfat;
            double decision = total_cp-cp;
            double total_dm = 0;
            boolean decision_flag = false;
            double concmix_add_dm = 0;
            double concmix_add_cp = 0;
            double concmix_add_tdn = 0;

            //Displaying results.
            double final_dm = dm_from_concmix+dm_from_grass+dm_from_straw+azolla_weight+bdgs_dry_weight+bdgs_wet_weight+bypassfat_weight;
            double final_cp = cp_from_concmix+cp_from_grass+cp_from_straw+cp_from_azolla+cp_from_bdgs_wet+cp_from_bdgs_dry+cp_from_bypassfat;
            double final_tdn = tdn_from_concmix+tdn_from_grass+tdn_from_straw;

            final_dm = (double)Math.round(final_dm*100)/100;
            final_cp = (double)Math.round(final_cp*100)/100;
            final_tdn = (double)Math.round(final_tdn*100)/100;

            feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromStraw, dm_from_straw);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromGrass, dm_from_grass);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromConcmix, dm_from_concmix);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromStraw, cp_from_straw);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromGrass, cp_from_grass);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromConcmix, cp_from_concmix);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromStraw, tdn_from_straw);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromGrass, tdn_from_grass);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromConcmix, tdn_from_concmix);

            feedTypeEnergyMap.put(FeedTypePropertyConstants.strawTdnAvg, straw_tdn_avg);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.grassTdnAvg, grass_tdn_avg);
            feedTypeEnergyMap.put(FeedTypePropertyConstants.concmixTdnAvg, concmix_tdn_avg);

            calculateFeedFreshWeight(strawMap, dm_from_straw, straw_tdn_total);
            calculateFeedFreshWeight(grassMap, dm_from_grass, grass_tdn_total);
            calculateFeedFreshWeight(concMixMap, dm_from_concmix, concmix_tdn_total);
            layout = (LinearLayout)findViewById(R.id.dynamic_layout);

            prepareFinalView(layout, strawMap, grassMap, concMixMap, specialFeedMap);

            LinearLayout layout_submit = (LinearLayout)findViewById(R.id.linearLayout);
            layout_submit.setVisibility(View.GONE);
            ScrollView scroll_feeds = (ScrollView)findViewById(R.id.scroll_feeds);
            scroll_feeds.setVisibility(View.GONE);
            ScrollView new_feed = (ScrollView)findViewById(R.id.add_new_feed);
            new_feed.setVisibility(View.GONE);
            LinearLayout dynamic_layout = (LinearLayout)findViewById(R.id.dynamic_layout);
            dynamic_layout.setVisibility(View.VISIBLE);
        }
    }

    public void calculate_new(View v){

        //Toast.makeText(getApplicationContext(), "in calling method", //Toast.LENGTH_LONG).show();
        final_output="";
        if(language.equalsIgnoreCase("telugu")){
            calculate_for_telugu(v);
            return;
        }

        //Toast.makeText(getApplicationContext(), "in calling method2", //Toast.LENGTH_LONG).show();

        boolean ration_balanced = true;
        roughage = 0;
        concmix = 0;
        azolla_flag = false;
        bdgs_wet_flag = false;
        bdgs_dry_flag = false;

         azolla_flag = false;  bdgs_wet_flag = false;  bdgs_dry_flag = false;  bypassfat_flag = false;
        urea_flag = false;

         azolla_dm=0;  azolla_cp=0;  azolla_tdn=0; azolla_fresh=0;azolla_cost=0;
         azolla_weight = 0;cp_from_azolla=0;tdn_from_azolla=0;dm_from_azolla=0;

         bdgs_dry_dm=0;  bdgs_dry_cp=0;  bdgs_dry_tdn=0; bdgs_dry_fresh=0;bdgs_dry_cost = 0;
         bdgs_dry_weight = 0;cp_from_bdgs_dry=0;tdn_from_bdgs_dry=0;dm_from_bdgs_dry=0;

         bdgs_wet_dm=0;  bdgs_wet_cp=0;  bdgs_wet_tdn=0; bdgs_wet_fresh=0;bdgs_wet_cost=0;
         bdgs_wet_weight = 0;cp_from_bdgs_wet=0;tdn_from_bdgs_wet=0;dm_from_bdgs_wet=0;

         bypassfat_dm=0;  bypassfat_cp=0;  bypassfat_tdn=0; bypassfat_fresh=0;bypassfat_cost=0;
         bypassfat_weight = 0;cp_from_bypassfat=0;tdn_from_bypassfat=0;dm_from_bypassfat=0;


        //Declaring the variables.
        boolean straw_flag = false;
        boolean grass_flag = false;
        boolean concmix_flag = false;

        double grass_cp_avg = 0;
        double grass_tdn_avg = 0;
        double grass_ca_avg = 0;
        double grass_p_avg = 0;
        double straw_cp_avg = 0;
        double straw_tdn_avg = 0;
        double straw_ca_avg = 0;
        double straw_p_avg = 0;
        double concmix_cp_avg = 0;
        double concmix_tdn_avg = 0;
        double concmix_ca_avg = 0;
        double concmix_p_avg = 0;

        straws_selected = new ArrayList<>();
        grass_selected = new ArrayList<>();
        concmix_selected = new ArrayList<>();
        grass_cp = new ArrayList<>();
        straw_cp = new ArrayList<>();
        concmix_cp = new ArrayList<>();
        grass_tdn = new ArrayList<>();
        straw_tdn = new ArrayList<>();
        concmix_tdn = new ArrayList<>();
        grass_dm = new ArrayList<>();
        straw_dm = new ArrayList<>();
        concmix_dm = new ArrayList<>();
        grass_ca = new ArrayList<>();
        straw_ca = new ArrayList<>();
        concmix_ca = new ArrayList<>();
        grass_p = new ArrayList<>();
        straw_p = new ArrayList<>();
        concmix_p = new ArrayList<>();

        grass_indi = new ArrayList<>();
        straw_indi = new ArrayList<>();
        concmix_indi = new ArrayList<>();

        //cost
        ArrayList<Double> grass_cost = new ArrayList<Double>();
        ArrayList<Double> straw_cost = new ArrayList<Double>();
        ArrayList<Double> concmix_cost = new ArrayList<Double>();
        double total_cost = 0;

        //Variables created for the sake of edit option. This code written using these Maps are far better
//        than the one wrote without them with just concrete mathematics.
        this.strawMap = new HashMap<String, FeedBean>();
        this.grassMap = new HashMap<String, FeedBean>();
        this.concMixMap = new HashMap<String, FeedBean>();
        this.specialFeedMap = new HashMap<String, FeedBean>(); //These store the feeds that are selected by the user.

//        To store the feed_properties like dmFromStraw, cpFromStraw etc.
        Map<String, Double> feedTypeEnergyMap = new HashMap<>();

        LinearLayout layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cereal_straws");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        int count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cereal_straws"+i);
            Log.d("Checkbox Checking : ","cereal_straws"+i);
            Log.d("Count : ",Integer.toString(count));
            Log.d("Max count ",Integer.toString(layout.getChildCount()));
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();
                Log.d("Entered here","Chck box selected");
                straw_flag = true;
                straws_selected.add(c.getText().toString());
                straw_cp.add(cereal_straws_cp_list.get(count));
                straw_tdn.add(cereal_straws_tdn_list.get(count));
                straw_dm.add(cereal_straws_dm_list.get(count));
                straw_ca.add(cereal_straws_ca_list.get(count));
                straw_p.add(cereal_straws_p_list.get(count));

                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);


                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);
                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                straw_cost.add(cost_indi);

                strawMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cereal_straws_dm_list.get(count))
                                                        .cpAndTdn(cereal_straws_cp_list.get(count), cereal_straws_tdn_list.get(count))
                                                        .caAndPh(cereal_straws_ca_list.get(count), cereal_straws_p_list.get(count))
                                                        .cost(cost_indi)
                                                        .build());

            }
            Log.d("Checkbox","condition check");
            Log.d("Count",Integer.toString(count));
            Log.d("Count",Integer.toString(layout.getChildCount()));
            if(count>=layout.getChildCount()-2)
                break;
        }

         layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("legume_straws");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("legume_straws"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                straw_flag = true;
                straws_selected.add(c.getText().toString());
                straw_cp.add(legume_straws_cp_list.get(count));
                straw_tdn.add(legume_straws_tdn_list.get(count));
                straw_dm.add(legume_straws_dm_list.get(count));
                straw_ca.add(legume_straws_ca_list.get(count));
                straw_p.add(legume_straws_p_list.get(count));


                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);
                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                straw_cost.add(cost_indi);

                strawMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(legume_straws_dm_list.get(count))
                        .cpAndTdn(legume_straws_cp_list.get(count), legume_straws_tdn_list.get(count))
                        .caAndPh(legume_straws_ca_list.get(count), legume_straws_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }

         layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("native_and_improved_grasses");
      //  ////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("native_and_improved_grasses"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                ////////////////////////Toast.makeText(this,c.getText(),////////////////////////Toast.LENGTH_LONG).show();

                grass_flag = true;
                grass_selected.add(c.getText().toString());
                grass_cp.add(native_and_improved_grasses_cp_list.get(count));
                grass_tdn.add(native_and_improved_grasses_tdn_list.get(count));
                grass_dm.add(native_and_improved_grasses_dm_list.get(count));
                grass_ca.add(native_and_improved_grasses_ca_list.get(count));
                grass_p.add(native_and_improved_grasses_p_list.get(count));

                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);

                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                grass_cost.add(cost_indi);

                grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(native_and_improved_grasses_dm_list.get(count))
                        .cpAndTdn(native_and_improved_grasses_cp_list.get(count), native_and_improved_grasses_tdn_list.get(count))
                        .caAndPh(native_and_improved_grasses_ca_list.get(count), native_and_improved_grasses_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }


         layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("legume_variety");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("legume_variety"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                grass_flag = true;
                grass_selected.add(c.getText().toString());
                grass_cp.add(legume_variety_cp_list.get(count));
                grass_tdn.add(legume_variety_tdn_list.get(count));
                grass_dm.add(legume_variety_dm_list.get(count));
                grass_ca.add(legume_variety_ca_list.get(count));
                grass_p.add(legume_variety_p_list.get(count));

                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.

                EditText ed = (EditText)layout1.getChildAt(index+2);

                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                grass_cost.add(cost_indi);

                grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(legume_variety_dm_list.get(count))
                        .cpAndTdn(legume_variety_cp_list.get(count), legume_variety_tdn_list.get(count))
                        .caAndPh(legume_variety_ca_list.get(count), legume_variety_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cakes");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cakes"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                concmix_flag = true;
                concmix_selected.add(c.getText().toString());
                concmix_cp.add(cakes_cp_list.get(count));
                concmix_tdn.add(cakes_tdn_list.get(count));
                concmix_dm.add(cakes_dm_list.get(count));
                concmix_ca.add(cakes_ca_list.get(count));
                concmix_p.add(cakes_p_list.get(count));

                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);

                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                concmix_cost.add(cost_indi);

                concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cakes_dm_list.get(count))
                        .cpAndTdn(cakes_cp_list.get(count), cakes_tdn_list.get(count))
                        .caAndPh(cakes_ca_list.get(count), cakes_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("byproducts");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("byproducts"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                String text = c.getText().toString().toLowerCase();
                if(text.contains("ddgs (wet)")){
                    bdgs_wet_flag = true;
//                    concmix_flag = true;

                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText) layout1.getChildAt(index + 2);
                    try {
                        bdgs_wet_cost = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        bdgs_wet_cost = 0;
                    }

                    bdgs_wet_dm = byproducts_dm_list.get(count);
                    bdgs_wet_cp = byproducts_cp_list.get(count);
                    bdgs_wet_tdn = byproducts_tdn_list.get(count);
                    bdgs_wet_ca = byproducts_ca_list.get(count);
                    bdgs_wet_p = byproducts_p_list.get(count);

                    specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(byproducts_dm_list.get(count))
                            .cpAndTdn(byproducts_cp_list.get(count), byproducts_tdn_list.get(count))
                            .caAndPh(byproducts_ca_list.get(count), byproducts_p_list.get(count))
                            .cost(bdgs_wet_cost)
                            .build());
                }
                else if(text.equalsIgnoreCase("BDGS (dry)")){
                    bdgs_dry_flag = true;

                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText) layout1.getChildAt(index + 2);
                    try {
                        bdgs_dry_cost = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        bdgs_dry_cost = 0;
                    }

                    bdgs_dry_dm = byproducts_dm_list.get(count);
                    bdgs_dry_cp = byproducts_cp_list.get(count);
                    bdgs_dry_tdn = byproducts_tdn_list.get(count);
                }
                else {
                    concmix_flag = true;
                    concmix_selected.add(c.getText().toString());
                    concmix_cp.add(byproducts_cp_list.get(count));
                    concmix_tdn.add(byproducts_tdn_list.get(count));
                    concmix_dm.add(byproducts_dm_list.get(count));
                    concmix_ca.add(byproducts_ca_list.get(count));
                    concmix_p.add(byproducts_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    concmix_cost.add(cost_indi);

                    concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(byproducts_dm_list.get(count))
                            .cpAndTdn(byproducts_cp_list.get(count), byproducts_tdn_list.get(count))
                            .caAndPh(byproducts_ca_list.get(count), byproducts_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("miscellaneous");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("miscellaneous"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()) {
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();
                String text = c.getText().toString().toLowerCase();
                if (text.equalsIgnoreCase("azolla")) {
                    azolla_flag = true;

                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText) layout1.getChildAt(index + 2);
                    try {
                        azolla_cost = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        azolla_cost = 0;
                    }

                    azolla_dm = miscellaneous_dm_list.get(count);
                    azolla_cp = miscellaneous_cp_list.get(count);
                    azolla_tdn = miscellaneous_tdn_list.get(count);
                    azolla_ca = miscellaneous_ca_list.get(count);
                    azolla_p = miscellaneous_p_list.get(count);

                    specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                            .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                            .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                            .cost(azolla_cost)
                            .build());
                }
                else if (text.equalsIgnoreCase("bypass fat")) {
                    bypassfat_flag = true;

                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText) layout1.getChildAt(index + 2);
                    try {
                        bypassfat_cost = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        bypassfat_cost  = 0;
                    }

                    bypassfat_dm = miscellaneous_dm_list.get(count);
                    bypassfat_cp = miscellaneous_cp_list.get(count);
                    bypassfat_tdn = miscellaneous_tdn_list.get(count);
                    bypassfat_ca = miscellaneous_ca_list.get(count);
                    bypassfat_p = miscellaneous_p_list.get(count);

                    specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                            .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                            .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                            .cost(bypassfat_cost)
                            .build());
                }
                else if (text.equalsIgnoreCase("urea")) {
                    urea_flag = true;

                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText) layout1.getChildAt(index + 2);
                    try {
                        urea_cost = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        urea_cost = 0;
                    }

                    urea_dm = miscellaneous_dm_list.get(count);
                    urea_cp = miscellaneous_cp_list.get(count);
                    urea_tdn = miscellaneous_tdn_list.get(count);
                    urea_ca = miscellaneous_ca_list.get(count);
                    urea_p = miscellaneous_p_list.get(count);

                    specialFeedMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                            .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                            .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                            .cost(urea_cost)
                            .build());
                }
                else if(text.equalsIgnoreCase("Mineral Mixture(Type 1)")){

                }
                else {
                    concmix_flag = true;
                    concmix_selected.add(c.getText().toString());
                    concmix_cp.add(miscellaneous_cp_list.get(count));
                    concmix_tdn.add(miscellaneous_tdn_list.get(count));
                    concmix_dm.add(miscellaneous_dm_list.get(count));
                concmix_ca.add(miscellaneous_ca_list.get(count));
                                   concmix_p.add(miscellaneous_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    concmix_cost.add(cost_indi);

                    concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(miscellaneous_dm_list.get(count))
                            .cpAndTdn(miscellaneous_cp_list.get(count), miscellaneous_tdn_list.get(count))
                            .caAndPh(miscellaneous_ca_list.get(count), miscellaneous_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("cereal_grains");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("cereal_grains"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                concmix_flag = true;
                concmix_selected.add(c.getText().toString());
                concmix_cp.add(cereal_grains_cp_list.get(count));
                concmix_tdn.add(cereal_grains_tdn_list.get(count));
                concmix_dm.add(cereal_grains_dm_list.get(count));
                concmix_ca.add(cereal_grains_ca_list.get(count));
                concmix_p.add(cereal_grains_p_list.get(count));


                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);

                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                concmix_cost.add(cost_indi);

                concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(cereal_grains_dm_list.get(count))
                        .cpAndTdn(cereal_grains_cp_list.get(count), cereal_grains_tdn_list.get(count))
                        .caAndPh(cereal_grains_ca_list.get(count), cereal_grains_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        layout = (LinearLayout)findViewById(R.id.rel1).findViewWithTag("concentrate_mixture");
        //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
        count = -1;
        for(int i=0;i<Integer.MAX_VALUE;i++){
            CheckBox c = (CheckBox)findViewById(R.id.rel1).findViewWithTag("concentrate_mixture"+i);
            if(count==layout.getChildCount()-1)
                break;
            if(c!=null)
                count++;
            if(c!=null&&c.isChecked()){
                //////////////////////////Toast.makeText(this,c.getText(),//////////////////////////Toast.LENGTH_LONG).show();

                concmix_flag = true;
                concmix_selected.add(c.getText().toString());
                concmix_cp.add(concentrate_mixture_cp_list.get(count));
                concmix_tdn.add(concentrate_mixture_tdn_list.get(count));
                concmix_dm.add(concentrate_mixture_dm_list.get(count));
                concmix_ca.add(concentrate_mixture_ca_list.get(count));
                concmix_p.add(concentrate_mixture_p_list.get(count));


                LinearLayout layout1 = (LinearLayout)c.getParent();
                int index = layout1.indexOfChild(c);

                //Get the Edittext.
                EditText ed = (EditText)layout1.getChildAt(index+2);

                double cost_indi = 0;
                try{
                    cost_indi = Double.parseDouble(ed.getText().toString());
                }
                catch(Exception e){
                    cost_indi = 0;
                }
                concmix_cost.add(cost_indi);

                concMixMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(concentrate_mixture_dm_list.get(count))
                        .cpAndTdn(concentrate_mixture_cp_list.get(count), concentrate_mixture_tdn_list.get(count))
                        .caAndPh(concentrate_mixture_ca_list.get(count), concentrate_mixture_p_list.get(count))
                        .cost(cost_indi)
                        .build());
            }
            if(count==layout.getChildCount()-2)
                break;
        }

        try {
            layout = (LinearLayout) findViewById(R.id.rel1).findViewWithTag("hay");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            for (int i = 0; i < layout.getChildCount() - 1; i++) {
                CheckBox c = (CheckBox) findViewById(R.id.rel1).findViewWithTag("hay" + i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if (c!=null&&c.isChecked()) {
                    ////////////////////////Toast.makeText(this, c.getText(), ////////////////////////Toast.LENGTH_LONG).show();

                    try {
                        grass_flag = true;
                        grass_selected.add(c.getText().toString());
                        grass_cp.add(hay_cp_list.get(count));
                        grass_tdn.add(hay_tdn_list.get(count));
                        grass_dm.add(hay_dm_list.get(count));
                        grass_ca.add(hay_ca_list.get(count));
                      grass_p.add(hay_p_list.get(count));
                    }
                    catch (Exception e){

                        //////////////////Toast.makeText(getApplicationContext(),"Error1",//////////////////Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        return;
                    }

                    LinearLayout layout1 = null;
                    int index  =0;
                    try {

                         layout1 = (LinearLayout) c.getParent();
                         index = layout1.indexOfChild(c);

                    }
                    catch(Exception e){
                       //////////////////Toast.makeText(getApplicationContext(),"Error2",//////////////////Toast.LENGTH_LONG).show();
                        return;
                    }
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    grass_cost.add(cost_indi);

                    grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(hay_dm_list.get(count))
                            .cpAndTdn(hay_cp_list.get(count), hay_tdn_list.get(count))
                            .caAndPh(hay_ca_list.get(count), hay_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

        }catch(Exception e){
            //////////////////Toast.makeText(getApplicationContext(),"Exception in considering hay",//////////////////Toast.LENGTH_LONG).show();
            return;
        }

        try {
            layout = (LinearLayout) findViewById(R.id.rel1).findViewWithTag("silage");
            //////////////////////////Toast.makeText(this,Integer.toString(layout.getChildCount()),//////////////////////////Toast.LENGTH_LONG).show();
            count = -1;
            for (int i = 0; i < layout.getChildCount() - 1; i++) {
                CheckBox c = (CheckBox) findViewById(R.id.rel1).findViewWithTag("silage" + i);
                if(count==layout.getChildCount()-1)
                    break;
                if(c!=null)
                    count++;
                if (c!=null&&c.isChecked()) {
                    ////////////////////////Toast.makeText(this, c.getText(), ////////////////////////Toast.LENGTH_LONG).show();

                    grass_flag = true;
                    grass_selected.add(c.getText().toString());
                    grass_cp.add(silage_cp_list.get(count));
                    grass_tdn.add(silage_tdn_list.get(count));
                    grass_dm.add(silage_dm_list.get(count));
                    grass_ca.add(silage_ca_list.get(count));
                    grass_p.add(silage_p_list.get(count));


                    LinearLayout layout1 = (LinearLayout) c.getParent();
                    int index = layout1.indexOfChild(c);

                    //Get the Edittext.
                    EditText ed = (EditText)layout1.getChildAt(index+2);

                    double cost_indi = 0;
                    try{
                        cost_indi = Double.parseDouble(ed.getText().toString());
                    }
                    catch(Exception e){
                        cost_indi = 0;
                    }
                    grass_cost.add(cost_indi);

                    grassMap.put(c.getText().toString(), new FeedBean.Builder(c.getText().toString()).DM(silage_dm_list.get(count))
                            .cpAndTdn(silage_cp_list.get(count), silage_tdn_list.get(count))
                            .caAndPh(silage_ca_list.get(count), silage_p_list.get(count))
                            .cost(cost_indi)
                            .build());
                }
                if(count==layout.getChildCount()-2)
                    break;
            }

        }
        catch(Exception e){
            return;
        }

        //Now do all the calculations.
        double grass_cp_total=0,grass_tdn_total=0,straw_cp_total=0,straw_tdn_total=0,concmix_cp_total=0,concmix_tdn_total=0,grass_ca_total=0,grass_p_total=0,straw_ca_total=0,straw_p_total=0,concmix_ca_total=0,concmix_p_total=0;
        for(int i=0;i<grass_cp.size();i++){
            grass_cp_avg = (double)grass_cp_avg+grass_cp.get(i);
            grass_tdn_avg =  (double)grass_tdn_avg+grass_tdn.get(i);
        }

        for(int i=0;i<straw_cp.size();i++){
            straw_cp_avg = (double)straw_cp_avg+straw_cp.get(i);
            straw_tdn_avg =  (double)straw_tdn_avg+straw_tdn.get(i);
        }

        for(int i=0;i<concmix_cp.size();i++){
            concmix_cp_avg = (double)concmix_cp_avg+concmix_cp.get(i);
            concmix_tdn_avg =  (double)concmix_tdn_avg+concmix_tdn.get(i);
        }

        grass_cp_total = grass_cp_avg;
        straw_cp_total = straw_cp_avg;
        concmix_cp_total = concmix_cp_avg;

        grass_tdn_total = grass_tdn_avg;
        straw_tdn_total = straw_tdn_avg;
        concmix_tdn_total = concmix_tdn_avg;

        grass_cp_avg = (double) grass_cp_avg/grass_cp.size();
        straw_cp_avg = (double) straw_cp_avg/straw_cp.size();
        concmix_cp_avg = (double) concmix_cp_avg/concmix_cp.size();

        grass_tdn_avg = (double) grass_tdn_avg/grass_tdn.size();
        straw_tdn_avg = (double) straw_tdn_avg/straw_tdn.size();
        concmix_tdn_avg = (double) concmix_tdn_avg/concmix_tdn.size();

        //Initialize variables.
        double dm_from_straw =0, cp_from_straw = 0, tdn_from_straw = 0;
        double dm_from_grass =0, cp_from_grass = 0, tdn_from_grass = 0;
        double dm_from_concmix =0, cp_from_concmix = 0, tdn_from_concmix = 0;

        double dmDup = Double.parseDouble(DM_str);
        double cpDup = Double.parseDouble(CP_str);
        double tdnDup = Double.parseDouble(TDN_str);

        Log.d("Calculations" , "CP Total : "+cpDup);
        Log.d("Calculations" , "TDN Total : "+tdnDup);

        if(bdgs_wet_flag && tdnDup > 0){
            /*if(this.concMixMap.size() > 1) {
                tdn_from_bdgs_wet = tdn_from_concmix*0.65;
                bdgs_wet_weight = tdn_from_bdgs_wet/bdgs_wet_tdn*100;
                if(bdgs_wet_weight<0)
                    bdgs_wet_weight = 0;
                bdgs_wet_fresh = (double) bdgs_wet_weight / bdgs_wet_dm * 100;

                cp_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_cp / 100;
                ca_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_ca*10;
                p_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_p*10;
            }
            else {
            */    bdgs_wet_weight = (double) Double.parseDouble(DM_str) * 0.2;
                bdgs_wet_fresh = (double) bdgs_wet_weight / bdgs_wet_dm * 100;

                cp_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_cp / 100;
                tdn_from_bdgs_wet = bdgs_wet_weight * bdgs_wet_tdn / 100;
                ca_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_ca*10;
                p_from_bdgs_wet = bdgs_wet_weight*bdgs_wet_p*10;
//            }

            updateMap(specialFeedMap, FeedNameConstants.ddgsWet, bdgs_wet_fresh);
            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;
        }

        if(straw_flag && tdnDup > 0){
            dm_from_straw = (double) weight*0.014;
            cp_from_straw = (double) dm_from_straw*straw_cp_avg/100;
            tdn_from_straw = (double) dm_from_straw*straw_tdn_avg/100;

            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

            Log.d("Calculations" , "CP Straw : "+cp_from_straw);
            Log.d("Calculations" , "TDN Straw : "+tdn_from_straw);
        }

        if(grass_flag && tdnDup > 0){
            dm_from_grass = (double) weight*0.0075;
            cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
            tdn_from_grass = (double) dm_from_grass*grass_tdn_avg/100;

            if(tdn_from_grass > tdnDup){
                tdn_from_grass = tdnDup;
                dm_from_grass = (double) tdn_from_grass*100/grass_tdn_avg;
                cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
            }

            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

            Log.d("Calculations" , "CP grass : "+cp_from_grass);
            Log.d("Calculations" , "TDN grass : "+tdn_from_grass);
        }

        if(concmix_flag && tdnDup > 0){
            if(tdnDup > 0){
                tdn_from_concmix = tdnDup;
                dm_from_concmix = (double) tdn_from_concmix/concmix_tdn_avg*100;
                cp_from_concmix = (double) dm_from_concmix*concmix_cp_avg/100;

                tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
                cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
                dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

                Log.d("Calculations" , "CP Concmix : "+cp_from_concmix);
                Log.d("Calculations" , "TDN Concmix : "+tdn_from_concmix);
            }
            else{
                Log.d("Calculations" , "TDN already met : "+tdnDup);
            }
        }

        Log.d("Calculations" , "CP and TDN not within the range");
        Log.d("Calculations" , "CP remaining : "+cpDup);
        Log.d("Calculations" , "TDN Remaining : "+tdnDup);
        if(Math.abs(cpDup) > 50 || (tdnDup > 300)){

        }


        if(!(straw_flag)){
            cp_from_straw=0;
            tdn_from_straw=0;
        }

        if(!(grass_flag)){
            cp_from_grass=0;
            tdn_from_grass=0;
        }

        if(!(straw_flag) && (tdnDup > 0)){
            dm_from_grass=weight*0.014+weight*0.0075;
            tdn_from_grass = (double)dm_from_grass*grass_tdn_avg/100;
            cp_from_grass = (double)dm_from_grass*grass_cp_avg/100;

            if(tdn_from_grass > Double.parseDouble(TDN_str)-bdgs_wet_tdn){
                tdn_from_grass = Double.parseDouble(TDN_str)-bdgs_wet_tdn;
                dm_from_grass = (double) tdn_from_grass*100/grass_tdn_avg;
                cp_from_grass = (double) dm_from_grass*grass_cp_avg/100;
            }

            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

            tdn_from_straw = 0;
            cp_from_straw = 0;
            dm_from_straw = 0;
        }

        if((!(concmix_flag))){
            if(!concmix_flag) {
                dm_from_concmix = 0;
                cp_from_concmix = 0;
            }
            else if(tdn_from_concmix < 0){
                cp_from_concmix = (double) cp-cp_from_grass-cp_from_straw;

                if(cp_from_concmix > 0) {
                    dm_from_concmix = (double) cp_from_concmix / concmix_cp_avg * 100;
                    tdn_from_concmix = (double) dm_from_concmix * concmix_tdn_avg / 100;
                }
                else{
                    dm_from_concmix = 0;
                    cp_from_concmix = 0;
                }
            }
        }


        if((!(concmix_flag)) && (tdnDup > 0)){
            tdn_from_grass+=(double)tdnDup;
            dm_from_grass = (double)tdn_from_grass/grass_tdn_avg*100;
            cp_from_grass = (double)dm_from_grass*grass_cp_avg/100;

            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

            dm_from_concmix=0;
            cp_from_concmix=0;
            tdn_from_concmix=0;
        }

        if(!(grass_flag) && tdnDup > 0){
            tdn_from_concmix = tdnDup;
            dm_from_concmix = tdn_from_concmix/(concmix_tdn_avg/100);
            cp_from_concmix = dm_from_concmix*concmix_cp_avg/100;

            tdnDup = Double.parseDouble(TDN_str)-tdn_from_bdgs_wet-tdn_from_straw-tdn_from_grass-tdn_from_concmix;
            cpDup = Double.parseDouble(CP_str)-cp_from_bdgs_wet-cp_from_straw-cp_from_grass-cp_from_concmix;
            dmDup = Double.parseDouble(DM_str)-dm_from_bdgs_wet-dm_from_straw-dm_from_grass-dm_from_concmix;

            dm_from_grass=0;
            cp_from_grass=0;
            tdn_from_grass=0;
        }


        if(azolla_flag){
            azolla_weight = (double)Double.parseDouble(DM_str)*0.01;
            azolla_fresh = (double)azolla_weight/azolla_dm*100;
            cp_from_azolla = azolla_weight*azolla_cp/100;
            tdn_from_azolla = azolla_weight*azolla_tdn/100;
            ca_from_azolla = azolla_weight*azolla_ca*10;
            p_from_azolla = azolla_weight*azolla_p*10;

            updateMap(specialFeedMap, FeedNameConstants.azolla, azolla_fresh);
        }

        if(bdgs_dry_flag){
            bdgs_dry_weight = (double)Double.parseDouble(DM_str)*0.2;
            bdgs_dry_fresh = (double)bdgs_wet_weight/bdgs_wet_dm*100;

            cp_from_bdgs_dry = bdgs_dry_weight*bdgs_dry_cp/100;
            tdn_from_bdgs_dry = bdgs_dry_weight*bdgs_dry_tdn/100;
        }

        if(bypassfat_flag){
            bypassfat_weight = (double)Double.parseDouble(DM_str)*2.5/100;
            bypassfat_fresh = (double)bypassfat_weight/bypassfat_dm*100;

            cp_from_bypassfat = bypassfat_weight*bypassfat_cp/100;
            tdn_from_bypassfat = bypassfat_weight*bypassfat_tdn/100;
            ca_from_bypassfat = bypassfat_weight*bypassfat_ca*10;
            p_from_bypassfat = bypassfat_weight*bypassfat_p*10;

            updateMap(specialFeedMap, FeedNameConstants.bypassFat, bypassfat_fresh);
        }

        if(urea_flag){
            urea_weight = (double)Double.parseDouble(DM_str)*0.01;
            urea_weight = (urea_weight > 0.1)? 0.1 : urea_weight;
            urea_fresh = (double)urea_weight/urea_dm*100;


            cp_from_urea = urea_weight*urea_cp/100;
            tdn_from_urea = urea_weight*urea_tdn/100;
            ca_from_urea = urea_weight*urea_ca*10;
            p_from_urea = urea_weight*urea_p*10;

            updateMap(specialFeedMap, FeedNameConstants.urea, urea_fresh);
        }

        if(concmix_flag && tdnDup > 0){
            tdn_from_concmix = tdnDup;
            dm_from_concmix = (double) tdn_from_concmix/concmix_tdn_avg*100;
            cp_from_concmix = (double) dm_from_concmix*concmix_cp_avg/100;
        }

        double total_cp = cp_from_concmix+cp_from_grass+cp_from_straw+cp_from_azolla+cp_from_bdgs_dry+cp_from_bdgs_wet+cp_from_bypassfat;
        double decision = total_cp-cp;
        double total_dm = 0;
        boolean decision_flag = false;
        double concmix_add_dm = 0;
        double concmix_add_cp = 0;
        double concmix_add_tdn = 0;

        //Displaying results.
        double final_dm = dm_from_concmix+dm_from_grass+dm_from_straw+azolla_weight+bdgs_dry_weight+bdgs_wet_weight+bypassfat_weight;
        double final_cp = cp_from_concmix+cp_from_grass+cp_from_straw+cp_from_azolla+cp_from_bdgs_wet+cp_from_bdgs_dry+cp_from_bypassfat;
        double final_tdn = tdn_from_concmix+tdn_from_grass+tdn_from_straw;

        final_dm = (double)Math.round(final_dm*100)/100;
        final_cp = (double)Math.round(final_cp*100)/100;
        final_tdn = (double)Math.round(final_tdn*100)/100;

        feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromStraw, dm_from_straw);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromGrass, dm_from_grass);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.dmFromConcmix, dm_from_concmix);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromStraw, cp_from_straw);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromGrass, cp_from_grass);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.cpFromConcmix, cp_from_concmix);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromStraw, tdn_from_straw);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromGrass, tdn_from_grass);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.tdnFromConcmix, tdn_from_concmix);

        feedTypeEnergyMap.put(FeedTypePropertyConstants.strawTdnAvg, straw_tdn_avg);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.grassTdnAvg, grass_tdn_avg);
        feedTypeEnergyMap.put(FeedTypePropertyConstants.concmixTdnAvg, concmix_tdn_avg);

        calculateFeedFreshWeight(strawMap, dm_from_straw, straw_tdn_total);
        calculateFeedFreshWeight(grassMap, dm_from_grass, grass_tdn_total);
        calculateFeedFreshWeight(concMixMap, dm_from_concmix, concmix_tdn_total);
        layout = (LinearLayout)findViewById(R.id.dynamic_layout);

        prepareFinalView(layout, strawMap, grassMap, concMixMap, specialFeedMap);

        LinearLayout layout_submit = (LinearLayout)findViewById(R.id.linearLayout);
        layout_submit.setVisibility(View.GONE);
        ScrollView scroll_feeds = (ScrollView)findViewById(R.id.scroll_feeds);
        scroll_feeds.setVisibility(View.GONE);
        ScrollView new_feed = (ScrollView)findViewById(R.id.add_new_feed);
        new_feed.setVisibility(View.GONE);
        LinearLayout dynamic_layout = (LinearLayout)findViewById(R.id.dynamic_layout);
        dynamic_layout.setVisibility(View.VISIBLE);


    }

    private void prepareFinalView(LinearLayout layout, Map<String, FeedBean> strawMap, Map<String, FeedBean> grassMap, Map<String, FeedBean> concMixMap, Map<String, FeedBean> specialFeedMap) {
        prepareFarmerDetailView(layout);
        prepareViewsForFeeds(layout, strawMap, grassMap, concMixMap, specialFeedMap);
    }

    private void prepareViewsForFeeds(LinearLayout layout, Map<String, FeedBean> strawMap, Map<String, FeedBean> grassMap, Map<String, FeedBean> concMixMap, Map<String, FeedBean> specialFeedMap) {
        TableLayout feedsInfoTable = new TableLayout(this);
        feedsInfoTable.addView(prepareHeaderForTable(getString(R.string.feedsHeader), getString(R.string.requiredHeader)));
        prepareTableDataForFeeds(strawMap, grassMap, concMixMap, specialFeedMap, feedsInfoTable);
        styleTable(feedsInfoTable);
        layout.addView(feedsInfoTable);
        prepareNutrientAndResultViews(layout, strawMap, grassMap, concMixMap, specialFeedMap);
    }

    private void prepareNutrientAndResultViews(LinearLayout layout, Map<String, FeedBean> strawMap, Map<String, FeedBean> grassMap, Map<String, FeedBean> concMixMap, Map<String, FeedBean> specialFeedMap) {
        LinearLayout subLinearLayout = new LinearLayout(this);
        subLinearLayout.setOrientation(LinearLayout.VERTICAL);
        subLinearLayout.setTag("subLinearLayout");

        NutrientsSuppliedAndTotalCostBean nutrientsSupplied = new NutrientsSuppliedAndTotalCostBean();
        updateNutrientsSupplied(strawMap, nutrientsSupplied, true, 0.3, 0.64);
        updateNutrientsSupplied(grassMap, nutrientsSupplied, true, 0.3, 0.64);
        updateNutrientsSupplied(concMixMap, nutrientsSupplied, false, 0.6, 0.74);
        updateNutrientsSupplied(specialFeedMap, nutrientsSupplied, false, 0.6, 0.74);

        subLinearLayout.addView(prepareTableForNutrients(nutrientsSupplied));
        subLinearLayout.addView(prepareCostDialog(nutrientsSupplied));
        subLinearLayout.addView(prepareRoughageConcRatioDialog(nutrientsSupplied));

        //Handle exceptions.
        addExceptionViews(subLinearLayout, nutrientsSupplied);
        layout.addView(subLinearLayout);
    }

    private void updateNutrientsSupplied(Map<String, FeedBean> feedMap, NutrientsSuppliedAndTotalCostBean nutrientsSupplied, boolean isRoughage, double caAbsorbable, double pAbsorbable) {
        for (String k : feedMap.keySet()) {
            FeedBean feedBean = feedMap.get(k);
            nutrientsSupplied.increment(feedBean.getDryWeight(), feedBean.getFeedCp(),
                    feedBean.getFeedTDN(), feedBean.getFeedCa(), feedBean.getFeedP(), caAbsorbable, pAbsorbable);
            nutrientsSupplied.incrementRoughageConcentrateAndCost(feedBean.getDryWeight(), isRoughage,
                    feedBean.getFeedCost()*feedBean.getFeedWeight());
        }
    }

    private void addExceptionViews(LinearLayout layout, NutrientsSuppliedAndTotalCostBean nutrientsSupplied) {
        boolean noExceptions = true;
        double cpDelta = Double.parseDouble(CP_str) * 0.1;
        double maxCp = cpDelta > 0.05 ? Double.parseDouble(CP_str)*1.1 : Double.parseDouble(CP_str) + 0.05;
        double minCp = cpDelta > 0.05 ? Double.parseDouble(CP_str)*0.9 : Double.parseDouble(CP_str) - 0.05;
        double tdnDelta = Double.parseDouble(TDN_str)*0.05;
        /*double tdnMax = (tdnDelta > 0.3)? Double.parseDouble(TDN_str)*1.05 : Double.parseDouble(TDN_str)+0.3;
        double tdnMin = (tdnDelta > 0.3)? Double.parseDouble(TDN_str)*0.95 : Double.parseDouble(TDN_str)-0.3;*/
        double tdnMax = (tdnDelta > 0.3)? Double.parseDouble(TDN_str)+0.3 : Double.parseDouble(TDN_str)+0.3;
        double tdnMin = (tdnDelta > 0.3)? Double.parseDouble(TDN_str)-0.3 : Double.parseDouble(TDN_str)-0.3;

        if(nutrientsSupplied.getTotalCP() > maxCp || nutrientsSupplied.getTotalCP() < minCp){
            noExceptions = false;
            String exceptionMessage = getString(R.string.cpExcessString);
            exceptionMessage = nutrientsSupplied.getTotalCP() < minCp ?
                    getString(R.string.cpDeficientString): exceptionMessage;

            if(!(this.grassMap.isEmpty() && this.concMixMap.isEmpty())){
                exceptionMessage += " "+getString(R.string.editFieldsMessage);
            }

            layout.addView(prepareExceptionsDialog(exceptionMessage));
        }
        if(nutrientsSupplied.getTotalTDN() < tdnMin){
            noExceptions = false;
            String exceptionMessage = getString(R.string.tdnDeficientString);
            if(!(this.grassMap.isEmpty() && this.concMixMap.isEmpty())){
                exceptionMessage += " "+getString(R.string.editFieldsMessage);
            }
            layout.addView(prepareExceptionsDialog(exceptionMessage));
        }
        if(nutrientsSupplied.getTotalTDN() > tdnMax){
            noExceptions = false;
            String exceptionMessage = getString(R.string.tdnExcessString);
            if(!(this.grassMap.isEmpty() && this.concMixMap.isEmpty())){
                exceptionMessage += " "+getString(R.string.editFieldsMessage);
            }
            layout.addView(prepareExceptionsDialog(exceptionMessage));
        }
        if(nutrientsSupplied.getTotalCA() < Double.parseDouble(CA_str)){
            double diff = Double.parseDouble(CA_str) - nutrientsSupplied.getTotalCA();
            diff = (double) Math.round(diff * 1000)/1000;
            double value = (double)((diff/0.18)/0.75)*1000;
            value = (double)Math.round(value*1)/1;
            value = (value > 100)? 100 : value;
            String exceptionMessage = getString(R.string.caDeficientString1)+" "+value+getString(R.string.caDeficientString2);

            if(value > 0)
                layout.addView(prepareExceptionsDialog(exceptionMessage));
        }

        if(!noExceptions){
            createViewForExceptionHandling(layout);
        }
        else{
            createViewForBalancedRation(layout);
        }
    }

    private void createViewForBalancedRation(LinearLayout layout) {
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.right);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity=Gravity.CENTER;
        layoutParams.width = 100;
        layoutParams.height = 100;
        layoutParams.setMargins(0,10,0,0);
        image.setTag("imageCorrect");
        image.setLayoutParams(layoutParams);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedMixture.this.finish();
                startActivity(new Intent(FeedMixture.this, Feed_Ration.class).putExtra("language",language));
            }
        });

        layout.addView(image);

        Button btn = new Button(this);
        btn.setTextColor(Color.BLUE);
        btn.setText(getString(R.string.save));
        btn.setTextSize(30);
        btn.setBackgroundColor(Color.WHITE);
        btn.setGravity(Gravity.CENTER);
        btn.setTag("saveButton");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create File Firectory.
                final LinearLayout layout1 = (LinearLayout)findViewById(R.id.dynamic_layout);
                final RelativeLayout layout2 = (RelativeLayout)findViewById(R.id.rel1);
                final ScrollView scrollview = (ScrollView)findViewById(R.id.reportScreen);
                Button save = (Button)layout1.findViewWithTag("saveButton");
                layout1.removeView(save);
                ImageView imageview = (ImageView)layout1.findViewWithTag("imageCorrect");
                layout1.removeView(imageview);

                File f = create_report_directory();

                //Update Farmers File.
                File farmersFile = new File(Environment.getExternalStorageDirectory() +
                        File.separator + "Ration Formulator"+File.separator+"farmers_info.txt");
                if(!farmersFile.exists()) {
                    try {
                        farmersFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Writer output = null;
                String result = farmerName+";;"+farmerAddress+";;"+animalNumber;

                try {
                    output = new BufferedWriter(new FileWriter(farmersFile.getAbsolutePath(), true));  //clears file every time
                    output.append("\n" + result);
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }

                generate_pdf(f,layout1);
                Toast.makeText(getApplicationContext(), getString(R.string.reportSuccessString), Toast.LENGTH_LONG).show();
                //layout1.addView(imageview);
                //layout1.addView(save);

            }
        });
        layout.addView(btn);
    }

    private void createViewForExceptionHandling(LinearLayout layout) {
        Button reselectFeedsButton = new Button(this);
        reselectFeedsButton.setTextColor(Color.GREEN);
        reselectFeedsButton.setText(getString(R.string.reselectFeedsbutton));
        reselectFeedsButton.setTextSize(30);
        reselectFeedsButton.setBackgroundColor(Color.WHITE);
        reselectFeedsButton.setGravity(Gravity.CENTER_HORIZONTAL);
        reselectFeedsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout_submit = (LinearLayout) findViewById(R.id.linearLayout);
                layout_submit.setVisibility(View.VISIBLE);

                ScrollView scroll_feeds = (ScrollView) findViewById(R.id.scroll_feeds);
                scroll_feeds.setVisibility(View.VISIBLE);

                ScrollView new_feed = (ScrollView) findViewById(R.id.add_new_feed);
                new_feed.setVisibility(View.VISIBLE);

                LinearLayout layout = (LinearLayout) findViewById(R.id.dynamic_layout);
                layout.setVisibility(View.GONE);
                layout.removeAllViews();
            }
        });

        layout.addView(reselectFeedsButton);
    }

    private View prepareExceptionsDialog(String exceptionMessage) {
        TextView exceptionDialog = new TextView(this);
        exceptionDialog.setText(exceptionMessage);
        exceptionDialog.setPadding(0, 0, 0, 20);
        exceptionDialog.setTextColor(Color.RED);
        exceptionDialog.setId(R.id.button);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(2, Color.BLUE);
        exceptionDialog.setBackgroundDrawable(drawable);
        exceptionDialog.setPadding(10,5,0,5);

        return exceptionDialog;
    }

    private TextView prepareCostDialog(NutrientsSuppliedAndTotalCostBean nutrientsSupplied) {
        TextView dialog = new TextView(this);
        dialog.setText(getString(R.string.totalCostString)+ Math.round(nutrientsSupplied.getTotalCost() * 100)/100 );
        dialog.setPadding(0, 0, 0, 20);
        dialog.setTextColor(Color.BLUE);

        giveBorder(dialog);
        setMargin(0,0,0,40,dialog);
        return dialog;
    }

    private TextView prepareRoughageConcRatioDialog(NutrientsSuppliedAndTotalCostBean nutrientsSupplied) {
        TextView dialog = new TextView(this);
        double roughage = nutrientsSupplied.getRoughage() / nutrientsSupplied.getTotalDM() * 100;
        roughage = Math.round(roughage * 1)/1;
        double concentrate = 100 - roughage;
        dialog.setText(getString(R.string.roughageRatioString)+roughage + " : " + concentrate);
        dialog.setPadding(0, 0, 0, 20);
        dialog.setTextColor(Color.BLUE);

        giveBorder(dialog);
        setMargin(0,0,0,40,dialog);
        return dialog;
    }

    private void giveBorder(View view){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(2, Color.DKGRAY);
        view.setBackgroundDrawable(drawable);
        view.setPadding(10,5,10,5);
    }

    private void setMargin(int left, int top, int right, int bottom, View view){
        LinearLayout.LayoutParams buttonLayoutParams = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(buttonLayoutParams);
    }

    private TableLayout prepareTableForNutrients(NutrientsSuppliedAndTotalCostBean nutrientsSupplied) {
        TableLayout nutrientsInfoTable = new TableLayout(this);
        nutrientsInfoTable.addView(prepareHeaderForTable(getString(R.string.nutrientsHeader), getString(R.string.requiredHeaderNutrients), getString(R.string.suppliedHeaderNutrients)));
        prepareTableDataForNutrients(nutrientsSupplied, nutrientsInfoTable);
        styleTable(nutrientsInfoTable);
//        nutrientsInfoTable.setPadding(10,5,0,5);

        return nutrientsInfoTable;
    }

    private View prepareTableDataForNutrients(NutrientsSuppliedAndTotalCostBean nutrientsSupplied, TableLayout table) {
        prepareTableRowForNutrients(table, nutrientsSupplied, getString(R.string.dryMatter), DM_str, Double.toString(nutrientsSupplied.getTotalDM()), 100);
        prepareTableRowForNutrients(table, nutrientsSupplied, getString(R.string.CP), CP_str, Double.toString(nutrientsSupplied.getTotalCP()), 100);
        prepareTableRowForNutrients(table, nutrientsSupplied, getString(R.string.TDN), TDN_str, Double.toString(nutrientsSupplied.getTotalTDN()), 100);
        prepareTableRowForNutrients(table, nutrientsSupplied, getString(R.string.CA), CA_str, Double.toString(nutrientsSupplied.getTotalCA()), 1000);
        prepareTableRowForNutrients(table, nutrientsSupplied, getString(R.string.PH), P_str, Double.toString(nutrientsSupplied.getTotalPH()), 1000);

        return table;
    }

    private void prepareTableRowForNutrients(TableLayout table, NutrientsSuppliedAndTotalCostBean nutrientsSupplied,
                                             String nutrientType, String actualValue,
                                             String calculatedValue, int decimalConfig){
        TableRow tr =  new TableRow(this);
        TextView c1 = new TextView(this);
        c1.setText(nutrientType);
        c1.setGravity(Gravity.LEFT);
        tr.addView(c1);

        TextView c2 = new TextView(this);
        c2.setText(limitDecimals(actualValue, decimalConfig));
        c2.setGravity(Gravity.CENTER);
        tr.addView(c2);

        TextView c3 = new TextView(this);
        c3.setText(limitDecimals(calculatedValue, decimalConfig));
        c3.setGravity(Gravity.CENTER);
        tr.addView(c3);

        table.addView(tr);
    }

    private String limitDecimals(String actualValue, int factor) {
        double newValue = (double)Math.round(Double.parseDouble(actualValue)*factor)/factor;
        return Double.toString(newValue);
    }

    private TableLayout prepareTableDataForFeeds(Map<String, FeedBean> strawMap, Map<String, FeedBean> grassMap,
                                                 Map<String, FeedBean> concMixMap, Map<String, FeedBean> specialFeedMap,
                                                 TableLayout table) {
        prepareTableRows(table, strawMap, true);
        prepareTableRows(table, grassMap, false);
        prepareTableRows(table, concMixMap, false);
        prepareTableRows(table, specialFeedMap, true);

        return table;
    }

    private void prepareTableRows(TableLayout table, Map<String, FeedBean> feedMap, boolean shouldRestrictValue) {
        for (String k : feedMap.keySet()) {
            FeedBean feedBean = feedMap.get(k);
            table.addView(prepareRowForFeed(feedBean.getFeedName(), feedBean.getFeedWeight(), shouldRestrictValue, feedMap));
        }
    }

    private View prepareRowForFeed(String feedName, double feedWeight, boolean shouldRestrict, Map<String, FeedBean> feedMap) {
        TableRow tr =  new TableRow(this);
        TextView c1 = new TextView(this);
        c1.setText(feedName);
        c1.setGravity(Gravity.LEFT);
        c1.setTextColor(Color.parseColor("#800000"));
        c1.setMaxWidth((int)(getMaxWidth() * 0.4));

        EditText c2 = new EditText(this);
        String feedWeightLimitedDecimals = limitDecimals(Double.toString(feedWeight), 10);
        c2.setText(feedWeightLimitedDecimals);
        c2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        c2.setKeyListener(DigitsKeyListener.getInstance(true,true));
        c2.setTag(feedName);
        c2.setTextColor(Color.parseColor("#FF5733"));
        c2.addTextChangedListener(new TextWatcherForFeeds(c2, feedMap, getApplicationContext()));
        if(shouldRestrict) {
            c2.setFilters(new InputFilter[]{new NumericalInputFilterEditText(0, Double.parseDouble(feedWeightLimitedDecimals), c2, getString(R.string.maxFilterString))});
        }
        c2.setGravity(Gravity.CENTER_HORIZONTAL);
        tr.addView(c1);
        tr.addView(c2);

        return tr;
    }

    private double getMaxWidth() {
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        return width;
    }

    private TableRow prepareHeaderForTable(String... headers) {
        TableRow row =  new TableRow(this);

        for(String header : headers){
            TextView cell = new TextView(this);
            cell.setText(header);
            cell.setGravity(Gravity.LEFT);
            cell.setTextColor(Color.BLUE);
            row.addView(cell);
        }

        return row;
    }

    private void styleTable(TableLayout table){
        table.setStretchAllColumns(true);
        table.bringToFront();

        LinearLayout.LayoutParams buttonLayoutParams = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.setMargins(0, 0, 0, 40);
        table.setLayoutParams(buttonLayoutParams);

        giveBorder(table);
    }

    private void prepareFarmerDetailView(LinearLayout layout) {
        TableLayout farmerDetails =  new TableLayout(this);
        farmerDetails.setTag("farmerDetails");
        farmerDetails.setStretchAllColumns(true);
        farmerDetails.bringToFront();
        // farmerDetails.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,7f));

        TextView intro = new TextView(this);
        intro.setText(getString(R.string.farmerDetails));
        intro.setTextColor(Color.BLUE);
        intro.setGravity(Gravity.CENTER_HORIZONTAL);
        farmerDetails.addView(intro);

        TableRow tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        TextView c11 = new TextView(this);
        c11.setText(getString(R.string.farmerName));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        TextView c21 = new TextView(this);
        c21.setText(farmerName);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);
        DisplayMetrics mDisplayMetrics = getApplicationContext().getResources().getDisplayMetrics();

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.farmerAddress));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));

        c21 = new TextView(this);
        c21.setText(farmerAddress);
        c21.setMaxLines(1);
        c21.setEllipsize(TextUtils.TruncateAt.END);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.animalNumber));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));

        c21 = new TextView(this);
        c21.setText(animalNumber);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.animalType));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));

        c21 = new TextView(this);
        c21.setText(animalType);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.milkYield));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        c21 = new TextView(this);
        c21.setText(milk_str);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.fatContent));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        c21 = new TextView(this);
        c21.setText(fat_content);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
        String today = dateFormat.format(date).toString();

        tr1 =  new TableRow(this);
        // tr1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        c11 = new TextView(this);
        c11.setText(getString(R.string.dateTime));
        //  c1.setLayoutParams(buttonLayoutParams);
        c11.setGravity(Gravity.LEFT);
        // c11.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        c21 = new TextView(this);
        c21.setText(today);
        //  c1.setLayoutParams(buttonLayoutParams);
        c21.setGravity(Gravity.LEFT);
        c21.setTextColor(Color.BLUE);
        // c21.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
        tr1.addView(c11);
        tr1.addView(c21);
        farmerDetails.addView(tr1);

        farmerDetails.setPadding(10,5,0,5);
        LinearLayout.LayoutParams buttonLayoutParams = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.setMargins(0, 0, 0, 40);
        farmerDetails.setLayoutParams(buttonLayoutParams);


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(2, Color.BLACK);
        farmerDetails.setBackgroundDrawable(drawable);

        layout.addView(farmerDetails);
    }

    private void calculateFeedFreshWeight(Map<String, FeedBean> feedMap, double dmFromFeedType, double totalTdnFromFeedType) {
        for (String k : feedMap.keySet()) {
            FeedBean feedBean = feedMap.get(k);
            feedBean.computeWeight(dmFromFeedType, totalTdnFromFeedType);
            feedMap.put(k, feedBean);
        }
    }

    private void updateMap(Map<String, FeedBean> feedMap, String feedName, double feedWeight) {
        FeedBean feedBean = feedMap.get(feedName);
        feedBean.setFeedWeight(feedWeight);
        feedMap.put(feedName, feedBean);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void generate_pdf(File dir, LinearLayout layout1) {
        try{
            String mPath = dir.getAbsolutePath()+File.separator+animalNumber+".pdf";
            PdfDocument report = new PdfDocument();

            View content = layout1;
            int pno = 1;

            PdfDocument.PageInfo pageinfo = new PdfDocument.PageInfo.Builder(content.getWidth()+10,content.getHeight(),pno).create();
            PdfDocument.Page page = report.startPage(pageinfo);

            content.draw(page.getCanvas());
            report.finishPage(page);
            OutputStream out = null;
            try {
                File outputFile = new File(dir.getAbsolutePath(), animalNumber + ".pdf");
                outputFile.createNewFile();
                out = new FileOutputStream(outputFile);
                report.writeTo(out);
            }
            finally{
                report.close();
                out.close();
            }

        }
        catch(Exception e){

        }
    }

    private void takeScreenshot(File dir) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = dir.getAbsolutePath()+File.separator+animalNumber+".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
    private String generate_outputString() {
        String result = "";
        result+= "Farmer Details:\n\n";
        result+= "Ration Formulation done by Farmer - '"+farmerName+"' for the animal bearing the number - '"+animalNumber+"'\n\n";
        result+= "Animal Details:\n\n";
        result+= "Animal Type : "+animalType+"\n";
        result+= "Animal Weight : "+weight_animal+"\n";
        result+= "Milk Yield : "+milk_str+"\n\n";
        result+= "Ration Details:\n\n";
        result+= final_output;

        return result;
    }

    private File create_report_directory() {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Ration Formulator");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File subFolder = new File(folder.getAbsolutePath()+File.separator+farmerName);
        if (!subFolder.exists()) {
            subFolder.mkdir();
        }

        File mainFile = new File(subFolder.getAbsolutePath()+File.separator+animalNumber+".txt");
        if(!mainFile.exists()) {
            try {
                mainFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return subFolder;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent i = new Intent(FeedMixture.this, Feed_Ration.class).putExtra("language",language);
        startActivity(i);
        finish();
    }

    //Code for adding new feed.
    public void add_new_feed(View v){
        if(language.equalsIgnoreCase("telugu")){
            add_new_feed_telugu(v);
            return;
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeedMixture.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



        alertDialog.setTitle("Add new Feed");

        ArrayList<String> feed_types = new ArrayList<String>();
        feed_types.add("Select Feed Type");
        feed_types.add("Cereal Straws");
        feed_types.add("Legume Straws");
        feed_types.add("Native and Improved grasses");
        feed_types.add("Legume Variety");
        feed_types.add("Cakes");
        feed_types.add("Byproducts");
        feed_types.add("Miscellaneous");
        feed_types.add("Cereal Grains");
        feed_types.add("Concentrate Mixture");
        feed_types.add("Hay");
        feed_types.add("Silage");

        ScrollView s1= new ScrollView(FeedMixture.this);
        s1.setFillViewport(true);



        LinearLayout l = new LinearLayout(FeedMixture.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(FeedMixture.this);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 50, 0,20);
        s.setLayoutParams(lp1);

        s.setPadding(0, 0, 0, 10);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,feed_types);
        s.setAdapter(adapter);
        s.setSelection(0);
        s.setTag("select");
        l.addView(s);
//        alertDialog.setView(s);
        final EditText input4 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp5.setMargins(0, 0, 0,30);
        input4.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input4.setSingleLine();
        input4.setLayoutParams(lp5);
        input4.setHint("Enter the Name of the feed");
        input4.setTag("name");
        l.addView(input4);

        final EditText input = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0,30);
        input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input.setSingleLine();
        input.setLayoutParams(lp);
        input.setHint("Enter Protein Content(%cp)");
        input.setTag("cp");
        l.addView(input);



        final EditText input1 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(0, 0, 0,15);
        input1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input1.setSingleLine();
        input1.setLayoutParams(lp2);
        input1.setHint("Enter TDN(%TDN)");
        input1.setTag("tdn");
        l.addView(input1);

        final EditText input3 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 0, 0,20);
        input3.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input3.setSingleLine();
        input3.setLayoutParams(lp3);
        input3.setHint("Enter Dry Matter Content(%dm)");
        input3.setTag("dm");
        l.addView(input3);

        final EditText input5 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(0, 0, 0,20);
        input5.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input5.setSingleLine();
        input5.setLayoutParams(lp4);
        input5.setHint("Enter Calcium Content(%Ca)");
        input5.setTag("ca");
        l.addView(input5);

        final EditText input6 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp51 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp51.setMargins(0, 0, 0,20);
        input6.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input6.setSingleLine();
        input6.setLayoutParams(lp51);
        input6.setHint("Enter Phosphorous Content(%p)");
        input6.setTag("p");
        l.addView(input6);

        s1.addView(l);
        alertDialog.setView(s1);

            alertDialog.setPositiveButton("Add",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Dialog f = (Dialog) dialog;
                            Spinner s = (Spinner) f.findViewById(R.id.welcome_text).findViewWithTag("select");
                            EditText ed = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("cp");
                            EditText ed1 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("tdn");
                            EditText ed2 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("dm");
                            EditText ed3 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("name");
                            EditText ed4 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("ca");
                            EditText ed5 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("p");


                            String file_name = "";
                            String selected = s.getSelectedItem().toString().toLowerCase();

                            if (selected.equalsIgnoreCase("Cereal Straws")) {
                                file_name = "cereal_straws";
                            } else if (selected.equalsIgnoreCase("Legume Straws")) {
                                file_name = "legume_straws";
                            } else if (selected.equalsIgnoreCase("Native and Improved grasses")) {
                                file_name = "native_and_improved_grasses";
                            } else if (selected.equalsIgnoreCase("Legume Variety")) {
                                file_name = "legume_variety";
                            } else if (selected.equalsIgnoreCase("Cakes")) {
                                file_name = "cakes";
                            } else if (selected.equalsIgnoreCase("Byproducts")) {
                                file_name = "byproducts";
                            } else if (selected.equalsIgnoreCase("Miscellaneous")) {
                                file_name = "miscellaneous";
                            } else if (selected.equalsIgnoreCase("Cereal Grains")) {
                                file_name = "cereal_grains";
                            } else if (selected.equalsIgnoreCase("Concentrate Mixture")) {
                                file_name = "concentrate_mixture";
                            } else if (selected.equalsIgnoreCase("Hay")) {
                                file_name = "hay";
                            } else if (selected.equalsIgnoreCase("Silage")) {
                                file_name = "silage";
                            } else {
                                file_name = "";
                            }


                            final String to_be_written = ed3.getText().toString() + "," + ed2.getText().toString() + "," + ed.getText().toString() + "," + ed1.getText().toString() + "," + ed4.getText().toString() + "," + ed5.getText().toString()+", ";
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //////////////////////////Toast.makeText(getApplicationContext(),to_be_written,//////////////////////////Toast.LENGTH_LONG).show();
                                }
                            });

                            //Update the file.
                            if (file_name.equals("")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //////////////////////////Toast.makeText(getApplicationContext(),"Please Select any feed type",//////////////////////////Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                Writer output = null;
                                try {
                                    output = new BufferedWriter(new FileWriter(static_path + "/" + file_name + ".txt", true));  //clears file every time
                                    output.append("\n" + to_be_written);
                                    output.close();
                                    LinearLayout s1 = (LinearLayout) findViewById(R.id.linearLayout1);
                                    s1.removeAllViews();
                                    generate_feeds();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //////Toast.makeText(getApplicationContext(), "Feed Successfully added", //////Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });


        alertDialog.show();
    }

    public void add_new_feed_telugu(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeedMixture.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



        alertDialog.setTitle("కొత్త మేత జోడించండి");

        ArrayList<String> feed_types = new ArrayList<String>();
        feed_types.add("మేత రకం ఎంచుకోండి");
        feed_types.add("ఎండు గడ్డి రకాలు");
        feed_types.add("ధాన్యపు జాతి ఎండు గడ్డి");
        feed_types.add("పచ్చి గడ్డి రకాలు");
        feed_types.add("పప్పు జాతి గడ్డి రకాలు");
        feed_types.add("చెక్కలు");
        feed_types.add("ఉప ఉత్పత్తులు");
        feed_types.add("ఇతర మేత రకాలు");
        feed_types.add("ధాన్యపు గింజలు");
        feed_types.add("దాణ మిశ్రమము");
        feed_types.add("పప్పు జాతి ఎండు గడ్డి");
        feed_types.add("పాతర గడ్డి రకాలు(సైలేజ్)");

        ScrollView s1= new ScrollView(FeedMixture.this);
        s1.setFillViewport(true);

        LinearLayout l = new LinearLayout(FeedMixture.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(FeedMixture.this);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 50, 0,20);
        s.setLayoutParams(lp1);

        s.setPadding(0, 0, 0, 10);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,feed_types);
        s.setAdapter(adapter);
        s.setSelection(0);
        s.setTag("select");
        l.addView(s);
//        alertDialog.setView(s);
        final EditText input4 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp5.setMargins(0, 0, 0,30);
        input4.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input4.setSingleLine();
        input4.setLayoutParams(lp5);
        input4.setHint("మేత యొక్క పేరు నమోదు చెయ్యండి");
        input4.setTag("name");
        l.addView(input4);

        final EditText input = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0,30);
        input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input.setSingleLine();
        input.setLayoutParams(lp);
        input.setHint("మేత యొక్క మాంస కృత్తులు శాతం");
        input.setTag("cp");
        l.addView(input);



        final EditText input1 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(0, 0, 0,15);
        input1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input1.setSingleLine();
        input1.setLayoutParams(lp2);
        input1.setHint("మేత యొక్క  శక్తి శాతం");
        input1.setTag("tdn");
        l.addView(input1);

        final EditText input3 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 0, 0,20);
        input3.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input3.setSingleLine();
        input3.setLayoutParams(lp3);
        input3.setHint("మేత యొక్క  ఎండు పదార్థ శాతం");
        input3.setTag("dm");
        l.addView(input3);

        final EditText input5 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(0, 0, 0,20);
        input5.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input5.setSingleLine();
        input5.setLayoutParams(lp4);
        input5.setHint("మేత యొక్క  కాల్షియం శాతం");
        input5.setTag("ca");
        l.addView(input5);

        final EditText input6 = new EditText(FeedMixture.this);
        LinearLayout.LayoutParams lp51 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp51.setMargins(0, 0, 0,20);
        input6.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input6.setSingleLine();
        input6.setLayoutParams(lp51);
        input6.setHint("మేత యొక్క  ఫాస్పొరస్ శాతం");
        input6.setTag("p");
        l.addView(input6);

        s1.addView(l);
        alertDialog.setView(s1);

        alertDialog.setPositiveButton("జోడించు",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog f = (Dialog) dialog;
                        Spinner s = (Spinner) f.findViewById(R.id.welcome_text).findViewWithTag("select");
                        EditText ed = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("cp");
                        EditText ed1 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("tdn");
                        EditText ed2 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("dm");
                        EditText ed3 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("name");
                        EditText ed4 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("ca");
                        EditText ed5 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("p");


                        String file_name = "";
                        String selected = s.getSelectedItem().toString().toLowerCase();

                        if (selected.equalsIgnoreCase("ఎండు గడ్డి రకాలు")) {
                            file_name = "cereal_straws";
                        } else if (selected.equalsIgnoreCase("ధాన్యపు జాతి ఎండు గడ్డి")) {
                            file_name = "legume_straws";
                        } else if (selected.equalsIgnoreCase("పచ్చి గడ్డి రకాలు")) {
                            file_name = "native_and_improved_grasses";
                        } else if (selected.equalsIgnoreCase("పప్పు జాతి పచ్చి గడ్డి రకాలు")) {
                            file_name = "legume_variety";
                        } else if (selected.equalsIgnoreCase("చెక్కలు")) {
                            file_name = "cakes";
                        } else if (selected.equalsIgnoreCase("ఉప ఉత్పత్తులు")) {
                            file_name = "byproducts";
                        } else if (selected.equalsIgnoreCase("ఇతర మేత రకాలు")) {
                            file_name = "miscellaneous";
                        } else if (selected.equalsIgnoreCase("ధాన్యపు గింజలు")) {
                            file_name = "cereal_grains";
                        } else if (selected.equalsIgnoreCase("దాణ మిశ్రమము")) {
                            file_name = "concentrate_mixture";
                        } else if (selected.equalsIgnoreCase("కోసి యెండవేసినగడ్డి")) {
                            file_name = "hay";
                        } else if (selected.equalsIgnoreCase("పాతర గడ్డి రకాలు(సైలేజ్)")) {
                            file_name = "silage";
                        } else {
                            file_name = "";
                        }


                        final String to_be_written =  " ," + ed2.getText().toString() + "," + ed.getText().toString() + "," + ed1.getText().toString() + "," + ed4.getText().toString() + "," + ed5.getText().toString() +","+ ed3.getText().toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //////////////////////////Toast.makeText(getApplicationContext(),to_be_written,//////////////////////////Toast.LENGTH_LONG).show();
                            }
                        });

                        //Update the file.
                        if (file_name.equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //////////////////////////Toast.makeText(getApplicationContext(),"Please Select any feed type",//////////////////////////Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Writer output = null;
                            try {
                                output = new BufferedWriter(new FileWriter(static_path + "/" + file_name + ".txt", true));  //clears file every time
                                output.append("\n" + to_be_written);
                                output.close();
                                LinearLayout s1 = (LinearLayout) findViewById(R.id.linearLayout1);
                                s1.removeAllViews();
                                generate_feeds();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //////Toast.makeText(getApplicationContext(), "Feed Successfully added", //////Toast.LENGTH_LONG).show();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        alertDialog.setNegativeButton("రద్దు చేయు",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


        alertDialog.show();
    }

    private void syncAssets() {
        //////Toast.makeText(getApplicationContext(),"Entered copy assets fuunction in feed mixture",//////Toast.LENGTH_SHORT).show();
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        /*for(String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);

                String out1= Environment.getExternalStorageDirectory().getAbsolutePath();
                File outFile = new File(out1, filename);

                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
        }
*/

        for (String filename : files) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(assetManager.open(filename), "UTF-8"));
                Set<String> assetFileContents = new LinkedHashSet<>();
                String line;
                while((line = br.readLine()) != null){
                    assetFileContents.add(line);
                }
                br.close();

                File newFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
                if(! newFile.exists()){
                    newFile.createNewFile();
                }
                br = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename)));
                while((line = br.readLine()) != null){
                    if(!(line.isEmpty() || assetFileContents.contains(line))){
                        assetFileContents.add(line);
                    }
                }
                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename)));
                for(String eachLine : assetFileContents){
                    bw.write(eachLine+"\n");
                }
                bw.close();
            }
            catch(IOException ioe){

            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    public void getDirectory(){
try{
    static_path = Environment.getExternalStorageDirectory().getAbsolutePath();
    File f = new File(static_path+"/cakes.txt");
    if(f.exists()){
        file_found = true;
    }
}
catch(Exception e){

}
    }

    public boolean check_for_telugu(){

        try{
            static_path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File f = new File(static_path+"/cakes.txt");
            if(f.exists()){
                file_found = true;
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = "";
                while((line=br.readLine())!=null){
                    try{
                        String name = line.split(",")[6];
                        if(name==null){
                            return true;
                        }
                        else if(name.length()==0||name.equalsIgnoreCase("")){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    catch(Exception e){
                        return true;
                    }
                }
            }
            else{
                return true;
            }
        }
        catch(Exception e){
                return true;
        }
        return false;
    }

    public boolean check_for_english(){

        try{
            static_path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File f = new File(static_path+"/cakes.txt");
            if(f.exists()){
                file_found = true;
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = "";
                while((line=br.readLine())!=null){
                       try{
                           String name = line.split(",")[0];
                           if(name==null){
                               return true;
                           }
                           else if(name.length()==0||name.equalsIgnoreCase("")){
                               return true;
                           }
                           else{
                               return false;
                           }
                       }
                       catch(Exception e){
                           return true;
                       }
                }
            }
            else{
                return true;
            }
        }
        catch(Exception e){
                return true;
        }
        return false;
    }


    public void delete_new_feeds(View v){
        try {
            LinearLayout s1 = (LinearLayout) findViewById(R.id.linearLayout1);
            s1.removeAllViews();
            syncAssets();
            generate_feeds();
////////////////////////Toast.makeText(this,"Feeds Deleted successfully",////////////////////////Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){

        }

    }

    private class TextWatcherForFeeds implements TextWatcher{
        EditText editText = null;
        Map<String, FeedBean> feedMap = null;
        Context appContext = null;

        TextWatcherForFeeds(EditText editText, Map<String, FeedBean> feedMap, Context context){
            this.editText = editText;
            this.feedMap = feedMap;
            this.appContext = context;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            FeedBean feedBean = feedMap.get(this.editText.getTag().toString());
            double result;
            try{
                result = Double.parseDouble(editable.toString());
            }
            catch(NumberFormatException nbe){
                result = 0;
            }
            feedBean.setFeedWeight(result);
            feedMap.put(this.editText.getTag().toString(), feedBean);

            LinearLayout mainLayout = (LinearLayout)findViewById(R.id.dynamic_layout);
            LinearLayout mainTableLayout = (LinearLayout) mainLayout.findViewWithTag("subLinearLayout");
            mainLayout.removeView(mainTableLayout);

            prepareNutrientAndResultViews(mainLayout, strawMap, grassMap, concMixMap, specialFeedMap);
//            generateLayoutWith(strawMap, grassMap, concMixMap, specialFeedMap);
        }
    }
}

