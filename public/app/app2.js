var app = angular.module('myapp',[]);
  app.controller('UserController', function UserController($scope) {
    $scope.users = [
      {
        name: 'Javier Espinoza',
        email: 'jespinoza73gmail.com'
      },
      {
        name: 'Gisselle Cornejo',
        email: 'gisselle.cornejogmail.com'
      }
    ];
  });