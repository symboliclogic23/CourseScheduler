package android.reserver.coursescheduler.All.UI;

import android.content.Context;
import android.content.Intent;
import android.reserver.coursescheduler.All.Database.MyRepository;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Term;
import android.reserver.coursescheduler.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/*
    Christina Murray
    C196
    Version 1.3

 */


public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{
    //private MyRepository repository;
    private List<Assessments> mAssessments;
    private Context context;


    private final LayoutInflater mInflater;



    public AssessmentAdapter(List<Assessments> mAssessments, Context context) {
        this.mAssessments= mAssessments;
        mInflater = LayoutInflater.from(context);
        this.context = context;

    }
    public void setAssessments(List<Assessments> allAssessments){
        mAssessments=allAssessments;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        Assessments assessments= mAssessments.get(position);
       // holder.assessmentName.setText(assessments.getAssessmentName());
        holder.assessmentName.setText(assessments.getAssessmentName());


    }

    @Override
    public int getItemCount() {
        if (mAssessments != null) {
            return mAssessments.size();
        } else return 0;
    }



    public class AssessmentViewHolder extends RecyclerView.ViewHolder {
       // public TextView assessmentName;
       public TextView assessmentName;



        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
           // assessmentName=itemView.findViewById(R.id.assessment_name);
            assessmentName=itemView.findViewById(R.id.actual_assessment_name);
            String formatDate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Assessments current=mAssessments.get(position);
                    String assessmentStart=sdf.format(current.getAssessmentStart().getTime());
                    String assessmentEnd=sdf.format(current.getAssessmentEnd().getTime());

                    Intent intent= new Intent(context, EditAssessmentDetails.class);
                    intent.putExtra("assessmentToEdit", current.getAssessmentName());
                    intent.putExtra("assessmentIDEdit", current.getAssessmentID());
                    intent.putExtra("courseAssessmentToEdit",current.getCourseID());
                    intent.putExtra("assessmentTypeToSet", current.getAssessmentType());
                    intent.putExtra("assessmentStart",assessmentStart);
                    intent.putExtra("assessmentEnd",assessmentEnd);
                    context.startActivity(intent);
                }
            });

        }
    }
}
