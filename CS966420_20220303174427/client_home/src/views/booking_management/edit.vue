<template>
	<div class="diy_edit page_booking_management" id="booking_management_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','service_type') || $check_field('add','service_type') || $check_field('get','service_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>服务类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_service_type" v-model="form['service_type']" placeholder="请输入服务类型" v-if="(form['service_type'] && $check_field('set','service_type')) || (!form['service_type'] && $check_field('add','service_type'))"  :disabled="disabledObj['service_type_isDisabled']"/>
							<span v-else-if="$check_field('get','service_type')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','shop_address') || $check_field('add','shop_address') || $check_field('get','shop_address')" class="col-12 col-md-6">
						<div class="diy_title">
							<span> 店铺地址:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_shop_address" v-model="form['shop_address']" placeholder="请输入 店铺地址" v-if="(form['shop_address'] && $check_field('set','shop_address')) || (!form['shop_address'] && $check_field('add','shop_address'))"  :disabled="disabledObj['shop_address_isDisabled']"/>
							<span v-else-if="$check_field('get','shop_address')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contacts') || $check_field('add','contacts') || $check_field('get','contacts')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>联系人:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contacts" v-model="form['contacts']" placeholder="请输入联系人" v-if="(form['contacts'] && $check_field('set','contacts')) || (!form['contacts'] && $check_field('add','contacts'))"  :disabled="disabledObj['contacts_isDisabled']"/>
							<span v-else-if="$check_field('get','contacts')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))"  :disabled="disabledObj['contact_number_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','single_price') || $check_field('add','single_price') || $check_field('get','single_price')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>单次价格:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_single_price" v-model="form['single_price']" placeholder="请输入单次价格" v-if="(form['single_price'] && $check_field('set','single_price')) || (!form['single_price'] && $check_field('add','single_price'))"  :disabled="disabledObj['single_price_isDisabled']"/>
							<span v-else-if="$check_field('get','single_price')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','service_times') || $check_field('add','service_times') || $check_field('get','service_times')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>服务次数:
							</span>
						</div>
						<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_service_times" v-model.number="form['service_times']" placeholder="请输入服务次数" v-if="(form['service_times'] && $check_field('set','service_times')) || (!form['service_times'] && $check_field('add','service_times'))" :disabled="disabledObj['service_times_isDisabled']" />
							<span v-else-if="$check_field('get','service_times')">{{ form[''] }}</span>
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
					<div v-if="$check_field('set','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('get','time_of_appointment')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约时间:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['time_of_appointment_isDisabled']" id="form_time_of_appointment" v-model="form['time_of_appointment']" placeholder="请输入预约时间" v-if="(form['time_of_appointment'] && $check_field('set','time_of_appointment')) || (!form['time_of_appointment'] && $check_field('add','time_of_appointment'))" />
							<span v-else-if="$check_field('get','time_of_appointment')">{{ form[''] }}</span>
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
				url_get_obj: "~/api/booking_management/get_obj?",
				url_add: "~/api/booking_management/add?",
				url_set: "~/api/booking_management/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"service_type": "",
					"shop_address": "",
					"contacts": "",
					"contact_number": "",
					"single_price": "",
					"service_times": 0,
					"total_price": "",
					"time_of_appointment": "",
					"user_account": 0,
					"booking_management_id": 0,
				},

				obj: {
					"service_type":'', // 服务类型
					"shop_address":'', //  店铺地址
					"contacts":'', // 联系人
					"contact_number":'', // 联系电话
					"single_price":'', // 单次价格
					"service_times":0, // 服务次数
					"total_price":'', // 总价格
					"time_of_appointment": "1970-01-01 00:00:00",
					"user_account": 0, // 用户账号
					"booking_management_id": 0,
				},

				// 表单字段
				form: {
					"service_type":'', // 服务类型
					"shop_address":'', //  店铺地址
					"contacts":'', // 联系人
					"contact_number":'', // 联系电话
					"single_price":'', // 单次价格
					"service_times":0, // 服务次数
					"total_price":'', // 总价格
					"time_of_appointment": "1970-01-01 00:00:00",
					"user_account": 0, // 用户账号
					"booking_management_id": 0,

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

				// ID字段
				field: "booking_management_id",
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
				this.$post("~/api/booking_management/upload?", form, (res) => {
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
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd")
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
