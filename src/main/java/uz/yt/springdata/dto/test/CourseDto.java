package uz.yt.springdata.dto.test;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CourseDto {
    private Integer id;
    @NonNull
    private String name;
    private int duration;
    private List<StudentDto> students;

    public CourseDto(Integer id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
}
