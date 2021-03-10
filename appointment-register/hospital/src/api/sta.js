import request from '@/utils/request'

const api_name = '/admin/statistics'

export default {

    getCountMap(searchObj) {
        return request({
            url: `${api_name}/getCountMap`,
            method: 'get',
            params: searchObj
        })
    }
}
