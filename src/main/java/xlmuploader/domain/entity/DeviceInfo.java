package xlmuploader.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "screen_width")
  private Integer screenWidth;

  @Column(name = "screen_height")
  private Integer screenHeight;

  @Column(name = "screen_dpi")
  private Integer screenDpi;

  @Column(name = "newspaper_name")
  private String newspaperName;

  @Column(name = "upload_date")
  private LocalDateTime uploadDate;

  @Column
  private String filename;

}
