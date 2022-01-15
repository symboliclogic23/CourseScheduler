package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


public class EditAssessmentDetails extends AppCompatActivity {
    String assessmentName;
    EditText editAssessmentName;
    EditText editStart;
    EditText editEnd;
    MyRepository repository;
    Assessments currentAssessment;
    Course course;
    int assessmentCourseID =-1;
    int assessmentID=-1;
    String assessmentCourseName=null;
    String newAssessmentCourseName;
    String rbValue;
    String item;
    private View view;
    private RadioGroup radioGroup;
    private RadioButton performanceButton;
    private RadioButton objectiveButton;
    String assessmentType;
    private final int EDIT_RESULT_CODE = 0;
    final Calendar calendarStart = Calendar.getInstance();
    final Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener editStartDate;
    DatePickerDialog.OnDateSetListener editEndDate;
    String assessmentStart;
    String assessmentEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment_details);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        assessmentName= getIntent().getStringExtra("assessmentToEdit");
        assessmentCourseID= getIntent().getIntExtra("courseAssessmentToEdit",-1);
        assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
        assessmentType=getIntent().getStringExtra("assessmentTypeToSet");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");

        editAssessmentName=findViewById(R.id.editAssessmentNameInput);
        editAssessmentName.setText(assessmentName);
        editStart=findViewById(R.id.assessment_start_edit_input);
        editEnd =findViewById(R.id.assessment_end_edit_input);

        repository= new MyRepository(getApplication());
        List<Assessments> currentAssessment = repository.getAllAssessments();


        if(assessmentName!=null) {
            editStart.setText(assessmentStart);
            editEnd.setText(assessmentEnd);
            for (Assessments a : currentAssessment) {
                if (a.getAssessmentName().equals(assessmentName)) {
                    assessmentCourseID = a.getAssessmentID();
                    rbValue=a.getAssessmentType();
                }
            }

            for (Course b : repository.getAllCourses()) {
                if (b.getCourseID() == assessmentCourseID) {
                    assessmentCourseName = b.getCourseName();
                }
            }
            String performanceValue = "Performance";
            String objectiveValue = "Objective";

            radioGroup = (RadioGroup)findViewById(R.id.eARadio_group_assessment);
            performanceButton =(RadioButton) findViewById(R.id.performanceRBEA);
            objectiveButton= (RadioButton) findViewById(R.id.objectiveRBEA);

            if(assessmentType==null){
                objectiveButton.setChecked(true);
            }

            if (assessmentType.contains(objectiveValue)){
                objectiveButton.setChecked(true);
            }
            else if(assessmentType.contains(performanceValue)){
                performanceButton.setChecked(true);

            }
        }
        editStart=findViewById(R.id.assessment_start_edit_input);
        editEnd =findViewById(R.id.assessment_end_edit_input);


        editStartDate = new DatePickerDialog.OnDateSetListener() {
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

        editStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditAssessmentDetails.this, editStartDate, calendarStart
                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editEndDate = new DatePickerDialog.OnDateSetListener() {
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

        editEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(EditAssessmentDetails.this, editEndDate, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }


    private void formatStartDate() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        editStart.setText(sdf.format(calendarStart.getTime()));
    }

    private void formatEndDate(){
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        editEnd.setText(sdf.format(calendarEnd.getTime()));
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessments_detail_menu, menu);
        return true;
    }


    /*protected void onStart(){
        super.onStart();
        assessmentName= getIntent().getStringExtra("assessmentToEdit");
        assessmentCourseID= getIntent().getIntExtra("courseAssessmentToEdit",-1);
        assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
        assessmentType=getIntent().getStringExtra("assessmentTypeToSet");

        editAssessmentName=findViewById(R.id.editAssessmentNameInput);
        editAssessmentName.setText(assessmentName);

        repository= new MyRepository(getApplication());
        List<Assessments> currentAssessment = repository.getAllAssessments();


        if(assessmentName!=null) {
            for (Assessments a : currentAssessment) {
                if (a.getAssessmentName().equals(assessmentName)) {
                    assessmentCourseID = a.getAssessmentID();
                    rbValue=a.getAssessmentType();
                }
            }

            for (Course b : repository.getAllCourses()) {
                if (b.getCourseID() == assessmentCourseID) {
                    assessmentCourseName = b.getCourseName();
                }
            }
            String performanceValue = "Performance";
            String objectiveValue = "Objective";

            radioGroup = (RadioGroup)findViewById(R.id.eARadio_group_assessment);
            performanceButton =(RadioButton) findViewById(R.id.performanceRBEA);
            objectiveButton= (RadioButton) findViewById(R.id.objectiveRBEA);

            if (assessmentType.contains(objectiveValue)){
                objectiveButton.setChecked(true);
            }
            else if(assessmentType.contains(performanceValue)){
                performanceButton.setChecked(true);

            }
        }


    }*/


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
        String newStartDate=editStart.getText().toString();
        String newEndDate=editEnd.getText().toString();
        EditText getUpdateAssessmentName=findViewById(R.id.editAssessmentNameInput);
        String assessmentNameN=getUpdateAssessmentName.getText().toString();
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        Date startTestDate= null;
        Date endTestDate=null;

        switch (item.getItemId()) {
            case android.R.id.home:
                /*assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
                Intent refreshScreen=new Intent(EditAssessmentDetails.this, AssessmentDetail.class);
                refreshScreen.putExtra("courseAssessmentToEdit",assessmentID);*/
                finish();
                return true;
            case R.id.deleteTest:
                assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
                if(assessmentID>0){
                    deleteAssessment();
                    finish();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "No Assessment to Delete. Does not exist.";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            case R.id.notifyStartTest:
                assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
                if(assessmentID>0) {
                    Date startTestNotify = null;

                    try {
                        startTestNotify = sdf.parse(newStartDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Long trigger = startTestNotify.getTime();
                    Intent intent = new Intent(EditAssessmentDetails.this, MyReceiver.class);
                    intent.putExtra("key", assessmentNameN + " assessment begins!");
                    PendingIntent sender = PendingIntent.getBroadcast(EditAssessmentDetails.this, ++MainActivity.numAlert, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                }
                return true;
            case R.id.notifyEndTest:
                assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
                if(assessmentID>0) {
                    Date endTestNotify = null;

                    try {
                        endTestNotify = sdf.parse(newEndDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Long trigger2 = endTestNotify.getTime();
                    Intent intent2 = new Intent(EditAssessmentDetails.this, MyReceiver.class);
                    intent2.putExtra("key", assessmentNameN + " assessment ends!");
                    PendingIntent sender2 = PendingIntent.getBroadcast(EditAssessmentDetails.this, ++MainActivity.numAlert, intent2, 0);
                    AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onRadioButtonClicked(View view) {
        this.view = view;

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.objectiveRBEA:
                if (checked)
                    rbValue="Objective";
                break;
            case R.id.performanceRBEA:
                if (checked)
                    rbValue="Performance";
                break;
        }
    }


    public void deleteAssessment(){
        assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
        int toastID = assessmentID;
        if(assessmentID>0){
            for(Assessments da: repository.getAllAssessments()){
                if(da.getAssessmentID()==assessmentID){
                    repository.deleteAssessments(da);
                    Context context = getApplicationContext();
                    CharSequence text = "Assessment deleted successfully: " + assessmentID;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }
    }


    public void saveAssessment(View view) {
        int assessmentID2Check=0;
        int updateCourseID;
        EditText getUpdateAssessmentName;
        String setAssessmentName;
        List<Assessments> allAssessments=repository.getAllAssessments();

        assessmentID= getIntent().getIntExtra("assessmentIDEdit",-1);
        assessmentCourseID= getIntent().getIntExtra("courseAssessmentToEdit",-1);
        Log.d(String.valueOf(assessmentCourseID), "print before insert");
        getUpdateAssessmentName=findViewById(R.id.editAssessmentNameInput);
        setAssessmentName= getUpdateAssessmentName.getText().toString();

        updateCourseID=assessmentCourseID;
        String newStartDate=editStart.getText().toString();
        String newEndDate=editEnd.getText().toString();

        for(Assessments at: allAssessments){
            if(allAssessments.size()==0){
                assessmentID2Check=0;
                Log.d("workingTest", String.valueOf(assessmentID2Check));
            }
            else if(assessmentID>0){
                assessmentID2Check=assessmentID;
                Log.d("working", String.valueOf(assessmentID2Check));
            }
            else{
                assessmentID2Check=-1;
            }
        }

        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        Date startTestDate= null;
        Date endTestDate=null;

        try {
            startTestDate = sdf.parse(newStartDate);
            endTestDate=sdf.parse(newEndDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.eARadio_group_assessment);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);

        String rbValue = (String) radioButton.getText();


        if(assessmentID>-1){
            //repository= new MyRepository(getApplication());
           /*List<Assessments> currentAssessment = repository.getAllAssessments();
            for (Assessments a : currentAssessment) {
                if (a.getAssessmentName().equals(assessmentName)) {
                    //assessmentCourseID = a.getAssessmentID();
                    assessmentID= a.getAssessmentID();
                }
            }*/


            try {
                if (!(setAssessmentName.isEmpty()) && startTestDate!= null && endTestDate != null) {
                    Assessments updateAssessment = new Assessments(assessmentID, assessmentCourseID, setAssessmentName, rbValue, startTestDate, endTestDate);
                    repository.updateAssessments(updateAssessment);

                    Context context = getApplicationContext();
                    CharSequence text = "Assessmment updated successfully";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Assessment name & dates required, please complete";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            catch(Exception e){
                    e.printStackTrace();
                }



        }
        else if(assessmentID2Check==0){
            try {
                if(!(setAssessmentName.isEmpty()) && startTestDate != null && endTestDate != null){
                //Log.d(String.valueOf(assessmentCourseID), "print before insert");
                //assessmentID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID();
                Assessments newAssessment = new Assessments(++assessmentID2Check, assessmentCourseID, setAssessmentName, rbValue, startTestDate, endTestDate);
                repository.insertAssessments(newAssessment);

                Context context = getApplicationContext();
                CharSequence text = "Assessment added successfully";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",assessmentID2Check);
                setResult(RESULT_OK,returnIntent);
                finish();

            }
               else{
                    Context context = getApplicationContext();
                    CharSequence text = "Assessment name & dates required, please complete";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(assessmentID2Check==-1){
            try {
                if (!(setAssessmentName.isEmpty()) && startTestDate != null && endTestDate != null) {
                    //Log.d(String.valueOf(assessmentCourseID), "print before insert");
                    assessmentID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID();
                    Assessments newAssessment = new Assessments(++assessmentID, assessmentCourseID, setAssessmentName, rbValue, startTestDate, endTestDate);
                    repository.insertAssessments(newAssessment);

                    Context context = getApplicationContext();
                    CharSequence text = "Assessment added successfully";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", assessmentCourseID);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Assessment name & dates required, please complete";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}