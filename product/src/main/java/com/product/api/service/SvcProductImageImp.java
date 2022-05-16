package com.product.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;

/* Property source hace que referenciemos al archivo path.config para obtener la ruta */
@PropertySource("classpath:configuration/path.config")
@Service
public class SvcProductImageImp implements SvcProductImage {

	@Autowired
	RepoProductImage repo;
	
	//El nombre de la llave
	@Value("${product.images.path}")
	private String path;
	
	@Override
	public List<ProductImage> getProductImages(Integer product_id) {
		return repo.findByProductId(product_id);
	}

	@Override
	public ApiResponse createProductImage(ProductImage in) {
		try {
			File folder = new File(path + "/" + in.getProduct_id());
			if (!folder.exists())
				folder.mkdirs();
			
			String file = path + "/" + in.getProduct_id() + "/img_" + new Date().getTime() + ".bmp";
			
			byte[] data = Base64.getMimeDecoder().decode(in.getImage().substring(in.getImage().indexOf(",")+1, in.getImage().length()));
			
			try (OutputStream stream = new FileOutputStream(file)) {
				stream.write(data);
			}
			in.setStatus(1);
			in.setImage(in.getProduct_id() + "/img_" + new Date().getTime() + ".bmp");
			
			repo.save(in);
			return new ApiResponse("Se agregó una nueva imagen al producto!");
		} catch (Exception e) {
			throw new ApiException("No se pudo agregar una nueva imagen al producto.\n" + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse deleteProductImage(Integer product_id) {
		if (repo.deleteProductImage(product_id) > 0)
			return new ApiResponse("Imagen del producto eliminado!");
		else
			throw new ApiException("La imagen del producto no se pudo eliminar", HttpStatus.BAD_REQUEST);
	}

}
