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
package com.ta.mvc.command;

import com.ta.mvc.common.TAIResponseListener;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;

/**
 * @Title TAICommand
 * @package com.ta.mvc.command
 * @Description TAICommand一个命令接口所有命令需要从此实现
 * @author 白猫
 * @date 2013-1-16 下午 16:51
 * @version V1.0
 */
public interface TAICommand
{
	TARequest getRequest();

	void setRequest(TARequest request);

	TAResponse getResponse();

	void setResponse(TAResponse response);

	void execute();

	TAIResponseListener getResponseListener();

	void setResponseListener(TAIResponseListener listener);

	void setTerminated(boolean terminated);

	boolean isTerminated();

}
