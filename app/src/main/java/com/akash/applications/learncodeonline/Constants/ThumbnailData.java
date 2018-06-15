package com.akash.applications.learncodeonline.Constants;

import com.akash.applications.learncodeonline.Model.ThumbnailModel;
import com.akash.applications.learncodeonline.Model.YouTubeThumbnailModel;
import com.akash.applications.learncodeonline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akash on 14/6/18.
 */

public class ThumbnailData {
    public static List<ThumbnailModel> getFreeCourseList()
    {
        List<ThumbnailModel> list = new ArrayList<>();
        list.add(new ThumbnailModel("DATA STRUCTURE",R.drawable.img_3));
        list.add(new ThumbnailModel("JAVA",R.drawable.img_12));
        list.add(new ThumbnailModel("ALGORITHM",R.drawable.img_4));
        list.add(new ThumbnailModel("NETWORKING",R.drawable.img_14));
        list.add(new ThumbnailModel("JAVASCRIPT",R.drawable.img_js));
        list.add(new ThumbnailModel("FUN WITH ICON",R.drawable.img_icdes));

        return list;
    }

    public static List<ThumbnailModel> getPaidCourseList()
    {
        List<ThumbnailModel> list = new ArrayList<>();
        list.add(new ThumbnailModel("iOS Applications",R.drawable.img_11));
        list.add(new ThumbnailModel("Android Applications",R.drawable.img_1));
        list.add(new ThumbnailModel("Social Login",R.drawable.img_2));
        list.add(new ThumbnailModel("Machine Learning",R.drawable.img_datasc));
        list.add(new ThumbnailModel("Ethical Hacking",R.drawable.img_8));


        return list;
    }

    public static List<YouTubeThumbnailModel> getVideoCourseList()
    {
        List<YouTubeThumbnailModel> list = new ArrayList<>();
        list.add(new YouTubeThumbnailModel("KOTLIN",R.drawable.img_kotlin,"https://www.youtube.com/playlist?list=PLRAV69dS1uWTesDLqDluecCuxHVv4BTJ7"));
        list.add(new YouTubeThumbnailModel("FLEXBOX",R.drawable.img_flexbox,"https://www.youtube.com/playlist?list=PLRAV69dS1uWSZJXa62hmOUKLnOZqxYrn9"));
        list.add(new YouTubeThumbnailModel("REGULAR EXPRESSION",R.drawable.img_regex,"https://www.youtube.com/playlist?list=PLRAV69dS1uWTy-hzDNUrq3_mGjvm3ilQK"));
        list.add(new YouTubeThumbnailModel("ADOBE XD",R.drawable.img_adobexd,"https://www.youtube.com/playlist?list=PLRAV69dS1uWSUAYohDFYhn24WgfnpzzNF"));
        list.add(new YouTubeThumbnailModel("WORDPRESS",R.drawable.img_wordpress,"https://www.youtube.com/playlist?list=PLRAV69dS1uWQEceWisbmY5FbyIEktQoC-"));
        list.add(new YouTubeThumbnailModel("JAVA MEDIA PLAYER",R.drawable.img_java,"https://www.youtube.com/playlist?list=PLRAV69dS1uWSKVqHMGJ3ebZ_Wc2UTj-Vf"));
        list.add(new YouTubeThumbnailModel("PROGRAMMING LANG",R.drawable.img_proglang,"https://www.youtube.com/watch?v=5bm5tT0bN7c"));

        return list;
    }

    public static List<ThumbnailModel> getExamsCourseList()
    {
        List<ThumbnailModel> list = new ArrayList<>();
        list.add(new ThumbnailModel("Hackon",R.drawable.img_6));
        list.add(new ThumbnailModel("Quizzeria",R.drawable.img_5));
        list.add(new ThumbnailModel("Codesters",R.drawable.img_13));
        list.add(new ThumbnailModel("Hackerna",R.drawable.img_16));
        list.add(new ThumbnailModel("Codesters",R.drawable.img_13));
        list.add(new ThumbnailModel("CodeVita",R.drawable.img_9));
        return list;
    }
}
