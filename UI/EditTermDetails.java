package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
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


public class EditTermDetails extends AppCompatActivity {
    MyRepository repository;
    final Calendar calendarStart = Calendar.getInstance();
    final Calendar calendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener editTermStart;
    DatePickerDialog.OnDateSetListener editTermEnd;
    EditText termNameEdit;
    EditText termStartEdit;
    EditText termEndEdit;
    int termIDPassed;
    String termName;
    int newTermId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        termName = getIntent().getStringExtra("editTermName");
        //newTermId=getIntent().getIntExtra("newTerm",-1);
        termIDPassed= getIntent().getIntExtra("termIDPass2Edit",-1);
        repository= new MyRepository(getApplication());
        termNameEdit = findViewById(R.id.edit_term_name_input);

        String termStartString = getIntent().getStringExtra("editTermStart");
        String termEndString=getIntent().getStringExtra("editTermEnd");

        if(termName!=null){
            termNameEdit.setText(termName);
            if(termStartString!=null) {
                termStartEdit = findViewById(R.id.edit_term_start_input);
                termStartEdit.setText(termStartString);
            }
            if(termEndString!=null) {
                termEndEdit = findViewById(R.id.edit_term_end_input);
                termEndEdit.setText(termEndString);
            }

        }


        termStartEdit = findViewById(R.id.edit_term_start_input);
        termEndEdit=findViewById(R.id.edit_term_end_input);

        editTermStart = new DatePickerDialog.OnDateSetListener() {
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

        termStartEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditTermDetails.this, editTermStart, calendarStart
                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editTermEnd = new DatePickerDialog.OnDateSetListener() {
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

        termEndEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(EditTermDetails.this, editTermEnd, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



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

    private void formatStartDate() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        termStartEdit.setText(sdf.format(calendarStart.getTime()));
    }

    private void formatEndDate(){
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        termEndEdit.setText(sdf.format(calendarEnd.getTime()));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void SaveTermChanges(View view) {
        repository= new MyRepository(getApplication());
        List<Term> allTerm =repository.getAllTerm();

        termName = getIntent().getStringExtra("editTermName");
        termIDPassed= getIntent().getIntExtra("termIDPass2Edit",-1);
        //newTermId=getIntent().getIntExtra("newTerm",-1);
        EditText termNameEdit2 = findViewById(R.id.edit_term_name_input);
        EditText termStartEdit2 = findViewById(R.id.edit_term_start_input);
        EditText termEndEdit2=findViewById(R.id.edit_term_end_input);
        String updateTermName = termNameEdit2.getText().toString();
        int termID = 0;
        for(Term t: allTerm){
            if(allTerm.size()==0){
                termID=0;
                Log.d("working", String.valueOf(termID));
            }
            else if(t.getTermName().equals(termName)){
                termID= t.getTermID();
                Log.d("working", String.valueOf(termID));
            }
            else{
                termID=-1;
            }
        }


        String newTermStart=termStartEdit2.getText().toString();
        String newTermEnd=termEndEdit2.getText().toString();


        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        Date startTermDate=null;
        Date endTermDate=null;

        try {
            startTermDate = sdf.parse(newTermStart);
            endTermDate=sdf.parse(newTermEnd);
        }
        catch (ParseException e){
            e.printStackTrace();
        }







        if (termIDPassed>0) {
            if (!(updateTermName.isEmpty()) && startTermDate!=null && endTermDate!=null) {
                try {
                    Term updateTerm = new Term(termIDPassed, updateTermName, startTermDate, endTermDate);
                    repository.updateTerm(updateTerm);

                    Context context = getApplicationContext();
                    CharSequence text = "Term update successful";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Term name and dates must have value, please enter";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else if(termID==0){
            //int newTermID2=repository.getAllTerm().get(repository.getAllTerm().size()-1).getTermID();
            Log.d("working", String.valueOf(termID));
            Log.d("term#", String.valueOf(termID));
            if(!(updateTermName.isEmpty()) && startTermDate!=null && endTermDate!=null) {
                Term insertNewTerm = new Term(++termID, updateTermName, startTermDate, endTermDate);
                repository.insertTerm(insertNewTerm);
                Context context = getApplicationContext();
                CharSequence text = "Term added successful";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Term name and dates must have value, please enter";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else if(termID==-1) {
            if(!(updateTermName.isEmpty()) && startTermDate!=null && endTermDate!=null) {

                int newTermID2 = allTerm.get(allTerm.size() - 1).getTermID();
                newTermID2 = newTermID2 + 1;
                Log.d("working", String.valueOf(newTermID2));
                Log.d("term#", String.valueOf(newTermID2));
                Term insertNewTerm = new Term(newTermID2, updateTermName, startTermDate, endTermDate);


                try {

                    repository.insertTerm(insertNewTerm);
                    Context context = getApplicationContext();
                    CharSequence text = "Term added successful";
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
                CharSequence text = "Term name and dates must have value, please enter";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


            }

        }

    public void recreate (){

    }

}