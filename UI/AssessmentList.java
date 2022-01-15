package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.R;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class AssessmentList extends AppCompatActivity {
private MyRepository repository;
private TextView textView;
String courseAssessment;
int courseID;
private RecyclerView recyclerView;
private AssessmentAdapter assessmentAdapter;
List<Assessments> filteredAssessments;
List<Course> currentCourse;
String courseName;
List<Assessments> allAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = findViewById(R.id.recyclerView3);
        //courseID= getIntent().getIntExtra("courseID2");
        int courseID = getIntent().getIntExtra("assessmentCourseID", -1);
        String courseName= getIntent().getStringExtra("assessmentCourseName");
        List<Assessments> filteredAssessments = new ArrayList<>();

        repository = new MyRepository(getApplication());

        if(courseID>0) {
            allAssessments = repository.getAllAssessments();
            for(Assessments a: allAssessments){
                if(a.getCourseID()==courseID){
                    filteredAssessments.add(a);
                }
            }


        }



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(allAssessments,AssessmentList.this);

        recyclerView.setAdapter(assessmentAdapter);
        assessmentAdapter.setAssessments(allAssessments);

        if(filteredAssessments!=null){
            assessmentAdapter.setAssessments(filteredAssessments);
        }
    }




    @Override
    public void onResume() {

        super.onResume();
        //int courseID = getIntent().getIntExtra("result", -1);
        int courseID = getIntent().getIntExtra("assessmentCourseID", -1);
        List<Assessments> filteredAssessments = new ArrayList<>();
        repository = new MyRepository(getApplication());

        if(courseID>0) {
            allAssessments = repository.getAllAssessments();
            for(Assessments a: allAssessments){
                if(a.getCourseID()==courseID){
                    filteredAssessments.add(a);
                }
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(allAssessments,AssessmentList.this);
        recyclerView.setAdapter(assessmentAdapter);

        assessmentAdapter.setAssessments(allAssessments);
        if(filteredAssessments!=null){
            assessmentAdapter.setAssessments(filteredAssessments);
        }

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




    public void newAssessmentEdit(View view) {
        int courseID = getIntent().getIntExtra("assessmentCourseID", -1);
        Intent intent=new Intent(AssessmentList.this, EditAssessmentDetails.class);
        intent.putExtra("courseAssessmentToEdit",courseID);


        startActivity(intent);

    }
}