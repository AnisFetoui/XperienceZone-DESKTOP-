/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.MYDB;
import classes.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.Categorie;
import classes.Evenement;
import classes.Produit;
import classes.Traitement;
import classes.User;
import classes.activites;
import Interface.IService;



/**
 *
 * @author LENOVO GAMING
 */
public class ServiceReclamation implements IService<Reclamation> {

  private Connection con;
    private PreparedStatement pre;
    private Statement ste;
    
    public ServiceReclamation() {
        con = MYDB.getinstance().getCon();
    }

    @Override
    public void ajouterR(Reclamation r) {
    try {
        String req = "INSERT INTO reclamations(idU,dateREC,typeRec,refObject,details) VALUES (?,?,?,?,?)";
        pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pre.setInt(1, r.getIdU());
        pre.setDate(2, r.getDateREC());
        pre.setInt(3, r.getTypeRec());
        pre.setInt(4, r.getRefObject());
        pre.setString(5, r.getDetails());

        pre.executeUpdate();

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int idRFromDatabase = generatedKeys.getInt(1);
                r.setIdR(idRFromDatabase);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    
    @Override
    public void supprimerR(int idR) {
    try {
        String req = "DELETE FROM reclamations WHERE idR = ?";
        pre = con.prepareStatement(req);
        pre.setInt(1, idR);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
   
@Override
public void modifierR(Reclamation r) {
    try {
        if (r.getIdR() == 0) {
            System.out.println("L'objet Reclamation n'a pas d'ID défini. Impossible de modifier.");
            return;
        }

        String req = "UPDATE reclamations SET idU=?, dateREC=?, typeRec=?, refObject=?, details=? WHERE idR = ?";
        pre = con.prepareStatement(req);
        pre.setInt(1, r.getIdU());
        pre.setDate(2, r.getDateREC());
        pre.setInt(3, r.getTypeRec());
        pre.setInt(4, r.getRefObject());
        pre.setString(5, r.getDetails());
        pre.setInt(6, r.getIdR()); // Utilisation de l'ID pour identifier la ligne à mettre à jour

        // Exécutez la mise à jour
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    @Override
    public List afficher() {
         List<Reclamation> reclamation = new ArrayList<>();
        String sql ="select * from reclamations";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setIdR(rs.getInt("idR"));
                r.setIdU(rs.getInt("idU"));
                r.setDateREC(rs.getDate("dateREC"));
                r.setTypeRec(rs.getInt("typeRec"));
                r.setRefObject(rs.getInt("refObject"));
                r.setDetails(rs.getString("details"));
                reclamation.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamation;
    }

    public void ajouterT(Traitement t) {
           try {
         String req = "INSERT INTO traitements(idrec,refobj,dateR,idU,typeR,resume,stat)values(?,?,?,?,?,?,?)";
            pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, t.getIdrec());
            pre.setInt(2, t.getRefobj());
            pre.setDate(3, t.getDateR());
            pre.setInt(4, t.getIdU());
            pre.setInt(5, t.getTypeR());
            pre.setString(6, t.getResume());
            pre.setString(7, t.getStat());
            pre.executeUpdate();

       

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int idTFromDatabase = generatedKeys.getInt(1);
                t.setIdT(idTFromDatabase);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }
    
    
    public void modifierT(Traitement t) {
    try {
        if (t.getIdT() == 0) {
            System.out.println("L'objet traitement n'a pas d'ID défini. Impossible de modifier.");
            return;
        }
        
        String req = "UPDATE traitements SET idrec=?, refobj=?, dateR=?, idU=?, typeR=?, resume=?, stat=? WHERE idT = ?";
            pre = con.prepareStatement(req);
            pre.setInt(1, t.getIdrec());
            pre.setInt(2, t.getRefobj());
            pre.setDate(3, t.getDateR());
            pre.setInt(4, t.getIdU());
            pre.setInt(5, t.getTypeR());
            pre.setString(6, t.getResume());
            pre.setString(7, t.getStat());
            pre.setInt(8, t.getIdT());
            
        
        
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    
        public void supprimerT(int idT) {
    try {
        String req = "DELETE FROM traitements WHERE idT = ?";
        pre = con.prepareStatement(req);
        pre.setInt(1, idT);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
        


  public List afficherT() {
         List<Traitement> traitement = new ArrayList<>();
        String sql ="select * FROM traitementS";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Traitement t = new Traitement();
                t.setIdT(rs.getInt("idT"));
                t.setIdrec(rs.getInt("idrec"));
                t.setRefobj(rs.getInt("refobj"));
                t.setDateR(rs.getDate("dateR"));
                t.setIdU(rs.getInt("idU"));
                t.setTypeR(rs.getInt("typeR"));
                t.setResume(rs.getString("resume"));
                t.setStat(rs.getString("stat"));




                
                traitement.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return traitement;
    }

       public User chercherByEmail(String email) {
    String sql ="SELECT * FROM utilisateur WHERE mail='"+email+"'";
    User u = new User();
    try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            
            u = new User(rs.getInt("id_user"),
                      rs.getString("username"), rs.getString("mail"),rs.getString("mdp"), rs.getString("role"),rs.getString("image"), rs.getInt("age"), rs.getString("sexe"));
        }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    return u;
    } 

    public List<Evenement> afficherevent() {
        //var
        
       
        List<Evenement> evenements =new ArrayList<>();
        //requette
        String req ="SELECT * FROM evenement";
          try {
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(req);
              while (rs.next()){
                  evenements.add(new Evenement(rs.getInt(1), rs.getString(2),rs.getString(3) ,rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
              }
          } catch (SQLException ex) {
              }
    
        return evenements;
       
    }
    
    
        public List afficherA() {
         List<activites> activite = new ArrayList<>();
        String sql ="select * FROM activites";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                activites r = new activites();
                r.setId_act(rs.getInt("Id_act"));
                r.setNom_act(rs.getString("nom_act"));
                r.setDescription(rs.getString("description"));
                r.setOrganisateur(rs.getString("organisateur"));
                r.setLieu_act(rs.getString("lieu_act"));
                r.setAdresse(rs.getString("adresse"));
                r.setImages(rs.getString("images"));
                r.setPlace_dispo(rs.getInt("place_dispo"));
                r.setPrix_act(rs.getString("prix_act"));
                r.setDuree(rs.getInt("duree"));
                r.setPeriode(rs.getString("periode"));
                r.setIdUser(rs.getInt("id_user"));
                
                
                activite.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activite;
    }

public List<Produit> affihcer() {
    List<Produit> produits = new ArrayList<>();
    try {
        String req = "SELECT * FROM produit";
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(req);
        ServiceReclamation cs = new ServiceReclamation();
        while (rs.next()) {
            Produit p = new Produit();
            p.setId_prod(rs.getInt(1));
            p.setNom_prod(rs.getString(2));
            p.setPrix_prod(rs.getDouble(3));
            p.setdescription_prod(rs.getString(4));
            p.setquantite(rs.getInt(5));
            p.setImage(rs.getString(6));
            p.setCategorie(cs.readById(rs.getInt(7)));
            produits.add(p);
        }
    } catch (SQLException ex) {
        // Gérer les exceptions ici
    }
    return  produits;
}

    public Categorie readById(int id) {
 Categorie c = new Categorie();
        try {
            
       String req="SELECT * FROM categorie WHERE `id_categorie`='"+id+"'";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(req);
            rs.beforeFirst();
            rs.next();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setDescription_categorie(rs.getString(3));           
                c.setType_categorie(rs.getString(4)); 
        } catch (SQLException ex) {
        }
        return  c;
    }

    
       public void ajouterActivite(activites a) {
        try {
            String req = "INSERT INTO activites (nom_act, description, organisateur,lieu_act,adresse,images, place_dispo, prix_act,durée,periode,id_user)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, a.getNom_act());
            pre.setString(2, a.getDescription());
            pre.setString(3, a.getOrganisateur());
            pre.setString(4, a.getLieu_act());
            pre.setString(5, a.getAdresse());
            pre.setString(6, a.getImages());
            pre.setInt(7, a.getPlace_dispo());
            pre.setString(8, a.getPrix_act());
            pre.setInt(9, a.getDuree());
            pre.setString(10, a.getPeriode());
            pre.setInt(11,a.getId_user());//lajout de id user
            //ajouter la fonction de anis connexion pour recuperer lid du utilisateur connecté
            pre.executeUpdate();
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }
       
public void ajouterevenement(Evenement r) {
    try {
        String req = "INSERT INTO evenement (nom_event, descript, date_event, heure_event, lieu_event, nb_participants, image, organisateur) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, r.getNom_event());
        pre.setString(2, r.getDescript());
        pre.setDate(3, r.getDate_event());
        pre.setTime(4, Time.valueOf(r.getHeure_event()));
        pre.setString(5, r.getLieu_event());
        pre.setInt(6, r.getNb_participant());
        pre.setString(7, r.getImage());
        pre.setString(8, r.getOrganisateur());
        pre.executeUpdate(); // Correction : Utilisez executeUpdate() sans argument
        System.out.println("Événement ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    public void ajout (Produit p)  {
    
     
       try {
            String req;
           req = "INSERT INTO Produit(Nom_prod,Prix_prod,description_prod,quantite,image,id_categorie)values(?,?,?,?,?,?)";
            PreparedStatement pre;
           pre = con.prepareStatement(req);
            pre.setString(1,p.getNom_prod());
            pre.setDouble(2,p.getPrix_prod());
            pre.setString(3,p.getdescription_prod());
            pre.setInt(4,p.getquantite());
            pre.setString(5,p.getImage());
            pre.setInt(6, p.getCategorie().getId_categorie());            
            pre.executeUpdate();
            System.out.println("Produit Added Successfully!");
       
      } catch (SQLException ex) {
                System.out.println(ex);
            
      }
 }
    
       public void ajout (Categorie c)  {
    
     
       try {
            String req;
           req = "INSERT INTO Categorie(Nom_categorie,Description_categorie,Type_categorie)values(?,?,?)";
            PreparedStatement pre =con.prepareStatement(req);
            pre.setString(1,c.getNom_categorie());          
            pre.setString(2,c.getDescription_categorie());            
            pre.setString(3,c.getType_categorie());
            
            pre.executeUpdate();
            System.out.println("Categorie Added Successfully!");
       
      } catch (SQLException ex) {
                System.out.println(ex);
            
      }
       
 } 
       


    // Méthode pour obtenir les statistiques de réclamations par type
    public Map<Integer, Integer> getReclamationStatistics() {
        // Créer une connexion à la base de données
        

        // Map pour stocker les statistiques
        Map<Integer, Integer> statistics = new HashMap<>();

        // Requête SQL pour compter les réclamations par type
        String query = "SELECT typeRec, COUNT(*) AS count FROM reclamations GROUP BY typeRec";

        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int typeRec = resultSet.getInt("typeRec");
                int count = resultSet.getInt("count");
                statistics.put(typeRec, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

      

        return statistics;
    }
public Traitement getTraitementParIdReclamation(int idReclamation) throws SQLException {
    Traitement traitement = null;

    try {
        String query = "SELECT * FROM traitements WHERE idrec = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idReclamation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    traitement = new Traitement();
                    traitement.setIdT(resultSet.getInt("idT"));
                    traitement.setIdrec(resultSet.getInt("idrec"));
                    traitement.setIdU(resultSet.getInt("idU"));
                    traitement.setRefobj(resultSet.getInt("refobj"));
                    traitement.setDateR(resultSet.getDate("dateR"));
                    traitement.setTypeR(resultSet.getInt("typeR"));
                    traitement.setResume(resultSet.getString("resume"));
                    traitement.setStat(resultSet.getString("stat"));
                }
            }
        }
    } catch (SQLException ex) {
        // Gérez l'exception appropriée ici, par exemple, journalisez ou renvoyez null
        ex.printStackTrace(); // Journalisation de l'exception
        throw ex; // Vous pouvez également lancer l'exception à l'appelant si nécessaire
    }

    return traitement;
}
  

  public Map<String, Integer> getTraitementStatistics() {
        // Créer une connexion à la base de données
        

        // Map pour stocker les statistiques
        Map<String, Integer> statisticsT = new HashMap<>();

        // Requête SQL pour compter les réclamations par type
        String query = "SELECT stat, COUNT(*) AS count FROM traitements GROUP BY stat";

        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String stat = resultSet.getString("stat");
                int count = resultSet.getInt("count");
                statisticsT.put(stat, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return statisticsT;
}
}

