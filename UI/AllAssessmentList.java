package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.R;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllAssessmentList extends AppCompatActivity {
MyRepository repository;
private RecyclerView assessmentRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_assessment_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView assessmentRV = findViewById(R.id.allAssessmentListRecyclerView);
        repository=new MyRepository(getApplication());

        List<Assessments> getAllAssessments = repository.getAllAssessments();

        assessmentRV.setLayoutManager(new LinearLayoutManager(this));
        final AllAssessmentAdapter assessmentAdapter=new AllAssessmentAdapter(getAllAssessments,AllAssessmentList.this);
        assessmentRV.setAdapter(assessmentAdapter);


        assessmentAdapter.setAllAssessments(getAllAssessments);

    }

    public void onResume(){
        super.onResume();
        RecyclerView assessmentRV = findViewById(R.id.allAssessmentListRecyclerView);
        repository=new MyRepository(getApplication());

        List<Assessments> allAssessments = repository.getAllAssessments() ;
        final AllAssessmentAdapter assessmentAdapter=new AllAssessmentAdapter(allAssessments,AllAssessmentList.this);

        assessmentRV.setAdapter(assessmentAdapter);
        assessmentRV.setLayoutManager(new LinearLayoutManager(this));

        assessmentAdapter.setAllAssessments(allAssessments);
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

}