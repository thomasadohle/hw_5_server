(function(){
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    
    console.log(userService.findUserById(123).username);
    $(main);
    
    function main() {
    	
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#rowFld");
        $userRowTemplate = $(".wbdv-template");
        $tbody = $("tbody");
        $usernameFld.val("alice");
        userService
            .findAllUsers()
            .then(renderUsers);
        $("#wbdv-row-template").hide();
        //Event handlers
  	  $(document).on("click", ".wbdv-delete-button", function() {
  		  console.log("clicked");
  		  var rowId = getUserIdFromDeleteButton($(this));
  		  deleteUser(rowId);
  		  });
  	  $(document).on("click", "#createBtn", function(){
  		 var username = $("#usernameFld").val();
  		 var password = $("#passwordFld").val();
  		 var firstName = $("#firstNameFld").val();
  		 var lastName = $("#lastNameFld").val();
  		 var newUser = createUser(username, password, firstName, lastName);
  		 console.log("new users username is: " + username);
  	  });

        
       
    }
    function getUserIdFromDeleteButton(deleteButton){
    	var rowId = deleteButton.closest('tr').attr('id');
    	console.log("ID for row is: " + rowId);
    	return rowId;
    }
    function createUser(username, password, firstName, lastName) {
    	var newUser = new User (username, password, firstName, lastName);
    	userService.createUser(username, password, firstName, lastName);
    	console.log("called userService.createUser")
    	renderUser(newUser);
    }
    function findAllUsers() {
    	userService.findAllUsers()
    }
    function findUserById(userId) {  
    	userService.findUserById(userId);
    }
    function deleteUser(userId) {
    	var deleteText = '#'.concat(userId)
    	console.log(deleteText)
    	var userToDelete = $(deleteText);
    	console.log(userToDelete);
    	userToDelete.remove();
    }
    function selectUser() {  }
    function updateUser(user, username, password, firstName, lastName) {
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    }
    
    function renderUser(user) { 
    	console.log(user);
        var clone = $userRowTemplate.clone();
        clone.find(".username").html(user.firstName);
        clone.find(".password").html(user.password);
        clone.find(".firstName").html(user.firstName);
        clone.find(".lastName").html(user.lastName);
        clone.attr("id",user.id);
        console.log(clone.id);
        $tbody.append(clone);
    }
    
    
    function renderUsers(users) {
        for(var u=0; u<users.length; u++) {
            console.log(users[u]);
            var clone = $userRowTemplate.clone();
            clone.find(".username").html(users[u].firstName);
            clone.find(".password").html(users[u].password);
            clone.find(".firstName").html(users[u].firstName);
            clone.find(".lastName").html(users[u].lastName);
            clone.attr("id",users[u].id);
            console.log(clone.id);
            $tbody.append(clone);
        }
    }
})();

$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip(); 
	});