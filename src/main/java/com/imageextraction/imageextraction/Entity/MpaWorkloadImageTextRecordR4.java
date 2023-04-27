package com.imageextraction.imageextraction.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "mpa_workload_image_text_record_r4")
public class MpaWorkloadImageTextRecordR4 {
    @Id
    @Column(name = "",length = 50)
    private String workLoadUnitTextR4Id;
    @ManyToOne
    @JoinColumn(name = "workload_img_text_r4_id")
    private MpaWrkloadImageTextR4 workloadImageText;
   @Column(name = "record_text",length = 5000)
    private String recordText;
   @Column(name = "error_msg",length = 5000)
    private String errorMsg;
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
