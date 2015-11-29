package net.brooke.apppartment2;

/**
 * Created by seanwenzel on 11/22/15.
 */

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class CustomAdapter extends BaseAdapter {
    String [] result;
    private String[] amounts;
    Context context;
    //int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(AddBillEvenlyActivity mainActivity, String[] nameList) {
        // TODO Auto-generated constructor stub
        result=nameList;
        amounts = new String[result.length];
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public String[] getAmounts() {
        return amounts;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return amounts[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder
    {
        public TextView tv;
        public EditText et;
        public TextWatcher tw;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //final ViewHolder holder;
        View rowView = convertView;
        //View rowView;
        if (rowView == null) {
            //System.out.println("convertView was null: position = " + position);
            ViewHolder holder = new ViewHolder();
            rowView = inflater.inflate(R.layout.listitems, parent, false);
            holder.tv = (TextView) rowView.findViewById(R.id.tv);
            holder.et = (EditText) rowView.findViewById(R.id.et);

            rowView.setTag(holder);

        }

            ViewHolder holder = (ViewHolder) rowView.getTag();


        if (holder.tw != null)
            holder.et.removeTextChangedListener(holder.tw);



        // Keep a reference to the TextWatcher so that we can remove it later
        holder.tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                amounts[position] = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        holder.et.addTextChangedListener(holder.tw);

        holder.tv.setText(result[position]);
        holder.et.setText(amounts[position]);

        return rowView;
    }

}
