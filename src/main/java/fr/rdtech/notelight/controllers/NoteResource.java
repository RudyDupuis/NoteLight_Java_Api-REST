package fr.rdtech.notelight.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.rdtech.notelight.bll.NoteManager;
import fr.rdtech.notelight.bo.Note;
import fr.rdtech.notelight.bo.NoteDTO;

@Path("/notes")
public class NoteResource {
	private NoteManager noteManager = NoteManager.getInstance();
	
	@GET @Path("/{idNote: \\d+}")
	public Response select(@PathParam("idNote") int idNote){
		Note note;
		
		try {
			note = noteManager.select(idNote);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		if(note == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.status(Response.Status.OK).entity(note).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(NoteDTO noteDTO){
		try {
			noteManager.insert(new Note(noteDTO.getIdUser(), noteDTO.getMessage()));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(NoteDTO noteDTO){
		try {
			noteManager.update(new Note(noteDTO.getIdNote(), noteDTO.getIdUser(), noteDTO.getMessage()));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE @Path("/{idNote: \\d+}")
	public Response delete(@PathParam("idNote") int idNote){
		try {
			noteManager.delete(idNote);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
	
}
