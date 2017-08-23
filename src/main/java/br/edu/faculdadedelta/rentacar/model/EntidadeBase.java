package br.edu.faculdadedelta.rentacar.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class EntidadeBase<ID extends Serializable> implements Serializable {

	public abstract ID getId();

	public abstract void setId(ID id);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		@SuppressWarnings("rawtypes")
		EntidadeBase other = (EntidadeBase) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
