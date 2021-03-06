package pl.wolski.bank.annotations;


import org.springframework.beans.factory.annotation.Autowired;
import pl.wolski.bank.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UniquePersonalIdentificationNumberValidator implements ConstraintValidator<UniquePersonalIdentificationNumber, BigDecimal> {

    @Autowired(required = false)
    private UserService userService;

    public void initialize(UniquePersonalIdentificationNumber constraint) {
    }

    public boolean isValid(BigDecimal personalIdentificationNumber, ConstraintValidatorContext context) {
        //w trakcie uruchamiania RepositoryInitializer usługi userService jeszcze nie ma wstrzykniętej do tego walidatora dlatego userService == null.
        return userService == null || (personalIdentificationNumber != null && userService.isUniquePersonalIdentificationNumber(personalIdentificationNumber));
    }

}