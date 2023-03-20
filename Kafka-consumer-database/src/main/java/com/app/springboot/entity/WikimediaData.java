package com.app.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="wikimedia_recentchanges")
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Porque es grande
    @Lob
    private String wikiEventData;
}
