package vn.hcmus.fit.mycontact.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    validator constraint
    @NotEmpty // Không được bỏ trống tên liên hệ
    @Column(nullable = false)
    private String name;

    @Email // email phải đúng định dạng
    private String email;

    private String phone;
}
