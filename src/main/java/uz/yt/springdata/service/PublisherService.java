package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Publisher;
import uz.yt.springdata.dto.PublisherDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.mapping.PublisherMapping;
import uz.yt.springdata.repository.PublisherRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository repository;

    public ResponseDTO<Page<PublisherDTO>> getAll(Integer size, Integer page){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Publisher> result = repository.findAll(pageRequest);

        List<PublisherDTO> list = result.stream()
                .map(a -> PublisherMapping.toDto(a, 0))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Page<PublisherDTO> response = new PageImpl<>(list, result.getPageable(), result.getTotalElements());

        return new ResponseDTO<>(true, 0, "OK", response);
    }
}