package uz.yt.springdata.rest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.test.StudentDto;
import uz.yt.springdata.service.test.StudentService;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseDTO<StudentDto> add(@RequestBody StudentDto studentDto){
        return studentService.add(studentDto);
    }

}
