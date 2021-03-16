package br.com.zup.proposta.compartilhado.validacao;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
public @interface CPFouCNPJ {

    String message() default  "CPF ou CNPJ precisam ser válidos!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
