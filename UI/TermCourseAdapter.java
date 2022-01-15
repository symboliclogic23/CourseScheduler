package android.reserver.coursescheduler.All.UI;

import android.content.Context;
import android.content.Intent;
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
public class TermCourseAdapter extends RecyclerView.Adapter<TermCourseAdapter.TermCourseViewHolder> {
    private List<Course> mCourse;
    private Context context;

    class TermCourseViewHolder extends RecyclerView.ViewHolder {
        public TextView courseName;
        public TextView termStart;
        public TextView termEnd;



        public TermCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName=itemView.findViewById(R.id.termcoursename);
            String formatDate = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Course current=mCourse.get(position);
                    String courseStartDate=sdf.format(current.getCourseStartDate().getTime());
                    String courseEndDate=sdf.format(current.getCourseEndDate().getTime());
                    Intent intent= new Intent(context, CourseDetail.class);
                    intent.putExtra("termCourseNameDet", current.getCourseName());
                    intent.putExtra("termIDPassingOn",current.getCourseTermID());
                    intent.putExtra("courseIDPassing", current.getCourseID());
                    intent.putExtra("courseTermStart",courseStartDate);
                    intent.putExtra("courseTermEnd",courseEndDate);

                    context.startActivity(intent);
                }
            });
        }
    }

    private final LayoutInflater mInflater;



    public TermCourseAdapter(List<Course> mCourse, Context context) {
        this.mCourse= mCourse;
        mInflater = LayoutInflater.from(context);
        this.context = context;

    }

    @NonNull
    @Override
    public TermCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_course_list_item,parent,false);
        return new TermCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermCourseViewHolder holder, int position) {
       Course course= mCourse.get(position);
        if (mCourse != null) {
           // final Course current = mCourse.get(position);
            //String courseName2=current.getCourseName();
            holder.courseName.setText(course.getCourseName());

        } else {
            // Covers the case of data not being ready yet.
            holder.courseName.setText("No Course");
        }
        //holder.courseName.setText(course.getCourseName());
       // holder.termStart.setText(term.getTermStartDate());

    }
    public void setCourse(List<Course> courses){
        mCourse=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCourse != null) {
            return mCourse.size();
        } else return 0;
    }


}