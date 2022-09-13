package com.kakaopay.helpCenter.api.user.domain.counselor.repository;

import com.kakaopay.helpCenter.api.user.domain.counselor.entity.Counselor;
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
