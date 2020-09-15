'use strict';

angular.module("app")

.factory('catalog', ['$http', '$q', 'COOLSTORE_CONFIG', 'Auth', '$location', function($http, $q, COOLSTORE_CONFIG, $auth, $location) {
	var factory = {}, products, baseUrl;

    baseUrl = $location.protocol() + '://catalog-springboot-' + COOLSTORE_CONFIG.OCP_NAMESPACE + '.' + $location.host().replace(/^.*?\.(.*)/g,"$1") + '/services/products';

    //baseUrl = 'http://catalog-springboot-runtimes-demo.apps.cluster-mel-c023.mel-c023.sandbox900.opentlc.com/services/products'

    factory.getProducts = function() {
		var deferred = $q.defer();
        if (products) {
            deferred.resolve(products);
        } else {
            $http({
                method: 'GET',
                url: baseUrl
            }).then(function(resp) {
                products = resp.data;
                deferred.resolve(resp.data);
            }, function(err) {
                deferred.reject(err);
            });
        }
	   return deferred.promise;
	};

	return factory;
}]);
