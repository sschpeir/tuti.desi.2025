package tuti.desi.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemReceta {
	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column
	    private Integer cantidad;
	    @Column
	    private Integer calorias;

	    @ManyToOne
	    @JoinColumn(name = "receta_id")
	    private Receta receta;

	    @ManyToOne
	    @JoinColumn(name = "ingrediente_id")
	    private Ingrediente ingrediente;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		public Integer getCalorias() {
			return calorias;
		}

		public void setCalorias(Integer calorias) {
			this.calorias = calorias;
		}

		public Receta getReceta() {
			return receta;
		}

		public void setReceta(Receta receta) {
			this.receta = receta;
		}

		public Ingrediente getIngrediente() {
			return ingrediente;
		}

		public void setIngrediente(Ingrediente ingrediente) {
			this.ingrediente = ingrediente;
		}
	    
	    
	}


