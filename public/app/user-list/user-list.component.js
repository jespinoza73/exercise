'use strict';
// Register `userList` component, along with its associated controller and template
angular.
  module('userList').
  component('userList', {

    templateUrl: 'assets/app/user-list/user-list.template.html',
    
    controller: [ '$http', 
        function UserController($http) {
          var self = this;
          self.selectedUserId = null;
          
          self.select = function select(userId){
            self.selectedUserId = userId;
          };

          self.loadModel = function loadModel(){
            $http.get('users').then(
              function(response) {
              self.users = response.data;
              },
              function (error){
                 alert("Fatal Error. Please contact Administrator");
              }
            );
          };
          
          self.remove = function remove(){

            var r = confirm("Are you sure you want delete user?");

            if (r == true) {
              $http.delete('users/'+this.selectedUserId).then(
                function(response) {
                  var data = response.data;
                  if (data.result == "ok"){
                    self.loadModel();  
                  }
                  else{
                    alert("Error Deleting User");
                  }
                },
                function (error){
                   alert("Fatal Error. Please contact Administrator");
                }
              );
            }
            
          };

          self.loadModel();
        }
      ]

  });