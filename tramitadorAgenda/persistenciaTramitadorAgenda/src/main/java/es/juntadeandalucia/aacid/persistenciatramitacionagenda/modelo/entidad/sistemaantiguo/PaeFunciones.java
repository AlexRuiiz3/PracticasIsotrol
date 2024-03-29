package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

// Generated 18-may-2010 17:40:19 by Hibernate Tools 3.2.4.CR1


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * PaeFunciones generated by hbm2java
 * @author Guadaltel S.A.
 */
@Entity
@Table(name = "PAE_FUNCIONES")
public class PaeFunciones implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6011299589388835421L;
	private long idFuncion;
    private String funcion;
    private String funcionSel;
    private Set<PaeEntidadesParticipantes> paeEntidadesParticipanteses = new HashSet<PaeEntidadesParticipantes>(0);

    /**
     * Constructor vacío por defecto
     */
    public PaeFunciones() {
    }

    /**
     * Constructor con un parámetro
     * @param idFuncion long
     */
    public PaeFunciones(long idFuncion) {
        this.idFuncion = idFuncion;
    }

    /**
     * Constructor con parámetros
     * @param idFuncion long 
     * @param funcion String
     * @param paeEntidadesParticipanteses Set<PaeEntidadesParticipantes>
     */
    public PaeFunciones(long idFuncion, String funcion, Set<PaeEntidadesParticipantes> paeEntidadesParticipanteses) {
        this.idFuncion = idFuncion;
        this.funcion = funcion;
        this.paeEntidadesParticipanteses = paeEntidadesParticipanteses;
    }

    /**
     * Getter de idFuncion
     * @return idFuncion long
     */
    @Id
    @Column(name = "ID_FUNCION", nullable = false, precision = 10, scale = 0)
    public long getIdFuncion() {
        return this.idFuncion;
    }

    /**
     * Setter de idFuncion
     * @param idFuncion long
     */
    public void setIdFuncion(long idFuncion) {
        this.idFuncion = idFuncion;
    }

    /**
     * Getter de funcion
     * @return funcion String
     */
    @Column(name = "FUNCION", length =50)
    public String getFuncion() {
        return this.funcion;
    }

    /**
     * Setter de funcion
     * @param funcion String
     */
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    /**
     * Getter de paeEntidadesParticipanteses;
     * @return paeEntidadesParticipanteses Set<PaeEntidadesParticipantes>
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paeFunciones")
    public Set<PaeEntidadesParticipantes> getPaeEntidadesParticipanteses() {
        return this.paeEntidadesParticipanteses;
    }

    /**
     * Setter de paeEntidadesPArticipanteses
     * @param paeEntidadesParticipanteses Set<PaeEntidadesParticipantes>
     */
    public void setPaeEntidadesParticipanteses(Set<PaeEntidadesParticipantes> paeEntidadesParticipanteses) {
        this.paeEntidadesParticipanteses = paeEntidadesParticipanteses;
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
     * Setter de funconSel
     * @param funcionSel String
     */
    public void setFuncionSel(String funcionSel) {
        this.funcionSel = funcionSel;
    }


}
