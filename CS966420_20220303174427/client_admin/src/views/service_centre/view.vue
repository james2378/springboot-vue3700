<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','service_type') || $check_field('add','service_type') || $check_field('set','service_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="服务类型" prop="service_type">
					<el-select id="service_type" v-model="form['service_type']"
						v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','service_type')) || (!form['service_centre_id'] && $check_field('add','service_type'))">
						<el-option v-for="o in list_service_type" :key="o['service_type']" :label="o['service_type']"
							:value="o['service_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','service_type')">{{form['service_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','single_price') || $check_field('add','single_price') || $check_field('set','single_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="单次价格" prop="single_price">
					<el-input id="single_price" v-model="form['single_price']" placeholder="请输入单次价格"
							  v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','single_price')) || (!form['service_centre_id'] && $check_field('add','single_price'))" :disabled="disabledObj['single_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','single_price')">{{form['single_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','shop_address') || $check_field('add','shop_address') || $check_field('set','shop_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label=" 店铺地址" prop="shop_address">
					<el-input id="shop_address" v-model="form['shop_address']" placeholder="请输入 店铺地址"
							  v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','shop_address')) || (!form['service_centre_id'] && $check_field('add','shop_address'))" :disabled="disabledObj['shop_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','shop_address')">{{form['shop_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contacts') || $check_field('add','contacts') || $check_field('set','contacts')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系人" prop="contacts">
					<el-input id="contacts" v-model="form['contacts']" placeholder="请输入联系人"
							  v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','contacts')) || (!form['service_centre_id'] && $check_field('add','contacts'))" :disabled="disabledObj['contacts_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contacts')">{{form['contacts']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','contact_number')) || (!form['service_centre_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['service_centre_id'] && $check_field('set','remarks')) || (!form['service_centre_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
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
				field: "service_centre_id",
				url_add: "~/api/service_centre/add?",
				url_set: "~/api/service_centre/set?",
				url_get_obj: "~/api/service_centre/get_obj?",
				url_upload: "~/api/service_centre/upload?",

				query: {
					"service_centre_id": 0,
				},

				form: {
					"service_type":'', // 服务类型
					"single_price":'', // 单次价格
					"shop_address":'', //  店铺地址
					"contacts":'', // 联系人
					"contact_number":'', // 联系电话
					"remarks":'', // 备注
					"service_centre_id": 0, // ID

				},
				disabledObj:{
					"service_type_isDisabled": false,
					"single_price_isDisabled": false,
					"shop_address_isDisabled": false,
					"contacts_isDisabled": false,
					"contact_number_isDisabled": false,
					"remarks_isDisabled": false,
				},
				//服务类型选项列表
				list_service_type: [],

			}
		},
		methods: {
			/**
			 * 获取服务类型列表
			 */
			async get_list_service_type() {
				var json = await this.$get("~/api/service_classification/get_list?");
				if(json.result && json.result.list){
					this.list_service_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/service_centre/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/service_centre/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/service_centre/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/service_centre/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/service_centre/view','get');
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
			this.get_list_service_type();
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
