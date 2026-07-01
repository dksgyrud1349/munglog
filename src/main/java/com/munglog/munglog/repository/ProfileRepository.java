package com.munglog.munglog.repository;

import com.munglog.munglog.entity.ProFile;
import com.munglog.munglog.enums.IsDeleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProFile, String>, JpaSpecificationExecutor<ProFile> {

    @Query("""
            SELECT  p
            FROM    ProFile p
            WHERE   p.membershipNo = :membershipNo
            AND     p.isDeleted = :isDeleted
            """)
    List<ProFile> dogList(@Param("membershipNo") String membershipNo
                        , @Param("isDeleted") IsDeleted isDeleted);

    @Query("""
            SELECT  p
            FROM    ProFile p
            WHERE   p.membershipNo = :membershipNo
            AND     p.isDeleted = :isDeleted
            AND     p.dogId = :dogId
            """)
    ProFile dogInfo(@Param("membershipNo") String membershipNo
                    , @Param("isDeleted") IsDeleted isDeleted
                    , @Param("dogId") String dogId);
}
