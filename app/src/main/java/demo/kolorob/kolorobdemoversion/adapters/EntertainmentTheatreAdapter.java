package demo.kolorob.kolorobdemoversion.adapters;

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

public class EntertainmentTheatreAdapter extends BaseAdapter
{
    Activity context;

    String event_type[];
    String event_fee[];
    String event_date[];
    String remarks[];
    String event_fee_ffp[];
    String event_fee_foc[];




    public EntertainmentTheatreAdapter(Activity context, String[] event_type,String[] event_fee,String[] event_date,String[] remarks,String[] event_fee_ffp,String[] event_fee_foc) {
        super();
        this.context = context;

        this.event_type = event_type;
        this.event_fee = event_fee;
        this.event_date = event_date;
        this.remarks = remarks;
        this.event_fee_ffp = event_fee_ffp;
        this.event_fee_foc = event_fee_foc;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return event_type.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {

        TextView event_type ;
        TextView event_fee;
        TextView event_date ;
        TextView remarks;
        TextView event_fee_ffp ;
        TextView event_fee_foc;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_ent_theatre, null);
            holder = new ViewHolder();

            holder.event_type = (TextView) convertView.findViewById(R.id.event_type);
            holder.event_fee = (TextView) convertView.findViewById(R.id.event_fee);
            holder.event_date = (TextView) convertView.findViewById(R.id.event_date);
            holder.remarks = (TextView) convertView.findViewById(R.id.remarks);
            holder.event_fee_ffp = (TextView) convertView.findViewById(R.id.event_fee_ffp);
            holder.event_fee_foc = (TextView) convertView.findViewById(R.id.event_fee_foc);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.event_type.setText("ভ্যাকসিন ফি : "+event_type[position]);
        holder.event_fee.setText("ভ্যাকসিন নাম : " +event_fee[position]);
        holder.event_date.setText("ভ্যাকসিন ফি : "+event_date[position]);
        holder.remarks.setText("ভ্যাকসিন নাম : " +remarks[position]);
        holder.event_fee_ffp.setText("ভ্যাকসিন ফি : "+event_fee_ffp[position]);
        holder.event_fee_foc.setText("ভ্যাকসিন নাম : " +event_fee_foc[position]);

        return convertView;
    }

}


