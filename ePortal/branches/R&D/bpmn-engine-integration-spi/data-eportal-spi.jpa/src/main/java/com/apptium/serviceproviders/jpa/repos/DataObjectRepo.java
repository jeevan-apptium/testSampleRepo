package com.apptium.serviceproviders.jpa.repos;

import org.springframework.data.repository.CrudRepository;

import com.apptium.serviceproviders.jpa.beans.DataObjectEntity;

public interface DataObjectRepo extends CrudRepository<DataObjectEntity, String>{

}
