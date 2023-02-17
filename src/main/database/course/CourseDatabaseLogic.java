package main.database.course;

import main.models.CourseModel;

public class CourseDatabaseLogic {
    public static void submitCreateCourseDataToDatabase(CourseModel courseModel) {
        //TODO: Add logic to submit data to database, should probably make a db factory for doing different request...
        System.out.println("Submitting Course Data......");
        System.out.println(courseModel);
    }
}
