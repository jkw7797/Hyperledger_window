$(document).ready(function(){
	$('#getAssetBtn').click(function(){
		const id=$('#id').val();
		$.post(   '../getAsset',
					{id},
					function(data){
						alert(data);
					}   
		);
	});
	
	
	$('#setAssetBtn').click(function(){
		const id=$('#id2').val();
		const amount=$('#amount').val();
		alert(id+":"+amount);
		$.post( '../setAsset', 
				{id, amount} , 
				function(data){
					alert(data);
				} 
		);
	});
	
	$('#sendAssetBtn').click(function(){
		const from_id=$('#from_id').val();
		const to_id=$('#to_id').val();
		const amount=$('#amount2').val();
		alert(from_id+":"+to_id+":"+amount);
		$.post( '../sendAsset', 
				{from_id,to_id, amount} , 
				function(data){
					alert(data);
				} 
		);
	});
	
	$('#getHistoryBtn').click(function(){
		const history_id=$('#history_id').val();		
		
		$.post( '../getHistory', 
				{history_id} , 
				function(data){
					$('textarea').val(data);
				} 
		);
	});
	
});




