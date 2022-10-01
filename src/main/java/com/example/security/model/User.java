package com.example.security.model;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "\"user\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    @CreationTimestamp
    private Timestamp createDate;

}
