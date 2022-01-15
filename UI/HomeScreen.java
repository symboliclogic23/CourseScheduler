package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.R;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void termlist(View view) {
        Intent intent=new Intent(HomeScreen.this, TermList.class);
        startActivity(intent);
    }


    public void courseList(View view) {
        Intent intent=new Intent(HomeScreen.this, AllCoursesList.class);
        startActivity(intent);
    }

    public void allAssessmentsList(View view) {
        Intent intent=new Intent(HomeScreen.this, AllAssessmentList.class);
        startActivity(intent);
    }
}