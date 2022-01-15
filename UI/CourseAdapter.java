package android.reserver.coursescheduler.All.UI;

import android.content.Context;
import android.content.Intent;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
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


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    //private MyRepository repository;
    private List<Course> mCourse;

    private Context context;

    public CourseAdapter(List<Course> mCourse, Context context) {
        //this.mTermCourse = mTermCourse;
        this.mCourse=mCourse;
        //this.mTerm=mTerm;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_item,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        //TermCourse termCourse = mTermCourse.getValue().get(position);
        Course course = mCourse.get(position);
        holder.courseName.setText(course.getCourseName());
        //holder.courseStatus.setText(course.getCourseStatus());


    }

    @Override
    public int getItemCount() {
        return mCourse.size();
       // return mTermCourse.getValue().size();
    }
    public void setCourses(List<Course> allCourses){
        mCourse=allCourses;
        notifyDataSetChanged();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView courseName;
        String formatDate = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.US);


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName=itemView.findViewById(R.id.term_name_course_row);
            //courseStatus=itemView.findViewById(R.id.termcourse_status_row);
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
}
