package ryudeo.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ryudeo.capstoneproject.R;

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