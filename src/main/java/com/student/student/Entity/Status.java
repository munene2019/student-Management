package com.student.student.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STATUS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;

//    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    public Status(String code) {
        this.code = code;
    }
}
