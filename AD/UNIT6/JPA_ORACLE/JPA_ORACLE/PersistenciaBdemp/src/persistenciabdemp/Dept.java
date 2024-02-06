/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciabdemp;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "DEPT", catalog = "", schema = "BLANCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dept.findAll", query = "SELECT d FROM Dept d"),
    @NamedQuery(name = "Dept.findByDeptno", query = "SELECT d FROM Dept d WHERE d.deptno = :deptno"),
    @NamedQuery(name = "Dept.findByDname", query = "SELECT d FROM Dept d WHERE d.dname = :dname"),
    @NamedQuery(name = "Dept.findByLoc", query = "SELECT d FROM Dept d WHERE d.loc = :loc")})
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPTNO", nullable = false)
    private int deptno;
    @Column(name = "DNAME", length = 14)
    private String dname;
    @Column(name = "LOC", length = 13)
    private String loc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deptno")
    private Collection<Emp> empCollection;

    public Dept() {
    }

    public Dept(int deptno) {
        this.deptno = deptno;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @XmlTransient
    public Collection<Emp> getEmpCollection() {
        return empCollection;
    }

    public void setEmpCollection(Collection<Emp> empCollection) {
        this.empCollection = empCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
       // hash += (deptno != null ? deptno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dept)) {
            return false;
        }
        Dept other = (Dept) object;
        boolean Dept = false;
        
        
        return Dept; 
    }

    @Override
    public String toString() {
        return "persistenciabdemp.Dept[ deptno=" + deptno + " ]";
    }
    
}
