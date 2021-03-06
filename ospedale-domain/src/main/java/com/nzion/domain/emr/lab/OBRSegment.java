package com.nzion.domain.emr.lab;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.nzion.domain.Provider;
import org.hibernate.annotations.Cascade;

import com.nzion.domain.Patient;
import com.nzion.domain.emr.soap.PatientLabOrder;
import com.nzion.domain.emr.soap.PatientSoapNote;
import com.nzion.domain.emr.soap.PatientVitalSignSet;
import com.nzion.util.UtilValidator;

@Entity
@XmlRootElement(name = "OBR")
public class OBRSegment {

    private String requisitionNumber;
    private Long id;
    private String testId;
    private String label;
    private SpecimenModel specimen;
    private String technicianComment;
    private Set<OBXSegment> observations;
    private PatientSoapNote soapNote;
    private Provider provider;
    private Patient patient;
    private Date observationDateTime;
    private String resultStatus;
    private boolean reviewedByProvider;
    private PatientLabOrder patientLabOrder;
    private String providerComment;
    private LabTestPanel labTestPanel;


    @OneToOne
    public PatientSoapNote getSoapNote() {
        return soapNote;
    }

    public void setSoapNote(PatientSoapNote soapNote) {
        this.soapNote = soapNote;
    }

    public boolean isReviewedByProvider() {
        return reviewedByProvider;
    }

    @Column(length = 1024)
    public String getTechnicianComment() {
        return technicianComment;
    }

    public void setTechnicianComment(String technicianComment) {
        this.technicianComment = technicianComment;
    }

    @OneToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @OneToOne
    public SpecimenModel getSpecimen() {
        return specimen;
    }

    public void setSpecimen(SpecimenModel specimen) {
        this.specimen = specimen;
    }

    @OneToMany(targetEntity = OBXSegment.class, mappedBy = "obrSegment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<OBXSegment> getObservations() {
        if (observations == null) observations = new HashSet<OBXSegment>();
        return observations;
    }

    public void setObservations(Set<OBXSegment> observations) {
        this.observations = observations;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Transient
    public void addOBX(OBXSegment obxSegment) {
        obxSegment.setPatient(this.patient);
        obxSegment.setObrSegment(this);
        if(UtilValidator.isEmpty(obxSegment.getObservationDateTime()))
        	obxSegment.setObservationDateTime(this.getObservationDateTime());
        getObservations().add(obxSegment);
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getObservationDateTime() {
        return observationDateTime;
    }

    public void setObservationDateTime(Date observationDateTime) {
        this.observationDateTime = observationDateTime;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    @OneToOne
    @JoinColumn(name = "PATIENT_LAB_ORDER")
    public PatientLabOrder getPatientLabOrder() {
        return patientLabOrder;
    }

    public void setPatientLabOrder(PatientLabOrder patientLabOrder) {
        this.patientLabOrder = patientLabOrder;
    }

    public void setReviewedByProvider(boolean reviewedByProvider) {
        this.reviewedByProvider = reviewedByProvider;
    }

    @Column(length = 1024)
    public String getProviderComment() {
        return providerComment;
    }

    public void setProviderComment(String providerComment) {
        this.providerComment = providerComment;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public LabTestPanel getLabTestPanel() {
        return labTestPanel;
    }

    public void setLabTestPanel(LabTestPanel labTestPanel) {
        this.labTestPanel = labTestPanel;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
