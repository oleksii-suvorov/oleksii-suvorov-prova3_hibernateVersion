package com.journaldev.model;

import javax.persistence.*;

/**
 * @author oleksii
 * pojo della tabella newfont_dati.pcab_nodes
 */

@Entity
@Table(name = "pcab_nodes")
public class Node {

	public Node() {
	}

	@Id
	@Column(name = "id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "pk_pcab_nodes")
	private long pk_pcab_nodes;

	private int id_entity_classification;


	/**
	 * legamento tra  Nodi e Progetti
	 */
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Project.class)
	@JoinColumn(name = "pk_pcab_nodes", insertable = false, updatable = false)
	private Project project;

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public long getPk_pcab_nodes() {
		return this.pk_pcab_nodes;
	}

	@Column(name = "id_entity_classification")
	public void setPk_pcab_nodes(long pk_pcab_nodes) {
		this.pk_pcab_nodes = pk_pcab_nodes;
	}

	public int getId_entity_classification() {
		return id_entity_classification;
	}

	public void setId_entity_classification(int id_entity_classification) {
		this.id_entity_classification = id_entity_classification;
	}

	@Override
	public String toString() {
		return "Node{" +
						"type='" + id_entity_classification + '\'' +
						"id='" + pk_pcab_nodes +
						'}';
	}
}
