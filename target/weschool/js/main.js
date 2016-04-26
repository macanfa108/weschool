$(document).ready(function() {
	var count = 1; //页面加载时默认加载第一页数据
	var anywords = "test";
	var loadingBtn = $("#loadingBtn");
	var parent = $("#main-table-view"); //信息项容器
	//判断本地存储
	if (window.sessionStorage.getItem('oldContent')) {
		//有则取出缓存
		parent.html(window.sessionStorage.getItem('oldContent'));
		console.log("加载old");
	} else {
		//加载数据
		loadJsonArray();
		console.log("加载新数据");
	}
	console.log("程序异步执行ing");

	//加载更多
	loadingBtn.on("click", function() {
		//每次加载下一页数据
		loadJsonArray(anywords, ++count);
	});
	//检索图书
	$("#search").on('keyup', function() {
		//获取搜索框值
		anywords = $(this).val();
		//已用本地存储代替改功能：当搜索框为空值时，显示上一次的值
		oldParent = parent.html();
		console.log(oldParent);
		if (anywords === "") {
			parent.html(oldParent);
			return;
		}
		$.ajax({
			url: "http://weschool.jking.net/search?anywords=" + anywords + "&page=1&rows=5",
			dataType: 'jsonp',
			success: function(data) {
				if (data.rows.length > 0) {
					var html = '';
					parent.html("");
					$.each(data.rows, function(index, ele) {
						html += '<li class="mui-table-view-cell"><a class="mui-navigate-right" href="' + data.rows[index].url + '">' +
							'<h4 class="mui-ellipsis">' + data.rows[index].name + '</h4>' +
							'<h5 class="mui-ellipsis">作者：' + data.rows[index].descripe + '</h5>' +
							'<p class="mui-h6 mui-ellipsis">出版社：' + data.rows[index].publisher + '</p>' +
							'<span class="mui-badge mui-badge-primary">' + data.rows[index].surplus + '</span>' +
							'</a></li>';
					});
					parent.append(html);
					console.log("查询完");
					//实现本地存储
					SessionStorage();
					console.log("存储查询完");

				} else {
					var info = "没有找到你检索的内容！";
					console.log(info);
					parent.html('<li class="mui-table-view-cell" >' + info + '</li>');
					return;
				}

			}

		});
	});

});
//加载数据封装
function loadJsonArray(anywords, page) {
	//加载页数
	Page = page ? page : 1;
	//关键字
	Anywords = anywords ? anywords : "test";
	//正在加载按钮
	var loadingBtn= $("#loadingBtn");
	loadingBtn.html("正在加载...");
		
	$.ajax({
		url: "http://weschool.jking.net/search?anywords=" + Anywords + "&page=" + Page + "&rows=5",
		dataType: 'jsonp',
		success: function(data) {
			var html = '';
			var parent = $("#main-table-view");
			$.each(data.rows, function(index, ele) {
				html += '<li class="mui-table-view-cell"><a class="mui-navigate-right" href="' + data.rows[index].url + '">' +
					'<h4 class="mui-ellipsis">' + data.rows[index].name + '</h4>' +
					'<h5 class="mui-ellipsis">作者：' + data.rows[index].descripe + '</h5>' +
					'<p class="mui-h6 mui-ellipsis">出版社：' + data.rows[index].publisher + '</p>' +
					'<span class="mui-badge mui-badge-primary">' + data.rows[index].surplus + '</span>' +
					'</a></li>';
			});
			parent.append(html);
			console.log("加载2完");
			//实现本地存储
			SessionStorage();
			console.log("存储完");
			//恢复加载按钮内容
			loadingBtn.html("加载更多...")
		}
	});
};
//本地存储
function SessionStorage() {
	if (window.sessionStorage) {
		console.log("存储开始");
		var value = $("#main-table-view").html();
//		console.log(value);
		window.sessionStorage.setItem("oldContent", value);
	}
}