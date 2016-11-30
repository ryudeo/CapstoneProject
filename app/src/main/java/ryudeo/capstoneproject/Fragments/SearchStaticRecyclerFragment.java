package ryudeo.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.HttpClient;
import ryudeo.capstoneproject.R;
import ryudeo.capstoneproject.Adapters.SearchItemListAdapter;

public class SearchStaticRecyclerFragment extends Fragment {

    public SearchStaticRecyclerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_static_recycler, container, false);


        return rootView;
    }
}