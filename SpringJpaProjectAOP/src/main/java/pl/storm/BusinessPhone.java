package pl.storm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BusinessPhone {
    @Id
    @GeneratedValue
    private long id;
    private int number;

    @OneToOne(mappedBy = "businessPhone")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "BusinessPhone{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
