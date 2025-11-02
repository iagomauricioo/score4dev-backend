package org.acme.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class UnprocessableEntityMapper implements ExceptionMapper<UnprocessableEntityException> {

    @Override
    public Response toResponse(UnprocessableEntityException exception) {
        return Response
                .status(422)
                .entity(new ErrorResponse(422, "Erro de validação", exception.getMessage()))
                .build();
    }
}
