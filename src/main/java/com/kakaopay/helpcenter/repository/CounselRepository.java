package com.kakaopay.helpcenter.repository;

import com.kakaopay.helpcenter.entity.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselRepository extends JpaRepository<Counsel, Long> {

    List<Counsel> findAllBy();

    List<Counsel> findAllByCounselorIdIsNull();

}
