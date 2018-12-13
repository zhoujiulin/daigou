package xiaolan.daigou.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable{

	/**
	 * 
	 */
    private static final long serialVersionUID = 7901048489335996904L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(name="name_wechat")
	private String nameWechat;
	
	@Column(name="name_livraison")
	private String nameLivraison;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="adresse")
	private String adresse;

    public Client() {

    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNameWechat(String nameWechat){
        this.nameWechat = nameWechat;
    }

    public String getNameWechat(){
        return this.nameWechat;
    }

    public void setNameLivraison(String nameLivraison){
        this.nameLivraison = nameLivraison;
    }

    public String getNameLivraison(){
        return this.nameLivraison;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public String getAdresse(){
        return this.adresse;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
