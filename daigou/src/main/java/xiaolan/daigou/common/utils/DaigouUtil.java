package xiaolan.daigou.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;

public class DaigouUtil {

	public static EnumStatusCommande isCommandeArriveePretADistribuer(Commande commande) {
		String nameWechat = commande.getClient().getNameWechat();
		String nameLivraison = commande.getClient().getNameLivraison();
		String telephone = commande.getClient().getTelephone();
		String adresse = commande.getClient().getAdresse();
		if(nameWechat == null || "".equals(nameWechat) || nameLivraison == null || "".equals(nameLivraison) || telephone == null || "".equals(telephone) || adresse == null || "".equals(adresse)) {
			return EnumStatusCommande.COMMANDE_MANQUE_INFO_CLIENT;
		}else {
			return EnumStatusCommande.COMMANDE_PRET_A_DISTRIBUER;
		}
	}
	
	public static Object cloneObject(Object objSource) {
		Object objDes = null;
		Class<?> clazz = objSource.getClass();
		Constructor<?> construtctor;
		try {
			construtctor = clazz.getConstructor();
			objDes = construtctor.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				if(!Modifier.isStatic(field.getModifiers())) {
					String fieldName = field.getName();
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					String getMethodName = "get" + firstLetter + fieldName.substring(1);
					Method getMethod = clazz.getMethod(getMethodName);
					Object value = getMethod.invoke(objSource);
			
					String setMethodName = "set" + firstLetter + fieldName.substring(1);
					Method setMethod = clazz.getMethod(setMethodName, new Class[] { field.getType() });
					setMethod.invoke(objDes, new Object[] { value });
				}
			}
		
		} catch (NoSuchMethodException e) {

		} catch (SecurityException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		} catch (IllegalArgumentException e) {

		} catch (InvocationTargetException e) {

		}
		
		return objDes;
	}
	
	public static Commande computeStatusCommande(Commande commande) {
		if(commande.getStatus() == EnumStatusCommande.NEW_COMMANDE 
				|| commande.getStatus() == EnumStatusCommande.COMMANDE_PARTIE_PRET
				|| commande.getStatus() == EnumStatusCommande.COMMANDE_PARTIE_PRET_A_ENVOYER
				|| commande.getStatus() == EnumStatusCommande.COMMANDE_PRET_A_ENVOYER) {
			EnumStatusCommande statusCommande = EnumStatusCommande.COMMANDE_PRET_A_ENVOYER;
			
			boolean isArticleNonPrepare = true;
			boolean isArticlePretAEnvoyer = true;
			for(Article article : commande.getArticles()) {
				if(article.getStatusArticle() == EnumStatusArticle.PREPARE_PARTIE) {
					statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET;
					isArticlePretAEnvoyer = false;
				}
				if(article.getStatusArticle() != EnumStatusArticle.NON_PREPARE) {
					isArticleNonPrepare = false;
				}else {
					isArticlePretAEnvoyer = false;
				}
			}
			
			if(isArticleNonPrepare) {
				statusCommande = EnumStatusCommande.NEW_COMMANDE;
			}else {
				statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET;
				boolean isComamndePartiePretAEnvoyer = false;
				for(Article article : commande.getArticles()) {
					if(article.getStatusArticle() == EnumStatusArticle.PREPARE_TOUT) {
						isComamndePartiePretAEnvoyer = true;
					}
				}
				if(isComamndePartiePretAEnvoyer) {
					statusCommande = EnumStatusCommande.COMMANDE_PARTIE_PRET_A_ENVOYER;
				}
			}
			
			if(isArticlePretAEnvoyer) {
				statusCommande = EnumStatusCommande.COMMANDE_PRET_A_ENVOYER;
			}
			
			commande.setStatus(statusCommande);
		}
		if(commande.getStatus() == EnumStatusCommande.COMMANDE_MANQUE_INFO_CLIENT || commande.getStatus() == EnumStatusCommande.COMMANDE_PRET_A_DISTRIBUER) {
			commande.setStatus(DaigouUtil.isCommandeArriveePretADistribuer(commande));
		}
		
		return commande;
	}
	
	public static EnumStatusArticle computeStatusArticle(Article article){
		int count = article.getCount();
		int countAchete = article.getCountArticleAchete();
		int countFromStockageEnFrance = article.getCountArticleFromStockageFrance();
		int countFromStockageEnChine = article.getCountArticleFromStockageChine();
		int countFromStockageEnRoute = article.getCountArticleFromStockageEnRoute();
		
		int countPrepare = countAchete + countFromStockageEnFrance + countFromStockageEnChine + countFromStockageEnRoute;
		if(count == countPrepare) {
			return EnumStatusArticle.PREPARE_TOUT;
		}else if(count < countPrepare) {
			return EnumStatusArticle.QTE_INCORRECT;
		}else if(count > countPrepare && countPrepare != 0) {
			return EnumStatusArticle.PREPARE_PARTIE;
		}else {
			return EnumStatusArticle.NON_PREPARE;
		}
	}
	
	public static Commande createNewObjectCommande(Commande commande, Utilisateur utilisateur, EnumStatusCommande status) {
		Commande newCommande = new Commande();
		newCommande.setClient(commande.getClient());
		newCommande.setStatus(status);
		newCommande.setTypeCommande(commande.getTypeCommande());
		newCommande.setUtilisateur(utilisateur);
		
		return newCommande;
	}
}
