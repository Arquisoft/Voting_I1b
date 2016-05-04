package es.uniovi.asw.model;

import javax.persistence.*;


@Entity 
@Table(name="VOTOS")
public class Vote {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VOTO")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Voting voting;
	@Column(name="OPCION")
	private String option;
	
	public Vote(){}
	
	public Vote(Voting voting, String option) {
		super();
		this.voting = voting;
		this.option = option;
	}
	public Vote(long id,Voting voting, String option) {
		this(voting,option);
		this.id=id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Long getId() {
		return id;
	}

	public Voting getVoting() {
		return voting;
	}

	public void setVoting(Voting voting) {
		this.voting = voting;
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
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", voting=" + voting + ", option=" + option + "]";
	}

	public void setId(long id) {
		this.id=id;
	}

}