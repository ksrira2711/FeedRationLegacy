package ration_formulation.feed_ration_latest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ration_formulation.feed_ration_latest.R;

public class results extends Feed_Ration {

    String DM_str=null,TDN_str=null,CP_str=null,CA_str=null,P_str=null,milk_str=null,weight_animal="";
String language = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
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
        }
        //Toast.makeText(getApplicationContext(),"Button is clicked and intent is working", //Toast.LENGTH_LONG).show();

        if(language!=null&&language.equalsIgnoreCase("english")) {
            TableLayout data = (TableLayout) findViewById(R.id.data_table);
            data.setStretchAllColumns(true);
            data.bringToFront();
            TableRow tr = new TableRow(this);
            // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            //  tr.setLayoutParams(buttonLayoutParams);
            tr.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            TextView c1 = new TextView(this);
            c1.setText("NUTRIENTS");
            //  c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            TextView c2 = new TextView(this);
            c2.setText("REQUIREMENTS (KG)");
            // c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);
            // String text = "sjdfbjskdbfjksdfb";

            tr = new TableRow(this);
            //  RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            // tr.setLayoutParams(buttonLayoutParams);
            tr.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1 = new TextView(this);
            c1.setText("DRY MATTER (DM)");
            // c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            c2 = new TextView(this);
            c2.setText(DM_str);
            // c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);

            tr = new TableRow(this);
            // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            //   tr.setLayoutParams(buttonLayoutParams);
            //  tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
            tr.setBackgroundResource(R.drawable.border);
            c1 = new TextView(this);
            c1.setText("CP");
            //  c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            c2 = new TextView(this);
            c2.setText(CP_str);
            // c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);


            tr = new TableRow(this);
            // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            // tr.setLayoutParams(buttonLayoutParams);
            // tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
            tr.setBackgroundResource(R.drawable.border);
            c1 = new TextView(this);
            c1.setText("TDN");
            // c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            c2 = new TextView(this);
            c2.setText(TDN_str);
            // c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);

            tr = new TableRow(this);
            // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            //  tr.setLayoutParams(buttonLayoutParams);
            //   tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
            tr.setBackgroundResource(R.drawable.border);
            c1 = new TextView(this);
            c1.setText("CALCIUM (Ca)");
            //  c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            c2 = new TextView(this);
            c2.setText(CA_str);
            //  c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);

            tr = new TableRow(this);
            // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //  buttonLayoutParams.setMargins(5, 5, 5, 5);
            //  tr.setLayoutParams(buttonLayoutParams);
            //  tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
            tr.setBackgroundResource(R.drawable.border);
            c1 = new TextView(this);
            c1.setText("PHOSPHOROUS (P)");
            //  c1.setLayoutParams(buttonLayoutParams);
            c1.setGravity(Gravity.LEFT);
            c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c1.setTextColor(Color.parseColor("#ff183bff"));
            c2 = new TextView(this);
            c2.setText(P_str);
            //  c2.setLayoutParams(buttonLayoutParams);
            c2.setGravity(Gravity.CENTER);
            c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            c2.setTextColor(Color.parseColor("#ff183bff"));
            tr.addView(c1);
            tr.addView(c2);
            data.addView(tr);
            data.setVisibility(View.VISIBLE);
        }
        else if(language!=null&&language.equalsIgnoreCase("telugu")){
            {
                TableLayout data = (TableLayout) findViewById(R.id.data_table);
                data.setStretchAllColumns(true);
                data.bringToFront();
                TableRow tr = new TableRow(this);
                // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                //  tr.setLayoutParams(buttonLayoutParams);
                tr.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                TextView c1 = new TextView(this);
                c1.setText("పోషకాలు");
                //  c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                TextView c2 = new TextView(this);
                c2.setText("అవసరాలు (కిలొ)");
                // c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);
                // String text = "sjdfbjskdbfjksdfb";

                tr = new TableRow(this);
                //  RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                // tr.setLayoutParams(buttonLayoutParams);
                tr.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1 = new TextView(this);
                c1.setText("ఎండు పదార్థం (DM)");
                // c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                c2 = new TextView(this);
                c2.setText(DM_str);
                // c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);

                tr = new TableRow(this);
                // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                //   tr.setLayoutParams(buttonLayoutParams);
                //  tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
                tr.setBackgroundResource(R.drawable.border);
                c1 = new TextView(this);
                c1.setText("ప్రోటీన్");
                //  c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                c2 = new TextView(this);
                c2.setText(CP_str);
                // c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);


                tr = new TableRow(this);
                // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                // tr.setLayoutParams(buttonLayoutParams);
                // tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
                tr.setBackgroundResource(R.drawable.border);
                c1 = new TextView(this);
                c1.setText("శక్తి");
                // c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                c2 = new TextView(this);
                c2.setText(TDN_str);
                // c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);

                tr = new TableRow(this);
                // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                //  tr.setLayoutParams(buttonLayoutParams);
                //   tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
                tr.setBackgroundResource(R.drawable.border);
                c1 = new TextView(this);
                c1.setText("కాల్షియం (Ca)");
                //  c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                c2 = new TextView(this);
                c2.setText(CA_str);
                //  c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);

                tr = new TableRow(this);
                // RelativeLayout.LayoutParams buttonLayoutParams = new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //  buttonLayoutParams.setMargins(5, 5, 5, 5);
                //  tr.setLayoutParams(buttonLayoutParams);
                //  tr.setBackgroundColor(Color.parseColor("#FFFF1A00"));
                tr.setBackgroundResource(R.drawable.border);
                c1 = new TextView(this);
                c1.setText("భాస్వరం (P)");
                //  c1.setLayoutParams(buttonLayoutParams);
                c1.setGravity(Gravity.LEFT);
                c1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c1.setTextColor(Color.parseColor("#ff183bff"));
                c2 = new TextView(this);
                c2.setText(P_str);
                //  c2.setLayoutParams(buttonLayoutParams);
                c2.setGravity(Gravity.CENTER);
                c2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                c2.setTextColor(Color.parseColor("#ff183bff"));
                tr.addView(c1);
                tr.addView(c2);
                data.addView(tr);
                data.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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

    public void feed_calculate(View view) {
        Intent i = new Intent(results.this, FeedMixture.class);
        i.putExtra("DM", DM_str);
        i.putExtra("CP", CP_str);
        i.putExtra("TDN", TDN_str);
        i.putExtra("CA", CA_str);
        i.putExtra("P", P_str);
        i.putExtra("milk", milk_str);
        i.putExtra("body_weight",weight_animal);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent i = new Intent(results.this, Feed_Ration.class);
        i.putExtra("language",language);
        startActivity(i);
        finish();
    }
}
