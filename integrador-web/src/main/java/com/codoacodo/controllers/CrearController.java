package com.codoacodo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codoacodo.dao.OradorDAO;

/*HERENCIA*/
//Create Controller es hijo de :
@WebServlet("/CreateController")

public class CrearController extends HttpServlet
{
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String mail = req.getParameter("mail");
		String tema = req.getParameter("tema");
		String fechaAlta = req.getParameter("fechaAlta");
		
		//crear ProductoDAO
		OradorDAO dao = new OradorDAO();
		
		//ejecutar el metodo crearProducto(parametros...)
		dao.crearOrador(nombre, apellido, mail, tema, fechaAlta);
		
		//ctrl+shit+o
		//ir a la siguiente pagina
		resp.sendRedirect(req.getContextPath()+"/api/ListadoController");
	}
}
