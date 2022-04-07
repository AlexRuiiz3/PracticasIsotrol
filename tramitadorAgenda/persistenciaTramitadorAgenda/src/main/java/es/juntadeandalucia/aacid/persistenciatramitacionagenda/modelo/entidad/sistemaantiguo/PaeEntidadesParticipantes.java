package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PAE_ENTIDADES_PARTICIPANTES")
public class PaeEntidadesParticipantes {

    private long idEntParticipante;
    private PaeSolicitudes paeSolicitudes;
    private String entParticipante;
    private String otraFuncion;
    private String entidadSel;
    private String funcionSel;
    private int indice;
    private List<PaeConOtrasAportaciones> paeConOtrasAportaciones;
    private Double total;
    private Double porcentajeTotal;
    private PaeFunciones paeFunciones;
    private PaeTEntidadParticipante paeTEntidadParticipante;
    /**
     * Constructor vacío por defecto
     */
    public PaeEntidadesParticipantes() {
    }

    /**
     * Constructor con un parámetro
     * @param idEntParticipante long
     */
    public PaeEntidadesParticipantes(long idEntParticipante) {
        this.idEntParticipante = idEntParticipante;
    }

    /**
     * Constructor con parámetros
     * @param idEntParticipante long
     * @param paeTEntidadParticipante PaeTEntidadParticipante
     * @param paeFunciones PaeFunciones
     * @param paeSolicitudes PaeSolicitudes
     * @param entParticipante String
     * @param otraFuncion String
     */
    public PaeEntidadesParticipantes(long idEntParticipante, PaeSolicitudes paeSolicitudes, String entParticipante, String otraFuncion) {
        this.idEntParticipante = idEntParticipante;
        this.paeSolicitudes = paeSolicitudes;
        this.entParticipante = entParticipante;
        this.otraFuncion = otraFuncion;
    }

    /**
     * Getter de idEntParticipante
     * @return idEntParticipante long
     */
    @Id
    @Column(name = "ID_ENT_PARTICIPANTE", nullable = false, precision = 10, scale = 0)
    public long getIdEntParticipante() {
        return this.idEntParticipante;
    }

    /**
     * Setter de idEntParticipante
     * @param idEntParticipante long
     */
    public void setIdEntParticipante(long idEntParticipante) {
        this.idEntParticipante = idEntParticipante;
    }

 

    /**
     * Getter de paeSolicitudes
     * @return paeSolicitudes PaeSolucitudes
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOLICITUD")
    public PaeSolicitudes getPaeSolicitudes() {
        return this.paeSolicitudes;
    }

    /**
     * Setter de paeSolicitudes
     * @param paeSolicitudes PaeSolicitudes
     */
    public void setPaeSolicitudes(PaeSolicitudes paeSolicitudes) {
        this.paeSolicitudes = paeSolicitudes;
    }

    /**
     * Getter de entParticipante
     * @return entParticipante String
     */
    @Column(name = "ENT_PARTICIPANTE", length = 50)
    public String getEntParticipante() {
        return this.entParticipante;
    }

    /**
     * Setter de entParticipante
     * @param entParticipante String
     */
    public void setEntParticipante(String entParticipante) {
        this.entParticipante = entParticipante;
    }

    /**
     * Getter de otraFuncion
     * @return otraFuncion String
     */
    @Column(name = "OTRA_FUNCION", length = 50)
    public String getOtraFuncion() {
        return this.otraFuncion;
    }

    /**
     * Setter de otraFuncion
     * @param otraFuncion String
     */
    public void setOtraFuncion(String otraFuncion) {
        this.otraFuncion = otraFuncion;
    }

    /**
     * Getter de entidadSel
     * @return entidadSel String
     */
    @Transient
    public String getEntidadSel() {
        return entidadSel;
    }

    /**
     * Setter de entidadSel
     * @param entidadSel String
     */
    public void setEntidadSel(String entidadSel) {
        this.entidadSel = entidadSel;
    }

    /**
     * Getter de funcionSel
     * @return funcionSel String
     */
    @Transient
    public String getFuncionSel() {
        return funcionSel;
    }

    /**
     * Setter de funcionSel
     * @param funcionSel String
     */
    public void setFuncionSel(String funcionSel) {
        this.funcionSel = funcionSel;
    }

    /**
     * Getter de indice
     * @return indice int
     */
    @Transient
    public int getIndice() {
        return indice;
    }

    /**
     * Setter de indice
     * @param indice int
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Getter de paeConOtrasAportaciones
     * @return paeConOtrasAportaciones Set<PaeConOtrasAportaciones>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paeEntidadesParticipantes")
    public List<PaeConOtrasAportaciones> getPaeConOtrasAportaciones() {
        return paeConOtrasAportaciones;
    }

    /**
     * Setter de paeConOtrasAportaciones
     * @param paeConOtrasAportaciones Set<PaeConOtrasAportaciones>
     */
    public void setPaeConOtrasAportaciones(List<PaeConOtrasAportaciones> paeConOtrasAportaciones) {
        this.paeConOtrasAportaciones = paeConOtrasAportaciones;
    }

    @Transient
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	@Transient
	public Double getPorcentajeTotal() {
		return porcentajeTotal;
	}

	public void setPorcentajeTotal(Double porcentajeTotal) {
		this.porcentajeTotal = porcentajeTotal;
	}
	 /**
     * Getter de paeFunciones
     * @return paeFunciones PaeFunciones
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_FUNCION")
	public PaeFunciones getPaeFunciones() {
		return paeFunciones;
	}
    /**
     * Setter de paeFunciones
     * @param paeFunciones PaeFunciones
     */
	public void setPaeFunciones(PaeFunciones paeFunciones) {
		this.paeFunciones = paeFunciones;
	}

	 
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "FK_T_ENT_PARTIC")
  public PaeTEntidadParticipante getPaeTEntidadParticipante() {
    return this.paeTEntidadParticipante;
  }
  
  public void setPaeTEntidadParticipante(PaeTEntidadParticipante paeTEntidadParticipante) {
    this.paeTEntidadParticipante = paeTEntidadParticipante;
  }
  


}
