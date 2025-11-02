package org.acme.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import org.acme.exception.UnprocessableEntityException;

@Embeddable
@Access(AccessType.FIELD)
public class Username {
    private String value;

    protected Username() {
    }

    public Username(String value) {
        if (!value.matches("^[A-ZÀ-ÿ][a-zà-ÿ]+(\\s[A-ZÀ-ÿ][a-zà-ÿ]+)+$")) {
            throw new UnprocessableEntityException("Nome de usuário inválido");
        }
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
