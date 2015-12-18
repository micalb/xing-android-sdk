/*
 * Copyright (С) 2015 XING AG (http://xing.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xing.api.model.user;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.squareup.moshi.Json;
import com.xing.api.model.XingCalendar;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a school.
 *
 * @author david.gonzalez
 * @see <a href="https://dev.xing.com/docs/post/users/me/educational_background/schools">School</a>
 */
public class School implements Serializable, Parcelable {
    private static final long serialVersionUID = -700850712160641923L;
    /** Creator object for Parcelable contract. */
    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel parcel) {
            return new School(parcel);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };
    private static final Pattern COMPILE = Pattern.compile(", ");

    /** School ID. */
    @Json(name = "id")
    private String id;
    /** Name of school. */
    @Json(name = "name")
    private String name;
    /** Degree. */
    @Json(name = "degree")
    private String degree;
    /** Additional notes such as specialized subjects. */
    @Json(name = "notes")
    private List<String> notes;
    /** Describes the field of study. */
    @Json(name = "subject")
    private String subject;
    /** Start date. */
    @Json(name = "begin_date")
    private XingCalendar beginDate;
    /** End date. */
    @Json(name = "end_date")
    private XingCalendar endDate;

    /**
     * Create a school object given id and name.
     *
     * @param id id of shcool.
     * @param name name of school.
     */
    public School(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    /** Create a simple School object with empty fields. */
    public School() {
    }

    /**
     * Create {@link Award} from {@link Parcel}.
     *
     * @param parcel Input {@link Parcel}
     */
    private School(Parcel parcel) {
        id = parcel.readString();
        name = parcel.readString();
        degree = parcel.readString();
        parcel.readList(notes, List.class.getClassLoader());
        subject = parcel.readString();
        beginDate = (XingCalendar) parcel.readSerializable();
        endDate = (XingCalendar) parcel.readSerializable();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof School)) {
            return false;
        }

        School school = (School) obj;
        return hashCode() == school.hashCode();
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(degree);
        parcel.writeList(notes);
        parcel.writeString(subject);
        parcel.writeSerializable(beginDate);
        parcel.writeSerializable(endDate);
    }

    /**
     * Return school ID.
     *
     * @return school id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set school id.
     *
     * @param id id of shcool.
     */
    public void setId(String id) {
        if (!TextUtils.isEmpty(id)) {
            this.id = id;
        }
    }

    /**
     * Return name of school.
     *
     * @return name of school.
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of school.
     *
     * @param name name of school.
     */
    public void setName(String name) {
        if (!TextUtils.isEmpty(name)) {
            this.name = name;
        }
    }

    /**
     * Return degree.
     *
     * @return degree.
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Set degree.
     *
     * @param degree degree.
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * Return additional notes such as specialized subjects.
     *
     * @return list of notes.
     */
    public List<String> getNotes() {
        return notes;
    }

    /**
     * Set additional notes such as specialized subjects.
     *
     * @param notes notes.
     */
    public void setNotes(String notes) {
        String[] splitNotes = COMPILE.split(notes);
        this.notes = Arrays.asList(splitNotes);
    }

    /**
     * Return subject of field of study.
     *
     * @return subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set subject of field of study.
     *
     * @param subject subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Return start date.
     *
     * @return start date.
     */
    public XingCalendar getBeginDate() {
        return beginDate;
    }

    /**
     * Set return date.
     *
     * @param beginDate start date.
     */
    public void setBeginDate(XingCalendar beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Return end date.
     *
     * @return end date.
     */
    public XingCalendar getEndDate() {
        return endDate;
    }

    /**
     * Set end date.
     *
     * @param endDate end date.
     */
    public void setEndDate(XingCalendar endDate) {
        this.endDate = endDate;
    }
}