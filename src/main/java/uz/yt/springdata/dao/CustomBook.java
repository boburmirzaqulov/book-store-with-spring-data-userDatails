package uz.yt.springdata.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class CustomBook {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nameuz")
    private String name;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "cost")
    private BigDecimal cost;

}
