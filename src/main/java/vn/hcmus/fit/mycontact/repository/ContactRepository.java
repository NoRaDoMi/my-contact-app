package vn.hcmus.fit.mycontact.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.hcmus.fit.mycontact.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
//    Tương ứng với SELECT * FROM contact WHERE name LIKE %term%.
    List<Contact> findByNameContaining(String term);
}
