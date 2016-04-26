
				var jsonp = function(url, callback) {
					if (typeof url === 'undefined') {
						throw 'the 1st param "url" missing';
					}
					if (typeof callback === 'undefined') {
						throw 'the 2nd param "callback" missing';
					}
					var jsonpcallback = 'callback' + new Date().valueOf();
					if (typeof callback !== 'string') {
						window[jsonpcallback] = callback;
						callback = jsonpcallback;
					} else {
						window[jsonpcallback] = function(data) {
							eval(callback).call(window, data);
						}
					}
					var script = document.createElement('script');
					script.setAttribute('type', 'text/javascript');
					script.setAttribute('src', url + (url.indexOf('?') == -1 ? '?' : '&') + 'callback=' + jsonpcallback);
					var head = document.getElementsByTagName('head')[0];
					head.appendChild(script);
				};
			window.onload = function() {
				jsonp('https://api.douban.com/v2/book/isbn/9787115299710 ', function (data){
					console.log(data);
				})
			};
