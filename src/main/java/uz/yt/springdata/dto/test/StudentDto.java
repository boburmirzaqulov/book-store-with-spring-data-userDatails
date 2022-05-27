package uz.yt.springdata.dto.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<CourseDto> courses;
}
