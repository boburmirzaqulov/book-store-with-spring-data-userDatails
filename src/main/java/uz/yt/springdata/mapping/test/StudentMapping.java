package uz.yt.springdata.mapping.test;

import uz.yt.springdata.dao.test.Student;
import uz.yt.springdata.dto.test.CourseDto;
import uz.yt.springdata.dto.test.StudentDto;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapping {

    public static Student toEntity(StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getAge());
    }

    public static StudentDto toDto(Student student){
        if (student == null) return null;

        List<CourseDto> courses = student.getCourses()
                .stream()
                .map(CourseMapping::toDto)
                .collect(Collectors.toList());

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                courses
                );
    }
}
