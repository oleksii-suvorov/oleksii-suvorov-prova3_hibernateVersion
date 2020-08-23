package com.journaldev.service;

import com.journaldev.config.SessionConfig;
import com.journaldev.model.Node;
import com.journaldev.model.Project;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypesCalculator {
  @Autowired
  private SessionConfig sessionConfig;

  @Value("${requestProps.type1Requested}")
  private int type1Requested;
  @Value("${requestProps.type2Requested}")
  private int type2Requested;

  @Value("${requestProps.projectLimit}")
  private int limit;

  @Value("${requestProps.projectOffset}")
  private int offSet;

  private int totalQuantity;
  private int type1Quantity;
  private int type2Quantity;
  private int totalSwapped;
  private int type1Swapped;
  private int type2Swapped;

  private List<Node> nodesFound = new ArrayList<>();
  private List<Project> projects = new ArrayList<>();
  private Session session;

  public void listNodes() {
    if (this.session == null) {
      session = sessionConfig.getSession();
    }
    Query query = session.createQuery("from Project");
    query.setFirstResult(offSet);
    query.setMaxResults(limit);
    List<Project> projects = query.list();
    calcolateTypes(projects);
    getInfo();
    setProjects(projects);
  }

  public void swapTypes() {
    if (projects.isEmpty()) {
      System.out.println("Projects list is empty");
    } else {
      for (Project pr : projects) {
        for (Node n : pr.getPk_pcab_nodes()) {
          if (n.getId_entity_classification() == type1Requested) {
            n.setId_entity_classification(type2Requested);
            type1Swapped++;
          } else {
            n.setId_entity_classification(type1Requested);
            type2Swapped++;
          }
          totalSwapped++;
          session.update(n);
        }
      }
      Transaction tx = session.beginTransaction();
      tx.commit();

      calcolateTypes(projects);
      getInfo();
    }
  }

  public void calcolateTypes(List<Project> projects) {
    clearInfo();
    if (projects.isEmpty()) {
      throw new RuntimeException("projects not found");
    }
    for (Project pr : projects) {
      nodesFound.addAll(pr.getPk_pcab_nodes());
    }

    if (nodesFound.isEmpty()) {
      throw new RuntimeException("nodes not found");
    } else {
      for (Node n : nodesFound) {
        if (n.getId_entity_classification() == type1Requested) {
          type1Quantity++;
        } else if (n.getId_entity_classification() == type2Requested) {
          type2Quantity++;
        }
      }
      totalQuantity = type1Quantity + type2Quantity;
    }
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public void getInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("Total nodes found ").append(totalQuantity).append(".\n")
          .append("Type ").append(type1Requested).append(": ").append(type1Quantity).append("\n")
          .append("Type ").append(type2Requested).append(": ").append(type2Quantity).append("\n");

    if(totalSwapped != 0) {
      sb = new StringBuilder();
      sb.append("Total swapped: ").append(totalSwapped).append(".\n")
            .append("Swapped into ").append(type1Requested).append(" type: ").append(type2Swapped).append("\n")
            .append("Swapped into ").append(type2Requested).append(" type: ").append(type1Swapped).append("\n");
    }
    System.out.println(sb.toString());
  }

  private void clearInfo() {
    this.totalQuantity = 0;
    this.type1Quantity = 0;
    this.type2Quantity = 0;
    nodesFound.clear();
  }

  public void closeSess() {
    if(this.session != null)
      this.session.close();
  }

}
