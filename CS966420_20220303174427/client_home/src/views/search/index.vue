<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="宠物资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/cat_products/list', 'get')"
				:list="result_cat_products_trade_name"
				title="猫猫用品商品名称"
				source_table="cat_products"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/user_registration/list', 'get')"
				:list="result_user_registration_user_account"
				title="用户注册用户账号"
				source_table="user_registration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/order_center/list', 'get')"
				:list="result_order_center_trade_name"
				title="订单中心商品名称"
				source_table="order_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/membership_center/list', 'get')"
				:list="result_membership_center_member_nickname"
				title="会员中心会员昵称"
				source_table="membership_center"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/service_centre/list', 'get')"
				:list="result_service_centre_service_type"
				title="服务中心服务类型"
				source_table="service_centre"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/booking_management/list', 'get')"
				:list="result_booking_management_service_type"
				title="预约管理服务类型"
				source_table="booking_management"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_cat_products_trade_name":[],
			"result_user_registration_user_account":[],
			"result_order_center_trade_name":[],
			"result_membership_center_member_nickname":[],
			"result_service_centre_service_type":[],
			"result_booking_management_service_type":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取trade_name
	 */
	get_cat_products_trade_name(){
		this.$get("~/api/cat_products/get_list?like=0", { page: 1, size: 10, "trade_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_cat_products_trade_name = json.result.list;
			result_cat_products_trade_name.map(o => o.title = o['trade_name'])
	  			this.result_cat_products_trade_name = result_cat_products_trade_name
		 	}
		});
	},
	/**
	 * 获取user_account
	 */
	get_user_registration_user_account(){
		this.$get("~/api/user_registration/get_list?like=0", { page: 1, size: 10, "user_account": this.query.word }, (json) => {
		  if (json.result) {
			var result_user_registration_user_account = json.result.list;
			result_user_registration_user_account.map(o => o.title = o['user_account'])
	  			this.result_user_registration_user_account = result_user_registration_user_account
		 	}
		});
	},
	/**
	 * 获取trade_name
	 */
	get_order_center_trade_name(){
		this.$get("~/api/order_center/get_list?like=0", { page: 1, size: 10, "trade_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_order_center_trade_name = json.result.list;
			result_order_center_trade_name.map(o => o.title = o['trade_name'])
	  			this.result_order_center_trade_name = result_order_center_trade_name
		 	}
		});
	},
	/**
	 * 获取member_nickname
	 */
	get_membership_center_member_nickname(){
		this.$get("~/api/membership_center/get_list?like=0", { page: 1, size: 10, "member_nickname": this.query.word }, (json) => {
		  if (json.result) {
			var result_membership_center_member_nickname = json.result.list;
			result_membership_center_member_nickname.map(o => o.title = o['member_nickname'])
	  			this.result_membership_center_member_nickname = result_membership_center_member_nickname
		 	}
		});
	},
	/**
	 * 获取service_type
	 */
	get_service_centre_service_type(){
		this.$get("~/api/service_centre/get_list?like=0", { page: 1, size: 10, "service_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_service_centre_service_type = json.result.list;
			result_service_centre_service_type.map(o => o.title = o['service_type'])
	  			this.result_service_centre_service_type = result_service_centre_service_type
		 	}
		});
	},
	/**
	 * 获取service_type
	 */
	get_booking_management_service_type(){
		this.$get("~/api/booking_management/get_list?like=0", { page: 1, size: 10, "service_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_booking_management_service_type = json.result.list;
			result_booking_management_service_type.map(o => o.title = o['service_type'])
	  			this.result_booking_management_service_type = result_booking_management_service_type
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_cat_products_trade_name();
		this.get_user_registration_user_account();
		this.get_order_center_trade_name();
		this.get_membership_center_member_nickname();
		this.get_service_centre_service_type();
		this.get_booking_management_service_type();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_cat_products_trade_name();
	  this.get_user_registration_user_account();
	  this.get_order_center_trade_name();
	  this.get_membership_center_member_nickname();
	  this.get_service_centre_service_type();
	  this.get_booking_management_service_type();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
