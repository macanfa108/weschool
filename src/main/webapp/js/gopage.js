//详细信息
mui(".mui-table-view").on('tap','.mui-table-view-cell',function(){
	console.log(0);
  //获取id
  var id = this.getAttribute("data-url");
  //传值给详情页面，通知加载新数据
  mui.fire(detail,'getDetail',{id:id});
  //打开新闻详情
  mui.openWindow({
    id:'index2',
    url:'index2.html'
  });
}) 
//info
	/*var detailPage = null;
	mui(".mui-table-view").on('tap', '.mui-table-view-cell', function() {
		console.log(0);
		//获取id
		var id = this.getAttribute("data-url");
		console.log(id);
		//传值给详情页面，通知加载新数据
		mui.fire(detailPage, 'newsId', {
			id: id
		});
		//打开新闻详情
		mui.openWindow({
			id: 'index2',
			url: 'index2.html'
		});
	})*/
//mui.init({
//	preloadPages: [{
//		id: 'index2.html',
//		url: 'index2.html'
//	}]
//});

//添加列表项的点击事件
console.log(1);
////mui("#main-table-view").on('tap','li', function(e) {
//	mui(".mui-table-view").on('tap','.mui-table-view-cell',function(e){
//	console.log(0);
//	var Url = this.getAttribute('data-url');
//	var param = {userId:3,toKen:'toKen',userRole:'userRole'};
//function SendPage(pageId,linkUrl,pageParam)
//{
//mui.openWindow({
//url:'index2.html',
//  id:'index2.html',
//extras:param
//});
//}
//	
//});
console.log(2);