/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciabdemp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "EMP", catalog = "", schema = "BLANCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emp.findAll", query = "SELECT e FROM Emp e"),
    @NamedQuery(name = "Emp.findByEmpno", query = "SELECT e FROM Emp e WHERE e.empno = :empno"),
    @NamedQuery(name = "Emp.findByEname", query = "SELECT e FROM Emp e WHERE e.ename = :ename"),
    @NamedQuery(name = "Emp.findByJob", query = "SELECT e FROM Emp e WHERE e.job = :job"),
    @NamedQuery(name = "Emp.findByHiredate", query = "SELECT e FROM Emp e WHERE e.hiredate = :hiredate"),
    @NamedQuery(name = "Emp.findBySal", query = "SELECT e FROM Emp e WHERE e.sal = :sal"),
    @NamedQuery(name = "Emp.findByComm", query = "SELECT e FROM Emp e WHERE e.comm = :comm")})
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMPNO", nullable = false)
    private Short empno;
    @Column(name = "ENAME", length = 10)
    private String ename;
    @Column(name = "JOB", length = 9)
    private String job;
    @Column(name = "HIREDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiredate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL", precision = 7, scale = 2)
    private BigDecimal sal;
    @Column(name = "COMM", precision = 7, scale = 2)
    private BigDecimal comm;
    @JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO", nullable = false)
    @ManyToOne(optional = false)
    private Dept deptno;
    @OneToMany(mappedBy = "mgr")
    private Collection<Emp> empCollection;
    @JoinColumn(name = "MGR", referencedColumnName = "EMPNO")
    @ManyToOne
    private Emp mgr;

    public Emp() {
    }

    public Emp(Short empno) {
        this.empno = empno;
    }

    public Short getEmpno() {
        return empno;
    }

    public void setEmpno(Short empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public Dept getDeptno() {
        return deptno;
    }

    public void setDeptno(Dept deptno) {
        this.deptno = deptno;
    }

    @XmlTransient
    public Collection<Emp> getEmpCollection() {
        return empCollection;
    }

    public void setEmpCollection(Collection<Emp> empCollection) {
        this.empCollection = empCollection;
    }

    public Emp getMgr() {
        return mgr;
    }

    public void setMgr(Emp mgr) {
        this.mgr = mgr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empno != null ? empno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emp)) {
            return false;
        }
        Emp other = (Emp) object;
        if ((this.empno == null && other.empno != null) || (this.empno != null && !this.empno.equals(other.empno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistenciabdemp.Emp[ empno=" + empno + " ]";
    }
    
}
