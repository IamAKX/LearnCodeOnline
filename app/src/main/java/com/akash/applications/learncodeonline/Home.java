package com.akash.applications.learncodeonline;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.akash.applications.learncodeonline.Adapters.FreeCourseAdapter;
import com.akash.applications.learncodeonline.Adapters.VideoCourseAdapter;
import com.akash.applications.learncodeonline.Constants.ThumbnailData;
import com.akash.applications.learncodeonline.SavePreferences.UserDetails;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class Home extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    SliderLayout sliderLayout;
    RecyclerView freeRecycler,paidRecycler,videoRecycler,examsRecylers;
    FreeCourseAdapter freeAdapter,paidAdapter,examsAdapter;
    VideoCourseAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sliderLayout = findViewById(R.id.slider);
        freeRecycler = findViewById(R.id.recycler_freecourse);
        paidRecycler = findViewById(R.id.recycler_paidcourse);
        videoRecycler = findViewById(R.id.recycler_videocourse);
        examsRecylers = findViewById(R.id.recycler_examscourse);

        initSlider();

        LinearLayoutManager freeLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager paidLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager videoLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager examsLayoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);

        freeAdapter = new FreeCourseAdapter(ThumbnailData.getFreeCourseList(),getBaseContext());
        freeRecycler.setLayoutManager(freeLayoutManager);
        freeRecycler.setAdapter(freeAdapter);

        paidAdapter = new FreeCourseAdapter(ThumbnailData.getPaidCourseList(),getBaseContext());
        paidRecycler.setLayoutManager(paidLayoutManager);
        paidRecycler.setAdapter(paidAdapter);

        videoAdapter = new VideoCourseAdapter(ThumbnailData.getVideoCourseList(),getApplicationContext());
        videoRecycler.setLayoutManager(videoLayoutManager);
        videoRecycler.setAdapter(videoAdapter);

        examsAdapter = new FreeCourseAdapter(ThumbnailData.getExamsCourseList(),getBaseContext());
        examsRecylers.setLayoutManager(examsLayoutManager);
        examsRecylers.setAdapter(examsAdapter);
    }

    private void initSlider() {
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Data Science",R.drawable.img_datasc);
        file_maps.put("JS",R.drawable.img_js);
        file_maps.put("Icons Sketch",R.drawable.img_icdes);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            switch (name) {
                case "Data Science":
                        textSliderView.getBundle()
                        .putString("extra", "https://courses.learncodeonline.in/learn/Machine-Learning-Bootcamp?");
                        break;

                case "JS":
                    textSliderView.getBundle()
                            .putString("extra", "https://courses.learncodeonline.in/learn/Javascript-for-2018-developer?");
                    break;

                case "Icons Sketch":
                    textSliderView.getBundle()
                            .putString("extra", "https://courses.learncodeonline.in/learn/Sketch-icons?");
                    break;
            }
            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(2500);
        sliderLayout.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                new UserDetails(getBaseContext()).logout();
                Toast.makeText(getBaseContext(),"Successfully Logged out",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(),NameEmail.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;

            case R.id.setting:

                startActivity(new Intent(getBaseContext(),NameEmail.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(slider.getBundle().get("extra").toString()));
        startActivity(i);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
