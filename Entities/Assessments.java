package android.reserver.coursescheduler.All.Entities;


import android.reserver.coursescheduler.All.Database.DateTypeConverter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName="assessment_table",foreignKeys = {
        @ForeignKey(
                entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id"

        )
})
@TypeConverters(DateTypeConverter.class)
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="assessment_id")
    private int assessmentID;
    @ColumnInfo(name="course_id")
    private int courseID;
    private String assessmentName;
    private String assessmentType;
    private Date assessmentStart;
    private Date assessmentEnd;

    public Assessments(int assessmentID, int courseID, String assessmentName, String assessmentType, Date assessmentStart, Date assessmentEnd) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Date getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(Date assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public Date getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(Date assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    @Override
    public String toString() {
        return "Assessments{" +

                "assessmentName='" + assessmentName + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentStart=" + assessmentStart +
                ", assessmentEnd=" + assessmentEnd +
                '}';
    }
}
