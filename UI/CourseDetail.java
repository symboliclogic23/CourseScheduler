package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class CourseDetail extends AppCompatActivity  {
    Button assessmentBtn;
    Button notesBtn;
    String courseName;
    String courseInstructor;
    String instructorEmail;
    String instructorPhone;
    TextView editCourseName;
    TextView editInstructorName;
    TextView editInstructorEmail;
    TextView editInstructorPhone;
    TextView courseStart;
    TextView courseEnd;
    MyRepository repository;
    TextView getCourseStatus;
    String courseStatus;
    List<Course> currentCourse;
    List<Term>allTerms;
    private int courseID;
    static int termIDPassed;
    int courseTermID;
    static int courseIDPassed;
    String courseStartDate;
    String courseEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseName= getIntent().getStringExtra("termCourseNameDet");
        termIDPassed=getIntent().getIntExtra("termIDPassingOn",0);
        courseIDPassed= getIntent().getIntExtra("courseIDPassing",-1);
        courseStartDate= getIntent().getStringExtra("courseTermStart");
        courseEndDate= getIntent().getStringExtra("courseTermEnd");
        Log.d(String.valueOf(termIDPassed),"this is termID");
        editCourseName=findViewById(R.id.cdSetCourseName);
        courseStart=findViewById(R.id.cdSetCourseStart);
        courseEnd=findViewById(R.id.cdSetCourseEnd);
        editCourseName.setText(courseName);
        if(courseStart!=null){
            courseStart.setText(courseStartDate);
        }
        if(courseEnd!=null) {
            courseEnd.setText(courseEndDate);
        }

        repository= new MyRepository(getApplication());
        currentCourse = repository.getAllCourses();
        allTerms = repository.getAllTerm();

        editInstructorName=(findViewById(R.id.cdSetInstructorName));
        editInstructorEmail=(findViewById(R.id.cdSetEmail));
        editInstructorPhone=(findViewById(R.id.cdSetPhone));
        getCourseStatus=(findViewById(R.id.cdSetCourseStatus));


        if(courseID>-1){

            for(Course ci: currentCourse){
                courseInstructor=ci.getInstructorName();
                instructorEmail=ci.getEmail();
                instructorPhone=ci.getPhone();
                courseID=ci.getCourseID();
                courseStatus=ci.getCourseStatus();
            }
        }
        editInstructorName.setText(courseInstructor);
        editInstructorEmail.setText(instructorEmail);
        editInstructorPhone.setText(instructorPhone);
        getCourseStatus.setText(courseStatus);





    }

    @Override
    public void onResume(){
        super.onResume();
        courseName= getIntent().getStringExtra("termCourseNameDet");
        termIDPassed=getIntent().getIntExtra("termIDPassingOn",0);
        courseIDPassed= getIntent().getIntExtra("courseIDPassing",-1);
        Log.d(String.valueOf(termIDPassed),"this is termID");
        editCourseName=findViewById(R.id.cdSetCourseName);
        Date updateCourseStart = null;
        Date updateCourseEnd = null;
        String formatDate = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);
        String updateCourseStartSt;
        String updateCourseEndSt;

        if(courseIDPassed>0) {
            for (Course ci : repository.getAllCourses()) {
                if(ci.getCourseID()==courseIDPassed){
                    courseName=ci.getCourseName();
                    courseInstructor=ci.getInstructorName();
                    instructorEmail=ci.getEmail();
                    instructorPhone=ci.getPhone();
                    courseID=ci.getCourseID();
                    courseStatus=ci.getCourseStatus();
                    updateCourseStart =ci.getCourseStartDate();
                    updateCourseEnd=ci.getCourseEndDate();

                }
            }
        }


        editCourseName.setText(courseName);
        editInstructorName=(findViewById(R.id.cdSetInstructorName));
        editInstructorEmail=(findViewById(R.id.cdSetEmail));
        editInstructorPhone=(findViewById(R.id.cdSetPhone));
        getCourseStatus=(findViewById(R.id.cdSetCourseStatus));
        courseStart=findViewById(R.id.cdSetCourseStart);
        courseEnd=findViewById(R.id.cdSetCourseEnd);
        updateCourseStartSt = sdf.format(updateCourseStart);
        updateCourseEndSt = sdf.format(updateCourseEnd);
        if(courseStart!=null) {
            courseStart.setText(updateCourseStartSt);
        }
        if(courseEnd!=null) {
            courseEnd.setText(updateCourseEndSt);
        }

        editInstructorName.setText(courseInstructor);
        editInstructorEmail.setText(instructorEmail);
        editInstructorPhone.setText(instructorPhone);
        getCourseStatus.setText(courseStatus);

    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        TextView courseSNotify = findViewById(R.id.cdSetCourseStart);
        TextView courseENotify=findViewById(R.id.cdSetCourseEnd);
        editCourseName=findViewById(R.id.cdSetCourseName);
        String cNameNotify = editCourseName.getText().toString();
        String cNotifyStart = courseSNotify.getText().toString();
        String cNofifyEnd = courseENotify.getText().toString();

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            case R.id.deleteCourse:
                deleteCourse();
                finish();
            case R.id.startNotifyCourse:
                Date startCourseNotify=null;

                try {
                    startCourseNotify=sdf.parse(cNotifyStart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=startCourseNotify.getTime();
                Intent intent= new Intent(CourseDetail.this,MyReceiver.class);
                intent.putExtra("key",cNameNotify+" course starts!" );
                PendingIntent sender=PendingIntent.getBroadcast(CourseDetail.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.endNotifyCourse:

                Date endCourseNotify=null;
                try {
                    endCourseNotify=sdf.parse(cNotifyStart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger2=endCourseNotify.getTime();
                Intent intent2= new Intent(CourseDetail.this,MyReceiver.class);
                intent2.putExtra("key",cNameNotify+" course ends!" );
                PendingIntent sender2=PendingIntent.getBroadcast(CourseDetail.this, ++MainActivity.numAlert,intent2,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteCourse(){
        courseIDPassed= getIntent().getIntExtra("courseIDPassing",-1);
        for(Assessments a: repository.getAllAssessments()){
            if(a.getCourseID()==courseIDPassed){
                repository.deleteAllCourseAssessments(courseIDPassed);
            }
        }


        for(Notes n: repository.getAllNotes()){
            if(n.getCourseID()==courseIDPassed){
                repository.deleteNotesCourse(courseIDPassed);
            }
        }

        for(Course c: repository.getAllCourses()){
            if(c.getCourseID()==courseIDPassed){
                repository.deleteCourse(c);
            }
        }
    }


    public void courseDetail(View view) {
        Intent intent=new Intent(CourseDetail.this, EditCourseDetail.class);
        TextView courseNameEdit;
        courseNameEdit=findViewById(R.id.cdSetCourseName);
        String courseNameToEdit= courseNameEdit.getText().toString();
        termIDPassed=getIntent().getIntExtra("termIDPassingOn",0);
        courseIDPassed= getIntent().getIntExtra("courseIDPassing",-1);
        int courseIDEdit = -1;

        for(Course c: repository.getAllCourses()){
            if(c.getCourseName().equals(courseNameToEdit)){
                //courseTermIDEdit=c.getCourseTermID();
                courseIDEdit=c.getCourseID();
            }
        }
        intent.putExtra("courseNameToEdit", courseNameToEdit);
        intent.putExtra("termIDPassingOn",termIDPassed);
        intent.putExtra("courseIDFromEdit",courseIDPassed);

        startActivity(intent);
    }

    public void assessmentsList(View view) {
        TextView courseName;
        courseName=findViewById(R.id.cdSetCourseName);
        String courseNameSend = courseName.getText().toString();
        int courseID = -1;

        for(Course c: repository.getAllCourses()){
            if(c.getCourseName().equals(courseNameSend)){
                courseID = c.getCourseID();
            }
        }

        Intent intent=new Intent(CourseDetail.this, AssessmentList.class);
        intent.putExtra("assessmentCourseID",courseID);
        intent.putExtra("assessmentCourseName",courseNameSend);

        startActivity(intent);

    }

    public void goToNotes(View view) {
        TextView tvNote;
        tvNote = findViewById(R.id.cdSetCourseName);
        courseIDPassed= getIntent().getIntExtra("courseIDPassing",-1);
        String courseNameNotes= tvNote.getText().toString();
        Intent notesIntent=new Intent(CourseDetail.this, CourseNotes.class);
        notesIntent.putExtra("courseNameNotes", courseNameNotes);
        notesIntent.putExtra("courseIDNotesPass",courseIDPassed);
        startActivity(notesIntent);
    }
}