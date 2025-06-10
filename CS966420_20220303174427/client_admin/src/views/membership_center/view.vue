<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','member_number') || $check_field('add','member_number') || $check_field('set','member_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员编号" prop="member_number">
					<el-input id="member_number" v-model="form['member_number']" placeholder="请输入会员编号"
							  v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','member_number')) || (!form['membership_center_id'] && $check_field('add','member_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','member_number')">{{form['member_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','member_nickname') || $check_field('add','member_nickname') || $check_field('set','member_nickname')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员昵称" prop="member_nickname">
					<el-input id="member_nickname" v-model="form['member_nickname']" placeholder="请输入会员昵称"
							  v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','member_nickname')) || (!form['membership_center_id'] && $check_field('add','member_nickname'))" :disabled="disabledObj['member_nickname_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','member_nickname')">{{form['member_nickname']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','membership_level') || $check_field('add','membership_level') || $check_field('set','membership_level')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员等级" prop="membership_level">
					<el-input id="membership_level" v-model="form['membership_level']" placeholder="请输入会员等级"
							  v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','membership_level')) || (!form['membership_center_id'] && $check_field('add','membership_level'))" :disabled="disabledObj['membership_level_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','membership_level')">{{form['membership_level']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','member_discount') || $check_field('add','member_discount') || $check_field('set','member_discount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="会员折扣" prop="member_discount">
					<el-input id="member_discount" v-model="form['member_discount']" placeholder="请输入会员折扣"
							  v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','member_discount')) || (!form['membership_center_id'] && $check_field('add','member_discount'))" :disabled="disabledObj['member_discount_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','member_discount')">{{form['member_discount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','user_account') || $check_field('add','user_account') || $check_field('set','user_account')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户账号" prop="user_account">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_account(form['user_account']) }}
							<!--<el-input id="business_name" v-model="form['user_account']" placeholder="请输入用户账号"-->
							<!--v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','user_account')) || (!form['membership_center_id'] && $check_field('add','user_account'))" :disabled="disabledObj['user_account_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_account')">{{form['user_account']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['membership_center_id'] && $check_field('set','user_account')) || (!form['membership_center_id'] && $check_field('add','user_account'))" id="user_account" v-model="form['user_account']" :disabled="disabledObj['user_account_isDisabled']">
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
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
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
				field: "membership_center_id",
				url_add: "~/api/membership_center/add?",
				url_set: "~/api/membership_center/set?",
				url_get_obj: "~/api/membership_center/get_obj?",
				url_upload: "~/api/membership_center/upload?",

				query: {
					"membership_center_id": 0,
				},

				form: {
					"member_number":this.$get_stamp(), // 会员编号
					"member_nickname":'', // 会员昵称
					"membership_level":'', // 会员等级
					"member_discount":'', // 会员折扣
					"user_account": 0, // 用户账号
					"examine_state": "未审核",
					"membership_center_id": 0, // ID

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
				// 用户组
				group_user_user_account: "",

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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/membership_center/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_center/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_center/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_center/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/membership_center/view','get');
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
