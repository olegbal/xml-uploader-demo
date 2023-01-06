package com.github.olegbal.xmluploader.service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public interface RSQLCriteriaQueryBuilder<T> {

  CriteriaQuery<T> build(String rsqlQuery, EntityManager entityManager);

}
