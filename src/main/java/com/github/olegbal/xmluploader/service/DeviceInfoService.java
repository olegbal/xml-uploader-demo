package com.github.olegbal.xmluploader.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.github.olegbal.xmluploader.domain.dto.DeviceInfoDto;

public interface DeviceInfoService {

  DeviceInfoDto updateDeviceInfo(DeviceInfoDto createDeviceInfoDto);

  DeviceInfoDto createDeviceInfo(MultipartFile xmlFile);

  Page<DeviceInfoDto> getAllDeviceInfo(String rsqlQuery, Pageable pageable);

}
