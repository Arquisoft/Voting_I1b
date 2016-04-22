package es.uniovi.asw.model;


public class VotingMinimalInfoAdapter {
	
	private Long id;
	private String description;
	
	
	public VotingMinimalInfoAdapter(){}

	public VotingMinimalInfoAdapter(Voting voting) {
		this.description=voting.getDescription();
		this.id=voting.getId();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		VotingMinimalInfoAdapter other = (VotingMinimalInfoAdapter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
