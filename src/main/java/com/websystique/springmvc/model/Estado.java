package com.websystique.springmvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="APP_BODEGA")
public class Bodega implements Serializable{

    
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="bodeg_ID", unique=true, nullable=false)
	private String bodegId;
	
	@NotEmpty
	@Column(name="UBICACION", unique=true, nullable=false)
	private String ubicacion;
		
	@NotEmpty
	@Column(name="DESCRIPCION", nullable=false)
	private String descripcion;
	@NotEmpty
	@Column(name="FECHAINGRESO", nullable=false)
	private String fechaIngreso;

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
        

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBodegId() {
        return bodegId;
    }

    public void setBodegId(String bodegId) {
        this.bodegId = bodegId;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	


	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bodegId == null) ? 0 : bodegId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		Bodega other = (Bodega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (bodegId == null) {
			if (other.bodegId != null)
				return false;
		} else if (!bodegId.equals(other.bodegId))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Bodega [id=" + id + ", bodegId=" + bodegId + ", ubicacion=" + ubicacion
				+ ", descripcion=" + descripcion + "]";
	}



	
}
