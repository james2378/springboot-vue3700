<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','service_type') || $check_field('add','service_type') || $check_field('set','service_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="服务类型" prop="service_type">
					<el-input id="service_type" v-model="form['service_type']" placeholder="请输入服务类型"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','service_type')) || (!form['booking_management__id'] && $check_field('add','service_type'))" :disabled="disabledObj['service_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','service_type')">{{form['service_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','shop_address') || $check_field('add','shop_address') || $check_field('set','shop_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label=" 店铺地址" prop="shop_address">
					<el-input id="shop_address" v-model="form['shop_address']" placeholder="请输入 店铺地址"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','shop_address')) || (!form['booking_management__id'] && $check_field('add','shop_address'))" :disabled="disabledObj['shop_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','shop_address')">{{form['shop_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contacts') || $check_field('add','contacts') || $check_field('set','contacts')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系人" prop="contacts">
					<el-input id="contacts" v-model="form['contacts']" placeholder="请输入联系人"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','contacts')) || (!form['booking_management__id'] && $check_field('add','contacts'))" :disabled="disabledObj['contacts_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contacts')">{{form['contacts']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','contact_number')) || (!form['booking_management__id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','single_price') || $check_field('add','single_price') || $check_field('set','single_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="单次价格" prop="single_price">
					<el-input id="single_price" v-model="form['single_price']" placeholder="请输入单次价格"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','single_price')) || (!form['booking_management__id'] && $check_field('add','single_price'))" :disabled="disabledObj['single_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','single_price')">{{form['single_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_times') || $check_field('add','service_times') || $check_field('set','service_times')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="服务次数" prop="service_times">
					<el-input-number id="service_times" v-model.number="form['service_times']"
						v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','service_times')) || (!form['booking_management__id'] && $check_field('add','service_times'))"></el-input-number>
					<div v-else-if="$check_field('get','service_times')">{{form['service_times']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total_price') || $check_field('add','total_price') || $check_field('set','total_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="总价格" prop="total_price">
					<el-input id="total_price" v-model="form['total_price']" placeholder="请输入总价格"
							  v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','total_price')) || (!form['booking_management__id'] && $check_field('add','total_price'))"  @focus="set_total_price()" :disabled="disabledObj['total_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_price')">{{form['total_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('set','time_of_appointment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约时间" prop="time_of_appointment">
					<el-date-picker :disabled="disabledObj['time_of_appointment_isDisabled']" v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','time_of_appointment')) || (!form['booking_management__id'] && $check_field('add','time_of_appointment'))" id="time_of_appointment"
						v-model="form['time_of_appointment']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','time_of_appointment')">{{form['time_of_appointment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_account') || $check_field('add','user_account') || $check_field('set','user_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户账号" prop="user_account">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_account(form['user_account']) }}
							<!--<el-input id="business_name" v-model="form['user_account']" placeholder="请输入用户账号"-->
							<!--v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','user_account')) || (!form['booking_management__id'] && $check_field('add','user_account'))" :disabled="disabledObj['user_account_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_account')">{{form['user_account']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['booking_management__id'] && $check_field('set','user_account')) || (!form['booking_management__id'] && $check_field('add','user_account'))" id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
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
				field: "booking_management__id",
				url_add: "~/api/booking_management_/add?",
				url_set: "~/api/booking_management_/set?",
				url_get_obj: "~/api/booking_management_/get_obj?",
				url_upload: "~/api/booking_management_/upload?",

				query: {
					"booking_management__id": 0,
				},

				form: {
					"service_type":'', // 服务类型
					"shop_address":'', //  店铺地址
					"contacts":'', // 联系人
					"contact_number":'', // 联系电话
					"single_price":'', // 单次价格
					"service_times":0, // 服务次数
					"total_price":'', // 总价格
					"time_of_appointment":'', // 预约时间
					"user_account": 0, // 用户账号
					"booking_management__id": 0, // ID

				},
				disabledObj:{
					"service_type_isDisabled": false,
					"shop_address_isDisabled": false,
					"contacts_isDisabled": false,
					"contact_number_isDisabled": false,
					"single_price_isDisabled": false,
					"total_price_isDisabled": false,
					"time_of_appointment_isDisabled": false,
					"user_account_isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],
				// 用户组
				group_user_user_account: "",

			}
		},
		methods: {
			set_total_price(){
				this.form.total_price =this.form.single_price * this.form.service_times
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
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["time_of_appointment"]) > 9999){
					this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/booking_management_/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/booking_management_/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/booking_management_/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/booking_management_/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/booking_management_/view','get');
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
