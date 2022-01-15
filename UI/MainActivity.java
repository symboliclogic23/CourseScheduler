package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.View;

import java.util.Date;
/*
    Christina Murray
    C196
    Version 1.3

 */
public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyRepository repository= new MyRepository(getApplication());
        /*Term term = new Term(0,"test term",new Date(), new Date());
        repository.insertTerm(term);
        Term term2 = new Term(1,"test term2",new Date(), new Date());
        repository.insertTerm(term2);
        Course course = new Course(1,1,"courseName",new Date(),new Date(),"In Progress","" +
                "courseInstructor","email","22222222" );
        repository.insertCourse(course);
        Course course2 = new Course(2,2,"courseName2",new Date(),new Date(),"Plan to take","" +
                "courseInstructor2","email","22222222" );
        Course course3 = new Course(3,2,"courseName3",new Date(),new Date(),"Plan to take","" +
                "courseInstructor2","email","22222222" );
        repository.insertCourse(course2);
        repository.insertCourse(course3);
        Assessments assessments = new Assessments(1,1, "name","Performance", new Date(),new Date());
        repository.insertAssessments(assessments);
        Assessments assessments2 = new Assessments(2,1, "name2","Objective", new Date(),new Date());
        repository.insertAssessments(assessments2);
        Assessments assessments3 = new Assessments(3,2, "name3","Objective", new Date(),new Date());
        repository.insertAssessments(assessments3);
        Notes notes1 = new Notes(1,1,"example of notes. we will make this longer");
        repository.insertNotes(notes1);
        Notes notes2 = new Notes(2,2,"example of shorter notes for courseID2");
        repository.insertNotes(notes2);*/

    }

    public void enter(View view) {
        Intent intent=new Intent(MainActivity.this, HomeScreen.class);
        startActivity(intent);
    }
}