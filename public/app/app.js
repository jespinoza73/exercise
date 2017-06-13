
var app=angular.module('myapp',[]);


app.controller("UserController", function UserController($scope) {

  $scope.selectedUserId = null;
  $scope.select = function select(userId){
    $scope.selectedUserId = userId;
  };

  $scope.users = [
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
  ];
});