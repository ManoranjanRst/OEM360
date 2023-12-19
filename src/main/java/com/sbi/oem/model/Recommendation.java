package com.sbi.oem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ref_id")
	private String referenceId;

	@Column(name = "descriptions")
	private String descriptions;

	@OneToOne
	@JoinColumn(name = "type_id")
	private RecommendationType recommendationType;

	@Column(name = "priority_id")
	private Long priorityId;

	@Column(name = "recommend_date")
	private Date recommendDate;

	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToOne
	@JoinColumn(name = "component_id")
	private Component component;

	@Column(name = "expected_impact")
	private String expectedImpact;

	@Column(name = "document_url")
	private String documentUrl;

	@Column(name = "file_url")
	private String fileUrl;

	@Column(name = "created_at")
	private Date createdAt;

	@OneToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public RecommendationType getRecommendationType() {
		return recommendationType;
	}

	public void setRecommendationType(RecommendationType recommendationType) {
		this.recommendationType = recommendationType;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Date getRecommendDate() {
		return recommendDate;
	}

	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String getExpectedImpact() {
		return expectedImpact;
	}

	public void setExpectedImpact(String expectedImpact) {
		this.expectedImpact = expectedImpact;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Recommendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recommendation(Long id, String referenceId, String descriptions, RecommendationType recommendationType,
			Long priorityId, Date recommendDate, Department department, Component component, String expectedImpact,
			String documentUrl, Date createdAt, User createdBy) {
		super();
		this.id = id;
		this.referenceId = referenceId;
		this.descriptions = descriptions;
		this.recommendationType = recommendationType;
		this.priorityId = priorityId;
		this.recommendDate = recommendDate;
		this.department = department;
		this.component = component;
		this.expectedImpact = expectedImpact;
		this.documentUrl = documentUrl;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

}