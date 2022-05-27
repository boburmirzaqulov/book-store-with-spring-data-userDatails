package uz.yt.springdata.mapping;

import org.springframework.context.annotation.Lazy;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.BookDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorMapping {

    public static AuthorDTO toDto(Author entity, int tier){

        if (tier > 1) return null;

        List<BookDTO> list = entity.getBooks()
                .stream()
                .map(a -> BookMapping.toDto(a, tier+1))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new AuthorDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                list
        );
    }

    public static Author toEntity(AuthorDTO author){
        return author == null ? null :
                new Author(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate());
    }
}
