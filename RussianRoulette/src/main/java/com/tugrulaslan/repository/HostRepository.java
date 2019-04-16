package com.tugrulaslan.repository;

import com.tugrulaslan.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select h from Host h where h.address like %?1%")
    List<Host> findByAddressContaining(@Param("address") String address);

    Host findByAddress(String address);
}
