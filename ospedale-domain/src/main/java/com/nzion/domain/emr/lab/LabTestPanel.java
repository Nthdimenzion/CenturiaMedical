package com.nzion.domain.emr.lab;

import com.nzion.domain.annot.AccountNumberField;
import com.nzion.domain.base.IdGeneratingBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@AccountNumberField("panelCode")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "panelCode"}))
@Access(AccessType.FIELD)
public class LabTestPanel extends IdGeneratingBaseEntity implements Serializable{
	
	@Column(length = 20)
	private String panelCode;
	
	private String panelDescription;
	
	private String panelPneumonic;
	
	private String department;
	

	@ManyToMany(targetEntity = LabTest.class,fetch = FetchType.EAGER)
	@JoinTable(name = "lab_test_panel_lab_test", 
	joinColumns = {@JoinColumn(name = "PANEL_CODE")},
	inverseJoinColumns = { @JoinColumn(name = "TEST_CODE") })
	private Set<LabTest> tests;

	public String getPanelCode() {
		return panelCode;
	}

	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}

	public String getPanelDescription() {
		return panelDescription;
	}

	public void setPanelDescription(String panelDescription) {
		this.panelDescription = panelDescription;
	}

	public String getPanelPneumonic() {
		return panelPneumonic;
	}

	public void setPanelPneumonic(String panelPneumonic) {
		this.panelPneumonic = panelPneumonic;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Set<LabTest> getTests() {
		return tests;
	}

	public void setTests(Set<LabTest> tests) {
		this.tests = tests;
	}
	
	

}
