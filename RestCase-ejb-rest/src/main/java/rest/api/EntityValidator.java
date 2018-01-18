package rest.api;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

public class EntityValidator<T> {

    private static final Logger log = Logger.getLogger(EntityValidator.class);

    private List<String> messages;

    public boolean validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraints = validator.validate(entity);
        messages = new ArrayList<>();
        String s;
        for (ConstraintViolation<T> constraint : constraints) {
            s = constraint.getPropertyPath() + "  " + constraint.getMessage();
            messages.add(s);
            log.warn( s);

        }
        return constraints.isEmpty();
    }

    public List<String> getMessages() {
        if (messages == null) return Collections.emptyList();
        return messages;
    }
}