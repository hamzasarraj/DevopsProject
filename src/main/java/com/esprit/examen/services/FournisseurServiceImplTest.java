package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

@ExtendWith(SpringExtension.class)
public class FournisseurServiceImplTest {
	
	@InjectMocks
	FournisseurServiceImpl FournisseurService;
	
    @Mock
    private FournisseurRepository fournisseurRepository;
    
    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;
    @Mock
    private SecteurActiviteRepository  secteurActiviteRepository;

    private Fournisseur fournisseur1 ;
    private Fournisseur fournisseur2 ;
    private DetailFournisseur detailFournisseur ;
    private SecteurActivite secteurActivite;
    
    @BeforeEach
    void setUp(){
    fournisseur1 = new Fournisseur();
    fournisseur2 = new Fournisseur();
    detailFournisseur =new DetailFournisseur();
    }
	
	@Test 
	public void testAddfournisseur() {
    	when(fournisseurRepository.save(fournisseur1)).thenReturn(fournisseur1);
    
    	Fournisseur persistedfournisseur = FournisseurService.addFournisseur(fournisseur1);
    	
		assertEquals(fournisseur1, persistedfournisseur); 
	} 
	
	@Test 
     void testRetrieveAllFournisseurs() {
    	List<Fournisseur> listOffournisseurs=new ArrayList<Fournisseur>();
    	listOffournisseurs.add(fournisseur1);
    	listOffournisseurs.add(fournisseur2);
		when(fournisseurRepository.findAll()).thenReturn(listOffournisseurs);
    	assertEquals(2,fournisseurRepository.retrieveAllProduits().size());
    	assertEquals(listOffournisseurs,FournisseurService.retrieveAllFournisseurs());
    }
    @Test 
    public void testUpdateFournisseur() {
    	when(fournisseurRepository.save(fournisseur1)).thenReturn(fournisseur1);
    	assertEquals(fournisseur1, FournisseurService.updateFournisseur(fournisseur1));
    }
    @Test
     void testRetrieveFournisseur() {
    	when(fournisseurRepository.findById(fournisseur1.getDetailFournisseur)).thenReturn(Optional.of(fournisseur1));
    	
    	assertEquals(fournisseur1, FournisseurService.retrieveFournisseur(fournisseur1.getDetailFournisseur));
    }
    @Test
    void testAssignFournisseurTosecteuractivity() {
    	when(fournisseurRepository.findById(fournisseur1.getDetailFournisseur)).thenReturn(Optional.of(fournisseur1));
    	when(secteurActiviteRepository.findById(SecteurActivite.getSecteurActivites)).thenReturn(Optional.of(secteurActivite));
    	when(fournisseurRepository.save(fournisseur1)).thenReturn(fournisseur1);
    	
    	FournisseurService.assignSecteurActiviteToFournisseur(fournisseur1.getSecteurActivites, secteurActivite.getSecteurActivites);
    	
    	verify(fournisseurRepository,times(1)).findById(fournisseur1.getDetailFournisseur);
    	verify(secteurActiviteRepository,times(1)).findById(secteurActivite.getSecteurActivites);
    	verify(fournisseurRepository,times(1)).save(fournisseur1);
    	
    }
}