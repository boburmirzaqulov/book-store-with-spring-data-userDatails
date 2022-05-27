package uz.yt.springdata.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Book")
@NamedQuery(name = "Book.findByAuthorName",
    query = "select b from Book b where b.authorId.firstName like ?1" //JPQL - Java Persistence Query Language
)
@NamedStoredProcedureQuery(name="Book.countBooks",
        procedureName = "orderr",
        parameters = {
                @StoredProcedureParameter(name = "num1", mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(name = "num2", mode = ParameterMode.IN, type = Integer.class)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "book_id_seq")
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameuz")
    private String nameUz;

    @Column(name = "nameru")
    private String nameRu;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "page_count")
    private Integer pageCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author authorId;

    @Column(name = "genre")
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    public Book(Integer id, String nameUz, BigDecimal cost, Date publishedDate, Integer pageCount, String genre) {
        this.id = id;
        this.nameUz = nameUz;
        this.cost = cost;
        this.publishedDate = publishedDate;
        this.pageCount = pageCount;
        this.genre = genre;
    }
}
