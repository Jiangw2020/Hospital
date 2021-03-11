import request from '@/utils/request'

const api_name = `/api/ucenter/wx`

export default {

  getLoginParam() {
    return request({
      url: `${api_name}/getLoginParam`,
      method: `get`
    })
  }
}
