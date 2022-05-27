package uz.yt.springdata.mapping;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.context.annotation.Lazy;
import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dao.Publisher;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.PublisherDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PublisherMapping {

    public static PublisherDTO toDto(Publisher publisher, int tier){
        if (tier > 1) return null;

        List<BookDTO> list = publisher.getBooks()
                .stream()
                .map(a -> BookMapping.toDto(a, tier+1))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new PublisherDTO(
                publisher.getId(),
                publisher.getName(),
                AddressMapping.toDto(publisher.getAddress()),
                list);
    }

    public static Publisher toEntity(PublisherDTO publisherDTO){
        return  new Publisher(publisherDTO.getId(), publisherDTO.getName());
    }
}
