import request from '@/utils/request'

const api_name = '/admin/cmn/dict'

export default {
  findByDictCode(dictCode) {
    return request({
      url: `${api_name}/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },

  findByParentId(parentId) {
    return request({
      url: `${api_name}/findChildData/${parentId}`,
      method: 'get'
    })
  }
}
