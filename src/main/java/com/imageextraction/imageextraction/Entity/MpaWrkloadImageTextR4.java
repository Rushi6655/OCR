package com.imageextraction.imageextraction.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mpa_workload_image_text_r4")
public class MpaWrkloadImageTextR4 {
    @Id
    @Column(name = "workload_img_text_r4_id",length = 50)
    private String workloadId;
    @Column(name = "img_text",length = 5000)
    private String imageText;
    @Lob
    @Column(name = "workload_img")
    private byte[] workingImage;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "updated_date")
    private String updatedDate;
    @Column(name = "deleted_by")
    private Integer deletedBy;
    @Column(name = "deleted_date")
    private String deletedDate;
}
