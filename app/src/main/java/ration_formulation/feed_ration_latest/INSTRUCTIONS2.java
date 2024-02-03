package ration_formulation.feed_ration_latest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;


public class INSTRUCTIONS2 extends Activity {
    public final String language = "Telugu";
    String static_path = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructions2, menu);
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

    public void onBackPressed() {
        // super.onBackPressed();
        Intent i = new Intent(INSTRUCTIONS2.this, Ration_Formulator.class).putExtra("language",language);
        startActivity(i);
        finish();
    }

    public void add_new_feed_telugu(View v){
        getDirectory();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(INSTRUCTIONS2.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



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

        ScrollView s1= new ScrollView(INSTRUCTIONS2.this);
        s1.setFillViewport(true);

        LinearLayout l = new LinearLayout(INSTRUCTIONS2.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(INSTRUCTIONS2.this);
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
        final EditText input4 = new EditText(INSTRUCTIONS2.this);
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

        final EditText input = new EditText(INSTRUCTIONS2.this);
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



        final EditText input1 = new EditText(INSTRUCTIONS2.this);
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

        final EditText input3 = new EditText(INSTRUCTIONS2.this);
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

        final EditText input5 = new EditText(INSTRUCTIONS2.this);
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

        final EditText input6 = new EditText(INSTRUCTIONS2.this);
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
                                output = new BufferedWriter(new FileWriter(static_path + "/" + file_name + ".txt", true));  //clears file every time
                                output.append("\n" + to_be_written);
                                output.close();

                                
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(getApplicationContext(), "Feed Successfully added", //Toast.LENGTH_LONG).show();
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
    
    public void getDirectory() {
        try {
            static_path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File f = new File(static_path + "/cakes.txt");
            if (f.exists()) {
                //Ok file is there.
            } else {
                //Copy the assets folder.
                copyAssets();
            }
        } catch (Exception e) {

        }
    }

    private void copyAssets() {
        ////Toast.makeText(getApplicationContext(),"Entered copy assets fuunction",////Toast.LENGTH_SHORT).show();
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        for(String filename : files) {
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
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
