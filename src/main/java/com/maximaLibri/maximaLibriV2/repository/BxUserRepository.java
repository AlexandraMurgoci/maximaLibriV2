package com.maximaLibri.maximaLibriV2.repository;

import com.maximaLibri.maximaLibriV2.model.BxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BxUserRepository extends JpaRepository<BxUser, Long> {
}

