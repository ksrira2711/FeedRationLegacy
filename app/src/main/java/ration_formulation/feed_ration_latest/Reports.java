package ration_formulation.feed_ration_latest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class Reports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.MainRelativeLayout);
        mainLayout.setBackgroundColor(Color.WHITE);

        ArrayList<String> farmers = new ArrayList<String>();
        farmers = getFarmerNames();

        Spinner famrerSelection = (Spinner)findViewById(R.id.farmerNameSelection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,farmers);
        famrerSelection.setAdapter(adapter);
        famrerSelection.setSelection(0);

        famrerSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String selectedFarmer = parent.getItemAtPosition(position).toString();
                populate_fields(selectedFarmer);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populate_fields(final String selectedFarmer) {
        File farmersFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Ration Formulator"+File.separator+selectedFarmer);
        LinearLayout layout = (LinearLayout)findViewById(R.id.individualReports);
        layout.removeAllViews();
        if(!farmersFile.exists())
            return;
        File[] files = farmersFile.listFiles();

        for(int i=0;i<files.length;i++){
            if(files[i].isFile()) {
               // Toast.makeText(getApplicationContext(), files[i].getName(), Toast.LENGTH_LONG).show();
                try{

                String extension = files[i].getName().split("\\.")[1];
                String reportName = files[i].getName().split("\\.")[0];
                    Log.d("REPORT SUCCESS",extension);
                    Log.d("REPORT SUCCESS",reportName);
                if (extension.equalsIgnoreCase("pdf")) {
                    LinearLayout indiReport = new LinearLayout(this);
                    indiReport.setOrientation(LinearLayout.HORIZONTAL);
                    indiReport.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
                    indiReport.setPadding(20, 10, 0, 10);

                    TextView reportNameView = new TextView(this);
                    SpannableString content = new SpannableString(reportName);
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    reportNameView.setText(content);
                    reportNameView.setTextColor(Color.BLUE);
                    reportNameView.setGravity(Gravity.LEFT);
                    reportNameView.setTag("INDIREPORT");
                    reportNameView.setTextSize(20);
                    indiReport.addView(reportNameView);

                    reportNameView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView textview = (TextView)view;
                            openPdfReport(textview.getText().toString(),selectedFarmer);
                        }
                    });

                    ImageView deleteReport = new ImageView(this);
                    deleteReport.setImageResource(R.drawable.deletereport);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(30,0,0,0);
                    layoutParams.width = 70;
                    layoutParams.height = 70;
                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    deleteReport.setLayoutParams(layoutParams);
                    deleteReport.setTag("DELETEREPORT");
                    indiReport.addView(deleteReport);
                    layout.addView(indiReport);

                    deleteReport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout parentView = (LinearLayout) v.getParent();
                            TextView Report = (TextView) parentView.findViewWithTag("INDIREPORT");
                            String reportTobeDeleted = Report.getText().toString();
                            deleteReport(reportTobeDeleted, selectedFarmer);
                            populate_fields(selectedFarmer);
                        }
                    });
                }
            }
                catch(Exception e){
                    e.printStackTrace();
                    Log.d("ERROR REPORT",files[i].getName());
                }
            }
        }
    }

    private void openPdfReport(String report, String selectedFarmer) {
        File Report = new File(Environment.getExternalStorageDirectory() + File.separator + "Ration Formulator"+File.separator+selectedFarmer+File.separator+report+".pdf");
        if(Report.exists()){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(Report);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

    }

    private void deleteReport(String reportTobeDeleted,String selectedFarmer) {
        File Report = new File(Environment.getExternalStorageDirectory() + File.separator + "Ration Formulator"+File.separator+selectedFarmer+File.separator+reportTobeDeleted+".pdf");
        if(Report.exists()){
            Report.delete();
        }
    }

    private ArrayList<String> getFarmerNames() {
        ArrayList<String> farmers = new ArrayList<String>();
        File farmersFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Ration Formulator");

        File[] files = farmersFile.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory())
                farmers.add(files[i].getName());
        }
        farmers.add(0,"Select Farmer");
        return farmers;
    }
}
