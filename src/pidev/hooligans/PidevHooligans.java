/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.hooligans;

import classes.Categorie;
import classes.Produit;
import classes.User;
import classes.panier;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import service.CategorieService;
import service.PanierService;
import service.Produitservice;
/**
 *
 * @author ASUS
 */
public class PidevHooligans {
  
    

    
    
   public static void main(String[] args) {
    
    
    

   //ajout panier//

   
     User user = new User();
    user.setId_user(20); // Assurez-vous que l'ID de l'utilisateur est correct.

    // Créez une instance de la classe Produit
    Produit produit = new Produit();
    produit.setId_prod(71); // Assurez-vous que l'ID du produit est correct.
 
    // Créez une instance de la classe panier
    panier Panier = new panier();
    Panier.setQuantite_panier(1); // Par exemple, 3 unités du produit.

    // Appelez la méthode ajout en passant les instances que vous avez créées
     PanierService sp = new PanierService();
       int quantite_panier = 2;
       
          
 sp.ajout(produit,  user,  quantite_panier) ;
            
    
    int id_user = 20; // Remplacez par l'ID de l'utilisateur dont vous souhaitez afficher le panier
    sp.afficher(id_user);
   sp.sommePrixParUtilisateur();
    System.out.println("Quantité mise à jour avec succès.");
    Produitservice produitService = new Produitservice();

        // Appelez la méthode sommePrixParUtilisateurAvecRemise.
       sp.sommePrixParUtilisateurAvecRemise();
    }

   

    // Remplacez l'ID de l'utilisateur par celui que vous souhaitez tester
   // int id_user = 15;
   

    // Appelez la méthode totalmontantPanier pour obtenir le montant total du panier
   // double  totalMontant;
     // totalMontant = Panier.totalmontantPanier(id_user);


}
 
  
    
    // Créez une instance de la classe panier
    

    



            //sp.supprimer(12);
   
    
   
    
     
 // ID de l'utilisateur
    
    // Créez une instance de votre classe de gestion des paniers

    // Appelez la méthode modifier pour mettre à jour le panier

   //  User utilisateur = new User(100, "NomUtilisateur", "EmailUtilisateur");
    
    //Créez une instance de Panier.
   /* panier panier = new panier(100, utilisateur);
    
    //Créez une instance de PanierService.
    PanierService PanierService = new PanierService();
    
    // Utilisez le service de panier pour ajouter le panier à la base de données.
    PanierService.ajout(panier);
    System.out.println(panier);
    
    System.out.println("Panier ajouté avec succès !");
     PanierService.supprimer(122);*/
     
  
          

    // Appeler la méthode afficher() pour récupérer les catégories
   

    // Parcourir les catégories et afficher leurs informations

      /* Categorie categorie; 
         CategorieService c =new CategorieService();
        
        categorie = new Categorie(1,"foot","sport","activite");
         c.ajout(categorie);
         Produit p1;
        p1 = new Produit( "long", 11.0, "rond", 13, "image", categorie);
        Produitservice ps = new Produitservice() {};
        ps.ajout(p1);
        System.out.println(p1);
        
        System.out.println(categorie);
          Produit p4;
       p4 = new Produit(4, "llll", 11.0, "rrr", 13, "image", categorie);
        c.supprimer(26); 
        ps.supprimer(5);
        System.out.println("update  ");
        ps.modifier(p1);
      
        panier panierAModifier = new panier();
        panierAModifier.setTotal(750.1); // Nouvelle valeur pour 'total'

        // Supposons que vous avez un objet User (utilisateur) et un objet Produit associés au panier
       // User utilisateur = new User();
        //utilisateur.setId_user(15); // ID de l'utilisateur

        
      //  produit.setId_prod(69); // ID du produit associé

       // panierAModifier.setU(utilisateur); // Associez l'utilisateur au panier
       // panierAModifier.setP(produit); // Associez le produit au panier

        // Appelez la méthode modifier pour mettre à jour le panier
        modifier(panierAModifier);

      //  System.out.println("Méthode de modification du panier exécutée avec succès !");
    }

    private static void modifier(panier panierAModifier) {
       

         
   }

   
       
  
    // Remplacez ces valeurs par les données que vous souhaitez tester
    /*Produit produit = new Produit();
    produit.setId_prod(4); // ID du produit que vous voulez modifier
    produit.setNom_prod("Nouveau Nom");
    produit.setPrix_prod(19.99);
    produit.setdescription_prod("Nouvelle Description");
    produit.setquantite(10);
    produit.setImage("chemin/vers/image.jpg");
    
   
    categorie.setId_categorie(22); // ID de la catégorie existante ou non
    categorie.setNom_categorie("Nom de la catégorie");
    categorie.setDescription_categorie("Description de la catégorie");
    categorie.setType_categorie("Type de la catégorie");
    
    produit.setCategorie(categorie);

    // Maintenant, appelez votre méthode de modification
    Produitservice produitService = new Produitservice();
    produitService.modifier(produit);
}
}

  
        // Appelez la méthode chercher avec les valeurs appropriées
     /* System.out.println(ps.chercher("nom_prod", "long"));
       System.out.println(c.chercher("nom_categorie", "NouveauNom"));
       CategorieService contenantAfficher = new CategorieService(); // Remplacez "VotreClasse" par le nom de votre classe contenant la méthode afficher()
        ArrayList<Categorie> categories = contenantAfficher.afficher();
        for (Categorie ca : categories) {
        System.out.println("ID: " + ca.getId_categorie());
        System.out.println("Nom: " + ca.getNom_categorie());
        System.out.println("Description: " + ca.getDescription_categorie());
        System.out.println("Type: " + ca.getType_categorie());
        System.out.println("--------------------");


         CategorieService categorieService = new CategorieService();
    
    //Créez une nouvelle catégorie que vous souhaitez modifier.
    Categorie categorieAModifier = new Categorie( 51,"NouveauNom", "NouvelleDescription", "NouveauType");

    // Utilisez la méthode modifier pour mettre à jour la catégorie.
    categorieService.modifier(categorieAModifier);

    // Vous pouvez également afficher la catégorie après la modification.
    System.out.println("Catégorie après modification : " + categorieService.readById(categorieAModifier.getId_categorie()));
    
   
   }
   
     
    // Créez une instance de votre classe de gestion des catégories
    // Supposons que vous avez une classe CategorieService avec la méthode sortBy
    CategorieService categorieService = new CategorieService();

    // Appelez la méthode sortBy pour trier les catégories par nom en ordre croissant
    ArrayList<Categorie> categoriesAsc = categorieService.sortBy("nom_categorie", "ASC");

    // Affichez les catégories triées par ordre croissant
    /*for (Categorie categorie : categoriesAsc) {
        System.out.println("ID: " + categorie.getId_categorie());
        System.out.println("Nom: " + categorie.getNom_categorie());
        System.out.println("Description: " + categorie.getDescription_categorie());
        System.out.println("Type: " + categorie.getType_categorie());
        System.out.println();
    }

    // Appelez la méthode sortBy pour trier les catégories par nom en ordre décroissant
    ArrayList<Categorie> categoriesDesc = categorieService.sortBy("nom_categorie", "DESC");

    // Affichez les catégories triées par ordre décroissant
    for (Categorie categorie : categoriesDesc) {
        System.out.println("ID: " + categorie.getId_categorie());
        System.out.println("Nom: " + categorie.getNom_categorie());
        System.out.println("Description: " + categorie.getDescription_categorie());
        System.out.println("Type: " + categorie.getType_categorie());
        System.out.println();
    }*/
  

   







  
    
     
   
   
       


 
   
    





    
    
  

   


  
    