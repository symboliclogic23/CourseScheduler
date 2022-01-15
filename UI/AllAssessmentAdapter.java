package android.reserver.coursescheduler.All.UI;

import android.content.Context;
import android.content.Intent;
import android.reserver.coursescheduler.All.Entities.Assessments;

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


public class AllAssessmentAdapter extends RecyclerView.Adapter<AllAssessmentAdapter.AllAssessmentViewHolder>{
    //private MyRepository repository;
    private List<Assessments> mAllAssessments;

    private Context context;

    public AllAssessmentAdapter(List<Assessments> mAllAssessments, Context context) {

        this.mAllAssessments=mAllAssessments;
        this.context = context;
    }

    public void setAllAssessments(List<Assessments> allAssessments){
        mAllAssessments=allAssessments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_assessment_list_item,parent,false);
        return new AllAssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAssessmentViewHolder holder, int position) {
        //TermCourse termCourse = mTermCourse.getValue().get(position);
        Assessments assessments = mAllAssessments.get(position);
        if(mAllAssessments!=null) {
            holder.assessmentAllName.setText(assessments.getAssessmentName());
        }
        else{
            holder.assessmentAllName.setText("No Assessment");
        }


    }


    @Override
    public int getItemCount() {
        if(mAllAssessments!=null) {
            return mAllAssessments.size();

        }
        else return 0;

    }



    public class AllAssessmentViewHolder extends RecyclerView.ViewHolder {
        public TextView assessmentAllName;
        String formatDate = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);


        public AllAssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentAllName=itemView.findViewById(R.id.all_actual_assessment_name);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Assessments current=mAllAssessments.get(position);
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

