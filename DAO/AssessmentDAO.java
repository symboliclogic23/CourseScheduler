package android.reserver.coursescheduler.All.DAO;

import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAssessments(Assessments assessments);

    @Update
    void updateAssessments(Assessments assessments);

    @Delete
    void deleteAssessments(Assessments assessments);

    @Query("SELECT * FROM assessment_table order by assessment_id")
    List<Assessments> getAllAssessments();

    @Query("DELETE FROM assessment_table WHERE course_id=:courseID")
    void deleteAllAssessmentsCourse(int courseID);

    @Query("SELECT * FROM assessment_table WHERE course_id=:courseID")
   List<Assessments> getAllAssessmentsCourse(int courseID);

}
