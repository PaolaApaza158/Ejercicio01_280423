package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetectorServlet", urlPatterns = {"/DetectorServlet"})
public class DetectorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cookie[] cookies = request.getCookies();
        Cookie contador = buscaCookie("contador",cookies);
        
        if(contador == null){
            Cookie cookie = new Cookie("contador","1");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            
            //mostrando el mensaje de primera visita
            PrintWriter out = response.getWriter();
            out.println ("<html>");
            out.println ("<body>");
            out.print("<h2><br>¡Bienvenido a nuestro sitio Web!</h2>");
            out.println ("</body>");
            out.println ("</html>");
            
        }else{
            int cont = Integer.parseInt(contador.getValue());
            cont++;
            Cookie cookie = new Cookie("contador",""+cont);
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            PrintWriter out = response.getWriter();
            out.println ("<html>");
            out.println ("<body>");
            out.print("<h2><br>Gracias por visitarnos nuevamente :)</h2>");
            out.println ("</body>");
            out.println ("</html>");
        }
    }
    
    private Cookie buscaCookie(String nombre, Cookie[] cookies){
        if (cookies == null){
            return null;
        }
        for(int i = 0; i < cookies.length; i++){
            if(cookies[i].getName().equals(nombre)){
                return cookies[i];
            }
        }
        return null;
    }
}

