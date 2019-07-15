package com.platform.maker.dataobject;

import com.platform.maker.MultiKeyClass.ApplicationMultiKeyClass;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@Data
public class Application  {
    @Id
    private String applicationId;

    private Integer courseId;

    private String schoolId;

    private Date startTimeLeftBorder;

    private Date startTimeRightBorder;

    private Integer appStatus;

    private Date createTime;

    private String otherRequest;

    public Application() {
    }
}
