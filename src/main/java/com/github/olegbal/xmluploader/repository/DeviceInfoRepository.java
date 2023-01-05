package com.github.olegbal.xmluploader.repository;


import com.github.olegbal.xmluploader.domain.entity.DeviceInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoRepository extends PagingAndSortingRepository<DeviceInfo, Long> {

}
