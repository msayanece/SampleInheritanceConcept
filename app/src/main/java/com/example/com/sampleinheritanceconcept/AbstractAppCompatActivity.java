package com.example.com.sampleinheritanceconcept;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.com.sampleinheritanceconcept.R.drawable.ic_beauty_health_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_business_service_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_dth_recharge_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_event_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_flight_ticket_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_giftcard_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_home_service_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_insurance_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_lessons_hobbies_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_money_transfer_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_movers_packers_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_online_booking_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_reminder_white;
import static com.example.com.sampleinheritanceconcept.R.drawable.ic_shopping_white;

/**
 * Created by Admin on 02-08-2017.
 */

public abstract class AbstractAppCompatActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView topRecyclerView;
    private List<TopRecyclerPojo> topRecyclerPojoList = new ArrayList<>();;
    private TopRecyclerPojo topRecyclerPojo;
    private int[] images ={ic_home_service_white, ic_online_booking_white, ic_flight_ticket_white, ic_reminder_white, ic_dth_recharge_white, ic_shopping_white, ic_movers_packers_white, ic_event_white, ic_beauty_health_white, ic_insurance_white, ic_money_transfer_white, ic_lessons_hobbies_white, ic_giftcard_white, ic_business_service_white};

    protected boolean useToolbar() {
        return true;
    }

    protected boolean useTopRecyler() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        configureTopRecylcer(view);
        super.setContentView(view);
        configureToolbar(view);
    }

    private void configureToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        if (toolbar != null) {
            if (useToolbar()) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                setOnClickForActionButtons(view);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    private void configureTopRecylcer(View view) {
        topRecyclerView = (RecyclerView) view.findViewById(R.id.top_recycler_view);
        if (topRecyclerView != null) {
            if (useToolbar()) {
                addTopRecyclerView();
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    private void setOnClickForActionButtons(View view){
        ImageView locBtn = (ImageView) view.findViewById(R.id.action_bar_loc_button);
        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AbstractAppCompatActivity.this, "Loc Clocked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTopRecyclerView() {
        topRecyclerView.setHasFixedSize(true);
        topRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        String[] whiteIcons = getResources().getStringArray(R.array.main_category);
        int whiteIconsArrayLength = whiteIcons.length;
        for (int i = 0; i<whiteIconsArrayLength; i++){
            topRecyclerPojo = new TopRecyclerPojo(""+i, whiteIcons[i], images[i]);
            topRecyclerPojoList.add(topRecyclerPojo);
        }
        TopRecyclerAdapter adapter = new TopRecyclerAdapter(topRecyclerPojoList,getApplicationContext());
        topRecyclerView.setAdapter(adapter);
        topRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(AbstractAppCompatActivity.this, topRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if (position == 0){
                            onClickHomeServiceBtn(view);
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void onClickHomeServiceBtn(View view) {
        Toast.makeText(this, "Home service", Toast.LENGTH_SHORT).show();
    }
}
