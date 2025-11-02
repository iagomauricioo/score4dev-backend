package org.acme;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getUsers() {
        return Response.ok(userService.getUsers()).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity user) {
        return Response.ok(userService.createUser(user)).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(UUID userId) {
        try {
            UserEntity user = userService.getUserById(userId);
            return Response.ok(user).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
