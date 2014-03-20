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
import pl.info.rkluszczynski.examples.upload.model.UploadedFile;
import pl.info.rkluszczynski.examples.upload.validator.FileValidator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
    FileValidator fileValidator;


	@RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
	public ModelAndView getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result)
    {
        logger.info("Executed GET /fileUploadForm");
		return new ModelAndView("uploadForm");
	}


	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public ModelAndView fileUploaded(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result,
            HttpSession session)
    {
        logger.info("Executed POST /fileUpload");

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);
		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

        String fileName = file.getOriginalFilename();
        try {
                BufferedImage imgBuff = ImageIO.read(file.getInputStream());
                logger.info("Successfully read image: " + fileName);
                session.setAttribute("uploadedImage", imgBuff);
		} catch (IOException e) {
            logger.warn("Problem reading image: " + fileName + ". Is it really image?", e);
		}
		return new ModelAndView("showFile", "message", fileName);
	}


    @RequestMapping(value = "/uploadedContent", method = RequestMethod.GET)
    public ModelAndView streamUploadedContent(
            HttpServletResponse response,
            HttpSession session)
    {
        if (session.getAttribute("uploadedImage") != null) {
            BufferedImage imgBuff = (BufferedImage) session.getAttribute("uploadedImage");
            try {
                ImageIO.write(imgBuff, "PNG", response.getOutputStream());
            } catch (IOException e) {
                logger.warn("Problem with streaming image!", e);
            }
        }
        return null;
    }
}
