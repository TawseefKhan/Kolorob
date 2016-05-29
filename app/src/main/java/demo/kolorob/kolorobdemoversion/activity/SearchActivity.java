package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.adapters.AllHolder;
import demo.kolorob.kolorobdemoversion.adapters.ListViewAdapterAllCategories;
import demo.kolorob.kolorobdemoversion.database.Education.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Entertainment.EntertainmentServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Financial.FinancialServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.Health.HealthServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.LegalAid.LegalAidServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Entertainment.EntertainmentServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.FInancial.FinancialServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.Health.HealthServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.LegalAid.LegalAidServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by HP on 5/25/2016.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    private LinearLayout llSubCatListHolder,exlist;
    ArrayList<String>subcategorynames;
    ListViewAdapterAllCategories adapter;
    EditText filterText;
    ListView allitemList;
    TextView searchtext;
    ImageButton more;
    boolean catstatus=false;
    int filcatid;
    RelativeLayout catholder;
    CheckBox check;
    LinearLayout fholder,fleft,fright;
    ArrayList<AllHolder>allHolders=new ArrayList<>();
    ArrayList<AllHolder>catHolders=new ArrayList<>();
    private ArrayList<FinancialServiceProviderItem>fetchedfin;
    private ArrayList<EducationServiceProviderItem>fetchededu;
    private ArrayList<LegalAidServiceProviderItem>fetchedleg;
    private ArrayList<EntertainmentServiceProviderItem>fetchedent;
    private ArrayList<HealthServiceProviderItem>fetchedhel;
    RadioGroup catgroup;

    public int getFilcatid() {
        return filcatid;
    }

    public void setFilcatid(int filcatid) {
        this.filcatid = filcatid;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfilter);

more=(ImageButton)findViewById(R.id.morebutton);
        searchtext=(TextView)findViewById(R.id.textView17) ;
        check=(CheckBox)findViewById(R.id.searchmbox);
          more.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fholder=(LinearLayout)findViewById(R.id.LinearLayoutfilter);
          catholder=(RelativeLayout)findViewById(R.id.categoryfilterholder);
          catholder.setVisibility(View.GONE);
          catgroup=(RadioGroup)findViewById(R.id.catradioGroup);
          catgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {

                  if (checkedId == R.id.edradioButton) {

                      setFilcatid(1);
                      catstatus=true;
                      calladapter(catstatus);

                  } else  if (checkedId == R.id.helradioButton2) {
                      //do work when radioButton2 is active
                      setFilcatid(2);
                      catstatus=true;
                      calladapter(catstatus);
                  }
                  else  if (checkedId == R.id.entradioButton5) {
                      //do work when radioButton2 is active
                      setFilcatid(3);
                      catstatus=true;
                      calladapter(catstatus);
                  }
                  else  if (checkedId == R.id.finradioButton4) {
                      //do work when radioButton2 is active
                      setFilcatid(6);
                      catstatus=true;
                      calladapter(catstatus);
                  }
                  else  if (checkedId == R.id.legradioButton3) {
                      //do work when radioButton2 is active
                      setFilcatid(5);
                      catstatus=true;
                      calladapter(catstatus);
                  }

check.setVisibility(View.VISIBLE);
              }
          });
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    // perform logic
                    fholder.setVisibility(View.VISIBLE);
                }

            }
        });
