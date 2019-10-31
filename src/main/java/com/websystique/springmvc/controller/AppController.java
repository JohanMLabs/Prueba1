package com.websystique.springmvc.controller;





import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.model.UserProfile;


import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Bodega;
import com.websystique.springmvc.model.Compras;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.BodegaService;
import com.websystique.springmvc.service.ComprasService;


import javax.annotation.Resource;


import javax.persistence.PersistenceUnit;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes
//@Resource(name = "singleTransactionsService")


public class AppController {

    @Autowired
    UserService userService;
    
    
   @Autowired
   BodegaService bodegaService;
   @Autowired
   ComprasService comprasService;


    @Autowired
    UserProfileService userProfileService;


    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
  

//------------------------------------- Usuarios------------------------------------------------------------
    
    
 //---Listar Usuarios------------------------------------------------------------
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }
 
//---Registrar Usuario----------------------------------------------------------
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
 
//---Registrar Usuario Save-----------------------------------------------------

    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!userService.isUserUSUUnique(user.getId(), user.getUsuId())) {
            FieldError usuError = new FieldError("user", "usuId", messageSource.getMessage("non.unique.usuId", new String[]{user.getUsuId()}, Locale.getDefault()));
            result.addError(usuError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "Usuario " + user.getNombre() + " " + user.getApellido() + " Registrado correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccess";
    }

//---Editar Usuario-------------------------------------------------------------
    @RequestMapping(value = {"/edit-user-{usuId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String usuId, ModelMap model) {
        User user = userService.findByUSU(usuId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
  
//---Editar Usuarios Save-------------------------------------------------------
    @RequestMapping(value = {"/edit-user-{usuId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable String usuId) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!userService.isUserUSUUnique(user.getId(), user.getUsuId())) {
            FieldError usuError = new FieldError("user", "usuId", messageSource.getMessage("non.unique.usuId", new String[]{user.getUsuId()}, Locale.getDefault()));
            result.addError(usuError);
            return "registration";
        }

        userService.updateUser(user);

        model.addAttribute("success", "Usuario " + user.getNombre() + " " + user.getApellido() + " Actualizado correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }

//---Eliminar Usuario-----------------------------------------------------------
    @RequestMapping(value = {"/delete-user-{usuId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String usuId) {
        userService.deleteUserByUSU(usuId);
        return "redirect:/list";
    }
    
    //----------------------------------Roles Usuario---------------------------
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    
    
//------------------------------------- Bodegas------------------------------------------------------------
//---Bodega Listar--------------------------------------------------------------
    
        @RequestMapping(value = {"/listb"}, method = RequestMethod.GET)
    public String listBodegas(ModelMap model) {

        List<Bodega> bodegas = bodegaService.findAllBodegas();
        model.addAttribute("bodegas", bodegas);
        model.addAttribute("loggedinuser", getPrincipal());
        return "bodegalist";
    }
 
    
    
     
    
//---Registrar Bodega-----------------------------------------------------------
    @RequestMapping(value = {"/newbodega"}, method = RequestMethod.GET)
    public String newBodega(ModelMap model) {
        Bodega bodega = new Bodega();
        model.addAttribute("bodega", bodega);
        model.addAttribute("editB", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationB";
    }
 
//---Registrar Bodega Save------------------------------------------------------

    @RequestMapping(value = {"/newbodega"}, method = RequestMethod.POST)
    public String saveUser(@Valid Bodega bodega, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "registrationB";
        }

        if (!bodegaService.isBodegaBODEGUnique(bodega.getId(), bodega.getBodegId())) {
            FieldError bodegError = new FieldError("bodega", "bodegId", messageSource.getMessage("non.unique.bodegId", new String[]{bodega.getBodegId()}, Locale.getDefault()));
            result.addError(bodegError);
            return "registrationB";
        }

        bodegaService.saveBodega(bodega);

        model.addAttribute("success", "El Objeto " + bodega.getBodegId() + " " + bodega.getDescripcion() + " Registrada correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccessB";
    }

//---Editar Bodega--------------------------------------------------------------
    @RequestMapping(value = {"/edit-bodega-{bodegId}"}, method = RequestMethod.GET)
    public String editBodega(@PathVariable String bodegId, ModelMap model) {
        Bodega bodega = bodegaService.findByBODEG(bodegId);
        model.addAttribute("bodega", bodega);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationB";
    }
//---Editar Bodegas Save-------------------------------------------------------
      @RequestMapping(value = {"/edit-bodega-{bodegId}"}, method = RequestMethod.POST)
    public String updateBodega(@Valid Bodega bodega, BindingResult result,
            ModelMap model, @PathVariable String bodegId) {

        if (result.hasErrors()) {
            return "registrationB";
        }

        if (!bodegaService.isBodegaBODEGUnique(bodega.getId(), bodega.getBodegId())) {
            FieldError bodegError = new FieldError("bodega", "bodegId", messageSource.getMessage("non.unique.bodegaId", new String[]{bodega.getBodegId()}, Locale.getDefault()));
            result.addError(bodegError);
            return "registrationB";
        }

        bodegaService.updateBodega(bodega);

        model.addAttribute("success", "El Objeto " + bodega.getBodegId() + " " + bodega.getDescripcion() + " Actualizado correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccessB";
    }


//---Eliminar Bodega------------------------------------------------------------
    @RequestMapping(value = {"/delete-bodega-{bodegId}"}, method = RequestMethod.GET)
    public String deleteBodega(@PathVariable String bodegId) {
        bodegaService.deleteBodegaByBODEG(bodegId);
        return "redirect:/listb";
    }

    
    

 //------------------------------------- Compras------------------------------------------------------------
//---Compras Listar-------------------------------------------------------------
    
        @RequestMapping(value = {"/listc"}, method = RequestMethod.GET)
    public String listCompras(ModelMap model) {

        List<Compras> compras = comprasService.findAllCompras();
        model.addAttribute("compras", compras);
        model.addAttribute("loggedinuser", getPrincipal());
        return "compraslist";
    }
 //---Registrar Compras-----------------------------------------------------------
    @RequestMapping(value = {"/newcompras"}, method = RequestMethod.GET)
    public String newCompras(ModelMap model) {
        Compras compra = new Compras();
        model.addAttribute("compra", compra);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationC";
    }
 
//---Registrar Compras Save------------------------------------------------------

    @RequestMapping(value = {"/newcompras"}, method = RequestMethod.POST)
    public String saveCompras(@Valid Compras compras, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "registrationC";
        }

        if (!comprasService.isComprasCOMUnique(compras.getId(), compras.getNombreId())) {
            FieldError comError = new FieldError("Compras", "nombreId", messageSource.getMessage("non.unique.nombreId", new String[]{compras.getNombreId()}, Locale.getDefault()));
            result.addError(comError);
            return "registrationC";
        }

        comprasService.saveCompras(compras);

        model.addAttribute("success", "El Objeto " + compras.getNombreId() + " " + compras.getDescripcion() + " Registrada correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccessC";
    }
    
    //---Editar Compras--------------------------------------------------------------
    @RequestMapping(value = {"/edit-compras-{nombreId}"}, method = RequestMethod.GET)
    public String editCompras(@PathVariable String nombreId, ModelMap model) {
        Compras compras = comprasService.findByCOM(nombreId);
        model.addAttribute("compras", compras);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationC";
    }
//---Editar Compras Save-------------------------------------------------------
      @RequestMapping(value = {"/edit-compras-{nombreId}"}, method = RequestMethod.POST)
    public String updateCompras(@Valid Compras compras, BindingResult result,
            ModelMap model, @PathVariable String nombreId) {

        if (result.hasErrors()) {
            return "registrationC";
        }

        if (!comprasService.isComprasCOMUnique(compras.getId(), compras.getNombreId())) {
            FieldError comError = new FieldError("compras", "nombreId", messageSource.getMessage("non.unique.nombreId", new String[]{compras.getNombreId()}, Locale.getDefault()));
            result.addError(comError);
            return "registrationC";
        }

        comprasService.updateCompras(compras);

        model.addAttribute("success", "El Objeto " + compras.getNombreId() + " " + compras.getDescripcion() + " Actualizado correctamente");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccessC";
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//--------------------------------Acceso denegado-----------------------------------------------------------	
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

//---------------------------   --Login-----------------------------------------------------------------	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

//------------------------------LogOut------------------------------------------------------------------	
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {

            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

//------------------------------Retorno Principal----------------------------------------------------------------	
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

//----------------------------Autentificacion Anonymous------------------------------------------------------------------	
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
    
    
    
	
		

       

	


	

	

}
