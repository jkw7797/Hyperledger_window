$(document).ready(function() {
	$('#queryAllCar').click(function() {
		$.post('../queryAllCar',
			{},
			function(data) {
				console.log(data);
				data = JSON.parse(data);
				info = data.info;

				let tableTag = '<table id="all_car" class="table" align="center">';
				tableTag += '<tr><th>ID</th><th>Color</th><th>Make</th><th>Model</th><th>Owner</th></tr>';

				info.forEach(function(car, index) {
					console.log(car);
					tableTag += '<tr><td>' + index + '</td><td>' + car.color + '</td><td>' + car.make + '</td><td>' + car.model + '</td><td>' + car.owner + '</td></tr>';
				});
				tableTag += '</table>';
				$('#all_car').html(tableTag);
			}
		);
	});
	$('#querySubmit').click(function(){
		let carNumber = $('#queryName').val()
		alert(carNumber);
		$.post('/querySubmit',
				{carNumber},
				function(data){
					alert(data);
					console.log(data);
					data=JSON.parse(data); 
					info = data.info;
					console.log("------------------------pass---------------------");
					console.log(info);
					let tableTag = `<td>${info.color}</td><td>${info.make}</td><td>${info.model}</td><td>${info.owner}</td>`;

					$('#query_car').html(tableTag);
				}
		);
	});

});