package pl.info.rkluszczynski.examples.upload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import pl.info.rkluszczynski.examples.upload.model.UploadedFile;
import pl.info.rkluszczynski.examples.upload.validator.FileValidator;

import java.io.*;

@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
    FileValidator fileValidator;

	@RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
	public ModelAndView getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
        logger.info("RUNNING /fileUploadForm");
		return new ModelAndView("uploadForm");
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUploaded(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
        logger.info("RUNNING /fileUpload");
		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String fileName = file.getOriginalFilename();

		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

		try {
			inputStream = file.getInputStream();

			File newFile = new File("C:/Users/nagesh.chauhan/files/" + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("showFile", "message", fileName);
	}

}
