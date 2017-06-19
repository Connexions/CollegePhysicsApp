/**
 * Copyright (c) 2017 Rice University
 * 
 * This software is subject to the provisions of the GNU Lesser General
 * Public License Version 2.1 (LGPL).  See LICENSE.txt for details. 
 */
package org.openstax.collegephysics.activity;

import java.util.ArrayList;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;

import org.openstax.collegephysics.R;
import org.openstax.collegephysics.beans.Content;
import org.openstax.collegephysics.fragment.LandingListFragment;
import org.openstax.collegephysics.handlers.MenuHandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * Activity to view list of OS books available. 
 * 
 * @author Ed Woodward
 *
 */
public class LandingActivity extends AppCompatActivity
{
   
    /** list of books as Content objects */
    ArrayList<Content> content;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        getSupportActionBar().setTitle(Html.fromHtml(getString(R.string.app_name_html)));


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        LandingListFragment fragment = new LandingListFragment();
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();

        final Context context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ViewBookmarksActivity.class);
                context.startActivity(intent);
            }
        });
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.landing_options_menu, menu);
        return true;
        
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        MenuHandler mh = new MenuHandler();
        return mh.handleContextMenu(item, this, null);
    }
    
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //Log.d("ViewLenses.onSaveInstanceState()", "saving data");
        outState.putSerializable(getString(R.string.cache_lenstypes), content);
        
    }
    
}
