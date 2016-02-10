package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazhaul Islam on 10/2/2016.
 */
public class FInancialInsuranceAdapter extends BaseAdapter
{
    Activity context;

    String service_name[];
    String yes_no[];
    String costs[];
    String remark[];






    public FInancialInsuranceAdapter(Activity context, String[] service_name,String[] yes_no,String[] costs,String[] remark) {
        super();
        this.context = context;

        this.service_name = service_name;
        this.yes_no = yes_no;
        this.costs = costs;
        this.remark = remark;



    }

    public int getCount() {
        // TODO Auto-generated method stub
        return service_name.length;
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

        TextView service_name ;
        TextView yes_no;
        TextView costs ;
        TextView remark;



    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_insurance, null);
            holder = new ViewHolder();

            holder.service_name = (TextView) convertView.findViewById(R.id.service_name);
            holder.yes_no = (TextView) convertView.findViewById(R.id.yes_no);
            holder.costs = (TextView) convertView.findViewById(R.id.costs);
            holder.remark = (TextView) convertView.findViewById(R.id.remark);


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.service_name.setText("ভ্যাকসিন ফি : "+service_name[position]);
        holder.yes_no.setText("ভ্যাকসিন নাম : " +yes_no[position]);
        holder.costs.setText("ভ্যাকসিন ফি : "+costs[position]);
        holder.remark.setText("ভ্যাকসিন নাম : " +remark[position]);


        return convertView;
    }

}



