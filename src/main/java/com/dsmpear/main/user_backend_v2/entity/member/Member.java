package com.dsmpear.main.user_backend_v2.entity.member;

import com.dsmpear.main.user_backend_v2.entity.report.Report;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_tbl")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30,nullable = false, name= "user_email")
    private String userEmail;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "report")
    private Report report;

}
