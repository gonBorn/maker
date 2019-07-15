package com.platform.maker.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Institution {
    @Id
    private String institutionId;

    private String openId;

    private String institutionName;

    private String institutionAddress;

    private String institutionPhone;

//    @OneToMany(mappedBy = "institution",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Transient
    private List<Course> courseList;
    public Institution() {
    }

    public Institution(String institutionId, String institutionName, String institutionAddress, String institutionPhone) {
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.institutionAddress = institutionAddress;
        this.institutionPhone = institutionPhone;
    }
}

