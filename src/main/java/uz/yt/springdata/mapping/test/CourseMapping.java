package uz.yt.springdata.mapping.test;

import uz.yt.springdata.dao.test.Course;
import uz.yt.springdata.dao.test.Student;
import uz.yt.springdata.dto.test.CourseDto;
import uz.yt.springdata.dto.test.StudentDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseMapping {

    public static Course toEntity(CourseDto courseDto, List<StudentDto> studentDtos){
        Set<Student> students = new HashSet<>();
        if (studentDtos != null){
            studentDtos.stream()
                    .map(StudentMapping::toEntity)
                    .forEach(students::add);
        }

        return courseDto == null ? null : new Course(
                courseDto.getId(),
                courseDto.getName(),
                courseDto.getDuration(),
                students
                );
    }



    public static CourseDto toDto(Course course){
        return course == null ? null : new CourseDto(
                course.getId(),
                course.getName(),
                course.getDuration()
        );
    }
}
