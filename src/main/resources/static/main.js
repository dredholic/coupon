var issueCoupon = new Vue({
	el: '#issue-coupon',
	data: {
		email: ''
	},
	methods: {
		issueCoupon: function () {
			console.log("email: " + this.email);
			$.ajax({
				type: 'POST',
				url: '/api/coupons',
				contentType: 'application/json',
				data: this.email,
				success: function (result) {
					alert(result);
					updateCoupons(0);
				},
				error: function (result) {
					alert(result.responseJSON.message);
				}
			});
		}
	}
});

Vue.component('coupon-grid', {
	template: '#coupon-table-template',
	props: {
		content: Array,
		columns: Array,
		first: Boolean,
		last: Boolean,
		pagenumber: Number,
		totalpages: Number,
		pages: Array
	},
	methods: {
		toTargetPage: function (event) {
			// 메소드 안에서 사용하는 `this` 는 Vue 인스턴스를 가리킵니다
			var message = event.target.innerText;
			console.log(message);
			if (message === 'Previous') {
				var priviousPageNumber = pagenumber - 1;
				if (priviousPageNumber > -1) {
					updateCoupons(priviousPageNumber);
				}
			} else if (message === 'Next') {
				var nextPageNumber = pagenumber + 1;
				if (nextPageNumber < this.totalpages)
				updateCoupons(nextPageNumber);
			} else {
				updateCoupons(message - 1);
			}
		}
	}
});

var initCouponsData = getCouponsData(0);

var listCoupon = new Vue({
	el: '#coupon-list',
	data: {
		gridColumns: ['id', 'email', 'couponCode', 'issuedAt'],
		content: initCouponsData.content,
		first: initCouponsData.first,
		last: initCouponsData.last,
		pagenumber: initCouponsData.number,
		totalpages: initCouponsData.totalPages,
		pages: Array.apply(null, {length: initCouponsData.totalPages}).map(Number.call, Number)
	}
});

function getCouponsData(targetPage) {
	return $.ajax({
		type: 'GET',
		url: '/api/coupons?page=' + targetPage + '&size=10',
		contentType: 'application/json',
		async: false,
		success: function (data) {
			console.log(data.content);
		},
		fail: function (data) {
			console.log(data);
		}
	}).responseJSON;
}

var updateCoupons = function (targetPage) {
	var response = getCouponsData(targetPage);
	console.log("updateCoupons: " + response);
	listCoupon.content = response.content;
	listCoupon.first = response.first;
	listCoupon.last = response.last;
	listCoupon.pagenumber = response.number;
	listCoupon.totalpages = response.totalPages;
	listCoupon.pages = Array.apply(null, {length: response.totalPages}).map(Number.call, Number);
};