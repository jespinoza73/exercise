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

          $http.get('users').then(function(response) {
            self.users = response.data;
          });
        }
      ]

     /* this.users = [
        {
          id:"1",
          name: 'Javier Espinoza',
          email: 'jespinoza73@gmail.com'
        },
        {
          id:"2",
          name: 'Gisselle Cornejo',
          email: 'gisselle.cornejo@gmail.com'
        }
      ];*/

  });