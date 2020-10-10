package com.example.socketdemo;

public class AuthToken {

    private String token;
     
    private User user;
    
     public AuthToken(){

    }
    
    
    public AuthToken(String token){
    	 this.token = token;
    }
    
    public AuthToken(String token,User  user){
        this.token = token;
        this.user= user;
        
        
    }

    
    
    
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * @return the userAggregatorDTO
	 */
	/*public UserAggregatorDTO getUserAggregatorDTO() {
		return userAggregatorDTO;
	}*/



	/**
	 * @param userAggregatorDTO the userAggregatorDTO to set
	 */
	/*public void setUserAggregatorDTO(UserAggregatorDTO userAggregatorDTO) {
		this.userAggregatorDTO = userAggregatorDTO;
	}*/



	

}
