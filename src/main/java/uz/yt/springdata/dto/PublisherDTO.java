package uz.yt.springdata.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
@AllArgsConstructor
public class PublisherDTO {

    private Integer id;
    private String name;
    private AddressDTO address;
    private List<BookDTO> books;
}
