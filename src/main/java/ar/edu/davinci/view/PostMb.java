package ar.edu.davinci.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ar.edu.davinci.auth.AuthMb;
import ar.edu.davinci.controller.PostController;
import ar.edu.davinci.model.Post;

@Named
public class PostMb {
	
	@Inject
	private AuthMb authMb;
	
	@Inject 
	private PostController postCntl;
	
	private String content;
	
	public void submitPost(){
		try{
			postCntl.addPost(authMb.getUser(), content);
			content = null;
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public List<Post> getPostList(){
		try{
			return postCntl.all(0, 10);
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}
	
	public List<Post> getMyPostList(){
		try{
			return postCntl.from(authMb.getUser(), 0, 10);
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
