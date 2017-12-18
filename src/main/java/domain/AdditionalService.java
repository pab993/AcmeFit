
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class AdditionalService extends Comentable {

	//Attributes
	// ========================================================

	private String	name;
	private Date	openingTime;
	private Date	closingTime;
	private int		duration;
	private int		maximumCapacity;
	private Double	cost;


	@SafeHtml
	@NotBlank
	@Column(unique = true)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	public Date getOpeningTime() {
		return this.openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	public Date getClosingTime() {
		return this.closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	@NotNull
	@Min(5)
	public int getDuration() {
		return this.duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@NotNull
	@Min(1)
	public int getMaximumCapacity() {
		return this.maximumCapacity;
	}
	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	@NotNull
	@Min(0)
	@Digits(fraction = 2, integer = 12)
	public Double getCost() {
		return this.cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}


	//Relationships
	// ===========================================================

	private Collection<Booking>	bookings;


	@OneToMany(mappedBy = "service")
	@Cascade(CascadeType.DELETE)
	public Collection<Booking> getBookings() {
		return this.bookings;
	}
	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}

}
