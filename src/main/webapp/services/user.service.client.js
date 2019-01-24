function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.getMostRecentUser = getMostRecentUser;
    this.search = search;
    this.url = 'http://localhost:8080/api/user';
    var self = this;
    function createUser(username, password, firstName, lastName, role) { 
    	url = "http://localhost:8080/createUser/" + username +"/" + password + "/" + firstName + "/" + lastName + "/" + role;
        return $.post(url).then(function(){
        	getMostRecentUser();
        });
    }
    function findAllUsers() {
        return fetch('http://localhost:8080/api/user')
            .then(function(response) {
                return response.json();
            });
    }
    function findUserById(userId) { 
    	console.log("The id sent to user.service.client.findUserById is: " + userId);
    	return fetch("http://localhost:8080/api/user/" + userId)
    		.then(function(response){
    			var jsonResponse = response.json();
    			console.log(jsonResponse);
    			return jsonResponse;
    	});
    }
    function getMostRecentUser(){
    	return fetch("http://localhost:8080/api/recent/")
		.then(function(response){
			var jsonResponse = response.json();
			console.log("response from getMostRecentUser:" + jsonResponse);
			return jsonResponse;
    });
    }
    function updateUser(user) {
    	var id = user.id;
    	var username = user.username;
    	var password = user.password;
    	var firstName = user.firstName;
    	var lastName = user.lastName;
    	var role = user.role;
    	var url = "http://localhost:8080/updateUser/"+username+"/"+password+"/"+firstName+"/"+lastName+"/"+id + "/" + role;
    	console.log(url);
    	$.post(url);
    }
    function deleteUser(userId) { 
    	var url = "http://localhost:8080/deleteUser/" + userId;
    	console.log(url);
    	$.post(url);
    }
   
    
    function search(username,  password, firstName, lastName, role){
    	if (username == ""){username="banana_Pancakes";}
    	if (password == ""){password="banana_Pancakes";}
    	if (firstName == ""){firstName="banana_Pancakes";}
    	if (lastName == ""){lastName="banana_Pancakes";}
    	var url = "http://localhost:8080/api/search/"+username +"/"+password + "/" + firstName + "/" +lastName+"/" + role;
    	return fetch(url)
		.then(function(response){
			var jsonResponse = response.json();
			console.log("response from search:" + jsonResponse);
			return jsonResponse;
    }); 
   
    /*
    function search(username, password, firstName, lastName, role){
    	var users = findAllUsers();
    	console.log(users);
    	var searchResults = [];
    	for(var u=0; u<users.length; u++) {
    		if(users[u].username.contains(userName) && users[u].password().contains(password)
					&& users[u].firstName().contains(firstName) && users[u].lastName().contains(lastName) 
					&& users[u].role().contains(role)){
    			searchResults.push(users[u]);
    		}
		return searchResults;
    	}}; */
    
}
}
