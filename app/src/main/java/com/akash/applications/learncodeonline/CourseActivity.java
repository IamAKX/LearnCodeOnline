package com.akash.applications.learncodeonline;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.akash.applications.learncodeonline.Constants.CourseConstanst;
import com.akash.applications.learncodeonline.Model.QusetionAnswerModel;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<QusetionAnswerModel> qaList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DATA STRUCTURE");

        listView = findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, CourseConstanst.topics);
        listView.setAdapter(adapter);

        new FetchQAs().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(),QuestionAnswer.class);
                i.putExtra("topic", CourseConstanst.topics[position]);
                i.putExtra("question", qaList.get(position).getQuestion());
                i.putExtra("answer", qaList.get(position).getAnswer());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private class FetchQAs extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, CourseConstanst.API,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //If we are getting success from server
                            Log.i("Checking", response + " ");
                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray array = obj.getJSONArray("questions");
                                for(int i = 0;i<array.length();i++)
                                {
                                    QusetionAnswerModel model = new QusetionAnswerModel();
                                    model.setQuestion(array.getJSONObject(i).getString("question"));
                                    model.setAnswer(array.getJSONObject(i).getString("Answer"));
                                    qaList.add(model);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            NetworkResponse networkResponse = error.networkResponse;

                            if(networkResponse != null && networkResponse.data != null)
                            {
                                Toast.makeText(getBaseContext(),networkResponse.data.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }) {

            };

            //Adding the string request to the queue
            stringRequest.setShouldCache(false);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
            requestQueue.getCache().clear();

            requestQueue.add(stringRequest);


            return null;
        }
    }
}
