package ration_formulation.feed_ration_latest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Bean.FeedBean;
import ration_formulation.feed_ration_latest.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Locale;

public class Feed_Ration extends Activity {
    private static final String TAG ="calculate" ;
    private Button button;
    String language = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed__ration);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            language = extras.getString("language");
            //Toast.makeText(getApplicationContext(),"in feed ration "+language,//Toast.LENGTH_LONG).show();
        }

        button = (Button) findViewById(R.id.button);

        if(language != null&&language.equals("Telugu")) {
            Spinner animal = (Spinner)findViewById(R.id.animal);
            ArrayList<String> spinnerArray = new ArrayList<String>();
            spinnerArray.add("ఆవు");
            spinnerArray.add("గేదె");
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            animal.setAdapter(spinnerArrayAdapter);

            EditText weight = (EditText) findViewById(R.id.weight);
            weight.setHint("బరువు ?");

            EditText milk_content = (EditText) findViewById(R.id.milk_content);
            milk_content.setHint("రోజువారీ పాల దిగుబడి (లీటర్స్)");

            EditText fat_content = (EditText) findViewById(R.id.fat_percent);
            fat_content.setHint("పాలులో వెన్న (శాతం)");

            EditText pregnancy = (EditText) findViewById(R.id.pregnancy);
            pregnancy.setHint("నెలలు (గర్భం)");

            Button b = (Button)findViewById(R.id.button);
            b.setText("మేత ఎంచుకోండి >>");

            EditText lactation = (EditText)findViewById(R.id.lactation);
            lactation.setHint("ఈత సంఖ్య");

            TextView t = (TextView)findViewById(R.id.welcome_text);
            t.setText("ఆవు లెదా గేదె ను ఎంచుకొని వివరాలను ఇవ్వండి");

            EditText t1 = (EditText) findViewById(R.id.FarmerName);
            t1.setHint("రైతు పేరు");

            t1 = (EditText) findViewById(R.id.FarmerAddress);
            t1.setHint("రైతు చిరునామా");

            t1 = (EditText) findViewById(R.id.AnimalNumber);
            t1.setHint("పశువు గుర్తింపు సంఖ్య");

            t.setHeight(80);
        }
            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed__ration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        try {
            //Disable keypad.
            View view1 = this.getCurrentFocus();
            if (view1 != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            Log.d(TAG, "in calculate");
            ////Toast.makeText(getApplicationContext(), "Button is clicked", ////Toast.LENGTH_LONG).show();
            EditText mEdit,lactation,fname,faddress,anumber;
            Spinner animal;
            String farmerName="",farmerAddress="",animalNumber="",Weight="";
            fname = (EditText) findViewById(R.id.FarmerName);
            farmerName = fname.getText().toString();
            if(farmerName==null||farmerName.equals("")){
                TextView frameNull = (TextView)findViewById(R.id.FarmerNameNull);
                frameNull.setVisibility(View.VISIBLE);
                return;
            }
            else{
                TextView frameNull = (TextView)findViewById(R.id.FarmerNameNull);
                frameNull.setVisibility(View.GONE);
            }

            faddress = (EditText) findViewById(R.id.FarmerAddress);
            farmerAddress = faddress.getText().toString();
            if(farmerAddress==null||farmerAddress.equals("")){
                TextView frameNull = (TextView)findViewById(R.id.FarmerAddressNull);
                frameNull.setVisibility(View.VISIBLE);
                return;
            }
            else{
                TextView frameNull = (TextView)findViewById(R.id.FarmerAddressNull);
                frameNull.setVisibility(View.GONE);
            }

            anumber = (EditText) findViewById(R.id.AnimalNumber);
            animalNumber = anumber.getText().toString();
            if(animalNumber==null||animalNumber.equals("")){
                TextView fnameNull = (TextView)findViewById(R.id.AnimalNumberNull);
                fnameNull.setVisibility(View.VISIBLE);
                return;
            }
            else{
                TextView frameNull = (TextView)findViewById(R.id.AnimalNumberNull);
                frameNull.setVisibility(View.GONE);
            }

            mEdit = (EditText) findViewById(R.id.weight);
            Weight = mEdit.getText().toString();
            if(Weight==null||Weight.equals("")){
                TextView fnameNull = (TextView)findViewById(R.id.WeightNull);
                fnameNull.setVisibility(View.VISIBLE);
                return;
            }
            else{
                TextView frameNull = (TextView)findViewById(R.id.WeightNull);
                frameNull.setVisibility(View.GONE);
            }

            animal = (Spinner) findViewById(R.id.animal);
            double body_weight = 0;
            double body_weight_scaled = 0;
            try {
                body_weight = Double.parseDouble(mEdit.getText().toString());
                body_weight_scaled = Math.pow(body_weight, 0.75);
            } catch (Exception e) {
                body_weight = 0;
                body_weight_scaled = 0;
            }
            Log.d(TAG, "in calculate1");
            double DM = 0, TDN = 0, CP = 0, CA = 0, P = 0;
            String DM_str = null, TDN_str = null, CP_str = null, CA_str = null, P_str = null, milk_str = null, weight_animal = "";

            weight_animal = Double.toString(body_weight);
            DM += body_weight * 0.02;
            CP += body_weight_scaled * 4.87;
            TDN += body_weight_scaled * 37.2;
            CA += body_weight * 31;
            P += body_weight * 20;
            mEdit = (EditText) findViewById(R.id.milk_content);
            double milk_content = 0;
            try {
                milk_content = Double.parseDouble(mEdit.getText().toString());
            } catch (Exception e) {
                milk_content = 0;
            }
            mEdit = (EditText) findViewById(R.id.fat_percent);
            double fat_content = 0;
            try {
                fat_content = Double.parseDouble(mEdit.getText().toString());
            } catch (Exception e) {
                fat_content = 0;
            }
            double fat_kg = (double) (milk_content * fat_content / 100);
            double FCM = (double) (0.4 * milk_content + 15 * fat_kg);
            Log.d(TAG, "in calculate2");
            String animal_name = "";
            try {
                animal_name = animal.getSelectedItem().toString();
            } catch (Exception e) {
                //Toast.makeText(getApplicationContext(), "Animal Name Exception", //Toast.LENGTH_LONG).show();
                animal_name = "";
            }
            Log.d(TAG, "in calculate3");
            mEdit = (EditText) findViewById(R.id.pregnancy);
            int pregnancy = 0;
            try {
                pregnancy = Integer.parseInt(mEdit.getText().toString());
            } catch (Exception e) {
                pregnancy = 0;
            }

            lactation = (EditText) findViewById(R.id.lactation);
            String lactation_number = lactation.getText().toString();
            int lactation_number_count = -1;

            boolean lactation_flag = false;
            try {
                lactation_number_count = Integer.parseInt(lactation_number);
                lactation_flag = true;
            } catch (Exception e) {
                lactation_flag = false;
            }

            Log.d(TAG, "in calculate4");
            if (animal_name.equals("Cow") || animal_name.equals("ఆవు")) {
                ////Toast.makeText(this, "Entered if",////Toast.LENGTH_SHORT).show();
                Log.d(TAG, "in calculate5");
                if (lactation_flag) {
                    if (lactation_number_count == 1) {
                        DM *= 1.2;
                        CP *= 1.2;
                        TDN *= 1.2;
                        CA *= 1.2;
                        P *= 1.2;
                    } else if (lactation_number_count == 2) {
                        DM *= 1.1;
                        CP *= 1.1;
                        TDN *= 1.1;
                        CA *= 1.1;
                        P *= 1.1;
                    }
                }
                Log.d(TAG, "in calculate6");
                DM += FCM * 0.51;
                CP += milk_content * 96;
                TDN += FCM * 330;
                CA += milk_content * 1230;
                P += milk_content * 900;
                if (pregnancy >= 7) {
                    CP += 200;
                    TDN += 750;
                }
            } else {
                if (lactation_flag) {
                    if (lactation_number_count == 1) {
                        DM *= 1.2;
                        CP *= 1.2;
                        TDN *= 1.2;
                        CA *= 1.2;
                        P *= 1.2;
                    } else if (lactation_number_count == 2) {
                        DM *= 1.1;
                        CP *= 1.1;
                        TDN *= 1.1;
                        CA *= 1.1;
                        P *= 1.1;
                    }
                }
                Log.d(TAG, "in calculate7");
                DM += FCM * 0.55;
                CP += milk_content * 124;
                TDN += FCM * 0.92 * 360;
                CA += milk_content * 1840;
                P += milk_content * 900;
                if (pregnancy >= 8) {
                    CP += 200;
                    TDN += 750;
                }
            }

            if (milk_content == 0.0) {
                //Toast.makeText(getApplicationContext(), "Entered for zero milk content", //Toast.LENGTH_LONG).show();
                CA = (double) 15.4 * body_weight;
                //Toast.makeText(getApplicationContext(), Double.toString(CA), //Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "in calculate8");
            TDN = (double) TDN / 1000;
            CP = (double) CP / 1000;
            CA = (double) CA / 1000000;
            P = (double) P / 1000000;
            DM = (double) Math.round(DM * 100) / 100;
            CA = (double) Math.round(CA * 1000) / 1000;
            TDN = (double) Math.round(TDN * 100) / 100;
            CP = (double) Math.round(CP * 100) / 100;
            P = (double) Math.round(P * 1000) / 1000;
            // TableLayout table_layout = (TableLayout)findViewById(R.id.data_table);
            Log.d(TAG, "in calculate9");
            DM_str = Double.toString(DM);
            CP_str = Double.toString(CP);
            TDN_str = Double.toString(TDN);
            CA_str = Double.toString(CA);
            P_str = Double.toString(P);
            milk_str = Double.toString(milk_content);
           // Intent i = new Intent(Feed_Ration.this, results.class);
            Intent i = new Intent(Feed_Ration.this, FeedMixture.class);
            Log.d(TAG, "in calculate10");

            i.putExtra("fatContent",Double.toString(fat_content));
            i.putExtra("DM", DM_str);
            i.putExtra("CP", CP_str);
            i.putExtra("TDN", TDN_str);
            i.putExtra("CA", CA_str);
            i.putExtra("P", P_str);
            i.putExtra("milk", milk_str);
            i.putExtra("body_weight", weight_animal);
            i.putExtra("language", language);
            i.putExtra("FarmerName",farmerName);
            i.putExtra("FarmerAddress",farmerAddress);
            i.putExtra("AnimalNumber",animalNumber);
            i.putExtra("AnimalType",animal_name);

            Log.d("Feed Ration First", "in calculate11");
            Log.d("Feed Ration First", i.toString());
            Toast.makeText(getApplicationContext(),"Sending fat content : "+fat_content,Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();


     /*   for (int i = 1; i <= 5; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 1; j <= 5; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
              //  tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setPadding(5, 5, 5, 5);
                tv.setText("R " + i + ", C" + j);

                row.addView(tv);

            }

            table_layout.addView(row);

        }*/

        }
        catch(Exception e){
            //Toast.makeText(getApplicationContext(),"Exception occurred",//Toast.LENGTH_LONG).show();
        }
    }
    public void get_farmer_detail(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Calling new method", Toast.LENGTH_LONG).show();

            }
        });
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Feed_Ration.this, AlertDialog.THEME_HOLO_LIGHT);
        alertDialog.setTitle("Cattle Info");

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.farmer_detail_alert, null);
        alertDialog.setView(dialogView);

        ArrayList<String> farmers = new ArrayList<String>();

        //Get Farmers from file.
        farmers = get_farmers();
        Spinner s = (Spinner) dialogView.findViewById(R.id.farmerNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,farmers);
        s.setAdapter(adapter);
        s.setSelection(0);

        alertDialog.setPositiveButton("Add/Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog f = (Dialog) dialog;

                        Spinner s = (Spinner) dialogView.findViewById(R.id.farmerNames);
                        EditText ed = (EditText) dialogView.findViewById(R.id.farmerName);
                        EditText ed1 = (EditText) dialogView.findViewById(R.id.address);
                        EditText ed2 = (EditText) dialogView.findViewById(R.id.animal_number);

                        if(ed.getText().toString()==null||ed.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter Farmers name", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        if(ed1.getText().toString()==null||ed1.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter valid address", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        if(ed2.getText().toString()==null||ed2.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter valid number", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        String result = "";
                        result+=ed.getText().toString()+";;";
                        result+=ed.getText().toString()+";;";
                        result+=ed.getText().toString();

                        //Create suitable directory.
                        File folder = new File(Environment.getExternalStorageDirectory() +
                                File.separator + "Ration Formulator");
                        boolean success = true;
                        if (!folder.exists()) {
                            success = folder.mkdir();

                        }
                        File result_file = null;
                        if(success) {
                            Log.d("Folder", "Created");
                            result_file = new File(folder.getAbsolutePath()+File.separator+"farmers_info.txt");
                            if(!result_file.exists()) {
                                try {
                                    result_file.createNewFile();
                                    Log.d("File", "Created");
                                }
                                catch(IOException e)
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Farmer Details could not be added successfully", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    Log.e("error",e.toString());
                                    return;
                                }
                            }
                        }
                        else
                            return;

                        Writer output = null;
                        try {
                            output = new BufferedWriter(new FileWriter(result_file.getAbsolutePath(), true));  //clears file every time
                            output.append("\n" + result);
                            output.close();


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Farmer Details added successfully", Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("error",e.toString());
                        }


                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = alertDialog.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long row_id) {
                if(position==0)
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Default Selected", Toast.LENGTH_LONG).show();

                        }
                    });
                else{
                    final String farmer_name = parent.getItemAtPosition(position).toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Selected : "+farmer_name, Toast.LENGTH_LONG).show();
                        }
                    });
                    final String farmer_detail = populate_details(farmer_name);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Farmer details : "+farmer_detail, Toast.LENGTH_LONG).show();
                        }
                    });
                    EditText editText = null;
                    editText = (EditText) dialogView.findViewById(R.id.farmerName);
                    editText.setText(farmer_detail.split(";;")[0]);

                    editText = (EditText) dialogView.findViewById(R.id.address);
                    editText.setText(farmer_detail.split(";;")[1]);

                    editText = (EditText) dialogView.findViewById(R.id.animal_number);
                    editText.setText(farmer_detail.split(";;")[2]);

                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setText("OK");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
    }
    public void get_farmer_details(View v) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Feed_Ration.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        alertDialog.setTitle("Cattle Info");

        final LinearLayout l = new LinearLayout(Feed_Ration.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        ArrayList<String> farmers = new ArrayList<String>();

        //Get Farmers from file.
        farmers = get_farmers();
        Spinner s = new Spinner(Feed_Ration.this);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 50, 0,20);
        s.setLayoutParams(lp1);

        s.setPadding(0, 0, 0, 10);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,farmers);
        s.setAdapter(adapter);
        s.setSelection(0);
        s.setTag("farmers_info");
        l.addView(s);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long row_id) {
                if(position==0)
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Default Selected", Toast.LENGTH_LONG).show();

                        }
                    });
                else{
                    final String farmer_name = parent.getItemAtPosition(position).toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Selected : "+farmer_name, Toast.LENGTH_LONG).show();
                        }
                    });
                    final String farmer_detail = populate_details(farmer_name);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Farmer details : "+farmer_detail, Toast.LENGTH_LONG).show();
                        }
                    });
                    LayoutInflater myLayout = LayoutInflater.from(getApplicationContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        final EditText input4 = new EditText(Feed_Ration.this);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp5.setMargins(0, 0, 0,30);
        input4.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input4.setSingleLine();
        input4.setLayoutParams(lp5);
        input4.setHint("Farmers Name");
        input4.setInputType(InputType.TYPE_CLASS_TEXT);
        input4.setTag("name");
        l.addView(input4);

        final EditText input5 = new EditText(Feed_Ration.this);
        LinearLayout.LayoutParams lp6 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp6.setMargins(0, 0, 0,30);
        input5.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input5.setSingleLine();
        input5.setLayoutParams(lp5);
        input5.setHint("Address");
        input5.setInputType(InputType.TYPE_CLASS_TEXT);
        input5.setTag("address");
        l.addView(input5);

        final EditText input6 = new EditText(Feed_Ration.this);
        LinearLayout.LayoutParams lp7 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp7.setMargins(0, 0, 0,30);
        input6.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input6.setSingleLine();
        input6.setLayoutParams(lp5);
        input6.setHint("Animal Number");
        input6.setInputType(InputType.TYPE_CLASS_TEXT);
        input6.setTag("number");
        l.addView(input6);

        alertDialog.setView(l);
        Log.d("Layout created","Layout created1");
        alertDialog.setPositiveButton("Add/Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog f = (Dialog) dialog;
                        Spinner s = (Spinner) f.findViewById(R.id.welcome_text).findViewWithTag("farmers_info");
                        EditText ed = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("name");
                        EditText ed1 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("address");
                        EditText ed2 = (EditText) f.findViewById(R.id.welcome_text).findViewWithTag("number");

                        if(ed.getText().toString()==null||ed.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter Farmers name", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        if(ed1.getText().toString()==null||ed1.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter valid address", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        if(ed2.getText().toString()==null||ed2.getText().toString().equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Pls enter valid number", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;
                        }

                        String result = "";
                        result+=ed.getText().toString()+";;";
                        result+=ed.getText().toString()+";;";
                        result+=ed.getText().toString();

                        //Create suitable directory.
                        File folder = new File(Environment.getExternalStorageDirectory() +
                                File.separator + "Ration Formulator");
                        boolean success = true;
                        if (!folder.exists()) {
                            success = folder.mkdir();

                        }
                        File result_file = null;
                        if(success) {
                            Log.d("Folder", "Created");
                            result_file = new File(folder.getAbsolutePath()+File.separator+"farmers_info.txt");
                            if(!result_file.exists()) {
                                try {
                                    result_file.createNewFile();
                                    Log.d("File", "Created");
                                }
                                catch(IOException e)
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Farmer Details could not be added successfully", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    Log.e("error",e.toString());
                                    return;
                                }
                            }
                        }
                        else
                            return;

                        Writer output = null;
                        try {
                            output = new BufferedWriter(new FileWriter(result_file.getAbsolutePath(), true));  //clears file every time
                            output.append("\n" + result);
                            output.close();


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Farmer Details added successfully", Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("error",e.toString());
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

    private String populate_details(String farmer_name) {
        ArrayList<String> farmers = new ArrayList<String>();
        String result ="";
        File farmers_file = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Ration Formulator"+File.separator+"farmers_info.txt");
        if(farmers_file.exists()){
            Log.d("File","Exists");
            String sCurrentLine;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(farmers_file.getAbsolutePath()));
                while ((sCurrentLine = br.readLine()) != null) {
                    String line = sCurrentLine;
                    if(line.toLowerCase().contains(farmer_name.toLowerCase())){
                        result = line;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private ArrayList<String> get_farmers() {
        ArrayList<String> farmers = new ArrayList<String>();
        File farmers_file = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Ration Formulator"+File.separator+"farmers_info.txt");
        if(farmers_file.exists()){
            Log.d("File","Exists");
            String sCurrentLine;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(farmers_file.getAbsolutePath()));
                while ((sCurrentLine = br.readLine()) != null) {
                    String line = sCurrentLine;
                    Log.d("Line",line);
                    String contents[] = line.split(";;");
                    if(contents.length>0) {
                        if(!(contents[0]==null||contents[0].equals("")))
                            farmers.add(contents[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        farmers.add(0,"Select Farmer");
        Log.d("Returning",farmers.toString());
        return farmers;
    }

    public void onBackPressed() {
        // super.onBackPressed();
        Intent i = new Intent(Feed_Ration.this, Ration_Formulator.class).putExtra("language",language);
        startActivity(i);
        finish();
    }
}
