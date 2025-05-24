package com.libreria.techbook;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class MyController {

	
    private final ProdottoJDBCTemp prodottoJDBCTemp;

    @Autowired
    public MyController(ProdottoJDBCTemp prodottoJDBCTemp) {
        this.prodottoJDBCTemp = prodottoJDBCTemp;
    }
    
   
    

    @GetMapping("/")
		public String mostraListaUsers(Model model) {
    	  // Verifica e crea le tabelle se non esistono già
        prodottoJDBCTemp.creaNuovaTabUsers();
        prodottoJDBCTemp.creaNuovaTabella();
        
        
            List<User> listUsers = prodottoJDBCTemp.ritornaUsers();
            ArrayList<Prodotto> lista = prodottoJDBCTemp.ritornaProdotto();
            model.addAttribute("listUsers", listUsers);
            model.addAttribute("lista", lista);
         
		return "vetrinaLogout";
    }
    

}

