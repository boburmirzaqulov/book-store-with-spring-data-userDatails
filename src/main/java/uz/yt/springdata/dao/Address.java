package uz.yt.springdata.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "region_id")
    private Integer regionId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "street")
    private String street;

    @Column(name = "homenumber")
    private String homeNumber;

    @Column(name = "orient")
    private String orient;

}
