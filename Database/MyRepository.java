package android.reserver.coursescheduler.All.Database;

import android.app.Application;
import android.reserver.coursescheduler.All.DAO.AssessmentDAO;
import android.reserver.coursescheduler.All.DAO.CourseDAO;
import android.reserver.coursescheduler.All.DAO.NotesDAO;

import android.reserver.coursescheduler.All.DAO.TermDAO;
import android.reserver.coursescheduler.All.Entities.Assessments;
import android.reserver.coursescheduler.All.Entities.Course;
import android.reserver.coursescheduler.All.Entities.Notes;
import android.reserver.coursescheduler.All.Entities.Term;


import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRepository
{
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private NotesDAO mNotesDAO;



    private List<Term> mAllTerm;
    private List<Course> mAllCourses;
    private List<Assessments> mAllAssessments;
    private List<Notes> mAllNotes;



    private static int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public MyRepository(Application application) {
        SchedulerDatabase db = SchedulerDatabase.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
        mNotesDAO=db.notesDAO();


    }


    public List<Term>getAllTerm() {
        databaseExecutor.execute(() ->{
            mAllTerm = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerm;
    }

    public void updateTerm(Term term) {
        databaseExecutor.execute(() ->{
            mTermDAO.updateTerm(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void insertTerm(Term term) {
        databaseExecutor.execute(() ->{
            mTermDAO.insertTerm(term);
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void deleteTerm(Term term) {
        databaseExecutor.execute(() ->{
            mTermDAO.deleteTerm(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void deleteTermByID(int termID) {
        databaseExecutor.execute(() ->{
            mTermDAO.deleteTermByID(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public List<Course>getAllCourses() {
        databaseExecutor.execute(() ->{
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void updateCourse(Course course) {
        databaseExecutor.execute(() ->{
            mCourseDAO.updateCourse(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void insertCourse(Course course) {
        databaseExecutor.execute(() ->{
            mCourseDAO.insertCourse(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void deleteCourse(Course course) {
        databaseExecutor.execute(() ->{
            mCourseDAO.deleteCourse(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteCourseByID(int courseID) {
        databaseExecutor.execute(() ->{
            mCourseDAO.deleteCourseByID(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<Assessments>getAllAssessments() {
        databaseExecutor.execute(() ->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void updateAssessments(Assessments assessments) {
        databaseExecutor.execute(() ->{
            mAssessmentDAO.updateAssessments(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void insertAssessments(Assessments assessments) {
        databaseExecutor.execute(() ->{
            mAssessmentDAO.insertAssessments(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteAssessments(Assessments assessments) {
        databaseExecutor.execute(() ->{
            mAssessmentDAO.deleteAssessments(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<Notes>getAllNotes() {
        databaseExecutor.execute(() ->{
            mAllNotes = mNotesDAO.getAllNotes();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllNotes;
    }

    public void updateNotes(Notes notes){
        databaseExecutor.execute(() ->{
            mNotesDAO.updateNotes(notes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void insertNotes(Notes notes){
        databaseExecutor.execute(() ->{
            mNotesDAO.insertNotes(notes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteNotes(Notes notes){
        databaseExecutor.execute(() ->{
            mNotesDAO.deleteNotes(notes);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void deleteNotesCourse(int courseID){
        databaseExecutor.execute(() ->{
            mNotesDAO.deleteAllNotesCourse(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteAllCourseAssessments(int courseID){
        databaseExecutor.execute(() ->{
            mAssessmentDAO.deleteAllAssessmentsCourse(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void deleteAllCourseTerm(int termID){
        databaseExecutor.execute(() ->{
            mCourseDAO.deleteAllCoursesTerm(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
