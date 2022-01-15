package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.MenuItem;

import java.util.List;
import java.util.Objects;

public class AllCoursesList extends AppCompatActivity {
MyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView courseRecyclerView = findViewById(R.id.courseListRecycler);
        repository=new MyRepository(getApplication());

        List<Course> allCourses = repository.getAllCourses() ;
        final CourseAdapter courseAdapter=new CourseAdapter(allCourses,this);

        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter.setCourses(allCourses);
        }
        //courseListRecycler
       //term_name_course_row

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView courseRecyclerView = findViewById(R.id.courseListRecycler);
        repository=new MyRepository(getApplication());

        List<Course> allCourses = repository.getAllCourses() ;
        final CourseAdapter courseAdapter=new CourseAdapter(allCourses,this);

        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter.setCourses(allCourses);
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
