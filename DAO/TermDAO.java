package android.reserver.coursescheduler.All.DAO;

import android.reserver.coursescheduler.All.Entities.Term;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TermDAO {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insertTerm(Term term);

        @Update
        void updateTerm(Term term);

        @Delete
        void deleteTerm(Term term);

        @Query("DELETE FROM term_table WHERE termID=:termID")
        void deleteTermByID(int termID);

        @Query("SELECT * FROM term_table ORDER BY termID")
        List<Term> getAllTerms();



    }

