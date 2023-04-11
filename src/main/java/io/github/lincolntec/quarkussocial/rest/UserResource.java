package io.github.lincolntec.quarkussocial.rest;

import io.github.lincolntec.quarkussocial.domain.model.User;
import io.github.lincolntec.quarkussocial.domain.repository.UserRepository;
import io.github.lincolntec.quarkussocial.rest.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository repository;

    @Inject
    public UserResource(UserRepository repository){

        this.repository = repository;
    }


    @POST
    @Transactional
    public Response createUser( CreateUserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        repository.persist(user);

        return Response.ok(userRequest).build();

    }

    @GET
    public Response listUserAlls(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userDate){
        User user = repository.findById(id);

        if(user != null){

            user.setName(userDate.getName());
            user.setAge(userDate.getAge());

            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }


}
