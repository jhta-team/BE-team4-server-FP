package com.kj.faq.repository;

import com.kj.faq.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaqBoardRepository extends JpaRepository<FaqBoard,Long> {
    @Query("select b from FaqBoard b")
    Page<FaqBoard> findByAll(Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM faq_board fb " +
            "WHERE FAQ_CATEGORY_ID IN  (" +
            "    SELECT FAQ_CATEGORY_ID " +
            "    FROM faq_category fc " +
            "    WHERE BIG_FAQ_CATEGORY = :keyword)",nativeQuery = true)
    Page<FaqBoard> findByCategory(Pageable pageable, @Param("keyword") String keyword);
}