//        else
//           toolbar = (Toolbar) findViewById(R.id.toolbars);

        // toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        ab.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //toggle.syncState();


    //    gridView = (GridView) findViewById(R.id.grid);

        SubCategoryTable subCategoryTable = new SubCategoryTable(SearchActivity.this);
        subcategorynames=subCategoryTable.getcatSubCategories(1);
        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
          allitemList=(ListView)findViewById(R.id.allitem);
       Populateholder();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.morebutton:
                searchtext.setText("where you want to search");
                catholder.setVisibility(View.VISIBLE);



        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
    public void Populateholder()
    {
        filterText = (EditText)findViewById(R.id.searchall);
        EducationServiceProviderTable educationServiceProviderTable=new EducationServiceProviderTable(SearchActivity.this);
        EntertainmentServiceProviderTable entertainmentServiceProviderTable=new EntertainmentServiceProviderTable(SearchActivity.this);
        HealthServiceProviderTable healthServiceProviderTable = new HealthServiceProviderTable(SearchActivity.this);
        FinancialServiceProviderTable financialServiceProviderTable = new FinancialServiceProviderTable(SearchActivity.this);
        LegalAidServiceProviderTable legalAidServiceProviderTable = new LegalAidServiceProviderTable(SearchActivity.this);
        fetchedent=entertainmentServiceProviderTable.getAllEntertainmentSubCategoriesInfo(3);
        fetchedfin=financialServiceProviderTable.getAllFinancialSubCategoriesInfo(6);
        fetchedleg=legalAidServiceProviderTable.getAllLegalAidSubCategoriesInfo(5);
        fetchedhel=healthServiceProviderTable.getAllHealthSubCategoriesInfo(2);
        fetchededu=educationServiceProviderTable.getAllEducationSubCategoriesInfo(1);
 String nameen,namebn,catid,node;
        int refname;
        for (int i=0;i<fetchededu.size();i++)
        {

            nameen=fetchededu.get(i).getEduNameEng();
            node=fetchededu.get(i).getIdentifierId();
            refname=fetchededu.get(i).getEduSubCategoryId();
            namebn=fetchededu.get(i).getEduNameBan();

      AllHolder all=new AllHolder(node,refname,nameen,namebn,1);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedhel.size();i++)
        {

            nameen=fetchedhel.get(i).getNodeName();
            node=fetchedhel.get(i).getNodeId();
            refname=fetchedhel.get(i).getRefNum();
            namebn=fetchedhel.get(i).getNameBn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,2);
            allHolders.add(all);
        }


        for (int i=0;i<fetchedleg.size();i++)
        {

            nameen=fetchedleg.get(i).getLegalaidNameEng();
            node=fetchedleg.get(i).getIdentifierId();
            refname=fetchedleg.get(i).getLegalaidSubCategoryId();
            namebn=fetchedleg.get(i).getLegalaidNameBan();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,5);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedent.size();i++)
        {

            nameen=fetchedent.get(i).getNodeName();
            node=fetchedent.get(i).getNodeId();
            refname=fetchedent.get(i).getEntSubCategoryId();
            namebn=fetchedent.get(i).getNodeNameBn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,3);
            allHolders.add(all);
        }
        for (int i=0;i<fetchedfin.size();i++)
        {

            nameen=fetchedfin.get(i).getNodeName();
            node=fetchedfin.get(i).getNodeId();
            refname=fetchedfin.get(i).getRefNum();
            namebn=fetchedfin.get(i).getNamebn();

            AllHolder all=new AllHolder(node,refname,nameen,namebn,6);
            allHolders.add(all);


        }
        calladapter(false);

    }
    private void calladapter(boolean status)
    {
        boolean instatus=status;
        if(instatus==true)
        {
int gotcatid=getFilcatid();
            catHolders.clear();
            for(int ii=0;ii<allHolders.size();ii++)
            {
                if(allHolders.get(ii).getCatid()==gotcatid)
                {
                    catHolders.add(allHolders.get(ii));
                }
            }
            adapter = new ListViewAdapterAllCategories(this, catHolders);

            allitemList.setAdapter(adapter);
        }
        else {
            adapter = new ListViewAdapterAllCategories(this, allHolders);

            allitemList.setAdapter(adapter);
        }

        int[] colors = {0, 0xFFFF0000, 0}; // red for the example
        allitemList.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        allitemList.setDividerHeight(1);

        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = filterText.getText().toString().toLowerCase(Locale.getDefault());

                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }

        });
        allitemList.setFastScrollEnabled(false);
        allitemList.setFastScrollEnabled(true);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}