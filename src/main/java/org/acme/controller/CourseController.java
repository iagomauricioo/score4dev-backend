package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.CourseEntity;
import org.acme.service.CourseService;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GET
    public Response getCourses() {
        return Response.ok(courseService.getCourses()).build();
    }

    @POST
    @Transactional
    public Response createCourse(CourseEntity course) {
        return Response.status(Response.Status.CREATED).entity(courseService.createCourse(course)).build();
    }

    @PATCH
    @Path("/{courseId}")
    @Transactional
    public Response editCourse(Long courseId, CourseEntity newCourse) {
        return Response.ok(courseService.editCourse(courseId, newCourse)).build();
    }
}
