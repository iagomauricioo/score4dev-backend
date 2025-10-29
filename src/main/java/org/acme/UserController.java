package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
}
