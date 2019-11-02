package vn.hcmus.fit.mycontact.service;

import vn.hcmus.fit.mycontact.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Iterable<Contact> findAll();

    List<Contact> search(String term);

    Optional<Contact> findById(Long id);

    void save(Contact contact);

    void delete(Long id);
}
