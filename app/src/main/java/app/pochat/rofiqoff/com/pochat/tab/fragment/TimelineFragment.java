package app.pochat.rofiqoff.com.pochat.tab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.activity.AddTimelineActivity;
import app.pochat.rofiqoff.com.pochat.tab.adapter.TimelineRecyclerAdapter;
import app.pochat.rofiqoff.com.pochat.tab.datatimeline.TimelineList;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {
	private FloatingActionButton fab;
	private RecyclerView recyclerView;
	private TimelineRecyclerAdapter recyclerAdapterTimeline;
	private ArrayList<TimelineList> timelineList;

	public TimelineFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_timeline, container, false);
		fab = (FloatingActionButton) view.findViewById(R.id.fab_timeline);

		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_timeline);
		timelineList = new ArrayList<>();
//        fab.setOnClickListener(klikFab());
		recyclerView.setHasFixedSize(true);

		LinearLayoutManager linearManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(linearManager);

		setRecylerViewData();
		recyclerAdapterTimeline = new TimelineRecyclerAdapter(getActivity(), timelineList);
		recyclerView.setAdapter(recyclerAdapterTimeline);

		fab.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(getContext(), AddTimelineActivity.class));
					}
				}
		);

		return view;
	}

	private void setRecylerViewData() {
		timelineList.add(new TimelineList("Rofiqo", R.drawable.gambar, R.drawable.poseter, "Deskripsi 1"));
		timelineList.add(new TimelineList("Fauzan", R.drawable.gambar, R.drawable.poster_dua, "Deskripsi 2"));
		timelineList.add(new TimelineList("Firdaus", R.drawable.gambar, R.drawable.poster_tiga, "Deskripsi 3"));
	}

	@OnClick(R.id.fab_timeline)
	public void addTimeLine(View view){
		startActivity(new Intent(getContext(), AddTimelineActivity.class));
	}

}
