package com.dev.sachin.sachinkumardemo.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.sachin.sachinkumardemo.Activities.HomeActivity;
import com.dev.sachin.sachinkumardemo.HelperClasses.GitApiClient;
import com.dev.sachin.sachinkumardemo.HelperClasses.GitApiInterface;
import com.dev.sachin.sachinkumardemo.HelperClasses.ReposDetails;
import com.dev.sachin.sachinkumardemo.HelperClasses.UserDetailsPrefs;
import com.dev.sachin.sachinkumardemo.R;
import com.dev.sachin.sachinkumardemo.HelperClasses.SwipeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    ViewPager viewPager;
    SwipeAdapter adapter;
    List<ReposDetails> al;
    List<ReposDetails> repoList;
    GitApiInterface apiInterface;
    @SuppressWarnings("deprecation")
    ProgressDialog progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=v.findViewById(R.id.viewpager);
        al=new ArrayList<>();
        progressBar=new ProgressDialog(getActivity());
        progressBar.setTitle("Please wait...");
        progressBar.show();
        ((HomeActivity) getActivity()).setActionBarTitle("Home");
        getRepoDeatils();


        return v;
    }


    public List<ReposDetails> getRepoDeatils(){
        apiInterface= GitApiClient.getApiClient().create(GitApiInterface.class);
        String reposUrl= UserDetailsPrefs.getuserDetails(getActivity()).getRepos_url();
        Call<List<ReposDetails>> reposCall=apiInterface.getRepos(reposUrl);

        reposCall.enqueue(new Callback<List<ReposDetails>>() {
            @Override
            public void onResponse(Call<List<ReposDetails>> call, Response<List<ReposDetails>> response) {
                progressBar.dismiss();
                if (response.code()==200) {
                    al = response.body();
                    adapter=new SwipeAdapter(getActivity(), (ArrayList<ReposDetails>) al);
                    viewPager.setAdapter(adapter);
                }
                else {
                    Toast.makeText(getActivity(), "Unexpected Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ReposDetails>> call, Throwable t) {
                progressBar.dismiss();
            t.printStackTrace();
                Toast.makeText(getActivity(), "Some problem occurred, Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        return repoList;
    }


}
