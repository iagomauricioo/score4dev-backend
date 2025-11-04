package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UserEntity;
import org.acme.service.UserService;

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

    @GET
    @Path("/{userId}")
    public Response getUserById(UUID userId) {
        UserEntity user = userService.getUserById(userId);
        return Response.ok(user).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity user) {
        return Response.status(Response.Status.CREATED).entity(userService.createUser(user)).build();
    }

    @PATCH
    @Path("/{userId}")
    @Transactional
    public Response editUser(UUID userId, UserEntity newUser) {
        return Response.ok(userService.editUser(userId, newUser)).build();
    }

    @DELETE
    @Path("/{userId}")
    @Transactional
    public Response deleteUser(UUID userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}
