package org.acme.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.entity.dto.ErrorResponseDTO;

import java.time.Instant;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof EntityNotFoundException) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponseDTO(Response.Status.NOT_FOUND.getStatusCode(), "Recurso não encontrado", exception.getMessage()))
                    .build();
        }

        if (exception instanceof UnprocessableEntityException) {
            return Response
                    .status(422)
                    .entity(new ErrorResponseDTO(422, "Erro de validação", exception.getMessage()))
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponseDTO(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro interno do servidor", exception.getMessage()))
                .build();
    }
}
