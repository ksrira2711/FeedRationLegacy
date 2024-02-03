package ration_formulation.feed_ration_latest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import ration_formulation.feed_ration_latest.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Ration_Formulator extends Activity {
    private static final long DELAY = 3000;
    private boolean scheduled = false;
    private Timer splashTimer;
    String language = "";
    Menu menu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration_formulator);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        final Spinner s = (Spinner) findViewById(R.id.language);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String language_input = extras.getString("language");
            if(language_input!=null){
                if (language_input.contains("English")) {
                    s.setSelection(1);
                } else if (language_input.contains("Telugu")) {
                    s.setSelection(2);
                } else {
                    s.setSelection(0);
                }
            }

            ////Toast.makeText(getApplicationContext(),"in feed ration "+language,////Toast.LENGTH_LONG).show();
        }


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                String language_selected = s.getSelectedItem().toString();
                if (language_selected.contains("English")) {
                    language = "English";
                    update(language);
                    updateMenuTitles(language);
                    setLocale("en");
                } else if (language_selected.contains("Telugu")) {
                    language = "Telugu";
                    update(language);
                    updateMenuTitles(language);
                    setLocale("te");
                } else {
                    language = "N";
                    update(language);
                    updateMenuTitles(language);
                }
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                //Toast.makeText(getApplicationContext(), "Nothing selected", //Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Ration_Formulator.class);
        startActivity(refresh);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.previousReports:
                Intent i = new Intent(Ration_Formulator.this,Reports.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
//        splashTimer.purge();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ration_formulator_activity, menu);
        this.menu = menu;
        return true;
    }

    private void updateMenuTitles(String language) {
        MenuItem reports = menu.findItem(R.id.previousReports);
        if (language.equals("Telugu")) {
            reports.setTitle("నివేదికలు చూడండి");
        }
        if (language.equals("English")) {
            reports.setTitle("View Reports");
        }
    }

    public void get_instructions(View v){
        //////Toast.makeText(getApplicationContext(),"Instructions not fed yet",//////Toast.LENGTH_LONG).show();
        if(language.equalsIgnoreCase("english")) {
            splashTimer = new Timer();
            splashTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Ration_Formulator.this.finish();
                    startActivity(new Intent(Ration_Formulator.this, INSTRUCTIONS1.class));
                }
            }, 100);
            scheduled = true;
        }
        else{
            splashTimer = new Timer();
            splashTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Ration_Formulator.this.finish();
                    startActivity(new Intent(Ration_Formulator.this, INSTRUCTIONS2.class));
                }
            }, 100);
            scheduled = true;
        }
    }

    public void go_to_app(View v){
        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Ration_Formulator.this.finish();
                startActivity(new Intent(Ration_Formulator.this, Feed_Ration.class).putExtra("language",language));
            }
        }, 100);
        scheduled = true;
    }

    public void update(String language){
        Button b = (Button)findViewById(R.id.button6);
        Button c = (Button)findViewById(R.id.button7);
        Button d = (Button)findViewById(R.id.button8);

        //Toast.makeText(getApplicationContext(),"Language update function",//Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"Language is : "+language,//Toast.LENGTH_LONG).show();

        if(language.equals("English")){
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
            d.setVisibility(View.VISIBLE);

            b.setText("About the APP");
            c.setText("START >>");
            d.setText("Add Feed(+)");

            b.setAlpha(1);
            c.setAlpha(1);
            d.setAlpha(1);

            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    add_new_feed(view);
                }
            });

            ImageView image = (ImageView)findViewById(R.id.imageView1);
            image.setVisibility(View.GONE);
        }
        else if(language.equals("Telugu")){
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
            d.setVisibility(View.VISIBLE);

            b.setAlpha(1);
            c.setAlpha(1);
            d.setAlpha(1);

            b.setText("సూచనలు");
            c.setText("ప్రారంభం >>");
            d.setText("కొత్త మేత జోడించు(+)");
            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    add_new_feed_telugu(view);
                }
            });

            ImageView image = (ImageView)findViewById(R.id.imageView1);
            image.setVisibility(View.GONE);
        }
        else{
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);

            ImageView image = (ImageView)findViewById(R.id.imageView1);
            image.setVisibility(View.VISIBLE);
        }
    }
    public void add_new_feed(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Ration_Formulator.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



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

        ScrollView s1= new ScrollView(Ration_Formulator.this);
        s1.setFillViewport(true);



        LinearLayout l = new LinearLayout(Ration_Formulator.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(Ration_Formulator.this);
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
        final EditText input4 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp5.setMargins(0, 0, 0,30);
        input4.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input4.setSingleLine();
        input4.setLayoutParams(lp5);
        input4.setHint("Enter the Name of the feed");
        input4.setInputType(InputType.TYPE_CLASS_TEXT);
        input4.setTag("name");
        l.addView(input4);
        int type = InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL;

        final EditText input = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0,30);
        input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input.setSingleLine();
        input.setLayoutParams(lp);
        input.setHint("Enter Protein Content(%cp)");
        input.setInputType(type);
        input.setTag("cp");
        l.addView(input);



        final EditText input1 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(0, 0, 0,15);
        input1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input1.setSingleLine();
        input1.setLayoutParams(lp2);
        input1.setHint("Enter TDN(%TDN)");
        input1.setInputType(type);
        input1.setTag("tdn");
        l.addView(input1);

        final EditText input3 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 0, 0,20);
        input3.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input3.setSingleLine();
        input3.setLayoutParams(lp3);
        input3.setHint("Enter Dry Matter Content(%dm)");
        input3.setInputType(type);
        input3.setTag("dm");
        l.addView(input3);

        final EditText input5 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(0, 0, 0,20);
        input5.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input5.setSingleLine();
        input5.setLayoutParams(lp4);
        input5.setHint("Enter Calcium Content(%Ca)");
        input5.setInputType(type);
        input5.setTag("ca");
        l.addView(input5);

        final EditText input6 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp51 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp51.setMargins(0, 0, 0,20);
        input6.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input6.setSingleLine();
        input6.setLayoutParams(lp51);
        input6.setHint("Enter Phosphorous Content(%p)");
        input6.setInputType(type);
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
                                ////////////////////Toast.makeText(getApplicationContext(),to_be_written,////////////////////Toast.LENGTH_LONG).show();
                            }
                        });

                        //Update the file.
                        if (file_name.equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ////////////////////Toast.makeText(getApplicationContext(),"Please Select any feed type",////////////////////Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Writer output = null;
                            try {
                                output = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file_name + ".txt", true));  //clears file every time
                                output.append("\n" + to_be_written);
                                output.close();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Feed Successfully added", Toast.LENGTH_LONG).show();
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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Ration_Formulator.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



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

        ScrollView s1= new ScrollView(Ration_Formulator.this);
        s1.setFillViewport(true);

        LinearLayout l = new LinearLayout(Ration_Formulator.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(Ration_Formulator.this);
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
        final EditText input4 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp5.setMargins(0, 0, 0,30);
        input4.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input4.setSingleLine();
        input4.setLayoutParams(lp5);
        input4.setHint("మేత యొక్క పేరు నమోదు చెయ్యండి");
        input4.setInputType(InputType.TYPE_CLASS_TEXT);
        input4.setTag("name");
        l.addView(input4);
        int type = InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL;
        final EditText input = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0,30);
        input.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input.setSingleLine();
        input.setLayoutParams(lp);
        input.setHint("మేత యొక్క మాంస కృత్తులు శాతం");
        input.setInputType(type);
        input.setTag("cp");
        l.addView(input);



        final EditText input1 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(0, 0, 0,15);
        input1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input1.setSingleLine();
        input1.setLayoutParams(lp2);
        input1.setHint("మేత యొక్క  శక్తి శాతం");
        input1.setInputType(type);
        input1.setTag("tdn");
        l.addView(input1);

        final EditText input3 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0, 0, 0,20);
        input3.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input3.setSingleLine();
        input3.setLayoutParams(lp3);
        input3.setHint("మేత యొక్క  ఎండు పదార్థ శాతం");
        input3.setInputType(type);
        input3.setTag("dm");
        l.addView(input3);

        final EditText input5 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp4.setMargins(0, 0, 0,20);
        input5.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        input5.setSingleLine();
        input5.setLayoutParams(lp4);
        input5.setHint("మేత యొక్క  కాల్షియం శాతం");
        input5.setInputType(type);
        input5.setTag("ca");
        l.addView(input5);

        final EditText input6 = new EditText(Ration_Formulator.this);
        LinearLayout.LayoutParams lp51 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp51.setMargins(0, 0, 0,20);
        input6.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input6.setSingleLine();
        input6.setLayoutParams(lp51);
        input6.setHint("మేత యొక్క  ఫాస్పొరస్ శాతం");
        input6.setInputType(type);
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
                                //////////////////////Toast.makeText(getApplicationContext(),to_be_written,//////////////////////Toast.LENGTH_LONG).show();
                            }
                        });

                        //Update the file.
                        if (file_name.equals("")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //////////////////////Toast.makeText(getApplicationContext(),"Please Select any feed type",//////////////////////Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Writer output = null;
                            try {
                                output = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file_name + ".txt", true));  //clears file every time
                                output.append("\n" + to_be_written);
                                output.close();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Feed Successfully added", Toast.LENGTH_LONG).show();
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

}
