var Lib_Random = (function() {
	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100);
		//return 0;
	};
	var randomColorFactor = function() {
		return Math.round(Math.random() * 255);
	};
	var randomColor = function(opacity) {
		return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ','
				+ randomColorFactor() + ',' + (opacity || '.3') + ')';
	};
	return {
		randomScalingFactor: randomScalingFactor,
		randomColorFactor: randomColorFactor,
		randomColor: randomColor
	}
})();