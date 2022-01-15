package android.reserver.coursescheduler.All.Entities;


import android.reserver.coursescheduler.All.Database.DateTypeConverter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName="notes_table", foreignKeys = {
        @ForeignKey(
                entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id"

        )
})
@TypeConverters(DateTypeConverter.class)
public class Notes {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="notes_id")
    private int notesID;

    @ColumnInfo(name="course_id")
    private int courseID;

    private String note;



    public Notes(int notesID, int courseID, String note) {
        this.notesID = notesID;
        this.courseID = courseID;
        this.note = note;
    }

    public int getNotesID() {
        return notesID;
    }

    public void setNotesID(int notesID) {
        this.notesID = notesID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Notes{" +


                "note='" + note + '\'' +
                '}';
    }
}
