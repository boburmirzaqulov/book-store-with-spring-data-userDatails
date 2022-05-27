package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.mapping.AuthorMapping;
import uz.yt.springdata.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public ResponseDTO<Page<AuthorDTO>> getAll(Integer size, Integer page) {
        PageRequest request = PageRequest.of(page, size);

        Page<Author> response = authorRepository.findAll(request);
        List<AuthorDTO> authorDTOS = response
                .stream()
                .map(a -> AuthorMapping.toDto(a, 1))
                .collect(Collectors.toList());

        return new ResponseDTO<>(true, 0, "OK",
                new PageImpl<>(authorDTOS, response.getPageable(), response.getTotalElements()));
    }

}
