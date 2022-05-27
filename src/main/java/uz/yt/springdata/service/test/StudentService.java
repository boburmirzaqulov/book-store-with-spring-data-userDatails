package uz.yt.springdata.service.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.test.Course;
import uz.yt.springdata.dao.test.Student;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.test.CourseDto;
import uz.yt.springdata.dto.test.StudentDto;
import uz.yt.springdata.mapping.test.StudentMapping;
import uz.yt.springdata.repository.test.CourseRepository;
import uz.yt.springdata.repository.test.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

//    public ResponseDTO<StudentDto> getById(Integer id){
//        Optional<Student> student = studentRepository.findById(id);
//
//    }

    public ResponseDTO<StudentDto> add(StudentDto studentDto){
        Student student = StudentMapping.toEntity(studentDto);

        /*Kurslar idlarini stream orqali listga yig'ish */
        List<Integer> courseIds = studentDto.getCourses()
                .stream()
                .map(CourseDto::getId)
                .collect(Collectors.toList());

        //Hosil qilingan kurslar idlari orqali kurslarni bazadan olamiz
        Set<Course> courses = new HashSet<>(courseRepository.findAllById(courseIds));

        student.setCourses(courses);

        studentRepository.save(student);

        return new ResponseDTO<>(true, 0, "OK", StudentMapping.toDto(student));
    }

}
