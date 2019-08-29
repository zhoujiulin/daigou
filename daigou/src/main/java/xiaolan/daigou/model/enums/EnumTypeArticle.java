package xiaolan.daigou.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import xiaolan.daigou.common.serialize.TypeArticleDeserializer;

@JsonDeserialize(using = TypeArticleDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumTypeArticle {
    ARTICLE_CLIENT(1, "enum.article.type.articleClient"),
    ARTICLE_STOCKAGE(2, "enum.article.type.articleStockage");


    private int index;
    private String libelle;
    
    EnumTypeArticle(int index, String libelle){
        this.index = index;
        this.libelle = libelle;
    }

    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
    public static EnumTypeArticle getEnumByIndex(int index) {
        for(EnumTypeArticle status : values()) {
            if(status.getIndex() == index) {
                return status;
            }
        }
        return null;
    }
}
