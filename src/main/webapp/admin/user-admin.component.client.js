(function(){
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);
    console.log("abc");
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
    }
    function createUser() {  }
    function findAllUsers() {  }
    function findUserById() {  }
    function deleteUser() {  }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) {  }
    function renderUsers(users) {
        for(var u=0; u<users.length; u++) {
            console.log(users[u]);
            var clone = $userRowTemplate.clone();
            clone.find(".username").html(users[u].firstName);
            clone.find(".password").html(users[u].password);
            clone.find(".firstName").html(users[u].firstName);
            clone.find(".lastName").html(users[u].lastName);
            $tbody.append(clone);
        }
    }
})();

$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip(); 
	});