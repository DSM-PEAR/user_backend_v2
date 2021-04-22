package com.dsmpear.main.user_backend_v2.entity.report;

import com.dsmpear.main.user_backend_v2.entity.comment.Comment;
import com.dsmpear.main.user_backend_v2.entity.member.Member;
import com.dsmpear.main.user_backend_v2.entity.reportfile.ReportFile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_tbl")
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Access access;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Field field;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;

    @Column(name = "is_submitted", nullable = false)
    private Boolean isSubmitted;

    @Column(name = "file_name",nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String github;

    @Column(nullable = false)
    private String languages;

    @Column(nullable = false, name = "team_name")
    private String teamName;

    @Column(nullable = true)
    private String comment;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "report")
    @JsonBackReference
    private List<Member> members;

    @OneToOne(mappedBy = "report", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private ReportFile reportFile;

    @OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Comment> comments;

    /*public Report update(ReportRequest reportRequest) {
        this.title = reportRequest.getTitle();
        this.description = reportRequest.getDescription();
        this.languages = reportRequest.getLanguages();
        this.type = reportRequest.getType();
        this.access = reportRequest.getAccess();
        this.field = reportRequest.getField();
        this.grade = reportRequest.getGrade();
        this.isSubmitted = reportRequest.getIsSubmitted();
        this.fileName = reportRequest.getFileName();
        this.github = reportRequest.getGithub();
        return this;
    }*/

}
