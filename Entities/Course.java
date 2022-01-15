package android.reserver.coursescheduler.All.Entities;


import android.reserver.coursescheduler.All.Database.DateTypeConverter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName="course_table", foreignKeys = {
        @ForeignKey(
                entity = Term.class,
                parentColumns = "termID",
                childColumns = "course_term_id"

        )
})
@TypeConverters(DateTypeConverter.class)
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="course_id")
    private int courseID;
    @ColumnInfo(name="course_term_id")
    private int courseTermID;
    private String courseName;
    private Date courseStartDate;
    private Date courseEndDate;
    private String courseStatus;
    private String instructorName;
    private String email;
    private String phone;

    public Course(int courseID, int courseTermID, String courseName, Date courseStartDate, Date courseEndDate, String courseStatus, String instructorName, String email, String phone) {
        this.courseID = courseID;
        this.courseTermID = courseTermID;
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.email = email;
        this.phone = phone;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseTermID() {
        return courseTermID;
    }

    public void setCourseTermID(int courseTermID) {
        this.courseTermID = courseTermID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Course{" +

                "courseName='" + courseName + '\'' +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
