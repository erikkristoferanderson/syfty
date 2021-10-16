package net.syfty.app.form.validation;

import net.syfty.app.database.entity.User;
import net.syfty.app.database.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
//import org.perscholas.musicpollwebsite.database.entity.User;
//import org.perscholas.musicpollwebsite.database.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueImpl implements ConstraintValidator<UsernameUnique, String> {

	public static final Logger LOG = LoggerFactory.getLogger(UsernameUniqueImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UsernameUnique constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return true;
		}

		User user = userRepository.findByEmail(value);

		return (user == null);
	}

}
