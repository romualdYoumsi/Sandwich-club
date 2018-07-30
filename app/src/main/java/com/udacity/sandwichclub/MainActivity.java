package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String FIRST_SANDWICH_POSITION = "firstSandPosition";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sandwiches);

        // Simplification: Using a ListView instead of a RecyclerView
        listView = findViewById(R.id.sandwiches_listview);
        listView.setAdapter(adapter);

//        triggering rotation screen to scroll list to last position
        if (savedInstanceState != null) {
            int firstSandposition = savedInstanceState.getInt(FIRST_SANDWICH_POSITION);
            Log.e(TAG, "savedInstanceState: firstSandposition=" + firstSandposition);

//            Scroll list to position
            listView.smoothScrollToPosition(firstSandposition + 2);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Log.e(TAG, "onCreate:onItemClick: position=" + position + " l=" + l);
                launchDetailActivity(position);
            }
        });

    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
