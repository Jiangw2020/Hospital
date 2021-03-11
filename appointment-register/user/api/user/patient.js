import request from '@/utils/request'

const api_name = `/api/user/patient`

export default {

  findList() {
    return request({
      url: `${api_name}/auth/findAll`,
      method: `get`
    })
  },

  getById(id) {
    return request({
      url: `${api_name}/auth/get/${id}`,
      method: 'get'
    })
  },

  save(patient) {
    return request({
      url: `${api_name}/auth/save`,
      method: 'post',
      data: patient
    })
  },

  updateById(patient) {
    return request({
      url: `${api_name}/auth/update`,
      method: 'put',
      data: patient
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/auth/remove/${id}`,
      method: 'delete'
    })
  }
}
