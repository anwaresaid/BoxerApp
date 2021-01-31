package project_487.com.hw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySpinnerAdapter  extends ArrayAdapter<Boxer> {
    private Context context;
    private int layoutResourceId;
    private LayoutInflater inflator;
    private ArrayList<Boxer> spinnerItemValues;

    public MySpinnerAdapter(Context context, int resource,  List values) {
        super(context, resource, values);
        this.context = context;
        this.layoutResourceId = resource;
        spinnerItemValues = (ArrayList<Boxer>) values;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getView(int position, View convertView,  ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position,  View convertView,  ViewGroup parent) {
        inflator =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflator.inflate(layoutResourceId,parent,false);

        TextView boxerSpinnerName = rowView.findViewById(R.id.spinnerRowText);


        Boxer item = spinnerItemValues.get(position);
        boxerSpinnerName.setText(item.getName());
        return rowView;
    }
}