package com.management.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private @NotEmpty
    @Size(
            min = 4,
            max = 20
    ) String nom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private  String Type;
    private String email;
    private long phone;

    public Patient() {

    }

    public Patient(String nom, String email) {
        this.nom=nom;
        this.email=email;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
         this.doctor=doctor;

    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;







    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public static PatientBuilder builder() {
        return new PatientBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }
    public String getType() {
        return this.Type;
    }


    public Date getDateNaissance() {
        return this.dateNaissance;
    }

    public boolean isMalade() {
        return this.malade;
    }



    public void setId(final Long id) {
        this.id = id;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }
    public void setType(final String type) {
        this.Type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, dateNaissance, malade, Type, email, phone);
    }

    public void setDateNaissance(final Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setMalade(final boolean malade) {
        this.malade = malade;
    }


    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Patient)) {
            return false;
        } else {
            Patient other = (Patient)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isMalade() != other.isMalade()) {
                return false;
            }  else {
                label52: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label52;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label52;
                    }

                    return false;
                }

                Object this$nom = this.getNom();
                Object other$nom = other.getNom();
                if (this$nom == null) {
                    if (other$nom != null) {
                        return false;
                    }
                } else if (!this$nom.equals(other$nom)) {
                    return false;
                }

                Object this$dateNaissance = this.getDateNaissance();
                Object other$dateNaissance = other.getDateNaissance();
                if (this$dateNaissance == null) {
                    if (other$dateNaissance != null) {
                        return false;
                    }
                } else if (!this$dateNaissance.equals(other$dateNaissance)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Patient;
    }


    public String toString() {
        Long var10000 = this.getId();
        return "Patient(id=" + var10000 + ", nom=" + this.getNom() + ", dateNaissance=" + String.valueOf(this.getDateNaissance()) + ", malade=" + this.isMalade() + ", type=" + this.getType() + ")";
    }

    public Patient(Doctor doctor) {
        this.doctor = doctor;

    }

    public Patient(final Long id, final String nom, final Date dateNaissance, final boolean malade, final String Type, Doctor doctor) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
        this.Type=Type;
        this.doctor = doctor;

    }



    public static class PatientBuilder {
        private Long id;
        private String nom;
        private Date dateNaissance;
        private boolean malade;
        private String Type;

        PatientBuilder() {
        }

        public PatientBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public PatientBuilder nom(final String nom) {
            this.nom = nom;
            return this;
        }

        public PatientBuilder dateNaissance(final Date dateNaissance) {
            this.dateNaissance = dateNaissance;
            return this;
        }

        public PatientBuilder malade(final boolean malade) {
            this.malade = malade;
            return this;
        }

        public PatientBuilder type( String Type ) {
            this.Type = Type;
            return this;
        }

        public Patient build() {
            return new Patient(this.id, this.nom, this.dateNaissance, this.malade, this.Type, this.build().doctor);
        }

        public String toString() {
            Long var10000 = this.id;
            return "Patient.PatientBuilder(id=" + var10000 + ", nom=" + this.nom + ", dateNaissance=" + String.valueOf(this.dateNaissance) + ", malade=" + this.malade + ", type=" + this.Type + ")";
        }
    }
}
