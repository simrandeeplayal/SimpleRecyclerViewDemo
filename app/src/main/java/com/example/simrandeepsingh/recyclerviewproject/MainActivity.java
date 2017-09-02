package com.example.simrandeepsingh.recyclerviewproject;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem> listitems;
    private static final String URL_DATA = "https://api.learn2crack.com/android/jsonos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclervieww);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
        loadRecyclerViewdata();
        ItemTouchHelper itemtouch=new ItemTouchHelper(createhelpercallback());
        itemtouch.attachToRecyclerView(recyclerView);


    }

    private ItemTouchHelper.Callback createhelpercallback() {
     ItemTouchHelper.SimpleCallback simpleitemtouch=
             new ItemTouchHelper.SimpleCallback(0,
                     ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){

                 @Override
                 public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                     return false;
                 }

                 @Override
                 public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                     deleteItem(viewHolder.getAdapterPosition());

                 }
             };
             return simpleitemtouch;
    }

    private void deleteItem(int adapterPosition) {
        listitems.remove(adapterPosition);
        adapter.notifyItemRemoved(adapterPosition);

    }

    private void loadRecyclerViewdata() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading Data...");
        pd.show();

        StringRequest stringrequest = new StringRequest(Request.Method.GET
                , URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        pd.dismiss();
                        try {
                            JSONObject json=new JSONObject(s);
                            JSONArray array=json.getJSONArray("android");

                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject o=array.getJSONObject(i);
                                Listitem  item=new Listitem(
                                        o.getString("ver"),
                                        o.getString("name"),
                                        o.getString("api")
                                );
                                listitems.add(item);
                            }
                            adapter=new Myadapter(listitems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(MainActivity.this,""+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestqueue=Volley.newRequestQueue(this);
        requestqueue.add(stringrequest);

    }

}