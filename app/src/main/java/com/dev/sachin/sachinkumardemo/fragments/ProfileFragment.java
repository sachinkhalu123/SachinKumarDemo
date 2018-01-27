package com.dev.sachin.sachinkumardemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.sachin.sachinkumardemo.Activities.HomeActivity;
import com.dev.sachin.sachinkumardemo.HelperClasses.UserDetailsPrefs;
import com.dev.sachin.sachinkumardemo.HelperClasses.UserProfile;
import com.dev.sachin.sachinkumardemo.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle("Profile");
        TextView nameTxt=v.findViewById(R.id.profileFrag_name);
        TextView loginTxt=v.findViewById(R.id.profileFrag_login);
        TextView htmlTxt=v.findViewById(R.id.profileFrag_htmlurl);
        TextView locationTxt=v.findViewById(R.id.profileFrag_location);
        TextView publicRepoTxt=v.findViewById(R.id.profileFrag_publicrepo);
        TextView followersTxt=v.findViewById(R.id.profileFrag_followers);
        TextView followingTxt=v.findViewById(R.id.profileFrag_following);
        UserProfile profile=UserDetailsPrefs.getuserDetails(getActivity());
        nameTxt.setText("Name: "+ profile.getName());
        loginTxt.setText("Login: "+profile.getLogin());
        htmlTxt.setText("html_url: "+profile.getHtml_url());
        locationTxt.setText("Location: "+profile.getLocation());
        publicRepoTxt.setText("public repos: "+profile.getPublicRepos());
        followersTxt.setText("followers: "+profile.getFollowers());
        followingTxt.setText("following: "+profile.getFollowing());

        return v;
    }

}
