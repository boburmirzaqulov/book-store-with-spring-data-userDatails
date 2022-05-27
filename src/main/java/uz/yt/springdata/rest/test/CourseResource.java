package uz.yt.springdata.rest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.test.CourseDto;
import uz.yt.springdata.service.test.CourseService;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseResource {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseDTO<CourseDto> add(@RequestBody CourseDto courseDto){
        return courseService.add(courseDto);
    }
}
