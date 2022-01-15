package android.reserver.coursescheduler.All.Entities;

import android.reserver.coursescheduler.All.Database.DateTypeConverter;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;


@Entity(tableName="term_table")
@TypeConverters(DateTypeConverter.class)
public class Term {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="termID")
        private int termID;

        @ColumnInfo(name="termName")
        private String termName;

        @ColumnInfo(name="termStart")
        private Date termStartDate;

        @ColumnInfo(name="termEnd")
        private Date termEndDate;

        public int getTermID() {
                return termID;
        }

        public void setTermID(int termID) {
                this.termID = termID;
        }

        public String getTermName() {
                return termName;
        }

        public void setTermName(String termName) {
                this.termName = termName;
        }

        public Date getTermStartDate() {
                return termStartDate;
        }

        public void setTermStartDate(Date termStartDate) {
                this.termStartDate = termStartDate;
        }

        public Date getTermEndDate() {
                return termEndDate;
        }

        public void setTermEndDate(Date termEndDate) {
                this.termEndDate = termEndDate;
        }

        public Term(int termID, String termName, Date termStartDate, Date termEndDate) {
                this.termID=termID;
                this.termName = termName;
                this.termStartDate = termStartDate;
                this.termEndDate = termEndDate;
        }

        @Override
        public String toString() {
                return "Term{" +

                        "termName='" + termName + '\'' +
                        ", termStartDate=" + termStartDate +
                        ", termEndDate=" + termEndDate +
                        '}';
        }
}
