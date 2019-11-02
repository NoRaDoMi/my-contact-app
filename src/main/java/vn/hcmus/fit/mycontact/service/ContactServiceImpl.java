package vn.hcmus.fit.mycontact.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.hcmus.fit.mycontact.entity.Contact;
import vn.hcmus.fit.mycontact.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> search(String term) {
        return contactRepository.findByNameContaining(term);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
