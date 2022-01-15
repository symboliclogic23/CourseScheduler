package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class EditCourseDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String courseName;
    String termNameSpin;
    String courseInstructor;
    String instructorEmail;
    String instructorPhone;
    EditText editCourseName;
    EditText editInstructorName;
    EditText editInstructorEmail;
    EditText editInstructorPhone;
    EditText screenCourseStart;
    EditText screenCourseEnd;
    MyRepository repository;
    String courseStatus;
    List<Course> currentCourse;
    List<Term>allTerms;
    private int courseID;
    int courseTermID;
    int courseIDFromCD;
    int courseTermIDFromCD;
    final Calendar calendarStart = Calendar.getInstance();
    final Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener editCourseStart;
    DatePickerDialog.OnDateSetListener editCourseEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_detail);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        courseName= getIntent().getStringExtra("courseNameToEdit");
        editCourseName=findViewById(R.id.edit_course_name);
        editCourseName.setText(courseName);
        courseIDFromCD = getIntent().getIntExtra("courseIDFromEdit",-1);
        courseTermIDFromCD= getIntent().getIntExtra("termIDPassingOn",-1);


        screenCourseStart=findViewById(R.id.edit_course_start);
        screenCourseEnd=findViewById(R.id.edit_course_end);
        repository= new MyRepository(getApplication());
        currentCourse = repository.getAllCourses();
        allTerms = repository.getAllTerm();
        editInstructorName=(findViewById(R.id.edit_course_instructor));
        editInstructorEmail=(findViewById(R.id.edit_instructor_email));
        editInstructorPhone=(findViewById(R.id.edit_instructor_phone));

        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        Date courseStart=null;
        Date courseEnd=null;

        if(courseName!=null){

            for(Course ci: currentCourse){
                courseInstructor=ci.getInstructorName();
                instructorEmail=ci.getEmail();
                instructorPhone=ci.getPhone();
                courseID=ci.getCourseID();
                courseStart=ci.getCourseStartDate();
                courseEnd=ci.getCourseEndDate();
            }

        }



        editInstructorName.setText(courseInstructor);
        editInstructorEmail.setText(instructorEmail);
        editInstructorPhone.setText(instructorPhone);



        Spinner spinner = (Spinner) findViewById(R.id.editcourseStatusSpinner);
       // Spinner termSpinner= (Spinner)findViewById(R.id.courseTermSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        //termSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Plan to take");
        categories.add("In Progress");
        categories.add("Completed");
        categories.add("Dropped");


        List<String>termList=new ArrayList<>();
        for(Term tl: allTerms){
            termList.add(tl.getTermName());
        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> termListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, termList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        //termSpinner.setAdapter(termListAdapter);

        if(courseName!=null){

            if(courseStart !=null){
                String courseStartFormat = sdf.format(courseStart);

                screenCourseStart.setText(courseStartFormat);

            }
            if(courseEnd!=null){
                String courseEndFormat=sdf.format(courseEnd);
                screenCourseEnd.setText(courseEndFormat);
            }
            String currentCourseStatus= getCurrentCourseStatus(courseName);
            int spinnerPosition = dataAdapter.getPosition(currentCourseStatus);
            spinner.setSelection(spinnerPosition);

            String currentTermName = getCurrentTerm(courseName);
            int spinnerTermPosition = termListAdapter.getPosition(currentTermName);
            //termSpinner.setSelection(spinnerTermPosition);
        }
        spinner.setOnItemSelectedListener(this);
        //termSpinner.setOnItemSelectedListener(this);

        screenCourseStart=findViewById(R.id.edit_course_start);
        screenCourseEnd=findViewById(R.id.edit_course_end);

        editCourseStart= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String formatDate = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);

                formatStartDate();

            }
        };

        screenCourseStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditCourseDetail.this, editCourseStart, calendarStart
                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editCourseEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                calendarEnd.set(Calendar.YEAR, year);
                calendarEnd.set(Calendar.MONTH, month);
                calendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String formatEndDate = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(formatEndDate, Locale.US);

                formatEndDate();

            }
        };

        screenCourseEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(EditCourseDetail.this, editCourseEnd, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void formatStartDate() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        screenCourseStart.setText(sdf.format(calendarStart.getTime()));
    }

    private void formatEndDate(){
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        screenCourseEnd.setText(sdf.format(calendarEnd.getTime()));
    }

    //hide keyboard with touchevent
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public String getCurrentCourseStatus(String courseName){
        for (Course s: currentCourse){
            if(s.getCourseName().equals(courseName)){
                courseStatus = s.getCourseStatus();
            }
        }
        return courseStatus;
    }

    public String getCurrentTerm(String courseName){
        for (Course s: currentCourse){
            if(s.getCourseName().equals(courseName)){
                courseTermID= s.getCourseTermID();
                for(Term tid: allTerms){
                    if(courseTermID==tid.getTermID()){
                        termNameSpin= tid.getTermName();
                    }
                }
            }
        }
        return termNameSpin;
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

    public void saveCourse(View view) {
        int saveCourseID = courseID;
        int courseIDCheck =0;
        courseIDFromCD = getIntent().getIntExtra("courseIDFromEdit",-1);
        courseTermIDFromCD= getIntent().getIntExtra("termIDPassingOn",-1);

        editCourseName=findViewById(R.id.edit_course_name);
        editInstructorName=(findViewById(R.id.edit_course_instructor));
        editInstructorEmail=(findViewById(R.id.edit_instructor_email));
        editInstructorPhone=(findViewById(R.id.edit_instructor_phone));
        screenCourseStart=findViewById(R.id.edit_course_start);
        screenCourseEnd=findViewById(R.id.edit_course_end);

        String updateCourseName = editCourseName.getText().toString();
        String updateInstructorName = editInstructorName.getText().toString();
        String updateEmail= editInstructorEmail.getText().toString();
        String updatePhone=editInstructorPhone.getText().toString();

        Spinner courseStatusSpinner = (Spinner) findViewById(R.id.editcourseStatusSpinner);
        String newCourseStatus= courseStatusSpinner.getSelectedItem().toString();
        String newStartDate=screenCourseStart.getText().toString();
        String newEndDate=screenCourseEnd.getText().toString();


        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        Date startCourseDate= null;
        Date endCourseDate=null;

        try {
            startCourseDate = sdf.parse(newStartDate);
            endCourseDate=sdf.parse(newEndDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        List<Course> allCourses = repository.getAllCourses();
        for(Course c: allCourses){
            if(allCourses.size()==0){
                courseIDCheck=0;
                Log.d("workingCourse", String.valueOf(courseIDCheck));
            }
            else if(courseIDFromCD>0){
                courseIDCheck=courseIDFromCD;
                Log.d("working", String.valueOf(courseIDCheck));
            }
            else{
                courseIDCheck=-1;
            }
        }


        if(courseIDFromCD>0) {
            if (!(updateCourseName.isEmpty()) && startCourseDate!=null && endCourseDate!=null) {
                Course updateCourseInfo = new Course(courseIDFromCD, courseTermIDFromCD, updateCourseName,
                        startCourseDate, endCourseDate, newCourseStatus, updateInstructorName, updateEmail, updatePhone);
                try {
                    repository.updateCourse(updateCourseInfo);
                    Context context = getApplicationContext();
                    CharSequence text = "Course updated successfully";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Course name and dates can not be blank.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else if(courseIDCheck==0){
            if(!(updateCourseName.isEmpty()) && startCourseDate!=null && endCourseDate!=null) {
                Course newCourseInfo = new Course(++courseIDCheck, courseTermIDFromCD, updateCourseName,
                        startCourseDate, endCourseDate, newCourseStatus, updateInstructorName, updateEmail, updatePhone);

                try {
                    repository.insertCourse(newCourseInfo);
                    Context context = getApplicationContext();
                    CharSequence text = "New course added successfully";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Course name and dates can not be blank.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else if(courseIDCheck<0) {
            if (!(updateCourseName.isEmpty()) && startCourseDate!=null && endCourseDate!=null) {
                int newCourseID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID();
                Course newCourseInfo = new Course(++newCourseID, courseTermIDFromCD, updateCourseName,
                        startCourseDate, endCourseDate, newCourseStatus, updateInstructorName, updateEmail, updatePhone);

                try {
                    repository.insertCourse(newCourseInfo);
                    Context context = getApplicationContext();
                    CharSequence text = "New course added successfully";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Course name and dates can not be blank.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    }
}