package ration_formulation.feed_ration_latest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
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

import ration_formulation.feed_ration_latest.R;


public class INSTRUCTIONS1 extends Activity {
    public final String language = "English";
    String static_path = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructions1, menu);
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

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent i = new Intent(INSTRUCTIONS1.this, Ration_Formulator.class).putExtra("language",language);
        startActivity(i);
        finish();
    }

    public void add_new_feed(View v){
        getDirectory();
       
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(INSTRUCTIONS1.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);



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

        ScrollView s1= new ScrollView(INSTRUCTIONS1.this);
        s1.setFillViewport(true);



        LinearLayout l = new LinearLayout(INSTRUCTIONS1.this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(0, 10, 0, 10);
        l.setId(R.id.welcome_text);
        l.setBackgroundColor(Color.WHITE);

        Spinner s = new Spinner(INSTRUCTIONS1.this);
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
        final EditText input4 = new EditText(INSTRUCTIONS1.this);
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

        final EditText input = new EditText(INSTRUCTIONS1.this);
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



        final EditText input1 = new EditText(INSTRUCTIONS1.this);
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

        final EditText input3 = new EditText(INSTRUCTIONS1.this);
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

        final EditText input5 = new EditText(INSTRUCTIONS1.this);
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

        final EditText input6 = new EditText(INSTRUCTIONS1.this);
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
                                output = new BufferedWriter(new FileWriter(static_path + "/" + file_name + ".txt", true));  //clears file every time
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

    public void getDirectory() {
        try {
            static_path = Environment.getExternalStorageDirectory().getAbsolutePath();
            Toast.makeText(getApplicationContext(),static_path,Toast.LENGTH_LONG).show();
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
        Toast.makeText(getApplicationContext(),"Entered copy assets fuunction",Toast.LENGTH_LONG).show();
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
