package ryudeo.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.HttpClient;
import ryudeo.capstoneproject.R;
import ryudeo.capstoneproject.Adapters.SearchItemListAdapter;

public class SearchStaticRecyclerFragment extends Fragment {



    public SearchStaticRecyclerFragment() {
        // Required empty public constructor
    }
    private void requestSearch(String searchQuery) {

        HttpClient httpClient = new HttpClient();
        httpClient.requestFoodListWithSearchQuery(searchQuery, new HttpClient.HttpClientCallback() {
            @Override
            public void onSuccess(ArrayList<FoodInfo> foodInfos) {

            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_static_recycler, container, false);
        View searchBoxView = inflater.inflate(R.layout.activity_fab_food, container, false);
        RecyclerView recyclerView = ((RecyclerView) rootView.findViewById(R.id.search_static_recycler));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<FoodInfo> mFoodInfoList = new ArrayList<>();
        ArrayList<FoodInfo> mFoodInfoListResult = new ArrayList<>();

//        mFoodInfoList.add(new FoodInfo("rap god", 100));
//        mFoodInfoList.add(new FoodInfo("asbadaf", 200));
//        mFoodInfoList.add(new FoodInfo("samaa lamma dumaa lamaa", 300));
//        mFoodInfoList.add(new FoodInfo("eminem", 400));
//        mFoodInfoList.add(new FoodInfo("apple mac book", 500));
//        mFoodInfoList.add(new FoodInfo("iPhone", 600));
//        mFoodInfoList.add(new FoodInfo("jklasflafslhjafhl", 700));
//        mFoodInfoList.add(new FoodInfo("asljfasjlafjlaflk", 800));
//        mFoodInfoList.add(new FoodInfo("adlafljkafnafg,n", 900));



        final SearchItemListAdapter myAdapter = new SearchItemListAdapter(mFoodInfoListResult, getActivity());

        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        final EditText searchBox = (EditText)searchBoxView.findViewById(R.id.search_expanded_edit_text);

        requestSearch("만두");

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        return rootView;
    }

    // Define a public click listener interface for items of the v7.RecyclerView which has no OnItemClickListener by default
    public interface ListOnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }



    public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {
        public List<String> mStringList;
        public ListViewAdapter(List<String> stringList) {
            this.mStringList = stringList;
        }

        private ListOnItemClickListener mOnItemClickListener;
        public void setListOnItemClickListener(ListOnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        class ListViewHolder extends RecyclerView.ViewHolder {
            private final TextView mDetailText;
            public ListViewHolder(View itemView) {
                super(itemView);
                mDetailText = (TextView) itemView.findViewById(R.id.card_details);
            }
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.search_list_item, parent, false));
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

        @Override
        public void onBindViewHolder(final ListViewHolder viewHolder, int position) {
            viewHolder.mDetailText.setText(mStringList.get(position));

            // Click event called here
            if (mOnItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
                    }
                });

                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                        return false;
                    }
                });
            }

        }
    }
}