<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="订单编号" prop="order_number">
					<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','order_number')) || (!form['order_center_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','commodity_number') || $check_field('add','commodity_number') || $check_field('set','commodity_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品编号" prop="commodity_number">
					<el-input id="commodity_number" v-model="form['commodity_number']" placeholder="请输入商品编号"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','commodity_number')) || (!form['order_center_id'] && $check_field('add','commodity_number'))" :disabled="disabledObj['commodity_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','commodity_number')">{{form['commodity_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','trade_name') || $check_field('add','trade_name') || $check_field('set','trade_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品名称" prop="trade_name">
					<el-input id="trade_name" v-model="form['trade_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','trade_name')) || (!form['order_center_id'] && $check_field('add','trade_name'))" :disabled="disabledObj['trade_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','trade_name')">{{form['trade_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','commodity_price') || $check_field('add','commodity_price') || $check_field('set','commodity_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品价格" prop="commodity_price">
					<el-input id="commodity_price" v-model="form['commodity_price']" placeholder="请输入商品价格"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','commodity_price')) || (!form['order_center_id'] && $check_field('add','commodity_price'))" :disabled="disabledObj['commodity_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','commodity_price')">{{form['commodity_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','membership_level') || $check_field('add','membership_level') || $check_field('set','membership_level')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员等级" prop="membership_level">
					<el-input id="membership_level" v-model="form['membership_level']" placeholder="请输入会员等级"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','membership_level')) || (!form['order_center_id'] && $check_field('add','membership_level'))" :disabled="disabledObj['membership_level_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_level')">{{form['membership_level']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','member_discount') || $check_field('add','member_discount') || $check_field('set','member_discount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员折扣" prop="member_discount">
					<el-input id="member_discount" v-model="form['member_discount']" placeholder="请输入会员折扣"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','member_discount')) || (!form['order_center_id'] && $check_field('add','member_discount'))" :disabled="disabledObj['member_discount_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','member_discount')">{{form['member_discount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="购买数量" prop="purchase_quantity">
					<el-input-number id="purchase_quantity" v-model.number="form['purchase_quantity']"
						v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','purchase_quantity')) || (!form['order_center_id'] && $check_field('add','purchase_quantity'))"></el-input-number>
					<div v-else-if="$check_field('get','purchase_quantity')">{{form['purchase_quantity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total_price') || $check_field('add','total_price') || $check_field('set','total_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="总价格" prop="total_price">
					<el-input id="total_price" v-model="form['total_price']" placeholder="请输入总价格"
							  v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','total_price')) || (!form['order_center_id'] && $check_field('add','total_price'))"  @focus="set_total_price()" :disabled="disabledObj['total_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_price')">{{form['total_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_account') || $check_field('add','user_account') || $check_field('set','user_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户账号" prop="user_account">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_account(form['user_account']) }}
							<!--<el-input id="business_name" v-model="form['user_account']" placeholder="请输入用户账号"-->
							<!--v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','user_account')) || (!form['order_center_id'] && $check_field('add','user_account'))" :disabled="disabledObj['user_account_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_account')">{{form['user_account']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','user_account')) || (!form['order_center_id'] && $check_field('add','user_account'))" id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
								<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user_account')" id="user_account" v-model="form['user_account']" :disabled="true">
								<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
							<el-option v-for="o in list_user_user_account" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','order_date_') || $check_field('add','order_date_') || $check_field('set','order_date_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="下单日期" prop="order_date_">
					<el-date-picker :disabled="disabledObj['order_date__isDisabled']" v-if="user_group === '管理员' || (form['order_center_id'] && $check_field('set','order_date_')) || (!form['order_center_id'] && $check_field('add','order_date_'))" id="order_date_"
						v-model="form['order_date_']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','order_date_')">{{form['order_date_']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "order_center_id",
				url_add: "~/api/order_center/add?",
				url_set: "~/api/order_center/set?",
				url_get_obj: "~/api/order_center/get_obj?",
				url_upload: "~/api/order_center/upload?",

				query: {
					"order_center_id": 0,
				},

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
					"order_date_":'', // 下单日期
					"order_center_id": 0, // ID

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
				// 用户组
				group_user_user_account: "",

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
			/**
			 * 获取用户注册用户组
			 */
			async get_group_user_user_account() {
				this.form["user_account"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=用户注册");
				if(json.result && json.result.obj){
					this.group_user_user_account = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user_account(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user_account.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="user_account") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_user_account(id){
				var obj = this.list_user_user_account.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "用户账号":
							if(param["user_account"] > 0){
								param["user_account"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["order_date_"] = this.$toTime(parseInt(this.form["order_date_"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["order_date_"]) > 9999){
					this.form["order_date_"] = this.$toTime(parseInt(this.form["order_date_"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/order_center/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/order_center/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_user_account();
			this.get_group_user_user_account();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
