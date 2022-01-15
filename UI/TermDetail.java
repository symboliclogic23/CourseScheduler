package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


public class TermDetail extends AppCompatActivity {
    //private static String termName;
   // String termName;
    //EditText textView5;
    MyRepository repository;
    String termName2;
    //RecyclerView recyclerView3;
    EditText termName7;

    int termID=-1;
    String termStartDate;
    String termEndDate;
    //List<Course> filteredCourses;
    //List<Term> filteredTerm;
    //Term termDetailName;
    //Button coursesButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String termName= getIntent().getStringExtra("termName");

        termID = getIntent().getIntExtra("termIDForRefresh",-1);
        termStartDate=getIntent().getStringExtra("termStartDate");
        termEndDate=getIntent().getStringExtra("termEndDate");
        TextView textView5=findViewById(R.id.detail_term_name);
        TextView termStartDateCon=findViewById(R.id.term_start_date);
        TextView termEndDateCon=findViewById(R.id.term_end_date);
        textView5.setText(termName);
        if(termStartDate!=null){
            termStartDateCon.setText(termStartDate);
        }
        if(termEndDateCon!=null){
            termEndDateCon.setText(termEndDate);
        }



        //setTermName(termName);

        repository= new MyRepository(getApplication());

    }

    @Override
    public void onResume(){
        super.onResume();
        termID = getIntent().getIntExtra("termIDForRefresh",-1);
        repository= new MyRepository(getApplication());
        String updatedTermName =getIntent().getStringExtra("termName");
        Date updatedTermStart =null;
        Date updatedTermEnd =null;
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        for(Term t: repository.getAllTerm()){
            if(t.getTermID()==termID){
                updatedTermName=t.getTermName();
                updatedTermStart=t.getTermStartDate();
                updatedTermEnd=t.getTermEndDate();

            }
        }

        TextView textView5=findViewById(R.id.detail_term_name);
        TextView termStartDateCon=findViewById(R.id.term_start_date);
        TextView termEndDateCon=findViewById(R.id.term_end_date);
        textView5.setText(updatedTermName);
        String termStartString=sdf.format(updatedTermStart);
        String termEndString=sdf.format(updatedTermEnd);

        if(termStartString!=null) {
            termStartDateCon.setText(termStartString);
        }
        if(termEndString!=null) {
            termEndDateCon.setText(termEndString);
        }
        textView5.setText(updatedTermName);


    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.term_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        TextView termStartDateCon =findViewById(R.id.term_start_date);
        TextView textView5=findViewById(R.id.detail_term_name);
        TextView termEndDateCon=findViewById(R.id.term_end_date);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                deleteTerm();
                return true;
            case R.id.notifyStart:

                String editTermStart=termStartDateCon.getText().toString();
                String termNameNotification=textView5.getText().toString();

                Date startDateNotify=null;
                try {
                    startDateNotify=sdf.parse(editTermStart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=startDateNotify.getTime();
                Intent intent= new Intent(TermDetail.this,MyReceiver.class);
                intent.putExtra("key",termNameNotification+" term start!" );
                PendingIntent sender=PendingIntent.getBroadcast(TermDetail.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEnd:

                String editTermEnd=termEndDateCon.getText().toString();
                String termNameNotification2=textView5.getText().toString();
                Date endDateNotify=null;
                try {
                    endDateNotify=sdf.parse(editTermEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger2=endDateNotify.getTime();
                Intent intent2= new Intent(TermDetail.this,MyReceiver.class);
                intent2.putExtra("key",termNameNotification2+" term ends!" );
                PendingIntent sender2=PendingIntent.getBroadcast(TermDetail.this, ++MainActivity.numAlert,intent2,0);
                AlarmManager alarmManager2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteTerm(){

        TextView textView5=findViewById(R.id.detail_term_name);
        String termDelete = textView5.getText().toString();
        termID = getIntent().getIntExtra("termIDForRefresh",-1);
        List<Course> associatedCourses = new ArrayList<>();



        for(Course c: repository.getAllCourses()) {
            if (c.getCourseTermID() == termID) {
                associatedCourses.add(c);
                Context context = getApplicationContext();
                CharSequence text = "Unable to delete term until associated courses deleted!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        try{
          if(termID>0 && associatedCourses.size()==0){
                    repository.deleteTermByID(termID);
                    Context context = getApplicationContext();
                    CharSequence text = "Deleted Successfully!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    finish();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void termCourses(View view) {
        TextView termName7;
        termID = getIntent().getIntExtra("termIDForRefresh",-1);
        termName7=(TextView) findViewById(R.id.detail_term_name);
        String screenName=termName7.getText().toString();

        Intent intent= new Intent(TermDetail.this, TermCourseList.class);
        intent.putExtra("termCourseName", screenName);
        /*for(Term t: repository.getAllTerm()){
            if(t.getTermName().equals(screenName)){
                termID= t.getTermID();

            }
        }*/
        intent.putExtra("termID2CourseList",termID);
        //int termID = 0;


        startActivity(intent);
        //startActivity(intent2);


    }


    public void termDetail(View view) {

        TextView termName8;
        TextView termStartDateCon=findViewById(R.id.term_start_date);
        TextView termEndDateCon=findViewById(R.id.term_end_date);
        termName8=findViewById(R.id.detail_term_name);
        termID = getIntent().getIntExtra("termIDForRefresh",-1);
        String screenName2=termName8.getText().toString();
        String editTermStart=termStartDateCon.getText().toString();
        String editTermEnd=termEndDateCon.getText().toString();

        Intent intent2= new Intent(TermDetail.this, EditTermDetails.class);
        intent2.putExtra("editTermName", screenName2);
        intent2.putExtra("editTermStart",editTermStart);
        intent2.putExtra("editTermEnd",editTermEnd);
        intent2.putExtra("termIDPass2Edit",termID);

        startActivity(intent2);
    }

}