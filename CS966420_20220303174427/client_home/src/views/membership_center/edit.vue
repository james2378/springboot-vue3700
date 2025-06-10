<template>
	<div class="diy_edit page_membership_center" id="membership_center_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','member_number') || $check_field('add','member_number') || $check_field('get','member_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>会员编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_member_number" v-model="form['member_number']" placeholder="请输入会员编号" v-if="(form['member_number'] && $check_field('set','member_number')) || (!form['member_number'] && $check_field('add','member_number'))" :disabled="true"/>
							<span v-else-if="$check_field('get','member_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','member_nickname') || $check_field('add','member_nickname') || $check_field('get','member_nickname')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>会员昵称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_member_nickname" v-model="form['member_nickname']" placeholder="请输入会员昵称" v-if="(form['member_nickname'] && $check_field('set','member_nickname')) || (!form['member_nickname'] && $check_field('add','member_nickname'))"  :disabled="disabledObj['member_nickname_isDisabled']"/>
							<span v-else-if="$check_field('get','member_nickname')">{{ form[''] }}</span>
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
					<div v-if="user_group === '管理员' || $check_examine()" class="col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/membership_center/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
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
				url_get_obj: "~/api/membership_center/get_obj?",
				url_add: "~/api/membership_center/add?",
				url_set: "~/api/membership_center/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"member_number": "",
					"member_nickname": "",
					"membership_level": "",
					"member_discount": "",
					"user_account": 0,
					"membership_center_id": 0,
				},

				obj: {
					"member_number":this.$get_stamp(), // 会员编号
					"member_nickname":'', // 会员昵称
					"membership_level":'', // 会员等级
					"member_discount":'', // 会员折扣
					"user_account": 0, // 用户账号
					"examine_state": "未审核",
					"membership_center_id": 0,
				},

				// 表单字段
				form: {
					"member_number":this.$get_stamp(), // 会员编号
					"member_nickname":'', // 会员昵称
					"membership_level":'', // 会员等级
					"member_discount":'', // 会员折扣
					"user_account": 0, // 用户账号
					"examine_state": "未审核",
					"membership_center_id": 0,

				},
				disabledObj:{
					"member_number_isDisabled": false,
					"member_nickname_isDisabled": false,
					"membership_level_isDisabled": false,
					"member_discount_isDisabled": false,
					"user_account_isDisabled": false,
				},
				// 用户列表
				list_user_user_account: [],

				// ID字段
				field: "membership_center_id",
			}
		},
		methods: {
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
				this.$post("~/api/membership_center/upload?", form, (res) => {
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
