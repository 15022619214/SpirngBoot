package com.spb.boot.Repository;

import com.spb.boot.Model.Testinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TestinfoRepository extends JpaRepository<Testinfo,Integer> ,JpaSpecificationExecutor<Testinfo> {

    public List<Testinfo> findAll();
}
