<template>
	<div>
		<b-form-group v-if="$check_register_field('add','user_account','/user_registration/view')" id="input-group-0" label="用户账号" label-for="input-0">
			<b-form-input id="input-0" v-model="form['user_account']" type="text" placeholder="用户账号" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','gender','/user_registration/view')" id="input-group-1" label="性别" label-for="input-1">
			<b-form-select id="input-1" v-model="form['gender']" :options="getDropList(list_gender)"></b-form-select>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','age','/user_registration/view')" id="input-group-2" label="年龄" label-for="input-2">
			<b-form-input id="input-2" v-model="form['age']" type="text" placeholder="年龄" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','membership_level','/user_registration/view')" id="input-group-3" label="会员等级" label-for="input-3">
			<b-form-input id="input-3" v-model="form['membership_level']" type="text" placeholder="会员等级" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','member_discount','/user_registration/view')" id="input-group-4" label="会员折扣" label-for="input-4">
			<b-form-input id="input-4" v-model="form['member_discount']" type="text" placeholder="会员折扣" trim></b-form-input>
		</b-form-group>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: () => {
					return {

					}
				}
			}
		},
		data(){
			return {
				url_upload: "~/api/user_registration/upload?",
						list_gender: "男,女",
			}
		},
		methods: {
			// 下拉数据列表转换
			getDropList(e){
				let resultArr = [];
				if(e){
					let arr = e.split(",");
					arr.forEach(item => {
						resultArr.push({
							value: item,
							text: item
						})
					});
				}
				return resultArr;
			},
			/**
			 * 上传图片
			 * @param {Object} file 文件对象
			 * @param {key} 保存键名
			 */
			uploadFile(file, key = "img") {
				var _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', file[0]) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						// _this.form[key] = json.result.url;
						_this.$delete(_this.form,key);
						_this.$set(_this.form,key,json.result.url);
						console.log(_this.form[key])
					} else {
						_this.$toast('上传失败！');
					}
				});
			},
		}
	}
</script>

<style>
</style>
