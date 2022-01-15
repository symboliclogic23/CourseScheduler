package android.reserver.coursescheduler.All.DAO;


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
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course_table ORDER BY course_term_id and course_id")
    List<Course> getAllCourses();

    @Query("DELETE FROM course_table WHERE course_term_id=:termID")
    void deleteAllCoursesTerm(int termID);

    @Query("DELETE FROM course_table WHERE course_id=:courseID")
    void deleteCourseByID(int courseID);

    @Query("SELECT * FROM course_table WHERE course_term_id=:termID")
    List<Course>getAllCourseTerm(int termID);



}
