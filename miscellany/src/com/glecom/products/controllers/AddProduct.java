package com.glecom.products.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.glecom.products.models.Product;
import com.glecom.products.models.ProductBuilder;
import com.glecom.products.models.dao.ProductDao;

/**
 * Servlet implementation class AddProduct
 */
/**
 * @author navneet
 * used for adding new product.
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String imageFolderPath = "G:/gl/eclipse-workshop/miscellany_n/WebContent/product_images/new";
	private final String serverFolderPath = "./product_images/new";
	private final String tempFilePath = "G:/gl/db/images/temp";
	private long maxImageSize = 5*1024*1024;
	private ProductBuilder productBuilder = new ProductBuilder();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(tempFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxImageSize );
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(!item.isFormField()) {
					String name = new File(item.getName()).getName();
					productBuilder.imagePath = serverFolderPath + "/" + name;
					item.write(new File(imageFolderPath + "/" + name));
				}
				else productBuilder.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProductDao productDao = new ProductDao();
		try {
			productDao.addProduct(new Product(productBuilder));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("msg", "Your product is added. add another.");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./add-product.jsp");
		request.setAttribute("success", true);
		requestDispatcher.forward(request, response);
	}

}
