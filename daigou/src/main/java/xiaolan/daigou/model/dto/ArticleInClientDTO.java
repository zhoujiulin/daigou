package xiaolan.daigou.model.dto;

public class ArticleInClientDTO {

	private Long idArticle;
	
	private String nameArticle;
	
	private int countArticleAchete;
	
	private int countArticleFromStockageChine;

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

	public int getCountArticleAchete() {
		return countArticleAchete;
	}

	public void setCountArticleAchete(int countArticleAchete) {
		this.countArticleAchete = countArticleAchete;
	}

	public int getCountArticleFromStockageChine() {
		return countArticleFromStockageChine;
	}

	public void setCountArticleFromStockageChine(int countArticleFromStockageChine) {
		this.countArticleFromStockageChine = countArticleFromStockageChine;
	}
}
