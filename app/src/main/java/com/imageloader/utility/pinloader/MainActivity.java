package com.imageloader.utility.pinloader;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.imageloader.utility.pinloader.adapters.PinloaderAdapter;
import com.imageloader.utility.pinloader.beans.Pin;
import com.imageloader.utility.pinloader.utils.MaterialProgressDialog;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PinloaderAdapter adapter;
    private List<Pin> pinList;
    private String URL="http://pastebin.com/raw/wgkJgazE";
    private MaterialProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        pinList = new ArrayList<>();
        adapter = new PinloaderAdapter(this, pinList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getPins(URL);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void getPins(String url){
        mProgressDialog= new MaterialProgressDialog(MainActivity.this);
        mProgressDialog.show("");

        Ion.with(getApplicationContext())
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        cancelProgress();
                        Pin newPin=new Pin();
                        if(result!=null && result.size()>1){
                            for(int i=0;i<result.size();i++){
                                JsonObject singlePin=result.get(i).getAsJsonObject();
                                String id=singlePin.get("id").getAsString();
                                int likes=singlePin.get("likes").getAsInt();
                                String userName=singlePin.get("user").getAsJsonObject().get("name").getAsString();
                                String createAt=singlePin.get("created_at").getAsString();
                                String smallImage=singlePin.get("urls").getAsJsonObject().get("small").getAsString();
                                String largeImage=singlePin.get("urls").getAsJsonObject().get("full").getAsString();
                                String categories="";
                                JsonArray cat=singlePin.get("categories").getAsJsonArray();
                                for(int j=0;j<cat.size();j++){
                                    categories+=cat.get(j).getAsJsonObject().get("title").getAsString()+", ";
                                }
                                categories=categories.substring(0,categories.length()-2);

                                newPin= new Pin(id,likes,userName,categories,createAt,smallImage,largeImage);
                                pinList.add(newPin);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    public void cancelProgress(){
        mProgressDialog.dismiss("");
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
