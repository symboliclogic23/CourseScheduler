package android.reserver.coursescheduler.All.Database;

import android.content.Context;
import android.reserver.coursescheduler.All.DAO.AssessmentDAO;
import android.reserver.coursescheduler.All.DAO.CourseDAO;
import android.reserver.coursescheduler.All.DAO.NotesDAO;
import android.reserver.coursescheduler.All.DAO.TermDAO;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.All.Entities.Term;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Term.class, Course.class, Assessments.class, Notes.class}, version =17, exportSchema = false)
@TypeConverters(DateTypeConverter.class)
public abstract class SchedulerDatabase extends RoomDatabase{

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract NotesDAO notesDAO();


    private static volatile SchedulerDatabase INSTANCE;

        static SchedulerDatabase getDatabase(final Context context){
            if(INSTANCE==null){
                synchronized (SchedulerDatabase.class){
                    if(INSTANCE==null){
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),SchedulerDatabase.class,"MySchedulerDatabase.db")
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;

        }
 }

