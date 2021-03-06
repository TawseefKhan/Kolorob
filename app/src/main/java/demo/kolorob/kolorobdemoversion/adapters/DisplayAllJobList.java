package demo.kolorob.kolorobdemoversion.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazharul.Islam1 on 5/10/2016.
 */
public class DisplayAllJobList extends BaseAdapter
{
    Activity context;
    String title[];
    String salary_range[];
    String remaing_date[];
    String address[];
    String contract_number[];
    String positions[];
    LinearLayout titlePart,salaryPart,addressPart,numberpart,remainingdate_lin,salary_range_lin;
    int height,width;
    View top,bottom;


    public DisplayAllJobList(Activity context, String[] title,String[] salary_range,String[] remaining_date, String[] address,String[] contract_number,String[] positions) {
        super();
        this.context = context;
        this.title = title;
        this.salary_range = salary_range;
        this.remaing_date = remaining_date;
        this.address =address;
        this.contract_number=contract_number;
        this.positions=positions;




    }

    public int getCount() {
        // TODO Auto-generated method stub
        return title.length;
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
        TextView title;
        TextView  salary_range;
        TextView remaining_date;
        TextView address;
        TextView contact_number;
        TextView positions;


    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.display_all_job_list, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.salary_range = (TextView) convertView.findViewById(R.id.salary_range);
            holder.remaining_date = (TextView) convertView.findViewById(R.id.remaining_date);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.contact_number = (TextView) convertView.findViewById(R.id.contact_number);
            holder.positions = (TextView) convertView.findViewById(R.id.positions);


            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        height= displayMetrics.heightPixels;
        width=displayMetrics.widthPixels;








        holder.title.setText("" + positions[position]);
        holder.title.setTextSize(26);
        holder.salary_range.setText("স্যালারি : " + salary_range[position]+" টাকা");
        holder.remaining_date.setText("শেষ সময়: " + English_to_bengali_number_conversion(remaing_date[position]));
        holder.address.setText("ঠিকানা: " + address[position]);
        holder.contact_number.setText("যোগাযোগের নম্বর: " + contract_number[position]);
        holder.positions.setText("কোম্পানি: " + title[position]);

        return convertView;
    }

    public String English_to_bengali_number_conversion(String english_number) {
        int v = english_number.length();
        String concatResult = "";
        for (int i = 0; i < v; i++) {
            if (english_number.charAt(i) == '1')
                concatResult = concatResult + "১";
            else if (english_number.charAt(i) == '2')
                concatResult = concatResult + "২";
            else if (english_number.charAt(i) == '3')
                concatResult = concatResult + "৩";
            else if (english_number.charAt(i) == '4')
                concatResult = concatResult + "৪";
            else if (english_number.charAt(i) == '5')
                concatResult = concatResult + "৫";
            else if (english_number.charAt(i) == '6')
                concatResult = concatResult + "৬";
            else if (english_number.charAt(i) == '7')
                concatResult = concatResult + "৭";
            else if (english_number.charAt(i) == '8')
                concatResult = concatResult + "৮";
            else if (english_number.charAt(i) == '9')
                concatResult = concatResult + "৯";
            else if (english_number.charAt(i) == '0')
                concatResult = concatResult + "০";
            else{
                concatResult = concatResult + english_number.charAt(i);
            }
        }
        return concatResult;
    }

}

