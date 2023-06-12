package com.glecom.products.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadTest
 */
@WebServlet("/FileUploadTest")
public class FileUploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String imagePath = "G:/gl/db/images";
	private final String tempFilePath = "G:/gl/db/images/temp";
	private long maxImageSize = 20*1024;
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(tempFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(maxImageSize );
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem item:fileItems) {
				if(!item.isFormField()) {
					String name = new File(item.getName()).getName();
					item.write(new File(imagePath + File.separator + name));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
