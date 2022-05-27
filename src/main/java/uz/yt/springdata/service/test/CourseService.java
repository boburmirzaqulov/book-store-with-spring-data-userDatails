package uz.yt.springdata.service.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.test.Course;
import uz.yt.springdata.dao.test.Student;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.test.CourseDto;
import uz.yt.springdata.dto.test.StudentDto;
import uz.yt.springdata.mapping.test.CourseMapping;
import uz.yt.springdata.repository.test.CourseRepository;
import uz.yt.springdata.repository.test.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public ResponseDTO<CourseDto> add(CourseDto courseDto){
        List<Integer> studentIds = courseDto
                .getStudents()
                .stream()
                .map(StudentDto::getId)
                .collect(Collectors.toList());

        Set<Student> students = new HashSet<>(studentRepository.findAllById(studentIds));

        Course course = CourseMapping.toEntity(courseDto, null);
        course.setStudents(students);

        courseRepository.save(course);

        return new ResponseDTO<>(true, 0, "OK", CourseMapping.toDto(course));
    }
}
