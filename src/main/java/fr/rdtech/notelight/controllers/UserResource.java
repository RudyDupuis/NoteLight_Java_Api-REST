package fr.rdtech.notelight.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.rdtech.notelight.bll.NoteManager;
import fr.rdtech.notelight.bll.UserManager;
import fr.rdtech.notelight.bo.Note;
import fr.rdtech.notelight.bo.User;
import fr.rdtech.notelight.bo.UserDTO;

@Path("/users")
public class UserResource {
	private UserManager userManager = UserManager.getInstance();
	private NoteManager noteManager = NoteManager.getInstance();
	
	@POST @Path("/connect")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response connectionUser(UserDTO userDTO) {
		User user;
		
		try {
			user = userManager.connectionUser(new User(userDTO.getMail(), userDTO.getPassword()));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.status(Response.Status.OK).entity(user).build();
	}
		
	
	
	@GET @Path("/{idUser: \\d+}/notes")
	public Response selectAll(@PathParam("idUser") int idUser){
		List<Note> notes;
		
		try {
			notes = noteManager.selectAll(idUser);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		if(notes.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.status(Response.Status.OK).entity(notes).build();
	}
	
	@POST  @Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(UserDTO userDTO) {
		try {
			userManager.insert(new User(userDTO.getMail(), userDTO.getPassword()));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
	
	
}
