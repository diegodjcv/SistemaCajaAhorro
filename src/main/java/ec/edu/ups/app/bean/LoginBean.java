package ec.edu.ups.app.bean;

import ec.edu.ups.app.bussiness.UsuarioON;
import ec.edu.ups.app.modelo.Rol;
import ec.edu.ups.app.modelo.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioON usuarioON;

    private String usuario;
    private String clave;

    private Usuario usuarioLogueado;

    public String login() {
    	String res="";
        try {

            usuarioLogueado = usuarioON.login(usuario, clave);
            System.out.println(usuarioLogueado.getPersona().getNombres());
            if (usuarioLogueado != null) {

                HttpSession session = (HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(true);

                session.setAttribute("usuario", usuarioLogueado);
                System.out.println("login");
                Rol r= new Rol();
                r=usuarioLogueado.getRol();
                System.out.println(r.getTipo()+":::::::::::::::::::::::ROL");
                
                if(r.getTipo().equals("ADMINISTRADOR")) {
                	res = "/dashboardAdmin.xhtml?faces-redirect=true";
                }else {
                	res = "/dashboard.xhtml?faces-redirect=true";
                }
                
                
                
            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error",
                                "Usuario o contraseña incorrectos"));

            }

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error:::::::::",
                            e.getMessage()));

        }
        System.out.println("RESULTADO: "+res);
        return res;

    }
/**
    public void logout() {

        try {

            HttpSession session = (HttpSession) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getSession(false);

            if (session != null) {
                session.invalidate();
            }

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            FacesContext.getCurrentInstance()
                                    .getExternalContext()
                                    .getRequestContextPath()
                                    + "/dashboard.xhtml?faces-redirect=true");
            System.out.println("::__::__::__::__");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
**/
    public boolean isLogueado() {

        return usuarioLogueado != null;

    }
    
    public void imp() {
    	System.out.println("::::::::::::::::::::::"+login());
    }
    
    public void limpiar() {
    	usuario ="";
    	clave = "";
    }

    public String getNombreUsuario() {

        if (usuarioLogueado != null) {
            return usuarioLogueado.getPersona().getNombres() + " "
                    + usuarioLogueado.getPersona().getApellidos();
        }

        return "";

    }

    //=========================
    // Getters y Setters
    //=========================

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}