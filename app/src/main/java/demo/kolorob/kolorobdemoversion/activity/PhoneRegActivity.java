package demo.kolorob.kolorobdemoversion.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Job.JobServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.SharedPreferencesHelper;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/**
 * Created by arafat on 1/11/2016.
 */
public class PhoneRegActivity extends Activity {
    ImageView close,kivabejabejob;
    TextView close_tv;
    ImageButton Feedback;
    String username="kolorobapp";
    String password="2Jm!4jFe3WgBZKEN";
    /**
     * Following components are only for LegalAid
     * For other categories this components may vary
     * In that case design the layout for specific category and call them in  setContentView(R.layout.activity_details_info);
     * */
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    private String phoneNumber;
    private int width;
    private int height;

    private EditText phone;
    private EditText name;
    private TextView fb,openTime,close_Time,breakTIme,jobName,road,block,landmark;

    //TODO Declare object for each subcategory item. Different for each category. Depends on the database table.
    JobServiceProviderItem jobServiceProviderItem;

    private Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_reg);


        phone  = (EditText)findViewById(R.id.phone_id);
        phoneNumber=phone.getText().toString();


        con = this;
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

    }

    public void submit(View v) {

        phoneNumber = phone.getText().toString();
        int size = phoneNumber.length();

        if (size != 11) {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }
        else if (phoneNumber.charAt(0) != '0') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        }
        else if (phoneNumber.charAt(1) != '1') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        } else if (phoneNumber.charAt(2) == '2' || phoneNumber.charAt(2) == '3' || phoneNumber.charAt(2) == '4') {
            AlertMessage.showMessage(this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                    "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");
        } else {
            sendPhoneNumberToServer(phoneNumber);
        }
    }


    public void sendPhoneNumberToServer(final String phone)
    {

       // http://192.168.43.57/demo/api/customer_reg?phone=01711310912
        String url = "http://kolorob.net/demo/api/customer_reg?phone="+phone+"&username="+username+"&password="+password+"" ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(PhoneRegActivity.this,response,Toast.LENGTH_SHORT).show();
                       // Log.d(">>>>>","status "+response);

                        String notvalid=response;

                        try {

                            //

                            if(response.equals("true"))
                            {
                                SharedPreferencesHelper.setNumber(con,phoneNumber);
                                AlertMessage.showMessage(PhoneRegActivity.this, "রেজিস্ট্রেশন সফলভাবে সম্পন্ন হয়েছে",
                                        " রেজিস্ট্রেশন করার জন্য আপনাকে ধন্যবাদ");


                            }

                            else if(response.equals("\"Invalid Phone Number\""))
                            {
                                AlertMessage.showMessage(PhoneRegActivity.this, "দুঃখিত আপনার ফোন নম্বরটি সঠিক নয়",
                                        "অনুগ্রহ পূর্বক সঠিক ফোন নম্বরটি ইনপুট দিন");                            }
                            else
                            {




                                SharedPreferencesHelper.setNumber(con,phoneNumber);

                                AlertMessage.showMessage(PhoneRegActivity.this, "দুঃখিত",
                                        "আপনি ইতিপূর্বে  রেজিস্ট্রেশন করে ফেলেছেন");
                            }

                                        //  finish();






                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastMessageDisplay.ShowToast(PhoneRegActivity.this,error.toString());


                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                return params;
            }

        };

// Adding request to request queue

        RequestQueue requestQueue = Volley.newRequestQueue(PhoneRegActivity.this);
        requestQueue.add(stringRequest);



    }
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
