package uz.yt.springdata.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.context.annotation.Lazy;
import uz.yt.springdata.dao.Publisher;

import java.math.BigDecimal;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String name;
    private BigDecimal cost;
    private String genre;
    private String publishedDate;
    private Integer pageCount;
    private AuthorDTO author;
    private PublisherDTO publisherDTO;

    public BookDTO(Integer id, String name, BigDecimal cost, String genre, String publishedDate, Integer pageCount) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
    }

    public String toString(){
        return String.format("%d-kitob: \nNomi: %s \nNarxi: %.2f \nJanri: %s \nMuallif: %s", id, name, cost, genre, author);
    }
}
