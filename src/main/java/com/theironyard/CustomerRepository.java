package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by vajrayogini on 3/9/16.
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
