package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.auth.UserRoles;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookResource {

    private final static Logger log = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;

    @GetMapping
    public ResponseDTO<Page<BookDTO>> getAll(@RequestParam Integer size, @RequestParam Integer page){
        return bookService.getAllBooks(size, page);
    }

    @GetMapping("/by-param")
    public ResponseDTO<?> getAllWithParam(@RequestParam MultiValueMap<String, String> params){
        return bookService.getAllWithParam(params);
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyAuthority('BOOK:CREATE', 'ROLE_BOOK_MANAGER')")
    public ResponseDTO<BookDTO> add(@RequestBody BookDTO bookDTO){

        return bookService.addNew(bookDTO);
    }

    @PutMapping
    public ResponseDTO<BookDTO> update(@RequestBody BookDTO bookDTO){
        return bookService.update(bookDTO);
    }
}
