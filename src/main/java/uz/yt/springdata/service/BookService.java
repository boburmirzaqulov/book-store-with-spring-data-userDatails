package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dao.CustomBook;
import uz.yt.springdata.dao.Publisher;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.ValidatorDTO;
import uz.yt.springdata.helper.NumberHelper;
import uz.yt.springdata.helper.StringHelper;
import uz.yt.springdata.helper.Validator;
import uz.yt.springdata.helper.constants.AppResponseCode;
import uz.yt.springdata.helper.constants.AppResponseMessages;
import uz.yt.springdata.mapping.BookMapping;
import uz.yt.springdata.repository.AuthorRepository;
import uz.yt.springdata.repository.BookRepository;
import uz.yt.springdata.repository.PublisherRepository;
import uz.yt.springdata.repository.impl.BookRepositoryImpl;
import uz.yt.springdata.scheduling.ScheduleExample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookRepositoryImpl bookRepositoryImpl;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<BookDTO> addNew(BookDTO bookDTO){

        try {
            List<ValidatorDTO> errors = Validator.validateBook(bookDTO);
            if (errors.size() > 0) return new ResponseDTO<>(false, AppResponseCode.VALIDATION_ERROR,
                    AppResponseMessages.VALIDATION_ERROR, bookDTO, errors);

            Book book = BookMapping.toEntity(bookDTO);
            book.setId(null);

            Optional<Author> author = authorRepository.findById(bookDTO.getAuthor().getId());
            if (!author.isPresent()){
                return new ResponseDTO<>(false, AppResponseCode.NOT_FOUND, AppResponseMessages.NOT_FOUND,
                        bookDTO, List.of(new ValidatorDTO("authorId", AppResponseMessages.NOT_FOUND)));
            }

            Optional<Publisher> publisher = publisherRepository.findById(bookDTO.getPublisherDTO().getId());
            if (!publisher.isPresent()){
                return new ResponseDTO<>(false, AppResponseCode.NOT_FOUND, AppResponseMessages.NOT_FOUND,
                        bookDTO, List.of(new ValidatorDTO("publisherId", AppResponseMessages.NOT_FOUND)));
            }

            book.setAuthorId(author.get());
            book.setPublisher(publisher.get());

            bookRepository.save(book);

            return new ResponseDTO<>(true,
                    AppResponseCode.OK,
                    AppResponseMessages.OK,
                    BookMapping.toDto(book, 1),
                    null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, AppResponseCode.DATABASE_ERROR,
                    AppResponseMessages.DATABASE_ERROR, null, null);
        }
    }

    public ResponseDTO<List<BookDTO>> findByAuthorName(String name){
        List<Book> books = bookRepository.findByAuthorName(name, PageRequest.of(0, 3));
        if (books != null){
            List<BookDTO> bookDTOS = books
                    .stream()
                    .map(a -> BookMapping.toDto(a, 1))
                    .collect(Collectors.toList());
            return new ResponseDTO<>(true, 0, "OK", bookDTOS);
        }

        return new ResponseDTO<>(false, -1, "NOT FOUND", null);

    }

    public ResponseDTO<Page<BookDTO>> getAllBooks(Integer size, Integer page){
        PageRequest pageable = PageRequest.of(page, size);
        Page<Book> books = bookRepository.findAll(pageable);
        if (!books.isEmpty()){
            List<BookDTO> response = new ArrayList<>();
            for (Book book : books){
                response.add(BookMapping.toDto(book, 1));
            }

            Page<BookDTO> result = new PageImpl(response, books.getPageable(), books.getTotalElements());
            return new ResponseDTO<>(true, 0, "OK", result);
        }

        return new ResponseDTO<>(false, -1, "ERROR", null);
    }

    public ResponseDTO<BookDTO> update(BookDTO bookDTO) {
        try {
            if (bookDTO.getId() == null){
                return new ResponseDTO<>(false, -2, "ID bo'sh bo'lishi mumkin emas", bookDTO);
            }

            Optional<Book> _book = bookRepository.findById(bookDTO.getId());
            if (!_book.isPresent()){
                return new ResponseDTO<>(false, -3, "Bu ID bo'yicha ma'lumot mavjud emas", bookDTO);
            }
            Book book = _book.get();
            BookMapping.setEntity(book, bookDTO);

            bookRepository.save(book);

            return new ResponseDTO<>(true, 0, "OK", bookDTO);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, 0, e.getMessage(), null);
        }
    }

    public ResponseDTO<?> getAllWithParam(MultiValueMap<String, String> params) {
        return new ResponseDTO<>(true, 0, "OK", bookRepositoryImpl.getAllBooksByParam(params).get());
    }
}
