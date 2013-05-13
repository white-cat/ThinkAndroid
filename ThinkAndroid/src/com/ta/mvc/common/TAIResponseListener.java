/*
 * Copyright (C) 2013  WhiteCat 白猫 (www.thinkandroid.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ta.mvc.common;

/**
 * @Title TAIResponseListener
 * @package om.ta.common.TABaseEntity
 * @Description TAIResponseListener是数据返回的一个监听器
 * @author 白猫
 * @date 2013-1-16 下午
 * @version V1.0
 */
public interface TAIResponseListener
{
	void onStart();

	void onSuccess(TAResponse response);

	void onRuning(TAResponse response);

	void onFailure(TAResponse response);

	void onFinish();
}
