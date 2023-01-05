package com.github.olegbal.xmluploader.service;

import com.github.olegbal.xmluploader.domain.entity.DeviceInfo;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Service;

@Service
public class DeviceInfoRSQLCriteriaQueryBuilder implements RSQLCriteriaQueryBuilder<DeviceInfo> {

  @Override
  public CriteriaQuery<DeviceInfo> build(String rsqlQuery, EntityManager entityManager) {
    RSQLVisitor<CriteriaQuery<DeviceInfo>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
    Node rootNode = new RSQLParser().parse(rsqlQuery);

    return rootNode.accept(visitor, entityManager);
  }
}
