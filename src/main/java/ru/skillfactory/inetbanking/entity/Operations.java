package ru.skillfactory.inetbanking.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
public class Operations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer operation_type;
    private Integer balance;
    private LocalDateTime trans_date;
    private Long users_id;

}

