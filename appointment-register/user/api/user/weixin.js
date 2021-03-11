import request from '@/utils/request'

const api_name = `/api/user/weixin`

export default {

  getLoginParam() {
    return request({
      url: `${api_name}/getLoginParam`,
      method: `get`
    })
  }
}
