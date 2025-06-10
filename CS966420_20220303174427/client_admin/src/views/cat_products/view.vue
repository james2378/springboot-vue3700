<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','commodity_number') || $check_field('add','commodity_number') || $check_field('set','commodity_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品编号" prop="commodity_number">
					<el-input id="commodity_number" v-model="form['commodity_number']" placeholder="请输入商品编号"
							  v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','commodity_number')) || (!form['cat_products_id'] && $check_field('add','commodity_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','commodity_number')">{{form['commodity_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','trade_name') || $check_field('add','trade_name') || $check_field('set','trade_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品名称" prop="trade_name">
					<el-input id="trade_name" v-model="form['trade_name']" placeholder="请输入商品名称"
							  v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','trade_name')) || (!form['cat_products_id'] && $check_field('add','trade_name'))" :disabled="disabledObj['trade_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','trade_name')">{{form['trade_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','product_picture') || $check_field('add','product_picture') || $check_field('set','product_picture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品图片" prop="product_picture">
					<el-upload :disabled="disabledObj['product_picture_isDisabled']" id="product_picture" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_product_picture"
						:show-file-list="false" v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','product_picture')) || (!form['cat_products_id'] && $check_field('add','product_picture'))">
						<img v-if="form['product_picture']" :src="$fullUrl(form['product_picture'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','product_picture')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['product_picture'])" :preview-src-list="[$fullUrl(form['product_picture'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','commodity_price') || $check_field('add','commodity_price') || $check_field('set','commodity_price')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品价格" prop="commodity_price">
					<el-input id="commodity_price" v-model="form['commodity_price']" placeholder="请输入商品价格"
							  v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','commodity_price')) || (!form['cat_products_id'] && $check_field('add','commodity_price'))" :disabled="disabledObj['commodity_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','commodity_price')">{{form['commodity_price']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','merchandise_inventory') || $check_field('add','merchandise_inventory') || $check_field('set','merchandise_inventory')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品库存" prop="merchandise_inventory">
					<el-input id="merchandise_inventory" v-model="form['merchandise_inventory']" placeholder="请输入商品库存"
							  v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','merchandise_inventory')) || (!form['cat_products_id'] && $check_field('add','merchandise_inventory'))" :disabled="disabledObj['merchandise_inventory_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','merchandise_inventory')">{{form['merchandise_inventory']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','commodity_specifications') || $check_field('add','commodity_specifications') || $check_field('set','commodity_specifications')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品规格" prop="commodity_specifications">
					<el-input id="commodity_specifications" v-model="form['commodity_specifications']" placeholder="请输入商品规格"
							  v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','commodity_specifications')) || (!form['cat_products_id'] && $check_field('add','commodity_specifications'))" :disabled="disabledObj['commodity_specifications_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','commodity_specifications')">{{form['commodity_specifications']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','product_details') || $check_field('add','product_details') || $check_field('set','product_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="商品详情" prop="product_details">
					<el-input type="textarea" id="product_details" v-model="form['product_details']" placeholder="请输入商品详情"
						v-if="user_group === '管理员' || (form['cat_products_id'] && $check_field('set','product_details')) || (!form['cat_products_id'] && $check_field('add','product_details'))" :disabled="disabledObj['product_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','product_details')">{{form['product_details']}}</div>
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
				field: "cat_products_id",
				url_add: "~/api/cat_products/add?",
				url_set: "~/api/cat_products/set?",
				url_get_obj: "~/api/cat_products/get_obj?",
				url_upload: "~/api/cat_products/upload?",

				query: {
					"cat_products_id": 0,
				},

				form: {
					"commodity_number":this.$get_stamp(), // 商品编号
					"trade_name":'', // 商品名称
					"product_picture":'', // 商品图片
					"commodity_price":'', // 商品价格
					"merchandise_inventory":'', // 商品库存
					"commodity_specifications":'', // 商品规格
					"product_details":'', // 商品详情
					"cat_products_id": 0, // ID

				},
				disabledObj:{
					"commodity_number_isDisabled": false,
					"trade_name_isDisabled": false,
					"product_picture_isDisabled": false,
					"commodity_price_isDisabled": false,
					"merchandise_inventory_isDisabled": false,
					"commodity_specifications_isDisabled": false,
					"product_details_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传商品图片
			 * @param {Object} param图片参数
			 */
			upload_product_picture(param){
				this.uploadFile(param.file, "product_picture");
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
					bl = this.$check_action('/cat_products/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cat_products/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cat_products/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/cat_products/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/cat_products/view','get');
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
