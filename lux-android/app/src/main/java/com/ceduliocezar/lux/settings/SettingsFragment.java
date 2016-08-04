package com.ceduliocezar.lux.settings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.pickcgenre.GenrePickerFragment;

public class SettingsFragment extends Fragment {

//    private RecyclerView recycler;
//    private List<History> history = new ArrayList<>();

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, GenrePickerFragment.newInstance())
                .commit();
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.settings_container, GenrePickerFragment.newInstance())
//                .commit();

//        recycler = (RecyclerView) rootView.findViewById(R.id.user_history);
//        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recycler.setAdapter(new HistoryAdapter(history));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
