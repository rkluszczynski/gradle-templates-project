package pl.info.rkluszczynski.examples.upload.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.info.rkluszczynski.examples.upload.model.UploadedFile;

public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object uploadedFile, Errors errors) {
		UploadedFile file = (UploadedFile) uploadedFile;

		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file",
                    "uploadForm.selectFile",
					"Please select a file with size > 0!");
		}
	}
}
