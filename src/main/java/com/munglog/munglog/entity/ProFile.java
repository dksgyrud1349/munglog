package com.munglog.munglog.entity;

import com.munglog.munglog.enums.IsDeleted;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOG_PROFILE")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProFile {

    @Id
    @Column(name = "DOG_ID", nullable = false)
    private String dogId;

    @Column(name = "DOG_NM", nullable = false)
    private String dogNm;

    @Column(name = "BREED", nullable = false)
    private String breed;

    @Column(name = "AGE", nullable = false)
    private int age;

    @Column(name = "WEIGHT", nullable = false)
    private double weight;

    @Column(name = "MEMBERSHIP_NO", nullable = false)
    private String membershipNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_DELETED", nullable = false)
    private IsDeleted isDeleted;

    @Column(name = "PROFILE_IMG_URL")
    private String profileImgUrl;
}
