package android.reserver.coursescheduler.All.UI;

import android.content.Context;
import android.content.Intent;

import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/*
    Christina Murray
    C196
    Version 1.3

 */
public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    public class TermViewHolder extends RecyclerView.ViewHolder {
        public TextView termName;
        public TextView termStart;
        public TextView termEnd;



        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termName=itemView.findViewById(R.id.term_title_row);
            //termStart=itemView.findViewById(R.id.term_start_row);
            //termEnd=itemView.findViewById(R.id.term_end_row);
            String formatDate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Term current=mTerm.get(position);
                    String termStartString=sdf.format(current.getTermStartDate().getTime());
                    String termEndString=sdf.format(current.getTermEndDate().getTime());

                    Intent intent= new Intent(context, TermDetail.class);
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termIDForRefresh",current.getTermID());
                    intent.putExtra("termStartDate",termStartString);
                    intent.putExtra("termEndDate",termEndString);

                    //intent.putExtra("termID", current.getTermID());
                    //intent.putExtra("name", current.getThingName());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Term> mTerm;
    private final LayoutInflater mInflater;

    private Context context;

    public TermAdapter(List<Term> mTerm, Context context) {
        this.mTerm = mTerm;
        mInflater = LayoutInflater.from(context);
        this.context = context;

    }
    public void setTerm(List<Term> term){
        mTerm=term;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        Term term = mTerm.get(position);
        if(mTerm!=null) {
            holder.termName.setText(term.getTermName());
        }
        else {
            // Covers the case of data not being ready yet.
            holder.termName.setText("No Term");
        }

       // holder.termStart.setText(term.getTermStartDate());

    }

    @Override
    public int getItemCount() {
        if(mTerm!=null) {
            return mTerm.size();

        }
        else return 0;
    }



}