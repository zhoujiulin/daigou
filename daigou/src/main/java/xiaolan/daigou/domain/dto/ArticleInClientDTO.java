package xiaolan.daigou.domain.dto;

public class ArticleInClientDTO {

	private Long idArticle;
	
	private String nameArticle;
	
	int countArticleFromStockageChine;

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

	public int getCountArticleFromStockageChine() {
		return countArticleFromStockageChine;
	}

	public void setCountArticleFromStockageChine(int countArticleFromStockageChine) {
		this.countArticleFromStockageChine = countArticleFromStockageChine;
	}
}
