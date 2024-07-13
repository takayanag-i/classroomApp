package jp.co.collasho.classroom.test;

import java.util.List;
import jp.co.collasho.classroom.dto.CourseCellDto;
import jp.co.collasho.classroom.dto.CourseDto;
import jp.co.collasho.classroom.service.enrollment.DisplayDriver;

public class CheckTable {
    public static void main(String[] arges) {
        DisplayDriver driver = new DisplayDriver();
        List<CourseDto> courses = driver.getCourses("1401");
        CourseCellDto[][] table = driver.getCourseTable(courses);
    }
}
