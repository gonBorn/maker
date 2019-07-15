package com.platform.maker.Form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import java.util.Date;
@Data
public class SubmitApplicationForm {
    private String startTimeLeftBorder;
    private String startTimeRightBorder;
    private String otherRequest;
}
