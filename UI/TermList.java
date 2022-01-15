package android.reserver.coursescheduler.All.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.Menu;
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


public class TermList extends AppCompatActivity {
    private MyRepository repository;
    private RecyclerView recyclerView2;
    private TextView textView;
    private TermAdapter termAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView2 = findViewById(R.id.recyclerView);

        repository = new MyRepository(getApplication());
        List<Term> allTerms=repository.getAllTerm();

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        final TermAdapter termAdapter = new TermAdapter(allTerms,TermList.this);
        recyclerView2.setAdapter(termAdapter);
        termAdapter.setTerm(allTerms);
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


    @Override
    public void onResume() {

        super.onResume();

        recyclerView2 = findViewById(R.id.recyclerView);
        repository = new MyRepository(getApplication());

        List<Term> allTerms=repository.getAllTerm();

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        final TermAdapter termAdapter = new TermAdapter(allTerms,TermList.this);
        recyclerView2.setAdapter(termAdapter);
        termAdapter.setTerm(allTerms);

    }

    @Override
    public void onRestart() {

        super.onRestart();

        recyclerView2 = findViewById(R.id.recyclerView);

        repository = new MyRepository(getApplication());
        List<Term> allTerms=repository.getAllTerm();

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        final TermAdapter termAdapter = new TermAdapter(allTerms,TermList.this);
        recyclerView2.setAdapter(termAdapter);
        termAdapter.setTerm(allTerms);
    }

    public void termDetail(View view) {
        Intent intent=new Intent(TermList.this, TermDetail.class);

        startActivity(intent);

    }

    public void newTerm(View view) {
        Intent intent2=new Intent(TermList.this, EditTermDetails.class);
        /*int newTerm = -1;
        intent.putExtra("newTerm",newTerm);*/
        startActivity(intent2);
    }
}