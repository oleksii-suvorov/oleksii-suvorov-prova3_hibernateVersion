package com.journaldev.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;

/**
 * @author oleksii
 * pojo della tabella newfont_dati.projects
 */

@Entity
@Table(name = "projects")
public class Project {
  public Project() {
  }
  @Id
  @Column(name = "pk_projects")
  private long pk_projects;

  public long getPk_projects() {
    return pk_projects;
  }

  public void setPk_projects(long pk_projects) {
    this.pk_projects = pk_projects;
  }

  /**
   * legamento tra Progetti e Nodi
   */
  @OneToMany(mappedBy = "project", targetEntity = Node.class)
  @Fetch(FetchMode.SUBSELECT)
  private List<Node> pk_pcab_nodes;

  public List<Node> getPk_pcab_nodes() {
    return pk_pcab_nodes;
  }
  public void setPk_pcab_nodes(List<Node> nodes) {

    this.pk_pcab_nodes = nodes;
  }

  @Override
  public String toString() {
    return "Project{" +
          "pk_projects=" + getPk_projects() + "nodes='" + pk_pcab_nodes +
          '}';
  }
}
