<template>
	<div class="diy_edit page_order_center" id="order_center_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','order_number') || $check_field('add','order_number') || $check_field('get','order_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>订单编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_order_number" v-model="form['order_number']" placeholder="请输入订单编号" v-if="(form['order_number'] && $check_field('set','order_number')) || (!form['order_number'] && $check_field('add','order_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','order_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','commodity_number') || $check_field('add','commodity_number') || $check_field('get','commodity_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>商品编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_commodity_number" v-model="form['commodity_number']" placeholder="请输入商品编号" v-if="(form['commodity_number'] && $check_field('set','commodity_number')) || (!form['commodity_number'] && $check_field('add','commodity_number'))"  :disabled="disabledObj['commodity_number_isDisabled']"/>
							<span v-else-if="$check_field('get','commodity_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','trade_name') || $check_field('add','trade_name') || $check_field('get','trade_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>商品名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_trade_name" v-model="form['trade_name']" placeholder="请输入商品名称" v-if="(form['trade_name'] && $check_field('set','trade_name')) || (!form['trade_name'] && $check_field('add','trade_name'))"  :disabled="disabledObj['trade_name_isDisabled']"/>
							<span v-else-if="$check_field('get','trade_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','commodity_price') || $check_field('add','commodity_price') || $check_field('get','commodity_price')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>商品价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_commodity_price" v-model="form['commodity_price']" placeholder="请输入商品价格" v-if="(form['commodity_price'] && $check_field('set','commodity_price')) || (!form['commodity_price'] && $check_field('add','commodity_price'))"  :disabled="disabledObj['commodity_price_isDisabled']"/>
							<span v-else-if="$check_field('get','commodity_price')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','membership_level') || $check_field('add','membership_level') || $check_field('get','membership_level')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>会员等级:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_membership_level" v-model="form['membership_level']" placeholder="请输入会员等级" v-if="(form['membership_level'] && $check_field('set','membership_level')) || (!form['membership_level'] && $check_field('add','membership_level'))"  :disabled="disabledObj['membership_level_isDisabled']"/>
							<span v-else-if="$check_field('get','membership_level')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','member_discount') || $check_field('add','member_discount') || $check_field('get','member_discount')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>会员折扣:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_member_discount" v-model="form['member_discount']" placeholder="请输入会员折扣" v-if="(form['member_discount'] && $check_field('set','member_discount')) || (!form['member_discount'] && $check_field('add','member_discount'))"  :disabled="disabledObj['member_discount_isDisabled']"/>
							<span v-else-if="$check_field('get','member_discount')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('get','purchase_quantity')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>购买数量:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_purchase_quantity" v-model.number="form['purchase_quantity']" placeholder="请输入购买数量" v-if="(form['purchase_quantity'] && $check_field('set','purchase_quantity')) || (!form['purchase_quantity'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']" />
							<span v-else-if="$check_field('get','purchase_quantity')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','total_price') || $check_field('add','total_price') || $check_field('get','total_price')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>总价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_total_price" v-model="form['total_price']" placeholder="请输入总价格" v-if="(form['total_price'] && $check_field('set','total_price')) || (!form['total_price'] && $check_field('add','total_price'))"  @focus="set_total_price()" :disabled="disabledObj['total_price_isDisabled']"/>
							<span v-else-if="$check_field('get','total_price')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','user_account') || $check_field('add','user_account') || $check_field('get','user_account')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户账号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_account" :disabled="disabledObj['user_account_isDisabled']" v-model="form['user_account']" v-if="(form['user_account'] && $check_field('set','user_account')) || (!form['user_account'] && $check_field('add','user_account'))" >
								<option v-for="o in list_user_user_account" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_account')">{{ form['user_account'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','order_date_') || $check_field('add','order_date_') || $check_field('get','order_date_')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>下单日期:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['order_date__isDisabled']" id="form_order_date_" v-model="form['order_date_']" placeholder="请输入下单日期" v-if="(form['order_date_'] && $check_field('set','order_date_')) || (!form['order_date_'] && $check_field('add','order_date_'))" />
							<span v-else-if="$check_field('get','order_date_')">{{ form[''] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/order_center/get_obj?",
				url_add: "~/api/order_center/add?",
				url_set: "~/api/order_center/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"order_number": "",
					"commodity_number": "",
					"trade_name": "",
					"commodity_price": "",
					"membership_level": "",
					"member_discount": "",
					"purchase_quantity": 0,
					"total_price": "",
					"user_account": 0,
					"order_date_": "",
					"order_center_id": 0,
				},

				obj: {
					"order_number":this.$get_stamp(), // 订单编号
					"commodity_number":'', // 商品编号
					"trade_name":'', // 商品名称
					"commodity_price":'', // 商品价格
					"membership_level":'', // 会员等级
					"member_discount":'', // 会员折扣
					"purchase_quantity":0, // 购买数量
					"total_price":'', // 总价格
					"user_account": 0, // 用户账号
					"order_date_": "1970-01-01 00:00:00",
					"order_center_id": 0,
				},

				// 表单字段
				form: {
					"order_number":this.$get_stamp(), // 订单编号
					"commodity_number":'', // 商品编号
					"trade_name":'', // 商品名称
					"commodity_price":'', // 商品价格
					"membership_level":'', // 会员等级
					"member_discount":'', // 会员折扣
					"purchase_quantity":0, // 购买数量
					"total_price":'', // 总价格
					"user_account": 0, // 用户账号
					"order_date_": "1970-01-01 00:00:00",
					"order_center_id": 0,

				},
				disabledObj:{
					"order_number_isDisabled": false,
					"commodity_number_isDisabled": false,
					"trade_name_isDisabled": false,
					"commodity_price_isDisabled": false,
					"membership_level_isDisabled": false,
					"member_discount_isDisabled": false,
					"total_price_isDisabled": false,
					"user_account_isDisabled": false,
					"order_date__isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],

				// ID字段
				field: "order_center_id",
			}
		},
		methods: {
			set_total_price(){
				this.form.total_price =this.form.commodity_price * this.form.member_discount * this.form.purchase_quantity
			},
			/**
			 * 获取用户注册用户列表
			 */
			async get_list_user_user_account() {
				// if(this.user_group !== "管理员" && this.form["user_account"] === 0) {
				//     this.form["user_account"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=用户注册");
				if(json.result && json.result.list){
					this.list_user_user_account = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user_account(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=用户注册");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_account"] = user_id
								_this.disabledObj['user_account' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user_account") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/order_center/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["order_date_"] = this.$toTime(parseInt(this.form["order_date_"]),"yyyy-MM-dd")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user_account();
			this.get_list_user_user_account();
		}
	}
</script>

<style>
</style>
