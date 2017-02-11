// Based on https://github.com/cschneider/Karaf-Tutorial/tree/master/tasklist-blueprint-cdi/angular-ui
var app = angular.module('taskApp', []);
var tasksBase = "/rest/tasks";
app.controller('TaskController', function ($scope, $http) {
    $scope.reload = function () {
        $http.get(tasksBase)
            .success(function (response) {
                $scope.tasks = response.tasks;
            });
    };
    $scope.reload();
});

