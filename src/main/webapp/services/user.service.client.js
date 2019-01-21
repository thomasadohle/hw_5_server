function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
    var self = this;
    function createUser(username, password, firstName, lastName) { 
    	url = "http://localhost:8080/createUser/" + username +"/" + password + "/" + firstName + "/" + lastName;
    	console.log(url)
    	$.post(url);
    }
    function findAllUsers() {
        return fetch(this.url)
            .then(function(response) {
                return response.json();
            });
    }
    function findUserById(userId) { 
    	return fetch("http://localhost:8080/api/user/" + userId)
    		.then(function(response){
    			var jsonResponse = response.json();
    			console.log(jsonResponse);
    			return jsonResponse;
    	});
    }
    function updateUser(userId, user, callback) {
    	console.log("updateUser");
    }
    function deleteUser(userId) { 
    	url = "http://localhost:8080/deleteUser/" + userId;
    	console.log(url);
    	$.post(url);
    }
}