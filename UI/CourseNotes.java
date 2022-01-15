package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class CourseNotes extends AppCompatActivity {
String courseNameNotes;
MyRepository repository;
List<Course> currentCourse;
int courseID=-1;
int termCourseID=-1;
EditText notesText;
String notesFromDB;
int courseIDSet=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_notes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseNameNotes=getIntent().getStringExtra("courseNameNotes");
        courseIDSet=getIntent().getIntExtra("courseIDNotesPass",-1);


        repository= new MyRepository(getApplication());
        currentCourse = repository.getAllCourses();
        List<Notes> notes = repository.getAllNotes();
        //List<Notes> courseNotes= new ArrayList<>();

        for(Course c: currentCourse) {
            if (c.getCourseName().equals(courseNameNotes)) {
                courseID = c.getCourseID();
                if (courseID > 0) {
                    for (Notes n : notes) {
                        if (n.getCourseID() == courseID) {
                            notesFromDB=n.getNote();
                        }
                    }
                }
            }
        }

        if(notesFromDB!=null){
            notesText=findViewById(R.id.editTextTextMultiLine);
            notesText.setText(notesFromDB);

        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notes_detail_menu, menu);
        return true;
    }

    //hide keyboard if touch even outside of multitext area
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean hideKB = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int ks[] = new int[2];
            w.getLocationOnScreen(ks);
            float x = event.getRawX() + w.getLeft() - ks[0];
            float y = event.getRawY() + w.getTop() - ks[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom()) ) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return hideKB;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete3:
                deleteCourseNotes();

                //notesText=findViewById(R.id.editTextTextMultiLine);
                //notesText.setText(notesFromDB);

                return true;
            case R.id.share:
                EditText getNotes=findViewById(R.id.editTextTextMultiLine);
                String setNotes= getNotes.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, setNotes);
                // (Optional) Here we're setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Notes");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void saveCourseNotes(View view) {
        EditText getNotes;
        String setNotes;
        List<Notes> mNotes=repository.getAllNotes();
        int notesID = -1;

        for(Course c: currentCourse) {
            if (c.getCourseName().equals(courseNameNotes)) {
                courseID = c.getCourseID();
                if (courseID > 0) {
                    for (Notes n : mNotes) {
                        if (n.getCourseID() == courseID) {
                            notesID = n.getNotesID();
                        }
                    }
                }
            }
        }
        getNotes=findViewById(R.id.editTextTextMultiLine);
        setNotes= getNotes.getText().toString();
        if(notesID>0){
            try {
                Notes updateNotes = new Notes(notesID, courseID, setNotes);
                repository.updateNotes(updateNotes);
            }
            catch(Exception e){
                Context context = getApplicationContext();
                CharSequence text = "Note update failed";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }
        else{
            try{
            Notes newNotes= new Notes(++notesID, courseID,setNotes);
            repository.insertNotes(newNotes);}
            catch (Exception e) {
                Context context = getApplicationContext();
                CharSequence text = "Note save failed";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                notesText=findViewById(R.id.editTextTextMultiLine);
                notesText.setText(setNotes);
            }
        }
    }
    public void deleteCourseNotes(){
        courseNameNotes=getIntent().getStringExtra("courseNameNotes");
        courseIDSet=getIntent().getIntExtra("courseIDNotesPass",-1);
        repository= new MyRepository(getApplication());
        currentCourse = repository.getAllCourses();
        List<Notes> notes = repository.getAllNotes();


        for(Course c: currentCourse) {
            if (c.getCourseName().equals(courseNameNotes)) {
                courseID = c.getCourseID();
            }
        }
        if(courseIDSet>0){
            try {
                repository.deleteNotesCourse(courseIDSet);
                notesText=findViewById(R.id.editTextTextMultiLine);
                notesText.setText("");
            }
            catch(Exception e){

                Context context = getApplicationContext();
                CharSequence text = "Note deletion failed";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        courseNameNotes=getIntent().getStringExtra("courseNameNotes");
        courseIDSet=getIntent().getIntExtra("courseIDNotesPass",-1);


        repository= new MyRepository(getApplication());
        currentCourse = repository.getAllCourses();
        List<Notes> notes = repository.getAllNotes();
        //List<Notes> courseNotes= new ArrayList<>();

        for(Course c: currentCourse) {
            if (c.getCourseName().equals(courseNameNotes)) {
                courseID = c.getCourseID();
                if (courseID > 0) {
                    for (Notes n : notes) {
                        if (n.getCourseID() == courseID) {
                            notesFromDB=n.getNote();
                        }
                    }
                }
            }
        }

        if(notesFromDB!=null){
            notesText=findViewById(R.id.editTextTextMultiLine);
            notesText.setText(notesFromDB);

        }
    }

}