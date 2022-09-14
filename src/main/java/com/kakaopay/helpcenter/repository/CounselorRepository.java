package com.kakaopay.helpcenter.repository;

import com.kakaopay.helpcenter.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    Counselor findById(String id);

    List<Counselor> findAllBy();

    Counselor findByIdAndPassword(String id, String password);

    boolean existsByIdAfter(String id);
}
