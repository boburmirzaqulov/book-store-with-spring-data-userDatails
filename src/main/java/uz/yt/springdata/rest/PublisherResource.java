package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.yt.springdata.dao.Publisher;
import uz.yt.springdata.dto.PublisherDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.PublisherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherResource {

    private final PublisherService publisherService;
    @GetMapping("/get-all")
    public ResponseDTO<Page<PublisherDTO>> getAll(@RequestParam(defaultValue = "20") Integer size,
                                                  @RequestParam(defaultValue = "0") Integer page){
        return publisherService.getAll(size, page);
    }
}
