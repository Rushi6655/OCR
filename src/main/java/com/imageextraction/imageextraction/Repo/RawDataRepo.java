package com.imageextraction.imageextraction.Repo;

import com.imageextraction.imageextraction.Entity.RawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataRepo extends JpaRepository<RawData,Integer> {

}
