package uz.yt.springdata.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorResource {

    private final AuthorService authorService;

    @GetMapping("/get-all")
    public ResponseDTO<Page<AuthorDTO>> getAll(Integer size, Integer page){
        return authorService.getAll(size, page);
    }

}
