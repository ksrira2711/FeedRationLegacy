package InputFilters;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.Toast;

public class NumericalInputFilterEditText implements InputFilter {
    private double min, max;
    EditText editText = null;
    private String errorMessage = null;

    public NumericalInputFilterEditText(double min, double max, EditText editText, String message) {
        this.min = min;
        this.max = max;
        this.editText = editText;
        this.errorMessage = message;
    }

    public NumericalInputFilterEditText(String min, String max, EditText editText, String message) {
        this.min = Double.parseDouble(min);
        this.max = Double.parseDouble(max);
        this.editText = editText;
        this.errorMessage = message;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String result = null;
        try {
            String editTextInput = "";

            if("".equals(source) && dstart < dend){
                editTextInput = dest.subSequence(0,dstart).toString()+dest.subSequence(dstart+1,dest.length()).toString();
                result = Character.toString(dest.charAt(dstart));
            }
            else if(dstart > dest.length() && dstart == dend){
                editTextInput = dest.toString()+source;
            }
            else if(dstart == dend){
                editTextInput = dest.subSequence(0,dstart).toString()+source+dest.subSequence(dstart,dest.length()).toString();
            }

            double input = Double.parseDouble(editTextInput);
            if (isInRange(min, max, input))
                return null;
            else{
                this.editText.setError(this.errorMessage+" "+this.max);
            }
        } catch (NumberFormatException nfe) {
            return null;
        }
        return result != null ? result : "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
