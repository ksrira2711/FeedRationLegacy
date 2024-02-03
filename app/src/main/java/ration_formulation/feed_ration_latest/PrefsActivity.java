package ration_formulation.feed_ration_latest;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.Buffer;
import java.util.ArrayList;

import ration_formulation.feed_ration_latest.R;

public class PrefsActivity extends PreferenceActivity {
    ArrayList<String> farmers = new ArrayList<String>();
    ArrayList<String> animals = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        ArrayList<String> farmers = new ArrayList<String>();
        ArrayList<String> animals = new ArrayList<String>();
        ListPreference farmers_list = null;
        ListPreference animals_list = null;
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            farmers_list = (ListPreference) findPreference("farmerName");
            animals_list = (ListPreference) findPreference("animalNumber");
            Preference null_list = (Preference) findPreference("NoFarmer");
            Preference button = (Preference) findPreference("Fetch");
            Preference button1 = (Preference) findPreference("Delete");

            PreferenceScreen screen = getPreferenceScreen();

            //Get Farmers from file.
            get_farmers();
            CharSequence[] farmer_names = new CharSequence[farmers.size()];
            CharSequence[] animal_names = new CharSequence[animals.size()];
            CharSequence[] values1 = new CharSequence[farmers.size()];
            CharSequence[] values2 = new CharSequence[animals.size()];

            if(farmers.size()==0||animals.size()==0){

                screen.removePreference(farmers_list);
                screen.removePreference(animals_list);
            }
            else {
                screen.removePreference(null_list);
                for (int i = 0; i < farmers.size(); i++) {
                    farmer_names[i] = farmers.get(i);
                    values1[i] = Integer.toString(i + 1);
                }
                for (int i = 0; i < animals.size(); i++) {
                    animal_names[i] = animals.get(i);
                    values2[i] = Integer.toString(i + 1);
                }
                farmers_list.setEntries(farmer_names);
                farmers_list.setEntryValues(values1);
                animals_list.setEntries(animal_names);
                animals_list.setEntryValues(values2);
            }

            farmers_list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    String value = preference.getSummary().toString();
                    Log.d("Value",value);
                    return true;
                }
            });
            
            /*animals_list.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String farmerName = farmers_list.getEntry().toString();
                    if(!(farmerName==null||farmerName.equals(""))){
                        get_farmers(farmerName);
                        CharSequence[] values2 = new CharSequence[animals.size()];
                        CharSequence[] animal_names = new CharSequence[animals.size()];
                        for (int i = 0; i < animals.size(); i++) {
                            animal_names[i] = animals.get(i);
                            values2[i] = Integer.toString(i + 1);
                        }
                        animals_list.setEntries(animal_names);
                        animals_list.setEntryValues(values2);
                    }
                    return false;
                }
            });*/

            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String farmerName = farmers_list.getEntry().toString();
                    String animalNumber = animals_list.getEntry().toString();
                    Log.d("In function","Enteredf");
                    Log.d("In function",farmerName);
                    Log.d("In function",animalNumber);
                    File f = get_report_directory(farmerName,animalNumber);
                    if(f.exists()){
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri = Uri.fromFile(f);
                            intent.setDataAndType(uri, "image/*");
                            startActivity(intent);
                    }
                    else{
                        Log.d("In function",f.getAbsolutePath()+" does not exist");
                        Toast.makeText(getActivity(), "Report does not exist for this animal", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });
            button1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Log.d("Deleting","Infunction");
                    String farmerName = farmers_list.getEntry().toString();
                    String animalNumber = animals_list.getEntry().toString();
                    delete_report(farmerName,animalNumber);
                    update_text(farmerName,animalNumber);
                    return true;
                }
            });
        }

        private void get_farmers(String farmerName) {
            File farmers_file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Ration Formulator"+File.separator+"farmers_info.txt");
            if(farmers_file.exists()){
                Log.d("File","Exists");
                String sCurrentLine;
                BufferedReader br = null;
                try {
                    animals.removeAll(animals);
                    br = new BufferedReader(new FileReader(farmers_file.getAbsolutePath()));
                    while ((sCurrentLine = br.readLine()) != null) {
                        String line = sCurrentLine;
                        String contents[] = line.split(";;");
                        if(contents.length>1&&line.contains(farmerName)) {
                            if(!(contents[2]==null||contents[2].equals("")))
                                animals.add(contents[2]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d("Returning",farmers.toString());
        }

        private void update_text(String farmerName, String animalNumber) {
            Log.d("Deleting","Updating text");
            File farmers_file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Ration Formulator"+File.separator+"farmers_info.txt");
            try {
                Log.d("Deleting","Entered try");
                BufferedReader br = new BufferedReader(new FileReader(farmers_file));
                String line = "";
                String result = "";
                while((line=br.readLine())!=null){
                    String currentLine = line;
                    if(!(currentLine.contains(farmerName)&&currentLine.contains(animalNumber))){
                        result+= line+"\n";
                        Log.d("Deleting","Adding "+line);
                    }
                    else{
                        Log.d("Deleting","Removing "+line);
                    }
                }
                Writer output = null;
                output = new BufferedWriter(new FileWriter(farmers_file.getAbsolutePath(), false));  //clears file every time
                output.write(result);
                output.close();
                Log.d("Deleting","Try finished");

            }
            catch(IOException ioe){
                Log.d("Deleting","in catch");
            }
        }

        private File get_report_directory(String farmerName,String animalNumber) {
            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Ration Formulator");

            File subFolder = new File(folder.getAbsolutePath()+File.separator+farmerName+File.separator+animalNumber+".jpg");
            return subFolder;
        }
        private File delete_report(String farmerName,String animalNumber) {
            Log.d("Deleting","In delete function");
            File folder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Ration Formulator");

            File subFolder = new File(folder.getAbsolutePath()+File.separator+farmerName+File.separator+animalNumber+".jpg");
            if(subFolder.exists()){
                subFolder.delete();
                Log.d("Deleting","png file deleted");
            }
            else{
                Log.d("Deleting","file not deleted");
            }
            return subFolder;
        }
        private ArrayList<String> get_farmers() {
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
                        if(contents.length>1) {
                            if(!(contents[2]==null||contents[2].equals("")))
                                animals.add(contents[2]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d("Returning",farmers.toString());
            return farmers;
        }
    }

}