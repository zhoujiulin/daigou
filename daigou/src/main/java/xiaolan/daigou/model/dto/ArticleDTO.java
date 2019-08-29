package xiaolan.daigou.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import xiaolan.daigou.common.converter.StatusArticleConverter;
import xiaolan.daigou.common.converter.StatusArticlePreparationConverter;
import xiaolan.daigou.common.converter.TypeArticleConverter;
import xiaolan.daigou.model.enums.EnumStatusArticle;
import xiaolan.daigou.model.enums.EnumStatusArticlePreparation;
import xiaolan.daigou.model.enums.EnumTypeArticle;

public class ArticleDTO {

	private Long idArticle;

    private String nameArticle;
    
    private double priceAchat;
    
    private double priceVente;
    
    private int count;
    
    private int countArticleAchete;

    private int countArticleAcheteDistribue;
    
    private int countArticleFromStockageFrance;
    
    private int countArticleFromStockageEnRoute;
    
    private int countArticleFromStockageChine;
    
    private int countArticleFromStockageChineDistribue;
    
	@Convert(converter = StatusArticlePreparationConverter.class)
	private EnumStatusArticlePreparation statusArticlePreparation;

	@Convert(converter = StatusArticleConverter.class)
	private EnumStatusArticle statusArticle;
	
	@Convert(converter = TypeArticleConverter.class)
	private EnumTypeArticle typeArticle;

	@JsonIgnoreProperties("articles")
	private ColisDTO colis;

	@JsonIgnoreProperties("articles")
	private CommandeDTO commande;
	
    private Date dateCreation;
    
    private Date dateEnvoie;
    
    private Date dateArrive;
	
    private Date dateDistribution;

    private Date dateTermine;
    
    private int countSelectedEnRouteToChine;
    
	private UtilisateurDTO utilisateur;

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getNameArticle() {
		return nameArticle;
	}

	public void setNameArticle(String nameArticle) {
		this.nameArticle = nameArticle;
	}

	public double getPriceAchat() {
		return priceAchat;
	}

	public void setPriceAchat(double priceAchat) {
		this.priceAchat = priceAchat;
	}

	public double getPriceVente() {
		return priceVente;
	}

	public void setPriceVente(double priceVente) {
		this.priceVente = priceVente;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountArticleAchete() {
		return countArticleAchete;
	}

	public void setCountArticleAchete(int countArticleAchete) {
		this.countArticleAchete = countArticleAchete;
	}

	public int getCountArticleFromStockageFrance() {
		return countArticleFromStockageFrance;
	}

	public void setCountArticleFromStockageFrance(int countArticleFromStockageFrance) {
		this.countArticleFromStockageFrance = countArticleFromStockageFrance;
	}

	public int getCountArticleFromStockageEnRoute() {
		return countArticleFromStockageEnRoute;
	}

	public void setCountArticleFromStockageEnRoute(int countArticleFromStockageEnRoute) {
		this.countArticleFromStockageEnRoute = countArticleFromStockageEnRoute;
	}

	public int getCountArticleFromStockageChine() {
		return countArticleFromStockageChine;
	}
	
	public int getCountArticleAcheteDistribue() {
		return countArticleAcheteDistribue;
	}

	public void setCountArticleAcheteDistribue(int countArticleAcheteDistribue) {
		this.countArticleAcheteDistribue = countArticleAcheteDistribue;
	}

	public int getCountArticleFromStockageChineDistribue() {
		return countArticleFromStockageChineDistribue;
	}

	public void setCountArticleFromStockageChineDistribue(int countArticleFromStockageChineDistribue) {
		this.countArticleFromStockageChineDistribue = countArticleFromStockageChineDistribue;
	}

	public void setCountArticleFromStockageChine(int countArticleFromStockageChine) {
		this.countArticleFromStockageChine = countArticleFromStockageChine;
	}

	public EnumStatusArticlePreparation getStatusArticlePreparation() {
		return statusArticlePreparation;
	}

	public void setStatusArticlePreparation(EnumStatusArticlePreparation statusArticlePreparation) {
		this.statusArticlePreparation = statusArticlePreparation;
	}
	
	public EnumStatusArticle getStatusArticle() {
		return statusArticle;
	}

	public void setStatusArticle(EnumStatusArticle statusArticle) {
		this.statusArticle = statusArticle;
	}

	public EnumTypeArticle getTypeArticle() {
		return typeArticle;
	}

	public void setTypeArticle(EnumTypeArticle typeArticle) {
		this.typeArticle = typeArticle;
	}

	public ColisDTO getColis() {
		return colis;
	}

	public void setColis(ColisDTO colis) {
		this.colis = colis;
	}

	public CommandeDTO getCommande() {
		return commande;
	}

	public void setCommande(CommandeDTO commande) {
		this.commande = commande;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public Date getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
	}

	public Date getDateDistribution() {
		return dateDistribution;
	}

	public void setDateDistribution(Date dateDistribution) {
		this.dateDistribution = dateDistribution;
	}

	public Date getDateTermine() {
		return dateTermine;
	}

	public void setDateTermine(Date dateTermine) {
		this.dateTermine = dateTermine;
	}

	public int getCountSelectedEnRouteToChine() {
		return countSelectedEnRouteToChine;
	}

	public void setCountSelectedEnRouteToChine(int countSelectedEnRouteToChine) {
		this.countSelectedEnRouteToChine = countSelectedEnRouteToChine;
	}

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
	}
}
