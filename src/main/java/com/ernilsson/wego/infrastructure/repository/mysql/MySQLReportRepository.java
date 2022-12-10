package com.ernilsson.wego.infrastructure.repository.mysql;

import com.ernilsson.wego.infrastructure.repository.mysql.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MySQLReportRepository extends JpaRepository<ReportEntity, UUID> {
    @Query("SELECT re FROM report re WHERE ST_Distance_Sphere(point(re.longitude, re.latitude), point(:longitude, :latitude)) < :radius")
    List<ReportEntity> findByDistanceToPoint(double latitude, double longitude, int radius);
}
