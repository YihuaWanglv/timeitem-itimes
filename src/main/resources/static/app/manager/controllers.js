(function(angular) {
  var CategoryController = function($scope, Category) {
    Category.query(function(response) {
      $scope.categorys = response ? response : [];
    });
    $scope.addCategory = function(categoryName) {
      new Category({
        categoryName: categoryName
      }).$save(function(category) {
        $scope.categorys.push(category);
      });
      $scope.newCategory = "";
    };
    $scope.updateCategory = function(category) {
      category.$update();
    };
    $scope.deleteCategory = function(category) {
      category.$remove(function() {
        $scope.categorys.splice($scope.categorys.indexOf(category), 1);
      });
    };
    $scope.edit = true;
    $scope.changeButtonFlag = function() {
      $scope.edit = (!$scope.edit);
    }
  };

  var ProjectController = function($scope, Project) {
    Project.query(function(response){
      $scope.projects = response ? response : [];
    });
    $scope.createProject = function(projectName) {
      new Project({
        projectName: projectName
      }).$save(function(project){
        $scope.projects.push(project);
      });
      $scope.newProject = "";
    }
    $scope.updateProject = function(project) {
      project.$update();
    }
    $scope.deleteProject = function(project) {
      project.$remove(function(){
        $scope.projects.splice($scope.projects.indexOf(project),1);
      });
    }
    $scope.changeButtonFlag = function(project) {
      project.editting = !project.editting;
    }
  }

  var LocationController = function($scope, Location) {
    Location.query(function(response) {
      $scope.locations = response ? response : [];
    });
    $scope.createLocation = function(locationName) {
      new Location({
        location: locationName
      }).$save(function(location) {
        $scope.locations.push(location);
      });
      $scope.newLocation = "";
    }
    $scope.updateLocation = function(location) {
      location.$update();
    }
    $scope.deleteLocation = function(location) {
      location.$remove(function(){
        $scope.locations.splice($scope.locations.indexOf(location),1);
      });
    }
    $scope.changeButtonFlag = function(location) {
      location.editting = !location.editting;
    }
  }

  var TagController = function($scope, Tag) {
    Tag.query(function(response) {
      $scope.tags = response ? response : [];
    });
    $scope.addTag = function(tagName) {
      new Tag({
        tagName: tagName
      }).$save(function(tag) {
        $scope.tags.push(tag);
      });
      $scope.newTag = "";
    }
    $scope.updateTag = function(tag) {
      tag.$update();
    }
    $scope.deleteTag = function(tag) {
      tag.$remove(function(){
        $scope.tags.splice($scope.tags.indexOf(tag),1);
      });
    }
    $scope.changeButtonFlag = function(tag) {
      tag.editting = !tag.editting;
    }
  }
  
  CategoryController.$inject = ['$scope', 'Category'];
  ProjectController.$inject = ['$scope','Project'];
  LocationController.$inject = ['$scope','Location'];
  TagController.$inject = ['$scope','Tag'];

  angular.module("manager.controllers").controller("CategoryController", CategoryController);
  angular.module("manager.controllers").controller("ProjectController", ProjectController);
  angular.module("manager.controllers").controller("LocationController", LocationController);
  angular.module("manager.controllers").controller("TagController", TagController);
}(angular));