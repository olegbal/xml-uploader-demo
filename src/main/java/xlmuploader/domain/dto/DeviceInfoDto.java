package xlmuploader.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoDto {

  private Long id;

  private Integer screenWidth;

  private Integer screenHeight;

  private Integer screenDpi;

  private String newspaperName;

  private LocalDateTime uploadDate;

  private String filename;
}
