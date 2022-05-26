package com.example.products.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "new")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNew;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "news" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Image> imageList;


}
