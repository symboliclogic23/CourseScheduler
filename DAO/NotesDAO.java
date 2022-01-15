package android.reserver.coursescheduler.All.DAO;

import android.reserver.coursescheduler.All.Entities.Notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNotes(Notes notes);

    @Update
    void updateNotes(Notes notes);

    @Delete
    void deleteNotes (Notes notes);

    @Query("SELECT * FROM notes_table ORDER BY notes_id")
    List<Notes> getAllNotes();

    @Query("DELETE FROM notes_table WHERE course_id=:courseID")
    void deleteAllNotesCourse(int courseID);

    @Query("SELECT * FROM notes_table WHERE course_id=:courseID")
    List<Notes> getAllCourseNotes(int courseID);

}
