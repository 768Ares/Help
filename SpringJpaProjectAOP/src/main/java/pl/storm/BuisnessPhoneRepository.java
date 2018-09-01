package pl.storm;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Repository
public class BuisnessPhoneRepository {

    public BusinessPhone add(BusinessPhone businessPhone, EntityManager entityManager) {
        entityManager.persist(businessPhone);
        return businessPhone;
    }

}
