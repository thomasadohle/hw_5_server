//Transitioning between local development and Heroku deployment, for some reason this.baseUrl is not interpreted inside the getMostRecentUser function.
// Had to hard cord the URL for that method

function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.getMostRecentUser = getMostRecentUser;
    this.search = search;
    this.baseUrl = 'https://salty-falls-99802.herokuapp.com/';
    var self = this;
    
    //Makes API call to generate Java object when a new user is created
    function createUser(username, password, firstName, lastName, role) { 
    	var url = this.baseUrl + "/createUser/" + username +"/" + password + "/" + firstName + "/" + lastName + "/" + role;
        return $.post(url).then(function(){
        	getMostRecentUser();
        });
    }
    
    //Returns all users stored on server
    function findAllUsers() {
    	var url = this.baseUrl + "/api/user";
        return fetch(url)
            .then(function(response) {
                return response.json();
            });
    }
    
    //Returns a specific user based on UserID
    function findUserById(userId) {
    	var url = this.baseUrl + "/api/user/" + userId;
    	return fetch(url)
    		.then(function(response){
    			var jsonResponse = response.json();
    			console.log(jsonResponse);
    			return jsonResponse;
    	});
    }
    
    //Returns the user most recently added to server
    function getMostRecentUser(){
    	var url = "https://salty-falls-99802.herokuapp.com/api/recent/";
    	var urlAgain = this.baseUrl + "/api/recent/";
    	console.log(this.baseUrl);
    	console.log(url);
    	return fetch(url)
		.then(function(response){
			var jsonResponse = response.json();
			return jsonResponse;
    });
    }
    
    //Makes API call update Java User object
    function updateUser(user) {
    	var id = user.id;
    	var username = user.username;
    	var password = user.password;
    	var firstName = user.firstName;
    	var lastName = user.lastName;
    	var role = user.role;
    	var url = this.baseUrl + "/updateUser/"+username+"/"+password+"/"+firstName+"/"+lastName+"/"+id + "/" + role;
    	$.post(url);
    }
    
    //Makes API call to delete Java object
    function deleteUser(userId) { 
    	var url = this.baseUrl + "/deleteUser/" + userId;
    	$.post(url);
    }
   
    //Makes API call to return Java User objects based on parameters.
    //I could not figure out how to make the API work when arguments were not provided in the form
    //So if nothing is passed, the parameter is set to banana_Pancakes. The server knows to ignore these arguments.
    // https://i.kym-cdn.com/entries/icons/original/000/028/021/work.jpg
    function search(username,  password, firstName, lastName, role){
    	if (username == ""){username="banana_Pancakes";}
    	if (password == ""){password="banana_Pancakes";}
    	if (firstName == ""){firstName="banana_Pancakes";}
    	if (lastName == ""){lastName="banana_Pancakes";}
    	var url = this.baseUrl + "/api/search/"+username +"/"+password + "/" + firstName + "/" +lastName+"/" + role;
    	return fetch(url)
		.then(function(response){
			var jsonResponse = response.json();
			console.log("response from search:" + jsonResponse);
			return jsonResponse;
    }); 
   
   
    
}
}
