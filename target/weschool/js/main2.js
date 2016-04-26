(function($) {
	var count = 1; //页面加载时默认加载第一页数据
	var anywords = "test";
	var loadingBtn = $("#loadingBtn");
	loadJsonArray();
	function loadJsonArray(anywords, page) {
		//加载页数
		Page = page ? page : 1;
		//关键字
		Anywords = anywords ? anywords : "test";

		$.ajax({
			url: "http://weschool.jking.net/search?anywords=" + Anywords + "&page=" + Page + "&rows=5",
			dataType: 'jsonp',
			success: function(data) {

				//			 console.log(data);
				//			console.log(data.rows);
				var html = '';
				var parent = $("#main-table-view");
				$.each(data.rows, function(index, ele) {
					//				console.log(data.rows[index].name);
					html = $('<li class="mui-table-view-cell"><a class="mui-navigate-right">' + data.rows[index].name + '</a></li>');
					//				console.log(html);
					parent.append(html);
				})
			}
		});
	}
})(mui);