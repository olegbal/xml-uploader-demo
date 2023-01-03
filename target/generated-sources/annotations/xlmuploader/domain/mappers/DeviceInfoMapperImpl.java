package xlmuploader.domain.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import xlmuploader.domain.dto.DeviceInfoDto;
import xlmuploader.domain.entity.DeviceInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-04T00:41:50+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class DeviceInfoMapperImpl implements DeviceInfoMapper {

    @Override
    public DeviceInfoDto toDeviceInfoDto(DeviceInfo deviceInfo) {
        if ( deviceInfo == null ) {
            return null;
        }

        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();

        deviceInfoDto.setId( deviceInfo.getId() );
        deviceInfoDto.setScreenWidth( deviceInfo.getScreenWidth() );
        deviceInfoDto.setScreenHeight( deviceInfo.getScreenHeight() );
        deviceInfoDto.setScreenDpi( deviceInfo.getScreenDpi() );
        deviceInfoDto.setNewspaperName( deviceInfo.getNewspaperName() );
        deviceInfoDto.setUploadDate( deviceInfo.getUploadDate() );
        deviceInfoDto.setFilename( deviceInfo.getFilename() );

        return deviceInfoDto;
    }

    @Override
    public DeviceInfo toDeviceInfo(DeviceInfoDto identifiedDeviceInfoDto) {
        if ( identifiedDeviceInfoDto == null ) {
            return null;
        }

        DeviceInfo deviceInfo = new DeviceInfo();

        deviceInfo.setId( identifiedDeviceInfoDto.getId() );
        deviceInfo.setScreenWidth( identifiedDeviceInfoDto.getScreenWidth() );
        deviceInfo.setScreenHeight( identifiedDeviceInfoDto.getScreenHeight() );
        deviceInfo.setScreenDpi( identifiedDeviceInfoDto.getScreenDpi() );
        deviceInfo.setNewspaperName( identifiedDeviceInfoDto.getNewspaperName() );
        deviceInfo.setUploadDate( identifiedDeviceInfoDto.getUploadDate() );
        deviceInfo.setFilename( identifiedDeviceInfoDto.getFilename() );

        return deviceInfo;
    }
}
