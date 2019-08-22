package xiaolan.daigou.domain.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;

public class ArticleStockageDTO {

    private Long idArticleStockage;
    
    private String nameArticleStockage;
    
    private double priceAchatStockage;
    
    private int countStockageFranceAvailable;
    
    private int countStockageFranceReserve;
    
    private int countStockageFranceColis;
	
    private int countStockageEnRoute;
    
    private int countStockageEnRouteAvailable;
    
    private int countStockageChine;
    
    private int countStockageChineAvailable;
    
	private UtilisateurDTO utilisateur;

	public Long getIdArticleStockage() {
		return idArticleStockage;
	}

	public void setIdArticleStockage(Long idArticleStockage) {
		this.idArticleStockage = idArticleStockage;
	}

	public String getNameArticleStockage() {
		return nameArticleStockage;
	}

	public void setNameArticleStockage(String nameArticleStockage) {
		this.nameArticleStockage = nameArticleStockage;
	}

	public double getPriceAchatStockage() {
		return priceAchatStockage;
	}

	public void setPriceAchatStockage(double priceAchatStockage) {
		this.priceAchatStockage = priceAchatStockage;
	}

	public int getCountStockageFranceAvailable() {
		return countStockageFranceAvailable;
	}

	public void setCountStockageFranceAvailable(int countStockageFranceAvailable) {
		this.countStockageFranceAvailable = countStockageFranceAvailable;
	}

	public int getCountStockageFranceReserve() {
		return countStockageFranceReserve;
	}

	public void setCountStockageFranceReserve(int countStockageFranceReserve) {
		this.countStockageFranceReserve = countStockageFranceReserve;
	}

	public int getCountStockageFranceColis() {
		return countStockageFranceColis;
	}

	public void setCountStockageFranceColis(int countStockageFranceColis) {
		this.countStockageFranceColis = countStockageFranceColis;
	}

	public int getCountStockageEnRoute() {
		return countStockageEnRoute;
	}

	public void setCountStockageEnRoute(int countStockageEnRoute) {
		this.countStockageEnRoute = countStockageEnRoute;
	}

	public int getCountStockageEnRouteAvailable() {
		return countStockageEnRouteAvailable;
	}

	public void setCountStockageEnRouteAvailable(int countStockageEnRouteAvailable) {
		this.countStockageEnRouteAvailable = countStockageEnRouteAvailable;
	}

	public int getCountStockageChine() {
		return countStockageChine;
	}

	public void setCountStockageChine(int countStockageChine) {
		this.countStockageChine = countStockageChine;
	}

	public int getCountStockageChineAvailable() {
		return countStockageChineAvailable;
	}

	public void setCountStockageChineAvailable(int countStockageChineAvailable) {
		this.countStockageChineAvailable = countStockageChineAvailable;
	}

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}
}
