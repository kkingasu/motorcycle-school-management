package main.models;

import java.util.Date;

public class CourseModel {
    int course_id;
    String name;
    String description;
    String Type;
    Date date;
    Float cost;

    int range_number;

    public Boolean getRange_am() {
        return range_am;
    }

    public void setRange_am(Boolean range_am) {
        this.range_am = range_am;
    }

    public Boolean getRange_pm() {
        return range_pm;
    }

    public void setRange_pm(Boolean range_pm) {
        this.range_pm = range_pm;
    }

    Boolean range_am;
    Boolean range_pm;


    public int getRange_number() {
        return range_number;
    }

    public void setRange_number(int range_number) {
        this.range_number = range_number;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
