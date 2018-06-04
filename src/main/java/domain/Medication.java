package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medication  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String name;
	private Integer code;
	private Boolean outpatient;
	private String brand;	
	private String description;
	private String standard;
	private Integer dailyDosage;
	private Integer cycles;
	private Integer frequency;
	private Integer timeTreatment;
	private Integer timeInterval;
	
	private List<Unit> unit = new ArrayList<>();
	private List<Medicine> medicine = new ArrayList<>();
	private List<Access> access = new ArrayList<>();
	
	public Medication() {}

	public Medication(Integer id, String name, Integer code, Boolean outpatient, String brand, String description,
			String standard, Integer dailyDosage, Integer cycles, Integer frequency, Integer timeTreatment,
			Integer timeInterval, List<Unit> unit, List<Medicine> medicine, List<Access> access) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.outpatient = outpatient;
		this.brand = brand;
		this.description = description;
		this.standard = standard;
		this.dailyDosage = dailyDosage;
		this.cycles = cycles;
		this.frequency = frequency;
		this.timeTreatment = timeTreatment;
		this.timeInterval = timeInterval;
		this.unit = unit;
		this.medicine = medicine;
		this.access = access;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getOutpatient() {
		return outpatient;
	}

	public void setOutpatient(Boolean outpatient) {
		this.outpatient = outpatient;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getDailyDosage() {
		return dailyDosage;
	}

	public void setDailyDosage(Integer dailyDosage) {
		this.dailyDosage = dailyDosage;
	}

	public Integer getCycles() {
		return cycles;
	}

	public void setCycles(Integer cycles) {
		this.cycles = cycles;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Integer getTimeTreatment() {
		return timeTreatment;
	}

	public void setTimeTreatment(Integer timeTreatment) {
		this.timeTreatment = timeTreatment;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public List<Unit> getUnit() {
		return unit;
	}

	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

	public List<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(List<Medicine> medicine) {
		this.medicine = medicine;
	}

	public List<Access> getAccess() {
		return access;
	}

	public void setAccess(List<Access> access) {
		this.access = access;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
