package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class TermCourseList extends AppCompatActivity {
//private String termName;
private String getTermName;
private MyRepository repository;
private int termID;
private int courseID;
//List<Term> allTerms;
//List<Course> filteredCourse;
//RecyclerView recyclerView6;
String termCourseName;
int setCourseID;

//TermCourseAdapter termCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        termCourseName=getIntent().getStringExtra("termCourseName");
        setCourseID= getIntent().getIntExtra("termID2CourseList",-1);

        RecyclerView recyclerView6 = findViewById(R.id.courseTermListRecyclerView);
        repository=new MyRepository(getApplication());

        List<Term>allTerms=repository.getAllTerm();
        List<Course> filteredCourse = repository.getAllCourses() ;
        //String termName= getIntent().getStringExtra("termName");
        List<Course> filter2Course = new ArrayList<>();
        //int termCourseID= Integer.parseInt(getIntent().getStringExtra("termCourseID"));


        for(Term t: allTerms ){
            if (t.getTermName().equals(termCourseName)) {
                termID = t.getTermID();

                if(termID>0) {
                    for(Course c: filteredCourse){
                      if(c.getCourseTermID()==termID)  {
                        filter2Course.add(c);
                        }
                    }
            }
        }

        }






        final TermCourseAdapter termCourseAdapter=new TermCourseAdapter(filteredCourse,this);

        recyclerView6.setAdapter(termCourseAdapter);
        recyclerView6.setLayoutManager(new LinearLayoutManager(this));
        if(filter2Course!=null){
            termCourseAdapter.setCourse(filter2Course);
        }
        else{
            termCourseAdapter.setCourse(filteredCourse);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        setCourseID= getIntent().getIntExtra("termID2CourseList",-1);
        termCourseName=getIntent().getStringExtra("termCourseName");
        RecyclerView recyclerView6 = findViewById(R.id.courseTermListRecyclerView);
        repository=new MyRepository(getApplication());

        List<Term>allTerms=repository.getAllTerm();
        List<Course> filteredCourse = repository.getAllCourses() ;
        //String termName= getIntent().getStringExtra("termName");
        List<Course> filter2Course = new ArrayList<>();
        //int termCourseID= Integer.parseInt(getIntent().getStringExtra("termCourseID"));


        for(Term t: allTerms ){
            if (t.getTermName().equals(termCourseName)) {
                termID = t.getTermID();

                if(termID>0) {
                    for(Course c: filteredCourse){
                        if(c.getCourseTermID()==termID)  {
                            filter2Course.add(c);
                        }
                    }
                }
            }

        }
        final TermCourseAdapter termCourseAdapter=new TermCourseAdapter(filteredCourse,this);

        recyclerView6.setAdapter(termCourseAdapter);
        recyclerView6.setLayoutManager(new LinearLayoutManager(this));
        if(filter2Course!=null){
            termCourseAdapter.setCourse(filter2Course);
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


    public void courseTermDetail(View view) {
        setCourseID= getIntent().getIntExtra("termID2CourseList",-1);
        Intent intent=new Intent(TermCourseList.this, EditCourseDetail.class);
        intent.putExtra("termIDPassingOn",setCourseID);
        startActivity(intent);
    }
}