package ration_formulation.feed_ration_latest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ration_formulation.feed_ration_latest.R;


public class FeedSupplement extends Activity {
    String DM_str=null,TDN_str=null,CP_str=null,CA_str=null,P_str=null,milk_str=null,paddy_cost=null,grass_cost=null,cm_cost=null;
    String total_cost=null;
    String paddy_weight=null,grass_weight=null,cm_weight=null;
    double DM,TDN,CP,CA,P,milk,paddy,grass,cm;
    double DM1,TDN1,CP1,CA1,P1,milk1,paddy1,grass1,cm1,cm_r,gr_r,pa_r,cost=0;
    String fail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_supplement);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            DM_str = extras.getString("DM");
            CP_str = extras.getString("CP");
            TDN_str = extras.getString("TDN");
            CA_str = extras.getString("CA");
            P_str = extras.getString("P");
            milk_str = extras.getString("milk");
            paddy_cost = extras.getString("paddy_cost");
            grass_cost = extras.getString("grass_cost");
            cm_cost = extras.getString("cm_cost");
            fail = extras.getString("fail");
        }
        DM = Double.parseDouble(DM_str);
        milk = Double.parseDouble(milk_str);
        paddy = Double.parseDouble(paddy_cost);
        grass = Double.parseDouble(grass_cost);
        cm = Double.parseDouble(cm_cost);
        TDN = Double.parseDouble(TDN_str);
        CA = Double.parseDouble(CA_str);
        CP = Double.parseDouble(CP_str);
        P = Double.parseDouble(P_str);

        //Toast.makeText(this,"in supplement",//Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,DM_str,//Toast.LENGTH_SHORT).show();

        if(milk<=7){
            TDN1 = (double)0.4*TDN;
            cm_r =   (double)TDN1/0.7;
            cm_r = (double)cm_r/0.9;
            cost+= cm_r*cm;
            TDN1 = (double)0.25*TDN;
            gr_r =   (double)TDN1/0.5;
            gr_r =   (double)gr_r/0.25;
            cost+= gr_r*grass;
            TDN1 = (double)0.35*TDN;
            pa_r =   (double)TDN1/0.4;
            pa_r =   (double)pa_r/0.9;
            cost+= pa_r*paddy;

        }
        else if(milk>7&&milk<=12)
        {
            TDN1 = (double)0.55*TDN;
            cm_r =   (double)TDN1/0.7;
            cm_r = (double)cm_r/0.9;
            cost+= cm_r*cm;
            TDN1 = (double)0.25*TDN;
            gr_r =   (double)TDN1/0.5;
            gr_r =   (double)gr_r/0.25;
            cost+= gr_r*grass;
            TDN1 = (double)0.20*TDN;
            pa_r =   (double)TDN1/0.4;
            pa_r =   (double)pa_r/0.9;
            cost+= pa_r*paddy;
        }
        else
        {
            TDN1 = (double)0.6*TDN;
            cm_r =   (double)TDN1/0.7;
            cm_r = (double)cm_r/0.9;
            cost+= cm_r*cm;
            TDN1 = (double)0.20*TDN;
            gr_r =   (double)TDN1/0.5;
            gr_r =   (double)gr_r/0.25;
            cost+= gr_r*grass;
            TDN1 = (double)0.20*TDN;
            pa_r =   (double)TDN1/0.4;
            pa_r =   (double)pa_r/0.9;
            cost+= pa_r*paddy;
        }
        total_cost = Double.toString(cost);
        paddy_weight = Double.toString(pa_r);
        grass_weight = Double.toString(gr_r);
        cm_weight = Double.toString(cm_r);
        TextView t = (TextView)findViewById(R.id.textView2);
        t.setText("The paddy required is "+paddy_weight);
         t = (TextView)findViewById(R.id.textView3);
        t.setText("The grass required is "+grass_weight);
         t = (TextView)findViewById(R.id.textView4);
        t.setText("The cm required is "+cm_weight);
         t = (TextView)findViewById(R.id.textView5);
        t.setText("The total cost is "+total_cost);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed_supplement, menu);
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
}
