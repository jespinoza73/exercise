angular.
  module('userDetail').
  component('userDetail', {
    templateUrl: 'assets/app/user-detail/user-detail.template.html',
    controller: ['$routeParams', '$http',
      function UserDetailController($routeParams, $http) {
        
        var self = this;
        self.submit = function submit(){
        	$http.post('users',self.user).then(
	        	function(response) {
		            self.saveOkay = response.data.result == "ok";
		            //We can use saveOkay to show a nice msg to user.
		            //for later.
		            if (self.saveOkay){
		            	alert("User Saved Successfully!!!");
		        	}
		        	else{
		        		alert("Error saving user"+ response.data.msg);
		        	}
		         },
                function (error){
                   alert("Fatal Error. Please contact Administrator");
                }
	         );
        };

        this.userId = $routeParams.userId;
        if (this.userId == "new"){
        	this.user = {_id:null, name:"",emailAddress:""};
        }
        else{

        	$http.get('users/'+this.userId).then(
	        	 function(response) {
		            self.user = response.data;
		         },
                function (error){
                   alert("Fatal Error. Please contact Administrator");
                }
	         );
        	//this.user = {_id:this.userId,name:this.userId,email:this.userId};
        }

      }
    ]
  });