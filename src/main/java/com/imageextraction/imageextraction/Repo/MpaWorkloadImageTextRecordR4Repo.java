package com.imageextraction.imageextraction.Repo;

import com.imageextraction.imageextraction.Entity.MpaWorkloadImageTextRecordR4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MpaWorkloadImageTextRecordR4Repo extends JpaRepository<MpaWorkloadImageTextRecordR4,String> {
}
